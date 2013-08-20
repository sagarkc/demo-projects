/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.SecurityContext
 * Date:	Aug 20, 2013  6:03:15 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class SecurityContext {

	private static SecurityContext context;
	
	private SecurityContext(){
		
	}
	/**
	 * @return the context
	 */
	public static SecurityContext getContext() {
		if(null != context)
			return context;
		synchronized (SecurityContext.class) {
			if(null == context){
				context = new SecurityContext();
			}
		}
		return context;
	}
	
}
