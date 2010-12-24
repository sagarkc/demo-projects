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
 * 	Name	: ResourceLoader.java
 * 	Type	: com.gs.rpad.resources.io.ResourceLoader
 * 
 * 	Created	: Dec 23, 2010
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

import com.gs.rpad.resources.ResourceMap;

/**
 * @author Sabuj Das
 *
 */
public class ResourceLoader {
	
	private static final Logger logger = Logger.getLogger(ResourceLoader.class);

	public static final String RESOURCE_LOCATION_PARAM = "resourceLocation";
	
	private final List<String> resourceLocations;
	private final ResourceMap resourceMap;
	

	/**
	 * @param resourceLocations
	 * @param resourceMap
	 */
	public ResourceLoader(List<String> resourceLocations, ResourceMap resourceMap) {
		this.resourceLocations = resourceLocations;
		this.resourceMap = resourceMap;
		initResourceLoader();
	}

	/**
	 * @return the resourceLocations
	 */
	public List<String> getResourceLocations() {
		return resourceLocations;
	}
	
	/**
	 * @return the resourceMap
	 */
	public ResourceMap getResourceMap() {
		return resourceMap;
	}

	public void initResourceLoader(){
		logger.info("START\t:: Initialize ResourceLoader");
		if(null != getResourceLocations() && getResourceLocations().size() > 0){
			for (String resourceFileName : getResourceLocations()) {
				getResourceMap().initResources(resourceFileName);
			}
		}
		logger.info("DONE\t:: Initialize ResourceLoader");
	}
	
}
