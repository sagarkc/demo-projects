/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.glassforest.plaf.ui.GlassForestButtonUI
 * Date     : May 20, 2013
 */
package com.gs.glassforest.plaf.ui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicButtonListener;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.text.View;

import sun.awt.AppContext;
import sun.reflect.generics.tree.BottomSignature;
import sun.swing.SwingUtilities2;

import com.gs.glassforest.plaf.UiConstants;
import com.gs.glassforest.util.GlassForestUtils;

/**
 * @author Sabuj Das
 *
 */
public class GlassForestButtonUI extends BasicButtonUI {
    
	private static Rectangle viewRect = new Rectangle();
    private static Rectangle textRect = new Rectangle();
    private static Rectangle iconRect = new Rectangle();
	
	private static final Object GF_BUTTON_UI_KEY = new Object();
	// Crate UI
	public static ComponentUI createUI(JComponent c) {
        AppContext appContext = AppContext.getAppContext();
        GlassForestButtonUI gfButtonUI =
                (GlassForestButtonUI) appContext.get(GF_BUTTON_UI_KEY);
        if (gfButtonUI == null) {
            gfButtonUI = new GlassForestButtonUI();
            appContext.put(GF_BUTTON_UI_KEY, gfButtonUI);
        }
        return gfButtonUI;
    }
	
	private String layout(AbstractButton b, FontMetrics fm, int width,
			int height) {
		Insets i = b.getInsets();
		viewRect.x = i.left;
		viewRect.y = i.top;
		viewRect.width = width - (i.right + viewRect.x);
		viewRect.height = height - (i.bottom + viewRect.y);

		textRect.x = textRect.y = textRect.width = textRect.height = 0;
		iconRect.x = iconRect.y = iconRect.width = iconRect.height = 0;

		// layout the text and icon
		return SwingUtilities.layoutCompoundLabel(b, fm, b.getText(),
				b.getIcon(), b.getVerticalAlignment(),
				b.getHorizontalAlignment(), b.getVerticalTextPosition(),
				b.getHorizontalTextPosition(), viewRect, iconRect, textRect,
				b.getText() == null ? 0 : b.getIconTextGap());
	}
	

	public void installDefaults(AbstractButton b) {
        super.installDefaults(b);
    }

    public void uninstallDefaults(AbstractButton b) {
        super.uninstallDefaults(b);
    }
    
    protected BasicButtonListener createButtonListener(AbstractButton b) {
        return super.createButtonListener(b);
    }
    
    @Override
    public void paint(Graphics graphics, JComponent c) {
    	AbstractButton button = (AbstractButton)c;
    	ButtonModel model = button.getModel();
    	int w = c.getWidth();
		int h = c.getHeight();
		Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(UiConstants.ButtonsColors.BORDER);
		g.drawRect(0, 0, w, h);
        Rectangle2D r = new Rectangle2D.Double(1, 1, w - 1, h - 1);
        GradientPaint gp = new GradientPaint(
                0, 0,
                UiConstants.ButtonsColors.GRAD_TOP,
                0, h / 2,
                UiConstants.ButtonsColors.GRAD_BOTTOM,
                true);
        if(button.isRolloverEnabled() && model.isRollover() && button.isEnabled()){
        	g.setColor(UiConstants.ButtonsColors.HOVER_BORDER);
    		g.drawRect(0, 0, w, h);
    		r = new Rectangle2D.Double(1, 1, w - 1, h - 1);
            gp = new GradientPaint(
                    0, 0,
                    UiConstants.ButtonsColors.HOVER_GRAD_TOP,
                    0, h / 2,
                    UiConstants.ButtonsColors.HOVER_GRAD_BOTTOM,
                    true);
            g.setPaint(gp);
            g.fill(r);
        } else {
        	g.setColor(UiConstants.ButtonsColors.BORDER);
    		g.drawRect(0, 0, w, h);
    		r = new Rectangle2D.Double(1, 1, w - 1, h - 1);
    	    gp = new GradientPaint(
	                0, 0,
	                UiConstants.ButtonsColors.GRAD_TOP,
	                0, h / 2,
	                UiConstants.ButtonsColors.GRAD_BOTTOM,
	                true);
            g.setPaint(gp);
            g.fill(r);
        }
        
        
        String text = layout(button, SwingUtilities2.getFontMetrics(button, g),
               button.getWidth(), button.getHeight());

        clearTextShiftOffset();

        // perform UI specific press action, e.g. Windows L&F shifts text
        if (model.isArmed() && model.isPressed()) {
            paintButtonPressed(g,button);
        }

        // Paint the Icon
        if(button.getIcon() != null) {
            paintIcon(g,c,iconRect);
        }

        if (text != null && !text.equals("")){
            View v = (View) c.getClientProperty(BasicHTML.propertyKey);
            if (v != null) {
                v.paint(g, textRect);
            } else {
                paintText(g, button, textRect, text);
            }
        }

        if (button.isFocusPainted() && button.hasFocus()) {
            // paint UI specific focus
            paintFocus(g,button,viewRect,textRect,iconRect);
        }
        
        
    }
    
    
    @Override
    protected void paintText(Graphics g, AbstractButton b, Rectangle textRect,
    		String text) {
        ButtonModel model = b.getModel();
        FontMetrics fm = SwingUtilities2.getFontMetrics(b, g);
        int mnemonicIndex = b.getDisplayedMnemonicIndex();

        /* Draw the Text */
        if(model.isEnabled() && !(model.isArmed() || model.isPressed())) {
            /*** paint the text normally */
            g.setColor(UiConstants.ButtonsColors.ENABLED_FB);
            SwingUtilities2.drawStringUnderlineCharAt(b, g,text, mnemonicIndex,
                                          textRect.x + getTextShiftOffset(),
                                          textRect.y + fm.getAscent() + getTextShiftOffset());
        } else if(model.isArmed() || model.isPressed()){
        	g.setColor(UiConstants.ButtonsColors.PRESSED_FG);
            SwingUtilities2.drawStringUnderlineCharAt(b, g,text, mnemonicIndex,
                                          textRect.x, textRect.y + fm.getAscent());
        }
        else {
            /*** paint the text disabled ***/
            g.setColor(UiConstants.ButtonsColors.DISABLED_FG);
            SwingUtilities2.drawStringUnderlineCharAt(b, g,text, mnemonicIndex,
                                          textRect.x, textRect.y + fm.getAscent());
            g.setColor(b.getBackground().darker());
            SwingUtilities2.drawStringUnderlineCharAt(b, g,text, mnemonicIndex,
                                          textRect.x - 1, textRect.y + fm.getAscent() - 1);
        }
    }
    
    
    
    protected void paintButtonPressed(Graphics graphics, AbstractButton b) {
        if ( b.isContentAreaFilled() ) {
            int w = b.getWidth();
    		int h = b.getHeight();
    		int x = 0;//b.getX();
    		int y = 0;//b.getY();
    		Graphics2D g = (Graphics2D) graphics;
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            
    		g.setColor(UiConstants.ButtonsColors.SELECTED_OUTTER_BORDER);
    		g.drawRect(0, 0, w, h);
    		
    		Rectangle2D r = new Rectangle2D.Double(x + 1, y + 1, w - 1, h - 1);
            GradientPaint gp = new GradientPaint(
                    0, 0,
                    UiConstants.ButtonsColors.SELECTED_BG_GRAD_TOP,
                    0, h / 2,
                    UiConstants.ButtonsColors.SELECTED_BG_GRAD_BOTTOM,
                    true);

            g.setPaint(gp);
            g.fill(r);
        }
    }

    protected void paintFocus(Graphics graphics, AbstractButton b,
                              Rectangle viewRect, Rectangle textRect, Rectangle iconRect){
    	Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Rectangle focusRect = new Rectangle(2, 2, b.getWidth()-4, b.getHeight()-4);
        g.setColor(UiConstants.ButtonsColors.FOCUS_COLOR);
        BasicGraphicsUtils.drawDashedRect(g, (int)focusRect.getX(), (int)focusRect.getY(),
        		(int)focusRect.getWidth(), (int)focusRect.getHeight());
        
    }


    protected void paintText(Graphics g, JComponent c, Rectangle textRect, String text) {
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();
        FontMetrics fm = SwingUtilities2.getFontMetrics(c, g);
        int mnemIndex = b.getDisplayedMnemonicIndex();

        /* Draw the Text */
        if(model.isEnabled()) {
            /*** paint the text normally */
            g.setColor(b.getForeground());
        }
        else {
            /*** paint the text disabled ***/
            g.setColor(Color.GRAY);
        }
        SwingUtilities2.drawStringUnderlineCharAt(c, g,text,mnemIndex,
                                  textRect.x, textRect.y + fm.getAscent());
    }
}