/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.tools.colorhound.ui.ColorGrabKeyListener
 * Date     : May 23, 2013
 */

package com.gs.tools.colorhound.ui;

import com.gs.tools.colorhound.event.ApplicationEventManager;
import com.gs.tools.colorhound.event.ColorGrabEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class ColorGrabKeyListener implements KeyListener{

    private ApplicationEventManager eventManager 
            = ApplicationEventManager.getInstance();

    
    
    public void keyTyped(KeyEvent evt) {
        System.out.println("keyTyped: " + evt.getKeyCode());
        if(KeyEvent.CTRL_MASK == evt.getModifiers()
                && KeyEvent.VK_G == evt.getKeyCode()){
            System.out.println("grab");
            
        }
    }

    public void keyPressed(KeyEvent evt) {
        System.out.println("keyTyped: " + evt.getKeyCode());
    }

    public void keyReleased(KeyEvent evt) {
        System.out.println("keyTyped: " + evt.getKeyCode());
    }

    
    
}
