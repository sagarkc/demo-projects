/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.event.SecurityContextLoaderEventListener
 * Date:	Aug 20, 2013  6:06:36 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public interface SecurityContextLoaderEventListener extends EventHandler {

	void securityContextLoaded(SecurityContextLoaderEvent event);
	
}
