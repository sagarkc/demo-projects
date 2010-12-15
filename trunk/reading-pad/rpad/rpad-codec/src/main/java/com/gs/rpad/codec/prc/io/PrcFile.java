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
 * 	Name	: PrcFile.java
 * 	Type	: com.gs.rpad.codec.prc.io.PrcFile
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

package com.gs.rpad.codec.prc.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;

import com.gs.rpad.codec.prc.model.PRCHeader;
import com.gs.rpad.codec.prc.model.PRCResource;
import com.gs.rpad.codec.prc.model.PRCResourceHeader;
import com.gs.utils.text.StringUtil;

/**
 * @author Sabuj Das
 *
 */
public class PrcFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4816646447283034225L;

	private PRCHeader header;
	private List<PRCResourceHeader> resourceHeaders;
	private List<PRCResource> resources;
	
	/**
	 * Canonical Name of the file (path/name.extension)
	 */
	private String fileName;
	private File originalFile;
	
	
	/**
	 * @param fileName
	 * @throws FileNotFoundException 
	 */
	public PrcFile(String fileName) throws FileNotFoundException {
		if(!StringUtil.hasValidContent(fileName)){
			throw new FileNotFoundException("Cannot Find the File : " + fileName);
		}
		this.fileName = fileName;
		originalFile = new File(fileName);
		if(!originalFile.exists()){
			throw new FileNotFoundException("Cannot Find the File : " + fileName);
		}
	}

	/**
	 * @param originalFile
	 * @throws FileNotFoundException 
	 */
	public PrcFile(File originalFile) throws FileNotFoundException {
		this.originalFile = originalFile;
		if(!originalFile.exists()){
			throw new FileNotFoundException("Cannot Find the File : " + fileName);
		}
		this.fileName = this.originalFile.getAbsolutePath();
	}

	/**
	 * @return the header
	 */
	public PRCHeader getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(PRCHeader header) {
		this.header = header;
	}

	/**
	 * @return the resourceHeaders
	 */
	public List<PRCResourceHeader> getResourceHeaders() {
		return resourceHeaders;
	}

	/**
	 * @param resourceHeaders the resourceHeaders to set
	 */
	public void setResourceHeaders(List<PRCResourceHeader> resourceHeaders) {
		this.resourceHeaders = resourceHeaders;
	}

	/**
	 * @return the resources
	 */
	public List<PRCResource> getResources() {
		return resources;
	}

	/**
	 * @param resources the resources to set
	 */
	public void setResources(List<PRCResource> resources) {
		this.resources = resources;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @return the originalFile
	 */
	public File getOriginalFile() {
		return originalFile;
	}
	
	
	
}
