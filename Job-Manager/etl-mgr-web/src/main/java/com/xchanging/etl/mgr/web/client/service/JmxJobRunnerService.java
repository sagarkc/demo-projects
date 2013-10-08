/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.service.JmxJobRunnerService
 * Date:	Sep 20, 2013  12:05:43 PM
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
@RemoteServiceRelativePath(JmxJobRunnerService.RPC_TARGET + WebConstants.RPC_EXT)
public interface JmxJobRunnerService extends RemoteService{

	String RPC_TARGET = "jmxJobRunnerService";
	
	public String runJob(String jobName);
}
