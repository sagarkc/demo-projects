/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.desk.EtlManagerLauncher
 * Date:	Oct 25, 2013  7:38:47 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.desk;

import com.xchanging.etl.mgr.desk.view.EtlMgrDesktopFrame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class EtlManagerLauncher {

	/**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        try {
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            try {
                javax.swing.UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex1) {
                Logger.getLogger(EtlManagerLauncher.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } 
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EtlMgrDesktopFrame().setVisible(true);
            }
        });
    }
}
