/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.tools.colorhound.ui.ColorPanel
 * Date     : May 23, 2013
 */

package com.gs.tools.colorhound.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class ColorPanel extends JPanel implements MouseListener{

    private Color selectedColor;
    private String colorHexCode;
    
    private final static int MAX_WIDTH = 24;
    private final static int MAX_HEIGHT = 24;
    

    public ColorPanel() {
        selectedColor = Color.BLACK;
        colorHexCode = Color.BLACK.toString();
        Dimension d = new Dimension(MAX_WIDTH, MAX_HEIGHT);
        setMaximumSize(d);
        setMinimumSize(d);
        setPreferredSize(d);
        setBorder(null);
        setLayout(null);
    }

    public void mouseClicked(MouseEvent e) {
        
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
    public void paint(Graphics g) {
        
    }
    
    
    
    
}
