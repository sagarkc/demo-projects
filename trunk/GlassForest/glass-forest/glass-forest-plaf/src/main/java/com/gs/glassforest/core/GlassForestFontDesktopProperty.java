/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.glassforest.core.GlassForestFontDesktopProperty
 * Date     : May 20, 2013
 */
package com.gs.glassforest.core;

import java.awt.Font;

import com.gs.glassforest.theme.DefaultGlassForestTheme;

/**
 * @author Sabuj Das
 *
 */
public class GlassForestFontDesktopProperty extends com.sun.java.swing.plaf.windows.DesktopProperty {
    /**
     * Maps from metal font theme type as defined in MetalTheme
     * to the corresponding desktop property name.
     */
    private static final String[] propertyMapping = {
        "win.ansiVar.font.height",
        "win.tooltip.font.height",
        "win.ansiVar.font.height",
        "win.menu.font.height",
        "win.frame.captionFont.height",
        "win.menu.font.height"
    };

    /**
     * Corresponds to a MetalTheme font type.
     */
    private int type;


    /**
     * Creates a MetalFontDesktopProperty. The key used to lookup the
     * desktop property is determined from the type of font.
     *
     * @param type MetalTheme font type.
     */
    public GlassForestFontDesktopProperty(int type) {
        this(propertyMapping[type], type);
    }

    /**
     * Creates a MetalFontDesktopProperty.
     *
     * @param key Key used in looking up desktop value.
     * @param toolkit Toolkit used to fetch property from, can be null
     *        in which default will be used.
     * @param type Type of font being used, corresponds to MetalTheme font
     *        type.
     */
    public GlassForestFontDesktopProperty(String key, int type) {
        super(key, null);
        this.type = type;
    }

    /**
     * Overriden to create a Font with the size coming from the desktop
     * and the style and name coming from DefaultMetalTheme.
     */
    protected Object configureValue(Object value) {
        if (value instanceof Integer) {
            value = new Font(DefaultGlassForestTheme.getDefaultFontName(type),
            		DefaultGlassForestTheme.getDefaultFontStyle(type),
                             ((Integer)value).intValue());
        }
        return super.configureValue(value);
    }

    /**
     * Returns the default font.
     */
    protected Object getDefaultValue() {
        return new Font(DefaultGlassForestTheme.getDefaultFontName(type),
        		DefaultGlassForestTheme.getDefaultFontStyle(type),
        		DefaultGlassForestTheme.getDefaultFontSize(type));
    }
}
