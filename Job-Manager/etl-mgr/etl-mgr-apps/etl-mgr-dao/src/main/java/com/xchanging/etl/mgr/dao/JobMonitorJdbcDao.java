/**
 * File :: com.xchanging.etl.mgr.dao.JobMonitorJdbcDao
 * Date :: 26-Jul-2013
 */
package com.xchanging.etl.mgr.dao;

import java.util.List;

import com.xchanging.etl.mgr.common.exception.ApplicationException;
import com.xchanging.etl.mgr.model.vo.JobExecutionHistoryVo;
import com.xchanging.etl.mgr.model.vo.JobMonitorHistoryVo;
import com.xchanging.etl.mgr.model.vo.JobMonitorVo;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public interface JobMonitorJdbcDao {

	public List<JobMonitorVo> getAllJobHistory() throws ApplicationException;
	
	public List<String> getDistinctJobNames() throws ApplicationException;
	public List<JobMonitorHistoryVo> getLastJobExecutionByJobNames() throws ApplicationException;

	/**
	 * 
	 * 
	 * @return
	 * @throws ApplicationException 
	 */
	public List<JobExecutionHistoryVo> getJobExecutionHistoryByName(String jobName) throws ApplicationException;

	public List<JobExecutionHistoryVo> loadJobCurrentExecutionData(
			String[] jobNames) throws ApplicationException;
	
}
