/*
	title:	visit url scraper
	desc:	follows urls tracks cookies etc
	author: Joel Aemmer
	date:	12/1/08

*/


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;



class visitURL
{
	log l = new log();

	//tld array
	String[] tlds = {".com",".net",".org",".edu",".biz",".us",".info"};

	String last_url = "";
	String cookie[] = new String[10];
	String cookieS[] = new String[10];
	String cookie_domain[] = new String[10];
	
	public String fileLocation;
	public String fileType;

	visitURL(){
		for(int i=0;i<cookie.length;i++){
			cookie[i] = "";
			cookieS[i] = "";
			cookie_domain[i] = "";
		}
	}

	String visitURL(String url){
		String host = "";
		String protocol = "";
		String method = "POST";
		String quString = "";
		String path = "";
		try {
			URL url2 = new URL(url);
			protocol = url2.getProtocol();
			host = url2.getHost();
			path = url2.getPath();
			
			quString = url2.getQuery();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return visitURL(protocol + "://" + host + path, true, method, 0,quString);
	}

	String visitURL(String url, boolean followRedirect){
		return visitURL(url, followRedirect, "POST", 0,"");
	}

	String visitURL(String url, String methodType){
		return visitURL(url, true, methodType, 0,"");
	}

	String visitURL(String url, String methodType, String postContent){
		return visitURL(url, true, methodType, 0,postContent);
	}

	String visitURL(String url, boolean followRedirect, String methodType){
		return visitURL(url, followRedirect, methodType, 0,"");
	}

	String visitURL(String url, boolean followRedirect, String methodType, int maxLines){
		return visitURL(url, followRedirect, methodType, maxLines,"");
	}

	String visitURL(String url, boolean followRedirect, String methodType, int maxLines,String postContent){
		String file = "";

		HttpURLConnection connection = null;
		URL authUrlOpen = null;
		String redirect_url = "";
		boolean isBinaryFile = false;
		l.log_msg("VISITING URL - " + url);

		try{
			//used to find out the domain
			int tld_loc = -1;
			int tld_length = -1;

			for(int j=0;j<tlds.length;j++){
				if(tld_loc == -1){
					tld_loc = url.indexOf(tlds[j]);
					tld_length = tlds[j].length();
				}
			}

			int host_start = url.indexOf("https://");

			if(host_start == -1){
				host_start = url.indexOf("http://")+7;
			}else{
				host_start = host_start +8;
			}

			String host = url.substring(host_start,tld_loc+tld_length);
			int domain_start = 0;
			if(host.indexOf(".") < tld_loc)
				domain_start = host.indexOf(".");

			String domain = host.substring(domain_start,host.length());

			//TODO - set this to fix fragnet since it  had both www. and .  domains being seperated. i'm not sure if it will break someone lese
			//I think it's right though to always save the cookie based on domain. then just as long as the domain is part of the host param, then it's okay (or just ignore host)
			host= domain.substring(1);
			domain = host;

			int cookie_number;
			for(cookie_number=0;cookie_number<this.cookie.length;cookie_number++){
				if(cookie_domain[cookie_number].equals(domain)){
					break;
				}else if(cookie_domain[cookie_number].equals(host)){
					break;
				}else if(cookie_domain[cookie_number]==""){
					break;
				}
			}

			String cookie = this.cookie[cookie_number];
			String cookieS = this.cookieS[cookie_number];

			authUrlOpen = new URL(url);
			connection = (HttpURLConnection)authUrlOpen.openConnection();

			//set all required HTTP headers to post
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod(methodType);
			connection.setInstanceFollowRedirects(false);

			if(postContent.length() > 0){
				connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			}else{
				connection.setRequestProperty("Content-type", "application/text");
			}
			//connection.setRequestProperty("Host", host);
			connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
			//connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			//connection.setRequestProperty("Accept-Language", "en-us,en;q=0.5");
			//connection.setRequestProperty("Accept-Encoding", "gzip,deflate");
			//connection.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
			//connection.setRequestProperty("Keep-Alive", "300");
			//connection.setRequestProperty("Connection", "keep-alive");
			connection.setRequestProperty("Referer", last_url);

			last_url = url;

/*
			int cookie_number;
			for(cookie_number=0;cookie_number<this.cookie.length;cookie_number++){
				if(cookie_domain[cookie_number].equals(domain)){
					break;
				}else if(cookie_domain[cookie_number]==""){
					break;
				}
			}

			String cookie = this.cookie[cookie_number];
			String cookieS = this.cookieS[cookie_number];
*/

			if(url.substring(0,5).equals("https")){
				connection.setRequestProperty("Cookie", cookie + cookieS);
			}else{
				connection.setRequestProperty("Cookie", cookie);
			}

	//		l.log_msg( cookie );

			connection.setRequestProperty("Content-Length", String.valueOf(postContent.length()));
			if(postContent.length() > 0 && methodType.equals("POST")){
				// Send POST output.
				DataOutputStream  printout = new DataOutputStream (connection.getOutputStream ());
			    printout.writeBytes (postContent);
			    printout.flush ();
			    printout.close ();

	//		    l.log_msg( postContent );
			}


			////System.out.println("About to read cookie");
			//log_msg("About to read cookie");
			// Get all cookies from the server.
			// Note: The first call to getHeaderFieldKey() will implicit send
			// the HTTP request to the server.

			String key = "";
			if (connection != null)
			{
				for (int i=0; ; i++) {
					String headerName = connection.getHeaderFieldKey(i);
					String headerValue = connection.getHeaderField(i);

//					l.log_msg("    " + headerName + ": " +	headerValue);

					if (headerName == null && headerValue == null) {

						//l.log_msg( "header name and value are null" );

						// No more headers
						if(redirect_url.length() > 0 && followRedirect){
							l.log_msg("redirecting...");
							return visitURL(redirect_url, true, "GET", maxLines);
						}

						break;
					}
					if("Content-Disposition".equals(headerName)){
						if(headerValue != null){
							if(headerValue.contains("attachment")){
								int index = headerValue.indexOf('=');
								if(index >= 0){
									String ss = headerValue.substring(index+1);
									l.log_msg("Content File: "+ss);
									String type = ss.substring(ss.indexOf('.'));
									if(type.equalsIgnoreCase(fileType)){
										
									}
								}
							}
						}
					}
					if ("Set-Cookie".equalsIgnoreCase(headerName)) {
						// Parse cookie
						String[] fields = headerValue.split(";\\s*");

						//String cookieValue = fields[0];
						boolean secure = false;

						for(int j=0;j<fields.length;j++){
							if(fields[j].equals("Secure")){
								secure = true;
							}
						}

						updateCookie(host,fields,secure);

						//cookie += cookieValue + ";";
					}
					if(headerName != null && headerName.equals("Location")){

						if(headerValue.indexOf("http")==-1)
							headerValue = url.substring(0,tld_loc+tld_length) + "/" + headerValue;

						//if(headerValue.indexOf(url.substring(0,tld_loc+tld_length)) > -1){
							//l.log_msg("redirecting...");
							//l.log_msg( "redirect_url = " + redirect_url );
							redirect_url = headerValue;
							//return visitURL(headerValue, false, "GET", maxLines);
						//}else{
							//l.log_msg("redirecting to outside domain, skipping: " + headerValue);
						//}

					}
				}



				StringBuffer sb = new StringBuffer();
				BufferedReader reader = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));

				//outputs to a file
				/*
				char chars[] = new char[1024];
				int len = 0;
				//Write chunks of characters to the StringBuffer
				PrintWriter out = new PrintWriter(new FileWriter("out.html"),true);

				while ((len = reader.read(chars, 0, chars.length)) >= 0)
				{
					sb.append(chars, 0, len);
					out.write(chars, 0, len);
				}
				out.close();
				*/

				String line = "";
				//String file = "";
				//int start_line = 0;



				if(maxLines > 0){
					l.log_msg("partial download " + maxLines + " lines");
					for(int line_num =0; (line = reader.readLine()) != null && line_num < maxLines; line_num++)
					{
						file += line;
					}
				}else{
					/*
					for(int line_num =0; (line = reader.readLine()) != null; line_num++)
					{
						file += line;
					}
					*/

					char chars[] = new char[1024];
					int len = 0;
					while ((len = reader.read(chars, 0, chars.length)) >= 0)
					{
						sb.append(chars, 0, len);
					}

					file = sb.toString();
				}

				int refresh = file.indexOf("URL=",file.indexOf("http-equiv=\"refresh")+1);
				if(file.indexOf("http-equiv=\"refresh") > -1 && refresh > -1 && followRedirect){
					l.log_msg("redirecting2...");
					file = visitURL(file.substring(refresh+4,file.indexOf("\"",refresh+4)),true,"GET");
				}

			}

		}catch(FileNotFoundException e){
			file = "Invalid Item";
		}catch(Exception e){
			e.printStackTrace();
			l.log_msg(e.toString());
		}

		return file;
	}


	void updateCookie(String host, String[] fields, boolean secureOnly){
		String domain = host;
		boolean domain_found=false;
		for(int j=0;j<fields.length;j++){
			int domain_loc = fields[j].indexOf("domain=");
			if(domain_loc==-1)
				domain_loc = fields[j].indexOf("Domain=");
			if(domain_loc > -1){
				domain = fields[j].substring(domain_loc+7,fields[j].length());
				break;
			}
		}

		//TODO - set to ignore the host for now
		host = domain;

		int cookie_number;
		for(cookie_number=0;cookie_number<this.cookie.length;cookie_number++){
			if(cookie_domain[cookie_number].equals(domain)){
				break;
			}else if(cookie_domain[cookie_number]==""){
				break;
			}
		}

		String cookie = this.cookie[cookie_number];
		String cookieS = this.cookieS[cookie_number];

		String cookieValue = fields[0];
		String varName = "", varValue = "";
		int cookieStart = 0, cookieEnd = 0;

		varName  = cookieValue.substring(0,cookieValue.indexOf("=")+1);
		varValue = cookieValue.substring(cookieValue.indexOf("=")+1,cookieValue.length());

		if(secureOnly){
			if(cookieS.indexOf(varName) >= 0){
				cookieStart = cookieS.indexOf(varName) + varName.length();
				cookieEnd   = cookieS.indexOf(";", cookieStart);

				cookieS = cookieS.substring(0,cookieStart) + varValue + cookieS.substring(cookieEnd,cookieS.length());
			}else{
				cookieS += cookieValue + ";";
			}
		}else{
			if(cookie.indexOf(varName) >= 0){
				cookieStart = cookie.indexOf(varName) + varName.length();
				cookieEnd   = cookie.indexOf(";", cookieStart);

				cookie = cookie.substring(0,cookieStart) + varValue + cookie.substring(cookieEnd,cookie.length());
			}else{
				cookie += cookieValue + ";";
			}
			if(varValue.equals("EXPIRED")){
				cookie = cookie.replaceAll(varName + "EXPIRED;", "");
			}
		}

		for(int j=0;j<fields.length;j++){
			if(fields[j].indexOf("expires=") > -1){
				int start_d = fields[j].indexOf("expires=")+8;
				int end_d = fields[j].length();
				String date_e = fields[j].substring(start_d,end_d);

		        DateFormat df = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss z");

		        int year_length = date_e.substring(date_e.lastIndexOf("-")+1,date_e.indexOf(" ",date_e.lastIndexOf("-"))).length();
		        if(year_length == 2){
		        	df = new SimpleDateFormat("EEE, dd-MMM-yy HH:mm:ss z");
		        }

		        try
		        {
		            java.util.Date exp = df.parse(date_e);
		            if(new java.util.Date().after(exp)){
						int start = cookie.indexOf(varName);
						if(start > -1){
							int end = cookie.indexOf(";",start)+1;
							cookie = cookie.substring(0,start) + cookie.substring(end,cookie.length());
						}
		            }
		        } catch (ParseException e)
		        {
		            e.printStackTrace();
		        }

			}
		}


		this.cookie[cookie_number] = cookie;
		this.cookieS[cookie_number] = cookieS;
		this.cookie_domain[cookie_number] = domain;

	}

	//getting values from the cookie for forms is the wrong approach. parse them out of the html instead
/*
	String GetCookieVal( String domain, String cookieName )
	{
		int cookie_number;
		for(cookie_number=0;cookie_number<this.cookie.length;cookie_number++){
			if(cookie_domain[cookie_number].equals(domain)){
				break;
			}else if(cookie_domain[cookie_number]==""){
				break;
			}
		}

		String cookie = this.cookie[cookie_number];

		int start = cookie.indexOf( cookieName );
		int end = cookie.indexOf(";",start);
		String val = cookie.substring(start+cookieName.length()+1, end);

		return val;
	}
*/
}


class log{
	
	public void log_msg(String s){
		System.out.println(s);
	}
}