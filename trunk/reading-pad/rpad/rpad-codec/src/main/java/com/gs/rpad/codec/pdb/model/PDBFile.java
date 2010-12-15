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
 * 	Name	: PDBFile.java
 * 	Type	: com.gs.rpad.codec.pdb.model.PDBFile
 * 
 * 	Created	: Dec 13, 2010
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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sabuj Das
 *
 */
public class PDBFile {

	private PDBHeader header;
	private List<PDBRecord> records;
	
	/**
	 * 
	 */
	public PDBFile() {
		header = new PDBHeader();
		records = new ArrayList<PDBRecord>();
	}

	/**
	 * @return the header
	 */
	public PDBHeader getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(PDBHeader header) {
		this.header = header;
	}

	/**
	 * @return the records
	 */
	public List<PDBRecord> getRecords() {
		return records;
	}

	/**
	 * @param records the records to set
	 */
	public void setRecords(List<PDBRecord> records) {
		this.records = records;
	}
	
	
}
