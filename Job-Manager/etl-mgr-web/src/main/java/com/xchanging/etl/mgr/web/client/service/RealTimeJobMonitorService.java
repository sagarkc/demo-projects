/**
 * 
 */
package com.xchanging.etl.mgr.web.client.service;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.xchanging.etl.mgr.web.shared.WebConstants;

/**
 * @author Sabuj
 *
 */
@RemoteServiceRelativePath(RealTimeJobMonitorService.RPC_TARGET + WebConstants.RPC_EXT)
public interface RealTimeJobMonitorService {
	String RPC_TARGET = "realTimeJobMonitorService";
}
