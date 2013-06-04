/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.glassforest.plaf.ui.GlassForestToggleButtonUI
 * Date     : May 21, 2013
 */
package com.gs.glassforest.plaf.ui;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicToggleButtonUI;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import sun.awt.AppContext;

/**
 * @author Sabuj Das
 *
 */
public class GlassForestToggleButtonUI extends BasicToggleButtonUI {

    private static final Object GF_TOGGLE_BUTTON_UI_KEY = new Object();
    
    public static ComponentUI createUI(JComponent b) {
        AppContext appContext = AppContext.getAppContext();
        GlassForestToggleButtonUI glassForestToggleButtonUI =
                (GlassForestToggleButtonUI) appContext.get(GF_TOGGLE_BUTTON_UI_KEY);
        if (glassForestToggleButtonUI == null) {
            glassForestToggleButtonUI = new GlassForestToggleButtonUI();
            appContext.put(GF_TOGGLE_BUTTON_UI_KEY, glassForestToggleButtonUI);
        }
        return glassForestToggleButtonUI;
    }
    
    
    
}
