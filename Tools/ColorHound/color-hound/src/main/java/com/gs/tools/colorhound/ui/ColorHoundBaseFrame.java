/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.tools.colorhound.ui.ColorHoundBaseFrame
 * Date     : May 23, 2013
 */

package com.gs.tools.colorhound.ui;

import com.gs.tools.colorhound.event.ApplicationEventManager;
import com.gs.tools.colorhound.event.ColorGrabEvent;
import com.gs.tools.colorhound.event.ColorGrabListener;
import java.awt.event.KeyEvent;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class ColorHoundBaseFrame extends javax.swing.JFrame 
implements ColorGrabListener{

    private ApplicationEventManager eventManager 
            = ApplicationEventManager.getInstance();
    
    /** Creates new form ColorHoundBaseFrame */
    public ColorHoundBaseFrame() {
        initComponents();
        ImagePanel imagePanel = new ImagePanel(null);
        imagePanelScrollPane.setViewportView(imagePanel);
        eventManager.registerListener(ColorGrabEvent.class, this);
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        baseContentPanel = new javax.swing.JPanel();
        leftPanel = new javax.swing.JPanel();
        paletteToolBar = new javax.swing.JToolBar();
        addColorButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        paletteListComboBox = new javax.swing.JComboBox();
        editColorButton = new javax.swing.JButton();
        deleteColorButton = new javax.swing.JButton();
        paletteContentPanel = new javax.swing.JPanel();
        colorSourceTabbedPane = new javax.swing.JTabbedPane();
        imagePanel = new javax.swing.JPanel();
        imageToolBar = new javax.swing.JToolBar();
        openImageButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        imagePanelScrollPane = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jColorChooser1 = new javax.swing.JColorChooser();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        baseMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        hideMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        FormListener formListener = new FormListener();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(899, 497));
        setPreferredSize(new java.awt.Dimension(899, 497));
        addKeyListener(formListener);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/Message"); // NOI18N
        leftPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("lbl.palette.panel.header"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(153, 153, 255))); // NOI18N

        paletteToolBar.setFloatable(false);
        paletteToolBar.setRollover(true);

        addColorButton.setText("Add");
        addColorButton.setFocusable(false);
        addColorButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addColorButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        paletteToolBar.add(addColorButton);
        paletteToolBar.add(jSeparator2);

        paletteToolBar.add(paletteListComboBox);

        editColorButton.setText("Edit");
        editColorButton.setFocusable(false);
        editColorButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editColorButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        paletteToolBar.add(editColorButton);

        deleteColorButton.setText("Delete");
        deleteColorButton.setFocusable(false);
        deleteColorButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteColorButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteColorButton.addActionListener(formListener);
        paletteToolBar.add(deleteColorButton);

        paletteContentPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paletteToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
            .addComponent(paletteContentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addComponent(paletteToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paletteContentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        imageToolBar.setFloatable(false);
        imageToolBar.setRollover(true);

        openImageButton.setText("Open");
        openImageButton.setFocusable(false);
        openImageButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openImageButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        imageToolBar.add(openImageButton);

        clearButton.setText("Clear");
        clearButton.setFocusable(false);
        clearButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        clearButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        imageToolBar.add(clearButton);

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imageToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
            .addGroup(imagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagePanelScrollPane)
                .addContainerGap())
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanelLayout.createSequentialGroup()
                .addComponent(imageToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imagePanelScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                .addContainerGap())
        );

        colorSourceTabbedPane.addTab("Image", imagePanel);

        jButton3.setText("jButton3");

        jButton4.setText("jButton4");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jColorChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jColorChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        colorSourceTabbedPane.addTab("Color Chooser", jPanel3);

        javax.swing.GroupLayout baseContentPanelLayout = new javax.swing.GroupLayout(baseContentPanel);
        baseContentPanel.setLayout(baseContentPanelLayout);
        baseContentPanelLayout.setHorizontalGroup(
            baseContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(baseContentPanelLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(colorSourceTabbedPane))
        );
        baseContentPanelLayout.setVerticalGroup(
            baseContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(colorSourceTabbedPane)
        );

        getContentPane().add(baseContentPanel, java.awt.BorderLayout.CENTER);

        fileMenu.setText(bundle.getString("lbl.file.menu")); // NOI18N

        newMenuItem.setText(bundle.getString("lbl.new.menu.item")); // NOI18N
        fileMenu.add(newMenuItem);
        fileMenu.add(jSeparator1);

        hideMenuItem.setText(bundle.getString("lbl.hide.menu.item")); // NOI18N
        fileMenu.add(hideMenuItem);

        exitMenuItem.setText(bundle.getString("lbl.exit.menu.item")); // NOI18N
        fileMenu.add(exitMenuItem);

        baseMenuBar.add(fileMenu);

        jMenu2.setText("Edit");
        baseMenuBar.add(jMenu2);

        setJMenuBar(baseMenuBar);

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, java.awt.event.KeyListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == deleteColorButton) {
                ColorHoundBaseFrame.this.deleteColorButtonActionPerformed(evt);
            }
        }

        public void keyPressed(java.awt.event.KeyEvent evt) {
            if (evt.getSource() == ColorHoundBaseFrame.this) {
                ColorHoundBaseFrame.this.formKeyPressed(evt);
            }
        }

        public void keyReleased(java.awt.event.KeyEvent evt) {
            if (evt.getSource() == ColorHoundBaseFrame.this) {
                ColorHoundBaseFrame.this.formKeyReleased(evt);
            }
        }

        public void keyTyped(java.awt.event.KeyEvent evt) {
        }
    }// </editor-fold>//GEN-END:initComponents

    private void deleteColorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteColorButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteColorButtonActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        
    }//GEN-LAST:event_formKeyReleased

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addColorButton;
    private javax.swing.JPanel baseContentPanel;
    private javax.swing.JMenuBar baseMenuBar;
    private javax.swing.JButton clearButton;
    private javax.swing.JTabbedPane colorSourceTabbedPane;
    private javax.swing.JButton deleteColorButton;
    private javax.swing.JButton editColorButton;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem hideMenuItem;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JScrollPane imagePanelScrollPane;
    private javax.swing.JToolBar imageToolBar;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JMenuItem newMenuItem;
    private javax.swing.JButton openImageButton;
    private javax.swing.JPanel paletteContentPanel;
    private javax.swing.JComboBox paletteListComboBox;
    private javax.swing.JToolBar paletteToolBar;
    // End of variables declaration//GEN-END:variables

    public void colorGrabbed(ColorGrabEvent event) {
        System.out.println(event.getNewValue());
    }

    
    
}
