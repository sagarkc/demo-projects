/* ******************************************************************************
 * 	
 * 	Name	: CommandManager.java
 * 	Type	: com.springdemo.CommandManager
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
public abstract class CommandManager {

	public void process() {
		// grab a new instance of the appropriate Command interface
		Command command = createCommand();
		// set the state on the (hopefully brand new) Command instance
		command.print();
	}

	// okay... but where is the implementation of this method?
	protected abstract Command createCommand();
	
	protected abstract UserDao createUserDao();

}
