/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.service.JmxJobRunnerServiceAsync
 * Date:	Oct 8, 2013  1:00:14 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public interface JmxJobRunnerServiceAsync {

	public void runJob(String jobName, AsyncCallback<String> asyncCallback);
	
}
