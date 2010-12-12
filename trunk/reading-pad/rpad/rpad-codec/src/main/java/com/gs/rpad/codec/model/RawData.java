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
 * 	Name	: RawData.java
 * 	Type	: com.gs.rpad.codec.model.RawData
 * 
 * 	Created	: Dec 10, 2010
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

package com.gs.rpad.codec.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * 
 *
 */
public class RawData<T> implements Serializable {

	private int offset;
	private String name;
	private int size;
	private T value;
	private byte[] data;
	
	/**
	 * 
	 */
	public RawData() {
		// TODO Auto-generated constructor stub
	}
	
	

	public RawData(int offset, String name, int size) {
		this.offset = offset;
		this.name = name;
		this.size = size;
	}



	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}



	public byte[] getData() {
		return data;
	}



	public void setData(byte[] data) {
		this.data = data;
	}



	
}
