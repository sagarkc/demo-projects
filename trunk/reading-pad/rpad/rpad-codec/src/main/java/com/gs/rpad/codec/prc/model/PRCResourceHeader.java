/** *****************************************************************************
 * 		Reading Pad																*
 *  Is a free software for reading different types of e-Books.					*
 * 	This software mainly supports the following types of e-Books:				*
 * 		1. PDB / PRC - container format for code databases in Palm OS, 			*
 * 			Garnet OS and Access Linux Platform. 								*
 * 		2. PDF - Portable Document Format										*
 * 		3. TXT / TEXT - Simple text file										*
 * 		4. CBR / CBZ / RAR - Comic book files									*
 * 																				*
 * 																				*
 * ---  File Info  -------------------------------------------------------------*
 * 
 * 	Name	: PRCResourceHeader.java
 * 	Type	: com.gs.rpad.codec.prc.model.PRCResourceHeader
 * 
 * 	Created	: Dec 15, 2010
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

package com.gs.rpad.codec.prc.model;

import java.io.Serializable;
import java.util.Arrays;

import com.gs.utils.text.ConversionUtil;
import com.gs.utils.text.StringUtil;

/**
 * @author Sabuj Das
 *
 */
public class PRCResourceHeader implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6624200285338798721L;

	public static final int LENGTH = 10;
	
	private String resourceName;
	private Integer resourceId;
	private Integer resourceDataOffset;
	
	/**
	 * 
	 */
	public PRCResourceHeader() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the resourceName
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * @param resourceName the resourceName to set
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	/**
	 * @return the resourceId
	 */
	public Integer getResourceId() {
		return resourceId;
	}

	/**
	 * @param resourceId the resourceId to set
	 */
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * @return the resourceDataOffset
	 */
	public Integer getResourceDataOffset() {
		return resourceDataOffset;
	}

	/**
	 * @param resourceDataOffset the resourceDataOffset to set
	 */
	public void setResourceDataOffset(Integer resourceDataOffset) {
		this.resourceDataOffset = resourceDataOffset;
	}
	
	public void populateResourceHeader(byte[] resourceHeaderData){
		byte[] data = Arrays.copyOfRange(resourceHeaderData, 0, 4);
		if(null != data){
			setResourceName(StringUtil.convertToString(data));
		}
		data = Arrays.copyOfRange(resourceHeaderData, 4, 6);
		if(null != data){
			setResourceId(ConversionUtil.byteArrayToInt(data));
		}
		data = Arrays.copyOfRange(resourceHeaderData, 6, 10);
		if(null != data){
			setResourceDataOffset(ConversionUtil.byteArrayToInt(data));
			if(getResourceDataOffset() < 0){
				System.out.println(Integer.toBinaryString(data[0]) 
						+ " | " + Integer.toBinaryString(data[1]) + " | "
						+ " | " + Integer.toBinaryString(data[2]) + " | "
						+ " | " + Integer.toBinaryString(data[3]));
			}
		}
	}
}
