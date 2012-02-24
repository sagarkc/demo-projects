/* ******************************************************************************
 * 	
 * 	Name	: CommandAutocompletePanel.java
 * 	Type	: com.gs.jprompt.app.components.CommandAutocompletePanel
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

package com.gs.jprompt.app.components;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 * @author sabuj.das
 * @MailTo sabuj.das@gmail.com
 * 
 */
public class CommandAutocompletePanel extends JPanel {

    public CommandAutocompletePanel() {
        initComponents();
    }

    public JList getFilesFoldersList() {
		return filesFoldersList;
	}

	public JList getShellCommandList() {
		return shellCommandList;
	}

	@SuppressWarnings("unchecked")
    private void initComponents() {

        autoCompleteTabbedPane = new JTabbedPane();
        jScrollPane1 = new JScrollPane();
        filesFoldersList = new JList();
        jScrollPane2 = new JScrollPane();
        shellCommandList = new JList();

        setLayout(new java.awt.BorderLayout());

        jScrollPane1.setViewportView(filesFoldersList);

        autoCompleteTabbedPane.addTab("Files/Folders", jScrollPane1);

        jScrollPane2.setViewportView(shellCommandList);

        autoCompleteTabbedPane.addTab("Shell Commands", jScrollPane2);

        add(autoCompleteTabbedPane, java.awt.BorderLayout.CENTER);
    }
    
    private JTabbedPane autoCompleteTabbedPane;
    private JList filesFoldersList;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JList shellCommandList;
    
}