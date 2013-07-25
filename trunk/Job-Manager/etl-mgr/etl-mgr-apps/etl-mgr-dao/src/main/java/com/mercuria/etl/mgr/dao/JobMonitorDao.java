package com.mercuria.etl.mgr.dao;

import java.util.List;

import com.mercuria.etl.mgr.model.vo.JobMonitorVo;

public interface JobMonitorDao {

	/**
	 * List all the job execution history.
	 * 
	 * @return List of JobMonitorVo
	 */
	List<JobMonitorVo> getJobExecutionHistory();
	
}
