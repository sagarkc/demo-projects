/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.server.endpoint.rt.RTLastHourJobMonitorPushServerEndpoint
 * Date:	Aug 22, 2013  6:02:06 PM
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
import com.xchanging.etl.mgr.util.DateUtility;
import com.xchanging.etl.mgr.web.client.event.push.RealtimeJobMonitorDataEvent;
import com.xchanging.etl.mgr.web.client.service.rt.RTLastHourJobMonitorPushService;
import com.xchanging.etl.mgr.web.shared.WebConstants;

import de.novanic.eventservice.service.RemoteEventServiceServlet;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
@Component(value=RTLastHourJobMonitorPushService.RPC_TARGET)
public class RTLastHourJobMonitorPushServerEndpoint extends
		RemoteEventServiceServlet implements RTLastHourJobMonitorPushService {

	private static final long serialVersionUID = -6191185376142083455L;

	private static Logger logger = Logger
			.getLogger(RTAllJobsJobMonitorPushServerEndpoint.class);
	private boolean pushEnabled = false;
	private RTJobFilterCriteria jobFilterCriteria;

	@Autowired
	private JobMonitorService jobMonitorService;

	public void pushToClient() {
		if (pushEnabled) {
			if(null == jobFilterCriteria){
				jobFilterCriteria = new RTJobFilterCriteria();
			}
			jobFilterCriteria.setStartedOnOrAfter(DateUtility.getCurrentDateWitHour());
			List<JobExecutionHistoryVo> data = jobMonitorService
					.loadRealtimeJobMonitorData(jobFilterCriteria);
			addEvent(WebConstants.DOMAIN_MONITOR_JOB,
					new RealtimeJobMonitorDataEvent(RealtimeJobMonitorDataEvent.RT_LAST_HOUR_JOBS, data));
		}
	}

	@Override
	public void start() {
		pushEnabled = true;
		logger.info("Start RTAllJobsJobMonitorPushService");
	}

	@Override
	public void start(RTJobFilterCriteria rtJobFilterCriteria) {
		pushEnabled = true;
		logger.info("Start RTAllJobsJobMonitorPushService");
	}

	@Override
	public void stop() {
		pushEnabled = false;
		logger.info("Stop RTAllJobsJobMonitorPushService");
	}

}
