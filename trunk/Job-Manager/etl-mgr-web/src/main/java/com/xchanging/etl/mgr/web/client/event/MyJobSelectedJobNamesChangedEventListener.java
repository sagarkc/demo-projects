/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.event.MyJobSelectedJobNamesChangedEventListener
 * Date:	Aug 8, 2013  8:02:06 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public interface MyJobSelectedJobNamesChangedEventListener extends EventHandler{

	void selectedJobNamesChanged(MyJobSelectedJobNamesChangedEvent event);
	
}
