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

import com.xchanging.etl.mgr.web.server.endpoint.rt.RTAllJobsJobMonitorPushServerEndpoint;
import com.xchanging.etl.mgr.web.server.endpoint.rt.RTLastDayJobMonitorPushServerEndpoint;
import com.xchanging.etl.mgr.web.server.endpoint.rt.RTLastHourJobMonitorPushServerEndpoint;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
@Component(value="realtimeJobMonitorTrigger")
public class RealtimeJobMonitorTrigger {

	@Autowired private RTAllJobsJobMonitorPushServerEndpoint allJobsJobMonitorPushServerEndpoint;
	@Autowired private RTLastDayJobMonitorPushServerEndpoint lastDayJobMonitorPushServerEndpoint;
	@Autowired private RTLastHourJobMonitorPushServerEndpoint lastHourJobMonitorPushServerEndpoint;
	
	public void loadCurrentJobInfo(){
		if(null != allJobsJobMonitorPushServerEndpoint){
			try {
				allJobsJobMonitorPushServerEndpoint.pushToClient();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(null != lastDayJobMonitorPushServerEndpoint){
			try {
				lastDayJobMonitorPushServerEndpoint.pushToClient();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(null != lastHourJobMonitorPushServerEndpoint){
			try {
				lastHourJobMonitorPushServerEndpoint.pushToClient();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
