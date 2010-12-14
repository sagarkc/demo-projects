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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gs.rpad.codec.pdb.model.PDBFile;
import com.gs.rpad.codec.pdb.model.PDBHeader;
import com.gs.rpad.codec.pdb.model.PDBRecord;
import com.gs.rpad.codec.pdb.model.PDBRecordHeader;
import com.gs.utils.io.IOUtil;
import com.gs.utils.text.ConversionUtil;

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
		PDBFile pdbFile = new PDBFile();
		byte[] full = null;
		List<byte[]> a = new ArrayList<byte[]>();
		try {
			
			inputStream = new BufferedInputStream(new FileInputStream(new File(fileName)));
			/*byte[] buffer = new byte[1024];
			int count=0;
			while((count = inputStream.read(buffer, 0, 1024)) > 0){
				full = new byte[count];
				full = Arrays.copyOf(buffer, count);
				a.add(full);
			}*/
			byte[] headerByte = new byte[PDBHeader.HEADER_LENGTH];
			byte[] recordHeaderByte = new byte[PDBRecordHeader.RECORD_HEDER_LENGTH];
			
			int length = inputStream.read(headerByte, 0, PDBHeader.HEADER_LENGTH);
			header.readPDBHeader(headerByte);
			pdbFile.setHeader(header);
			if(header.getNumRecords() != null){
				length = inputStream.read(recordHeaderByte, 0, recordHeaderByte.length);
				PDBRecordHeader recordHeader = new PDBRecordHeader();
				recordHeader.setOffset(ConversionUtil.byteArrayToInt(Arrays.copyOfRange(recordHeaderByte, 0, 4)));
				recordHeader.setAttributes(recordHeaderByte[4]);
				recordHeader.setOffset(ConversionUtil.byteArrayToInt(Arrays.copyOfRange(recordHeaderByte, 6, 8)));
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
