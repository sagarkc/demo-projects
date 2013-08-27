/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.service.JmxMBeanInfoServiceAsync
 * Date:	Aug 27, 2013  11:45:01 AM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public interface JmxMBeanInfoServiceAsync {

	/**
	 * 
	 * @see com.xchanging.etl.mgr.web.client.service.JmxMBeanInfoService#getSchedulerMbeanNames(java.lang.String, int)
	 */
	void getSchedulerMbeanNames(String jmxHost, int jmxPort,
			AsyncCallback<List<String>> callback);

}
