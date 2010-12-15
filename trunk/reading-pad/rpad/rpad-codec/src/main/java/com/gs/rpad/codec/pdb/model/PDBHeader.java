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
 * 	Name	: PDBHeader.java
 * 	Type	: com.gs.rpad.codec.pdb.model.PDBHeader
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

package com.gs.rpad.codec.pdb.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import com.gs.rpad.codec.model.RawData;
import com.gs.utils.collection.CollectionUtils;
import com.gs.utils.io.FileRWUtil;
import com.gs.utils.io.IOUtil;
import com.gs.utils.text.ConversionUtil;
import com.gs.utils.text.StringUtil;

/**
 * <span class="mw-headline" id="PDB_Header">PDB Header</span></h3>
 * 
 * <p>
 * The PDB header is located at the beginning of the file and contains
 * meta-information on the file.
 * </p>
 * <table border="1">
 * <tbody>
 * <tr>
 * <th>Offset</th>
 * <th>Name</th>
 * <th>Type</th>
 * <th>Size</th>
 * </tr>
 * 
 * <tr>
 * <td>0x00</td>
 * <td>name</td>
 * <td>char</td>
 * <td>32 Bytes</td>
 * </tr>
 * <tr>
 * <td>0x20</td>
 * <td>file attributes</td>
 * <td>integer</td>
 * 
 * <td>2 Bytes</td>
 * </tr>
 * <tr>
 * <td>0x22</td>
 * <td>version</td>
 * <td>integer</td>
 * <td>2 Bytes</td>
 * </tr>
 * <tr>
 * <td>0x24</td>
 * <td>creation time</td>
 * 
 * <td>Macintosh time</td>
 * <td>4 Bytes</td>
 * </tr>
 * <tr>
 * <td>0x28</td>
 * <td>modification time</td>
 * <td>Macintosh time</td>
 * <td>4 Bytes</td>
 * </tr>
 * <tr>
 * <td>0x2c</td>
 * 
 * <td>backup time</td>
 * <td>Macintosh time</td>
 * <td>4 Bytes</td>
 * </tr>
 * <tr>
 * <td>0x30</td>
 * <td>modification number</td>
 * <td>integer</td>
 * <td>4 Bytes</td>
 * </tr>
 * 
 * <tr>
 * <td>0x34</td>
 * <td>app_info</td>
 * <td>integer</td>
 * <td>4 Bytes</td>
 * </tr>
 * <tr>
 * <td>0x38</td>
 * <td>sort_info</td>
 * <td>integer</td>
 * 
 * <td>4 Bytes</td>
 * </tr>
 * <tr>
 * <td>0x3c</td>
 * <td>type</td>
 * <td>integer</td>
 * <td>4 Bytes</td>
 * </tr>
 * <tr>
 * <td>0x40</td>
 * <td>creator</td>
 * 
 * <td>integer</td>
 * <td>4 Bytes</td>
 * </tr>
 * <tr>
 * <td>0x44</td>
 * <td>unique_id_seed</td>
 * <td>integer</td>
 * <td>4 Bytes</td>
 * </tr>
 * <tr>
 * <td>0x48</td>
 * 
 * <td>next_record_list</td>
 * <td>integer</td>
 * <td>4 Bytes</td>
 * </tr>
 * <tr>
 * <td>0x4c</td>
 * <td>num_records</td>
 * <td>integer</td>
 * <td>2 Bytes</td>
 * </tr>
 * 
 * </tbody>
 * </table>
 * <br/>
 * @author Sabuj Das
 */
public class PDBHeader implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3124191904377163276L;
	
	public static final int LENGTH = 78;
	
	private RawData<String> name;
	private RawData<Short> fileAttributes;
	private RawData<Short> version;
	
	private RawData<Date> creationTime;
	private RawData<Date> modificationTime;
	private RawData<Date> backupTime;
	
	private RawData<Integer> modificationNumber;
	private RawData<Integer> appInfo;
	private RawData<Integer> sortInfo;
	private RawData<Integer> type;
	private RawData<Integer> creator;
	private RawData<Integer> uniqueIdSeed;
	private RawData<Integer> nextRecordList;
	private RawData<Short> numRecords;
	
	
	/**
	 * 
	 */
	public PDBHeader() {
		initHeader();
	}


	/**
	 * Initializes the header bytes with the appropriate offset and length.
	 * 
	 */
	public void initHeader() {
		name = new RawData<String>(0x00, "name", 32);
		fileAttributes = new RawData<Short>(0x20, "file attributes", 2);
		version = new RawData<Short>(0x22, "version", 2);
		
		creationTime = new RawData<Date>(0x24, "creation time", 4);
		modificationTime = new RawData<Date>(0x28, "modification time", 4);
		backupTime = new RawData<Date>(0x2C, "backup time", 4);
		
		modificationNumber = new RawData<Integer>(0x30, "modification number", 4);
		appInfo = new RawData<Integer>(0x34, "app_info", 4);
		sortInfo = new RawData<Integer>(0x38, "sort_info", 4);
		type = new RawData<Integer>(0x3C, "type", 4);
		creator = new RawData<Integer>(0x40, "creator", 4);
		uniqueIdSeed = new RawData<Integer>(0x44, "unique_id_seed", 4);
		nextRecordList = new RawData<Integer>(0x48, "next_record_list", 4);
		numRecords = new RawData<Short>(0x4C, "num_records", 2);
	}
	
	public String getBinaryString(byte[] bytes){
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			buffer.append(Byte.toString(bytes[i]));
			if(i < bytes.length - 1){
				buffer.append(" | ");
			}
		}
		
		return buffer.toString();
	}

	/**
	 * Read the Header from a byte[] array.
	 * @param headerData byte[]
	 * @return {@link PDBHeader}
	 * @throws IOException
	 */
	public void readHeader(byte[] headerData) throws IOException{
		int length = 0;
		byte[] data = Arrays.copyOfRange(headerData, name.getOffset(), (name.getOffset() + name.getSize()));
		name.setData(data);
		System.out.println(getBinaryString(data));
		if(null != data){
			name.setValue(StringUtil.convertToString(data));
		}
		length += name.getSize();
		
		data = Arrays.copyOfRange(headerData, fileAttributes.getOffset(), (fileAttributes.getOffset() + fileAttributes.getSize()));
		fileAttributes.setData(data);
		System.out.println(getBinaryString(data));
		if(null != data){
			fileAttributes.setValue(ConversionUtil.byteArrayToShort(data));
		}
		length += fileAttributes.getSize();
		
		data = Arrays.copyOfRange(headerData, version.getOffset(), (version.getOffset() + version.getSize()));
		version.setData(data);
		System.out.println(getBinaryString(data));
		if(null != data){
			version.setValue(ConversionUtil.byteArrayToShort(data));
		}
		length += version.getSize();
		
		data = Arrays.copyOfRange(headerData, creationTime.getOffset(), (creationTime.getOffset() + creationTime.getSize()));
		creationTime.setData(data);
		System.out.println(getBinaryString(data));
		if(null != data){
			//creationTime.setValue(ConversionUtil.convertToDate(data));
		}
		length += creationTime.getSize();
		
		data = Arrays.copyOfRange(headerData, modificationTime.getOffset(), (modificationTime.getOffset() + modificationTime.getSize()));
		modificationTime.setData(data);
		System.out.println(getBinaryString(data));
		if(null != data){
			//modificationTime.setValue(ConversionUtil.convertToDate(data));
		}
		length += modificationTime.getSize();
		
		data = Arrays.copyOfRange(headerData, backupTime.getOffset(), (backupTime.getOffset() + backupTime.getSize()));
		backupTime.setData(data);
		System.out.println(getBinaryString(data));
		if(null != data){
			//backupTime.setValue(ConversionUtil.convertToDate(data));
		}
		length += backupTime.getSize();
		
		data = Arrays.copyOfRange(headerData, modificationNumber.getOffset(), (modificationNumber.getOffset() + modificationNumber.getSize()));
		modificationNumber.setData(data);
		System.out.println(getBinaryString(data));
		if(null != data){
			modificationNumber.setValue(ConversionUtil.byteArrayToInt(data));
		}
		length += modificationNumber.getSize();
		
		data = Arrays.copyOfRange(headerData, appInfo.getOffset(), (appInfo.getOffset() + appInfo.getSize()));
		appInfo.setData(data);
		System.out.println(getBinaryString(data));
		if(null != data){
			appInfo.setValue(ConversionUtil.byteArrayToInt(data));
		}
		length += appInfo.getSize();
		
		data = Arrays.copyOfRange(headerData, sortInfo.getOffset(), (sortInfo.getOffset() + sortInfo.getSize()));
		sortInfo.setData(data);
		System.out.println(getBinaryString(data));
		if(null != data){
			sortInfo.setValue(ConversionUtil.byteArrayToInt(data));
		}
		length += sortInfo.getSize();
		
		data = Arrays.copyOfRange(headerData, type.getOffset(), (type.getOffset() + type.getSize()));
		type.setData(data);
		System.out.println(getBinaryString(data));
		if(null != data){
			type.setValue(ConversionUtil.byteArrayToInt(data));
		}
		length += type.getSize();
		
		data = Arrays.copyOfRange(headerData, creator.getOffset(), (creator.getOffset() + creator.getSize()));
		creator.setData(data);
		System.out.println(getBinaryString(data));
		if(null != data){
			creator.setValue(ConversionUtil.byteArrayToInt(data));
		}
		length += creator.getSize();
		
		data = Arrays.copyOfRange(headerData, uniqueIdSeed.getOffset(), (uniqueIdSeed.getOffset() + uniqueIdSeed.getSize()));
		uniqueIdSeed.setData(data);
		System.out.println(getBinaryString(data));
		if(null != data){
			uniqueIdSeed.setValue(ConversionUtil.byteArrayToInt(data));
		}
		length += uniqueIdSeed.getSize();
		
		data = Arrays.copyOfRange(headerData, nextRecordList.getOffset(), (nextRecordList.getOffset() + nextRecordList.getSize()));
		nextRecordList.setData(data);
		System.out.println(getBinaryString(data));
		if(null != data){
			nextRecordList.setValue(ConversionUtil.byteArrayToInt(data));
		}
		length += nextRecordList.getSize();
		
		data = Arrays.copyOfRange(headerData, numRecords.getOffset(), (numRecords.getOffset() + numRecords.getSize()));
		numRecords.setData(data);
		System.out.println(getBinaryString(data));
		if(null != data){
			numRecords.setValue(ConversionUtil.byteArrayToShort(data));
		}
		length += numRecords.getSize();
		
	}

	public RawData<String> getName() {
		return name;
	}


	public void setName(RawData<String> name) {
		this.name = name;
	}


	public RawData<Short> getFileAttributes() {
		return fileAttributes;
	}


	public void setFileAttributes(RawData<Short> fileAttributes) {
		this.fileAttributes = fileAttributes;
	}


	public RawData<Short> getVersion() {
		return version;
	}


	public void setVersion(RawData<Short> version) {
		this.version = version;
	}


	public RawData<Date> getCreationTime() {
		return creationTime;
	}


	public void setCreationTime(RawData<Date> creationTime) {
		this.creationTime = creationTime;
	}


	public RawData<Date> getModificationTime() {
		return modificationTime;
	}


	public void setModificationTime(RawData<Date> modificationTime) {
		this.modificationTime = modificationTime;
	}


	public RawData<Date> getBackupTime() {
		return backupTime;
	}


	public void setBackupTime(RawData<Date> backupTime) {
		this.backupTime = backupTime;
	}


	public RawData<Integer> getModificationNumber() {
		return modificationNumber;
	}


	public void setModificationNumber(RawData<Integer> modificationNumber) {
		this.modificationNumber = modificationNumber;
	}


	public RawData<Integer> getAppInfo() {
		return appInfo;
	}


	public void setAppInfo(RawData<Integer> appInfo) {
		this.appInfo = appInfo;
	}


	public RawData<Integer> getSortInfo() {
		return sortInfo;
	}


	public void setSortInfo(RawData<Integer> sortInfo) {
		this.sortInfo = sortInfo;
	}


	public RawData<Integer> getType() {
		return type;
	}


	public void setType(RawData<Integer> type) {
		this.type = type;
	}


	public RawData<Integer> getCreator() {
		return creator;
	}


	public void setCreator(RawData<Integer> creator) {
		this.creator = creator;
	}


	public RawData<Integer> getUniqueIdSeed() {
		return uniqueIdSeed;
	}


	public void setUniqueIdSeed(RawData<Integer> uniqueIdSeed) {
		this.uniqueIdSeed = uniqueIdSeed;
	}


	public RawData<Integer> getNextRecordList() {
		return nextRecordList;
	}


	public void setNextRecordList(RawData<Integer> nextRecordList) {
		this.nextRecordList = nextRecordList;
	}


	public RawData<Short> getNumRecords() {
		return numRecords;
	}


	public void setNumRecords(RawData<Short> numRecords) {
		this.numRecords = numRecords;
	}
	

	

}
