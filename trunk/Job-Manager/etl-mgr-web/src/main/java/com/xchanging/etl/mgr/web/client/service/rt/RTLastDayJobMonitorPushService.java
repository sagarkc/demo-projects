/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.service.rt.RTLastDayJobMonitorPushService
 * Date:	Aug 22, 2013  4:25:13 PM
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
@RemoteServiceRelativePath(RTLastDayJobMonitorPushService.RPC_TARGET + WebConstants.RPC_EXT)
public interface RTLastDayJobMonitorPushService extends BasePushService, RemoteService {

	String RPC_TARGET = "rtLastDayJobMonitorPushService";
	
}