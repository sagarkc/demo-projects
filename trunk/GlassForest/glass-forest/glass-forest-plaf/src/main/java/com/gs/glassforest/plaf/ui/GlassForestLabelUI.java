/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.glassforest.plaf.ui.GlassForestLabelUI
 * Date     : May 21, 2013
 */
package com.gs.glassforest.plaf.ui;

import com.gs.glassforest.plaf.UiConstants;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicLabelUI;
import sun.awt.AppContext;
import sun.swing.SwingUtilities2;

/**
 * @author Sabuj Das
 *
 */
public class GlassForestLabelUI extends BasicLabelUI
{
   
    protected static GlassForestLabelUI glassForestPanelUI = new GlassForestLabelUI();

    private static final Object GLASS_FOREST_LABEL_UI_KEY = new Object();

    public static ComponentUI createUI(JComponent c) {
        if (System.getSecurityManager() != null) {
            AppContext appContext = AppContext.getAppContext();
            GlassForestLabelUI safeMetalLabelUI =
                    (GlassForestLabelUI) appContext.get(GLASS_FOREST_LABEL_UI_KEY);
            if (safeMetalLabelUI == null) {
                safeMetalLabelUI = new GlassForestLabelUI();
                appContext.put(GLASS_FOREST_LABEL_UI_KEY, safeMetalLabelUI);
            }
            return safeMetalLabelUI;
        }
        return glassForestPanelUI;
    }

    @Override
    protected void installDefaults(JLabel c) {
        super.installDefaults(c); 
        c.setFont(UiConstants.Fonts.TAHOMA_PLAIN);
    }

    
    
    /**
     * Just paint the text gray (Label.disabledForeground) rather than
     * in the labels foreground color.
     *
     * @see #paint
     * @see #paintEnabledText
     */
    protected void paintDisabledText(JLabel l, Graphics g, String s, int textX, int textY)
    {
        int mnemIndex = l.getDisplayedMnemonicIndex();
        g.setColor(UIManager.getColor("Label.disabledForeground"));
        SwingUtilities2.drawStringUnderlineCharAt(l, g, s, mnemIndex,
                                                   textX, textY);
    }
}