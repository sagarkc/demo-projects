package com.mercuria.etl.mgr.core.dao.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;


public class EtlCommonDaoImpl<T, I extends Serializable> extends GenericDaoImpl<T, I> {

	@PersistenceContext(unitName = "etlmgr.persistence.uinit", type = PersistenceContextType.TRANSACTION)
	public void setEntityManager(EntityManager enmgr) {
		super.initializeEntityManager(enmgr);
	}
}