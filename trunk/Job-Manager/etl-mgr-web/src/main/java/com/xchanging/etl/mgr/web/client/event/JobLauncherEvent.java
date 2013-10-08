/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.event.JobLauncherEvent
 * Date:	Oct 8, 2013  12:52:34 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class JobLauncherEvent extends GwtEvent<JobLauncherEventListener>{

	public static final Type<JobLauncherEventListener> TYPE
		= new Type<JobLauncherEventListener>();

	private final String jobName;

	/**
	 * @param jobMonitorData
	 */
	public JobLauncherEvent(String jobName) {
		this.jobName = jobName;
	}

	@Override
	public Type<JobLauncherEventListener> getAssociatedType() {
		return TYPE;
	}
	
	@Override
	protected void dispatch(JobLauncherEventListener handler) {
		handler.launchJob(this);
	}

	public String getJobName() {
		return jobName;
	}

	
	
}