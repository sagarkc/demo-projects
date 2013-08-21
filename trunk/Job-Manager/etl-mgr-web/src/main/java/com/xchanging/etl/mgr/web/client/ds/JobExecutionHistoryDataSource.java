/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.ds.JobExecutionHistoryDataSource
 * Date:	Aug 2, 2013  8:13:29 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.ds;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.xchanging.etl.mgr.model.vo.JobExecutionHistoryVo;
import com.xchanging.etl.mgr.model.vo.JobMonitorHistoryVo;
import com.xchanging.etl.mgr.web.client.core.GwtRpcObjectDataSource;
import com.xchanging.etl.mgr.web.client.endpoint.RemoteServiceEndpointFactory;
import com.xchanging.etl.mgr.web.client.service.JobMonitorServiceAsync;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class JobExecutionHistoryDataSource extends GwtRpcObjectDataSource {
	private static JobExecutionHistoryDataSource instance = null;  
	private final JobMonitorServiceAsync monitorService
	 = RemoteServiceEndpointFactory.getInstance().getJobMonitorServiceEndpoint();
	
    public static JobExecutionHistoryDataSource getInstance() {  
        if (instance == null) {  
            instance = new JobExecutionHistoryDataSource("JobExecutionHistoryDataSource");  
        }  
        return instance;  
    }  
	/**
	 * 
	 */
	private JobExecutionHistoryDataSource(String id) {
		setID(id);
		
		
		
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
		String jobName = "";
		if(null != request.getCriteria()){
			jobName = request.getCriteria().getAttribute("value");
		}
		monitorService.loadJobExecutionHistoryDateData(jobName, new AsyncCallback<List<JobExecutionHistoryVo>>() {
			
			@Override
			public void onSuccess(List<JobExecutionHistoryVo> jobExecutionData) {
				ListGridRecord[] records = new ListGridRecord[0];
				if(null != jobExecutionData && jobExecutionData.size() > 0)
				{
					records = new ListGridRecord[jobExecutionData.size()];
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
				}
				
				response.setData(records);
				processResponse(requestId, response);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				SC.say("ERROR from loadHistoricalMonitorData(): " + caught.getMessage());
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
