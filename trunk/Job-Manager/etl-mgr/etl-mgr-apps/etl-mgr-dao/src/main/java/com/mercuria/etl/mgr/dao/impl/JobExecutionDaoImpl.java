package com.mercuria.etl.mgr.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mercuria.etl.mgr.core.dao.impl.EtlCommonDaoImpl;
import com.mercuria.etl.mgr.dao.JobExecutionDao;
import com.mercuria.etl.mgr.model.entity.JobExecution;

@Repository
public class JobExecutionDaoImpl extends EtlCommonDaoImpl<JobExecution, BigInteger> 
	implements JobExecutionDao {

	@Override
	public List<JobExecution> getAllJobExecutions() {
		// TODO Auto-generated method stub
		return null;
	}

}
