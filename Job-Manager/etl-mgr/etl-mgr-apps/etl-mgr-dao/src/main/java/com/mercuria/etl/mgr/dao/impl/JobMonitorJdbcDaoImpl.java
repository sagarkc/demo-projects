/**
 * File :: com.mercuria.etl.mgr.dao.impl.JobMonitorJdbcDaoImpl
 * Date :: 26-Jul-2013
 */
package com.mercuria.etl.mgr.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mercuria.etl.mgr.common.exception.ApplicationException;
import com.mercuria.etl.mgr.core.jdbc.ReflectionBasedRowMapper;
import com.mercuria.etl.mgr.dao.JobMonitorJdbcDao;
import com.mercuria.etl.mgr.model.vo.JobMonitorVo;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
@Repository
public class JobMonitorJdbcDaoImpl implements JobMonitorJdbcDao {

	private static Logger logger = Logger.getLogger(JobMonitorJdbcDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private String getAllJobHistory;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	public List<JobMonitorVo> getAllJobHistory() throws ApplicationException{
		if(logger.isDebugEnabled()){
			logger.debug("Execute SQL: " + getAllJobHistory);
		}
		try{
			List<JobMonitorVo> jobMonitorVos 
			= jdbcTemplate.query(getAllJobHistory, 
					new ReflectionBasedRowMapper<JobMonitorVo>(JobMonitorVo.class));
			return jobMonitorVos;
		} catch (Exception e){
			logger.error(e);
			e.printStackTrace();
			throw new ApplicationException(e);
		}
	}
}
