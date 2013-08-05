/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.mercuria.etl.mgr.web.client.ds.JobExecutionHistoryDataSource
 * Date:	Aug 2, 2013  8:13:29 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.mercuria.etl.mgr.web.client.ds;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.mercuria.etl.mgr.model.vo.JobMonitorHistoryVo;
import com.mercuria.etl.mgr.web.WebConstants;
import com.mercuria.etl.mgr.web.client.core.GwtRpcObjectDataSource;
import com.mercuria.etl.mgr.web.client.service.JobMonitorService;
import com.mercuria.etl.mgr.web.client.service.JobMonitorServiceAsync;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.util.JSONEncoder;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class JobExecutionHistoryDataSource extends GwtRpcObjectDataSource {

	private final JobMonitorServiceAsync monitorService;
	/**
	 * 
	 */
	public JobExecutionHistoryDataSource() {
		
		monitorService = GWT.create(JobMonitorService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) monitorService;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL()
				+ JobMonitorService.RPC_TARGET + WebConstants.RPC_EXT);
		
		DataSourceField nameField = new DataSourceField("jobName", FieldType.TEXT);
		nameField.setPrimaryKey(true);
		
		DataSourceField exitCodeField = new DataSourceField("status", FieldType.TEXT);
		DataSourceField startTimeField = new DataSourceField("lastStartedTime", FieldType.DATETIME);
		DataSourceField endTimeField = new DataSourceField("lastEndedTime",  FieldType.DATETIME);
		DataSourceField executeJobField = new DataSourceField("executeJob", FieldType.ANY);
		
		addField(nameField);
		addField(exitCodeField); 
		addField(startTimeField); 
		addField(endTimeField);
		addField(executeJobField);
	}
	
	@Override
	protected void executeFetch(final String requestId, DSRequest request,
			final DSResponse response) {
		Window.alert("JobExecutionHistoryDataSource.executeFetch()");
		monitorService.loadHistoricalMonitorData(new AsyncCallback<List<JobMonitorHistoryVo>>() {
			
			@Override
			public void onSuccess(List<JobMonitorHistoryVo> jobExecutionData) {
				/*if(null == jobExecutionData || jobExecutionData.size() <= 0)
					return;*/
				Window.alert("onSuccess:: JobExecutionHistoryDataSource.executeFetch()\n"
						+ jobExecutionData.toString());
				ListGridRecord[] records = new ListGridRecord[jobExecutionData.size()];
				for (int i = 0; i < jobExecutionData.size(); i++) {
					JobMonitorHistoryVo monitorVo = jobExecutionData.get(i);
					ListGridRecord record = new ListGridRecord();
					record.setAttribute("jobName", monitorVo.getJobName());
					record.setAttribute("status", monitorVo.getStatus());
					record.setAttribute("lastStartedTime", monitorVo.getLastStartedTime());
					record.setAttribute("lastEndedTime", monitorVo.getLastEndedTime());
					JSOHelper.setObjectAttribute(null, "executionDetails", monitorVo.getExecutionDetails() );
					//record.setAttribute("executionDetails", );
					//Window.alert("JobExecutionHistoryGrid.populateData():: Record: " + record);
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
