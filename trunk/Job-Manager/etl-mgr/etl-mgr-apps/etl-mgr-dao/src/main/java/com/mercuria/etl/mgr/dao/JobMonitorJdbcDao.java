/**
 * File :: com.mercuria.etl.mgr.dao.JobMonitorJdbcDao
 * Date :: 26-Jul-2013
 */
package com.mercuria.etl.mgr.dao;

import java.util.List;

import com.mercuria.etl.mgr.common.exception.ApplicationException;
import com.mercuria.etl.mgr.model.vo.JobMonitorVo;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public interface JobMonitorJdbcDao {

	public List<JobMonitorVo> getAllJobHistory() throws ApplicationException;
	
}
