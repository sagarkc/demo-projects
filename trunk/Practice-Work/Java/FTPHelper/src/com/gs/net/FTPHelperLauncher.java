/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.net;

import com.gs.tools.net.ui.FtpFrame;
import javax.swing.UIManager;

/**
 *
 * @author 50120C1509
 */
public class FTPHelperLauncher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch( Exception e){
            
        }
        new FtpFrame().setVisible(true);
    }

}
