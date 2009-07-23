/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.ws.invoker;

import com.gs.ws.invoker.ui.JWSInvokerFrame;
import javax.swing.UIManager;

/**
 *
 * @author Green Moon
 */
public class InvokeWS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){
            
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JWSInvokerFrame().setVisible(true);
            }
        });
    }

}
