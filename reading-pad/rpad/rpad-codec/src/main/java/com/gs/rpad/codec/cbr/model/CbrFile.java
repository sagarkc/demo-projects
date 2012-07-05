/** *****************************************************************************
 * 	
 * 	Name	: CbrFile.java
 * 	Type	: com.gs.rpad.codec.cbr.model.CbrFile
 * 
 * 	Created	: Nov 2, 2011
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

package com.gs.rpad.codec.cbr.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;

/**
 * @author 50120C1509
 *
 */
public class CbrFile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1677656876758756L;
	
	private String fileName;
	private File file;
	
	private CbrFile(String fileName) throws FileNotFoundException{
		this.fileName = fileName;
		check(fileName);
	}
	
	

	private boolean check(String fileName) throws FileNotFoundException  {
		File file = new File(fileName);
		if(!file.exists()){
			throw new FileNotFoundException("File Not Found: " + fileName);
		}
		return false;
	}
	
	private boolean check(File file) throws FileNotFoundException {
		return check(file.getAbsolutePath());
	}



	private CbrFile(File file) throws FileNotFoundException {
		this.file = file;
		check(file);
	}
	
	
	
	
}
