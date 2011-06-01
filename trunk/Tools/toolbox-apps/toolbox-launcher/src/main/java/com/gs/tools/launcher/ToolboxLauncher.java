
package com.gs.tools.launcher;

import com.gs.tools.launcher.ui.ToolboxMainFrame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author 50120C1509
 */
public class ToolboxLauncher {

    public static void main(String[] args){
        try {
            // load properties
            // load status
            // show splash
            // open main window
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            ToolboxMainFrame frame = new ToolboxMainFrame();
            frame.setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ToolboxLauncher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ToolboxLauncher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ToolboxLauncher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ToolboxLauncher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
