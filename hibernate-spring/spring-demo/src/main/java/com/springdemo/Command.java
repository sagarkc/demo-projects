/* ******************************************************************************
 * 	
 * 	Name	: Command.java
 * 	Type	: com.springdemo.Command
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
public class Command {

	public static int count = 0;
	
	
	/**
	 * 
	 */
	public Command() {
		count ++;
		System.out.println("Command:: Instance Count: " + count);
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		count --;
		System.out.println("Command:: Instance Count: " + count);
		super.finalize();
	}
	
	public void print(){
		System.out.println("Command:: print method");
	}
	
}
