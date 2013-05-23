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

import com.gs.tools.colorhound.event.ApplicationEventManager;
import com.gs.tools.colorhound.event.ColorGrabEvent;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public ImagePanel() {
        
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void mouseClicked(MouseEvent e) {
        java.awt.Robot robot;
        try {
            robot = new Robot();
            ColorGrabEvent cge = new ColorGrabEvent(null, robot.getPixelColor(e.getX(), e.getY()), this);
        eventManager.fireEvent(cge);
        } catch (AWTException ex) {
            
        }
        
    }

    public void mousePressed(MouseEvent e) {
        
    }

    public void mouseReleased(MouseEvent e) {
        
    }

    public void mouseEntered(MouseEvent e) {
        
    }

    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void paintComponent(Graphics g){
 g.setColor(Color.red);
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            double leftX = 10;
            double topY = 10;
            double width = 600;
            double height = 600;
            Rectangle2D rect = new Rectangle2D.Double(leftX, topY, width, height);
            g2.draw(rect);
        }
    


    
    
    
    
}
