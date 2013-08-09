/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.ds.LatestJobExecutionDataSource
 * Date:	Aug 8, 2013  8:17:38 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.ds;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.xchanging.etl.mgr.model.vo.JobExecutionHistoryVo;
import com.xchanging.etl.mgr.model.vo.JobMonitorHistoryVo;
import com.xchanging.etl.mgr.web.WebConstants;
import com.xchanging.etl.mgr.web.client.core.GwtRpcObjectDataSource;
import com.xchanging.etl.mgr.web.client.service.JobMonitorService;
import com.xchanging.etl.mgr.web.client.service.JobMonitorServiceAsync;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class LatestJobExecutionDataSource extends GwtRpcObjectDataSource {
	private static LatestJobExecutionDataSource instance = null;  
	private final JobMonitorServiceAsync monitorService;
	
    public static LatestJobExecutionDataSource getInstance() {  
        if (instance == null) {  
            instance = new LatestJobExecutionDataSource("LatestJobExecutionDataSource");  
        }  
        return instance;  
    }  
	/**
	 * 
	 */
	private LatestJobExecutionDataSource(String id) {
		setID(id);
		
		monitorService = GWT.create(JobMonitorService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) monitorService;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL()
				+ JobMonitorService.RPC_TARGET + WebConstants.RPC_EXT);
		
		DataSourceField executionIdField = new DataSourceField(JobExecutionHistoryVo.Fields.JOB_EXECUTION_ID, FieldType.INTEGER);
		executionIdField.setPrimaryKey(true);
		
		DataSourceField nameField = new DataSourceField(JobExecutionHistoryVo.Fields.JOB_NAME, FieldType.TEXT);
		nameField.setForeignKey(JobMonitorHistoryDataSource.DS_ID
				+ "." + JobMonitorHistoryVo.Fields.JOB_NAME);
		
		DataSourceField exitCodeField = new DataSourceField(JobExecutionHistoryVo.Fields.EXIT_CODE, FieldType.TEXT);
		DataSourceField startTimeField = new DataSourceField(JobExecutionHistoryVo.Fields.START_TIME, FieldType.DATETIME);
		DataSourceField endTimeField = new DataSourceField(JobExecutionHistoryVo.Fields.END_TIME,  FieldType.DATETIME);
		DataSourceField exitMessageField = new DataSourceField(JobExecutionHistoryVo.Fields.EXIT_MESSAGE, FieldType.TEXT);
		
		addField(executionIdField);
		addField(nameField);
		addField(exitCodeField); 
		addField(startTimeField); 
		addField(endTimeField);
		addField(exitMessageField);
	}
	
	@Override
	protected void executeFetch(final String requestId, DSRequest request,
			final DSResponse response) {
		String[] jobNames = null;
		if(null != request.getCriteria()){
			jobNames = request.getCriteria().getAttributeAsStringArray("jobNames");
		}
		monitorService.loadJobCurrentExecutionData(jobNames, new AsyncCallback<List<JobExecutionHistoryVo>>() {
			
			@Override
			public void onSuccess(List<JobExecutionHistoryVo> jobExecutionData) {
				ListGridRecord[] records = new ListGridRecord[jobExecutionData.size()];
				if(null == jobExecutionData || jobExecutionData.size() <= 0){
					response.setData(records);
					processResponse(requestId, response);
					return;
				}
				
				for (int i = 0; i < jobExecutionData.size(); i++) {
					JobExecutionHistoryVo monitorVo = jobExecutionData.get(i);
					ListGridRecord record = new ListGridRecord();
					record.setAttribute(JobExecutionHistoryVo.Fields.JOB_NAME, monitorVo.getJobName());
					record.setAttribute(JobExecutionHistoryVo.Fields.JOB_EXECUTION_ID, monitorVo.getJobExecutionId());
					record.setAttribute(JobExecutionHistoryVo.Fields.EXIT_CODE, monitorVo.getExitCode());
					record.setAttribute(JobExecutionHistoryVo.Fields.START_TIME, monitorVo.getStartTime());
					record.setAttribute(JobExecutionHistoryVo.Fields.END_TIME, monitorVo.getEndTime());
					record.setAttribute(JobExecutionHistoryVo.Fields.EXIT_MESSAGE, monitorVo.getExitMessage());
					records[i] = record;
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

	@Override
	protected void executeAdd(String requestId, DSRequest request,
			DSResponse response) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void executeUpdate(String requestId, DSRequest request,
			DSResponse response) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void executeRemove(String requestId, DSRequest request,
			DSResponse response) {
		// TODO Auto-generated method stub

	}

}
