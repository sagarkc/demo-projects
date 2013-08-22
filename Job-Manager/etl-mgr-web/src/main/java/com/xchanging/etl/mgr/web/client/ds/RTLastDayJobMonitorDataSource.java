/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.ds.RTLastHourJobMonitorDataSource
 * Date:	Aug 22, 2013  6:54:58 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.ds;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.xchanging.etl.mgr.model.vo.JobExecutionHistoryVo;
import com.xchanging.etl.mgr.web.client.endpoint.RemoteServiceEndpointFactory;
import com.xchanging.etl.mgr.web.client.service.rt.RealTimeJobMonitorServiceAsync;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class RTLastDayJobMonitorDataSource extends RealTimeJobExecutionDataSource {
	private static RTLastDayJobMonitorDataSource instance = null;  
	
	private final RealTimeJobMonitorServiceAsync monitorService
		= RemoteServiceEndpointFactory.getInstance().getRealTimeJobMonitorServiceEndpoint();
	
    public static RTLastDayJobMonitorDataSource getInstance() {  
        if (instance == null) {  
            instance = new RTLastDayJobMonitorDataSource("RTLastDayJobMonitorDataSource");  
        }  
        return instance;  
    }  
	/**
	 * 
	 */
	private RTLastDayJobMonitorDataSource(String id) {
		super(id);
	}
	
	@Override
	protected void executeFetch(final String requestId, DSRequest request,
			final DSResponse response) {
		monitorService.loadRTLastDayJobMonitorData(new AsyncCallback<List<JobExecutionHistoryVo>>() {
			
			@Override
			public void onSuccess(List<JobExecutionHistoryVo> jobExecutionData) {
				ListGridRecord[] records = new ListGridRecord[0];
				populateData(requestId, response, jobExecutionData, records);
				response.setData(records);
				processResponse(requestId, response);
			}

			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR from loadRealtimeJobMonitorData(): " + caught.getMessage());
			}
		});
	}
	
	
	
}
