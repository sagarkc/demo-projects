/* ******************************************************************************
 * 	
 * 	Name	: EmployeeService.java
 * 	Type	: com.springdemo.EmployeeService
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

/**
 * @author sabuj.das
 * @MailTo sabuj.das@gmail.com
 * 
 */
public class EmployeeService {

	public static int count = 0;
	
	private EmployeeDao employeeDao;
	
	/**
	 * 
	 */
	public EmployeeService() {
		count ++;
		System.out.println("EmployeeService:: Instance Count: " + count);
	}
	
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		count --;
		System.out.println("EmployeeService:: Instance Count: " + count);
		super.finalize();
	}
	
	public void print(){
		System.out.println("EmployeeService:: print method");
	}
}
