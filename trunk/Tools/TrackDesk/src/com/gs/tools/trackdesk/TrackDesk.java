/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.tools.trackdesk.TrackDesk
 * Date     : May 10, 2013
 */

package com.gs.tools.trackdesk;

import com.gs.tools.trackdesk.ui.TrackDeskFrame;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class TrackDesk {

    public static final String OS_TYPE = System.getProperty("os.name").toLowerCase();;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            if(OS_TYPE.indexOf("win") >= 0){
                UIManager.setLookAndFeel(WindowsLookAndFeel.class.getCanonicalName());
            } else {
                UIManager.setLookAndFeel(NimbusLookAndFeel.class.getCanonicalName());
            }
        } catch (Exception ex) {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex1) {
                
            }
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrackDeskFrame().setVisible(true);
            }
        });
    }
}
