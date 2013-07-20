/**
 * File :: com.mercuria.etl.mgr.web.client.service.JobMonitorService
 * Date :: 20-Jul-2013
 */
package com.mercuria.etl.mgr.web.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.mercuria.etl.mgr.web.WebConstants;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
@RemoteServiceRelativePath(JobMonitorService.RPC_TARGET + WebConstants.RPC_EXT)
public interface JobMonitorService extends RemoteService{
	
	String RPC_TARGET = "jobMonitor";
	
	
}
