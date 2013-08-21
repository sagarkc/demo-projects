/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.server.endpoint.JobMonitorPushServiceEndpoint
 * Date:	Aug 21, 2013  1:03:06 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.server.endpoint;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xchanging.etl.mgr.model.vo.JobExecutionHistoryVo;
import com.xchanging.etl.mgr.service.JobDetailMonitorService;
import com.xchanging.etl.mgr.web.client.event.push.RealtimeJobMonitorDataEvent;
import com.xchanging.etl.mgr.web.client.service.JobMonitorPushService;
import com.xchanging.etl.mgr.web.shared.WebConstants;

import de.novanic.eventservice.service.RemoteEventServiceServlet;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
@Component(value=JobMonitorPushService.RPC_TARGET)
public class JobMonitorPushServiceEndpoint extends RemoteEventServiceServlet
		implements JobMonitorPushService {

	private static Logger logger = Logger.getLogger(JobMonitorPushServiceEndpoint.class);
	
	@Autowired
	private JobDetailMonitorService jobDetailMonitorService;
	
	public List<JobExecutionHistoryVo> loadJobCurrentExecutionData(
			String[] jobNames) {
		return jobDetailMonitorService.loadJobCurrentExecutionData(jobNames);
	}
	
	public synchronized void pushToClient() throws Exception {
		List<JobExecutionHistoryVo> data = loadJobCurrentExecutionData(null);
		addEvent(WebConstants.DOMAIN_MONITOR_JOB, new RealtimeJobMonitorDataEvent(data));
	}
	
	/* (non-Javadoc)
	 * @see com.xchanging.etl.mgr.web.client.service.JobMonitorPushService#start()
	 */
	@Override
	public void start() {
		logger.info("JobMonitorPushService started");
	}

}
