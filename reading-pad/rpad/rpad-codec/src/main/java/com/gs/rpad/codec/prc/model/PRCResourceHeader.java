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

import com.gs.utils.collection.CollectionUtils;
import com.gs.utils.exception.UtilityException;
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
	private Long resourceDataOffset;
	
	private byte[] data;
	
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
	public Long getResourceDataOffset() {
		return resourceDataOffset;
	}

	/**
	 * @param resourceDataOffset the resourceDataOffset to set
	 */
	public void setResourceDataOffset(Long resourceDataOffset) {
		this.resourceDataOffset = resourceDataOffset;
	}
	
	public void populateResourceHeader(byte[] resourceHeaderData) throws UtilityException{
		this.data = resourceHeaderData;
		byte[] data = CollectionUtils.sliceBytes(resourceHeaderData, 0, 4);
		if(null != data){
			setResourceName(StringUtil.convertToString(data, false));
		}
		data = CollectionUtils.sliceBytes(resourceHeaderData, 4, 2);
		if(null != data){
			setResourceId(ConversionUtil.byteArrayToInt(data));
		}
		data = CollectionUtils.sliceBytes(resourceHeaderData, 6, 4);
		if(null != data){
			setResourceDataOffset(ConversionUtil.byteArrayToLong(data));
			if(getResourceDataOffset()  < 0){
				System.out.println(
						"Header \t:: " + printArray(resourceHeaderData)
						+ "\nOffset \t:: " + printArray(data));
			}
		}
	}

	@Override
	public String toString() {
		return "PRCResourceHeader [\n\tresourceId=" + resourceId
				+ ", \n\tresourceDataOffset=" + resourceDataOffset + ", \n\tdata="
				+ Arrays.toString(data) + "\n]";
	}

	private String printArray(byte[] b){
		StringBuffer buffer = new StringBuffer("{ ");
		for (int i = 0; i < b.length; i++) {
			buffer.append(b[i]);
			if(i < b.length-1){
				buffer.append(", ");
			}
		}
		buffer.append(" }");
		return buffer.toString();
	}
	
	
}
