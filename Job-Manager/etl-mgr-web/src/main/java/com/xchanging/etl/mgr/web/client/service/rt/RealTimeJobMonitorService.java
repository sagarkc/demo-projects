/**
 * 
 */
package com.xchanging.etl.mgr.web.client.service.rt;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.xchanging.etl.mgr.model.criteria.RTJobFilterCriteria;
import com.xchanging.etl.mgr.model.vo.JobExecutionHistoryVo;
import com.xchanging.etl.mgr.web.shared.WebConstants;

/**
 * @author Sabuj
 *
 */
@RemoteServiceRelativePath(RealTimeJobMonitorService.RPC_TARGET + WebConstants.RPC_EXT)
public interface RealTimeJobMonitorService extends RemoteService{
	String RPC_TARGET = "realTimeJobMonitorService";
	
	List<JobExecutionHistoryVo> loadRealtimeJobMonitorData(RTJobFilterCriteria filterCriteria);
	
	List<JobExecutionHistoryVo> loadRTLastHourJobMonitorData();
	List<JobExecutionHistoryVo> loadRTLastDayJobMonitorData();
	List<JobExecutionHistoryVo> loadRTAllJobsJobMonitorData();
	
}
