/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.tools.md5sum;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Sabuj
 */
public class MD5SumFrame extends javax.swing.JFrame 
implements  PropertyChangeListener{

    private final ImageIcon frameIcon = new ImageIcon(getClass().getResource(
               "/img/barcode_2d.png"));
    private final ImageIcon clockIcon = new ImageIcon(getClass().getResource(
               "/img/clock.png"));
    private final ImageIcon acceptIcon = new ImageIcon(getClass().getResource(
               "/img/accept_48x48.png"));
    private final ImageIcon rejectIcon = new ImageIcon(getClass().getResource(
               "/img/reject_48x48.png"));
    
    private MD5GenerationWorker generationWorker;
    
    /**
     * Creates new form MD5SumFrame
     */
    public MD5SumFrame() {
        setIconImage(frameIcon.getImage());
        setLocationByPlatform(true);
        initComponents();
        generationProgressBar.setVisible(false);
        generationProgressBar.setValue(0);
        stopButton.setVisible(false);
        sourceTextArea.getDocument().addDocumentListener(
            new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    enableControl();
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    enableControl();
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    enableControl();
                }
                
                private void enableControl(){
                    if(null != sourceTextArea.getText() 
                            && !"".equals(sourceTextArea.getText().trim())){
                        compareButton.setEnabled(true);
                        return ;
                    }
                    compareButton.setEnabled(false);
                }
                
            });
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fileNameTextField = new javax.swing.JTextField();
        browseSourceButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        algoTypeComboBox = new javax.swing.JComboBox();
        generateButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        generateTextArea = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        sourceTextArea = new javax.swing.JTextArea();
        resultLabel = new javax.swing.JLabel();
        compareButton = new javax.swing.JButton();
        generationProgressBar = new javax.swing.JProgressBar();
        stopButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MD5 Sum");
        setMaximumSize(new java.awt.Dimension(560, 360));
        setMinimumSize(new java.awt.Dimension(560, 360));
        setPreferredSize(new java.awt.Dimension(560, 360));
        setResizable(false);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("File");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jLabel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(fileNameTextField, gridBagConstraints);

        browseSourceButton.setText("Select");
        browseSourceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseSourceButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(browseSourceButton, gridBagConstraints);

        jLabel2.setText("Algorithm");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jLabel2, gridBagConstraints);

        algoTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MD5", "SHA1", "SHA-256" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(algoTypeComboBox, gridBagConstraints);

        generateButton.setText("Generate");
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(generateButton, gridBagConstraints);

        jLabel3.setText("Generated Sum");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jLabel3, gridBagConstraints);

        generateTextArea.setEditable(false);
        generateTextArea.setColumns(20);
        generateTextArea.setRows(5);
        jScrollPane1.setViewportView(generateTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jLabel4.setText("Compare With");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jLabel4, gridBagConstraints);

        sourceTextArea.setColumns(20);
        sourceTextArea.setRows(5);
        jScrollPane2.setViewportView(sourceTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jScrollPane2, gridBagConstraints);

        resultLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        resultLabel.setMaximumSize(new java.awt.Dimension(52, 52));
        resultLabel.setMinimumSize(new java.awt.Dimension(52, 52));
        resultLabel.setPreferredSize(new java.awt.Dimension(52, 52));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        jPanel1.add(resultLabel, gridBagConstraints);

        compareButton.setText("Compare");
        compareButton.setEnabled(false);
        compareButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compareButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(compareButton, gridBagConstraints);

        generationProgressBar.setDoubleBuffered(true);
        generationProgressBar.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(generationProgressBar, gridBagConstraints);

        stopButton.setText("Stop");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(stopButton, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel6, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void browseSourceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseSourceButtonActionPerformed
        JFileChooser chooser = new JFileChooser(".");
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int opt = chooser.showOpenDialog(this);
        if(JFileChooser.APPROVE_OPTION == opt){
            File file = chooser.getSelectedFile();
            if(file != null && file.exists()){
                fileNameTextField.setText(file.getAbsolutePath());
                generateButton.setEnabled(true);
            }
        }
    }//GEN-LAST:event_browseSourceButtonActionPerformed

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        synchronized(this){
            generationWorker = new MD5GenerationWorker(fileNameTextField.getText(), 
                    algoTypeComboBox.getSelectedItem().toString()
                    );
            generationWorker.addPropertyChangeListener(this);
            generationWorker.execute();
        }
    }//GEN-LAST:event_generateButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        synchronized(this){
            if(null != generationWorker){
                generationWorker.stop();
            }
        }
    }//GEN-LAST:event_stopButtonActionPerformed

    private void compareButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compareButtonActionPerformed
        String l = generateTextArea.getText();
        String r = sourceTextArea.getText();
        if(r.equals(l)){
            resultLabel.setIcon(acceptIcon);
        } else {
            resultLabel.setIcon(rejectIcon);
        }
    }//GEN-LAST:event_compareButtonActionPerformed

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String name = evt.getPropertyName();
        if(WorkerTaskConstants.TASK_STATUS_START.equals(name)){
            generateButton.setEnabled(false);
            compareButton.setEnabled(false);
            generationProgressBar.setVisible(true);
            generationProgressBar.setValue(0);
            browseSourceButton.setEnabled(false);
            fileNameTextField.setEditable(false);
            resultLabel.setIcon(clockIcon);
            stopButton.setVisible(true);
            generateTextArea.setText("");
            sourceTextArea.setText("");
            sourceTextArea.setEditable(false);
        }
        if(WorkerTaskConstants.TASK_STATUS_FAILED.equals(name)
                || WorkerTaskConstants.TASK_STATUS_ABORT.equals(name)){
            generateButton.setEnabled(true);
            generationProgressBar.setVisible(false);
            generationProgressBar.setValue(0);
            browseSourceButton.setEnabled(true);
            fileNameTextField.setEditable(true);
            resultLabel.setIcon(clockIcon);
            stopButton.setVisible(false);
            sourceTextArea.setEditable(true);
        }
        if(WorkerTaskConstants.TASK_STATUS_DONE.equals(name)){
            generateButton.setEnabled(true);
            generationProgressBar.setVisible(false);
            generationProgressBar.setValue(0);
            browseSourceButton.setEnabled(true);
            fileNameTextField.setEditable(true);
            resultLabel.setIcon(clockIcon);
            stopButton.setVisible(false);
            sourceTextArea.setEditable(true);
            if(null != evt.getNewValue()){
                String sum = evt.getNewValue().toString();
                generateTextArea.setText(sum);
            }
        }
        if(WorkerTaskConstants.PROPERTY_PROGRESS.equals(name)){
            Integer progress = (Integer) evt.getNewValue();
            int val = generationProgressBar.getValue();
            if(progress > val){
                generationProgressBar.setValue((Integer)progress);
                generationProgressBar.updateUI();
            }
        }
    }

    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MD5SumFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox algoTypeComboBox;
    private javax.swing.JButton browseSourceButton;
    private javax.swing.JButton compareButton;
    private javax.swing.JTextField fileNameTextField;
    private javax.swing.JButton generateButton;
    private javax.swing.JTextArea generateTextArea;
    private javax.swing.JProgressBar generationProgressBar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel resultLabel;
    private javax.swing.JTextArea sourceTextArea;
    private javax.swing.JButton stopButton;
    // End of variables declaration//GEN-END:variables
}