/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.dao.mapper.JobMonitorHistoryRowMapper
 * Date:	Aug 1, 2013  4:33:17 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.xchanging.etl.mgr.model.vo.JobMonitorHistoryVo;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class JobMonitorHistoryRowMapper implements
		RowMapper<JobMonitorHistoryVo> {

	final private Map<String, JobMonitorHistoryVo> jobMonitorHistoryVoMap = new HashMap<>();
	
	@Override
	public JobMonitorHistoryVo mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		String jobName = rs.getString("JOB_NAME");
		String exitCode = rs.getString("EXIT_CODE");
		
		JobMonitorHistoryVo monitorHistoryVo = new JobMonitorHistoryVo();
		if(jobMonitorHistoryVoMap.containsKey(jobName)){
			monitorHistoryVo = jobMonitorHistoryVoMap.get(jobName);
		} 
		
		
		
		return monitorHistoryVo;
	}

	
	
}
