/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.core.JobServerFactory
 * Date:	Sep 5, 2013  11:45:38 AM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.core;

import java.util.LinkedHashMap;
import java.util.Map;

import com.xchanging.etl.mgr.core.jmx.JobServer;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public final class JobServerFactory {

	private static JobServerFactory factory;
	
	private Map<String, JobServer> jobServerMap;
	
	private JobServerFactory(){
		jobServerMap = new LinkedHashMap<>();
	}
	
	public static JobServerFactory getInstance(){
		if(null != factory)
			return factory;
		synchronized (JobServerFactory.class) {
			if(null == factory)
				factory = new JobServerFactory();
		}
		return factory;
	}
	
	public JobServer getJobServer(String name){
		return jobServerMap.get(name);
	}
	
	public void addJobServer(JobServer jobServer){
		if(null == jobServer)
			return;
		jobServerMap.put(jobServer.getName(), jobServer);
	}
	
}
