/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.tools.doc.extractor.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;



public class HttpUtility {

	private static Logger logger = Logger
			.getLogger(HttpUtility.class.getName());

	/**
	 * Get data from HTTP POST method.
	 * 
	 * This method uses
	 * <ul>
	 * <li>Connection timeout = 300000 (5 minutes)</li>
	 * <li>Socket/Read timeout = 300000 (5 minutes)</li>
	 * <li>Socket Read Buffer = 10485760 (10MB) to provide more space to read</li>
	 * </ul>
	 * -- in case the site is slow
	 * 
	 * @return
	 * @throws MalformedURLException
	 * @throws URISyntaxException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws Exception
	 */
	public static String getPostData(String sourceUrl, String postString)
			throws MalformedURLException, URISyntaxException,
			UnsupportedEncodingException, IOException, ClientProtocolException,
			Exception {
		String result = "";
		URL targetUrl = new URL(sourceUrl);

		/*
		 * Create http parameter to set Connection timeout = 300000 Socket/Read
		 * timeout = 300000 Socket Read Buffer = 10485760
		 */
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 300000);
		HttpConnectionParams.setSocketBufferSize(httpParams, 10485760);
		HttpConnectionParams.setSoTimeout(httpParams, 300000);

		// set the http param to the DefaultHttpClient
		HttpClient httpClient = new DefaultHttpClient(httpParams);

		// create POST method and set the URL and POST data
		HttpPost post = new HttpPost(targetUrl.toURI());
		StringEntity entity = new StringEntity(postString, "UTF-8");
		post.setEntity(entity);
		logger.info("Execute the POST request with all input data");
		// Execute the POST request on the http client to get the response
		HttpResponse response = httpClient
				.execute(post, new BasicHttpContext());
		if (null != response && response.getStatusLine().getStatusCode() == 200) {
			HttpEntity httpEntity = response.getEntity();
			if (null != httpEntity) {
				long contentLength = httpEntity.getContentLength();
				logger.info("Content length: " + contentLength);
				// no data, if the content length is insufficient
				if (contentLength <= 0) {
					return "";
				}

				// read the response to String
				InputStream responseStream = httpEntity.getContent();
				if (null != responseStream) {
					BufferedReader reader = null;
					StringWriter writer = null;
					try {
						reader = new BufferedReader(new InputStreamReader(
								responseStream));
						writer = new StringWriter();
						int count = 0;
						int size = 1024 * 1024;
						char[] chBuff = new char[size];
						while ((count = reader.read(chBuff, 0, size)) >= 0) {
							writer.write(chBuff, 0, count);
						}
						result = writer.getBuffer().toString();
					} catch (Exception ex) {
						ex.printStackTrace();
						throw ex;
					} finally {
						IOUtils.closeQuietly(responseStream);
						IOUtils.closeQuietly(reader);
						IOUtils.closeQuietly(writer);
					}
				}
			}
		}
		logger.info("data read complete");
		return result;
	}

	public static byte[] getFile(String sourceUrl) throws Exception {
        byte[] result = null;

		/*
		 * Create http parameter to set Connection timeout = 300000 Socket/Read
		 * timeout = 300000 Socket Read Buffer = 10485760
		 */
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 300000);
		HttpConnectionParams.setSocketBufferSize(httpParams, 10485760);
		HttpConnectionParams.setSoTimeout(httpParams, 300000);

		// set the http param to the DefaultHttpClient
		HttpClient httpClient = new DefaultHttpClient(httpParams);

		// create POST method and set the URL and POST data
		HttpGet httpGet = new HttpGet(sourceUrl);
		
		logger.info("Execute the GET request ...");
		// Execute the POST request on the http client to get the response
		HttpResponse response = httpClient
				.execute(httpGet, new BasicHttpContext());
		if (null != response && response.getStatusLine().getStatusCode() == 200) {
			HttpEntity httpEntity = response.getEntity();
			if (null != httpEntity) {
				
				// read the response to String
				InputStream responseStream = httpEntity.getContent();
				if (null != responseStream) {
					BufferedInputStream reader = null;
                    ByteArrayOutputStream baos=null;
					try {
                        baos = new ByteArrayOutputStream();
						reader = new BufferedInputStream(responseStream);
						int count = 0;
						int size = 1024 * 1024;
						byte[] chBuff = new byte[size];
						while ((count = reader.read(chBuff, 0, size)) >= 0) {
							baos.write(chBuff, 0, count);
						}
						result = baos.toByteArray();
					} catch (Exception ex) {
						ex.printStackTrace();
						throw ex;
					} finally {
						IOUtils.closeQuietly(responseStream);
						IOUtils.closeQuietly(reader);
						IOUtils.closeQuietly(baos);
					}
				}
			}
		}
		logger.info("data read complete");
		return result;
    }

	public static byte[] getExcelFromPost(String sourceUrl, String postString)
			throws Exception {
		byte[] result = null;
		URL targetUrl = new URL(sourceUrl);

		/*
		 * Create http parameter to set Connection timeout = 300000 Socket/Read
		 * timeout = 300000 Socket Read Buffer = 10485760
		 */
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 300000);
		HttpConnectionParams.setSocketBufferSize(httpParams, 10485760);
		HttpConnectionParams.setSoTimeout(httpParams, 300000);

		// set the http param to the DefaultHttpClient
		HttpClient httpClient = new DefaultHttpClient(httpParams);
		// create POST method and set the URL and POST data
		HttpPost post = new HttpPost(targetUrl.toURI());
		
		post.addHeader("Host",	targetUrl.getHost());
		post.addHeader("User-Agent",	"Mozilla/5.0 (Windows NT 6.0; rv:8.0) Gecko/20100101 Firefox/8.0");
		post.addHeader("Accept",	"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		post.addHeader("Accept-Language",	"en-gb,en;q=0.5");
		post.addHeader("Accept-Encoding",	"gzip, deflate");
		post.addHeader("Accept-Charset",	"ISO-8859-1,utf-8;q=0.7,*;q=0.7");
		post.addHeader("Connection",	"keep-alive");
		post.addHeader("Content-Type",	"application/x-www-form-urlencoded");
		post.addHeader("X-Requested-With",	"XMLHttpRequest");
		
		StringEntity entity = new StringEntity(postString, "UTF-8");
		post.setEntity(entity);
		logger.info("Execute the POST request with all input data");
		// Execute the POST request on the http client to get the response
		HttpResponse response = httpClient
				.execute(post, new BasicHttpContext());
		if (null != response && response.getStatusLine().getStatusCode() == 200) {
			HttpEntity httpEntity = response.getEntity();
			if (null != httpEntity) {
				long contentLength = httpEntity.getContentLength();
				logger.info("Content length: " + contentLength);
				logger.info("Content type: " + httpEntity.getContentType().getValue());
				// no data, if the content length is insufficient
				if (contentLength <= 0) {
					return null;
				}

				// read the response to String
				InputStream responseStream = httpEntity.getContent();
				if (null != responseStream) {
					result = IOUtils.toByteArray(responseStream);
					
				}
			}
		}
		logger.info("data read complete :: length " + result.length);
		return result;
	}
	
	
	public static DefaultHttpClient getDefaultHttpClient() {
		try {
			
			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
			HttpConnectionParams.setConnectionTimeout(params, 300000);
			HttpConnectionParams.setSocketBufferSize(params, 10485760);
			HttpConnectionParams.setSoTimeout(params, 300000);

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(
					params, registry);

			DefaultHttpClient httpClient = new DefaultHttpClient(ccm, params);
			
			return httpClient;
		} catch (Exception e) {
			e.printStackTrace();
			return new DefaultHttpClient();
		}
	}
    
    public static DefaultHttpClient getLoginHttpClient(String userName, String password) {
		try {
			
			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
			HttpConnectionParams.setConnectionTimeout(params, 300000);
			HttpConnectionParams.setSocketBufferSize(params, 10485760);
			HttpConnectionParams.setSoTimeout(params, 300000);

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(
					params, registry);

			DefaultHttpClient httpClient = new DefaultHttpClient(ccm, params);
			httpClient.getCredentialsProvider().setCredentials(
					new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT,
							AuthScope.ANY_REALM),
					new UsernamePasswordCredentials(userName, password));
			return httpClient;
		} catch (Exception e) {
			e.printStackTrace();
			return new DefaultHttpClient();
		}
	}
    
    
	
	public static HttpClient getDefaultHttpsClient() {
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
	        trustStore.load(null, null);
	        
			SSLSocketFactory sf = new DefaultSecureSocketFactory(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
			HttpConnectionParams.setConnectionTimeout(params, 300000);
			HttpConnectionParams.setSocketBufferSize(params, 10485760);
			HttpConnectionParams.setSoTimeout(params, 300000);

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			registry.register(new Scheme("https", sf, 443));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(
					params, registry);

			DefaultHttpClient httpClient = new DefaultHttpClient(ccm, params);
			HttpClientBuilder b =HttpClientBuilder.create();
            return b.build();
			//return httpClient;
		} catch (Exception e) {
			e.printStackTrace();
			return new DefaultHttpClient();
		}
	}
	
	
	public static DefaultHttpClient getLoginHttpsClient(String userName, String password) {
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
	        trustStore.load(null, null);
	        
			SSLSocketFactory sf = new DefaultSecureSocketFactory(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
			HttpConnectionParams.setConnectionTimeout(params, 300000);
			HttpConnectionParams.setSocketBufferSize(params, 10485760);
			HttpConnectionParams.setSoTimeout(params, 300000);

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			registry.register(new Scheme("https", sf, 443));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(
					params, registry);
			
			DefaultHttpClient httpClient = new DefaultHttpClient(ccm, params);
			httpClient.getCredentialsProvider().setCredentials(
					new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT,
							AuthScope.ANY_REALM),
					new UsernamePasswordCredentials(userName, password));
			return httpClient;
		} catch (Exception e) {
			e.printStackTrace();
			return new DefaultHttpClient();
		}
	}



	public static String readContentAsText(HttpResponse response) 
			throws IllegalStateException, IOException {
		if(null == response)
			return null;
		byte[] result = null;
		HttpEntity httpEntity = response.getEntity();
		if (null != httpEntity) {
			long contentLength = httpEntity.getContentLength();
			logger.info("Content length: " + contentLength);
			logger.info("Content type: " + httpEntity.getContentType().getValue());
			// read the response to String
			InputStream responseStream = httpEntity.getContent();
			if (null != responseStream) {
				result = IOUtils.toByteArray(responseStream);
				
			}
		}
		
		if(null != result && result.length > 0)
			return new String(result);
		
		return null;
	}
	
	public static byte[] readContentAsByte(HttpResponse response) 
			throws IllegalStateException, IOException {
		if(null == response)
			return null;
		byte[] result = null;
		HttpEntity httpEntity = response.getEntity();
		if (null != httpEntity) {
			long contentLength = httpEntity.getContentLength();
			logger.info("Content length: " + contentLength);
			logger.info("Content type: " + httpEntity.getContentType().getValue());
			// read the response to String
			InputStream responseStream = httpEntity.getContent();
			if (null != responseStream) {
				result = IOUtils.toByteArray(responseStream);
				
			}
		}
		
		return result;
	}
	
	public static byte[] readResponseContent(HttpResponse response) 
			throws IllegalStateException, IOException {
		if(null == response)
			return null;
		byte[] result = null;
		HttpEntity httpEntity = response.getEntity();
		if (null != httpEntity) {
			long contentLength = httpEntity.getContentLength();
			logger.info("Content length: " + contentLength);
			logger.info("Content type: " + httpEntity.getContentType().getValue());
			// read the response to String
			InputStream responseStream = httpEntity.getContent();
			if (null != responseStream) {
				result = IOUtils.toByteArray(responseStream);
			}
		}
		return result;
	}
	
	public static byte[] readFileFromPOST(String sourceUrl)  throws IOException{
		return readFileFromPOST(sourceUrl, null);
	}
	
	public static byte[] readFileFromPOST(String sourceUrl, 
			Map<String, String> reqParams) throws IOException{
		HttpClient httpClient = null;
		try{
			httpClient = getDefaultHttpClient();
			HttpPost httpRequest = new HttpPost(sourceUrl) ;
			if(null != reqParams && reqParams.size() > 0){
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (String param : reqParams.keySet()) {
					paramList.add(new BasicNameValuePair(param, reqParams.get(param)));
				}
				StringEntity reqEntity = new StringEntity(
						URLEncodedUtils.format(paramList, "UTF-8"), "UTF-8");
				httpRequest.setEntity(reqEntity);
			}
			
			HttpResponse response = httpClient.execute(httpRequest);
			
			return readResponseContent(response);
		} finally {
			if(null != httpClient)
				httpClient.getConnectionManager().shutdown();
		}
	}
	
	
}

class DefaultSecureSocketFactory extends SSLSocketFactory {
	private SSLContext sslContext = SSLContext.getInstance("SSLv3");

	public DefaultSecureSocketFactory(KeyStore trustStore)
			throws NoSuchAlgorithmException, KeyManagementException,
			KeyStoreException, UnrecoverableKeyException {
		super(trustStore);
		TrustManager tm = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};

		sslContext.init(null, new TrustManager[] { tm }, null);
	}

		public Socket createSocket(Socket socket, String host, int port,
			boolean autoClose) throws IOException, UnknownHostException {
		return sslContext.getSocketFactory().createSocket(socket, host, port,
				autoClose);
	}

		public Socket createSocket() throws IOException {
		return sslContext.getSocketFactory().createSocket();
	}
	
	
}
