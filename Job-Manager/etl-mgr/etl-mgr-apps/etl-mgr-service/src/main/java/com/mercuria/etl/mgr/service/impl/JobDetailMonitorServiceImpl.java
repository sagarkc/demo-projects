package com.mercuria.etl.mgr.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mercuria.etl.mgr.common.exception.ApplicationException;
import com.mercuria.etl.mgr.dao.JobInstanceDao;
import com.mercuria.etl.mgr.dao.JobMonitorJdbcDao;
import com.mercuria.etl.mgr.model.entity.JobExecution;
import com.mercuria.etl.mgr.model.entity.JobInstance;
import com.mercuria.etl.mgr.model.vo.JobMonitorVo;
import com.mercuria.etl.mgr.service.JobDetailMonitorService;

@Service
public class JobDetailMonitorServiceImpl implements JobDetailMonitorService {

	private static Logger logger = Logger.getLogger(JobDetailMonitorServiceImpl.class);
	
	@Autowired
	private JobInstanceDao jobInstanceDao;
	@Autowired 
	private JobMonitorJdbcDao jobMonitorJdbcDao;
	
	public void setJobInstanceDao(JobInstanceDao jobInstanceDao) {
		this.jobInstanceDao = jobInstanceDao;
	}

	public void setJobMonitorJdbcDao(JobMonitorJdbcDao jobMonitorJdbcDao) {
		this.jobMonitorJdbcDao = jobMonitorJdbcDao;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<JobMonitorVo> getJobExecutionHistory() {
		List<JobInstance> jobInstances = null;
		try {
			jobInstances = jobInstanceDao.getAllInstances();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		List<JobMonitorVo> jobMonitorVos = new ArrayList<>();
		if(null != jobInstances){
			if(logger.isDebugEnabled()){
				logger.debug("Total instance count: " + jobInstances.size());
			}
			
			for (JobInstance jobInstance : jobInstances) {
				JobMonitorVo jobMonitorVo = new JobMonitorVo();
				
				jobMonitorVo.setJobName(jobInstance.getJobName());
				
				Set<JobExecution> executions = jobInstance.getJobExecutions();
				if(null != executions && executions.size() > 0){
					if(logger.isDebugEnabled()){
						logger.debug("Total execution count: " + executions.size() 
								+ " for job instance: " + jobInstance.getInstanceId());
					}
					JobExecution execution = executions.iterator().next();
					jobMonitorVo.setStartTime(execution.getStartTime());
					jobMonitorVo.setEndTime(execution.getEndTime());
					
					jobMonitorVo.setExitCode(execution.getExitCode());
					jobMonitorVo.setExitMessage(execution.getExitMessage());
				}
				
				jobMonitorVos.add(jobMonitorVo);
			}
		}
		if(logger.isDebugEnabled()){
			logger.debug("Total monitorVO count: " + jobMonitorVos.size());
		}
		return jobMonitorVos;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<JobMonitorVo> getAllJobHistory() {
		try{
			return jobMonitorJdbcDao.getAllJobHistory();
		} catch (ApplicationException e){
			logger.error(e);
		}
		return new ArrayList<>();
	}

	
}
