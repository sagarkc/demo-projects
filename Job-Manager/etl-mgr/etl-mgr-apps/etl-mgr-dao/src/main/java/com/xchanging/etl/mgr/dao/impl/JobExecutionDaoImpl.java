package com.xchanging.etl.mgr.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xchanging.etl.mgr.core.dao.impl.EtlCommonDaoImpl;
import com.xchanging.etl.mgr.dao.JobExecutionDao;
import com.xchanging.etl.mgr.model.entity.JobExecution;

@Repository
public class JobExecutionDaoImpl extends EtlCommonDaoImpl<JobExecution, BigInteger> 
	implements JobExecutionDao {

	@Override
	public List<JobExecution> getAllJobExecutions() {
		// TODO Auto-generated method stub
		return null;
	}

}
