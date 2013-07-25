package com.mercuria.etl.mgr.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mercuria.etl.mgr.core.jdbc.ReflectionBasedRowMapper;
import com.mercuria.etl.mgr.dao.JobMonitorDao;
import com.mercuria.etl.mgr.model.vo.JobMonitorVo;

@Repository
public class JobMonitorDaoImpl implements JobMonitorDao {

	private static Logger logger = Logger.getLogger(JobMonitorDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<JobMonitorVo> getJobExecutionHistory() {
		String sql = "SELECT * FROM vwBatchJobs";
		if(logger.isDebugEnabled()){
			logger.debug("Execute SQL: " + sql);
		}
		List<JobMonitorVo> jobMonitorVos = jdbcTemplate.query(sql, new ReflectionBasedRowMapper<JobMonitorVo>(JobMonitorVo.class));
		if(logger.isDebugEnabled()){
			logger.debug("Found records: " + (null != jobMonitorVos ? jobMonitorVos.size() : 0));
		}
		
		return jobMonitorVos;
	}

}
