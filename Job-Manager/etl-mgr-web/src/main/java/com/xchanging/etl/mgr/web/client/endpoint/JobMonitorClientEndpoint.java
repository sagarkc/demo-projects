/**
 * File :: com.xchanging.etl.mgr.web.client.endpoint.JobMonitorClientEndpoint
 * Date :: 24-Jul-2013
 */
package com.xchanging.etl.mgr.web.client.endpoint;


import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.xchanging.etl.mgr.model.vo.JobMonitorHistoryVo;
import com.smartgwt.client.util.SC;
import com.xchanging.etl.mgr.web.WebConstants;
import com.xchanging.etl.mgr.web.client.core.UIEventManager;
import com.xchanging.etl.mgr.web.client.event.HistoricalJobMonitorEvent;
import com.xchanging.etl.mgr.web.client.service.JobMonitorService;
import com.xchanging.etl.mgr.web.client.service.JobMonitorServiceAsync;


/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class JobMonitorClientEndpoint {

	private static UIEventManager uiEventManager = UIEventManager.getInstance();
	private JobMonitorServiceAsync monitorService;
	
	public JobMonitorClientEndpoint() {
		monitorService = GWT.create(JobMonitorService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) monitorService;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL()
				+ JobMonitorService.RPC_TARGET + WebConstants.RPC_EXT);
	}
	
	public void loadHistoricalMonitorData(){
		SC.say("JobMonitorClientEndpoint.loadHistoricalMonitorData()");
		monitorService.loadHistoricalMonitorData(new AsyncCallback<List<JobMonitorHistoryVo>>() {
			
			@Override
			public void onSuccess(List<JobMonitorHistoryVo> result) {
				HistoricalJobMonitorEvent event = new HistoricalJobMonitorEvent(result);
				uiEventManager.fireEvent(event);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				SC.say("ERROR from loadHistoricalMonitorData(): " + caught.getStackTrace());
			}
		});
	}
	
	public void loadHistoricalMonitorData(List<String> jobNames){
		
	}
	
	public void loadHistoricalMonitorData(List<String> jobNames, Date startTime, Date endTime){
		
	}
	
	public void loadCurrentMonitorData(List<String> jobNames){
		
	}
}
