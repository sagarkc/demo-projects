/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.tools.doc.extractor.app.view;

import com.gs.tools.doc.extractor.core.WebDocumentExtractor;
import com.gs.tools.doc.extractor.core.html.HTMLDocumentExtractor;
import com.gs.utils.swing.window.WindowUtil;
import java.io.File;
import javax.swing.ImageIcon;
import org.apache.log4j.Logger;

/**
 *
 * @author sabuj
 */
public class DocumentExtractorFrame extends javax.swing.JFrame {

    private static Logger logger = Logger.getLogger(DocumentExtractorFrame.class);
    private final WebDocumentExtractor documentExtractor = new HTMLDocumentExtractor();
    private File selectedFolder;

    /**
     * Creates new form DocumentExtractorFrame
     */
    public DocumentExtractorFrame() {
        logger.info("Create Frame...");
        initComponents();
        setIconImage((new ImageIcon(getClass()
                .getResource("/images/web-doc-extract_24x24.png"))).getImage());
        
        logger.info("Position Frame to center");
        WindowUtil.bringToCenter(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("WEB Document Extractor");
        setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        setMinimumSize(new java.awt.Dimension(640, 380));
        setPreferredSize(new java.awt.Dimension(640, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}