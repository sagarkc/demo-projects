/* ******************************************************************************
 * 	
 * 	Name	: Tree.java
 * 	Type	: com.gs.automill.util.Tree
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

package com.gs.automill.util;

import java.util.Set;

import com.gs.automill.util.tree.Node;

/**
 * @author sabuj.das
 * @MailTo sabuj.das@gmail.com
 * 
 */
public class Tree<T> {

	private final Node<T> root;
	

	public Tree(Node<T> root) {
		if(null == root){
			throw new IllegalArgumentException("Root node cannot be null...");
		}
		this.root = root;
	}

	public Node<T> getRoot() {
		return root;
	}
	
	public boolean isRoot(T item){
		if(null != item){
			return (root.getData().equals(item));
		}
		return false;
	}
	
	public boolean addLastNode(T lastItem, T ... pathItems) throws Exception{
		if(null != pathItems && pathItems.length > 0){
			if(!isRoot(pathItems[0])){
				throw new Exception("Invalid root item");
			}
			Node<T> head = findLastNodeInPath(pathItems);
			if(null != head){
				Node<T> node = new Node<T>(lastItem);
				node.setParent(head);
				head.getChildren().add(node);
				return true;
			}
			
		}
		return false;
	}
	
	public Node<T> findLastNodeInPath(T ... pathItems ){
		if(null != pathItems && pathItems.length > 0){
			Node<T> head = root;
			if(!head.getData().equals(pathItems[0]))
				return null;
			for (int i = 1; i < pathItems.length; i++) {
				Set<Node<T>> children = head.getChildren();
				for (Node<T> node : children) {
					if(node.getData().equals(pathItems[i])){
						head = node;
						continue;
					}
				}
			}
			
			return head;
		}
		
		return null;
	}
	
	public Set<Node<T>> list(){
		return root.getChildren();
	}
}
