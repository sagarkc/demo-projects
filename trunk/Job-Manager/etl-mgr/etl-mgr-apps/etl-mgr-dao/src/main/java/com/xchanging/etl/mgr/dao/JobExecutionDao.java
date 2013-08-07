package com.xchanging.etl.mgr.dao;

import java.util.List;

import com.xchanging.etl.mgr.model.entity.JobExecution;

public interface JobExecutionDao {

	List<JobExecution> getAllJobExecutions();
}
