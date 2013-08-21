/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.server.trigger.RealtimeJobMonitorTrigger
 * Date:	Aug 21, 2013  4:19:52 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.server.trigger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xchanging.etl.mgr.web.server.endpoint.JobMonitorPushServiceEndpoint;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
@Component(value="realtimeJobMonitorTrigger")
public class RealtimeJobMonitorTrigger {

	@Autowired private JobMonitorPushServiceEndpoint jobMonitorPushService;
	
	public void loadCurrentJobInfo(){
		if(null != jobMonitorPushService){
			try {
				jobMonitorPushService.pushToClient();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
