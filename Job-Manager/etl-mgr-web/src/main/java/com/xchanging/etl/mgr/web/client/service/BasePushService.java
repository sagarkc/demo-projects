/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.service.BasePushService
 * Date:	Aug 22, 2013  4:26:22 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.service;

import com.xchanging.etl.mgr.model.criteria.RTJobFilterCriteria;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public interface BasePushService {

	public void start();
	
	public void start(RTJobFilterCriteria rtJobFilterCriteria);
	
	public void stop();
}
