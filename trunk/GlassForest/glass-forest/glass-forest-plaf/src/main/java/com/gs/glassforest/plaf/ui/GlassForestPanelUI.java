/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.glassforest.plaf.ui.GlassForestPanelUI
 * Date     : May 21, 2013
 */

package com.gs.glassforest.plaf.ui;

import com.gs.glassforest.plaf.UiConstants;
import static com.gs.glassforest.plaf.ui.GlassForestLabelUI.glassForestPanelUI;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPanelUI;
import sun.awt.AppContext;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class GlassForestPanelUI extends BasicPanelUI{

    protected static GlassForestPanelUI glassForestPanelUI = new GlassForestPanelUI();

    private static final Object GLASS_FOREST_PANEL_UI_KEY = new Object();
    
    public static ComponentUI createUI(JComponent c) {
        if (System.getSecurityManager() != null) {
            AppContext appContext = AppContext.getAppContext();
            GlassForestPanelUI safePanelUI =
                    (GlassForestPanelUI) appContext.get(GLASS_FOREST_PANEL_UI_KEY);
            if (safePanelUI == null) {
                safePanelUI = new GlassForestPanelUI();
                appContext.put(GLASS_FOREST_PANEL_UI_KEY, safePanelUI);
            }
            return safePanelUI;
        }
        return glassForestPanelUI;
    }

    @Override
    protected void installDefaults(JPanel c) {
        super.installDefaults(c); 
        c.setFont(UiConstants.Fonts.TAHOMA_PLAIN);
        c.setBackground(UiConstants.PanelColors.BACKGROUND);
    }
    
}
