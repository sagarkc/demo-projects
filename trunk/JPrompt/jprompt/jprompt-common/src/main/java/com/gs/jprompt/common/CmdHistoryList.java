/* ******************************************************************************
 * 	
 * 	Name	: CircularLinkedList.java
 * 	Type	: com.gs.jprompt.common.CircularLinkedList
 * 
 * 	Created	: Feb 23, 2012
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

package com.gs.jprompt.common;

import java.util.ArrayList;

/**
 * @author sabuj.das
 * @MailTo sabuj.das@gmail.com
 * 
 */
public class CmdHistoryList<T> extends ArrayList<T>{

	private int currentIndex = -1;
	

	public CmdHistoryList() {
		super(JPromptConstants.CMD_HISTORY_MAX_LENGTH);
	}
	
	@Override
	public boolean add(T e) {
		if(JPromptConstants.CMD_HISTORY_MAX_LENGTH == size()){
			super.remove(0);
		}
		boolean b = super.add(e);
		currentIndex = size()-1;
		return b;
	}
	
	@Override
	public T remove(int index) {
		T t = super.remove(index);
		currentIndex = size()-1;
		return t;
	}


	public boolean hasNext(){
		return (currentIndex < size()-1);
	}
	
	public boolean hasPrevious(){
		return (currentIndex > 0);
	}

	public T getNext(){
		if(hasNext()){
			currentIndex++;
			return get(currentIndex);
		}
		return null;
	}
	
	public T getCurrentItem(){
		return get(currentIndex);
	}
	
	public T getPrevious(){
		if(hasPrevious()){
			currentIndex--;
			return get(currentIndex);
		}
		return null;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}
	
}
