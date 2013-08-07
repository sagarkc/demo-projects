package com.xchanging.etl.mgr.dao;

import java.util.List;

import com.xchanging.etl.mgr.common.exception.ApplicationException;
import com.xchanging.etl.mgr.model.entity.JobInstance;

public interface JobInstanceDao {

	List<JobInstance> getAllInstances() throws ApplicationException;
	
}
