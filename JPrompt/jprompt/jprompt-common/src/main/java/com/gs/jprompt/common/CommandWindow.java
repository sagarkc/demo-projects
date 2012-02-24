/* ******************************************************************************
 * 	
 * 	Name	: CommandWindow.java
 * 	Type	: com.gs.jprompt.common.CommandWindow
 * 
 * 	Created	: Feb 21, 2012
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

import javax.swing.JInternalFrame;

/**
 * @author sabuj.das
 * @MailTo sabuj.das@gmail.com
 * 
 */
public class CommandWindow {

	private String title;
	private String workingDirName;
	private JInternalFrame internalFrame;
	private ConsoleAppearance appearance;
	
	/**
	 * 
	 */
	public CommandWindow() {
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWorkingDirName() {
		return workingDirName;
	}

	public void setWorkingDirName(String workingDirName) {
		this.workingDirName = workingDirName;
	}

	public JInternalFrame getInternalFrame() {
		return internalFrame;
	}

	public void setInternalFrame(JInternalFrame internalFrame) {
		this.internalFrame = internalFrame;
	}

	public ConsoleAppearance getAppearance() {
		return appearance;
	}

	public void setAppearance(ConsoleAppearance appearance) {
		this.appearance = appearance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (!(obj instanceof CommandWindow)) {
			return false;
		}
		CommandWindow other = (CommandWindow) obj;
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}
	
	
	
}
