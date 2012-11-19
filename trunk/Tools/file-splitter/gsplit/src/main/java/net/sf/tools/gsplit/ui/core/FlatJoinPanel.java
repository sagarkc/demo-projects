/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.tools.gsplit.ui.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.sf.tools.gsplit.SplitterContext;
import net.sf.tools.gsplit.WorkerTaskConstants;
import net.sf.tools.gsplit.core.FlatFileJoiner;
import net.sf.tools.gsplit.util.CollectionListModel;
import net.sf.tools.gsplit.util.CollectionUtils;
import net.sf.tools.gsplit.util.FileBrowserUtil;
import net.sf.tools.gsplit.util.StringUtil;

import org.apache.log4j.Logger;


/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class FlatJoinPanel extends javax.swing.JPanel implements PropertyChangeListener{

	private static Logger logger = Logger.getLogger(FlatJoinPanel.class);
	private static SplitterContext context = SplitterContext.getContext();
	
	private CollectionListModel<File> allPartsListModel;
	private List<File> allPatrs;
	private CollectionListModel<File> allFilesListModel;
	private List<File> allFiles;
	private FlatFileJoiner flatFileAutoJoiner;
	private FlatFileJoiner flatFileManualJoiner;
	
    /**
     * Creates new form SecureJoinPanel
     */
    public FlatJoinPanel() {
        initComponents();
        allPatrs = new ArrayList<File>(0);
        allFiles = new ArrayList<File>(0);
        allPartsListModel = new CollectionListModel<File>(allPatrs);
        allPartsList.setModel(allPartsListModel);
        allFilesListModel = new CollectionListModel<File>(allFiles);
        addedFilesList.setModel(allFilesListModel);
        // auto panel
        processProgressBar.setVisible(false);
        stopButton.setVisible(false);
        startButton.setEnabled(false);
        selectedPartTextField.getDocument().addDocumentListener(new DocumentListener() {
            public void removeUpdate(DocumentEvent e) {
                updateAutoJoinStartButton();
            }

            public void insertUpdate(DocumentEvent e) {
                updateAutoJoinStartButton();
            }

            public void changedUpdate(DocumentEvent e) {
                updateAutoJoinStartButton();
            }
        });
        targetFileTextField.getDocument().addDocumentListener(new DocumentListener() {
            public void removeUpdate(DocumentEvent e) {
                updateAutoJoinStartButton();
            }

            public void insertUpdate(DocumentEvent e) {
                updateAutoJoinStartButton();
            }

            public void changedUpdate(DocumentEvent e) {
                updateAutoJoinStartButton();
            }
        });
        
        // manual panel
        manualProgressBar.setVisible(false);
        manualStopButton.setVisible(false);
        manualStartButton.setEnabled(false);
        
        manualTargetTextField.getDocument().addDocumentListener(new DocumentListener() {
            public void removeUpdate(DocumentEvent e) {
                updateManualJoinStartButton();
            }

            public void insertUpdate(DocumentEvent e) {
                updateManualJoinStartButton();
            }

            public void changedUpdate(DocumentEvent e) {
                updateManualJoinStartButton();
            }
        });
    }

    private void updateAutoJoinStartButton() {
        if (StringUtil.hasValidContent(selectedPartTextField.getText())
                && StringUtil.hasValidContent(targetFileTextField.getText())) {
            startButton.setEnabled(true);
        } else {
            startButton.setEnabled(false);
        }
    }
    
    private void updateManualJoinStartButton() {
        if (StringUtil.hasValidContent(manualTargetTextField.getText())
                && addedFilesList.getModel().getSize() > 0) {
            manualStartButton.setEnabled(true);
        } else {
            manualStartButton.setEnabled(false);
        }
    }
    
    private void startAutoJoin(){
    	synchronized (this) {
    		flatFileAutoJoiner = new FlatFileJoiner();
    		flatFileAutoJoiner.setSourceFiles(allPatrs);
    		flatFileAutoJoiner.setTargetFile(new File(targetFileTextField.getText()));
    		flatFileAutoJoiner.addPropertyChangeListener(this);
    		flatFileAutoJoiner.execute();
		}
    }
    
    private void startManualJoin(){
    	synchronized (this) {
    		flatFileManualJoiner = new FlatFileJoiner();
    		flatFileManualJoiner.setSourceFiles(allFiles);
    		flatFileManualJoiner.setTargetFile(new File(manualTargetTextField.getText()));
    		flatFileManualJoiner.addPropertyChangeListener(this);
    		flatFileManualJoiner.execute();
		}
    }
    
    private void stopAutoJoin(){
    	if(null != flatFileAutoJoiner){
    		flatFileAutoJoiner.cancel(true);
    	}
    }
    
    private void stopManualJoin(){
    	if(null != flatFileManualJoiner){
    		flatFileManualJoiner.cancel(true);
    	}
    }
    
    /* (non-Javadoc)
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    	String propertyName = evt.getPropertyName();
    	if (evt.getSource().equals(flatFileAutoJoiner)) {
            if (WorkerTaskConstants.TASK_STATUS_DONE.equals(propertyName)) {
                Object newValue = evt.getNewValue();
                processProgressBar.setValue(100);
            	processProgressBar.updateUI();
                JOptionPane.showMessageDialog(this,
                        String.format("Completed in [ %10.2f ] seconds.",
                        ((Long) newValue) / 1000.0));
                startButton.setEnabled(true);
                stopButton.setVisible(false);
                processProgressBar.setVisible(false);
                selectedPartTextField.setEditable(true);
                targetFileTextField.setEditable(true);
                browsePartButton.setEnabled(true);
                browseTargetFileButton.setEnabled(true);
            }
            if (WorkerTaskConstants.TASK_STATUS_ABORT.equals(propertyName)) {
                Object newValue = evt.getNewValue();
                JOptionPane.showMessageDialog(this, "Join aborted by user");
                startButton.setEnabled(true);
                stopButton.setVisible(false);
                processProgressBar.setVisible(false);
                selectedPartTextField.setEditable(true);
                targetFileTextField.setEditable(true);
                browsePartButton.setEnabled(true);
                browseTargetFileButton.setEnabled(true);
            }
            if (WorkerTaskConstants.PROPERTY_PROGRESS.equals(propertyName)) {
                String type = evt.getNewValue().toString();
                Object newProgress = evt.getNewValue();
                if (null != newProgress && newProgress instanceof Integer) {
                	processProgressBar.setValue((Integer) newProgress);
                	processProgressBar.updateUI();
                } else if (WorkerTaskConstants.TASK_STATUS_START.equals(newProgress)) {
                	startButton.setEnabled(false);
                	stopButton.setVisible(true);
                	processProgressBar.setVisible(true);
                	selectedPartTextField.setEditable(false);
                	targetFileTextField.setEditable(false);
                	browsePartButton.setEnabled(false);
                	browseTargetFileButton.setEnabled(false);
                }
            }

            if (WorkerTaskConstants.TASK_STATUS_FAILED.equals(propertyName)) {
                Object newValue = evt.getNewValue();
                JOptionPane.showMessageDialog(this, "Join failed for : " + newValue);
                startButton.setEnabled(true);
                stopButton.setVisible(false);
                processProgressBar.setVisible(false);
                selectedPartTextField.setEditable(true);
                targetFileTextField.setEditable(true);
                browsePartButton.setEnabled(true);
                browseTargetFileButton.setEnabled(true);
            }
        } else if (evt.getSource().equals(flatFileManualJoiner)) {
            if (WorkerTaskConstants.TASK_STATUS_DONE.equals(propertyName)) {
                Object newValue = evt.getNewValue();
                manualProgressBar.setValue(100);
            	manualProgressBar.updateUI();
                JOptionPane.showMessageDialog(this,
                        String.format("Completed in [ %10.2f ] seconds.",
                        ((Long) newValue) / 1000.0));
                manualStartButton.setEnabled(true);
                manualStopButton.setVisible(false);
                manualProgressBar.setVisible(false);
                manualTargetTextField.setEditable(true);
                addFilesButton.setEnabled(true);
                removeFilesButton.setEnabled(true);
                browseManualTargetButton.setEnabled(true);
                addedFilesList.setEnabled(true);
            }
            if (WorkerTaskConstants.TASK_STATUS_ABORT.equals(propertyName)) {
                Object newValue = evt.getNewValue();
                JOptionPane.showMessageDialog(this, "Join aborted by user");
                manualStartButton.setEnabled(true);
                manualStopButton.setVisible(false);
                manualProgressBar.setVisible(false);
                manualTargetTextField.setEditable(true);
                addFilesButton.setEnabled(true);
                removeFilesButton.setEnabled(true);
                browseManualTargetButton.setEnabled(true);
                addedFilesList.setEnabled(true);
            }
            if (WorkerTaskConstants.PROPERTY_PROGRESS.equals(propertyName)) {
                String type = evt.getNewValue().toString();
                Object newProgress = evt.getNewValue();
                if (null != newProgress && newProgress instanceof Integer) {
                	manualProgressBar.setValue((Integer) newProgress);
                	manualProgressBar.updateUI();
                } else if (WorkerTaskConstants.TASK_STATUS_START.equals(newProgress)) {
                	manualStartButton.setEnabled(false);
                	manualStopButton.setVisible(true);
                	manualProgressBar.setVisible(true);
                	manualTargetTextField.setEditable(false);
                	addFilesButton.setEnabled(false);
                	removeFilesButton.setEnabled(false);
                	browseManualTargetButton.setEnabled(false);
                	addedFilesList.setEnabled(false);
                }
            }

            if (WorkerTaskConstants.TASK_STATUS_FAILED.equals(propertyName)) {
                Object newValue = evt.getNewValue();
                JOptionPane.showMessageDialog(this, "Join failed for : " + newValue);
                manualStartButton.setEnabled(true);
                manualStopButton.setVisible(false);
                manualProgressBar.setVisible(false);
                manualTargetTextField.setEditable(true);
                addFilesButton.setEnabled(true);
                removeFilesButton.setEnabled(true);
                browseManualTargetButton.setEnabled(true);
                addedFilesList.setEnabled(true);
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
        flatJoinTabbedPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        selectedPartTextField = new javax.swing.JTextField();
        browsePartButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        allPartsList = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        targetFileTextField = new javax.swing.JTextField();
        browseTargetFileButton = new javax.swing.JButton();
        processProgressBar = new javax.swing.JProgressBar();
        stopButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        addedFilesList = new javax.swing.JList();
        moveToTopButton = new javax.swing.JButton();
        moveToBottomButton = new javax.swing.JButton();
        addFilesButton = new javax.swing.JButton();
        removeFilesButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        manualTargetTextField = new javax.swing.JTextField();
        browseManualTargetButton = new javax.swing.JButton();
        manualProgressBar = new javax.swing.JProgressBar();
        manualStopButton = new javax.swing.JButton();
        manualStartButton = new javax.swing.JButton();
        moveUpButton = new javax.swing.JButton();
        moveDownButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();

        FormListener formListener = new FormListener();

        setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMaximumSize(new java.awt.Dimension(32767, 35));
        jPanel3.setMinimumSize(new java.awt.Dimension(100, 35));
        jPanel3.setPreferredSize(new java.awt.Dimension(454, 35));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/security-low.png"))); // NOI18N
        jLabel1.setText("Flat Join - Not Secure");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrow-join.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Select any one of the parts ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 10, 0, 0);
        jPanel1.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 4, 0, 0);
        jPanel1.add(selectedPartTextField, gridBagConstraints);

        browsePartButton.setText("Browse");
        browsePartButton.addActionListener(formListener);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 0, 10);
        jPanel1.add(browsePartButton, gridBagConstraints);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("All the parts");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 76;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 0, 0);
        jPanel1.add(jLabel4, gridBagConstraints);

        allPartsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(allPartsList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 4, 0, 10);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Target File");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 83;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        jPanel1.add(jLabel5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(7, 4, 0, 0);
        jPanel1.add(targetFileTextField, gridBagConstraints);

        browseTargetFileButton.setText("Browse");
        browseTargetFileButton.addActionListener(formListener);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 10);
        jPanel1.add(browseTargetFileButton, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 4, 0, 0);
        jPanel1.add(processProgressBar, gridBagConstraints);

        stopButton.setText("Stop");
        stopButton.addActionListener(formListener);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(6, 24, 0, 10);
        jPanel1.add(stopButton, gridBagConstraints);

        startButton.setText("Start");
        startButton.addActionListener(formListener);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(6, 22, 11, 10);
        jPanel1.add(startButton, gridBagConstraints);

        flatJoinTabbedPane.addTab("Auto Join", jPanel1);

        jLabel6.setText("Files to Join");

        addedFilesList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        addedFilesList.addListSelectionListener(formListener);
        jScrollPane2.setViewportView(addedFilesList);

        moveToTopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/go-top.png"))); // NOI18N
        moveToTopButton.setEnabled(false);
        moveToTopButton.addActionListener(formListener);

        moveToBottomButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/go-bottom.png"))); // NOI18N
        moveToBottomButton.setEnabled(false);
        moveToBottomButton.addActionListener(formListener);

        addFilesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        addFilesButton.addActionListener(formListener);

        removeFilesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        removeFilesButton.setEnabled(false);
        removeFilesButton.addActionListener(formListener);

        jLabel7.setText("Target File");

        browseManualTargetButton.setText("Browse");
        browseManualTargetButton.addActionListener(formListener);

        manualStopButton.setText("Stop");
        manualStopButton.addActionListener(formListener);

        manualStartButton.setText("Start");
        manualStartButton.addActionListener(formListener);

        moveUpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/go-up.png"))); // NOI18N
        moveUpButton.setEnabled(false);
        moveUpButton.addActionListener(formListener);

        moveDownButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/go-down.png"))); // NOI18N
        moveDownButton.setEnabled(false);
        moveDownButton.addActionListener(formListener);

        clearButton.setText("Clear");
        clearButton.setEnabled(false);
        clearButton.addActionListener(formListener);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(addFilesButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(clearButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(removeFilesButton))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(moveUpButton)
                                    .addComponent(moveDownButton, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addComponent(moveToBottomButton, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(moveToTopButton, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(manualProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(manualTargetTextField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(browseManualTargetButton)
                            .addComponent(manualStopButton, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(manualStartButton)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addFilesButton, browseManualTargetButton, clearButton, manualStartButton, manualStopButton, moveDownButton, moveToBottomButton, moveToTopButton, moveUpButton, removeFilesButton});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(moveToTopButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moveUpButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(moveDownButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moveToBottomButton))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addFilesButton)
                    .addComponent(removeFilesButton)
                    .addComponent(clearButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(manualTargetTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseManualTargetButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(manualStopButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manualProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manualStartButton)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addFilesButton, clearButton, moveDownButton, moveToBottomButton, moveToTopButton, moveUpButton, removeFilesButton});

        flatJoinTabbedPane.addTab("Manual Join", jPanel2);

        add(flatJoinTabbedPane, java.awt.BorderLayout.CENTER);
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, javax.swing.event.ListSelectionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == browsePartButton) {
                FlatJoinPanel.this.browsePartButtonActionPerformed(evt);
            }
            else if (evt.getSource() == browseTargetFileButton) {
                FlatJoinPanel.this.browseTargetFileButtonActionPerformed(evt);
            }
            else if (evt.getSource() == stopButton) {
                FlatJoinPanel.this.stopButtonActionPerformed(evt);
            }
            else if (evt.getSource() == startButton) {
                FlatJoinPanel.this.startButtonActionPerformed(evt);
            }
            else if (evt.getSource() == moveToTopButton) {
                FlatJoinPanel.this.moveToTopButtonActionPerformed(evt);
            }
            else if (evt.getSource() == moveToBottomButton) {
                FlatJoinPanel.this.moveToBottomButtonActionPerformed(evt);
            }
            else if (evt.getSource() == addFilesButton) {
                FlatJoinPanel.this.addFilesButtonActionPerformed(evt);
            }
            else if (evt.getSource() == removeFilesButton) {
                FlatJoinPanel.this.removeFilesButtonActionPerformed(evt);
            }
            else if (evt.getSource() == browseManualTargetButton) {
                FlatJoinPanel.this.browseManualTargetButtonActionPerformed(evt);
            }
            else if (evt.getSource() == manualStopButton) {
                FlatJoinPanel.this.manualStopButtonActionPerformed(evt);
            }
            else if (evt.getSource() == manualStartButton) {
                FlatJoinPanel.this.manualStartButtonActionPerformed(evt);
            }
            else if (evt.getSource() == moveUpButton) {
                FlatJoinPanel.this.moveUpButtonActionPerformed(evt);
            }
            else if (evt.getSource() == moveDownButton) {
                FlatJoinPanel.this.moveDownButtonActionPerformed(evt);
            }
            else if (evt.getSource() == clearButton) {
                FlatJoinPanel.this.clearButtonActionPerformed(evt);
            }
        }

        public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
            if (evt.getSource() == addedFilesList) {
                FlatJoinPanel.this.addedFilesListValueChanged(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void browsePartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browsePartButtonActionPerformed
    	File selectedFile = FileBrowserUtil.openSingleFile(this, null, false, 
    			context.getAppSettings().getLastAccessedPathName());
        if (null != selectedFile) {
        	allPatrs.clear();
        	String extension = selectedFile.getName().substring(
        			selectedFile.getName().lastIndexOf('.')+1
        			).toLowerCase();
        	if(extension.startsWith("part")){
        		File dir = selectedFile.getParentFile();
        		File[] files = dir.listFiles();
        		if(null != files && files.length > 0){
        			for (File file : files) {
        				String ext = file.getName().substring(
        	        			file.getName().lastIndexOf('.')+1
        	        			).toLowerCase();
        				if(ext.startsWith("part")){
        					allPatrs.add(file);
        				}
					}
        		}
        	} else {
        		JOptionPane.showMessageDialog(this, "Please select a .part* file");
        		return;
        	}
        	
        	Collections.sort(allPatrs);
        	
        	allPartsList.updateUI();
        	selectedPartTextField.setText(selectedFile.getAbsolutePath());
            context.getAppSettings().setLastAccessedPathName(selectedFile.getAbsolutePath());
        }
    }//GEN-LAST:event_browsePartButtonActionPerformed

    private void browseTargetFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseTargetFileButtonActionPerformed
    	File file = FileBrowserUtil.openSingleFile(this, null, false,
                context.getAppSettings().getLastAccessedPathName());
        if (null != file) {
        	targetFileTextField.setText(file.getAbsolutePath());
            context.getAppSettings().setLastAccessedPathName(file.getAbsolutePath());
        }
    }//GEN-LAST:event_browseTargetFileButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        stopAutoJoin();
    }//GEN-LAST:event_stopButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        startAutoJoin();
    }//GEN-LAST:event_startButtonActionPerformed

    private void moveToTopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveToTopButtonActionPerformed
    	int index = addedFilesList.getSelectedIndex();
        if(index >= 1){
        	CollectionUtils.swapItems(allFiles, index, 0);
        	addedFilesList.setSelectedIndex(0);
        	addedFilesList.updateUI();
        }
    }//GEN-LAST:event_moveToTopButtonActionPerformed

    private void moveUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveUpButtonActionPerformed
        int index = addedFilesList.getSelectedIndex();
        if(index >= 1){
        	CollectionUtils.swapItems(allFiles, index, index-1);
        	addedFilesList.setSelectedIndex(index-1);
        	addedFilesList.updateUI();
        }
        
    }//GEN-LAST:event_moveUpButtonActionPerformed

    private void moveDownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveDownButtonActionPerformed
    	int index = addedFilesList.getSelectedIndex();
        if(index >= 0 && index < allFiles.size()-1){
        	CollectionUtils.swapItems(allFiles, index, index+1);
        	addedFilesList.setSelectedIndex(index+1);
        	addedFilesList.updateUI();
        }
    }//GEN-LAST:event_moveDownButtonActionPerformed

    private void moveToBottomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveToBottomButtonActionPerformed
    	int index = addedFilesList.getSelectedIndex();
        if(index >= 0 && index < allFiles.size()-1){
        	CollectionUtils.swapItems(allFiles, index, allFiles.size()-1);
        	addedFilesList.setSelectedIndex(allFiles.size()-1);
        	addedFilesList.updateUI();
        }
    }//GEN-LAST:event_moveToBottomButtonActionPerformed

    private void addFilesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFilesButtonActionPerformed
    	File[] selectedFiles = FileBrowserUtil.openMultipleFile(this, null, false, 
    			context.getAppSettings().getLastAccessedPathName());
        if (null != selectedFiles && selectedFiles.length > 0) {
        	for (File file : selectedFiles) {
        		allFiles.add(file);
			}
        	context.getAppSettings().setLastAccessedPathName(selectedFiles[0].getAbsolutePath());
        }
        addedFilesList.updateUI();
        if(addedFilesList.getModel().getSize() > 0){
        	clearButton.setEnabled(true);
        } else {
        	clearButton.setEnabled(false);
        }
    }//GEN-LAST:event_addFilesButtonActionPerformed

    private void removeFilesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFilesButtonActionPerformed
        int[] indeces = addedFilesList.getSelectedIndices();
        if(null != indeces){
        	for (int index : indeces) {
        		allFiles.remove(index);
			}
        }
        addedFilesList.updateUI();
        if(addedFilesList.getModel().getSize() > 0){
        	clearButton.setEnabled(true);
        } else {
        	clearButton.setEnabled(false);
        }
    }//GEN-LAST:event_removeFilesButtonActionPerformed

    private void browseManualTargetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseManualTargetButtonActionPerformed
    	File file = FileBrowserUtil.openSingleFile(this, null, false,
                context.getAppSettings().getLastAccessedPathName());
        if (null != file) {
        	manualTargetTextField.setText(file.getAbsolutePath());
            context.getAppSettings().setLastAccessedPathName(file.getAbsolutePath());
        }
    }//GEN-LAST:event_browseManualTargetButtonActionPerformed

    private void manualStopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manualStopButtonActionPerformed
        stopManualJoin();
    }//GEN-LAST:event_manualStopButtonActionPerformed

    private void manualStartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manualStartButtonActionPerformed
        startManualJoin();
    }//GEN-LAST:event_manualStartButtonActionPerformed

    private void addedFilesListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_addedFilesListValueChanged
        if(addedFilesList.getSelectedIndex() >= 0){
            removeFilesButton.setEnabled(true);
            if(addedFilesList.getSelectedIndex() > 0){
            	moveUpButton.setEnabled(true);
            	moveToTopButton.setEnabled(true);
            } else {
            	moveUpButton.setEnabled(false);
            	moveToTopButton.setEnabled(false);
            }
            
            if(addedFilesList.getSelectedIndex() < addedFilesList.getModel().getSize()-1){
            	moveDownButton.setEnabled(true);
                moveToBottomButton.setEnabled(true);
            } else {
            	moveDownButton.setEnabled(false);
                moveToBottomButton.setEnabled(false);
            }
        } else {
            removeFilesButton.setEnabled(false);
            moveUpButton.setEnabled(false);
            moveDownButton.setEnabled(false);
            moveToBottomButton.setEnabled(false);
            moveToTopButton.setEnabled(false);
        }
    }//GEN-LAST:event_addedFilesListValueChanged

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
    	allFiles.clear();
    	addedFilesList.updateUI();
    	
    	if(addedFilesList.getModel().getSize() > 0){
        	clearButton.setEnabled(true);
        } else {
        	clearButton.setEnabled(false);
        }
    	removeFilesButton.setEnabled(false);
        moveUpButton.setEnabled(false);
        moveDownButton.setEnabled(false);
        moveToBottomButton.setEnabled(false);
        moveToTopButton.setEnabled(false);
    }//GEN-LAST:event_clearButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addFilesButton;
    private javax.swing.JList addedFilesList;
    private javax.swing.JList allPartsList;
    private javax.swing.JButton browseManualTargetButton;
    private javax.swing.JButton browsePartButton;
    private javax.swing.JButton browseTargetFileButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JTabbedPane flatJoinTabbedPane;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JProgressBar manualProgressBar;
    private javax.swing.JButton manualStartButton;
    private javax.swing.JButton manualStopButton;
    private javax.swing.JTextField manualTargetTextField;
    private javax.swing.JButton moveDownButton;
    private javax.swing.JButton moveToBottomButton;
    private javax.swing.JButton moveToTopButton;
    private javax.swing.JButton moveUpButton;
    private javax.swing.JProgressBar processProgressBar;
    private javax.swing.JButton removeFilesButton;
    private javax.swing.JTextField selectedPartTextField;
    private javax.swing.JButton startButton;
    private javax.swing.JButton stopButton;
    private javax.swing.JTextField targetFileTextField;
    // End of variables declaration//GEN-END:variables
}
