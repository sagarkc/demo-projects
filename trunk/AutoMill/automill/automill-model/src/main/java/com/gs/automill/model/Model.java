/* ******************************************************************************
 * 	
 * 	Name	: Model.java
 * 	Type	: com.gs.automill.model.Model
 * 
 * 	Created	: May 6, 2012
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
 * The Model represents the Package/Class in a jar file. Model can have a parent and also can have
 * a Set of Models as children. The Model name ends with .class is a class file. 
 * 
 * @author sabuj.das
 * @MailTo sabuj.das@gmail.com
 * 
 */
public class Model {

	public static final String META_INF = "META_INF";
	public static final String PACKAGE_SEP = "/";
	public static final String QUALIFIED_NAME_SEP = ".";
	public static final String CLASS_NAME_ENDS = ".class";
	
	private String name;
	private String qualifiedName;
	private boolean isPackage = false;
	
	private Model parent;
	private Set<Model> children;
	
	/**
	 * Creates a new instance of Model by name. for the root model, the name and the 
	 * qualifiedName are same. Model cannot be created without a valid name.
	 * @param name
	 */
	public Model(String name) {
		this.children = new LinkedHashSet<Model>();
		if(null != name && name.trim().length() > 0){
			this.name = name;
			if(!name.contains(Model.QUALIFIED_NAME_SEP)
					&& !name.startsWith(META_INF)){
				setPackage(true);
			}
			this.qualifiedName = name;
		}
		else {
			throw new IllegalArgumentException("Invalid name...");
		}
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return boolean
	 */
	public boolean isPackage() {
		return isPackage;
	}

	public void setPackage(boolean isPackage) {
		this.isPackage = isPackage;
	}

	public Model getParent() {
		return parent;
	}

	public void setParent(Model parent) {
		this.parent = parent;
		this.qualifiedName = createQualifiedName();
	}

	public Set<Model> getChildren() {
		return children;
	}

	public void setChildren(Set<Model> children) {
		this.children = children;
	}

	public String getQualifiedName() {
		return qualifiedName;
	}

	/**
	 * @return
	 */
	private String createQualifiedName() {
		Model temp = this;
		StringBuffer buffer = new StringBuffer(temp.getName());
		while((temp = temp.getParent()) != null){
			buffer.insert(0, temp.getName()+QUALIFIED_NAME_SEP);
		}
		return buffer.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((qualifiedName == null) ? 0 : qualifiedName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Model)) {
			return false;
		}
		Model other = (Model) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (qualifiedName == null) {
			if (other.qualifiedName != null) {
				return false;
			}
		} else if (!qualifiedName.equals(other.qualifiedName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return qualifiedName;
	}

	/**
	 * @return
	 */
	public boolean hasChildren() {
		return (null != getChildren() && getChildren().size() > 0);
	}

	public void listPackages(Model model, final Set<Model> set, boolean listPackage){
		if(null == model || null == set)
			return;
		if(!model.hasChildren() ){
			if(listPackage){
				if(model.isPackage())
					set.add(model);
				else if(model.getParent() != null){
					set.add(model.getParent());
				}
			} else {
				if(!model.isPackage()){
					set.add(model);
				}
			}
			return; 
		}
		if(model.isPackage()){
			Set<Model> children = model.getChildren();
			if(null != children){
				for (Model child : children) {
					listPackages(child, set, listPackage);
				}
			}
		}
	}
	
}
