/** *****************************************************************************
 * 	
 * 	Name	: CbrLibrary.java
 * 	Type	: com.gs.rpad.codec.cbr.model.CbrLibrary
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

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import com.gs.rpad.RpadLibrary;

/**
 * @author sabuj.das
 *
 */
public class CbrLibrary extends RpadLibrary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 176978758765867567L;

	/**
	 * @param string
	 * @param string2
	 */
	public CbrLibrary(String libraryName, String rootPath) {
		super(libraryName, rootPath);
	}

	public static void main(String[] args) {
		CbrLibrary l = new CbrLibrary("", "");
	}
	
	/* (non-Javadoc)
	 * @see com.gs.rpad.RpadLibrary#createLibrary()
	 */
	@Override
	public boolean createLibrary() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.gs.rpad.RpadLibrary#openLibrary(java.lang.String)
	 */
	@Override
	public boolean openLibrary(String sourceFile) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.gs.rpad.RpadLibrary#importFiles(java.util.List)
	 */
	@Override
	public boolean importFiles(List dataFiles) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.gs.rpad.RpadLibrary#removeFiles(java.util.List)
	 */
	@Override
	public boolean removeFiles(List dataFiles) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.gs.rpad.RpadLibrary#saveLibrary(java.lang.String)
	 */
	@Override
	public boolean saveLibrary(String targetFile) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
