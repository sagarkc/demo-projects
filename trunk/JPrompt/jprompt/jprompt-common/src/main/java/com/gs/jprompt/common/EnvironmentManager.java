/* ******************************************************************************
 * 	
 * 	Name	: EnvironmentManager.java
 * 	Type	: com.gs.jprompt.common.EnvironmentManager
 * 
 * 	Created	: Feb 17, 2012
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

package com.gs.jprompt.common;

import java.util.HashSet;
import java.util.Set;

/**
 * @author sabuj.das
 * @MailTo sabuj.das@gmail.com
 * 
 */
public final class EnvironmentManager {

	private final static Environment systemEnvironment;
	private final static Set<String> systemVariables;
	static{
		systemEnvironment = new Environment();
		systemEnvironment.getProperties().putAll(System.getenv());
		systemVariables = new HashSet<String>(System.getenv().keySet());
	}
	
	private Environment userEnvironment;
	
	/**
	 * 
	 */
	public EnvironmentManager() {
		userEnvironment = new Environment();
	}

	public Environment getUserEnvironment() {
		return userEnvironment;
	}

	public static Environment getSystemenvironment() {
		return systemEnvironment;
	}

	public static Set<String> getSystemvariables() {
		return systemVariables;
	}
	
	
	
}
