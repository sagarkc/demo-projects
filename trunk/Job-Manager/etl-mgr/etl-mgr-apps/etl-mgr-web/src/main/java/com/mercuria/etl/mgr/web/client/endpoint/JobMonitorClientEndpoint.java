/**
 * File :: com.mercuria.etl.mgr.web.client.endpoint.JobMonitorClientEndpoint
 * Date :: 24-Jul-2013
 */
package com.mercuria.etl.mgr.web.client.endpoint;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.mercuria.etl.mgr.web.WebConstants;
import com.mercuria.etl.mgr.web.client.core.UIEventManager;
import com.mercuria.etl.mgr.web.client.event.HistoricalJobMonitorEvent;
import com.mercuria.etl.mgr.web.client.service.JobMonitorService;
import com.mercuria.etl.mgr.web.client.service.JobMonitorServiceAsync;
import com.mercuria.etl.mgr.web.shared.JSONDataParser;
import com.mercuria.etl.mgr.web.shared.model.JsonDataCollection;


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
		monitorService.loadHistoricalMonitorData(new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				Window.alert("SUCCESS: " + result);
				JsonDataCollection<JavaScriptObject> dataCollection = JSONDataParser.parseJsonToJSObject(result);
				HistoricalJobMonitorEvent event = new HistoricalJobMonitorEvent(dataCollection.getRecords());
				uiEventManager.fireEvent(event);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ERROR: " + caught.getMessage());
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
