/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.tools.trackdesk.ui.HeaderPanel
 * Date     : May 15, 2013
 */

package com.gs.tools.trackdesk.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class HeaderPanel extends javax.swing.JPanel {

    private String headerText;
    private ImageIcon icon;
    
    /** Creates new form HeaderPanel */
    public HeaderPanel(String headerText, ImageIcon icon) {
        setMinimumSize(new java.awt.Dimension(300, 42));
        setPreferredSize(new java.awt.Dimension(300, 42));
        this.headerText = headerText;
        this.icon = icon;
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, getWidth(), getHeight());
        
        RenderingHints hints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		hints.add(new RenderingHints(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY));
        
		g2.setRenderingHints(hints);
		int x = 0, y = 0, w = getWidth(), h = getHeight();
		int arch = 0;
		g2.setPaintMode();
		g2.setColor(UiConstants.HeaderColors.OUTTER_BORDER);
		g2.drawRoundRect(x, y, w, h, arch, arch);
		g2.setColor(UiConstants.HeaderColors.INNER_BORDER);
		g2.drawRoundRect(x+1, y+1, w-2, h-2, arch, arch);
        
        RoundRectangle2D r = new RoundRectangle2D.Double(x+2, y+2, w-3, h-3,arch,arch);
		GradientPaint gp = new GradientPaint(
				0, 0,
				UiConstants.HeaderColors.GRAD_TOP, 
				0, 55,
				UiConstants.HeaderColors.GRAD_BOTTOM, true);
		
		g2.setPaint(gp);
		g2.fill(r);
        g2.setColor(Color.BLACK);
        g2.setFont(UiConstants.Fonts.TAHOMA_BOLD.deriveFont(17.5F));
        g2.drawString(headerText, 5, 25);
		
        g2.drawString(headerText, getWidth()-80, 25);
        
    }
    
}