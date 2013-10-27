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
import javax.swing.UIManager;
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
            javax.swing.UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(EtlMgrDesktopFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EtlMgrDesktopFrame().setVisible(true);
            }
        });
    }
}
