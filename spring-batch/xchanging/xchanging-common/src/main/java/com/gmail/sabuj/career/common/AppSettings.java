package com.gmail.sabuj.career.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import com.gmail.sabuj.career.common.model.TaskDetail;
import com.gmail.sabuj.career.common.util.CollectionUtil;
import com.gmail.sabuj.career.common.util.StringUtil;

/**
 * 	AppSettings is Singleton class acts as a context of the current execution.
 * This object contains all the common settings for the application, those are
 * either loaded from the external app-settings file or from any other constants.
 * <br/>
 * Note: Changes in the properties file will not reload while the application is in running.
 * To reload the settings call: <code>AppSettings.getInstance().reload()</code>
 * <br/><br/>
 * <p>
 * 	The app-settings.properties file should be present in the path 
 * <code>./app-config/properties/app-settings.properties</code>.
 * This context also loads the Logger from app-log4j.xml file present in path
 * <code>./app-config/logger/app-log4j.xml</code>
 * </p>
 * @author <a href="mailo:career.sabuj@gmail.com">Sabuj Das</a>
 *
 */
public final class AppSettings implements AppConstants{

	private static Logger logger = null;
	private static final String appSettingsLocation = "./app-config/properties/app-settings.properties";
	public static final String appLoggerLocation = "./app-config/logger/app-log4j.xml";
	private Properties properties = new Properties();
	private List<TaskDetail> taskDetails = new ArrayList<TaskDetail>();
	private SimpleDateFormat dateFormat;
	private String[] csvColumnHeaders = new String[0]; 
	
	private static AppSettings settings;
	private AppSettings(){
		logger = Logger.getLogger(AppSettings.class);
	}
	
	/**
	 * This method is to retrieve the instance created. If the instance is not 
	 * created it will be initialized.
	 * @return AppSettings
	 */
	public static AppSettings getInstance(){
		synchronized (AppSettings.class) {
			if(null == settings)
				settings = new AppSettings();
		}
		return settings;
	}
	
	public List<TaskDetail> getTaskDetails() {
		return taskDetails;
	}

	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

	public void reload() throws IOException{
		configureSettings();
	}
	
	
	/**
	 * Load the application settings from the ap-settings.properties file.
	 * @throws IOException
	 */
	public void configureSettings() throws IOException {
		logger.info("START:: Configuring the application settings...");
		/*
		 * Read the properties file. Create instance of java.util.Properties
		 * and keep ready.
		 */
		FileInputStream fileInputStream = null;
		try{
			fileInputStream = new FileInputStream(appSettingsLocation);
			properties.load(fileInputStream);
		} finally {
			if(null != fileInputStream)
				fileInputStream.close();
		}
		
		/* create the temporary directory if not exists
		 * Property Name: temp.dir
		 */
		String tempDirName = settings.getProperty(PROP_NAME_TEMP_DIR);
		File tempDir = new File(tempDirName);
		logger.info("Temp dir : " + tempDirName);
		if(!tempDir.exists()){
			logger.info("Creating temp dir : " + tempDirName);
			tempDir.mkdirs();
		}
		
		/* create the generated folder if not exists, to store the generated
		 * CSV files.
		 * Property Name: generated.csv.path
		 */
		String genDirName = settings.getProperty(PROP_NAME_GENERATED_CSV_PATH);
		File genDir = new File(genDirName);
		logger.info("Generated file dir : " + genDirName);
		if(!genDir.exists()){
			logger.info("Creating Generated file dir : " + genDirName);
			genDir.mkdirs();
		}
		
		/*
		 * To download the files from internate, we may need to set the proxy
		 * configuration in Java System properties.
		 * To enable proxy, set the value of 'use.proxy.config' to YES, any other
		 * valu provided to this property will not set the proxy configuration.
		 * Example:
		 * 		use.proxy.config=NO
		 * 		proxy.server.host=host
		 * 		proxy.server.port=port
		 */
		logger.info("Use Proxy: " + getProperty(PROP_NAME_USE_PROXY_CONFIG));
		boolean useProxy = 
				( null != getProperty(PROP_NAME_USE_PROXY_CONFIG) 
					&& "YES".equalsIgnoreCase(getProperty(PROP_NAME_USE_PROXY_CONFIG))) 
				? true : false;
		if(useProxy){
			System.setProperty("http.proxyHost", getProperty(PROP_NAME_PROXY_SERVER_HOST));
			System.setProperty("http.proxyPort", getProperty(PROP_NAME_PROXY_SERVER_PORT));
			System.setProperty("https.proxyHost", getProperty(PROP_NAME_PROXY_SERVER_HOST));
			System.setProperty("https.proxyPort", getProperty(PROP_NAME_PROXY_SERVER_PORT));
			System.setProperty("ftp.proxyHost", getProperty(PROP_NAME_PROXY_SERVER_HOST));
			System.setProperty("ftp.proxyPort", getProperty(PROP_NAME_PROXY_SERVER_PORT));
		}
		
		/*
		 * creates the Date Formatter using java.text.SimpleDateFormat
		 * Property Name: simple.date.format
		 * Note: This property value must be complient with the pattern
		 * provided in the java.text.SimpleDateFormat class
		 */
		String dFormat = getProperty(PROP_NAME_SIMPLE_DATE_FORMAT);
		if(StringUtil.hasValidContent(dFormat)){
			dateFormat = new SimpleDateFormat(dFormat);
		}
		
		/*
		 * Creates the Array of CSV column header texts
		 * Property Name: generated.csv.column.headers
		 * Note: The header texts must be separated by '|'
		 */
		String colHeadersText = getProperty(PROP_NAME_CSV_COL_HEADERS);
		if(StringUtil.hasValidContent(colHeadersText)){
			String[] headers = colHeadersText.split("\\|");
			if(CollectionUtil.hasElements(headers)){
				csvColumnHeaders = headers;
			}
		}
		
		/*
		 * Creates the Task details mentioned with the property name "task.filter.params_*",
		 * where * is the task name. A list of TaskDetails will be created using the name 
		 * mentioned after the '_'.
		 * The task details is populated with the Filter parameters mentioned by the vale
		 * of these properties.
		 */
		Set<Object> propKeySet = properties.keySet();
		if(null != propKeySet){
			for (Object objKey : propKeySet) {
				String key = objKey.toString();
				if(key.startsWith(PROP_PREFIX_TASK_FILTER_PARAMS)){
					String taskName = key.substring(
							key.indexOf("_")+1); 
					TaskDetail taskDetail = new TaskDetail(taskName);
					String value = properties.getProperty(key);
					if(StringUtil.hasValidContent(value)){
						String[] values = value.split(",");
						if(CollectionUtil.hasElements(values)){
							for (String val : values) {
								String[] filter = val.split(":");
								if(filter.length == 2){
									populateTask(taskDetail, filter);
								}
							}
						}
						taskDetails.add(taskDetail);
					}
				}
			}
		}
		logger.info("DONE:: Configuring the application settings...");
	}
	
	private void populateTask(TaskDetail taskDetail, String[] filter) {
		if(FILTER_PROP_WORKBOOK_NAME.equals(filter[0])){
			taskDetail.setWorkbookName(filter[1]);
			taskDetail.setHasWorkBookFilter(true);
		}
		if (FILTER_PROP_UNIT_TYPE.equals(filter[0])) {
			taskDetail.setUnitType(filter[1]);
			taskDetail.setHasUnitTypeFilter(true);
		}
		if (FILTER_PROP_SOURCE_KEY.equals(filter[0])) {
			taskDetail.setSourceKey(filter[1]);
			taskDetail.setHasSourceKeyFilter(true);
		}
	}

	public String getProperty(String key){
		if(null != properties)
			return properties.getProperty(key);
		return null;
	}

	public String getCsvColumnHeader(int index) {
		if(null != csvColumnHeaders && csvColumnHeaders.length >= index){
			return csvColumnHeaders[index];
		}
		return null;
	}
	
	
}
