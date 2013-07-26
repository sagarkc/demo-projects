package com.mercuria.etl.mgr.dao;

import java.util.List;

import com.mercuria.etl.mgr.common.exception.ApplicationException;
import com.mercuria.etl.mgr.model.entity.JobInstance;

public interface JobInstanceDao {

	List<JobInstance> getAllInstances() throws ApplicationException;
	
}
