/** *****************************************************************************
 * 	
 * 	Name	: RpadLibrary.java
 * 	Type	: com.gs.rpad.RpadLibrary
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

package com.gs.rpad;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 50120C1509
 * 
 */
public abstract class RpadLibrary<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 176978758765867567L;
	
	private String rootPath;
	private String libraryName;

	private List<T> dataFiles;

	private List<RpadLibrary<T>> childLibraries;

	private RpadLibrary<T> parentLibrary;

	private boolean useSystemDirStructure = false;

	/**
	 * 
	 */
	@Deprecated
	public RpadLibrary() {
		// TODO Auto-generated constructor stub
	}
	
	public RpadLibrary(String libraryName, String rootPath) {
		this(libraryName, rootPath, false);
	}

	public RpadLibrary(String libraryName, String rootPath,
			boolean useSystemDirStructure) {
		this.libraryName = libraryName;
		this.rootPath = rootPath;
		this.useSystemDirStructure = useSystemDirStructure;
		
		childLibraries = new ArrayList<RpadLibrary<T>>();
		dataFiles = new ArrayList<T>();
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public String getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	public List<T> getDataFiles() {
		return dataFiles;
	}

	public void setDataFiles(List<T> dataFiles) {
		this.dataFiles = dataFiles;
	}

	public List<RpadLibrary<T>> getChildLibraries() {
		return childLibraries;
	}

	public void setChildLibraries(List<RpadLibrary<T>> childLibraries) {
		this.childLibraries = childLibraries;
	}

	public RpadLibrary<T> getParentLibrary() {
		return parentLibrary;
	}

	public void setParentLibrary(RpadLibrary<T> parentLibrary) {
		this.parentLibrary = parentLibrary;
	}

	public boolean isUseSystemDirStructure() {
		return useSystemDirStructure;
	}

	public void setUseSystemDirStructure(boolean useSystemDirStructure) {
		this.useSystemDirStructure = useSystemDirStructure;
	}

	public abstract boolean createLibrary();
	
	public abstract boolean openLibrary(String sourceFile) throws IOException;
	
	public abstract boolean importFiles(List<T> dataFiles);
	
	public abstract boolean removeFiles(List<T> dataFiles);
	
	public abstract boolean saveLibrary(String targetFile) throws IOException;
	
}
