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
 * 	Name	: PRCHeader.java
 * 	Type	: com.gs.rpad.codec.prc.model.PRCHeader
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

import com.gs.rpad.codec.pdb.model.PDBHeader;

/**
 * @author 50120C1509
 *
 */
public class PRCHeader extends PDBHeader {

	/**
	 * 
	 */
	private static final long serialVersionUID = 793837208703290375L;

	/**
	 * 
	 */
	public PRCHeader() {
		super();
	}
	
}
