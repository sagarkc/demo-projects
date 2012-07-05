/* ******************************************************************************
 * 	
 * 	Name	: EmployeeDao.java
 * 	Type	: com.springdemo.EmployeeDao
 * 
 * 	Created	: Apr 5, 2012
 * 	
 * 	Author	: Sabuj Das [ mailto::sabuj.das@gmail.com ]
 * 
 * -----------------------------------------------------------------------------*
 * 																				*
 * Copyright © Sabuj Das 2010 All Rights Reserved. 								*
 * <br/>No part of this document may be reproduced without written 				*
 * consent from the author.														*
 * 																				*
 ****************************************************************************** */

package com.springdemo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author sabuj.das
 * @MailTo sabuj.das@gmail.com
 * 
 */
public class EmployeeDao implements ApplicationContextAware{

	private ApplicationContext context;
	public static int count = 0;
	
	private UserDao userDao;
	
	/**
	 * 
	 */
	public EmployeeDao() {
		count ++;
		System.out.println("EmployeeDao:: Instance Count: " + count);
	}
	
	public UserDao getUserDao() {
		userDao = context.getBean("userDao01", UserDao.class);
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		count --;
		System.out.println("EmployeeDao:: Instance Count: " + count);
		super.finalize();
	}
	
	public void print(){
		System.out.println("EmployeeDao:: print method");
	}

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}
	
	
}
