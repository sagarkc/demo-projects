/**
 * File :: com.xchanging.etl.mgr.web.client.service.JobMonitorService
 * Date :: 20-Jul-2013
 */
package com.xchanging.etl.mgr.web.client.service;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.xchanging.etl.mgr.model.vo.JobExecutionHistoryVo;
import com.xchanging.etl.mgr.model.vo.JobMonitorHistoryVo;
import com.xchanging.etl.mgr.web.shared.WebConstants;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
@RemoteServiceRelativePath(JobMonitorService.RPC_TARGET + WebConstants.RPC_EXT)
public interface JobMonitorService extends RemoteService{
	
	String RPC_TARGET = "jobMonitor";
	
	List<JobMonitorHistoryVo> loadHistoricalMonitorData();
	
	String loadHistoricalMonitorData(List<String> jobNames);
	
	String loadHistoricalMonitorData(List<String> jobNames, Date startTime, Date endTime);
	
	String loadCurrentMonitorData(List<String> jobNames);

	List<JobMonitorHistoryVo> loadJobMonitorHistoryData();

	List<JobExecutionHistoryVo> loadJobExecutionHistoryDateData(String jobName);

	List<String> loadAllJobNames();

	List<JobExecutionHistoryVo> loadJobCurrentExecutionData(String[] jobNames);
}
