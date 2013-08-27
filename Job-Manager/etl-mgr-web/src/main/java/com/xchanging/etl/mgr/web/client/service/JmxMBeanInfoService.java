/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.service.JmxMBeanInfoService
 * Date:	Aug 27, 2013  11:37:05 AM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.xchanging.etl.mgr.web.shared.WebConstants;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
@RemoteServiceRelativePath(JmxMBeanInfoService.RPC_TARGET + WebConstants.RPC_EXT)
public interface JmxMBeanInfoService extends RemoteService {

	String RPC_TARGET = "jmxMBeanInfoService";
	
	List<String> getSchedulerMbeanNames(String jmxHost, int jmxPort);
	
}
