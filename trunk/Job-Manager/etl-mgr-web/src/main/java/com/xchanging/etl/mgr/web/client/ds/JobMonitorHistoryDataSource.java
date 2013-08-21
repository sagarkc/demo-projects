/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.ds.JobMonitorHistoryDataSource
 * Date:	Aug 6, 2013  12:18:13 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.ds;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.xchanging.etl.mgr.model.vo.JobMonitorHistoryVo;
import com.xchanging.etl.mgr.web.client.core.GwtRpcObjectDataSource;
import com.xchanging.etl.mgr.web.client.endpoint.RemoteServiceEndpointFactory;
import com.xchanging.etl.mgr.web.client.service.JobMonitorServiceAsync;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class JobMonitorHistoryDataSource extends GwtRpcObjectDataSource{
	
	public static final String DS_ID = "JobMonitorHistoryDataSource";
	private static JobMonitorHistoryDataSource instance = null;  
	private final JobMonitorServiceAsync monitorService
		= RemoteServiceEndpointFactory.getInstance().getJobMonitorServiceEndpoint();
	
    public static JobMonitorHistoryDataSource getInstance() {  
        if (instance == null) {  
            instance = new JobMonitorHistoryDataSource(DS_ID);  
        }  
        return instance;  
    }  
  
    private JobMonitorHistoryDataSource(String id) {  
        setID(id);  
        
		
		DataSourceField nameField = new DataSourceField(JobMonitorHistoryVo.Fields.JOB_NAME, FieldType.TEXT);
		nameField.setPrimaryKey(true);
		
		DataSourceField exitCodeField = new DataSourceField(JobMonitorHistoryVo.Fields.STATUS, FieldType.TEXT);
		DataSourceField startTimeField = new DataSourceField(JobMonitorHistoryVo.Fields.LAST_STARTED_TIME, FieldType.DATETIME);
		DataSourceField endTimeField = new DataSourceField(JobMonitorHistoryVo.Fields.LAST_ENDED_TIME,  FieldType.DATETIME);
		DataSourceField executeJobField = new DataSourceField("executeJob", FieldType.ANY);
		
		addField(nameField);
		addField(exitCodeField); 
		addField(startTimeField); 
		addField(endTimeField);
		addField(executeJobField);
  
        setClientOnly(true);  
  
    }

	@Override
	protected void executeFetch(final String requestId, DSRequest request,
			final DSResponse response) {
		monitorService.loadJobMonitorHistoryData(new AsyncCallback<List<JobMonitorHistoryVo>>() {
			
			@Override
			public void onSuccess(List<JobMonitorHistoryVo> jobExecutionData) {
				ListGridRecord[] records = new ListGridRecord[0];
				if(null != jobExecutionData && jobExecutionData.size() > 0){
					records = new ListGridRecord[jobExecutionData.size()];
					for (int i = 0; i < jobExecutionData.size(); i++) {
						JobMonitorHistoryVo monitorVo = jobExecutionData.get(i);
						ListGridRecord record = new ListGridRecord();
						record.setAttribute(JobMonitorHistoryVo.Fields.JOB_NAME, monitorVo.getJobName());
						record.setAttribute(JobMonitorHistoryVo.Fields.STATUS, monitorVo.getStatus());
						record.setAttribute(JobMonitorHistoryVo.Fields.LAST_STARTED_TIME, monitorVo.getLastStartedTime());
						record.setAttribute(JobMonitorHistoryVo.Fields.LAST_ENDED_TIME, monitorVo.getLastEndedTime());
						
						records[i] = record;
					}
				}
				
				response.setData(records);
				processResponse(requestId, response);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR from loadHistoricalMonitorData(): " + caught.getStackTrace());
			}
		});
	}

	/* (non-Javadoc)
	 * @see com.xchanging.etl.mgr.web.client.core.GwtRpcObjectDataSource#executeAdd(java.lang.String, com.smartgwt.client.data.DSRequest, com.smartgwt.client.data.DSResponse)
	 */
	@Override
	protected void executeAdd(String requestId, DSRequest request,
			DSResponse response) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.xchanging.etl.mgr.web.client.core.GwtRpcObjectDataSource#executeUpdate(java.lang.String, com.smartgwt.client.data.DSRequest, com.smartgwt.client.data.DSResponse)
	 */
	@Override
	protected void executeUpdate(String requestId, DSRequest request,
			DSResponse response) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.xchanging.etl.mgr.web.client.core.GwtRpcObjectDataSource#executeRemove(java.lang.String, com.smartgwt.client.data.DSRequest, com.smartgwt.client.data.DSResponse)
	 */
	@Override
	protected void executeRemove(String requestId, DSRequest request,
			DSResponse response) {
		// TODO Auto-generated method stub
		
	}  
    
    
}
