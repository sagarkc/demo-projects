package com.mercuria.etl.mgr.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mercuria.etl.mgr.common.exception.ApplicationException;
import com.mercuria.etl.mgr.core.dao.impl.EtlCommonDaoImpl;
import com.mercuria.etl.mgr.dao.JobInstanceDao;
import com.mercuria.etl.mgr.model.entity.JobInstance;

@Repository
public class JobInstanceDaoImpl extends EtlCommonDaoImpl<JobInstance, BigInteger> implements JobInstanceDao{

	@Autowired
	private String JPQL_JOB_MONITOR;
	
	@Override
	public List<JobInstance> getAllInstances() throws ApplicationException {
		try{
			List<JobInstance> instances = new ArrayList<>();
			
			Collection collection = getEntityManager().createQuery(JPQL_JOB_MONITOR).getResultList();
			if(null != collection && collection != null){
				for (Object object : collection) {
					if(object instanceof JobInstance){
						instances.add((JobInstance)object);
					}
				}
			}
			return instances;
		} catch(Exception exception){
			throw new ApplicationException(exception);
		}
	}
	
}
