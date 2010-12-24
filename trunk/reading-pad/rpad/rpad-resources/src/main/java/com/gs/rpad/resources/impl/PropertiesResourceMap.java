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
 * 	Name	: PropertiesResourceMap.java
 * 	Type	: com.gs.rpad.resources.impl.PropertiesResourceMap
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

package com.gs.rpad.resources.impl;

import java.awt.Font;
import java.awt.Image;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

import com.gs.rpad.resources.ResourceMap;
import com.gs.utils.io.IOUtil;
import com.gs.utils.text.StringUtil;

/**
 * @author Sabuj Das
 *
 */
public class PropertiesResourceMap implements ResourceMap {

	private static final Logger logger = Logger.getLogger(PropertiesResourceMap.class);
	
	private Properties properties;
	
	private static PropertiesResourceMap instance;
	
	private PropertiesResourceMap() {
		this.properties = new Properties();
	}
	
	public static PropertiesResourceMap getInstance(){
		synchronized (PropertiesResourceMap.class) {
			if(null == instance){
				instance = new PropertiesResourceMap();
			}
		}
		return instance;
	}
	
	public void initResources(String resourceFileName){
		logger.info("Initialize resources for : " + resourceFileName);
		InputStream inputStream = null;
		try {
			inputStream = getClass().getResourceAsStream(resourceFileName);
			if(null != inputStream){
				properties.load(inputStream);
			} else {
				logger.error("Cannot load the resource from : " + resourceFileName);
			}
		} catch (Exception e) {
			logger.error("Cannot load the resource from : " + resourceFileName, e);
		} finally {
			IOUtil.close(inputStream);
		}
	}
	
	/**
	 * Get the string value of a particular property as String
	 */
	@Override
	public String getString(String key) {
		if(null != properties){
			return properties.getProperty(key);
		}
		return "";
	}

	/**
	 * Get the image for a particular image file name
	 */
	@Override
	public Image getImage(String key) {
		try{
			String fileName = "";
			if(null != properties){
				fileName = properties.getProperty(key);
			}
			if(StringUtil.hasValidContent(fileName)){
				return new ImageIcon(getClass().getResource(fileName)).getImage();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	
	@Override
	public ImageIcon getImageIcon(String key) {
		try{
			String fileName = "";
			if(null != properties){
				fileName = properties.getProperty(key);
			}
			if(StringUtil.hasValidContent(fileName)){
				return new ImageIcon(getClass().getResource(fileName));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	
	@Override
	public Font getFont(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
