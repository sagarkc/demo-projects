/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.tools.colorhound.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicGraphicsUtils;

/**
 *
 * @author Sabuj
 */
public class EnlargedImagePanel extends JPanel{

    private final Color LINE_COLOR = Color.decode("0xFF988F");
    private Image image;
    private Dimension d = new Dimension(200, 100);
    
    public EnlargedImagePanel() {
        setDoubleBuffered(true);
        setPreferredSize(d);
        setMinimumSize(d);
        setMaximumSize(d);
        setLayout(null);
    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        if(null != image){
            g.drawImage(image, 0, 0, d.width, d.height, null);
        } else {
            super.paint(g);
        }
        g.setColor(LINE_COLOR);
        BasicGraphicsUtils.drawDashedRect(g, 100, 0, 0, 100);
        //BasicGraphicsUtils.drawDashedRect(g, 0, 50, 200, 0);
    }

    public void setImage(Image image) {
        this.image = image;
        repaint();
    }
    
    
    
}
