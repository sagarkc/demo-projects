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
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicButtonListener;
import javax.swing.plaf.basic.BasicButtonUI;

import sun.awt.AppContext;
import sun.swing.SwingUtilities2;

import com.gs.glassforest.util.GlassForestUtils;

/**
 * @author Sabuj Das
 *
 */
public class GlassForestButtonUI extends BasicButtonUI {
    

    private static final Object GLASS_FOREST_BUTTON_UI_KEY = new Object();

    // ********************************
    //          Create PLAF
    // ********************************
    public static ComponentUI createUI(JComponent c) {
        AppContext appContext = AppContext.getAppContext();
        GlassForestButtonUI metalButtonUI =
                (GlassForestButtonUI) appContext.get(GLASS_FOREST_BUTTON_UI_KEY);
        if (metalButtonUI == null) {
            metalButtonUI = new GlassForestButtonUI();
            appContext.put(GLASS_FOREST_BUTTON_UI_KEY, metalButtonUI);
        }
        return metalButtonUI;
    }

    // ********************************
    //          Install
    // ********************************
    public void installDefaults(AbstractButton b) {
        super.installDefaults(b);
    }

    public void uninstallDefaults(AbstractButton b) {
        super.uninstallDefaults(b);
    }

    // ********************************
    //         Create Listeners
    // ********************************
    protected BasicButtonListener createButtonListener(AbstractButton b) {
        return super.createButtonListener(b);
    }



    // ********************************
    //          Paint
    // ********************************
    /**
     * If necessary paints the background of the component, then
     * invokes <code>paint</code>.
     *
     * @param g Graphics to paint to
     * @param c JComponent painting on
     * @throws NullPointerException if <code>g</code> or <code>c</code> is
     *         null
     * @see javax.swing.plaf.ComponentUI#update
     * @see javax.swing.plaf.ComponentUI#paint
     * @since 1.5
     */
    public void update(Graphics g, JComponent c) {
        AbstractButton button = (AbstractButton)c;
        if ((c.getBackground() instanceof UIResource) &&
                  button.isContentAreaFilled() && c.isEnabled()) {
            ButtonModel model = button.getModel();
            if (!GlassForestUtils.isToolBarButton(c)) {
                if (!model.isArmed() && !model.isPressed() &&
                		GlassForestUtils.drawGradient(
                        c, g, "Button.gradient", 0, 0, c.getWidth(),
                        c.getHeight(), true)) {
                    paint(g, c);
                    return;
                }
            }
            else if (model.isRollover() && GlassForestUtils.drawGradient(
                        c, g, "Button.gradient", 0, 0, c.getWidth(),
                        c.getHeight(), true)) {
                paint(g, c);
                return;
            }
        }
        super.update(g, c);
    }

    protected void paintButtonPressed(Graphics g, AbstractButton b) {
        if ( b.isContentAreaFilled() ) {
            Dimension size = b.getSize();
            g.setColor(getSelectColor());
            g.fillRect(0, 0, size.width, size.height);
        }
    }

    protected void paintFocus(Graphics g, AbstractButton b,
                              Rectangle viewRect, Rectangle textRect, Rectangle iconRect){

        Rectangle focusRect = new Rectangle();
        String text = b.getText();
        boolean isIcon = b.getIcon() != null;

        // If there is text
        if ( text != null && !text.equals( "" ) ) {
            if ( !isIcon ) {
                focusRect.setBounds( textRect );
            }
            else {
                focusRect.setBounds( iconRect.union( textRect ) );
            }
        }
        // If there is an icon and no text
        else if ( isIcon ) {
            focusRect.setBounds( iconRect );
        }

        g.setColor(getFocusColor());
        g.drawRect((focusRect.x-1), (focusRect.y-1),
                  focusRect.width+1, focusRect.height+1);

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
            g.setColor(getDisabledTextColor());
        }
        SwingUtilities2.drawStringUnderlineCharAt(c, g,text,mnemIndex,
                                  textRect.x, textRect.y + fm.getAscent());
    }
}