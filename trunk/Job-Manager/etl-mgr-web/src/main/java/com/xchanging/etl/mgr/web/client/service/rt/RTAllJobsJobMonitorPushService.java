/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.service.rt.RTAllJobsJobMonitorPushService
 * Date:	Aug 22, 2013  4:22:43 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.service.rt;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.xchanging.etl.mgr.web.client.service.BasePushService;
import com.xchanging.etl.mgr.web.shared.WebConstants;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
@RemoteServiceRelativePath(RTAllJobsJobMonitorPushService.RPC_TARGET + WebConstants.RPC_EXT)
public interface RTAllJobsJobMonitorPushService extends BasePushService, RemoteService {

	String RPC_TARGET = "rtAllJobsJobMonitorPushService";
	
	
}
