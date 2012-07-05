/* ******************************************************************************
 * 	
 * 	Name	: Address.java
 * 	Type	: com.springdemo.Address
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

/**
 * @author sabuj.das
 * @MailTo sabuj.das@gmail.com
 * 
 */
public class Address {

	private String street;
	

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	public static class HomeAddress{
		private String city;

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}
		
		
	}
	
}
