/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.event.JobLauncherEventListener
 * Date:	Oct 8, 2013  12:53:42 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public interface JobLauncherEventListener extends EventHandler {

	/**
	 * @param jobLauncherEvent
	 */
	void launchJob(JobLauncherEvent jobLauncherEvent);

	
	
	
}
