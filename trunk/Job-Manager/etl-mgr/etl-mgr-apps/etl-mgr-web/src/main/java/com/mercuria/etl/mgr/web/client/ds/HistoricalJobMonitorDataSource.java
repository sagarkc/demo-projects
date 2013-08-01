/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.mercuria.etl.mgr.web.client.ds.HistoricalJobMonitorDataSource
 * Date:	Jul 31, 2013
 * 
 * Author:	Sabuj Das | sabuj.das@asia.xchanging.com
 * -------------------------------------------------------------------------- *
 */
package com.mercuria.etl.mgr.web.client.ds;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.mercuria.etl.mgr.web.WebConstants;
import com.mercuria.etl.mgr.web.client.core.AbstractRestDataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.util.DateDisplayFormatter;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

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
		DataSourceDateField startTimeField = new DataSourceDateField("startTime", "Job Start Time");
		addField(startTimeField);
		DataSourceDateField endTimeField = new DataSourceDateField("endTime",  "Job End Time");
		addField(endTimeField);
		DataSourceField exitMessageField = new DataSourceField("exitMessage", FieldType.TEXT, "Exit Message");
		addField(exitMessageField);
		
	}

	
	
}
