/* ******************************************************************************
 * 	
 * 	Name	: Environment.java
 * 	Type	: com.gs.jprompt.common.Environment
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

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.gs.utils.collection.CollectionUtils;
import com.gs.utils.text.StringUtil;

/**
 * @author sabuj.das
 * @MailTo sabuj.das@gmail.com
 * 
 */
public class Environment {

	
	private Properties properties = new Properties();
	private Set<String> environmentVariables = new LinkedHashSet<String>();
	
	/**
	 * 
	 */
	public Environment() {
		
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Set<String> getEnvironmentVariables() {
		return environmentVariables;
	}

	public void setEnvironmentVariables(Set<String> environmentVariables) {
		this.environmentVariables = environmentVariables;
	}
	
	public synchronized void set(String command){
		if(!StringUtil.hasValidContent(command)){
			return;
		}
		
		int spaceIndex = command.indexOf(' ');
		command = command.substring(spaceIndex+1);
		int index = command.indexOf('=');
		if(index > 0){
			String key = command.substring(0, index);
			String value = (command.length()-1 == index
					? ""
					: command.substring(index+1));
			set(key, value);
		}
	}
	
	
	private synchronized  boolean validateValue(String value){
		if(!StringUtil.hasValidContent(value)){
			return true;
		}
		if(value.indexOf('=') < 0)
			return true;
		return false;
	}
	
	public synchronized void set(String variable, String value){
		if(!StringUtil.hasValidContent(variable)){
			throw new IllegalArgumentException("Invalid variable name [NULL/Empty]");
		}
		if(!validateValue(value)){
			throw new IllegalArgumentException("Invalid value given in environment variavle : " + variable);
		}
		getProperties().put(variable, value);
		environmentVariables.add(variable);
	}
	
	public String get(String variable){
		return properties.getProperty(variable);
	}
	
	public Map<String, String> getAll(){
		Set<String> vars = getVariables();
		Map<String, String> allValues = new LinkedHashMap<String, String>();
		for (String var : vars) {
			allValues.put(var, get(var));
		}
		return allValues;
	}
	
	public Set<String> getVariables(){
		Set<Object> vars = properties.keySet();
		Set<String> variables = new LinkedHashSet<String>();
		if(CollectionUtils.hasElements(vars)){
			for (Object object : vars) {
				if(null != object && object instanceof String){
					variables.add(object.toString());
				}
			}
		}
		return variables;
	}
}
