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
 * 	Name	: PDBRecordHeader.java
 * 	Type	: com.gs.rpad.codec.pdb.model.PDBRecordHeader
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
public class PDBRecordHeader implements Serializable, Comparable<PDBRecordHeader> {

	
	private Integer offset;
	private byte attributes;
	private Integer uniqueID;
	
	
	/**
	 * 
	 */
	public PDBRecordHeader() {
		// TODO Auto-generated constructor stub
	}


	public Integer getOffset() {
		return offset;
	}


	public void setOffset(Integer offset) {
		this.offset = offset;
	}


	public byte getAttributes() {
		return attributes;
	}


	public void setAttributes(byte attributes) {
		this.attributes = attributes;
	}


	public Integer getUniqueID() {
		return uniqueID;
	}


	public void setUniqueID(Integer uniqueID) {
		this.uniqueID = uniqueID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((uniqueID == null) ? 0 : uniqueID.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PDBRecordHeader)) {
			return false;
		}
		PDBRecordHeader other = (PDBRecordHeader) obj;
		if (uniqueID == null) {
			if (other.uniqueID != null) {
				return false;
			}
		} else if (!uniqueID.equals(other.uniqueID)) {
			return false;
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(PDBRecordHeader o) {
		return uniqueID.compareTo(o.uniqueID);
	}
	
}
