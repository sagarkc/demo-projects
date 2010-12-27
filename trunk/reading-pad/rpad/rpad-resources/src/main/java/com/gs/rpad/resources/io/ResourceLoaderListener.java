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
 * 	Name	: ResourceLoaderListener.java
 * 	Type	: com.gs.rpad.resources.io.ResourceLoaderListener
 * 
 * 	Created	: Dec 27, 2010
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

package com.gs.rpad.resources.io;

import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author Sabuj Das
 *
 */
public class ResourceLoaderListener {

	private static final Logger logger = Logger.getLogger(ResourceLoaderListener.class);
	
	private List<String> resourceLocations;
	private ResourceLoader resourceLoader;
	
	private static ResourceLoaderListener instance;
	
	private ResourceLoaderListener(){}
	
	public static ResourceLoaderListener getInstance(){
		synchronized (ResourceLoaderListener.class) {
			if(null == instance){
				instance = new ResourceLoaderListener();
			}
		}
		return instance;
	}

	/**
	 * @return the resourceLocations
	 */
	public List<String> getResourceLocations() {
		return resourceLocations;
	}

	/**
	 * @param resourceLocations the resourceLocations to set
	 */
	public void setResourceLocations(List<String> resourceLocations) {
		this.resourceLocations = resourceLocations;
	}

	/**
	 * @return the resourceLoader
	 */
	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	/**
	 * @param resourceLoader the resourceLoader to set
	 */
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	
	
	
}
