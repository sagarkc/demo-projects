
package com.xchanging.etl.mgr.web;

import de.novanic.eventservice.client.event.domain.Domain;
import de.novanic.eventservice.client.event.domain.DomainFactory;

/**
 * @author Sabuj Das 
 *
 */
public interface WebConstants {

	String RPC_EXT = ".rpc";
	String DISPATCHER_EXT = ".htm";
	
	String FETCH_HISTORICAL_JOB_MONITOR_DATA = "getHistoricalJobMonitorData";
	
	String SERVICE_ROOT_REALTIME_JOB_MONITOR = "monitor/job/realtime";
	String SERVICE_ROOT_HISTORICAL_JOB_MONITOR = "monitor/job/historical";
	
	/**
	 * Domain for monitoring jobs
	 */
	Domain DOMAIN_MONITOR_JOB = DomainFactory.getDomain("CONTEXT_MONITOR_JOB");
	/**
	 * Domain for monitoring Scheduler
	 */
	Domain DOMAIN_MONITOR_SCHEDULER = DomainFactory.getDomain("CONTEXT_MONITOR_SCHEDULER");
	
}
