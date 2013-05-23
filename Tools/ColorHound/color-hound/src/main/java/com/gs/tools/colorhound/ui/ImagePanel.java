/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.tools.colorhound.ui.ImagePanel
 * Date     : May 23, 2013
 */

package com.gs.tools.colorhound.ui;

import com.gs.tools.colorhound.event.ApplicationEvent;
import com.gs.tools.colorhound.event.ApplicationEventManager;
import com.gs.tools.colorhound.event.ColorGrabEvent;
import com.gs.tools.colorhound.event.ColorGrabListener;
import java.awt.Color;
import java.awt.Image;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class ImagePanel extends JPanel implements MouseListener {

    private Image image;
    private ColorGrabKeyListener colorGrabKeyListener;
    private ApplicationEventManager eventManager 
            = ApplicationEventManager.getInstance();
    public ImagePanel(Image image) {
        this.image = image;
        setSize(200,200);
        setBackground(Color.green);
        addMouseListener(this);
        
    }

    public void mouseClicked(MouseEvent e) {
        java.awt.Robot.class
        ColorGrabEvent cge = new ColorGrabEvent("0", "1", this);
        eventManager.fireEvent(cge);
    }

    public void mousePressed(MouseEvent e) {
        
    }

    public void mouseReleased(MouseEvent e) {
        
    }

    public void mouseEntered(MouseEvent e) {
        
    }

    public void mouseExited(MouseEvent e) {
        
    }

    
    


    
    
    
    
}
