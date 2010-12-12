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
 * 	Name	: PDBRecord.java
 * 	Type	: com.gs.rpad.codec.pdb.model.PDBRecord
 * 
 * 	Created	: Dec 11, 2010
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

package com.gs.rpad.codec.pdb.model;

import java.io.Serializable;

/**
 * 
 *
 */
public class PDBRecord implements Serializable {

	private PDBRecordHeader recordHeader;
	private byte[] data;
	
	/**
	 * 
	 */
	public PDBRecord() {
		recordHeader = new PDBRecordHeader();
	}

	public PDBRecordHeader getRecordHeader() {
		return recordHeader;
	}

	public void setRecordHeader(PDBRecordHeader recordHeader) {
		this.recordHeader = recordHeader;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
	
}
