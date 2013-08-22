/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.server.endpoint.rt.RTAllJobsJobMonitorPushServerEndpoint
 * Date:	Aug 22, 2013  4:59:49 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.server.endpoint.rt;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xchanging.etl.mgr.model.criteria.RTJobFilterCriteria;
import com.xchanging.etl.mgr.model.vo.JobExecutionHistoryVo;
import com.xchanging.etl.mgr.service.JobMonitorService;
import com.xchanging.etl.mgr.web.client.event.push.RealtimeJobMonitorDataEvent;
import com.xchanging.etl.mgr.web.client.service.rt.RTAllJobsJobMonitorPushService;
import com.xchanging.etl.mgr.web.shared.WebConstants;

import de.novanic.eventservice.service.RemoteEventServiceServlet;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 * 
 */
@Component(value = RTAllJobsJobMonitorPushService.RPC_TARGET)
public class RTAllJobsJobMonitorPushServerEndpoint extends
		RemoteEventServiceServlet implements RTAllJobsJobMonitorPushService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6191185376142083455L;

	private static Logger logger = Logger
			.getLogger(RTAllJobsJobMonitorPushServerEndpoint.class);
	private boolean pushEnabled = false;
	private RTJobFilterCriteria jobFilterCriteria;

	@Autowired
	private JobMonitorService jobMonitorService;

	public void pushToClient() {
		if (pushEnabled) {
			List<JobExecutionHistoryVo> data = jobMonitorService
					.loadRealtimeJobMonitorData(jobFilterCriteria);
			addEvent(WebConstants.DOMAIN_MONITOR_JOB,
					new RealtimeJobMonitorDataEvent(data));
		}
	}

	@Override
	public void start() {
		pushEnabled = true;
		logger.info("Start RTAllJobsJobMonitorPushService");
	}

	@Override
	public void start(RTJobFilterCriteria rtJobFilterCriteria) {
		
	}

	@Override
	public void stop() {
		pushEnabled = false;
		logger.info("Stop RTAllJobsJobMonitorPushService");
	}

}
