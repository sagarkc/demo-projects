/* ******************************************************************************
 * 	
 * 	Name	: ConsoleAppearance.java
 * 	Type	: com.gs.jprompt.common.ConsoleAppearance
 * 
 * 	Created	: Feb 22, 2012
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

import java.awt.Color;
import java.awt.Font;

/**
 * @author sabuj.das
 * @MailTo sabuj.das@gmail.com
 * 
 */
public class ConsoleAppearance {

	private Font font;
	private Color fgColor, bgColor;
	
	/**
	 * 
	 */
	public ConsoleAppearance() {
		// TODO Auto-generated constructor stub
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Color getFgColor() {
		return fgColor;
	}

	public void setFgColor(Color fgColor) {
		this.fgColor = fgColor;
	}

	public Color getBgColor() {
		return bgColor;
	}

	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}
	
	
}
