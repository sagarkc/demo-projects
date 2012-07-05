/* ******************************************************************************
 * 	
 * 	Name	: Graph.java
 * 	Type	: com.gs.automill.util.Graph
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

package com.gs.automill.util;

import java.util.List;

/**
 * @author sabuj.das
 * @MailTo sabuj.das@gmail.com
 * 
 */
public abstract class Graph<T> {

	private int numVertices;
	private T[] vertices;
	private List<List<T>> adjacencyList;
	
	
	
}
