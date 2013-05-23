/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.glassforest.theme.DefaultGlassForestTheme
 * Date     : May 20, 2013
 */
package com.gs.glassforest.theme;

import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

import sun.awt.AppContext;
import sun.security.action.GetPropertyAction;
import sun.swing.SwingUtilities2;

import com.gs.glassforest.core.GlassForestFontDesktopProperty;
import com.gs.glassforest.plaf.GlassForestLookAndFeel;
import com.gs.glassforest.plaf.UiConstants;

/**
 * @author Sabuj Das
 *
 */
public class DefaultGlassForestTheme extends GlassForestTheme{

	public static final String BOLD_FONT_PROP = "swing.boldGlassForest";
	public static final String THEME_NAME = "GlassForest";
	/**
     * Whether or not fonts should be plain.  This is only used if
     * the defaults property 'swing.boldMetal' == "false".
     */
    private static final boolean PLAIN_FONTS;
    
    static {
        Object boldProperty = java.security.AccessController.doPrivileged(
            new GetPropertyAction(BOLD_FONT_PROP));
        if (boldProperty == null || !"false".equals(boldProperty)) {
            PLAIN_FONTS = false;
        }
        else {
            PLAIN_FONTS = true;
        }
    }
    
    /**
     * Names of the fonts to use.
     */
    private static final String[] fontNames = {
    	UiConstants.Fonts.DEFAULT_FONT.getFontName(),
    	UiConstants.Fonts.DEFAULT_FONT.getFontName(),
    	UiConstants.Fonts.DEFAULT_FONT.getFontName(),
    	UiConstants.Fonts.DEFAULT_FONT.getFontName(),
    	UiConstants.Fonts.DEFAULT_FONT.getFontName(),
    	UiConstants.Fonts.DEFAULT_FONT.getFontName()
    };
    
    /**
     * Styles for the fonts.  This is ignored if the defaults property
     * <code>swing.boldMetal</code> is false, or PLAIN_FONTS is true.
     */
    private static final int[] fontStyles = {
        Font.BOLD, 
        Font.PLAIN, 
        Font.PLAIN, 
        Font.BOLD, 
        Font.BOLD, 
        Font.PLAIN
    };
    /**
     * Sizes for the fonts.
     */
    private static final int[] fontSizes = {
        12, 12, 12, 12, 12, 10
    };
    
    /**
     * System property names used to look up fonts.
     */
    private static final String[] defaultNames = {
        "swing.plaf.glassForest.controlFont",
        "swing.plaf.glassForest.systemFont",
        "swing.plaf.glassForest.userFont",
        "swing.plaf.glassForest.controlFont",
        "swing.plaf.glassForest.controlFont",
        "swing.plaf.glassForest.smallFont"
    };
    
    /**
     * Returns the ideal font name for the font identified by key.
     */
    public static String getDefaultFontName(int key) {
        return fontNames[key];
    }

    /**
     * Returns the ideal font size for the font identified by key.
     */
    public static int getDefaultFontSize(int key) {
        return fontSizes[key];
    }
    
    /**
     * Returns the ideal font style for the font identified by key.
     */
    public static int getDefaultFontStyle(int key) {
        if (key != WINDOW_TITLE_FONT) {
            Object boldGlassForest = null;
            if (AppContext.getAppContext().get(
                    SwingUtilities2.LAF_STATE_KEY) != null) {
                // Only access the boldMetal key if a look and feel has
                // been loaded, otherwise we'll trigger loading the look
                // and feel.
                boldGlassForest = UIManager.get(BOLD_FONT_PROP);
            }
            if (boldGlassForest != null) {
                if (Boolean.FALSE.equals(boldGlassForest)) {
                    return Font.PLAIN;
                }
            }
            else if (PLAIN_FONTS) {
                return Font.PLAIN;
            }
        }
        return fontStyles[key];
    }

    /**
     * Returns the default used to look up the specified font.
     */
    static String getDefaultPropertyName(int key) {
        return defaultNames[key];
    }

    private FontDelegate fontDelegate;
    
    /**
	 * 
	 */
	public DefaultGlassForestTheme() {
		install();
	}
    
    /* (non-Javadoc)
     * @see com.gs.glassforest.theme.GlassForestTheme#install()
     */
    @Override
    public void install() {
    	if (GlassForestLookAndFeel.isWindows() &&
    			GlassForestLookAndFeel.useSystemFonts()) {
    		fontDelegate = new WindowsFontDelegate();
		}
		else {
			fontDelegate = new FontDelegate();
		}
    }
    
	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getName()
	 */
	@Override
	public String getName() {
		return THEME_NAME;
	}
	
	public boolean isSystemTheme() {
        return (getClass() == DefaultGlassForestTheme.class);
    }

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getControlTextFont()
	 */
	@Override
	public FontUIResource getControlTextFont() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getSystemTextFont()
	 */
	@Override
	public FontUIResource getSystemTextFont() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getUserTextFont()
	 */
	@Override
	public FontUIResource getUserTextFont() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getMenuTextFont()
	 */
	@Override
	public FontUIResource getMenuTextFont() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getWindowTitleFont()
	 */
	@Override
	public FontUIResource getWindowTitleFont() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getSubTextFont()
	 */
	@Override
	public FontUIResource getSubTextFont() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getFocusColor()
	 */
	@Override
	public ColorUIResource getFocusColor() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getDesktopColor()
	 */
	@Override
	public ColorUIResource getDesktopColor() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getControl()
	 */
	@Override
	public ColorUIResource getControl() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getControlShadow()
	 */
	@Override
	public ColorUIResource getControlShadow() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getControlDarkShadow()
	 */
	@Override
	public ColorUIResource getControlDarkShadow() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getControlInfo()
	 */
	@Override
	public ColorUIResource getControlInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getControlHighlight()
	 */
	@Override
	public ColorUIResource getControlHighlight() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getControlDisabled()
	 */
	@Override
	public ColorUIResource getControlDisabled() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getPrimaryControl()
	 */
	@Override
	public ColorUIResource getPrimaryControl() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getPrimaryControlShadow()
	 */
	@Override
	public ColorUIResource getPrimaryControlShadow() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getPrimaryControlDarkShadow()
	 */
	@Override
	public ColorUIResource getPrimaryControlDarkShadow() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getInactiveSystemTextColor()
	 */
	@Override
	public ColorUIResource getInactiveSystemTextColor() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getTextHighlightColor()
	 */
	@Override
	public ColorUIResource getTextHighlightColor() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getWindowTitleBackground()
	 */
	@Override
	public ColorUIResource getWindowTitleBackground() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getWindowTitleInactiveBackground()
	 */
	@Override
	public ColorUIResource getWindowTitleInactiveBackground() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getMenuBackground()
	 */
	@Override
	public ColorUIResource getMenuBackground() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getMenuSelectedBackground()
	 */
	@Override
	public ColorUIResource getMenuSelectedBackground() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getMenuDisabledForeground()
	 */
	@Override
	public ColorUIResource getMenuDisabledForeground() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getSeparatorForeground()
	 */
	@Override
	public ColorUIResource getSeparatorForeground() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.glassforest.theme.GlassForestTheme#getAcceleratorForeground()
	 */
	@Override
	public ColorUIResource getAcceleratorForeground() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static class FontDelegate {
        private static int[] defaultMapping = {
            CONTROL_TEXT_FONT, SYSTEM_TEXT_FONT,
            USER_TEXT_FONT, CONTROL_TEXT_FONT,
            CONTROL_TEXT_FONT, SUB_TEXT_FONT
        };
        FontUIResource fonts[];

        // menu and window are mapped to controlFont
        public FontDelegate() {
            fonts = new FontUIResource[6];
        }

        public FontUIResource getFont(int type) {
            int mappedType = defaultMapping[type];
            if (fonts[type] == null) {
                Font f = getPrivilegedFont(mappedType);

                if (f == null) {
                    f = new Font(getDefaultFontName(type),
                             getDefaultFontStyle(type),
                             getDefaultFontSize(type));
                }
                fonts[type] = new FontUIResource(f);
            }
            return fonts[type];
        }

        /**
         * This is the same as invoking
         * <code>Font.getFont(key)</code>, with the exception
         * that it is wrapped inside a <code>doPrivileged</code> call.
         */
        protected Font getPrivilegedFont(final int key) {
            return java.security.AccessController.doPrivileged(
                new java.security.PrivilegedAction<Font>() {
                    public Font run() {
                        return Font.getFont(getDefaultPropertyName(key));
                    }
                }
                );
        }
    }

	private static class WindowsFontDelegate extends FontDelegate {
        private GlassForestFontDesktopProperty[] props;
        private boolean[] checkedPriviledged;

        public WindowsFontDelegate() {
            props = new GlassForestFontDesktopProperty[6];
            checkedPriviledged = new boolean[6];
        }

        public FontUIResource getFont(int type) {
            if (fonts[type] != null) {
                return fonts[type];
            }
            if (!checkedPriviledged[type]) {
                Font f = getPrivilegedFont(type);

                checkedPriviledged[type] = true;
                if (f != null) {
                    fonts[type] = new FontUIResource(f);
                    return fonts[type];
                }
            }
            if (props[type] == null) {
                props[type] = new GlassForestFontDesktopProperty(type);
            }
            // While passing null may seem bad, we don't actually use
            // the table and looking it up is rather expensive.
            return (FontUIResource)props[type].createValue(null);
        }
    }
}
