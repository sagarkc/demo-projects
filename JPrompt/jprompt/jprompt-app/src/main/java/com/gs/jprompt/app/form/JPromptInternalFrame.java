package com.gs.jprompt.app.form;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.colorchooser.ColorChooserComponentFactory;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import com.gs.jprompt.JPromptImageConstants;
import com.gs.jprompt.app.components.AutoCompleteWindow;
import com.gs.jprompt.app.components.ConsoleTextArea;
import com.gs.jprompt.app.task.CommandRunner;
import com.gs.jprompt.common.Command;
import com.gs.jprompt.common.CommandWindow;
import com.gs.jprompt.common.ConsoleAppearance;
import com.gs.jprompt.common.EnvironmentManager;
import com.gs.jprompt.common.JPromptContext;
import com.gs.utils.text.StringUtil;

public class JPromptInternalFrame extends JInternalFrame{

	private static final JPromptContext context = JPromptContext.getContext();
	
	
	private JFrame parentFrame;
	private FormListener formListener;
	private CommandRunner<String, Command> commandRunner;
	private EnvironmentManager environmentManager;
	private ConsoleAppearance consoleAppearance;
	private AutoCompleteWindow autoCompleteWindow;
	


	private int previousKeyCode = 0;
	private int previousSelection = 0;
	private int previousCommandIndex = 0;
	
    public JPromptInternalFrame(JFrame parentFrame) {
    	synchronized (this) {
    		setName("JPromptInternalFrame_" + hashCode());
        	environmentManager = new EnvironmentManager();
        	this.parentFrame = parentFrame;
        	if(null == this.parentFrame){
        		//TODO: Throw exception
        	}
            
        	consoleAppearance = new ConsoleAppearance();
        	consoleAppearance.setFont(context.getDefaultEditorFont());
        	consoleAppearance.setFgColor(Color.WHITE);
        	consoleAppearance.setBgColor(Color.BLACK);

        	initComponents();
            
            CommandWindow commandWindow = new CommandWindow();
            commandWindow.setTitle(getTitle());
            commandWindow.setInternalFrame(this);
            context.getCommandWindowMap().put(getName(), commandWindow);
            
		}
    	
    }

    /* (non-Javadoc)
     * @see java.lang.Object#finalize()
     */
    @Override
    protected void finalize() throws Throwable {
    	super.finalize();
    }
    
	private void initComponents() {
        GridBagConstraints gridBagConstraints;

        promptAreaPopupMenu = new JPopupMenu();
        fontMenuItem = new JMenuItem();
        colorMenuItem = new JMenuItem();
        jSeparator4 = new JPopupMenu.Separator();
        copyMenuItem = new JMenuItem();
        saveMenuItem = new JMenuItem();
        jSeparator5 = new JPopupMenu.Separator();
        clearMenuItem = new JMenuItem();
        jSeparator6 = new JPopupMenu.Separator();
        startPauseMenuItem = new JMenuItem();
        stopMenuItem = new JMenuItem();
        jSeparator7 = new JPopupMenu.Separator();
        showToolbarCheckBoxMenuItem = new JCheckBoxMenuItem();
        promptToolBar = new JToolBar();
        fontButton = new JButton();
        colorButton = new JButton();
        jSeparator1 = new JToolBar.Separator();
        copyButton = new JButton();
        saveButton = new JButton();
        jSeparator2 = new JToolBar.Separator();
        clearButton = new JButton();
        jSeparator3 = new JToolBar.Separator();
        startPauseButton = new JButton();
        stopButton = new JButton();
        jScrollPane1 = new JScrollPane();
        promptTextArea = new ConsoleTextArea(parentFrame, context.consoleStartupDirectory);
        editModeCheckBox = new JCheckBox();

        autoCompleteWindow = new AutoCompleteWindow(promptTextArea);
        
        formListener = new FormListener();

        setFrameIcon(JPromptImageConstants.JPROMPT_FRAME_ICON);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle(context.getResourceBundle().getString("JPromptInternalFrame.title")); // NOI18N
        
        
        
        promptAreaPopupMenu.setName("promptAreaPopupMenu"); // NOI18N

        fontMenuItem.setText(context.getResourceBundle().getString("JPromptInternalFrame.fontMenuItem.text")); // NOI18N
        fontMenuItem.setIcon(new ImageIcon(getClass().getResource("/images/font_size.png")));
        fontMenuItem.setName("fontMenuItem"); // NOI18N
        fontMenuItem.addActionListener(formListener);
        promptAreaPopupMenu.add(fontMenuItem);
        
        colorMenuItem.setText(context.getResourceBundle().getString("JPromptInternalFrame.colorMenuItem.text")); // NOI18N
        colorMenuItem.setIcon(new ImageIcon(getClass().getResource("/images/color_swatch.png")));
        colorMenuItem.setName("colorMenuItem"); // NOI18N
        colorMenuItem.addActionListener(formListener);
        promptAreaPopupMenu.add(colorMenuItem);

        jSeparator4.setName("jSeparator4"); // NOI18N
        promptAreaPopupMenu.add(jSeparator4);

        copyMenuItem.setText(context.getResourceBundle().getString("JPromptInternalFrame.copyMenuItem.text")); // NOI18N
        copyMenuItem.setName("copyMenuItem"); // NOI18N
        copyMenuItem.setIcon(new ImageIcon(getClass().getResource("/images/copy_edit.gif")));
        copyMenuItem.addActionListener(formListener);
        promptAreaPopupMenu.add(copyMenuItem);

        saveMenuItem.setText(context.getResourceBundle().getString("JPromptInternalFrame.saveMenuItem.text")); // NOI18N
        saveMenuItem.setIcon(new ImageIcon(getClass().getResource("/images/save_edit.gif")));
        saveMenuItem.setName("saveMenuItem"); // NOI18N
        saveMenuItem.addActionListener(formListener);
        promptAreaPopupMenu.add(saveMenuItem);

        jSeparator5.setName("jSeparator5"); // NOI18N
        promptAreaPopupMenu.add(jSeparator5);

        clearMenuItem.setText(context.getResourceBundle().getString("JPromptInternalFrame.clearMenuItem.text")); // NOI18N
        clearMenuItem.setIcon(new ImageIcon(getClass().getResource("/images/edit-clear.png")));
        clearMenuItem.setName("clearMenuItem"); // NOI18N
        clearMenuItem.addActionListener(formListener);
        promptAreaPopupMenu.add(clearMenuItem);

        jSeparator6.setName("jSeparator6"); // NOI18N
        promptAreaPopupMenu.add(jSeparator6);

        startPauseMenuItem.setText(context.getResourceBundle().getString("JPromptInternalFrame.startPauseMenuItem.text")); // NOI18N
        startPauseMenuItem.setIcon(JPromptImageConstants.START_EXECUTION_ICON);
        startPauseMenuItem.setName("startPauseMenuItem"); // NOI18N
        startPauseMenuItem.addActionListener(formListener);
        promptAreaPopupMenu.add(startPauseMenuItem);

        stopMenuItem.setText(context.getResourceBundle().getString("JPromptInternalFrame.stopMenuItem.text")); // NOI18N
        stopMenuItem.setIcon(new ImageIcon(getClass().getResource("/images/nav_stop.gif")));
        stopMenuItem.setName("stopMenuItem"); // NOI18N
        stopMenuItem.addActionListener(formListener);
        promptAreaPopupMenu.add(stopMenuItem);

        jSeparator7.setName("jSeparator7"); // NOI18N
        promptAreaPopupMenu.add(jSeparator7);

        showToolbarCheckBoxMenuItem.setSelected(true);
        showToolbarCheckBoxMenuItem.setText(context.getResourceBundle().getString("JPromptInternalFrame.showToolbarCheckBoxMenuItem.text")); // NOI18N
        showToolbarCheckBoxMenuItem.setName("showToolbarCheckBoxMenuItem"); // NOI18N
        showToolbarCheckBoxMenuItem.addActionListener(formListener);
        promptAreaPopupMenu.add(showToolbarCheckBoxMenuItem);

        
        addInternalFrameListener(formListener);
        addFocusListener(formListener);
        getContentPane().setLayout(new GridBagLayout());

        promptToolBar.setFloatable(false);
        promptToolBar.setRollover(true);
        promptToolBar.setName("promptToolBar"); // NOI18N

        fontButton.setText(context.getResourceBundle().getString("JPromptInternalFrame.appearanceButton.text")); // NOI18N
        fontButton.setIcon(new ImageIcon(getClass().getResource("/images/font_size.png")));
        fontButton.setFocusable(false);
        fontButton.setHorizontalTextPosition(SwingConstants.CENTER);
        fontButton.setName("refreshButton"); // NOI18N
        fontButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        fontButton.addActionListener(formListener);
        promptToolBar.add(fontButton);

        colorButton.setText(""); // NOI18N
        colorButton.setIcon(new ImageIcon(getClass().getResource("/images/color_swatch.png")));
        colorButton.setFocusable(false);
        colorButton.setHorizontalTextPosition(SwingConstants.CENTER);
        colorButton.setName("refreshButton"); // NOI18N
        colorButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        colorButton.addActionListener(formListener);
        promptToolBar.add(colorButton);
        
        jSeparator1.setName("jSeparator1"); // NOI18N
        promptToolBar.add(jSeparator1);

        copyButton.setText(context.getResourceBundle().getString("JPromptInternalFrame.copyButton.text")); // NOI18N
        copyButton.setIcon(new ImageIcon(getClass().getResource("/images/copy_edit.gif")));
        copyButton.setFocusable(false);
        copyButton.setHorizontalTextPosition(SwingConstants.CENTER);
        copyButton.setName("copyButton"); // NOI18N
        copyButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        copyButton.addActionListener(formListener);
        promptToolBar.add(copyButton);

        saveButton.setText(context.getResourceBundle().getString("JPromptInternalFrame.saveButton.text")); // NOI18N
        saveButton.setIcon(new ImageIcon(getClass().getResource("/images/save_edit.gif"))); // NOI18N
        saveButton.setFocusable(false);
        saveButton.setHorizontalTextPosition(SwingConstants.CENTER);
        saveButton.setName("saveButton"); // NOI18N
        saveButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        saveButton.addActionListener(formListener);
        promptToolBar.add(saveButton);

        jSeparator2.setName("jSeparator2"); // NOI18N
        promptToolBar.add(jSeparator2);

        clearButton.setText(context.getResourceBundle().getString("JPromptInternalFrame.clearButton.text")); // NOI18N
        clearButton.setIcon(new ImageIcon(getClass().getResource("/images/edit-clear.png")));
        clearButton.setFocusable(false);
        clearButton.setHorizontalTextPosition(SwingConstants.CENTER);
        clearButton.setName("clearButton"); // NOI18N
        clearButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        clearButton.addActionListener(formListener);
        promptToolBar.add(clearButton);

        jSeparator3.setName("jSeparator3"); // NOI18N
        promptToolBar.add(jSeparator3);

        startPauseButton.setText(context.getResourceBundle().getString("JPromptInternalFrame.startPauseButton.text")); // NOI18N
        startPauseButton.setIcon(JPromptImageConstants.START_EXECUTION_ICON);
        startPauseButton.setFocusable(false);
        startPauseButton.setHorizontalTextPosition(SwingConstants.CENTER);
        startPauseButton.setName("startPauseButton"); // NOI18N
        startPauseButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        startPauseButton.addActionListener(formListener);
        promptToolBar.add(startPauseButton);

        stopButton.setText(context.getResourceBundle().getString("JPromptInternalFrame.stopButton.text")); // NOI18N
        stopButton.setIcon(new ImageIcon(getClass().getResource("/images/nav_stop.gif")));
        stopButton.setFocusable(false);
        stopButton.setHorizontalTextPosition(SwingConstants.CENTER);
        stopButton.setName("stopButton"); // NOI18N
        stopButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        stopButton.addActionListener(formListener);
        promptToolBar.add(stopButton);
        
        promptToolBar.add(new JToolBar.Separator());
        editModeCheckBox.setText(context.getResourceBundle().getString("JPromptInternalFrame.editModeCheckBox.text")); // NOI18N
        editModeCheckBox.setFocusable(false);
        editModeCheckBox.setHorizontalTextPosition(SwingConstants.RIGHT);
        editModeCheckBox.setName("editModeCheckBox"); // NOI18N
        editModeCheckBox.setVerticalTextPosition(SwingConstants.CENTER);
        editModeCheckBox.addActionListener(formListener);
        promptToolBar.add(editModeCheckBox);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 2, 0);
        getContentPane().add(promptToolBar, gridBagConstraints);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        promptTextArea.setColumns(20);
        promptTextArea.setRows(10);
        promptTextArea.setTabSize(4);
        promptTextArea.setComponentPopupMenu(promptAreaPopupMenu);
        promptTextArea.setName("promptTextArea"); // NOI18N
        promptTextArea.addCaretListener(formListener);
        promptTextArea.addFocusListener(formListener);
        promptTextArea.addInputMethodListener(formListener);
        promptTextArea.addPropertyChangeListener(formListener);
        promptTextArea.addKeyListener(formListener);
        promptTextArea.addVetoableChangeListener(formListener);
        promptTextArea.addMouseListener(formListener);
        promptTextArea.setAppearance(consoleAppearance);
        jScrollPane1.setViewportView(promptTextArea);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jScrollPane1, gridBagConstraints);

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements ActionListener, FocusListener, InputMethodListener, 
    		KeyListener, PropertyChangeListener, VetoableChangeListener, CaretListener, 
    		InternalFrameListener, MouseListener {
        
    	FormListener() {}
        
    	public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == fontButton) {
                JPromptInternalFrame.this.fontButtonActionPerformed(evt);
            }
            else if (evt.getSource() == colorButton) {
                JPromptInternalFrame.this.colorButtonActionPerformed(evt);
            }
            else if (evt.getSource() == copyButton) {
                JPromptInternalFrame.this.copyButtonActionPerformed(evt);
            }
            else if (evt.getSource() == saveButton) {
                JPromptInternalFrame.this.saveButtonActionPerformed(evt);
            }
            else if (evt.getSource() == clearButton) {
                JPromptInternalFrame.this.clearButtonActionPerformed(evt);
            }
            else if (evt.getSource() == startPauseButton) {
                JPromptInternalFrame.this.startPauseButtonActionPerformed(evt);
            }
            else if (evt.getSource() == stopButton) {
                JPromptInternalFrame.this.stopButtonActionPerformed(evt);
            }
            else if (evt.getSource() == fontMenuItem) {
                JPromptInternalFrame.this.fontButtonActionPerformed(evt);
            }
            else if (evt.getSource() == colorButton) {
                JPromptInternalFrame.this.colorButtonActionPerformed(evt);
            }
            else if (evt.getSource() == copyMenuItem) {
                JPromptInternalFrame.this.copyMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == saveMenuItem) {
                JPromptInternalFrame.this.saveMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == clearMenuItem) {
                JPromptInternalFrame.this.clearMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == startPauseMenuItem) {
                JPromptInternalFrame.this.startPauseMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == stopMenuItem) {
                JPromptInternalFrame.this.stopMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == showToolbarCheckBoxMenuItem) {
                JPromptInternalFrame.this.showToolbarCheckBoxMenuItemActionPerformed(evt);
            }
        }

        public void focusGained(FocusEvent evt) {
            if (evt.getSource() == promptTextArea) {
                JPromptInternalFrame.this.promptTextAreaFocusGained(evt);
            }
            else if (evt.getSource() == JPromptInternalFrame.this) {
                JPromptInternalFrame.this.formFocusGained(evt);
            }
        }

        public void focusLost(FocusEvent evt) {
            if (evt.getSource() == promptTextArea) {
                JPromptInternalFrame.this.promptTextAreaFocusLost(evt);
            }
            else if (evt.getSource() == JPromptInternalFrame.this) {
                JPromptInternalFrame.this.formFocusLost(evt);
            }
        }

        public void caretPositionChanged(InputMethodEvent evt) {
            if (evt.getSource() == promptTextArea) {
                JPromptInternalFrame.this.promptTextAreaCaretPositionChanged(evt);
            }
        }

        public void inputMethodTextChanged(InputMethodEvent evt) {
            if (evt.getSource() == promptTextArea) {
                JPromptInternalFrame.this.promptTextAreaInputMethodTextChanged(evt);
            }
        }

        public void keyPressed(KeyEvent evt) {
            if (evt.getSource() == promptTextArea) {
                JPromptInternalFrame.this.promptTextAreaKeyPressed(evt);
            }
        }

        public void keyReleased(KeyEvent evt) {
            if (evt.getSource() == promptTextArea) {
                JPromptInternalFrame.this.promptTextAreaKeyReleased(evt);
            }
        }

        public void keyTyped(KeyEvent evt) {
            if (evt.getSource() == promptTextArea) {
                JPromptInternalFrame.this.promptTextAreaKeyTyped(evt);
            }
        }

        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getSource() == promptTextArea) {
                JPromptInternalFrame.this.promptTextAreaPropertyChange(evt);
            } else if (evt.getSource() == commandRunner) {
                JPromptInternalFrame.this.commandRunnerPropertyChange(evt);
            }
            
        }

        public void vetoableChange(PropertyChangeEvent evt)throws PropertyVetoException {
            if (evt.getSource() == promptTextArea) {
                JPromptInternalFrame.this.promptTextAreaVetoableChange(evt);
            }
        }

        public void caretUpdate(CaretEvent evt) {
        	if (evt.getSource() instanceof JTextArea) {
        		JPromptInternalFrame.this.textAreaCaretUpdate(evt);
        	}
            if (evt.getSource() == promptTextArea) {
                JPromptInternalFrame.this.promptTextAreaCaretUpdate(evt);
            }
        }

        public void internalFrameActivated(InternalFrameEvent evt) {
            if (evt.getSource() == JPromptInternalFrame.this) {
                JPromptInternalFrame.this.formInternalFrameActivated(evt);
            }
        }

        public void internalFrameClosed(InternalFrameEvent evt) {
            if (evt.getSource() == JPromptInternalFrame.this) {
                JPromptInternalFrame.this.formInternalFrameClosed(evt);
            }
        }

        public void internalFrameClosing(InternalFrameEvent evt) {
            if (evt.getSource() == JPromptInternalFrame.this) {
                JPromptInternalFrame.this.formInternalFrameClosing(evt);
            }
        }

        public void internalFrameDeactivated(InternalFrameEvent evt) {
            if (evt.getSource() == JPromptInternalFrame.this) {
                JPromptInternalFrame.this.formInternalFrameDeactivated(evt);
            }
        }

        public void internalFrameDeiconified(InternalFrameEvent evt) {
            if (evt.getSource() == JPromptInternalFrame.this) {
                JPromptInternalFrame.this.formInternalFrameDeiconified(evt);
            }
        }

        public void internalFrameIconified(InternalFrameEvent evt) {
            if (evt.getSource() == JPromptInternalFrame.this) {
                JPromptInternalFrame.this.formInternalFrameIconified(evt);
            }
        }

        public void internalFrameOpened(InternalFrameEvent evt) {
            if (evt.getSource() == JPromptInternalFrame.this) {
                JPromptInternalFrame.this.formInternalFrameOpened(evt);
            }
        }

		public void mouseClicked(MouseEvent evt) {
			if (evt.getSource() == promptTextArea) {
                autoCompleteWindow.setVisible(false);
            }
		}

		public void mousePressed(MouseEvent evt) {
			
		}

		public void mouseReleased(MouseEvent evt) {
			// TODO Auto-generated method stub
			
		}

		public void mouseEntered(MouseEvent evt) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent evt) {
			// TODO Auto-generated method stub
			
		}
    }

    private void fontButtonActionPerformed(ActionEvent evt) {
        FontChooserDialog fd = new FontChooserDialog(parentFrame, true, consoleAppearance.getFont());
        int opt = fd.showFontChooserDialog();
        if(FontChooserDialog.OK_OPTION == opt){
        	Font font = fd.getSelectedFont();
        	//alpha clinin, jaynagar, dr saab er barir kache | kidney
        	if(null != font){
        		consoleAppearance.setFont(font);
        		promptTextArea.setAppearance(consoleAppearance);
        	}
        }
    }
    
    private void colorButtonActionPerformed(ActionEvent evt) {
    	ConsoleColorChooserDialog ccd = new ConsoleColorChooserDialog(parentFrame, true, 
    			consoleAppearance.getFgColor(), consoleAppearance.getBgColor());
    	int opt  = ccd.showFontChooserDialog();
    	if(ConsoleColorChooserDialog.OK_OPTION == opt){
    		if(null != ccd.getSelectedFGColor()){
    			consoleAppearance.setFgColor(ccd.getSelectedFGColor());
    		}
    		if(null != ccd.getSelectedBGColor()){
    			consoleAppearance.setBgColor(ccd.getSelectedBGColor());
    		}
    		promptTextArea.setAppearance(consoleAppearance);
    	}
    }

    public void textAreaCaretUpdate(CaretEvent evt) {
    	JTextArea editArea = (JTextArea) evt.getSource();
    	int linenum = 1;
		int columnnum = 1;
		try {
			
			int caretpos = editArea.getCaretPosition();
			linenum = editArea.getLineOfOffset(caretpos);
			columnnum = caretpos - editArea.getLineStartOffset(linenum);
			linenum += 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		updateCaretStatus(linenum, columnnum);
	}


	private void updateCaretStatus(int linenum, int columnnum) {
		((JPromptMainFrame)parentFrame).updateCaretStatus(linenum, columnnum);
	}


	private void copyButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void saveButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void clearButtonActionPerformed(ActionEvent evt) {
        promptTextArea.clearScreen();
    }

    private void startPauseButtonActionPerformed(ActionEvent evt) {
        executeCommand(null);
    }

    private void stopButtonActionPerformed(ActionEvent evt) {
        if(null != commandRunner){
        	commandRunner.stop();
        }
    }

    private void promptTextAreaKeyPressed(KeyEvent evt) {
    	ConsoleTextArea editArea = (ConsoleTextArea) evt.getSource();
    	if(null == editArea)
    		return;
    	previousKeyCode = evt.getKeyCode();
    	if(evt.isControlDown()){
    		if(KeyEvent.VK_ENTER == evt.getKeyCode()){
            	
            } 
    		else if(KeyEvent.VK_SPACE == evt.getKeyCode()){
        		promptTextArea.requestFocus();
        		autoCompleteWindow.showWindow();
        		evt.consume();
        	}
    		else if(KeyEvent.VK_C == evt.getKeyCode()){
    			commandRunner.stop();
    		}
    	} else {
    		if(KeyEvent.VK_ENTER == evt.getKeyCode()){
    			if(StringUtil.hasValidContent(editArea.getCommandLine()))
    				editArea.getCommandHistory().add(editArea.getCommandLine());
            	executeCommand(evt);
            }
    		else if(KeyEvent.VK_BACK_SPACE == evt.getKeyCode()){
            	int start = editArea.getSelectionStart();
            	int end = editArea.getSelectionEnd();
            	if(start != end){
            		editArea.updateSelection();
                	editArea.rePositionCaret();
            	}
            } 
    		else if(KeyEvent.VK_TAB == evt.getKeyCode()){
    			String fileName = getDirName();
    			if(StringUtil.hasValidContent(fileName)){
    				editArea.replaceLastWord(fileName);
    			}
    			evt.consume();
    		}
    		else if(KeyEvent.VK_UP == evt.getKeyCode()){
    			rotateCommandHistory(true, evt);
    		}
    		else if(KeyEvent.VK_DOWN == evt.getKeyCode()){
    			rotateCommandHistory(false, evt);
    		}
    		else if(KeyEvent.VK_LEFT != evt.getKeyCode()
            		&& KeyEvent.VK_RIGHT != evt.getKeyCode()
            		&& KeyEvent.VK_PAGE_UP != evt.getKeyCode()
            		&& KeyEvent.VK_PAGE_DOWN != evt.getKeyCode()
            		&& KeyEvent.VK_END != evt.getKeyCode()
            		&& KeyEvent.VK_HOME != evt.getKeyCode()){
            	editArea.rePositionCaret();
            } 
    	}
    	
        
    }
    
    public void rotateCommandHistory(boolean up, KeyEvent e){
		if((KeyEvent.VK_UP == previousKeyCode || KeyEvent.VK_DOWN == previousKeyCode)
				&& promptTextArea.getCaretPosition() >= promptTextArea.getEndPromptOffset()){
			String cmd = promptTextArea.getCommandHistory().getCurrentItem();
			
			if(StringUtil.hasValidContent(cmd)){
				promptTextArea.replaceRange(cmd, 
						promptTextArea.getEndPromptOffset(), 
						promptTextArea.getCommandLine().length() + promptTextArea.getEndPromptOffset());
				
				e.consume();
			}
			previousCommandIndex = promptTextArea.getCommandHistory().getCurrentIndex();
			if(KeyEvent.VK_UP == previousKeyCode){
				if(previousCommandIndex > 0)
					previousCommandIndex--;
			} else {
				if(previousCommandIndex < promptTextArea.getCommandHistory().size()-1)
					previousCommandIndex++;
			}
			promptTextArea.getCommandHistory().setCurrentIndex(previousCommandIndex);
		} 
		else if(KeyEvent.VK_UP != previousKeyCode && KeyEvent.VK_DOWN != previousKeyCode) {
			updateHistoryPointer();
			previousCommandIndex = promptTextArea.getCommandHistory().getCurrentIndex();
		}
	}
	
	public void updateHistoryPointer(){
		promptTextArea.getCommandHistory().setCurrentIndex(
				(promptTextArea.getCommandHistory().size() > 0) 
					? promptTextArea.getCommandHistory().size()-1
					: 0
				);
	}

    private String getDirName(){
    	String dirName = promptTextArea.getWorkingDirectory();
		if(!StringUtil.hasValidContent(dirName)){
			return "";
		}
		File dir = new File(dirName);
		if(!dir.exists())
			return "";
		
		File[] files = dir.listFiles();
		List<String> fileNames = new ArrayList<String>();
		if(null != files && files.length > 0){
			for (File file : files) {
				if(file.exists()){
					fileNames.add(file.getName());
				}
			}
		} else {
			return "";
		}
		
		if(0 != previousKeyCode){
			if(KeyEvent.VK_TAB == previousKeyCode){
				previousSelection++;
			}
		}
		if(previousSelection < fileNames.size())
			return fileNames.get(previousSelection);
		return "";
    }

	/**
	 * Execute command.
	 */
	public void executeCommand(KeyEvent e) {
		final Command command;
		final String commandLine = promptTextArea.getCommandLine();
		
		
		
		if(StringUtil.hasValidContent(commandLine)){
			if(commandLine.equalsIgnoreCase("cls")
					|| commandLine.equalsIgnoreCase("clear")){
				promptTextArea.clearScreen();
				promptTextArea.updateEndPromptOffset();
				promptTextArea.rePositionCaret();
				if(null != e){
					e.consume();
				}
				return;
			}
			
			command = new Command(commandLine);

			synchronized (this) {
				promptTextArea.setEditable(false);
				commandRunner = new CommandRunner<String, Command>(this, command, environmentManager);
				commandRunner.addPropertyChangeListener(formListener);
				commandRunner.setConsoleTextArea(promptTextArea);
				commandRunner.execute();
			}
		} else {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					promptTextArea.append(promptTextArea.getPrompt() + promptTextArea.getPromptCharacter());
					promptTextArea.setEditable(true);
					promptTextArea.updateEndPromptOffset();
				}
			});
		}
		
		
		
		
	}

    private void promptTextAreaKeyReleased(KeyEvent evt) {
    	
    }

    private void promptTextAreaKeyTyped(KeyEvent evt) {
    	
    	
    }

    private void promptTextAreaCaretUpdate(CaretEvent evt) {
        
    }

    private void promptTextAreaFocusGained(FocusEvent evt) {
        autoCompleteWindow.setVisible(false);
    }

    private void promptTextAreaFocusLost(FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void promptTextAreaCaretPositionChanged(InputMethodEvent evt) {
        // TODO add your handling code here:
    }

    private void promptTextAreaInputMethodTextChanged(InputMethodEvent evt) {
    	autoCompleteWindow.setVisible(false);
    }

    private void promptTextAreaPropertyChange(PropertyChangeEvent evt) {
        
    }

    private void promptTextAreaVetoableChange(PropertyChangeEvent evt)throws PropertyVetoException {
        // TODO add your handling code here:
    }

    private void refreshMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void copyMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void saveMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void clearMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void startPauseMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void stopMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void showToolbarCheckBoxMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void formFocusGained(FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void formFocusLost(FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void formInternalFrameActivated(InternalFrameEvent evt) {
        // TODO add your handling code here:
    }

    private void formInternalFrameClosed(InternalFrameEvent evt) {
        // TODO add your handling code here:
    }

    private void formInternalFrameClosing(InternalFrameEvent evt) {
    	context.getCommandWindowMap().remove(getName());
    }

    private void formInternalFrameDeactivated(InternalFrameEvent evt) {
        // TODO add your handling code here:
    }

    private void formInternalFrameDeiconified(InternalFrameEvent evt) {
        // TODO add your handling code here:
    }

    private void formInternalFrameIconified(InternalFrameEvent evt) {
        // TODO add your handling code here:
    }

    private void formInternalFrameOpened(InternalFrameEvent evt) {
        // TODO add your handling code here:
    }

	public void commandRunnerPropertyChange(PropertyChangeEvent evt) {
		String propertyName = evt.getPropertyName();
		if(CommandRunner.TASK_STATUS_DONE.equals(propertyName)){
			promptTextArea.setEditable(true);
			promptTextArea.rePositionCaret();
		}
		if(CommandRunner.TASK_STATUS_ABORT.equals(propertyName)){
			/*Object msg = evt.getNewValue();
			String txtMsg = "Transaction aborted";
			if(null != msg){
				txtMsg = msg.toString();
			}
			queryRunnerProgressBar.setIndeterminate(false);
			messageLabel.setText(txtMsg);
			queryLogTextArea.append("\n" + txtMsg);
			stopExecutionButton.setEnabled(false);
			stopExecutionMenuItem.setEnabled(false);
			
			runQueryButton.setEnabled(true);
			runQueryMenuItem.setEnabled(true);
			runAllButton.setEnabled(true);
			runAllMenuItem.setEnabled(true);
			runSelectedQueryButton.setEnabled(true);
			runSelectionMenuItem.setEnabled(true);*/
		}
		if(CommandRunner.PROPERTY_PROGRESS.equals(propertyName)){
			/*String type = evt.getNewValue().toString();
			String txtMsg = "Executing Query ...";
			queryLogTextArea.append("\n" + txtMsg);
			if(QueryExecutionTask.TASK_STATUS_START.equals(type)){
				queryResultTable.setModel(new DefaultTableModel());
				
				stopExecutionButton.setEnabled(true);
				stopExecutionMenuItem.setEnabled(true);
				
				runQueryButton.setEnabled(false);
				runQueryMenuItem.setEnabled(false);
				runAllButton.setEnabled(false);
				runAllMenuItem.setEnabled(false);
				runSelectedQueryButton.setEnabled(false);
				runSelectionMenuItem.setEnabled(false);
				
				queryRunnerProgressBar.setIndeterminate(true);
			} else {
				queryRunnerProgressBar.setIndeterminate(false);
			}
			messageLabel.setText(txtMsg);
			updateUI();*/
		}
		if(CommandRunner.TASK_STATUS_FAILED.equals(propertyName)){
			/*Object newValue = evt.getNewValue();
			String txtMsg = "Query execution Failed.";
			queryResultTable.setModel(new DefaultTableModel());
			if(null != newValue){
				if(newValue instanceof DbexException){
					txtMsg += " Reason:: " + ((DbexException)newValue).getExceptionMessage();
				}
			}
			
			Object oldValue = evt.getOldValue();
			if(null != oldValue){
				if(oldValue instanceof Long){
					messageLabel.setText(txtMsg + " [ Time taken : " + oldValue + "ms ]");
				}
			}
			queryLogTextArea.append("\n" + txtMsg);*/
		}
	}


    private JButton clearButton;
    private JMenuItem clearMenuItem;
    private JButton copyButton;
    private JMenuItem copyMenuItem;
    private JScrollPane jScrollPane1;
    private JToolBar.Separator jSeparator1;
    private JToolBar.Separator jSeparator2;
    private JToolBar.Separator jSeparator3;
    private JPopupMenu.Separator jSeparator4;
    private JPopupMenu.Separator jSeparator5;
    private JPopupMenu.Separator jSeparator6;
    private JPopupMenu.Separator jSeparator7;
    private JPopupMenu promptAreaPopupMenu;
    private ConsoleTextArea promptTextArea;
    private JToolBar promptToolBar;
    private JButton fontButton;
    private JButton colorButton;
    private JMenuItem fontMenuItem;
    private JMenuItem colorMenuItem;
    private JButton saveButton;
    private JMenuItem saveMenuItem;
    private JCheckBoxMenuItem showToolbarCheckBoxMenuItem;
    private JButton startPauseButton;
    private JMenuItem startPauseMenuItem;
    private JButton stopButton;
    private JMenuItem stopMenuItem;
    private JCheckBox editModeCheckBox;

}
