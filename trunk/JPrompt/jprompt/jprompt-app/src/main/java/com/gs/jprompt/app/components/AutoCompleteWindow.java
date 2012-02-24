/* ******************************************************************************
 * 	
 * 	Name	: AutoCompleteWindow.java
 * 	Type	: com.gs.jprompt.app.components.AutoCompleteWindow
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import com.gs.utils.text.StringUtil;

/**
 * @author sabuj.das
 * @MailTo sabuj.das@gmail.com
 * 
 */
public class AutoCompleteWindow extends JWindow 
	implements KeyListener, MouseListener, MouseWheelListener, FocusListener{

	private JList fileList;
	
	private ConsoleTextArea commandTextArea;
	private CommandAutocompletePanel autocompletePanel;
	private java.util.Vector<String> tempVector = new java.util.Vector<String>(1, 1);
	private StringBuffer typed = new StringBuffer();
	/**
	 * 
	 */
	public AutoCompleteWindow(ConsoleTextArea ta) {
		this.commandTextArea = ta;
		
		init();
	}
	
	private void init(){
		setSize(340, 240);
		setEnabled(true);
		setFocusable(true);
		setAlwaysOnTop(true);
		
		addKeyListener(this);
		addFocusListener(this);
		this.commandTextArea.addFocusListener(this);
		this.commandTextArea.addKeyListener(this);
		getContentPane().setLayout(new BorderLayout());
		
		autocompletePanel = new CommandAutocompletePanel();
		//BorderFactory.createLineBorder(Color.BLUE)
		fileList = new JList();
		fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.WHITE);
		
		/*panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createLineBorder(Color.BLACK, 1),
						BorderFactory.createMatteBorder(4, 4, 4, 4, Color.WHITE)
						),
				BorderFactory.createCompoundBorder(
						BorderFactory.createLineBorder(Color.GRAY),
						BorderFactory.createEmptyBorder(4, 4, 4, 4)
						)
				));*/
		
		//getContentPane().add(autocompletePanel, BorderLayout.CENTER);
		JScrollPane sc = new JScrollPane();
		sc.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createLineBorder(Color.BLACK, 1),
						BorderFactory.createMatteBorder(4, 4, 4, 4, Color.WHITE)
						),
				BorderFactory.createCompoundBorder(
						BorderFactory.createLineBorder(Color.GRAY),
						BorderFactory.createEmptyBorder(4, 4, 4, 4)
						)
				));
		sc.setViewportView(fileList);
		sc.setBackground(Color.WHITE);
		panel.add(sc, BorderLayout.CENTER);
		
		getContentPane().add(panel, BorderLayout.CENTER);
		
	}
	
	public void showWindow(){
		Point point = commandTextArea.getCaret().getMagicCaretPosition();
		if(null != point){
			Point taPoint = commandTextArea.getLocationOnScreen();
			
			setLocation((int)(point.getX() + taPoint.getX()), (int)(point.getY() + taPoint.getY()));
		}
		setVisible(true);
	}
	
	
	@Override
	public void setVisible(boolean b) {
		if(b){
			b = populateFiles();
		}
		super.setVisible(b);
	}

	/**
	 * 
	 */
	private boolean populateFiles() {
		String dirName = commandTextArea.getWorkingDirectory();
		if(!StringUtil.hasValidContent(dirName)){
			return false;
		}
		File dir = new File(dirName);
		if(!dir.exists())
			return false;
		
		File[] files = dir.listFiles();
		if(null != files && files.length > 0){
			DefaultListModel listModel = new DefaultListModel();
			for (File file : files) {
				if(file.exists()){
					listModel.addElement(file.getName());
				}
			}
			fileList.setModel(listModel);
			fileList.setSelectedIndex(0);
		} else {
			return false;
		}
		return true;
	}

	public Component getParentComponent() {
		return commandTextArea;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(KeyEvent.VK_ESCAPE == e.getKeyCode()){
			this.setVisible(false);
			typed.setLength(0);
			return;
		}
		char c = e.getKeyChar();
		if (c != '\r' && c != '\n' && c != '\t' && c != '\b')
			typed.append(e.getKeyChar());
		else
			typed.setLength(0);
		if (c == '\b')
			typed.setLength(typed.length() > 0 ? typed.length() - 1 : 0);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(KeyEvent.VK_TAB == e.getKeyCode()){
			select();
			setVisible(false);
		}
		
		int kc = e.getKeyCode();
		// if ESCAPE is pressed... remove the popup
		if(KeyEvent.VK_ESCAPE == kc){
			this.setVisible(false);
			typed.setLength(0);
			return;
		}
		int index = fileList.getSelectedIndex();
		if (kc == KeyEvent.VK_UP)
			moveUp();
		else if (kc == KeyEvent.VK_DOWN)
			moveDown();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int kc = e.getKeyCode();
		// if ESCAPE is pressed... remove the popup
		if(KeyEvent.VK_ESCAPE == kc){
			this.setVisible(false);
			typed.setLength(0);
			return;
		}
		if (kc == KeyEvent.VK_TAB){
			select();
			setVisible(false);
		}
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		//commandTextArea.requestFocus();
		//dispose();
		setVisible(false);
	}
	
	
	private void moveUp() {
		if (!this.isVisible())
			return;
		int index = fileList.getSelectedIndex();
		if(index <= 0)
			return;
		fileList.setSelectedIndex(index = index > 0 ? index - 1
				: tempVector.size() - 1);
		fileList.validate();
		fileList.scrollRectToVisible(fileList.getCellBounds(index, index - 1 < 0 ? 0
				: index - 1));
	}

	private void moveDown() {
		if (!this.isVisible())
			return;
		int index = fileList.getSelectedIndex();
		if(index <=0 || index == fileList.getModel().getSize()-1)
			return;
		fileList.setSelectedIndex(index = index < tempVector.size() - 1 ? index + 1
				: 0);
		fileList.validate();
		if (index == 0)
			fileList.scrollRectToVisible(fileList.getCellBounds(0, 1));
		else
			fileList.scrollRectToVisible(fileList.getCellBounds(index + 1 < tempVector
					.size() - 1 ? index + 1 : index, index + 1 < tempVector
					.size() - 1 ? index + 1 : index));
	}

	private void select() {
		if (!this.isVisible())
			return;
		
		String txt = commandTextArea.getText();
		int i = 0, n = commandTextArea.getCaretPosition() - 1, count = 0;
		char c;
		for (i = n; i >= 0; i--) {
			if (!(((c = txt.charAt(i)) >= 'a' && c <= 'z') || (c >= '0' && c <= '9'))
					&& count != 0)
				break;
			else
				count++;
		}
		if (count > 0 && this.isVisible()) {
			commandTextArea.setSelectionStart(i + 1);
			commandTextArea.setSelectionEnd(n + 1);
			if (i < n && !fileList.isSelectionEmpty())
				commandTextArea.replaceSelection((String) fileList.getSelectedValue());
		}
		this.setVisible(false);
	}

}
