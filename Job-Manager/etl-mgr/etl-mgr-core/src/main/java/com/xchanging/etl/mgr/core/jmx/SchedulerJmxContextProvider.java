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

import java.io.IOException;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public interface SchedulerJmxContextProvider {

	String formJmxUrl(String host, int port);

	/**
	 * @param jmxUrl
	 * @return
	 * @throws Exception 
	 */
	SchedulerJmxContext createContext(String jmxUrl) throws  Exception;
	
}
