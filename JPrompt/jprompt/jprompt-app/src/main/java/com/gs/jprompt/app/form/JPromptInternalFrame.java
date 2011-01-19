package com.gs.jprompt.app.form;

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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class JPromptInternalFrame extends JInternalFrame {

    public JPromptInternalFrame() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        promptAreaPopupMenu = new JPopupMenu();
        refreshMenuItem = new JMenuItem();
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
        refreshButton = new JButton();
        jSeparator1 = new JToolBar.Separator();
        copyButton = new JButton();
        saveButton = new JButton();
        jSeparator2 = new JToolBar.Separator();
        clearButton = new JButton();
        jSeparator3 = new JToolBar.Separator();
        startPauseButton = new JButton();
        stopButton = new JButton();
        jScrollPane1 = new JScrollPane();
        promptTextArea = new JTextArea();

        FormListener formListener = new FormListener();

        promptAreaPopupMenu.setName("promptAreaPopupMenu"); // NOI18N

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/gs/jprompt/app/form/Bundle"); // NOI18N
        refreshMenuItem.setText(bundle.getString("JPromptInternalFrame.refreshMenuItem.text")); // NOI18N
        refreshMenuItem.setName("refreshMenuItem"); // NOI18N
        refreshMenuItem.addActionListener(formListener);
        promptAreaPopupMenu.add(refreshMenuItem);

        jSeparator4.setName("jSeparator4"); // NOI18N
        promptAreaPopupMenu.add(jSeparator4);

        copyMenuItem.setText(bundle.getString("JPromptInternalFrame.copyMenuItem.text")); // NOI18N
        copyMenuItem.setName("copyMenuItem"); // NOI18N
        copyMenuItem.addActionListener(formListener);
        promptAreaPopupMenu.add(copyMenuItem);

        saveMenuItem.setText(bundle.getString("JPromptInternalFrame.saveMenuItem.text")); // NOI18N
        saveMenuItem.setName("saveMenuItem"); // NOI18N
        saveMenuItem.addActionListener(formListener);
        promptAreaPopupMenu.add(saveMenuItem);

        jSeparator5.setName("jSeparator5"); // NOI18N
        promptAreaPopupMenu.add(jSeparator5);

        clearMenuItem.setText(bundle.getString("JPromptInternalFrame.clearMenuItem.text")); // NOI18N
        clearMenuItem.setName("clearMenuItem"); // NOI18N
        clearMenuItem.addActionListener(formListener);
        promptAreaPopupMenu.add(clearMenuItem);

        jSeparator6.setName("jSeparator6"); // NOI18N
        promptAreaPopupMenu.add(jSeparator6);

        startPauseMenuItem.setText(bundle.getString("JPromptInternalFrame.startPauseMenuItem.text")); // NOI18N
        startPauseMenuItem.setName("startPauseMenuItem"); // NOI18N
        startPauseMenuItem.addActionListener(formListener);
        promptAreaPopupMenu.add(startPauseMenuItem);

        stopMenuItem.setText(bundle.getString("JPromptInternalFrame.stopMenuItem.text")); // NOI18N
        stopMenuItem.setName("stopMenuItem"); // NOI18N
        stopMenuItem.addActionListener(formListener);
        promptAreaPopupMenu.add(stopMenuItem);

        jSeparator7.setName("jSeparator7"); // NOI18N
        promptAreaPopupMenu.add(jSeparator7);

        showToolbarCheckBoxMenuItem.setSelected(true);
        showToolbarCheckBoxMenuItem.setText(bundle.getString("JPromptInternalFrame.showToolbarCheckBoxMenuItem.text")); // NOI18N
        showToolbarCheckBoxMenuItem.setName("showToolbarCheckBoxMenuItem"); // NOI18N
        showToolbarCheckBoxMenuItem.addActionListener(formListener);
        promptAreaPopupMenu.add(showToolbarCheckBoxMenuItem);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle(bundle.getString("JPromptInternalFrame.title")); // NOI18N
        setName("Form"); // NOI18N
        addInternalFrameListener(formListener);
        addFocusListener(formListener);
        getContentPane().setLayout(new GridBagLayout());

        promptToolBar.setFloatable(false);
        promptToolBar.setRollover(true);
        promptToolBar.setName("promptToolBar"); // NOI18N

        refreshButton.setText(bundle.getString("JPromptInternalFrame.refreshButton.text")); // NOI18N
        refreshButton.setFocusable(false);
        refreshButton.setHorizontalTextPosition(SwingConstants.CENTER);
        refreshButton.setName("refreshButton"); // NOI18N
        refreshButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        refreshButton.addActionListener(formListener);
        promptToolBar.add(refreshButton);

        jSeparator1.setName("jSeparator1"); // NOI18N
        promptToolBar.add(jSeparator1);

        copyButton.setText(bundle.getString("JPromptInternalFrame.copyButton.text")); // NOI18N
        copyButton.setFocusable(false);
        copyButton.setHorizontalTextPosition(SwingConstants.CENTER);
        copyButton.setName("copyButton"); // NOI18N
        copyButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        copyButton.addActionListener(formListener);
        promptToolBar.add(copyButton);

        saveButton.setText(bundle.getString("JPromptInternalFrame.saveButton.text")); // NOI18N
        saveButton.setIcon(new ImageIcon(getClass().getResource("/images/save_edit.gif"))); // NOI18N
        saveButton.setFocusable(false);
        saveButton.setHorizontalTextPosition(SwingConstants.CENTER);
        saveButton.setName("saveButton"); // NOI18N
        saveButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        saveButton.addActionListener(formListener);
        promptToolBar.add(saveButton);

        jSeparator2.setName("jSeparator2"); // NOI18N
        promptToolBar.add(jSeparator2);

        clearButton.setText(bundle.getString("JPromptInternalFrame.clearButton.text")); // NOI18N
        clearButton.setFocusable(false);
        clearButton.setHorizontalTextPosition(SwingConstants.CENTER);
        clearButton.setName("clearButton"); // NOI18N
        clearButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        clearButton.addActionListener(formListener);
        promptToolBar.add(clearButton);

        jSeparator3.setName("jSeparator3"); // NOI18N
        promptToolBar.add(jSeparator3);

        startPauseButton.setText(bundle.getString("JPromptInternalFrame.startPauseButton.text")); // NOI18N
        startPauseButton.setFocusable(false);
        startPauseButton.setHorizontalTextPosition(SwingConstants.CENTER);
        startPauseButton.setName("startPauseButton"); // NOI18N
        startPauseButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        startPauseButton.addActionListener(formListener);
        promptToolBar.add(startPauseButton);

        stopButton.setText(bundle.getString("JPromptInternalFrame.stopButton.text")); // NOI18N
        stopButton.setFocusable(false);
        stopButton.setHorizontalTextPosition(SwingConstants.CENTER);
        stopButton.setName("stopButton"); // NOI18N
        stopButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        stopButton.addActionListener(formListener);
        promptToolBar.add(stopButton);

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

    private class FormListener implements ActionListener, FocusListener, InputMethodListener, KeyListener, PropertyChangeListener, VetoableChangeListener, CaretListener, InternalFrameListener {
        FormListener() {}
        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == refreshButton) {
                JPromptInternalFrame.this.refreshButtonActionPerformed(evt);
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
            else if (evt.getSource() == refreshMenuItem) {
                JPromptInternalFrame.this.refreshMenuItemActionPerformed(evt);
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
            }
        }

        public void vetoableChange(PropertyChangeEvent evt)throws PropertyVetoException {
            if (evt.getSource() == promptTextArea) {
                JPromptInternalFrame.this.promptTextAreaVetoableChange(evt);
            }
        }

        public void caretUpdate(CaretEvent evt) {
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
    }

    private void refreshButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void copyButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void saveButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void clearButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void startPauseButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void stopButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void promptTextAreaKeyPressed(KeyEvent evt) {
        // TODO add your handling code here:
    }

    private void promptTextAreaKeyReleased(KeyEvent evt) {
        // TODO add your handling code here:
    }

    private void promptTextAreaKeyTyped(KeyEvent evt) {
        // TODO add your handling code here:
    }

    private void promptTextAreaCaretUpdate(CaretEvent evt) {
        // TODO add your handling code here:
    }

    private void promptTextAreaFocusGained(FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void promptTextAreaFocusLost(FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void promptTextAreaCaretPositionChanged(InputMethodEvent evt) {
        // TODO add your handling code here:
    }

    private void promptTextAreaInputMethodTextChanged(InputMethodEvent evt) {
        // TODO add your handling code here:
    }

    private void promptTextAreaPropertyChange(PropertyChangeEvent evt) {
        // TODO add your handling code here:
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
        // TODO add your handling code here:
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
    private JTextArea promptTextArea;
    private JToolBar promptToolBar;
    private JButton refreshButton;
    private JMenuItem refreshMenuItem;
    private JButton saveButton;
    private JMenuItem saveMenuItem;
    private JCheckBoxMenuItem showToolbarCheckBoxMenuItem;
    private JButton startPauseButton;
    private JMenuItem startPauseMenuItem;
    private JButton stopButton;
    private JMenuItem stopMenuItem;

}
