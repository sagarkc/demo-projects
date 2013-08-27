/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.server.endpoint.jmx.JmxMBeanInfoServerEndpoint
 * Date:	Aug 27, 2013  12:13:48 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.server.endpoint.jmx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.xchanging.etl.mgr.core.jmx.SchedulerJmxContext;
import com.xchanging.etl.mgr.core.jmx.SchedulerJmxContextFactory;
import com.xchanging.etl.mgr.web.client.service.JmxMBeanInfoService;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
@Component(value=JmxMBeanInfoService.RPC_TARGET)
public class JmxMBeanInfoServerEndpoint implements JmxMBeanInfoService{

	
	
	@Override
	public List<String> getSchedulerMbeanNames(String jmxHost, int jmxPort) {
		try {
			SchedulerJmxContext schedulerJmxContext = SchedulerJmxContextFactory.getInstance()
					.createSchedulerJmxContext(jmxHost, jmxPort);
			
			if(null != schedulerJmxContext){
				return Arrays.asList(schedulerJmxContext.getMbeanNames());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<String>();
	}

	
}
