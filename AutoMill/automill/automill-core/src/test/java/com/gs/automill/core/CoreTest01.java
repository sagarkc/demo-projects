/* ******************************************************************************
 * 	
 * 	Name	: CoreTest01.java
 * 	Type	: com.gs.automill.core.CoreTest01
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gs.automill.model.Model;
import com.gs.automill.model.PackageManager;
import com.gs.automill.util.Tree;

import junit.framework.TestCase;

/**
 * @author sabuj.das
 * @MailTo sabuj.das@gmail.com
 * 
 */
public class CoreTest01 {

	static String path = "D:/SVN_HOME/demo-projects/trunk/AutoMill/temp/dbex-model-1.0-Helium-1.jar";
	
	public static void main(String[] args) {
		
		BaseClassLoader l = new BaseClassLoader(null);
		try {
			l.addFile(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("After process");
		Set<Model> modelTree = PackageManager.getInstance().getPackages(path);
		for (Model model : modelTree) {
			Set<Model> pkzList = new HashSet<Model>();
			pkzList = PackageManager.getInstance().getClassModels(path);
			System.out.println(pkzList.size());
		}
		
	}
	
}
