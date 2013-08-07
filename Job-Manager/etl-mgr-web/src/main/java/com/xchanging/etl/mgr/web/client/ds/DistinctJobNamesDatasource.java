/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.ds.DistinctJobNamesDatasource
 * Date:	Aug 7, 2013  3:00:04 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.ds;

import java.util.List;

import com.google.gwt.core.client.GWT;
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
import com.xchanging.etl.mgr.web.client.view.JobHistoryFilterView;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class DistinctJobNamesDatasource extends GwtRpcObjectDataSource {

	private static DistinctJobNamesDatasource instance = null;  
	private final JobMonitorServiceAsync monitorService;
	
    public static DistinctJobNamesDatasource getInstance() {  
        if (instance == null) {  
            instance = new DistinctJobNamesDatasource("DistinctJobNamesDatasource");  
        }  
        return instance;  
    }  
	/**
	 * 
	 */
	private DistinctJobNamesDatasource(String id) {
		setID(id);
		monitorService = GWT.create(JobMonitorService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) monitorService;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL()
				+ JobMonitorService.RPC_TARGET + WebConstants.RPC_EXT);
		
		DataSourceField nameField = new DataSourceField(
				JobExecutionHistoryVo.Fields.JOB_NAME, FieldType.TEXT);
		nameField.setPrimaryKey(true);
		
		setFields(nameField);
	}
	
	
	@Override
	protected void executeFetch(final String requestId, final DSRequest request,
			final DSResponse response) {
		monitorService.loadAllJobNames(new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				if(null == result || result.size() <= 0)
					return;
				
				ListGridRecord[] records = new ListGridRecord[result.size()];
				for (int i = 0; i < result.size(); i++) {
					String jobName = result.get(i);
					ListGridRecord record = new ListGridRecord();
					record.setAttribute(JobMonitorHistoryVo.Fields.JOB_NAME, jobName);
					records[i] = record;
				}
				response.setData(records);
				processResponse(requestId, response);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
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
