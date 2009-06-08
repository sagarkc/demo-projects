/*
	title:	Supplier Feeds
	desc:	Downloads latest copy of supplier data feeds
	author: Joel Aemmer
	date:	11/12/06

	TODO:
	-
 */

import java.io.*;
import java.util.*;
import java.sql.*;
import java.util.zip.*;
import java.text.*;
import java.net.*;

import com.enterprisedt.net.ftp.FTPClient;
import com.enterprisedt.net.ftp.FTPMessageCollector;
import com.enterprisedt.net.ftp.FTPTransferType;
import com.enterprisedt.net.ftp.FTPConnectMode;
import com.enterprisedt.util.debug.Level;
import com.enterprisedt.util.debug.Logger;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.*;

class supplier_feeds implements Runnable{

/*	static String DB_HOST		=	"192.168.1.2";
	static String DB_DB			=	"catalog";
	static String DB_USER		=	"supplier_feeds";
	static String DB_PASS		=	"Bish4Nup948";

*/	static String DB_HOST		=	"localhost";
	static String DB_DB			=	"catalog_dev";
	static String DB_USER		=	"root";
	static String DB_PASS		=	"root123";
	
	public static final String SUPPLIERS_DIR = "D:\\NorthPacificTrading\\suppliers\\";
	public static final String DONE = "DONE";
	public static final String FAILED = "FAILED";
	
	Connection sqlconnection;
	ResultSet rs;
	File f;
	String supplier;
	Integer suppliers_feed_id;
	String RequestMethod = "POST";
	URL url, loginUrl;
	boolean isFormLogin = false;
	boolean isBasicLogin = false;
	boolean isAdvancedFTP = false;
	boolean isXLS = false;
	boolean isZip = false;
	boolean isZipXml = false;
	boolean isXml = false;
	String ftpHost, ftpUser, ftpPass, ftpDir, ftpFileLocation, ftpFileName;
	String username, password;// for basiclogin
	boolean updateUrl = false;
	String supplierUrl, formLoginUrl;
	public static FileWriter log;
	public static boolean isSuccessful[] = new boolean[1000];//assume 1000 suppliers max
	public static String supplierName[] = new String[1000];//assume 1000 suppliers max
	public static int supplierCount = 0;
	static Object lock = new Object(); //used to synchronize the threads

	supplier_feeds(){
		try {
			File logfile = new File("log.txt");
			if (!logfile.exists()) {
				logfile.createNewFile();
			}
			log = new FileWriter(logfile);
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}
	supplier_feeds(String supplier, int suppliers_feed_id){
		synchronized(this){
			supplierCount++;
			isSuccessful[supplierCount] = false;
			supplierName[supplierCount] = supplier + suppliers_feed_id;
		}
	}

	supplier_feeds(String supplier) {
		synchronized(this){
			supplierCount++;
			isSuccessful[supplierCount] = false;
			supplierName[supplierCount] = supplier;
		}

		this.supplier = supplier;
		suppliers_feed_id = 0;
		prepareFeeds();
	}


	public static void main(String[] args) {

		for(int i=0;i<1000;i++){
			isSuccessful[i] = true;
		}

		//db suppliers
		supplier_feeds s0 = new supplier_feeds();
		s0.log_msg("start: " + new java.util.Date());
		s0.getSuppliers();

		//manual configurations TODO: Un-comment it before final release
/*		supplier_feeds s1 = new supplier_feeds("ingramDesc");
		supplier_feeds s2 = new supplier_feeds("ingramInv");
		supplier_feeds s3 = new supplier_feeds("LNC");
		supplier_feeds s4 = new supplier_feeds("asiNew");
		supplier_feeds s5 = new supplier_feeds("asiPrice");
		supplier_feeds s6 = new supplier_feeds("jewelrySprite");
		supplier_feeds s7 = new supplier_feeds("dblPrice");
		supplier_feeds s8 = new supplier_feeds("dblAll");
		supplier_feeds s9 = new supplier_feeds("navarreAccessories");
		supplier_feeds s10 = new supplier_feeds("navarreVideoGames");
		supplier_feeds s11= new supplier_feeds("navarreSoftware");
		supplier_feeds s12 = new supplier_feeds("navarreMusic");
		supplier_feeds s13 = new supplier_feeds("navarreVideo");
		supplier_feeds s14 = new supplier_feeds("MotengInv");
		supplier_feeds s15 = new supplier_feeds("tatiana");
		supplier_feeds s16 = new supplier_feeds("tatianaInv");
		supplier_feeds s17 = new supplier_feeds("MotengDesc");
		supplier_feeds s18 = new supplier_feeds("navarreAccessoriesDesc");
		supplier_feeds s19 = new supplier_feeds("navarreVideoGamesDesc");
		supplier_feeds s20 = new supplier_feeds("navarreSoftwareDesc");
		supplier_feeds s21 = new supplier_feeds("navarreMusicDesc");
		supplier_feeds s22 = new supplier_feeds("navarreVideoDesc");

		Thread t1 = new Thread (s1);
		Thread t2 = new Thread (s2);
		Thread t3 = new Thread (s3);
		Thread t4 = new Thread (s4);
		Thread t5 = new Thread (s5);
		Thread t6 = new Thread (s6);
		Thread t7 = new Thread (s7);
		Thread t8 = new Thread (s8);
		Thread t9 = new Thread (s9);
		Thread t10 = new Thread (s10);
		Thread t11 = new Thread (s11);
		Thread t12 = new Thread (s12);
		Thread t13 = new Thread (s13);
		Thread t14 = new Thread (s14);
		Thread t15 = new Thread (s15);
		Thread t16 = new Thread (s16);
		Thread t17 = new Thread (s17);
		Thread t18 = new Thread (s18);
		Thread t19 = new Thread (s19);
		Thread t20 = new Thread (s20);
		Thread t21 = new Thread (s21);
		Thread t22 = new Thread (s22);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		t10.start();
		t11.start();
		t12.start();
		t13.start();
		t14.start();
		t15.start();
		t16.start();
		t17.start();
		t18.start();
		t19.start();
		t20.start();
		t21.start();
		t22.start();

*/
		//wait 30min for the threads to finish
		boolean isDone = false;
		try{
			for(int i=0;i<30 && !isDone;i++ ){//30 iterations=30mins
				isDone=true;
				Thread.currentThread().sleep(60 * 1000);//60 secs
				synchronized(lock){
					for(int j=1;j<=supplierCount;j++){
						if(!isSuccessful[j]){
							isDone = false;
						}
					}
				}
			}

		}catch(Exception e){
			s0.log_msg(e.toString());
			e.printStackTrace();
		}
		s0.log_msg("end: " + new java.util.Date());
		if(!isDone){
			s0.log_msg("shutting down early...");
			Runtime.getRuntime().exit(-1);
		}

	}

	private void getSuppliers() {

		try {
			String sql, supplier_url, foldername, file_type, dateFormat, formLoginUrl;

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			sqlconnection = DriverManager.getConnection("jdbc:mysql://" + DB_HOST + "/" + DB_DB, DB_USER, DB_PASS);
			Statement state_supplier = sqlconnection.createStatement();

			sql = "select s.company_name, sf.suppliers_feed_id, sf.url, "
					+ "sf.foldername, sf.file_type, sf.username,sf.password,dateFormat,sf.form_login as form_login "
					+ "from suppliers s left outer join suppliers_feed sf on "
					+ "s.suppliers_id=sf.suppliers_id where s.isEnabled=1 and "
					+ "sf.isEnabled=1 and sf.downloadEnabled=1";
			ResultSet rs_supplier = state_supplier.executeQuery(sql);
			for (int i = 0; rs_supplier.next(); i++) {
				try {
					supplier_feeds s1 = new supplier_feeds();
					s1.supplier = rs_supplier.getString("company_name");
					s1.suppliers_feed_id = rs_supplier.getInt("suppliers_feed_id");
					supplier_url = rs_supplier.getString("url");
					foldername = rs_supplier.getString("foldername");
					file_type = rs_supplier.getString("file_type");
					dateFormat = rs_supplier.getString("dateFormat");
					formLoginUrl = rs_supplier.getString("form_login");
					s1.formLoginUrl = formLoginUrl;
					
					s1.username = rs_supplier.getString("username");
					s1.password = rs_supplier.getString("password");

					if (s1.username != null) {
						s1.isBasicLogin = true;
					} else {
						s1.isBasicLogin = false;
					}

					if (file_type.equals("xls")) {
						s1.isXLS = true;
					} else {
						s1.isXLS = false;
					}
					if (file_type.equals("zip")) {
						s1.isZip = true;
					} else {
						s1.isZip = false;
					}
					if (file_type.equals("xml")) {
						s1.isXml = true;
					} else {
						s1.isXml = false;
					}
					if (file_type.equals("zip-xml")) {
						s1.isZipXml = true;
					} else {
						s1.isZipXml = false;
					}
					
					if(formLoginUrl != null && !formLoginUrl.equals("")){
						String host = "";
						String protocol = "";
						String method = "POST";
						String quString = "";
						String path = "";
						try {
							URL url2 = new URL(formLoginUrl);
							protocol = url2.getProtocol();
							host = url2.getHost();
							path = url2.getPath();
							quString = url2.getQuery();
						} catch (MalformedURLException e) {
							e.printStackTrace();
						}
						String htmlResponse = "";
						visitURL v = new visitURL();
						v.fileLocation = SUPPLIERS_DIR + foldername + "\\feed\\";
						v.fileType = file_type;
						htmlResponse = v.visitURL(protocol + "://" + host + path, true, method, 0,quString);
						//htmlResponse = v.visitURL(formLoginUrl);
						/*htmlResponse = v.visitURL("https://access.almo.com/login.asp", true, 
								"POST", 0, "uName=support@dropshipaccess.com&uPwd=SlyAlmo7747");*/ 
						htmlResponse = v.visitURL(supplier_url);
						if(DONE.equals(htmlResponse)){//AlmoAccess
							if(s1.supplier.equals("Almo Access")){
								f = new File(v.fileLocation + v.fileName);
								handleZipFile(f);
								handleAlmoZippedXmlFile(f);
							}
						}
						return;
					} else {
						s1.isFormLogin = false;
					}
					

					String old_supplier_url = "";
					if (dateFormat != null) {
						// lets check to see if a new version is available. if
						// so, lets update the db
						String filename = supplier_url.substring(supplier_url
								.lastIndexOf("/") + 1, supplier_url.length());

						String format = dateFormat.replaceAll("x", "");

						java.util.Date now = new java.util.Date();
						long dateMillis = now.getTime();
						// subtract a day
						long dayInMillis = 1000 * 60 * 60 * 24;
						dateMillis = dateMillis - dayInMillis;
						now.setTime(dateMillis);
						SimpleDateFormat sdf_now = new SimpleDateFormat(
								dateFormat.replaceAll("x", ""));
						String filename_date = sdf_now.format(now);

						String url_start = supplier_url.substring(0,
								supplier_url.lastIndexOf("/") + 1);

						String after_date = "";
						String before_date = "";
						boolean date_break = false;

						char[] filenameCA = filename.substring(0,
								filename.indexOf(".")).toCharArray();
						char[] dateFormatCA = dateFormat.toCharArray();
						if (filenameCA.length > dateFormatCA.length) {
							// we assume the filename has gained a digit for the
							// day or month (ie day 1 vs day 10)

							// if day is 10 then we need to adjust format
							dateFormat = dateFormat.replaceAll("dd", "d")
									.replaceAll("d", "dd");
							dateFormatCA = dateFormat.toCharArray();

							// if month is 10 then we know we need to adjust the
							// format
							if (filenameCA.length > dateFormatCA.length) {
								dateFormat = dateFormat.replaceAll("MM", "M")
										.replaceAll("M", "MM");
								dateFormatCA = dateFormat.toCharArray();
							}

						} else if (filenameCA.length < dateFormatCA.length) {

							// if day is 1 then we need to adjust format
							dateFormat = dateFormat.replaceAll("dd", "d");
							dateFormatCA = dateFormat.toCharArray();

							// if month is 1 then we know we need to adjust the
							// format
							if (filenameCA.length < dateFormatCA.length) {
								dateFormat = dateFormat.replaceAll("MM", "M");
								dateFormatCA = dateFormat.toCharArray();
							}
						}

						for (int j = 0; j < filenameCA.length; j++) {
							if (dateFormatCA[j] == 'x') {
								if (!date_break) {
									before_date += filenameCA[j];
								} else {
									after_date += filenameCA[j];
								}
							} else {
								date_break = true;
							}

						}

						String url_end = supplier_url.substring(supplier_url
								.lastIndexOf("."), supplier_url.length());

						old_supplier_url = supplier_url;
						supplier_url = url_start + before_date + filename_date
								+ after_date + url_end;
					}

					s1.parseURL(supplier_url, foldername);
					// could call constructor here if necessary, but it isn't
					// for this app
					// supplier_feeds();

					//boolean isSuccessful = start();

					if(old_supplier_url.length() > 0){
						s1.updateUrl = true;
						s1.supplierUrl = supplier_url;
					}

					Thread t1 = new Thread (s1);
					t1.start();
					/*
					boolean isSuccessful = true;
					//need to pause and check the success var until it's finished before continuing
					if(old_supplier_url.length() > 0){
						//wait 15min for the thread to finish
						for(int j=0;i<15 && t1.isAlive();j++ ){
							Thread.currentThread().sleep(60 * 1000);//60 secs
						}


						synchronized(this){
							for(int j=0;j<supplierCount;j++){
								if(supplierName[j]==s1.supplier + s1.suppliers_feed_id){
									isSuccessful=this.isSuccessful[j];
								}
							}
						}

						if(t1.isAlive()){
							t1.interrupt();
							isSuccessful = false;
						}
					}

					// if it worked, then updated db with new name, if not...
					// lets do the old one
					if (isSuccessful && old_supplier_url.length() > 0) {
						// now update the db
						// not using feed_id because we want to update names of
						// all feeds (need to update both entries for btol for
						// same file)
						sql = "update suppliers_feed set url='" + supplier_url
								+ "' where url='" + old_supplier_url + "'";
						Statement state_url = sqlconnection.createStatement();
						state_url.execute(sql);
						state_url.close();
					} else if (old_supplier_url.length() > 0) {
						// do the old one (do we even need to dl it? I guess if
						// they maybe updateded the same file a few times during
						// the day...)
						// parseURL(supplier_url,foldername);
						// boolean isSuccessful = start();
					}
					*/
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
			rs_supplier.close();
			state_supplier.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void parseURL(String supplier_url, String foldername) {
		try {
			String filename, supplier_file;
			filename = supplier_url.substring(
					supplier_url.lastIndexOf("/") + 1, supplier_url.length());
			supplier_file = SUPPLIERS_DIR + foldername
					+ "\\feed\\" + filename;

			url = new URL(supplier_url);
			f = new File(supplier_file);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void prepareFeeds() {
		try {

			if (supplier.equals("compgallery")) {
				url = new URL(
						"ftp://compgallery:reseller@compgallery.hosting4less.com/ItemDataFiles/Gallery_Complete.csv");
				f = new File(
						"C:\\NorthPacificTrading\\suppliers\\Computer Gallery\\feed\\Gallery_Complete.csv");
			} else if (supplier.equals("LNC")) {
				isBasicLogin = true;
				username = "data";
				password = "wholesale";

				java.util.Date now = new java.util.Date();
				long dateMillis = now.getTime();
				// subtract a day
				long dayInMillis = 1000 * 60 * 60 * 24;
				dateMillis = dateMillis - dayInMillis;
				now.setTime(dateMillis);
				SimpleDateFormat sdf_now = new SimpleDateFormat("MMddyy");
				String filename_ending = sdf_now.format(now);

				url = new URL("http://www.lncpetsupply.com/vision/Database"
						+ filename_ending + ".csv");
				f = new File(
						"C:\\NorthPacificTrading\\suppliers\\LNC\\feed\\database"
								+ filename_ending + ".csv");
				// url = new
				// URL("http://www.lncpetsupply.com/vision/database030608.csv");
				// f = new
				// File("C:\\NorthPacificTrading\\suppliers\\LNC\\feed\\database030608.csv");
			} else if (supplier.equals("ingramDesc")) {
				isAdvancedFTP = true;
				ftpHost = "ftpsecure.ingrammicro.com";
				ftpUser = "SAMPLE";
				ftpPass = "SAMP";
				ftpDir = "FUSION/US/SAMPLES";
				ftpFileLocation = "C:\\NorthPacificTrading\\suppliers\\Ingram Micro\\feed\\";
				ftpFileName = "PRICE.ZIP";
			} else if (supplier.equals("ingramInv")) {
				isAdvancedFTP = true;
				ftpHost = "ftpsecure.ingrammicro.com";
				ftpUser = "SAMPLE";
				ftpPass = "SAMP";
				ftpDir = "FUSION/US/AVAIL";
				ftpFileLocation = "C:\\NorthPacificTrading\\suppliers\\Ingram Micro\\feed\\";
				ftpFileName = "TOTAL.ZIP";
			} else if (supplier.equals("MotengDesc")) {
				url = new URL(
						"ftp://21222:DarylHowell@dropbox.moteng.com/ProdFile.txt");
				f = new File(
						"C:\\NorthPacificTrading\\suppliers\\Moteng\\feed\\ProdFile.txt");
			} else if (supplier.equals("MotengInv")) {
				url = new URL(
						"ftp://21222:DarylHowell@dropbox.moteng.com/InvStatFile.txt");
				f = new File(
						"C:\\NorthPacificTrading\\suppliers\\Moteng\\feed\\InvStatFile.txt");
			} else if (supplier.equals("fragrancex")) {
				url = new URL(
						"ftp://frgxdatafeed:datafeed@www.fragrancex.com/outgoingfeed.txt");
				f = new File(
						"C:\\NorthPacificTrading\\suppliers\\FragrenceX\\feed\\outgoingfeed.txt");
			} else if (supplier.equals("mimousa")) {
				url = new URL(
						"http://www.mimousa.com/mimousafiles/feeds/general.zip");
				f = new File(
						"C:\\NorthPacificTrading\\suppliers\\Mimo\\feed\\general.zip");
			} else if (supplier.equals("marshall")) {
				isXLS = true;
				url = new URL("http://marshalldc.com/productexport.i");
				f = new File(
						"C:\\NorthPacificTrading\\suppliers\\Marshall\\feed\\product2.xls");
			} else if (supplier.equals("navarreAccessories")) {
				isFormLogin = true;
				loginUrl = new URL(
						"http://www.navarre.com/Login.aspx?LoginID=7709232&password=dsa1337&cmd=login");
				url = new URL(
						"http://www.navarre.com/downcat.aspx?productline=accessories&Agreed=Yes&outputto=excel&submit1=I%20Agree");
				f = new File(
						"C:\\NorthPacificTrading\\suppliers\\Navarre\\feed\\NavarreAccessories.csv");
			} else if (supplier.equals("navarreAccessoriesDesc")) {
				url = new URL(
						"ftp://user%40ftp:pass%40support%40dropshipaccess%2Ecom@ftp.navarre.com/Content/Accessories.txt");
				f = new File(
						"C:\\NorthPacificTrading\\suppliers\\Navarre\\feed\\Accessories.txt");
			} else if (supplier.equals("navarreVideoGames")) {
				isFormLogin = true;
				loginUrl = new URL(
						"http://www.navarre.com/Login.aspx?LoginID=7709232&password=dsa1337&cmd=login");
				url = new URL(
						"http://www.navarre.com/downcat.aspx?productline=videogames&Agreed=Yes&outputto=excel&submit1=I%20Agree");
				f = new File(
						"C:\\NorthPacificTrading\\suppliers\\Navarre\\feed\\NavarreVideoGames.csv");
			} else if (supplier.equals("navarreVideoGamesDesc")) {
				url = new URL(
						"ftp://user%40ftp:pass%40support%40dropshipaccess%2Ecom@ftp.navarre.com/Content/Videogames.txt");
				f = new File(
						"C:\\NorthPacificTrading\\suppliers\\Navarre\\feed\\Videogames.txt");
			} else if (supplier.equals("navarreVideo")) {
				isFormLogin = true;
				loginUrl = new URL(
						"http://www.navarre.com/Login.aspx?LoginID=7709232&password=dsa1337&cmd=login");
				url = new URL(
						"http://www.navarre.com/downcat.aspx?productline=video&Agreed=Yes&outputto=excel&submit1=I%20Agree");
				f = new File(
						"C:\\NorthPacificTrading\\suppliers\\Navarre\\feed\\NavarreVideo.csv");
			} else if (supplier.equals("navarreVideoDesc")) {
				// url = new
				// URL("ftp://user%40ftp:pass%40support%40dropshipaccess%2Ecom@ftp.navarre.com/Content/Video.txt");
				// f = new
				// File("C:\\NorthPacificTrading\\suppliers\\Navarre\\feed\\Video.txt");
				isAdvancedFTP = true;
				ftpHost = "ftp.navarre.com";
				ftpUser = "user@ftp";
				ftpPass = "pass@support@dropshipaccess.com";
				ftpDir = "Content";
				ftpFileLocation = "C:\\NorthPacificTrading\\suppliers\\Navarre\\feed\\";
				ftpFileName = "Video.txt";
			} else if (supplier.equals("navarreSoftware")) {
				isFormLogin = true;
				loginUrl = new URL(
						"http://www.navarre.com/Login.aspx?LoginID=7709232&password=dsa1337&cmd=login");
				url = new URL(
						"http://www.navarre.com/downcat.aspx?productline=software&Agreed=Yes&outputto=excel&submit1=I%20Agree");
				f = new File(
						"C:\\NorthPacificTrading\\suppliers\\Navarre\\feed\\NavarreSoftware.csv");
			} else if (supplier.equals("navarreSoftwareDesc")) {
				url = new URL(
						"ftp://user%40ftp:pass%40support%40dropshipaccess%2Ecom@ftp.navarre.com/Content/Software.txt");
				f = new File(
						"C:\\NorthPacificTrading\\suppliers\\Navarre\\feed\\Software.txt");
			} else if (supplier.equals("navarreMusic")) {
				isFormLogin = true;
				loginUrl = new URL(
						"http://www.navarre.com/Login.aspx?LoginID=7709232&password=dsa1337&cmd=login");
				url = new URL(
						"http://www.navarre.com/downcat.aspx?productline=music&Agreed=Yes&outputto=excel&submit1=I%20Agree");
				f = new File(
						"C:\\NorthPacificTrading\\suppliers\\Navarre\\feed\\NavarreMusic.csv");
			} else if (supplier.equals("navarreMusicDesc")) {
				url = new URL(
						"ftp://user%40ftp:pass%40support%40dropshipaccess%2Ecom@ftp.navarre.com/Content/Music.txt");
				f = new File(
						"C:\\NorthPacificTrading\\suppliers\\Navarre\\feed\\Music.txt");
			} else if (supplier.equals("asiNew")) {
				// url = new URL("ftp://asispec:asi@67.104.19.206/NewSKUs.zip");
				// //newversion!!

				// url = new
				// URL("ftp://asispec:asi@67.104.19.206/ProductSpec.CSV");
				// f = new
				// File("C:\\NorthPacificTrading\\suppliers\\ASI\\feed\\ProductSpec.CSV");

				isAdvancedFTP = true;
				ftpHost = "67.104.19.206";
				ftpUser = "asispec";
				ftpPass = "asi";
				ftpDir = "./";
				ftpFileLocation = "C:\\NorthPacificTrading\\suppliers\\ASI\\feed\\";
				ftpFileName = "ProductSpec.CSV";

				// }else if(supplier.equals("asiDisc")){
				// url = new
				// URL("ftp://asispec:asi@67.91.114.176/discontinued.zip");
				// f = new
				// File("C:\\NorthPacificTrading\\suppliers\\ASI\\feed\\discontinued.zip");
			} else if (supplier.equals("asiPrice")) {
				// url = new
				// URL("ftp://asispec:asi@67.91.114.176/ASIInventory.csv");
				// //newversion!!
				url = new URL("http://67.104.19.211/csvd/78322942456/78322.csv");
				f = new File(
						"C:\\NorthPacificTrading\\suppliers\\ASI\\feed\\78322.csv");
			} else if (supplier.equals("jewelrySprite")) {
				url = new URL(
						"http://www.jewelrysprite.com/jewelrysprite2_com.txt.zip");
				f = new File(
						"C:\\NorthPacificTrading\\suppliers\\Jewelry Sprite\\feed\\jewelrysprite2_com.txt.zip");
			} else if (supplier.equals("petra")) {

				f = new File(
						"C:\\NorthPacificTrading\\suppliers\\Petra\\feed\\prodlist.csv");
			} else if (supplier.equals("petraCat")) {

				f = new File(
						"C:\\NorthPacificTrading\\suppliers\\Petra\\feed\\CAT106_FulfillmentCD.csv");
			} else if (supplier.equals("dblAll")) {
				url = new URL("http://www.dblsupport.com/inv_feed/dbl_all.txt");
				f = new File(
						"C:\\NorthPacificTrading\\suppliers\\DBL\\feed\\dbl_all.txt");
			} else if (supplier.equals("dblPrice")) {
				url = new URL(
						"http://opw.dblsupport.com/ecom/dbl_inventory.asp?custid=90120212&pwd=846530C");
				f = new File(
						"C:\\NorthPacificTrading\\suppliers\\DBL\\feed\\dbl_approved_items.txt");
			} else if (supplier.equals("tatiana")) {
				url = new URL(
						"http://static.zoovy.com/merchant/tatiana/datafeed_May16.xls");
				isXLS = true;
				f = new File(
						"C:\\NorthPacificTrading\\suppliers\\Tatiana\\feed\\datafeed_May16.xls");
			} else if (supplier.equals("tatianaInv")) {
				// isFormLogin = true;
				// RequestMethod = "GET";
				// loginUrl = new
				// URL("http://www.zoovy.com/login/index.cgi?login=tatiana*cyndee&password=flash2&authenticate=true");
				url = new URL(
						"http://static.zoovy.com/merchant/tatiana/Stock_May16.xls");
				isXLS = true;
				f = new File(
						"C:\\NorthPacificTrading\\suppliers\\Tatiana\\feed\\Stock_May16.xls");
			}

			else {
				log_msg("ERROR: no file selected");
				return;
			}

		} catch (Exception e) {
			log_msg(e.toString());
			e.printStackTrace();
		}
	}

	// returns true if successful, false if not
	public void run(){

		SimpleDateFormat sdf_now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String start_time = sdf_now.format(new java.util.Date());

		log_msg(supplier + "_" + suppliers_feed_id + " " + start_time);
		Statement state = null;
		try {

			
			// basic login is the web/os version of using user accounts to
			// authenticate
			if (isBasicLogin) {
				HttpURLConnection connection = null;
				String key = "";

				java.security.Security
						.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
				System.setProperty("java.protocol.handler.pkgs",
						"com.sun.net.ssl.internal.www.protocol");

				connection = (HttpURLConnection) url.openConnection();

				// set all required HTTP headers to post
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setUseCaches(false);
				connection.setRequestMethod("GET");
				connection.setInstanceFollowRedirects(false);
				connection.setRequestProperty("User-Agent",
						"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
				connection.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				connection.setRequestProperty("Content-Length", "0");
				connection.setRequestProperty("Connection", "Keep-Alive");

				// Authorization [Base64Encoder - user defined class to encode]
				connection.setRequestProperty("Authorization", "Basic "
						+ Base64Coder.encodeString(username + ":" + password));

				if (connection != null) {
					for (int i = 1; (key = connection.getHeaderFieldKey(i)) != null; i++) {
						// System.out.println(connection.getHeaderFieldKey(i) +
						// ": " + connection.getHeaderField(key));
					}

					BufferedInputStream in = new BufferedInputStream(connection
							.getInputStream());
					FileOutputStream out = new FileOutputStream(f);

					int i = 0;
					byte[] bytesIn = new byte[1024];
					while ((i = in.read(bytesIn)) >= 0) {
						out.write(bytesIn, 0, i);
					}
					out.close();
					in.close();

				}

				// if we use special form login (need to pass login cookie to dl
				// page)
			} else if (isFormLogin) {
				// first login
				HttpURLConnection connection = null;
				String cookie = "";
				String key = "";
				String viewstate;

				java.security.Security
						.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
				System.setProperty("java.protocol.handler.pkgs",
						"com.sun.net.ssl.internal.www.protocol");

				connection = (HttpURLConnection) loginUrl.openConnection();

				// set all required HTTP headers to post
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setUseCaches(false);
				connection.setRequestMethod(RequestMethod);
				connection.setInstanceFollowRedirects(false);
				connection.setRequestProperty("User-Agent",
						"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
				connection.setRequestProperty("Content-type",
						"application/text");
				connection.setRequestProperty("Content-Length", "0");

				if (connection != null) {

					for (int i = 0;; i++) {
						String headerName = connection.getHeaderFieldKey(i);
						String headerValue = connection.getHeaderField(i);

						// System.out.println(headerName + ": " + headerValue);

						if (headerName == null && headerValue == null) {
							// No more headers
							break;
						}
						if ("Set-Cookie".equalsIgnoreCase(headerName)) {
							// Parse cookie
							String[] fields = headerValue.split(";\\s*");

							String cookieValue = fields[0];

							cookie += cookieValue + ";";
						}
					}
				}
				// System.out.println(cookie);

				// get viewstate variable
				connection = (HttpURLConnection) url.openConnection();

				// set all required HTTP headers to post
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setUseCaches(false);
				connection.setRequestMethod("POST");
				connection.setInstanceFollowRedirects(false);
				connection.setRequestProperty("Content-type",
						"application/text");
				connection.setRequestProperty("Cookie", cookie);
				connection.setRequestProperty("Content-Length", "0");

				if (connection != null) {
					for (int i = 1; (key = connection.getHeaderFieldKey(i)) != null; i++) {
						// //System.out.println(connection.getHeaderFieldKey(i)
						// + ": " + connection.getHeaderField(key));
					}

					StringBuffer sb = new StringBuffer();

					BufferedReader reader = new BufferedReader(
							new InputStreamReader(connection.getInputStream()));

					String line;

					for (int line_num = 1; (line = reader.readLine()) != null; line_num++) {
						if (line.indexOf("__VIEWSTATE") > -1) {
							viewstate = line.substring(line
									.indexOf("__VIEWSTATE") + 20, line.indexOf(
									"\" />", line.indexOf("__VIEWSTATE") + 21));
							url = new URL(url.toString() + "&__VIEWSTATE="
									+ URLEncoder.encode(viewstate));
							break;
						}
					}

				}

				// get the data feed
				connection = (HttpURLConnection) url.openConnection();

				// set all required HTTP headers to post
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setUseCaches(false);
				connection.setRequestMethod("GET");
				connection.setInstanceFollowRedirects(false);
				connection.setRequestProperty("Content-type",
						"application/text");
				connection.setRequestProperty("Cookie", cookie);
				connection.setRequestProperty("Content-Length", "0");

				if (connection != null) {
					for (int i = 1; (key = connection.getHeaderFieldKey(i)) != null; i++) {
						// System.out.println(connection.getHeaderFieldKey(i) +
						// ": " + connection.getHeaderField(key));
					}

					BufferedInputStream in = new BufferedInputStream(connection
							.getInputStream());
					FileOutputStream out = new FileOutputStream(f);

					int i = 0;
					byte[] bytesIn = new byte[1024];
					while ((i = in.read(bytesIn)) >= 0) {
						out.write(bytesIn, 0, i);
					}
					out.close();
					in.close();

				}

			} else if (isAdvancedFTP) {
				// if the ftp server has complications and needs more power than
				// javas built in ftp client

				FTPClient ftp = null;

				// set up client
				ftp = new FTPClient();
				ftp.setRemoteHost(ftpHost);
				FTPMessageCollector listener = new FTPMessageCollector();
				ftp.setMessageListener(listener);

				ftp.connect();
				ftp.login(ftpUser, ftpPass);

				// set up passive ASCII transfers
				ftp.setConnectMode(FTPConnectMode.PASV);
				ftp.setType(FTPTransferType.ASCII);

				ftp.chdir(ftpDir);

				// copy file from server
				ftp.get(ftpFileLocation + ftpFileName, ftpFileName);

				// Shut down client
				ftp.quit();

				// if login is encoded in url or no login necessary (works for
				// basic http and ftp)
			} else {
				// download and save the file

				URLConnection con = url.openConnection();
				BufferedInputStream in = new BufferedInputStream(con
						.getInputStream());
				FileOutputStream out = new FileOutputStream(f);

				int i = 0;
				byte[] bytesIn = new byte[102400];
				while ((i = in.read(bytesIn)) >= 0) {

					out.write(bytesIn, 0, i);

				}
				out.close();
				in.close();

			}

			// if excel, then lets convert to csv
			// works, but not with marshells xls file. also, code not
			// finished...
			if (isXLS) {

				handleXlsFile(f);
				/*
				 *
				 * HSSFWorkbook wb = new HSSFWorkbook(fs); HSSFSheet sheet =
				 * wb.getSheetAt(0);
				 *
				 * int ROWS_BEFORE_DATA = 0;
				 *
				 * for (int i = ROWS_BEFORE_DATA; i <= sheet.getLastRowNum();
				 * i++) { HSSFRow currentRow = sheet.getRow(i);
				 * log_msg("Total Rows: " + (sheet.getLastRowNum()+1));
				 * log_msg("This row, row " + (i+1) + ", has a total of " +
				 * currentRow.getPhysicalNumberOfCells() + " cells.");
				 *
				 * // Minor loop, one per field int FIELD_COUNT = 10; for (int
				 * col =0; col<(FIELD_COUNT); col++){ int tempCount = col; short
				 * useColumn = (short)tempCount;
				 *
				 *
				 *
				 * log_msg("Current Column: " + (useColumn+1));
				 *
				 * log_msg("in Row: " + (currentRow.getRowNum()+1));
				 *
				 * log_msg("cell data: " + currentRow.getCell(useColumn));
				 *
				 *
				 *
				 * try{ switch(currentRow.getCell(useColumn).getCellType()){
				 *
				 * } }catch(Exception ex){ ex.printStackTrace(); } } }
				 */
			}
			/*
			 * If the feed file is a .zip file, get the file first and unzip and
			 * re-zip again with same name to avoid un-expected error in
			 * import_data
			 */
			if (isZip) {
				handleZipFile(f);
			}

			if (suppliers_feed_id > 0) {
				// update database with success details
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				sqlconnection = DriverManager
						.getConnection("jdbc:mysql://" + DB_HOST + "/" + DB_DB, DB_USER, DB_PASS);
				state = sqlconnection.createStatement();
				state.executeUpdate("insert into suppliers_feed_log (suppliers_feed_id,startDate,endDate,isSuccessful) values("
						+ suppliers_feed_id
						+ ", '"
						+ start_time
						+ "', now(), 1);");

				if(updateUrl){
					//state.execute("update suppliers_feed set url='" + supplier_url + "' where url='" + old_supplier_url + "'");
					state.execute("update suppliers_feed set url='" + supplierUrl + "' where suppliers_feed_id='" + suppliers_feed_id + "'");
				}


				state.close();
			}

			synchronized(lock){
				for(int i=1;i<=supplierCount;i++){
					if(supplierName[i]==this.supplier || supplierName[i]==this.supplier + this.suppliers_feed_id){
						isSuccessful[i]=true;
					}
				}
			}


		} catch (Exception e) {
			log_msg(e.toString());
			e.printStackTrace();
			try {
				if (suppliers_feed_id > 0) {
					// update database with error details
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					sqlconnection = DriverManager.getConnection("jdbc:mysql://" + DB_HOST + "/" + DB_DB, DB_USER, DB_PASS);
					state = sqlconnection.createStatement();
					state.executeUpdate("insert into suppliers_feed_log (suppliers_feed_id,startDate,endDate,isSuccessful,error) values("
							+ suppliers_feed_id
							+ ", '"
							+ start_time
							+ "', now(), 0,'" + e.toString() + "');");
					state.close();
				}

				//isSuccessful= false;
			} catch (Exception ee) {
				System.out.println(ee.toString());
				//isSuccessful= false;
			}
		}
	}
	
	private void handleAlmoZippedXmlFile(File f) throws ZipException, IOException{
		//  unzip
		ZipFile zipFile = new ZipFile(f);
		unzip(zipFile);
		File parentDir = new File(f.getParent()) ;
		
		String[] filenames = parentDir.list();
		for (int i=0; i<filenames.length; i++) {
			if(filenames[i].endsWith(".xml")){
				String mappingFile = "mappings/almo-mapping.xml";
				AlmoConversionUtil.convertXml2Csv(filenames[i], 
						null, ',', mappingFile);
			}
        }
	}
	
	private void handleXlsFile(File f) throws FileNotFoundException, IOException{
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(f));
		PrintStream ps = new PrintStream(f.toString() + ".csv");

		XLS2CSVmra xls2csv = new XLS2CSVmra(fs, ps, -1);
		xls2csv.process();
	}
	
	private void handleZipFile(File f) throws ZipException, IOException{
		//  unzip
		ZipFile zipFile = new ZipFile(f);
		unzip(zipFile);
		//  delete the old zip file, store the file name
		String oldZipName = f.getName();
		String parentDirPath = f.getParent() + "\\";
		f.delete();
		// re-zip with the same old zip file name
		createNewZip(oldZipName, parentDirPath);
	}

	private void createNewZip(String newZipFileName, String parentDirPath) {
		File parentDir = new File(parentDirPath);
		String[] filenames = parentDir.list();
	    byte[] buf = new byte[1024];

	    try {
	        String outFilename = parentDirPath + newZipFileName;
	        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outFilename));
	        for (int i=0; i<filenames.length; i++) {
				//log_msg("file::" + parentDirPath + filenames[i]);
	            FileInputStream in = new FileInputStream(parentDirPath + filenames[i]);
	            out.putNextEntry(new ZipEntry(filenames[i]));
	            int len;
	            while ((len = in.read(buf)) > 0) {
	                out.write(buf, 0, len);
	            }
	            out.closeEntry();
	            in.close();
	        }
	        out.close();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }


	}

	private void unzip(ZipFile zipFile) {
		Enumeration<? extends ZipEntry> entries = zipFile.entries();

		try {
			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entries.nextElement();

				if (entry.isDirectory()) {
					log_msg("Extracting directory: "+ entry.getName());
					(new File(entry.getName())).mkdir();
					continue;
				}

				log_msg("Extracting file: " + entry.getName());
				copyInputStream(zipFile.getInputStream(entry),
						new BufferedOutputStream(new FileOutputStream(
								f.getParent() + "/" + entry.getName())));
			}

			zipFile.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();

		}

	}

	private void copyInputStream(InputStream inputStream,
			BufferedOutputStream bufferedOutputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len;

		while ((len = inputStream.read(buffer)) >= 0)
			bufferedOutputStream.write(buffer, 0, len);

		inputStream.close();
		bufferedOutputStream.close();

	}

	private void log_msg(String msg) {
		try {
			System.out.println(msg);
			synchronized(lock){
				log.write(msg);
				log.write("\n");
				log.flush();
			}
		} catch (Exception ee) {
			System.out.println("logging error");
			ee.printStackTrace();
		}
	}

}
