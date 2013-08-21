/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.service.JobMonitorPushService
 * Date:	Aug 21, 2013  12:27:49 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.xchanging.etl.mgr.web.shared.WebConstants;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
@RemoteServiceRelativePath(JobMonitorPushService.RPC_TARGET + WebConstants.RPC_EXT)
public interface JobMonitorPushService extends RemoteService {

	String RPC_TARGET = "jobMonitorPushService";
	
	public void start();
	
}
