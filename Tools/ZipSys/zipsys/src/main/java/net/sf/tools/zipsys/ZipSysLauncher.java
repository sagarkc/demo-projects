package net.sf.tools.zipsys;

import javax.swing.UIManager;
import net.sf.tools.zipsys.ui.ZipsysFrame;

/**
 * Hello world!
 *
 */
public class ZipSysLauncher 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
        try {
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            try {
                javax.swing.UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex1) {
                throw ex1;
            }
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ZipsysFrame().setVisible(true);
            }
        });
    }
}
