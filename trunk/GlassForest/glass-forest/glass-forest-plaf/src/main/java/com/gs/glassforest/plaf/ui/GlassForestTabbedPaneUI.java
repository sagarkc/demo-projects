/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.glassforest.plaf.ui.GlassForestTabbedPaneUI
 * Date     : May 21, 2013
 */
package com.gs.glassforest.plaf.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.text.View;

import sun.swing.SwingUtilities2;

import com.gs.glassforest.plaf.UiConstants;
import com.gs.glassforest.plaf.UiConstants.TabColors;

/**
 * @author Sabuj Das
 *
 */
public class GlassForestTabbedPaneUI  extends BasicTabbedPaneUI {

    private static final int ADDED_TAB_HEIGTH = 2;
    private static final int ADDED_TAB_WIDTH = 10;
    private static final int SPACE_BETWEEN_TAB = 1;

    public static ComponentUI createUI(JComponent c) {
        return new GlassForestTabbedPaneUI();
    }

    protected LayoutManager createLayoutManager() {
        if (tabPane.getTabLayoutPolicy() == JTabbedPane.SCROLL_TAB_LAYOUT) {
            return super.createLayoutManager();
        }
        return new GlassForestTabbedPaneUI.TabbedPaneLayout();
    }
    
    public GlassForestTabbedPaneUI(){
        
    }
    
    public GlassForestTabbedPaneUI(JTabbedPane tabbedPane) {
        // FONT: BOLD
        tabbedPane.setFont(tabbedPane.getFont().deriveFont(Font.BOLD));

        // FONT: WHITE
        //tabbedPane.setForeground(Color.WHITE);

        // TAB BACKGROUND: BLUE (overrided to gray by paintTabBackground)
        //                 the background must be set to blue for the line
        //                 below the tabs to be blue, else it is the same
        //                 color as the tab background (would be gray) 
        tabbedPane.setBackground(UiConstants.TabColors.SELECTED_BG_GRAD_TOP);
    }

    // overrided to add more space each side of the tab title and spacing between tabs.
    protected void installDefaults() {
        super.installDefaults();

        // changed to add more space each side of the tab title.
        tabInsets.left = tabInsets.left + ADDED_TAB_WIDTH;
        tabInsets.right = tabInsets.right + ADDED_TAB_WIDTH;
        tabInsets.top = tabInsets.top + ADDED_TAB_HEIGTH;
        tabInsets.bottom = tabInsets.bottom + ADDED_TAB_HEIGTH;

        // changed to define the spacing between tabs.
        selectedTabPadInsets.left = SPACE_BETWEEN_TAB;
        selectedTabPadInsets.right = SPACE_BETWEEN_TAB;
    }

    
    
    @Override
    protected void paintTabBorder(Graphics graphics, int tabPlacement, int tabIndex,
            int x, int y, int w, int h, boolean isSelected) {
        if (null == graphics) {
            return;
        }
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        if(isSelected){
            g.setColor(TabColors.SELECTED_OUTTER_BORDER);
            g.drawRect(x, y, w, h);
            g.setColor(TabColors.SELECTED_INNER_BORDER);
            g.drawRect(x + 1, y + 1, w - 1, h - 1);
        } else {
            g.setColor(TabColors.BORDER);
            g.drawRect(x, y, w, h);
        }
        
    }

    @Override
    protected void paintTabBackground(Graphics graphics, int tabPlacement,
            int tabIndex, int x, int y, int w, int h, boolean isSelected) {

        if (null == graphics) {
            return;
        }
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if(isSelected){
            Rectangle2D r = new Rectangle2D.Double(x + 2, y + 2, w - 3, h - 3);
            GradientPaint gp = new GradientPaint(
                    0, 0,
                    TabColors.SELECTED_BG_GRAD_TOP,
                    0, h / 2,
                    TabColors.SELECTED_BG_GRAD_BOTTOM,
                    true);

            g.setPaint(gp);
            g.fill(r);
        } else {
            Rectangle2D r = new Rectangle2D.Double(x + 1, y + 1, w - 1, h - 1);
            GradientPaint gp = new GradientPaint(
                    0, 0,
                    TabColors.BACKGROUND_GRAD_TOP,
                    0, h / 2,
                    TabColors.BACKGROUND_GRAD_BOTTOM,
                    true);

            g.setPaint(gp);
            g.fill(r);
        }

    }

    @Override
    protected void paintText(Graphics graphics, int tabPlacement, Font font,
            FontMetrics metrics, int tabIndex, String title,
            Rectangle textRect, boolean isSelected) {
        if (null == graphics) {
            return;
        }
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setFont(font);

        View v = getTextViewForTab(tabIndex);
        if (v != null) {
            // html
            v.paint(g, textRect);
        } else {
            // plain text
            int mnemIndex = tabPane.getDisplayedMnemonicIndexAt(tabIndex);

            if (tabPane.isEnabled() && tabPane.isEnabledAt(tabIndex)) {
                Color fg = tabPane.getForegroundAt(tabIndex);
                if (isSelected && (fg instanceof UIResource)) {
                    Color selectedFG = TabColors.SELECTED_FOREGROUND;
                    if (selectedFG != null) {
                        fg = selectedFG;
                    }
                }
                g.setColor(fg);
                SwingUtilities2.drawStringUnderlineCharAt(tabPane, g,
                        title, mnemIndex,
                        textRect.x, textRect.y + metrics.getAscent());

            } else { // tab disabled
                g.setColor(tabPane.getBackgroundAt(tabIndex).brighter());
                SwingUtilities2.drawStringUnderlineCharAt(tabPane, g,
                        title, mnemIndex,
                        textRect.x, textRect.y + metrics.getAscent());
                g.setColor(tabPane.getBackgroundAt(tabIndex).darker());
                SwingUtilities2.drawStringUnderlineCharAt(tabPane, g,
                        title, mnemIndex,
                        textRect.x - 1, textRect.y + metrics.getAscent() - 1);

            }
        }
    }
}