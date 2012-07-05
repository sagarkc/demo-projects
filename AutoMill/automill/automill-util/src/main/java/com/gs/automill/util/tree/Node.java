/* ******************************************************************************
 * 	
 * 	Name	: Node.java
 * 	Type	: com.gs.automill.util.tree
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

package com.gs.automill.util.tree;

import java.util.HashSet;
import java.util.Set;

/**
 * @author sabuj.das
 * @MailTo sabuj.das@gmail.com
 * 
 */
public class Node<T> {

	private Node<T> parent;
	private Set<Node<T>> children;
	private T data;
	
	public Node(T data) {
		this.data = data;
		this.children = new HashSet<Node<T>>();
	}

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public Set<Node<T>> getChildren() {
		return children;
	}

	public void setChildren(Set<Node<T>> children) {
		this.children = children;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public void addChild(Node<T> child){
		if(null != child)
			this.children.add(child);
	}
	
	
	
}
