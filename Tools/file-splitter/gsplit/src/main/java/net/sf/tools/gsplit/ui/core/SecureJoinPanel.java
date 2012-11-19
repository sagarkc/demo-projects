/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.tools.gsplit.ui.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.sf.tools.gsplit.SplitterContext;
import net.sf.tools.gsplit.WorkerTaskConstants;
import net.sf.tools.gsplit.core.SecureFileJoiner;
import net.sf.tools.gsplit.util.ExtensionFileFilter;
import net.sf.tools.gsplit.util.FileBrowserUtil;
import net.sf.tools.gsplit.util.MetaDataFileView;
import net.sf.tools.gsplit.util.StringUtil;

import org.apache.log4j.Logger;

/**
 *
 * @author SG1736
 */
public class SecureJoinPanel extends javax.swing.JPanel implements PropertyChangeListener{

    private static Logger logger = Logger.getLogger(SecureJoinPanel.class);
    private static SplitterContext context = SplitterContext.getContext();
    
    private SecureFileJoiner fileAutoJoiner;
    
    /**
     * Creates new form SecureJoinPanel
     */
    public SecureJoinPanel() {
        initComponents();
        secureJoinProgressBar.setVisible(false);
        secureJoinStopButton.setVisible(false);
        secureJoinStartButton.setEnabled(false);
        secureJoinSourceTextField.getDocument().addDocumentListener(new DocumentListener() {
            public void removeUpdate(DocumentEvent e) {
            	updateStartButton();
            }

            public void insertUpdate(DocumentEvent e) {
            	updateStartButton();
            }

            public void changedUpdate(DocumentEvent e) {
            	updateStartButton();
            }
        });
        secureJoinTargetTextField.getDocument().addDocumentListener(new DocumentListener() {
            public void removeUpdate(DocumentEvent e) {
            	updateStartButton();
            }

            public void insertUpdate(DocumentEvent e) {
            	updateStartButton();
            }

            public void changedUpdate(DocumentEvent e) {
            	updateStartButton();
            }
        });
    }
    
    private void updateStartButton(){
    	if (StringUtil.hasValidContent(secureJoinSourceTextField.getText())
                && StringUtil.hasValidContent(secureJoinTargetTextField.getText())) {
            secureJoinStartButton.setEnabled(true);
        } else {
            secureJoinStartButton.setEnabled(false);
        }
    }
    
    private void startJoin(){
    	synchronized (this) {
            fileAutoJoiner = new SecureFileJoiner(
                    secureJoinSourceTextField.getText(),
                    secureJoinTargetTextField.getText());
            fileAutoJoiner.addPropertyChangeListener(this);
            fileAutoJoiner.execute();
        }
    }
    
    private void stopJoin(){
    	if (null != fileAutoJoiner) {
            fileAutoJoiner.cancel(true);
        }
    }
    
    
    /* (non-Javadoc)
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    	String propertyName = evt.getPropertyName();
    	if (evt.getSource().equals(fileAutoJoiner)) {
            if (WorkerTaskConstants.TASK_STATUS_DONE.equals(propertyName)) {
                Object newValue = evt.getNewValue();
                JOptionPane.showMessageDialog(this,
                        String.format("Completed in [ %10.2f ] seconds.",
                        ((Long) newValue) / 1000.0));
                secureJoinStartButton.setEnabled(true);
                secureJoinStopButton.setVisible(false);
                secureJoinProgressBar.setVisible(false);
                secureJoinSourceTextField.setEditable(true);
                secureJoinTargetTextField.setEditable(true);
                browseSecureJoinSourceButton.setEnabled(true);
                browseSecureJoinTargetButton.setEnabled(true);
            }
            if (WorkerTaskConstants.TASK_STATUS_ABORT.equals(propertyName)) {
                Object newValue = evt.getNewValue();
                JOptionPane.showMessageDialog(this, "Join aborted by user");
                secureJoinStartButton.setEnabled(true);
                secureJoinStopButton.setVisible(false);
                secureJoinProgressBar.setVisible(false);
                secureJoinSourceTextField.setEditable(true);
                secureJoinTargetTextField.setEditable(true);
                browseSecureJoinSourceButton.setEnabled(true);
                browseSecureJoinTargetButton.setEnabled(true);
            }
            if (WorkerTaskConstants.PROPERTY_PROGRESS.equals(propertyName)) {
                String type = evt.getNewValue().toString();
                Object newProgress = evt.getNewValue();
                if (null != newProgress && newProgress instanceof Integer) {
                    secureJoinProgressBar.setValue((Integer) newProgress);
                    secureJoinProgressBar.updateUI();
                } else if (WorkerTaskConstants.TASK_STATUS_START.equals(newProgress)) {
                    secureJoinStartButton.setEnabled(false);
                    secureJoinStopButton.setVisible(true);
                    secureJoinProgressBar.setVisible(true);
                    secureJoinSourceTextField.setEditable(false);
                    secureJoinTargetTextField.setEditable(false);
                    browseSecureJoinSourceButton.setEnabled(false);
                    browseSecureJoinTargetButton.setEnabled(false);
                }
            }

            if (WorkerTaskConstants.TASK_STATUS_FAILED.equals(propertyName)) {
                Object newValue = evt.getNewValue();
                JOptionPane.showMessageDialog(this, "Join failed for : " + newValue);
                secureJoinStartButton.setEnabled(true);
                secureJoinStopButton.setVisible(false);
                secureJoinProgressBar.setVisible(false);
                secureJoinSourceTextField.setEditable(true);
                secureJoinTargetTextField.setEditable(true);
                browseSecureJoinSourceButton.setEnabled(true);
                browseSecureJoinTargetButton.setEnabled(true);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        secureJoinSourceTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        secureJoinTargetTextField = new javax.swing.JTextField();
        secureJoinProgressBar = new javax.swing.JProgressBar();
        browseSecureJoinSourceButton = new javax.swing.JButton();
        browseSecureJoinTargetButton = new javax.swing.JButton();
        secureJoinStopButton = new javax.swing.JButton();
        secureJoinStartButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        FormListener formListener = new FormListener();

        setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMaximumSize(new java.awt.Dimension(32767, 35));
        jPanel3.setMinimumSize(new java.awt.Dimension(100, 35));
        jPanel3.setPreferredSize(new java.awt.Dimension(454, 35));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/security-high.png"))); // NOI18N
        jLabel1.setText("Secure Join");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrow-join.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel6.setText("Source Metadata File");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(jLabel6, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(secureJoinSourceTextField, gridBagConstraints);

        jLabel7.setText("Target File");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(jLabel7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(secureJoinTargetTextField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(secureJoinProgressBar, gridBagConstraints);

        browseSecureJoinSourceButton.setText("Browse");
        browseSecureJoinSourceButton.addActionListener(formListener);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(browseSecureJoinSourceButton, gridBagConstraints);

        browseSecureJoinTargetButton.setText("Browse");
        browseSecureJoinTargetButton.addActionListener(formListener);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(browseSecureJoinTargetButton, gridBagConstraints);

        secureJoinStopButton.setText("Stop");
        secureJoinStopButton.addActionListener(formListener);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(secureJoinStopButton, gridBagConstraints);

        secureJoinStartButton.setText("Start");
        secureJoinStartButton.addActionListener(formListener);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(secureJoinStartButton, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel3, gridBagConstraints);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == browseSecureJoinSourceButton) {
                SecureJoinPanel.this.browseSecureJoinSourceButtonActionPerformed(evt);
            }
            else if (evt.getSource() == browseSecureJoinTargetButton) {
                SecureJoinPanel.this.browseSecureJoinTargetButtonActionPerformed(evt);
            }
            else if (evt.getSource() == secureJoinStopButton) {
                SecureJoinPanel.this.secureJoinStopButtonActionPerformed(evt);
            }
            else if (evt.getSource() == secureJoinStartButton) {
                SecureJoinPanel.this.secureJoinStartButtonActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void browseSecureJoinSourceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseSecureJoinSourceButtonActionPerformed
    	File file = FileBrowserUtil.openSingleFile(this,
                new ExtensionFileFilter(new String[]{".mdat"}, "Splitter Metadata file"), 
                	false, context.getAppSettings().getLastAccessedPathName(), new MetaDataFileView());
        if (null != file) {
            secureJoinSourceTextField.setText(file.getAbsolutePath());
            context.getAppSettings().setLastAccessedPathName(file.getAbsolutePath());
        }
    }//GEN-LAST:event_browseSecureJoinSourceButtonActionPerformed

    private void browseSecureJoinTargetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseSecureJoinTargetButtonActionPerformed
    	File file = FileBrowserUtil.openSingleFile(this, null, false,
                context.getAppSettings().getLastAccessedPathName());
        if (null != file) {
            secureJoinTargetTextField.setText(file.getAbsolutePath());
            context.getAppSettings().setLastAccessedPathName(file.getAbsolutePath());
        }
    }//GEN-LAST:event_browseSecureJoinTargetButtonActionPerformed

    private void secureJoinStopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_secureJoinStopButtonActionPerformed
        stopJoin();
    }//GEN-LAST:event_secureJoinStopButtonActionPerformed

    private void secureJoinStartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_secureJoinStartButtonActionPerformed
        startJoin();
    }//GEN-LAST:event_secureJoinStartButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseSecureJoinSourceButton;
    private javax.swing.JButton browseSecureJoinTargetButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar secureJoinProgressBar;
    private javax.swing.JTextField secureJoinSourceTextField;
    private javax.swing.JButton secureJoinStartButton;
    private javax.swing.JButton secureJoinStopButton;
    private javax.swing.JTextField secureJoinTargetTextField;
    // End of variables declaration//GEN-END:variables
}
