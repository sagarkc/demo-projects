/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xchanging.etl.mgr.desk.view;

import com.xchanging.etl.mgr.desk.util.ResourceBundleManager;
import java.util.ResourceBundle;

/**
 *
 * @author Sabuj
 */
public class JobMonitorPanel extends javax.swing.JPanel {
    private ResourceBundle bundle 
            = ResourceBundleManager.getBundleManager().getResourceBundle();
    
    /**
     * Creates new form JobMonitorPanel
     */
    public JobMonitorPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jobMonitorActionsPanel = new com.xchanging.etl.mgr.desk.view.JobMonitorActionsPanel();
        monitoredJobsListPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());
        add(jobMonitorActionsPanel, java.awt.BorderLayout.PAGE_START);

        monitoredJobsListPanel.setLayout(new java.awt.BorderLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        monitoredJobsListPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(monitoredJobsListPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private com.xchanging.etl.mgr.desk.view.JobMonitorActionsPanel jobMonitorActionsPanel;
    private javax.swing.JPanel monitoredJobsListPanel;
    // End of variables declaration//GEN-END:variables
}