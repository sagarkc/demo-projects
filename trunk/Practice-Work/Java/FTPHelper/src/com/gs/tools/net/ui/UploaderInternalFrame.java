/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UploaderInternalFrame.java
 *
 * Created on Jun 5, 2011, 10:13:54 AM
 */
package com.gs.tools.net.ui;

import com.gs.tools.net.core.AppContext;
import com.gs.tools.net.core.Configuration;
import com.gs.tools.net.core.FtpUploadReturnData;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author 50120C1509
 */
public class UploaderInternalFrame extends javax.swing.JInternalFrame {

    private FileUploadTask fileUploadTask;

    /** Creates new form UploaderInternalFrame */
    public UploaderInternalFrame() {
        setFrameIcon((new ImageIcon(
                UploaderInternalFrame.class.getResource("/images/kftpgrabber.png").getFile()
                )));
        initComponents();
        List<Configuration> allCfg = new ArrayList<Configuration>();
        allCfg.addAll(AppContext.getAppContext().configurationList);
        Vector<Configuration> v = new Vector<Configuration>();
        v.addAll(allCfg);
        cfgNamesComboBox.setModel(new DefaultComboBoxModel(v));
        fileNamesList.setModel(new CollectionListModel(new ArrayList<String>()));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        refreshButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        hostNameTextField = new javax.swing.JTextField();
        userNameTextField = new javax.swing.JTextField();
        rootDirTextField = new javax.swing.JTextField();
        cfgNamesComboBox = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        removeAllButton = new javax.swing.JButton();
        removeFileButton = new javax.swing.JButton();
        addFilesButton = new javax.swing.JButton();
        uploadButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        fileNamesList = new javax.swing.JList();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        currentFileLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        fileProgressBar = new javax.swing.JProgressBar();
        overallProgressBar = new javax.swing.JProgressBar();
        cancelButton = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(" FTP Information "));

        jLabel1.setText("Configuration Name : ");

        refreshButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reload_green.png"))); // NOI18N
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Host Name");

        jLabel3.setText("User Name ");

        jLabel4.setText("Target Directory");

        hostNameTextField.setEditable(false);

        userNameTextField.setEditable(false);

        rootDirTextField.setForeground(new java.awt.Color(0, 0, 204));

        cfgNamesComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cfgNamesComboBoxActionPerformed(evt);
            }
        });
        cfgNamesComboBox.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cfgNamesComboBoxPropertyChange(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(0, 0, 204));
        jLabel6.setText("/");

        jCheckBox1.setText("Create Dir, if not exists");

        jCheckBox2.setText("Upload files only");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCheckBox2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(cfgNamesComboBox, 0, 469, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(refreshButton))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rootDirTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(userNameTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(hostNameTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox1)))))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jCheckBox1, jCheckBox2});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(refreshButton)
                    .addComponent(cfgNamesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(hostNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(userNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(rootDirTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, Short.MAX_VALUE)
                .addComponent(jCheckBox2))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(" Add Files "));

        removeAllButton.setText("Remove All");
        removeAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAllButtonActionPerformed(evt);
            }
        });

        removeFileButton.setText("Remove");
        removeFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFileButtonActionPerformed(evt);
            }
        });

        addFilesButton.setText("Add");
        addFilesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFilesButtonActionPerformed(evt);
            }
        });

        uploadButton.setText("Upload");
        uploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(fileNamesList);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addFilesButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(removeFileButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(removeAllButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(uploadButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addFilesButton, removeAllButton, removeFileButton, uploadButton});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(addFilesButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeFileButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeAllButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(uploadButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(" Status "));

        jLabel5.setText("Uploading");

        currentFileLabel.setForeground(new java.awt.Color(0, 0, 204));
        currentFileLabel.setText("File");

        jLabel7.setText("Overall");

        cancelButton.setText("Cancel");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 570, Short.MAX_VALUE)
                        .addComponent(cancelButton))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currentFileLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fileProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(overallProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel5, jLabel7});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(currentFileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fileProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(overallProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        List<Configuration> allCfg = new ArrayList<Configuration>();
        allCfg.addAll(AppContext.getAppContext().configurationList);
        Vector<Configuration> v = new Vector<Configuration>();
        v.addAll(allCfg);
        cfgNamesComboBox.setModel(new DefaultComboBoxModel(v));
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void cfgNamesComboBoxPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cfgNamesComboBoxPropertyChange
    }//GEN-LAST:event_cfgNamesComboBoxPropertyChange

    private void cfgNamesComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cfgNamesComboBoxActionPerformed
        Configuration p = (Configuration) cfgNamesComboBox.getSelectedItem();
        hostNameTextField.setText(p.getHostName());
        userNameTextField.setText(p.getUserName());
    }//GEN-LAST:event_cfgNamesComboBoxActionPerformed

    private void addFilesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFilesButtonActionPerformed
        JFileChooser chooser = new JFileChooser(AppContext.getAppContext().lastSelectedPath);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setMultiSelectionEnabled(true);
        int option = chooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File[] files = chooser.getSelectedFiles();
            if (null != files && files.length > 0) {
                for (File file : files) {
                    CollectionListModel<String> cm = (CollectionListModel<String>) fileNamesList.getModel();
                    cm.addElement(file.getAbsolutePath());
                    fileNamesList.setModel(cm);

                }
            }
        }
        fileNamesList.updateUI();
    }//GEN-LAST:event_addFilesButtonActionPerformed

    private void removeFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFileButtonActionPerformed
        int[] selectedIndices = fileNamesList.getSelectedIndices();
        CollectionListModel<String> cm = (CollectionListModel<String>) fileNamesList.getModel();
        cm.remove(selectedIndices);
        fileNamesList.setModel(cm);
        fileNamesList.updateUI();
    }//GEN-LAST:event_removeFileButtonActionPerformed

    private void removeAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAllButtonActionPerformed
        CollectionListModel<String> cm = (CollectionListModel<String>) fileNamesList.getModel();
        cm.getDataList().clear();
        fileNamesList.setModel(cm);
        fileNamesList.updateUI();
    }//GEN-LAST:event_removeAllButtonActionPerformed

    private void uploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadButtonActionPerformed
        Configuration configuration = (Configuration) cfgNamesComboBox.getSelectedItem();
        if (null == configuration) {
            JOptionPane.showMessageDialog(this, "No Configuration found.");
            return;
        }

        CollectionListModel<String> cm = (CollectionListModel<String>) fileNamesList.getModel();
        if (cm.getSize() <= 0) {
            JOptionPane.showMessageDialog(this, "No Files to upload.");
            return;
        }

        synchronized (this) {
            final List<String> fileNames = cm.getDataList();
            final UploaderInternalFrame comp = this;
            fileUploadTask = new FileUploadTask(configuration, fileNames);
            fileUploadTask.setRemoteLocation("/" + rootDirTextField.getText());

            fileUploadTask.addPropertyChangeListener(new PropertyChangeListener() {

                public void propertyChange(PropertyChangeEvent evt) {
                    String propertyName = evt.getPropertyName();
                    if (FileUploadTask.TASK_STATUS_DONE.equals(propertyName)) {
                        JOptionPane.showMessageDialog(comp, "Completed");
                        fileProgressBar.setValue(0);
                        fileProgressBar.updateUI();
                        overallProgressBar.setValue(0);
                        overallProgressBar.updateUI();
                        uploadButton.setEnabled(true);
                    }
                    if (FileUploadTask.TASK_STATUS_ABORT.equals(propertyName)) {
                        String message = evt.getNewValue().toString();
                        JOptionPane.showMessageDialog(comp, message);
                        fileProgressBar.setValue(0);
                        fileProgressBar.updateUI();
                        overallProgressBar.setValue(0);
                        overallProgressBar.updateUI();
                        uploadButton.setEnabled(true);
                    }
                    if (FileUploadTask.PROPERTY_PROGRESS.equals(propertyName)) {
                        Object newValue = evt.getNewValue();
                        if (newValue instanceof String) {
                            String type = newValue.toString();
                            if (FileUploadTask.TASK_STATUS_START.equals(type)) {
                                uploadButton.setEnabled(false);
                            }
                        }
                        else if(newValue instanceof FtpUploadReturnData)
                        {
                            FtpUploadReturnData data = (FtpUploadReturnData) newValue;
                            currentFileLabel.setText(data.currentFileName);
                            currentFileLabel.updateUI();
                            fileProgressBar.setValue(data.percentageDone);
                            fileProgressBar.updateUI();
                            overallProgressBar.setValue((data.completedFileCount / fileNames.size()) * 100);
                            overallProgressBar.updateUI();
                            uploadButton.setEnabled(false);
                        }
                    }
                    if (FileUploadTask.TASK_STATUS_FAILED.equals(propertyName)) {

                        Exception exception = (Exception) evt.getNewValue();
                        JOptionPane.showMessageDialog(comp, "Failed\n\n" + exception.getMessage());
                        fileProgressBar.setValue(0);
                        fileProgressBar.updateUI();
                        overallProgressBar.setValue(0);
                        overallProgressBar.updateUI();
                        uploadButton.setEnabled(true);
                    }
                }

                
            });

            fileUploadTask.execute();
        }


    }//GEN-LAST:event_uploadButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addFilesButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox cfgNamesComboBox;
    private javax.swing.JLabel currentFileLabel;
    private javax.swing.JList fileNamesList;
    private javax.swing.JProgressBar fileProgressBar;
    private javax.swing.JTextField hostNameTextField;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JProgressBar overallProgressBar;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton removeAllButton;
    private javax.swing.JButton removeFileButton;
    private javax.swing.JTextField rootDirTextField;
    private javax.swing.JButton uploadButton;
    private javax.swing.JTextField userNameTextField;
    // End of variables declaration//GEN-END:variables
}
