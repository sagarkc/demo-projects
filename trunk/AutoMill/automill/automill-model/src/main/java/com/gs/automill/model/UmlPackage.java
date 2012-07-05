/* ******************************************************************************
 * 	
 * 	Name	: UmlPackage.java
 * 	Type	: com.gs.automill.model.UmlPackage
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

package com.gs.automill.model;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author sabuj.das
 * @MailTo sabuj.das@gmail.com
 * 
 */
public class UmlPackage extends UmlBaseModel{

	public final static String PACKAGE_SEPARATOR = "/";
	
	private UmlPackage parent;
	private Set<UmlPackage> children;
	private String packageName;
	

	public UmlPackage(String modelName) {
		super(modelName);
		children = new LinkedHashSet<UmlPackage>();
	}

	public UmlPackage getParent() {
		return parent;
	}

	public void setParent(UmlPackage parent) {
		this.parent = parent;
	}

	public Set<UmlPackage> getChildren() {
		return children;
	}

	public void setChildren(Set<UmlPackage> children) {
		this.children = children;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	
	
}
