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
import com.xchanging.etl.mgr.web.client.service.JmxMBeanInfoService;
import com.xchanging.etl.mgr.web.client.service.JmxMBeanInfoServiceAsync;
import com.xchanging.etl.mgr.web.client.service.JobMonitorService;
import com.xchanging.etl.mgr.web.client.service.JobMonitorServiceAsync;
import com.xchanging.etl.mgr.web.client.service.rt.RTAllJobsJobMonitorPushService;
import com.xchanging.etl.mgr.web.client.service.rt.RTAllJobsJobMonitorPushServiceAsync;
import com.xchanging.etl.mgr.web.client.service.rt.RTFilteredJobMonitorPushService;
import com.xchanging.etl.mgr.web.client.service.rt.RTFilteredJobMonitorPushServiceAsync;
import com.xchanging.etl.mgr.web.client.service.rt.RTLastDayJobMonitorPushService;
import com.xchanging.etl.mgr.web.client.service.rt.RTLastDayJobMonitorPushServiceAsync;
import com.xchanging.etl.mgr.web.client.service.rt.RTLastHourJobMonitorPushService;
import com.xchanging.etl.mgr.web.client.service.rt.RTLastHourJobMonitorPushServiceAsync;
import com.xchanging.etl.mgr.web.client.service.rt.RTSelectedJobMonitorPushService;
import com.xchanging.etl.mgr.web.client.service.rt.RTSelectedJobMonitorPushServiceAsync;
import com.xchanging.etl.mgr.web.client.service.rt.RealTimeJobMonitorService;
import com.xchanging.etl.mgr.web.client.service.rt.RealTimeJobMonitorServiceAsync;
import com.xchanging.etl.mgr.web.shared.WebConstants;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public final class RemoteServiceEndpointFactory {

	private static RemoteServiceEndpointFactory instance;
	
	private final JobMonitorServiceAsync jobMonitorServiceEndpoint;
	private final RealTimeJobMonitorServiceAsync realTimeJobMonitorServiceEndpoint;
	
	private final RTAllJobsJobMonitorPushServiceAsync rtAllJobsJobMonitorPushServiceEndpoint;
	private final RTFilteredJobMonitorPushServiceAsync rtFilteredJobMonitorPushServiceEndpoint;
	private final RTLastDayJobMonitorPushServiceAsync rtLastDayJobMonitorPushServiceEndpoint;
	private final RTLastHourJobMonitorPushServiceAsync rtLastHourJobMonitorPushServiceEndpoint;
	private final RTSelectedJobMonitorPushServiceAsync rtSelectedJobMonitorPushServiceEndpoint;
	
	private final JmxMBeanInfoServiceAsync jmxMBeanInfoServerEndpoint;
	
	private RemoteServiceEndpointFactory(){
		jobMonitorServiceEndpoint = GWT.create(JobMonitorService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) jobMonitorServiceEndpoint;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL()
				+ JobMonitorService.RPC_TARGET + WebConstants.RPC_EXT);
		
		realTimeJobMonitorServiceEndpoint = GWT.create(RealTimeJobMonitorService.class);
		
		rtAllJobsJobMonitorPushServiceEndpoint = GWT.create(RTAllJobsJobMonitorPushService.class);
		rtFilteredJobMonitorPushServiceEndpoint = GWT.create(RTFilteredJobMonitorPushService.class);
		rtLastDayJobMonitorPushServiceEndpoint = GWT.create(RTLastDayJobMonitorPushService.class);
		rtLastHourJobMonitorPushServiceEndpoint = GWT.create(RTLastHourJobMonitorPushService.class);
		rtSelectedJobMonitorPushServiceEndpoint = GWT.create(RTSelectedJobMonitorPushService.class);
		
		jmxMBeanInfoServerEndpoint = GWT.create(JmxMBeanInfoService.class);
		
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
	 * @return the realTimeJobMonitorServiceEndpoint
	 */
	public RealTimeJobMonitorServiceAsync getRealTimeJobMonitorServiceEndpoint() {
		return realTimeJobMonitorServiceEndpoint;
	}

	/**
	 * @return the rtAllJobsJobMonitorPushServiceEndpoint
	 */
	public RTAllJobsJobMonitorPushServiceAsync getRtAllJobsJobMonitorPushServiceEndpoint() {
		return rtAllJobsJobMonitorPushServiceEndpoint;
	}

	/**
	 * @return the rtFilteredJobMonitorPushServiceEndpoint
	 */
	public RTFilteredJobMonitorPushServiceAsync getRtFilteredJobMonitorPushServiceEndpoint() {
		return rtFilteredJobMonitorPushServiceEndpoint;
	}

	/**
	 * @return the rtLastDayJobMonitorPushServiceEndpoint
	 */
	public RTLastDayJobMonitorPushServiceAsync getRtLastDayJobMonitorPushServiceEndpoint() {
		return rtLastDayJobMonitorPushServiceEndpoint;
	}

	/**
	 * @return the rtLastHourJobMonitorPushServiceEndpoint
	 */
	public RTLastHourJobMonitorPushServiceAsync getRtLastHourJobMonitorPushServiceEndpoint() {
		return rtLastHourJobMonitorPushServiceEndpoint;
	}

	/**
	 * @return the rtSelectedJobMonitorPushServiceEndpoint
	 */
	public RTSelectedJobMonitorPushServiceAsync getRtSelectedJobMonitorPushServiceEndpoint() {
		return rtSelectedJobMonitorPushServiceEndpoint;
	}

	public JmxMBeanInfoServiceAsync getJmxMBeanInfoServerEndpoint() {
		return jmxMBeanInfoServerEndpoint;
	}
	
	
	
}
