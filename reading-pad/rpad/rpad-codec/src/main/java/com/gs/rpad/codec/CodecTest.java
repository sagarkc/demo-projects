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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.gs.rpad.codec.pdb.model.PDBFile;
import com.gs.rpad.codec.pdb.model.PDBHeader;
import com.gs.rpad.codec.pdb.model.PDBRecord;
import com.gs.rpad.codec.pdb.model.PDBRecordHeader;
import com.gs.rpad.codec.prc.io.PrcFile;
import com.gs.rpad.codec.prc.model.PRCHeader;
import com.gs.rpad.codec.prc.model.PRCResourceHeader;
import com.gs.utils.common.FieldSpecificComparator;
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
		
		PrcFile prcFile = null;
		
		try {
			prcFile = new PrcFile(fileName);
			inputStream = new BufferedInputStream(new FileInputStream(new File(fileName)));
			byte[] headerByte = new byte[PDBHeader.LENGTH];
			byte[] recordHeaderByte = new byte[PRCResourceHeader.LENGTH];
			List<PRCResourceHeader> prcHeaders = new ArrayList<PRCResourceHeader>();
			int length = inputStream.read(headerByte, 0, PRCHeader.LENGTH);
			PRCHeader header = new PRCHeader();
			header.readHeader(headerByte);
			prcFile.setHeader(header);
			if(header.getNumRecords() != null){
				for(short i=0; i < header.getNumRecords().getValue(); i++){
					PRCResourceHeader recordHeader = new PRCResourceHeader();
					length = inputStream.read(recordHeaderByte, 0, recordHeaderByte.length);
					recordHeader.populateResourceHeader(recordHeaderByte);
					prcHeaders.add(recordHeader);
				}
				
			}
			FieldSpecificComparator<PRCResourceHeader, Integer> c1
				= new FieldSpecificComparator<PRCResourceHeader, Integer>("resourceDataOffset");
			Collections.sort(prcHeaders, c1);
			prcFile.setResourceHeaders(prcHeaders);
			System.out.println("Headers : " + prcHeaders.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			IOUtil.close(inputStream);
		}
		
		if(null != prcFile){
			FileChannel channel = null;
			BufferedWriter writer = null;
			try {
				channel = (new RandomAccessFile(prcFile.getOriginalFile(), "r"))
						.getChannel();
				ByteBuffer dst = null;
				writer = new BufferedWriter(new FileWriter("d:\\data.txt"));
				for(int i=0; i < prcFile.getResourceHeaders().size(); i++){
					PRCResourceHeader rh_i = prcFile.getResourceHeaders().get(i);
					PRCResourceHeader rh_next = prcFile.getResourceHeaders().get(i+1);
					if(rh_i.getResourceDataOffset().longValue() - rh_i.getResourceDataOffset().longValue() < 0)
						continue;
					if(rh_i.getResourceDataOffset().longValue() == 0 
							&& rh_next.getResourceDataOffset().longValue() == 0)
						continue;
					
						
					
					dst = ByteBuffer.allocate((int)(rh_next.getResourceDataOffset().longValue() - rh_i.getResourceDataOffset().longValue()));
					channel.position(rh_i.getResourceDataOffset());
					channel.read(dst, rh_next.getResourceDataOffset() -1 );
					writer.write(rh_i.toString() + "\n");
					writer.write(printArray(dst.array()) + "\n");
					dst = null;
				}
				channel.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				try {
					channel.close();
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			System.out.println("Done.............................");
		}
		
		/*try {
			inputStream = new BufferedInputStream(new FileInputStream(new File(fileName)));
			byte[] dataBytes = new byte[1];
			int count = 0, i =0;
			
			while((count = inputStream.read(dataBytes, 0, dataBytes.length)) > 0){
				System.out.println( (i++ +1) + " : " + ConversionUtil.byteArrayToInt(dataBytes) +  " > " + 
						ConversionUtil.getHexString(dataBytes));
				if(i == 77)
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			IOUtil.close(inputStream);
		}*/
	}
	
	private static String printArray(byte[] b){
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
	
	public static String toBinaryString(byte[] dataBytes){
		StringBuffer buffer = new StringBuffer();
		BigInteger bigInteger = new BigInteger(dataBytes);
		String bin = bigInteger.toString(2);
		
		if( (8 - bin.length()) > 0){
			for(int i=0; i< 8 - bin.length(); i++){
				buffer.append("0");
			}
		}
		buffer.append(bin);
		return buffer.toString();
	}
	
	public static String getBinaryString(byte[] bytes){
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			buffer.append(Integer.toBinaryString(bytes[i]));
			if(i < bytes.length - 1){
				buffer.append(" | ");
			}
		}
		
		return buffer.toString();
	}
	
	
	

}
