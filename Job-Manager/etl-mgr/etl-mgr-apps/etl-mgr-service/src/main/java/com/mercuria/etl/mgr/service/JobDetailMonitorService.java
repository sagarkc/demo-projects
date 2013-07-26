package com.mercuria.etl.mgr.service;

import java.util.List;

import com.mercuria.etl.mgr.model.vo.JobMonitorVo;

public interface JobDetailMonitorService {

	
	/**
	 * List all the job execution history.
	 * 
	 * @return List of JobMonitorVo
	 */
	List<JobMonitorVo> getJobExecutionHistory();
	
}
