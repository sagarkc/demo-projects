package com.xchanging.etl.mgr.web.shared;

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
	Domain DOMAIN_MONITOR_SCHEDULER = DomainFactory
			.getDomain("CONTEXT_MONITOR_SCHEDULER");

	/*
	 * COMPLETED FAILED STARTED
	 */
	String JOB_STATUS_COMPLETED = "COMPLETED";
	String JOB_STATUS_FAILED = "FAILED";
	String JOB_STATUS_STARTED = "STARTED";

	String JOB_EXIT_CODE_COMPLETED = "COMPLETED";
	String JOB_EXIT_CODE_FAILED = "FAILED";
	String JOB_EXIT_CODE_STARTED = "STARTED";
	String JOB_EXIT_CODE_UNKNOWN = "UNKNOWN";

	// JOB Status cell Styles
	String STYLE_JOB_STATUS_STARTED = 
			"border: .1em solid; border-radius:.5em; color: #00529B; "
			+ "background-color: #BDE5F8; font-weight:bold; ";
	String STYLE_JOB_STATUS_COMPLETED = 
			"border: .1em solid; border-radius:.5em; color: #4F8A10; "
			+ "background-color: #DFF2BF; ";
	String STYLE_JOB_STATUS_UNKNOWN = 
			"border: 1px solid; border-radius:.5em; "
			+ "color: #9F6000; background-color: #FEEFB3; ";
	String STYLE_JOB_STATUS_ERROR = 
			"border: 1px solid; border-radius:.5em; "
			+ "color: #D8000C; background-color: #FFBABA; ";
	
}
