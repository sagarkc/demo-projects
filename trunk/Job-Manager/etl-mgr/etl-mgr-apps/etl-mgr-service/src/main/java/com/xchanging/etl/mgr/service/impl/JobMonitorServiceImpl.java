/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.service.impl.JobMonitorServiceImpl
 * Date:	Aug 22, 2013  3:31:11 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xchanging.etl.mgr.common.exception.ApplicationException;
import com.xchanging.etl.mgr.dao.JobMonitorJdbcDao;
import com.xchanging.etl.mgr.model.criteria.RTJobFilterCriteria;
import com.xchanging.etl.mgr.model.vo.JobExecutionHistoryVo;
import com.xchanging.etl.mgr.service.JobMonitorService;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
@Service
public class JobMonitorServiceImpl implements JobMonitorService {
	private static Logger logger = Logger.getLogger(JobMonitorServiceImpl.class);
	
	@Autowired private JobMonitorJdbcDao jobMonitorJdbcDao;
	
	@Override
	public List<JobExecutionHistoryVo> loadRealtimeJobMonitorData(
			RTJobFilterCriteria filterCriteria) {
		List<JobExecutionHistoryVo> historyVos = new ArrayList<>();
		try{
			if(null == filterCriteria || !filterCriteria.hasCriteria()){
				historyVos = jobMonitorJdbcDao.loadRealtimeJobMonitorData();
			} else {
				historyVos = jobMonitorJdbcDao.loadRealtimeJobMonitorData(filterCriteria);
			}
			
			if(null == historyVos){
				logger.info("No data found");
				return new ArrayList<>();
			}
		} catch(ApplicationException ex){
			logger.error(ex);
		}
		return historyVos;
	}

}
