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

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicGraphicsUtils;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class ColorPanel extends JPanel implements MouseListener{

    private final JPanel parentPanel;
    private Color selectedColor;
    private String colorHexCode;
    private boolean selected = false;
    
    private final static int MAX_WIDTH = 34;
    private final static int MAX_HEIGHT = 34;
    private boolean colorGrabbed = false;
    

    public ColorPanel(JPanel parent) {
        this.parentPanel = parent;
        selectedColor = Color.WHITE;
        colorHexCode = Color.BLACK.toString();
        Dimension d = new Dimension(MAX_WIDTH, MAX_HEIGHT);
        setMaximumSize(d);
        setMinimumSize(d);
        setPreferredSize(d);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setLayout(null);
        addMouseListener(this);
        
    }

    public JPanel getParentPanel() {
        return parentPanel;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
    }

    public void mouseClicked(MouseEvent e) {
        selected = true;
        ColorPanelManager.getInstance().selectPanel(this);
        updateUI();
        parentPanel.updateUI();
        
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
        g.setColor((selected ? Color.ORANGE : Color.BLUE));
        BasicGraphicsUtils.drawDashedRect(g, 0, 0, MAX_WIDTH, MAX_HEIGHT);
        if(null != selectedColor){
            g.setColor(selectedColor);
            g.fillRect(4, 4, MAX_WIDTH-7, MAX_HEIGHT-7);
        }
    }

    public void setColorGrabbed(boolean b) {
        this.colorGrabbed = b;
    }

    public Color getSelectedColor() {
        return selectedColor;
    }

    public String getColorHexCode() {
        return colorHexCode;
    }

    public boolean isSelected() {
        return selected;
    }

    public boolean isColorGrabbed() {
        return colorGrabbed;
    }

    
    
    
    
    
    
}
