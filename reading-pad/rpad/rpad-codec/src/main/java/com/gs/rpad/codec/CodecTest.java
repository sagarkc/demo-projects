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
 * 	Name	: CodecTest.java
 * 	Type	: com.gs.rpad.codec.CodecTest
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

package com.gs.rpad.codec;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import com.gs.rpad.codec.pdb.model.PDBHeader;
import com.gs.utils.io.IOUtil;

/**
 * 
 *
 */
public class CodecTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String fileName = "D:/temp/The Wheel of Time/00 New Spring.prc";
		BufferedInputStream inputStream = null;
		PDBHeader header = new PDBHeader();
		try {
			inputStream = new BufferedInputStream(new FileInputStream(new File(fileName)));
			byte[] headerByte = new byte[PDBHeader.HEADER_LENGTH];
			int length = inputStream.read(headerByte, 0, PDBHeader.HEADER_LENGTH);
			
			header.readPDBHeader(headerByte);
			if(header.getNumRecords() != null){
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			IOUtil.close(inputStream);
		}
		
		byte[] b = new byte[PDBHeader.HEADER_LENGTH];
		try {
			inputStream = new BufferedInputStream(new FileInputStream(new File(fileName)));
			inputStream.read(b, 0, PDBHeader.HEADER_LENGTH);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			IOUtil.close(inputStream);
		}
		System.out.println(b);
	}
	


}
