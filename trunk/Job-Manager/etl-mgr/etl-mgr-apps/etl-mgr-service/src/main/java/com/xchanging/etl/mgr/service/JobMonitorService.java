/**
 * File :: com.xchanging.etl.mgr.service.JobMonitorService
 * Date :: 26-Jul-2013
 */
package com.xchanging.etl.mgr.service;

import java.util.List;

import com.xchanging.etl.mgr.model.vo.JobMonitorVo;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public interface JobMonitorService {

	public List<JobMonitorVo> getJobExecutionHistory();
	
}
