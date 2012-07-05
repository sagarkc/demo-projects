/* ******************************************************************************
 * 	
 * 	Name	: BaseClassLoader.java
 * 	Type	: com.gs.automill.core.BaseClassLoader
 * 
 * 	Created	: May 5, 2012
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

package com.gs.automill.core;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

import com.gs.automill.model.Model;
import com.gs.automill.model.PackageManager;
import com.gs.utils.collection.CollectionUtils;

/**
 * @author sabuj.das
 * @MailTo sabuj.das@gmail.com
 * 
 */
public class BaseClassLoader extends URLClassLoader {

	private Set<String> clazzNames;
	private static final PackageManager package_manager = PackageManager.getInstance();
	
	public BaseClassLoader(URL[] unusedArgument){
		super(new URL[]{});
		clazzNames = new HashSet<String>();
	}

	public void addFile (String path) throws IOException
    {
        String urlPath = "jar:file:/" + path + "!/";
        addURL (new URL (urlPath));
        package_manager.loadJar(path);
        Set<Model> clazzModels = package_manager.getClassModels(path);
        if(CollectionUtils.hasElements(clazzModels)){
        	for (Model model : clazzModels) {
				clazzNames.add(model.getQualifiedName());
			}
        }
    }
	
}
