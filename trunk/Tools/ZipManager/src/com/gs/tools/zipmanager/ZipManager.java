/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.tools.zipmanager.ZipManager
 * Date     : May 2, 2013
 */

package com.gs.tools.zipmanager;

import com.gs.tools.zipmanager.ui.ZipManagerFrame;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class ZipManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ZipManagerFrame().setVisible(true);
            }
        });
    }

}
