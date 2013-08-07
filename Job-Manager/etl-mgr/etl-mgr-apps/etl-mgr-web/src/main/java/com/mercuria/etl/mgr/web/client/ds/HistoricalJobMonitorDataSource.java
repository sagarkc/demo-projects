/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.ds.HistoricalJobMonitorDataSource
 * Date:	Jul 31, 2013
 * 
 * Author:	Sabuj Das | sabuj.das@asia.xchanging.com
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.ds;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.xchanging.etl.mgr.web.WebConstants;
import com.xchanging.etl.mgr.web.client.core.AbstractRestDataSource;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.OperationBinding;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.util.DateDisplayFormatter;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class HistoricalJobMonitorDataSource extends AbstractRestDataSource{

	private static HistoricalJobMonitorDataSource instance;
	private final DateTimeFormat dateFormat = DateTimeFormat.getFormat("MM-dd-yyyy HH:mm:ss.SSS");
	private final DateDisplayFormatter dateFormatter 
		= new DateDisplayFormatter() {
	        @Override
	        public String format(Date date) {
	            String form = dateFormat.format(date);
	            return form;
	        }
	    };

	public static HistoricalJobMonitorDataSource getInstance() {
		if(null != instance)
			return instance;
		synchronized (HistoricalJobMonitorDataSource.class) {
			if(null == instance)
				instance = new HistoricalJobMonitorDataSource("HistoricalJobMonitorDataSource");
		}
		return instance;
	}

	/**
	 * @param dataSourceId
	 */
	private HistoricalJobMonitorDataSource(String dataSourceId) {
		super(dataSourceId);
	}

	@Override
	protected String getServiceRoot() {
		return WebConstants.SERVICE_ROOT_HISTORICAL_JOB_MONITOR;
	}

	@Override
	protected void init() {
		setDataFormat(DSDataFormat.JSON);
		setJsonRecordXPath("/");
		
		
		DataSourceField idField = new DataSourceField("jobInstanceId", FieldType.INTEGER, "JOB Instance ID");
		idField.setPrimaryKey(true);
		idField.setCanEdit(false);
		idField.setHidden(true);
		addField(idField);
		
		DataSourceField nameField = new DataSourceField("jobName", FieldType.TEXT, "JOB Name");
		addField(nameField);
		DataSourceField exitCodeField = new DataSourceField("exitCode", FieldType.TEXT, "Exit Code");
		addField(exitCodeField);
		DataSourceField startTimeField = new DataSourceField("startTime", FieldType.DATETIME, "Job Start Time");
		addField(startTimeField);
		DataSourceField endTimeField = new DataSourceField("endTime", FieldType.DATETIME, "Job End Time");
		addField(endTimeField);
		DataSourceField exitMessageField = new DataSourceField("exitMessage", FieldType.TEXT, "Exit Message");
		addField(exitMessageField);
		
	}

	
	
}
