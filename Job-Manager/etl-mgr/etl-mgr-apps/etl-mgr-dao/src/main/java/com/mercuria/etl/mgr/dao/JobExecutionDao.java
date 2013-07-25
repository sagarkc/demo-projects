package com.mercuria.etl.mgr.dao;

import java.util.List;

import com.mercuria.etl.mgr.model.entity.JobExecution;

public interface JobExecutionDao {

	List<JobExecution> getAllJobExecutions();
}
