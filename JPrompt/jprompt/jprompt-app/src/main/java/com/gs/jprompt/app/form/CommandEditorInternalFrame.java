

package com.gs.jprompt.app.form;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import com.gs.jprompt.common.JPromptContext;


public class CommandEditorInternalFrame extends JInternalFrame {

	private static final JPromptContext context = JPromptContext.getContext();
	
    /** Creates new form CommandEditorInternalFrame */
    public CommandEditorInternalFrame() {
        initComponents();
    }

    
    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        cmdEditToolBar = new JToolBar();
        openButton = new JButton();
        jSeparator1 = new JToolBar.Separator();
        saveButton = new JButton();
        saveAsButton = new JButton();
        jSeparator2 = new JToolBar.Separator();
        runButton = new JButton();
        jSeparator3 = new JToolBar.Separator();
        lineWrapToggleButton = new JToggleButton();
        jScrollPane1 = new JScrollPane();
        commandEditorPane = new JEditorPane();

        FormListener formListener = new FormListener();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        ResourceBundle bundle = JPromptContext.getContext().getResourceBundle(); // NOI18N
        setTitle(bundle.getString("CommandEditorInternalFrame.title")); // NOI18N
        setMinimumSize(new Dimension(463, 246));
        setName("Form"); // NOI18N
        addInternalFrameListener(formListener);
        getContentPane().setLayout(new GridBagLayout());

        cmdEditToolBar.setFloatable(false);
        cmdEditToolBar.setRollover(true);
        cmdEditToolBar.setName("cmdEditToolBar"); // NOI18N

        openButton.setIcon(new ImageIcon(getClass().getResource("/images/folderopen.png"))); // NOI18N
        openButton.setText(bundle.getString("CommandEditorInternalFrame.openButton.text")); // NOI18N
        openButton.setFocusable(false);
        openButton.setHorizontalTextPosition(SwingConstants.CENTER);
        openButton.setName("openButton"); // NOI18N
        openButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        openButton.addActionListener(formListener);
        cmdEditToolBar.add(openButton);

        jSeparator1.setName("jSeparator1"); // NOI18N
        cmdEditToolBar.add(jSeparator1);

        saveButton.setIcon(new ImageIcon(getClass().getResource("/images/save_edit.gif"))); // NOI18N
        saveButton.setText(bundle.getString("CommandEditorInternalFrame.saveButton.text")); // NOI18N
        saveButton.setFocusable(false);
        saveButton.setHorizontalTextPosition(SwingConstants.CENTER);
        saveButton.setName("saveButton"); // NOI18N
        saveButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        saveButton.addActionListener(formListener);
        cmdEditToolBar.add(saveButton);

        saveAsButton.setIcon(new ImageIcon(getClass().getResource("/images/saveas_edit.gif"))); // NOI18N
        saveAsButton.setText(bundle.getString("CommandEditorInternalFrame.saveAsButton.text")); // NOI18N
        saveAsButton.setFocusable(false);
        saveAsButton.setHorizontalTextPosition(SwingConstants.CENTER);
        saveAsButton.setName("saveAsButton"); // NOI18N
        saveAsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        saveAsButton.addActionListener(formListener);
        cmdEditToolBar.add(saveAsButton);

        jSeparator2.setName("jSeparator2"); // NOI18N
        cmdEditToolBar.add(jSeparator2);

        runButton.setIcon(new ImageIcon(getClass().getResource("/images/new-prompt.png"))); // NOI18N
        runButton.setText(bundle.getString("CommandEditorInternalFrame.runButton.text")); // NOI18N
        runButton.setFocusable(false);
        runButton.setHorizontalTextPosition(SwingConstants.CENTER);
        runButton.setName("runButton"); // NOI18N
        runButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        runButton.addActionListener(formListener);
        cmdEditToolBar.add(runButton);

        jSeparator3.setName("jSeparator3"); // NOI18N
        cmdEditToolBar.add(jSeparator3);

        lineWrapToggleButton.setIcon(new ImageIcon(getClass().getResource("/images/system-restart.png"))); // NOI18N
        lineWrapToggleButton.setText(bundle.getString("CommandEditorInternalFrame.lineWrapToggleButton.text")); // NOI18N
        lineWrapToggleButton.setFocusable(false);
        lineWrapToggleButton.setHorizontalTextPosition(SwingConstants.CENTER);
        lineWrapToggleButton.setName("lineWrapToggleButton"); // NOI18N
        lineWrapToggleButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        lineWrapToggleButton.addActionListener(formListener);
        cmdEditToolBar.add(lineWrapToggleButton);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 2, 0);
        getContentPane().add(cmdEditToolBar, gridBagConstraints);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        commandEditorPane.setDoubleBuffered(true);
        commandEditorPane.setDragEnabled(true);
        commandEditorPane.setName("commandEditorPane"); // NOI18N
        commandEditorPane.addMouseMotionListener(formListener);
        commandEditorPane.addKeyListener(formListener);
        commandEditorPane.setFont(context.getDefaultEditorFont());
        jScrollPane1.setViewportView(commandEditorPane);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jScrollPane1, gridBagConstraints);

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements ActionListener, KeyListener, MouseMotionListener, InternalFrameListener {
        FormListener() {}
        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == openButton) {
                CommandEditorInternalFrame.this.openButtonActionPerformed(evt);
            }
            else if (evt.getSource() == saveButton) {
                CommandEditorInternalFrame.this.saveButtonActionPerformed(evt);
            }
            else if (evt.getSource() == saveAsButton) {
                CommandEditorInternalFrame.this.saveAsButtonActionPerformed(evt);
            }
            else if (evt.getSource() == runButton) {
                CommandEditorInternalFrame.this.runButtonActionPerformed(evt);
            }
            else if (evt.getSource() == lineWrapToggleButton) {
                CommandEditorInternalFrame.this.lineWrapToggleButtonActionPerformed(evt);
            }
        }

        public void keyPressed(KeyEvent evt) {
            if (evt.getSource() == commandEditorPane) {
                CommandEditorInternalFrame.this.commandEditorPaneKeyPressed(evt);
            }
        }

        public void keyReleased(KeyEvent evt) {
            if (evt.getSource() == commandEditorPane) {
                CommandEditorInternalFrame.this.commandEditorPaneKeyReleased(evt);
            }
        }

        public void keyTyped(KeyEvent evt) {
            if (evt.getSource() == commandEditorPane) {
                CommandEditorInternalFrame.this.commandEditorPaneKeyTyped(evt);
            }
        }

        public void mouseDragged(MouseEvent evt) {
            if (evt.getSource() == commandEditorPane) {
                CommandEditorInternalFrame.this.commandEditorPaneMouseDragged(evt);
            }
        }

        public void mouseMoved(MouseEvent evt) {
        }

        public void internalFrameActivated(InternalFrameEvent evt) {
            if (evt.getSource() == CommandEditorInternalFrame.this) {
                CommandEditorInternalFrame.this.formInternalFrameActivated(evt);
            }
        }

        public void internalFrameClosed(InternalFrameEvent evt) {
            if (evt.getSource() == CommandEditorInternalFrame.this) {
                CommandEditorInternalFrame.this.formInternalFrameClosed(evt);
            }
        }

        public void internalFrameClosing(InternalFrameEvent evt) {
            if (evt.getSource() == CommandEditorInternalFrame.this) {
                CommandEditorInternalFrame.this.formInternalFrameClosing(evt);
            }
        }

        public void internalFrameDeactivated(InternalFrameEvent evt) {
            if (evt.getSource() == CommandEditorInternalFrame.this) {
                CommandEditorInternalFrame.this.formInternalFrameDeactivated(evt);
            }
        }

        public void internalFrameDeiconified(InternalFrameEvent evt) {
            if (evt.getSource() == CommandEditorInternalFrame.this) {
                CommandEditorInternalFrame.this.formInternalFrameDeiconified(evt);
            }
        }

        public void internalFrameIconified(InternalFrameEvent evt) {
            if (evt.getSource() == CommandEditorInternalFrame.this) {
                CommandEditorInternalFrame.this.formInternalFrameIconified(evt);
            }
        }

        public void internalFrameOpened(InternalFrameEvent evt) {
            if (evt.getSource() == CommandEditorInternalFrame.this) {
                CommandEditorInternalFrame.this.formInternalFrameOpened(evt);
            }
        }
    }

    private void openButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void saveButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void saveAsButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void runButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void lineWrapToggleButtonActionPerformed(ActionEvent evt) {
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

    private void commandEditorPaneKeyPressed(KeyEvent evt) {
        // TODO add your handling code here:
    }

    private void commandEditorPaneKeyReleased(KeyEvent evt) {
        // TODO add your handling code here:
    }

    private void commandEditorPaneKeyTyped(KeyEvent evt) {
        // TODO add your handling code here:
    }

    private void commandEditorPaneMouseDragged(MouseEvent evt) {
        // TODO add your handling code here:
    }


    // Variables declaration 
    private JToolBar cmdEditToolBar;
    private JEditorPane commandEditorPane;
    private JScrollPane jScrollPane1;
    private JToolBar.Separator jSeparator1;
    private JToolBar.Separator jSeparator2;
    private JToolBar.Separator jSeparator3;
    private JToggleButton lineWrapToggleButton;
    private JButton openButton;
    private JButton runButton;
    private JButton saveAsButton;
    private JButton saveButton;
    // End of variables declaration

}
