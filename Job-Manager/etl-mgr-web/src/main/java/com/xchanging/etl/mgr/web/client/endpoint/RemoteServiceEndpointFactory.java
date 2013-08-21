/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.endpoint.RemoteServiceEndpointFactory
 * Date:	Aug 21, 2013  1:22:37 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.endpoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.xchanging.etl.mgr.web.client.service.JobMonitorPushService;
import com.xchanging.etl.mgr.web.client.service.JobMonitorPushServiceAsync;
import com.xchanging.etl.mgr.web.client.service.JobMonitorService;
import com.xchanging.etl.mgr.web.client.service.JobMonitorServiceAsync;
import com.xchanging.etl.mgr.web.shared.WebConstants;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public final class RemoteServiceEndpointFactory {

	private static RemoteServiceEndpointFactory instance;
	
	private final JobMonitorServiceAsync jobMonitorServiceEndpoint;
	private final JobMonitorPushServiceAsync jobMonitorPushServiceEndpoint;
	
	private RemoteServiceEndpointFactory(){
		jobMonitorServiceEndpoint = GWT.create(JobMonitorService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) jobMonitorServiceEndpoint;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL()
				+ JobMonitorService.RPC_TARGET + WebConstants.RPC_EXT);
		
		jobMonitorPushServiceEndpoint = GWT.create(JobMonitorPushService.class);
		
	}

	/**
	 * @return the instance
	 */
	public static RemoteServiceEndpointFactory getInstance() {
		if(null != instance)
			return instance;
		synchronized (RemoteServiceEndpointFactory.class) {
			if(null == instance){
				instance = new RemoteServiceEndpointFactory();
			}
		}
		return instance;
	}

	/**
	 * @return the jobMonitorServiceEndpoint
	 */
	public JobMonitorServiceAsync getJobMonitorServiceEndpoint() {
		return jobMonitorServiceEndpoint;
	}

	/**
	 * @return the jobMonitorPushServiceEndpoint
	 */
	public JobMonitorPushServiceAsync getJobMonitorPushServiceEndpoint() {
		return jobMonitorPushServiceEndpoint;
	}
	
	
	
}
