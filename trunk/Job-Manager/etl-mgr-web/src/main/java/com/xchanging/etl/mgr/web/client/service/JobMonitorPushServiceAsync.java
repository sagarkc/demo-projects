/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.service.JobMonitorPushServiceAsync
 * Date:	Aug 21, 2013  12:46:49 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public interface JobMonitorPushServiceAsync {

	/**
	 * 
	 * @see com.xchanging.etl.mgr.web.client.service.JobMonitorPushService#start()
	 */
	void start(AsyncCallback<Void> callback);

}
