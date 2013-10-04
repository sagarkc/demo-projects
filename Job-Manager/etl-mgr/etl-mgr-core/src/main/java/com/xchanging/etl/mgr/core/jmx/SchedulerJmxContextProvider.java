/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.core.jmx.SchedulerJmxContextProvider
 * Date:	Aug 27, 2013  12:20:04 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.core.jmx;


/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public interface SchedulerJmxContextProvider {

	String getHostName();
	int getPortNumber();
	String getJmxUrl();
	SchedulerJmxContext getSchedulerJmxContext();
	
	String formJmxUrl(String host, int port);

	/**
	 * @param jmxUrl
	 * @return
	 * @throws Exception 
	 */
	void createContext(String jmxUrl) throws  Exception;
	
}
