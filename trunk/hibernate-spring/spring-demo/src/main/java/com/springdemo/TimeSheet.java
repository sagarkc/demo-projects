/* ******************************************************************************
 * 	
 * 	Name	: TimeSheet.java
 * 	Type	: com.springdemo.TimeSheet
 * 
 * 	Created	: Jun 11, 2012
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

import java.util.List;

/**
 * @author sabuj.das
 * @MailTo sabuj.das@gmail.com
 * 
 */
public class TimeSheet {

	private String areaCode;
	private Address address;
	private List names;
	
	private TimeSheet(String areaCode) {
		this.address = new Address();
		this.areaCode = areaCode;
	}

	public List getNames() {
		return names;
	}

	public void setNames(List names) {
		this.names = names;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	public void setAreaCode(int areaCode) {
		this.areaCode = ""+areaCode;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void init1(){
		setAreaCode("222");
		
	}
	
	public void init2(){
		setAreaCode("34234");
		
	}

	public String getAreaCode() {
		return areaCode;
	} 
	
}
