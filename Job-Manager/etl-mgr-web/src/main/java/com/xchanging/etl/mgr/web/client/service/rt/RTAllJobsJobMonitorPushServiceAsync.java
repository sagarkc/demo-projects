/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.service.rt.RTAllJobsJobMonitorPushServiceAsync
 * Date:	Aug 22, 2013  6:50:21 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.service.rt;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.xchanging.etl.mgr.model.criteria.RTJobFilterCriteria;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public interface RTAllJobsJobMonitorPushServiceAsync {

	/**
	 * 
	 * @see com.xchanging.etl.mgr.web.client.service.BasePushService#start()
	 */
	void start(AsyncCallback<Void> callback);

	/**
	 * 
	 * @see com.xchanging.etl.mgr.web.client.service.BasePushService#start(com.xchanging.etl.mgr.model.criteria.RTJobFilterCriteria)
	 */
	void start(RTJobFilterCriteria rtJobFilterCriteria,
			AsyncCallback<Void> callback);

	/**
	 * 
	 * @see com.xchanging.etl.mgr.web.client.service.BasePushService#stop()
	 */
	void stop(AsyncCallback<Void> callback);

}
