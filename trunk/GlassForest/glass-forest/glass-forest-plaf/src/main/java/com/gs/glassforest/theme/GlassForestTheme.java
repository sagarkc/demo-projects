/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.glassforest.theme.GlassForestTheme
 * Date     : May 20, 2013
 */
package com.gs.glassforest.theme;

import javax.swing.UIDefaults;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 * @author Sabuj Das
 *
 */
public abstract class GlassForestTheme {


	// Contants identifying the various Fonts that are Theme can support
    public static final int CONTROL_TEXT_FONT = 0;
    public static final int SYSTEM_TEXT_FONT = 1;
    public static final int USER_TEXT_FONT = 2;
    public static final int MENU_TEXT_FONT = 3;
    public static final int WINDOW_TITLE_FONT = 4;
    public static final int SUB_TEXT_FONT = 5;

    static ColorUIResource white = new ColorUIResource( 255, 255, 255 );
    private static ColorUIResource black = new ColorUIResource( 0, 0, 0 );

    /**
     * Returns the name of this theme.
     *
     * @return the name of this theme
     */
    public abstract String getName();

   

    /**
     * Returns the control text font.
     *
     * @return the control text font
     */
    public abstract FontUIResource getControlTextFont();

    /**
     * Returns the system text font.
     *
     * @return the system text font
     */
    public abstract FontUIResource getSystemTextFont();

    /**
     * Returns the user text font.
     *
     * @return the user text font
     */
    public abstract FontUIResource getUserTextFont();

    /**
     * Returns the menu text font.
     *
     * @return the menu text font
     */
    public abstract FontUIResource getMenuTextFont();

    /**
     * Returns the window title font.
     *
     * @return the window title font
     */
    public abstract FontUIResource getWindowTitleFont();

    /**
     * Returns the sub-text font.
     *
     * @return the sub-text font
     */
    public abstract FontUIResource getSubTextFont();

    /**
     * Returns the white color. This returns opaque white
     * ({@code 0xFFFFFFFF}).
     *
     * @return the white color
     */
    public ColorUIResource getWhite() { return white; }

    /**
     * Returns the black color. This returns opaque black
     * ({@code 0xFF000000}).
     *
     * @return the black color
     */
    public ColorUIResource getBlack() { return black; }

    /**
     * Returns the focus color. This returns the value of
     * {@code getPrimary2()}.
     *
     * @return the focus color
     */
    public abstract ColorUIResource getFocusColor() ;

    /**
     * Returns the desktop color. This returns the value of
     * {@code getPrimary2()}.
     *
     * @return the desktop color
     */
    public abstract ColorUIResource getDesktopColor();

    /**
     * Returns the control color. This returns the value of
     * {@code getSecondary3()}.
     *
     * @return the control color
     */
    public abstract ColorUIResource getControl();

    /**
     * Returns the control shadow color. This returns
     * the value of {@code getSecondary2()}.
     *
     * @return the control shadow color
     */
    public abstract ColorUIResource getControlShadow();

    /**
     * Returns the control dark shadow color. This returns
     * the value of {@code getSecondary1()}.
     *
     * @return the control dark shadow color
     */
    public abstract ColorUIResource getControlDarkShadow();

    /**
     * Returns the control info color. This returns
     * the value of {@code getBlack()}.
     *
     * @return the control info color
     */
    public abstract ColorUIResource getControlInfo();

    /**
     * Returns the control highlight color. This returns
     * the value of {@code getWhite()}.
     *
     * @return the control highlight color
     */
    public abstract ColorUIResource getControlHighlight();

    /**
     * Returns the control disabled color. This returns
     * the value of {@code getSecondary2()}.
     *
     * @return the control disabled color
     */
    public abstract ColorUIResource getControlDisabled() ;

    /**
     * Returns the primary control color. This returns
     * the value of {@code getPrimary3()}.
     *
     * @return the primary control color
     */
    public abstract ColorUIResource getPrimaryControl();

    /**
     * Returns the primary control shadow color. This returns
     * the value of {@code getPrimary2()}.
     *
     * @return the primary control shadow color
     */
    public abstract ColorUIResource getPrimaryControlShadow();
    /**
     * Returns the primary control dark shadow color. This
     * returns the value of {@code getPrimary1()}.
     *
     * @return the primary control dark shadow color
     */
    public abstract ColorUIResource getPrimaryControlDarkShadow() ;

    /**
     * Returns the primary control info color. This
     * returns the value of {@code getBlack()}.
     *
     * @return the primary control info color
     */
    public ColorUIResource getPrimaryControlInfo() { return getBlack(); }

    /**
     * Returns the primary control highlight color. This
     * returns the value of {@code getWhite()}.
     *
     * @return the primary control highlight color
     */
    public ColorUIResource getPrimaryControlHighlight() { return getWhite(); }

    /**
     * Returns the system text color. This returns the value of
     * {@code getBlack()}.
     *
     * @return the system text color
     */
    public ColorUIResource getSystemTextColor() { return getBlack(); }

    /**
     * Returns the control text color. This returns the value of
     * {@code getControlInfo()}.
     *
     * @return the control text color
     */
    public ColorUIResource getControlTextColor() { return getControlInfo(); }

    /**
     * Returns the inactive control text color. This returns the value of
     * {@code getControlDisabled()}.
     *
     * @return the inactive control text color
     */
    public ColorUIResource getInactiveControlTextColor() { return getControlDisabled(); }

    /**
     * Returns the inactive system text color. This returns the value of
     * {@code getSecondary2()}.
     *
     * @return the inactive system text color
     */
    public abstract ColorUIResource getInactiveSystemTextColor() ;

    /**
     * Returns the user text color. This returns the value of
     * {@code getBlack()}.
     *
     * @return the user text color
     */
    public ColorUIResource getUserTextColor() { return getBlack(); }

    /**
     * Returns the text highlight color. This returns the value of
     * {@code getPrimary3()}.
     *
     * @return the text highlight color
     */
    public abstract ColorUIResource getTextHighlightColor() ;

    /**
     * Returns the highlighted text color. This returns the value of
     * {@code getControlTextColor()}.
     *
     * @return the highlighted text color
     */
    public ColorUIResource getHighlightedTextColor() { return getControlTextColor(); }

    /**
     * Returns the window background color. This returns the value of
     * {@code getWhite()}.
     *
     * @return the window background color
     */
    public ColorUIResource getWindowBackground() { return getWhite(); }

    /**
     * Returns the window title background color. This returns the value of
     * {@code getPrimary3()}.
     *
     * @return the window title background color
     */
    public abstract ColorUIResource getWindowTitleBackground() ;

    /**
     * Returns the window title foreground color. This returns the value of
     * {@code getBlack()}.
     *
     * @return the window title foreground color
     */
    public ColorUIResource getWindowTitleForeground() { return getBlack(); }

    /**
     * Returns the window title inactive background color. This
     * returns the value of {@code getSecondary3()}.
     *
     * @return the window title inactive background color
     */
    public abstract ColorUIResource getWindowTitleInactiveBackground() ;

    /**
     * Returns the window title inactive foreground color. This
     * returns the value of {@code getBlack()}.
     *
     * @return the window title inactive foreground color
     */
    public ColorUIResource getWindowTitleInactiveForeground() { return getBlack(); }

    /**
     * Returns the menu background color. This
     * returns the value of {@code getSecondary3()}.
     *
     * @return the menu background color
     */
    public abstract ColorUIResource getMenuBackground() ;

    /**
     * Returns the menu foreground color. This
     * returns the value of {@code getBlack()}.
     *
     * @return the menu foreground color
     */
    public ColorUIResource getMenuForeground() { return  getBlack(); }

    /**
     * Returns the menu selected background color. This
     * returns the value of {@code getPrimary2()}.
     *
     * @return the menu selected background color
     */
    public abstract ColorUIResource getMenuSelectedBackground() ;

    /**
     * Returns the menu selected foreground color. This
     * returns the value of {@code getBlack()}.
     *
     * @return the menu selected foreground color
     */
    public ColorUIResource getMenuSelectedForeground() { return getBlack(); }

    /**
     * Returns the menu disabled foreground color. This
     * returns the value of {@code getSecondary2()}.
     *
     * @return the menu disabled foreground color
     */
    public abstract ColorUIResource getMenuDisabledForeground() ;

    /**
     * Returns the separator background color. This
     * returns the value of {@code getWhite()}.
     *
     * @return the separator background color
     */
    public ColorUIResource getSeparatorBackground() { return getWhite(); }

    /**
     * Returns the separator foreground color. This
     * returns the value of {@code getPrimary1()}.
     *
     * @return the separator foreground color
     */
    public abstract ColorUIResource getSeparatorForeground() ;

    /**
     * Returns the accelerator foreground color. This
     * returns the value of {@code getPrimary1()}.
     *
     * @return the accelerator foreground color
     */
    public abstract ColorUIResource getAcceleratorForeground() ;

    /**
     * Returns the accelerator selected foreground color. This
     * returns the value of {@code getBlack()}.
     *
     * @return the accelerator selected foreground color
     */
    public ColorUIResource getAcceleratorSelectedForeground() { return getBlack(); }

    /**
     * Adds values specific to this theme to the defaults table. This method
     * is invoked when the look and feel defaults are obtained from
     * {@code MetalLookAndFeel}.
     * <p>
     * This implementation does nothing; it is provided for subclasses
     * that wish to customize the defaults table.
     *
     * @param table the {@code UIDefaults} to add the values to
     *
     * @see MetalLookAndFeel#getDefaults
     */
    public void addCustomEntriesToTable(UIDefaults table) {}

    /**
     * This is invoked when a MetalLookAndFeel is installed and about to
     * start using this theme. When we can add API this should be nuked
     * in favor of DefaultMetalTheme overriding addCustomEntriesToTable.
     */
    public void install() {
    	
    }

    /**
     * Returns true if this is a theme provided by the core platform.
     */
    public boolean isSystemTheme() {
        return false;
    }
	
    
    
}
