/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.core.jmx.SchedulerJmxContextFactory
 * Date:	Aug 26, 2013  7:04:34 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.core.jmx;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public final class SchedulerJmxContextFactory {

	private static SchedulerJmxContextFactory contextFactory;
	
	private Map<String, SchedulerJmxContext> schedulerJmxContextMap;
	private SchedulerJmxContextProvider schedulerJmxContextProvider;
	
	/**
	 * 
	 */
	private SchedulerJmxContextFactory() {
		schedulerJmxContextMap = new LinkedHashMap<>();
	}
	
	/**
	 * @return the schedulerJmxContextProvider
	 */
	public SchedulerJmxContextProvider getSchedulerJmxContextProvider() {
		return schedulerJmxContextProvider;
	}

	/**
	 * @param schedulerJmxContextProvider the schedulerJmxContextProvider to set
	 */
	public void setSchedulerJmxContextProvider(
			SchedulerJmxContextProvider schedulerJmxContextProvider) {
		this.schedulerJmxContextProvider = schedulerJmxContextProvider;
	}


	public static SchedulerJmxContextFactory getInstance() {
		if(null != contextFactory)
			return contextFactory;
		synchronized (SchedulerJmxContextFactory.class) {
			if(null == contextFactory){
				contextFactory = new SchedulerJmxContextFactory();
			}
		}
		return contextFactory;
	}

	public SchedulerJmxContext createSchedulerJmxContext(String jmxHost, int jmxPort) throws Exception{
		String jmxUrl = schedulerJmxContextProvider.formJmxUrl(jmxHost, jmxPort);
		if(schedulerJmxContextMap.containsKey(jmxUrl)){
			return schedulerJmxContextMap.get(jmxUrl);
		}
		SchedulerJmxContext context = schedulerJmxContextProvider.createContext(jmxUrl);
		schedulerJmxContextMap.put(jmxUrl, context);
		return context;
		
	}
	
}
