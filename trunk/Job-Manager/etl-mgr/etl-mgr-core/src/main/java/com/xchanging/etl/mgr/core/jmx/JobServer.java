/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.core.jmx.JobServer
 * Date:	Aug 30, 2013  4:38:48 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.core.jmx;

import com.xchanging.etl.mgr.model.scheduler.JobServerInfo;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class JobServer {

	private String name;
	private JobServerInfo jobServerInfo;

	/**
	 * 
	 */
	public JobServer() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the jobServerInfo
	 */
	public JobServerInfo getJobServerInfo() {
		return jobServerInfo;
	}

	/**
	 * @param jobServerInfo the jobServerInfo to set
	 */
	public void setJobServerInfo(JobServerInfo jobServerInfo) {
		this.jobServerInfo = jobServerInfo;
	}
	
	
}
