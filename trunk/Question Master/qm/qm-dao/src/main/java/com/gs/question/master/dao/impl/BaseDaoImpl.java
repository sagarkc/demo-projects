package com.gs.question.master.dao.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;


public class BaseDaoImpl<T, I extends Serializable> extends GenericDaoImpl<T, I> {

	@PersistenceContext(unitName = "qm.persistence.uinit", type = PersistenceContextType.TRANSACTION)
	public void setEntityManager(EntityManager enmgr) {
		super.initializeEntityManager(enmgr);
	}
}