/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.tools.doc.extractor.app;

import com.gs.tools.doc.extractor.app.view.DocumentExtractorFrame;
import java.io.File;
import javax.swing.UIManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 *
 * @author sabuj das
 */
public class DocumentExtractorLauncher {
    
    private static final String CONFIG_PATH = "./config/";
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        File loggerFile = new File(CONFIG_PATH + File.separator + "logger/log4j.xml");
        if(loggerFile.exists()){
            DOMConfigurator.configure(CONFIG_PATH + File.separator + "logger/log4j.xml");
        }
        else {
            System.out.println("Could not init logger.... ");
        }
                
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            Logger.getLogger(DocumentExtractorLauncher.class)
                    .warn("Could not apply System LookNfeel. Using Cross-Platform UI...");
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex1) {
                
            }
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DocumentExtractorFrame().setVisible(true);
            }
        });
    }
}
