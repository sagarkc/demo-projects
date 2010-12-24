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
 * 	Name	: ResourceMap.java
 * 	Type	: com.gs.rpad.resources.ResourceMap
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

package com.gs.rpad.resources;

import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * @author Sabuj Das
 * 
 */
public interface ResourceMap {
	
	String IMAGE_PATH = "/images";
	String IMAGE_16x16_PATH = "/images/_16x16";
	String IMAGE_24x24_PATH = "/images/_24x24";
	String IMAGE_32x32_PATH = "/images/_32x32";
	String IMAGE_BIGGER_PATH = "/images/bigger";
	
	public void initResources(String resourceFileName);

	public String getString(String key);

	public Image getImage(String key);

	public ImageIcon getImageIcon(String key);

	public Font getFont(String key);

}
