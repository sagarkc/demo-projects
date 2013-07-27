/**
 * File :: com.mercuria.etl.mgr.service.JobMonitorService
 * Date :: 26-Jul-2013
 */
package com.mercuria.etl.mgr.service;

import java.util.List;

import com.mercuria.etl.mgr.model.vo.JobMonitorVo;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public interface JobMonitorService {

	public List<JobMonitorVo> getJobExecutionHistory();
	
}
