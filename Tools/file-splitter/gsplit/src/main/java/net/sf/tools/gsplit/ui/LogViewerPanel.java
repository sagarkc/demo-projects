/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.tools.gsplit.ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import net.sf.tools.gsplit.SplitterConstants;
import org.apache.log4j.Logger;

/**
 *
 * @author SG1736
 */
public class LogViewerPanel extends javax.swing.JPanel {

    private static Logger logger = Logger.getLogger(LogViewerPanel.class);
    
    /**
     * Creates new form LogViewerPanel
     */
    public LogViewerPanel() {
        initComponents();
        readLogFile();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logViewerToolBar = new javax.swing.JToolBar();
        reloadLogButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        enableWrapToggleButton = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        logsTextArea = new javax.swing.JTextArea();

        FormListener formListener = new FormListener();

        setMinimumSize(new java.awt.Dimension(579, 346));
        setLayout(new java.awt.BorderLayout());

        logViewerToolBar.setFloatable(false);
        logViewerToolBar.setRollover(true);

        reloadLogButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reload_green.png"))); // NOI18N
        reloadLogButton.setText("Reload File");
        reloadLogButton.setToolTipText("Reload File");
        reloadLogButton.setFocusable(false);
        reloadLogButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        reloadLogButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        reloadLogButton.addActionListener(formListener);
        logViewerToolBar.add(reloadLogButton);
        logViewerToolBar.add(jSeparator1);

        enableWrapToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button-word-wrap.gif"))); // NOI18N
        enableWrapToggleButton.setText("Line Wrap");
        enableWrapToggleButton.setToolTipText("Line Wrap");
        enableWrapToggleButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        enableWrapToggleButton.setFocusable(false);
        enableWrapToggleButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        enableWrapToggleButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        enableWrapToggleButton.addActionListener(formListener);
        logViewerToolBar.add(enableWrapToggleButton);

        add(logViewerToolBar, java.awt.BorderLayout.PAGE_START);

        logsTextArea.setEditable(false);
        logsTextArea.setBackground(new java.awt.Color(231, 232, 232));
        logsTextArea.setColumns(20);
        logsTextArea.setRows(5);
        logsTextArea.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(logsTextArea);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == reloadLogButton) {
                LogViewerPanel.this.reloadLogButtonActionPerformed(evt);
            }
            else if (evt.getSource() == enableWrapToggleButton) {
                LogViewerPanel.this.enableWrapToggleButtonActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void reloadLogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadLogButtonActionPerformed
        logsTextArea.setText("");
        readLogFile();
    }//GEN-LAST:event_reloadLogButtonActionPerformed

    private void enableWrapToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enableWrapToggleButtonActionPerformed
        if(!enableWrapToggleButton.isSelected()){
            logsTextArea.setLineWrap(false);
        } else {
            logsTextArea.setLineWrap(true);
        }
    }//GEN-LAST:event_enableWrapToggleButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton enableWrapToggleButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar logViewerToolBar;
    private javax.swing.JTextArea logsTextArea;
    private javax.swing.JButton reloadLogButton;
    // End of variables declaration//GEN-END:variables

    private void readLogFile() {
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(SplitterConstants.LOG_FILE_NAME));
            String line = "";
            while((line = reader.readLine()) != null){
                logsTextArea.append(line + System.getProperty("line.separator"));
            }
            logsTextArea.updateUI();
        } catch(Exception ex){
            logger.error("Cannot read from file: ", ex);
        } finally {
            if(null != reader){
                try {
                    reader.close();
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                }
            }
        }
    }

    
    
}
