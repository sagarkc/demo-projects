/* ******************************************************************************
 * 	
 * 	Name	: JPromptLauncher.java
 * 	Type	: com.gs.jprompt.app.JPromptLauncher
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

package com.gs.jprompt.app;

import java.awt.EventQueue;

import javax.swing.UIManager;

import com.gs.jprompt.app.form.JPromptMainFrame;

/**
 * @author sabuj.das
 * @MailTo sabuj.das@gmail.com
 * 
 */
public class JPromptLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO: get saved state, set the application state using the saved state
		EventQueue.invokeLater(new Runnable() {
            public void run() {
            	try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
                new JPromptMainFrame().setVisible(true);
            }
        });
	}
 
	

}
