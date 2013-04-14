package com.gs.demo.hr.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

public class HRCommonDaoImpl<T, I extends Serializable> extends
		GenericDaoImpl<T, I> {

	@PersistenceContext(unitName = "hr.persistence.uinit", type = PersistenceContextType.TRANSACTION)
	public void setEntityManager(EntityManager enmgr) {
		super.initializeEntityManager(enmgr);
	}
}