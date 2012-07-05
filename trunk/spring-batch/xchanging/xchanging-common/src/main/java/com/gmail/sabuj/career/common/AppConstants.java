/*
 * 
 */

package com.gmail.sabuj.career.common;


/**
 * Interface 'AppConstants' is to store the constants shared in this application.
 * 
 * @author <a href="mailo:career.sabuj@gmail.com">Sabuj Das</a>
 *
 */
public interface AppConstants {

	// Start: property keys defined in the app-settings.properties file
	String PROP_NAME_TEMP_DIR = "temp.dir";
	String PROP_NAME_SOURCE_XLS_URL = "source.xls.url";
	String PROP_NAME_TARGET_XLS_FILENAME = "terget.xls.fileName";
	String PROP_NAME_SOURCE_CSV_URL = "source.csv.url";
	String PROP_NAME_TARGET_CSV_FILENAME = "target.csv.fileName";
	
	String PROP_PREFIX_TASK_FILTER_PARAMS = "task.filter.params_";
	
	String PROP_NAME_SIMPLE_DATE_FORMAT = "simple.date.format";
	String PROP_NAME_RELEASE_DATE_SOURCE = "release.date.source";
	String PROP_NAME_NEXT_RELEASE_DATE_SOURCE = "next.release.date.source";
	
	String PROP_NAME_USE_PROXY_CONFIG = "use.proxy.config";
	String PROP_NAME_PROXY_SERVER_HOST = "proxy.server.host";
	String PROP_NAME_PROXY_SERVER_PORT = "proxy.server.port";
	
	String PROP_NAME_GENERATED_CSV_PATH = "generated.csv.path";
	String PROP_NAME_GENERATED_CSV_FILENAME = "generated.csv.fileName";
	
	String PROP_NAME_CSV_COL_HEADERS = "generated.csv.column.headers";
	
	// Start: Filter parameters defiled in the properties file for creating tasks
	String FILTER_PROP_SEP = ":";
	String FILTER_PROP_WORKBOOK_NAME = "worksheet.name";
	String FILTER_PROP_UNIT_TYPE = "unit.type";
	String FILTER_PROP_SOURCE_KEY = "source.key";
	String FILTER_PROP_ROW_NUM = "row";
	String FILTER_PROP_COL_NUM = "col";
			
}
