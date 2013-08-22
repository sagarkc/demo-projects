/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.server.endpoint.rt.RealTimeJobMonitorServerEndpoint
 * Date:	Aug 22, 2013  3:26:54 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.server.endpoint.rt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xchanging.etl.mgr.model.criteria.RTJobFilterCriteria;
import com.xchanging.etl.mgr.model.vo.JobExecutionHistoryVo;
import com.xchanging.etl.mgr.service.JobMonitorService;
import com.xchanging.etl.mgr.util.DateUtility;
import com.xchanging.etl.mgr.web.client.service.rt.RealTimeJobMonitorService;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
@Component(value=RealTimeJobMonitorService.RPC_TARGET)
public class RealTimeJobMonitorServerEndpoint implements
		RealTimeJobMonitorService {

	@Autowired private JobMonitorService jobMonitorService;
	
	@Override
	public List<JobExecutionHistoryVo> loadRealtimeJobMonitorData(
			RTJobFilterCriteria filterCriteria) {
		return jobMonitorService.loadRealtimeJobMonitorData(filterCriteria);
	}

	
	@Override
	public List<JobExecutionHistoryVo> loadRTLastHourJobMonitorData() {
		RTJobFilterCriteria fc = new RTJobFilterCriteria();
		fc.setStartedOnOrAfter(DateUtility.getCurrentDateWitHour());
		return jobMonitorService.loadRealtimeJobMonitorData(fc);
	}

	
	@Override
	public List<JobExecutionHistoryVo> loadRTLastDayJobMonitorData() {
		RTJobFilterCriteria fc = new RTJobFilterCriteria();
		fc.setStartedOnOrAfter(DateUtility.getCurrentDateWithoutTime());
		return jobMonitorService.loadRealtimeJobMonitorData(fc);
	}

	
	@Override
	public List<JobExecutionHistoryVo> loadRTAllJobsJobMonitorData() {
		return jobMonitorService.loadRealtimeJobMonitorData(null);
	}

}
