/**
 * File :: com.xchanging.etl.mgr.dao.impl.JobMonitorJdbcDaoImpl
 * Date :: 26-Jul-2013
 */
package com.xchanging.etl.mgr.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.xchanging.etl.mgr.common.exception.ApplicationException;
import com.xchanging.etl.mgr.core.jdbc.ReflectionBasedRowMapper;
import com.xchanging.etl.mgr.dao.JobMonitorJdbcDao;
import com.xchanging.etl.mgr.model.vo.JobExecutionHistoryVo;
import com.xchanging.etl.mgr.model.vo.JobMonitorHistoryVo;
import com.xchanging.etl.mgr.model.vo.JobMonitorVo;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
@Repository
public class JobMonitorJdbcDaoImpl implements JobMonitorJdbcDao {

	private static Logger logger = Logger.getLogger(JobMonitorJdbcDaoImpl.class);
	
	@Autowired private JdbcTemplate jdbcTemplate;
	
	@Autowired private String getAllJobHistory;
	@Autowired private String SQL_getDistinctJobNames;
	@Autowired private String SQL_lastJobExecutionByJobNames;
	@Autowired private String SQL_jobExecutionHistoryByJobNames;
	@Autowired private String SQL_lastJobExecutionByJobNamesFiltered;

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
	
	public List<String> getDistinctJobNames() throws ApplicationException{
		try{
			List<String> jobMonitorVos 
				= jdbcTemplate.query(SQL_getDistinctJobNames, 
					new RowMapper<String>() {
						@Override
						public String mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							return rs.getString("JOB_NAME");
						}
				
					});
			return jobMonitorVos;
		} catch (Exception e){
			logger.error(e);
			e.printStackTrace();
			throw new ApplicationException(e);
		}
	}
	
	public List<JobMonitorHistoryVo> getLastJobExecutionByJobNames() throws ApplicationException{
		try{
			List<JobMonitorHistoryVo> jobMonitorVos 
			= jdbcTemplate.query(SQL_lastJobExecutionByJobNames, 
					new ReflectionBasedRowMapper<JobMonitorHistoryVo>(JobMonitorHistoryVo.class));
			return jobMonitorVos;
		} catch (Exception e){
			logger.error(e);
			e.printStackTrace();
			throw new ApplicationException(e);
		}
	}


	@Override
	public List<JobExecutionHistoryVo> getJobExecutionHistoryByName(String jobName) throws ApplicationException {
		try{
			List<JobExecutionHistoryVo> jobExecHistory 
			= jdbcTemplate.query(SQL_jobExecutionHistoryByJobNames, 
					new Object[] { jobName },
					new ReflectionBasedRowMapper<JobExecutionHistoryVo>(JobExecutionHistoryVo.class));
			return jobExecHistory;
		} catch (Exception e){
			logger.error(e);
			e.printStackTrace();
			throw new ApplicationException(e);
		}
	}
	
	
}
