/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.glassforest.plaf.GlassForestLookAndFeel
 * Date     : May 20, 2013
 */
package com.gs.glassforest.plaf;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;

import javax.swing.ButtonModel;
import javax.swing.DefaultButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
import javax.swing.LookAndFeel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicLookAndFeel;
import javax.swing.plaf.metal.MetalIconFactory;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.synth.Region;
import javax.swing.plaf.synth.SynthStyle;
import javax.swing.plaf.synth.SynthStyleFactory;
import javax.swing.text.DefaultEditorKit;

import sun.awt.AppContext;
import sun.awt.OSInfo;
import sun.awt.SunToolkit;
import sun.security.action.GetPropertyAction;
import sun.swing.DefaultLayoutStyle;
import sun.swing.SwingLazyValue;
import sun.swing.SwingUtilities2;

import com.gs.glassforest.core.GlassForestFontDesktopProperty;
import com.gs.glassforest.theme.DefaultGlassForestTheme;
import com.gs.glassforest.theme.GlassForestHighContrastTheme;
import com.gs.glassforest.theme.GlassForestTheme;
import com.gs.glassforest.theme.OceanGlassForestTheme;
import com.gs.glassforest.util.GlassForestUtils;

/**
 * @author Sabuj Das
 * 
 */
public class GlassForestLookAndFeel extends BasicLookAndFeel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 154765468743654684L;

	/**
	 * 
	 */
	public static final String SWING_GLASS_FOREST_THEME = "swing.glassForestTheme";

	/**
	 * 
	 */
	public static final String APP_CTXT_THEME_KEY = "currentGlassForestTheme";

	private static boolean GLASS_FOREST_LOOK_AND_FEEL_INITED = false;

	/**
	 * The name of the Look & Feel
	 */
	public static final String GLASS_FOREST = "GlassForest";
	/**
	 * True if checked for windows yet.
	 */
	private static boolean checkedWindows;
	/**
	 * True if running on Windows.
	 */
	private static boolean isWindows;

	/**
	 * Set to true first time we've checked swing.useSystemFontSettings.
	 */
	private static boolean checkedSystemFontSettings;

	/**
	 * True indicates we should use system fonts, unless the developer has
	 * specified otherwise with Application.useSystemFontSettings.
	 */
	private static boolean useSystemFonts;

	@Override public void initialize() {
        super.initialize();
    }
	
	public String getName() {
		return GLASS_FOREST;
	}

	public String getID() {
		return GLASS_FOREST;
	}

	public String getDescription() {
		return "Glass Forest Look and Feel";
	}

	public boolean isNativeLookAndFeel() {
		return false;
	}

	public boolean isSupportedLookAndFeel() {
		return true;
	}

	public boolean getSupportsWindowDecorations() {
		return true;
	}

	/**
	 * Returns true if running on Windows.
	 */
	public static boolean isWindows() {
		if (!checkedWindows) {
			OSInfo.OSType osType = AccessController.doPrivileged(OSInfo
					.getOSTypeAction());
			if (osType == OSInfo.OSType.WINDOWS) {
				isWindows = true;
				String systemFonts = AccessController
						.doPrivileged(new GetPropertyAction(
								"swing.useSystemFontSettings"));
				useSystemFonts = (systemFonts != null && (Boolean
						.valueOf(systemFonts).booleanValue()));
			}
			checkedWindows = true;
		}
		return isWindows;
	}

	/**
	 * Returns true if system fonts should be used, this is only useful for
	 * windows.
	 */
	public static boolean useSystemFonts() {
		if (isWindows() && useSystemFonts) {
			if (GLASS_FOREST_LOOK_AND_FEEL_INITED) {
				Object value = UIManager
						.get("Application.useSystemFontSettings");

				return (value == null || Boolean.TRUE.equals(value));
			}
			// If an instanceof GlassForestLookAndFeel hasn't been inited yet, we
			// don't want to trigger loading of a UI by asking the UIManager
			// for a property, assume the user wants system fonts. This will
			// be properly adjusted when install is invoked on the
			// GlassForestTheme
			return true;
		}
		return false;
	}

	/**
	 * Returns true if the high contrast theme should be used as the default
	 * theme.
	 */
	private static boolean useHighContrastTheme() {
		if (isWindows() && useSystemFonts()) {
			Boolean highContrast = (Boolean) Toolkit.getDefaultToolkit()
					.getDesktopProperty("win.highContrast.on");

			return (highContrast == null) ? false : highContrast.booleanValue();
		}
		return false;
	}

	public static void setCurrentTheme(GlassForestTheme theme) {
		// NOTE: because you need to recreate the look and feel after
		// this step, we don't bother blowing away any potential windows
		// values.
		if (theme == null) {
			throw new NullPointerException("Can't have null theme");
		}
		AppContext.getAppContext().put(APP_CTXT_THEME_KEY, theme);
	}

	public static GlassForestTheme getCurrentTheme() {
		GlassForestTheme currentTheme;
		AppContext context = AppContext.getAppContext();
		currentTheme = (GlassForestTheme) context.get(APP_CTXT_THEME_KEY);
		if (currentTheme == null) {
			if (useHighContrastTheme()) {
				currentTheme = new GlassForestHighContrastTheme();
			} else {
				String theme = AccessController
						.doPrivileged(new GetPropertyAction(
								SWING_GLASS_FOREST_THEME));
				if ("steel".equals(theme)) {
					currentTheme = new DefaultGlassForestTheme();
				} else {
					currentTheme = new OceanGlassForestTheme();
				}
			}
			setCurrentTheme(currentTheme);
		}
		return currentTheme;
	}

	protected void initClassDefaults(UIDefaults table) {
		super.initClassDefaults(table);
		final String uiPackageName = "com.gs.glassforest.plaf.ui.";

		Object[] uiDefaults = {
                "ButtonUI", uiPackageName + "GlassForestButtonUI",
              "CheckBoxUI", uiPackageName + "GlassForestCheckBoxUI",
              "ComboBoxUI", uiPackageName + "GlassForestComboBoxUI",
           "DesktopIconUI", uiPackageName + "GlassForestDesktopIconUI",
           "FileChooserUI", uiPackageName + "GlassForestFileChooserUI",
         "InternalFrameUI", uiPackageName + "GlassForestInternalFrameUI",
                 "LabelUI", uiPackageName + "GlassForestLabelUI",
    "PopupMenuSeparatorUI", uiPackageName + "GlassForestPopupMenuSeparatorUI",
           "ProgressBarUI", uiPackageName + "GlassForestProgressBarUI",
           "RadioButtonUI", uiPackageName + "GlassForestRadioButtonUI",
             "ScrollBarUI", uiPackageName + "GlassForestScrollBarUI",
            "ScrollPaneUI", uiPackageName + "GlassForestScrollPaneUI",
             "SeparatorUI", uiPackageName + "GlassForestSeparatorUI",
                "SliderUI", uiPackageName + "GlassForestSliderUI",
             "SplitPaneUI", uiPackageName + "GlassForestSplitPaneUI",
            "TabbedPaneUI", uiPackageName + "GlassForestTabbedPaneUI",
             "TextFieldUI", uiPackageName + "GlassForestTextFieldUI",
          "ToggleButtonUI", uiPackageName + "GlassForestToggleButtonUI",
               "ToolBarUI", uiPackageName + "GlassForestToolBarUI",
               "ToolTipUI", uiPackageName + "GlassForestToolTipUI",
                  "TreeUI", uiPackageName + "GlassForestTreeUI",
              "RootPaneUI", uiPackageName + "GlassForestRootPaneUI",
               "SpinnerUI", uiPackageName + "GlassForestSpinnerUI",
                 "PanelUI", uiPackageName + "GlassForestPanelUI",
     };

		table.putDefaults(uiDefaults);
	}

	protected void initSystemColorDefaults(UIDefaults table) {
		GlassForestTheme theme = getCurrentTheme();
		Color control = theme.getControl();
		
		Object[] systemColors = {
                "desktop", theme.getDesktopColor(), /* Color of the desktop background */
          "activeCaption", theme.getWindowTitleBackground(), /* Color for captions (title bars) when they are active. */
      "activeCaptionText", theme.getWindowTitleForeground(), /* Text color for text in captions (title bars). */
    "activeCaptionBorder", theme.getPrimaryControlShadow(), /* Border color for caption (title bar) window borders. */
        "inactiveCaption", theme.getWindowTitleInactiveBackground(), /* Color for captions (title bars) when not active. */
    "inactiveCaptionText", theme.getWindowTitleInactiveForeground(), /* Text color for text in inactive captions (title bars). */
  "inactiveCaptionBorder", theme.getControlShadow(), /* Border color for inactive caption (title bar) window borders. */
                 "window", theme.getWindowBackground(), /* Default color for the interior of windows */
           "windowBorder", control, /* ??? */
             "windowText", theme.getUserTextColor(), /* ??? */
                   "menu", theme.getMenuBackground(), /* Background color for menus */
               "menuText", theme.getMenuForeground(), /* Text color for menus  */
                   "text", theme.getWindowBackground(), /* Text background color */
               "textText", theme.getUserTextColor(), /* Text foreground color */
          "textHighlight", theme.getTextHighlightColor(), /* Text background color when selected */
      "textHighlightText", theme.getHighlightedTextColor(), /* Text color when selected */
       "textInactiveText", theme.getInactiveSystemTextColor(), /* Text color when disabled */
                "control", control, /* Default color for controls (buttons, sliders, etc) */
            "controlText", theme.getControlTextColor(), /* Default color for text in controls */
       "controlHighlight", theme.getControlHighlight(), /* Specular highlight (opposite of the shadow) */
     "controlLtHighlight", theme.getControlHighlight(), /* Highlight color for controls */
          "controlShadow", theme.getControlShadow(), /* Shadow color for controls */
        "controlDkShadow", theme.getControlDarkShadow(), /* Dark shadow color for controls */
              "scrollbar", control, /* Scrollbar background (usually the "track") */
                   "info", theme.getPrimaryControl(), /* ToolTip Background */
               "infoText", theme.getPrimaryControlInfo()  /* ToolTip Text */
        };

		table.putDefaults(systemColors);
	}

	/**
	 * Initialize the defaults table with the name of the ResourceBundle used
	 * for getting localized defaults.
	 */
	private void initResourceBundle(UIDefaults table) {
		table.addResourceBundle("com.gs.glassforest.i18n.GlassForest");
	}

	protected void createDefaultTheme() {
		getCurrentTheme();
	}

	public UIDefaults getDefaults() {
		// PENDING: move this to initialize when API changes are allowed
		GLASS_FOREST_LOOK_AND_FEEL_INITED = true;

		createDefaultTheme();
		UIDefaults table = super.getDefaults();
		GlassForestTheme currentTheme = getCurrentTheme();
		currentTheme.addCustomEntriesToTable(table);
		currentTheme.install();
		return table;
	}

	public void provideErrorFeedback(Component component) {
		super.provideErrorFeedback(component);
	}

	public Icon getDisabledIcon(JComponent component, Icon icon) {
		if ((icon instanceof ImageIcon)) {
			return GlassForestUtils.getDisabledButtonIcon(((ImageIcon) icon)
					.getImage());
		}
		return super.getDisabledIcon(component, icon);
	}

	public Icon getDisabledSelectedIcon(JComponent component, Icon icon) {
		if ((icon instanceof ImageIcon)) {
			return GlassForestUtils.getDisabledButtonIcon(((ImageIcon) icon)
					.getImage());
		}
		return super.getDisabledSelectedIcon(component, icon);
	}

	/**
     * Returns the control text font of the current theme. This is a
     * cover method for {@code getCurrentTheme().getControlTextColor()}.
     *
     * @return the control text font
     *
     * @see GlassForestTheme
     */
    public static FontUIResource getControlTextFont() { return getCurrentTheme().getControlTextFont();}

    /**
     * Returns the sytem text font of the current theme. This is a
     * cover method for {@code getCurrentTheme().getSystemTextFont()}.
     *
     * @return the system text font
     *
     * @see GlassForestTheme
     */
    public static FontUIResource getSystemTextFont() { return getCurrentTheme().getSystemTextFont();}

    /**
     * Returns the user text font of the current theme. This is a
     * cover method for {@code getCurrentTheme().getUserTextFont()}.
     *
     * @return the user text font
     *
     * @see GlassForestTheme
     */
    public static FontUIResource getUserTextFont() { return getCurrentTheme().getUserTextFont();}

    /**
     * Returns the menu text font of the current theme. This is a
     * cover method for {@code getCurrentTheme().getMenuTextFont()}.
     *
     * @return the menu text font
     *
     * @see GlassForestTheme
     */
    public static FontUIResource getMenuTextFont() { return getCurrentTheme().getMenuTextFont();}

    /**
     * Returns the window title font of the current theme. This is a
     * cover method for {@code getCurrentTheme().getWindowTitleFont()}.
     *
     * @return the window title font
     *
     * @see GlassForestTheme
     */
    public static FontUIResource getWindowTitleFont() { return getCurrentTheme().getWindowTitleFont();}

    /**
     * Returns the sub-text font of the current theme. This is a
     * cover method for {@code getCurrentTheme().getSubTextFont()}.
     *
     * @return the sub-text font
     *
     * @see GlassForestTheme
     */
    public static FontUIResource getSubTextFont() { return getCurrentTheme().getSubTextFont();}

    /**
     * Returns the desktop color of the current theme. This is a
     * cover method for {@code getCurrentTheme().getDesktopColor()}.
     *
     * @return the desktop color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getDesktopColor() { return getCurrentTheme().getDesktopColor(); }

    /**
     * Returns the focus color of the current theme. This is a
     * cover method for {@code getCurrentTheme().getFocusColor()}.
     *
     * @return the focus color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getFocusColor() { return getCurrentTheme().getFocusColor(); }

    /**
     * Returns the white color of the current theme. This is a
     * cover method for {@code getCurrentTheme().getWhite()}.
     *
     * @return the white color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getWhite() { return getCurrentTheme().getWhite(); }

    /**
     * Returns the black color of the current theme. This is a
     * cover method for {@code getCurrentTheme().getBlack()}.
     *
     * @return the black color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getBlack() { return getCurrentTheme().getBlack(); }

    /**
     * Returns the control color of the current theme. This is a
     * cover method for {@code getCurrentTheme().getControl()}.
     *
     * @return the control color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getControl() { return getCurrentTheme().getControl(); }

    /**
     * Returns the control shadow color of the current theme. This is a
     * cover method for {@code getCurrentTheme().getControlShadow()}.
     *
     * @return the control shadow color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getControlShadow() { return getCurrentTheme().getControlShadow(); }

    /**
     * Returns the control dark shadow color of the current theme. This is a
     * cover method for {@code getCurrentTheme().getControlDarkShadow()}.
     *
     * @return the control dark shadow color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getControlDarkShadow() { return getCurrentTheme().getControlDarkShadow(); }

    /**
     * Returns the control info color of the current theme. This is a
     * cover method for {@code getCurrentTheme().getControlInfo()}.
     *
     * @return the control info color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getControlInfo() { return getCurrentTheme().getControlInfo(); }

    /**
     * Returns the control highlight color of the current theme. This is a
     * cover method for {@code getCurrentTheme().getControlHighlight()}.
     *
     * @return the control highlight color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getControlHighlight() { return getCurrentTheme().getControlHighlight(); }

    /**
     * Returns the control disabled color of the current theme. This is a
     * cover method for {@code getCurrentTheme().getControlDisabled()}.
     *
     * @return the control disabled color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getControlDisabled() { return getCurrentTheme().getControlDisabled(); }

    /**
     * Returns the primary control color of the current theme. This is a
     * cover method for {@code getCurrentTheme().getPrimaryControl()}.
     *
     * @return the primary control color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getPrimaryControl() { return getCurrentTheme().getPrimaryControl(); }

    /**
     * Returns the primary control shadow color of the current theme. This is a
     * cover method for {@code getCurrentTheme().getPrimaryControlShadow()}.
     *
     * @return the primary control shadow color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getPrimaryControlShadow() { return getCurrentTheme().getPrimaryControlShadow(); }

    /**
     * Returns the primary control dark shadow color of the current
     * theme. This is a cover method for {@code
     * getCurrentTheme().getPrimaryControlDarkShadow()}.
     *
     * @return the primary control dark shadow color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getPrimaryControlDarkShadow() { return getCurrentTheme().getPrimaryControlDarkShadow(); }

    /**
     * Returns the primary control info color of the current theme. This is a
     * cover method for {@code getCurrentTheme().getPrimaryControlInfo()}.
     *
     * @return the primary control info color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getPrimaryControlInfo() { return getCurrentTheme().getPrimaryControlInfo(); }

    /**
     * Returns the primary control highlight color of the current
     * theme. This is a cover method for {@code
     * getCurrentTheme().getPrimaryControlHighlight()}.
     *
     * @return the primary control highlight color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getPrimaryControlHighlight() { return getCurrentTheme().getPrimaryControlHighlight(); }

    /**
     * Returns the system text color of the current theme. This is a
     * cover method for {@code getCurrentTheme().getSystemTextColor()}.
     *
     * @return the system text color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getSystemTextColor() { return getCurrentTheme().getSystemTextColor(); }

    /**
     * Returns the control text color of the current theme. This is a
     * cover method for {@code getCurrentTheme().getControlTextColor()}.
     *
     * @return the control text color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getControlTextColor() { return getCurrentTheme().getControlTextColor(); }

    /**
     * Returns the inactive control text color of the current theme. This is a
     * cover method for {@code
     * getCurrentTheme().getInactiveControlTextColor()}.
     *
     * @return the inactive control text color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getInactiveControlTextColor() { return getCurrentTheme().getInactiveControlTextColor(); }

    /**
     * Returns the inactive system text color of the current theme. This is a
     * cover method for {@code
     * getCurrentTheme().getInactiveSystemTextColor()}.
     *
     * @return the inactive system text color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getInactiveSystemTextColor() { return getCurrentTheme().getInactiveSystemTextColor(); }

    /**
     * Returns the user text color of the current theme. This is a
     * cover method for {@code getCurrentTheme().getUserTextColor()}.
     *
     * @return the user text color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getUserTextColor() { return getCurrentTheme().getUserTextColor(); }

    /**
     * Returns the text highlight color of the current theme. This is a
     * cover method for {@code getCurrentTheme().getTextHighlightColor()}.
     *
     * @return the text highlight color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getTextHighlightColor() { return getCurrentTheme().getTextHighlightColor(); }

    /**
     * Returns the highlighted text color of the current theme. This is a
     * cover method for {@code getCurrentTheme().getHighlightedTextColor()}.
     *
     * @return the highlighted text color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getHighlightedTextColor() { return getCurrentTheme().getHighlightedTextColor(); }

    /**
     * Returns the window background color of the current theme. This is a
     * cover method for {@code getCurrentTheme().getWindowBackground()}.
     *
     * @return the window background color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getWindowBackground() { return getCurrentTheme().getWindowBackground(); }

    /**
     * Returns the window title background color of the current
     * theme. This is a cover method for {@code
     * getCurrentTheme().getWindowTitleBackground()}.
     *
     * @return the window title background color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getWindowTitleBackground() { return getCurrentTheme().getWindowTitleBackground(); }

    /**
     * Returns the window title foreground color of the current
     * theme. This is a cover method for {@code
     * getCurrentTheme().getWindowTitleForeground()}.
     *
     * @return the window title foreground color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getWindowTitleForeground() { return getCurrentTheme().getWindowTitleForeground(); }

    /**
     * Returns the window title inactive background color of the current
     * theme. This is a cover method for {@code
     * getCurrentTheme().getWindowTitleInactiveBackground()}.
     *
     * @return the window title inactive background color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getWindowTitleInactiveBackground() { return getCurrentTheme().getWindowTitleInactiveBackground(); }

    /**
     * Returns the window title inactive foreground color of the current
     * theme. This is a cover method for {@code
     * getCurrentTheme().getWindowTitleInactiveForeground()}.
     *
     * @return the window title inactive foreground color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getWindowTitleInactiveForeground() { return getCurrentTheme().getWindowTitleInactiveForeground(); }

    /**
     * Returns the menu background color of the current theme. This is
     * a cover method for {@code getCurrentTheme().getMenuBackground()}.
     *
     * @return the menu background color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getMenuBackground() { return getCurrentTheme().getMenuBackground(); }

    /**
     * Returns the menu foreground color of the current theme. This is
     * a cover method for {@code getCurrentTheme().getMenuForeground()}.
     *
     * @return the menu foreground color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getMenuForeground() { return getCurrentTheme().getMenuForeground(); }

    /**
     * Returns the menu selected background color of the current theme. This is
     * a cover method for
     * {@code getCurrentTheme().getMenuSelectedBackground()}.
     *
     * @return the menu selected background color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getMenuSelectedBackground() { return getCurrentTheme().getMenuSelectedBackground(); }

    /**
     * Returns the menu selected foreground color of the current theme. This is
     * a cover method for
     * {@code getCurrentTheme().getMenuSelectedForeground()}.
     *
     * @return the menu selected foreground color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getMenuSelectedForeground() { return getCurrentTheme().getMenuSelectedForeground(); }

    /**
     * Returns the menu disabled foreground color of the current theme. This is
     * a cover method for
     * {@code getCurrentTheme().getMenuDisabledForeground()}.
     *
     * @return the menu disabled foreground color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getMenuDisabledForeground() { return getCurrentTheme().getMenuDisabledForeground(); }

    /**
     * Returns the separator background color of the current theme. This is
     * a cover method for {@code getCurrentTheme().getSeparatorBackground()}.
     *
     * @return the separator background color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getSeparatorBackground() { return getCurrentTheme().getSeparatorBackground(); }

    /**
     * Returns the separator foreground color of the current theme. This is
     * a cover method for {@code getCurrentTheme().getSeparatorForeground()}.
     *
     * @return the separator foreground color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getSeparatorForeground() { return getCurrentTheme().getSeparatorForeground(); }

    /**
     * Returns the accelerator foreground color of the current theme. This is
     * a cover method for {@code getCurrentTheme().getAcceleratorForeground()}.
     *
     * @return the separator accelerator foreground color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getAcceleratorForeground() { return getCurrentTheme().getAcceleratorForeground(); }

    /**
     * Returns the accelerator selected foreground color of the
     * current theme. This is a cover method for {@code
     * getCurrentTheme().getAcceleratorSelectedForeground()}.
     *
     * @return the accelerator selected foreground color
     *
     * @see GlassForestTheme
     */
    public static ColorUIResource getAcceleratorSelectedForeground() { return getCurrentTheme().getAcceleratorSelectedForeground(); }

    
    /**
     * Populates {@code table} with the defaults for metal.
     *
     * @param table the {@code UIDefaults} to add the values to
     * @throws NullPointerException if {@code table} is {@code null}
     */
    protected void initComponentDefaults(UIDefaults table) {
        super.initComponentDefaults( table );

        initResourceBundle(table);

        Color acceleratorForeground = getAcceleratorForeground();
        Color acceleratorSelectedForeground = getAcceleratorSelectedForeground();
        Color control = getControl();
        Color controlHighlight = getControlHighlight();
        Color controlShadow = getControlShadow();
        Color controlDarkShadow = getControlDarkShadow();
        Color controlTextColor = getControlTextColor();
        Color focusColor = getFocusColor();
        Color inactiveControlTextColor = getInactiveControlTextColor();
        Color menuBackground = getMenuBackground();
        Color menuSelectedBackground = getMenuSelectedBackground();
        Color menuDisabledForeground = getMenuDisabledForeground();
        Color menuSelectedForeground = getMenuSelectedForeground();
        Color primaryControl = getPrimaryControl();
        Color primaryControlDarkShadow = getPrimaryControlDarkShadow();
        Color primaryControlShadow = getPrimaryControlShadow();
        Color systemTextColor = getSystemTextColor();

        Insets zeroInsets = new InsetsUIResource(0, 0, 0, 0);

        Integer zero = Integer.valueOf(0);

        Object textFieldBorder =
            new SwingLazyValue("javax.swing.plaf.metal.MetalBorders",
                                          "getTextFieldBorder");

        Object dialogBorder = new MetalLazyValue(
                          "javax.swing.plaf.metal.MetalBorders$DialogBorder");

        Object questionDialogBorder = new MetalLazyValue(
                  "javax.swing.plaf.metal.MetalBorders$QuestionDialogBorder");

        Object fieldInputMap = new UIDefaults.LazyInputMap(new Object[] {
                           "ctrl C", DefaultEditorKit.copyAction,
                           "ctrl V", DefaultEditorKit.pasteAction,
                           "ctrl X", DefaultEditorKit.cutAction,
                             "COPY", DefaultEditorKit.copyAction,
                            "PASTE", DefaultEditorKit.pasteAction,
                              "CUT", DefaultEditorKit.cutAction,
                   "control INSERT", DefaultEditorKit.copyAction,
                     "shift INSERT", DefaultEditorKit.pasteAction,
                     "shift DELETE", DefaultEditorKit.cutAction,
                       "shift LEFT", DefaultEditorKit.selectionBackwardAction,
                    "shift KP_LEFT", DefaultEditorKit.selectionBackwardAction,
                      "shift RIGHT", DefaultEditorKit.selectionForwardAction,
                   "shift KP_RIGHT", DefaultEditorKit.selectionForwardAction,
                        "ctrl LEFT", DefaultEditorKit.previousWordAction,
                     "ctrl KP_LEFT", DefaultEditorKit.previousWordAction,
                       "ctrl RIGHT", DefaultEditorKit.nextWordAction,
                    "ctrl KP_RIGHT", DefaultEditorKit.nextWordAction,
                  "ctrl shift LEFT", DefaultEditorKit.selectionPreviousWordAction,
               "ctrl shift KP_LEFT", DefaultEditorKit.selectionPreviousWordAction,
                 "ctrl shift RIGHT", DefaultEditorKit.selectionNextWordAction,
              "ctrl shift KP_RIGHT", DefaultEditorKit.selectionNextWordAction,
                           "ctrl A", DefaultEditorKit.selectAllAction,
                             "HOME", DefaultEditorKit.beginLineAction,
                              "END", DefaultEditorKit.endLineAction,
                       "shift HOME", DefaultEditorKit.selectionBeginLineAction,
                        "shift END", DefaultEditorKit.selectionEndLineAction,
                       "BACK_SPACE", DefaultEditorKit.deletePrevCharAction,
                 "shift BACK_SPACE", DefaultEditorKit.deletePrevCharAction,
                           "ctrl H", DefaultEditorKit.deletePrevCharAction,
                           "DELETE", DefaultEditorKit.deleteNextCharAction,
                      "ctrl DELETE", DefaultEditorKit.deleteNextWordAction,
                  "ctrl BACK_SPACE", DefaultEditorKit.deletePrevWordAction,
                            "RIGHT", DefaultEditorKit.forwardAction,
                             "LEFT", DefaultEditorKit.backwardAction,
                         "KP_RIGHT", DefaultEditorKit.forwardAction,
                          "KP_LEFT", DefaultEditorKit.backwardAction,
                            "ENTER", JTextField.notifyAction,
                  "ctrl BACK_SLASH", "unselect"/*DefaultEditorKit.unselectAction*/,
                   "control shift O", "toggle-componentOrientation"/*DefaultEditorKit.toggleComponentOrientation*/
        });

        Object passwordInputMap = new UIDefaults.LazyInputMap(new Object[] {
                           "ctrl C", DefaultEditorKit.copyAction,
                           "ctrl V", DefaultEditorKit.pasteAction,
                           "ctrl X", DefaultEditorKit.cutAction,
                             "COPY", DefaultEditorKit.copyAction,
                            "PASTE", DefaultEditorKit.pasteAction,
                              "CUT", DefaultEditorKit.cutAction,
                   "control INSERT", DefaultEditorKit.copyAction,
                     "shift INSERT", DefaultEditorKit.pasteAction,
                     "shift DELETE", DefaultEditorKit.cutAction,
                       "shift LEFT", DefaultEditorKit.selectionBackwardAction,
                    "shift KP_LEFT", DefaultEditorKit.selectionBackwardAction,
                      "shift RIGHT", DefaultEditorKit.selectionForwardAction,
                   "shift KP_RIGHT", DefaultEditorKit.selectionForwardAction,
                        "ctrl LEFT", DefaultEditorKit.beginLineAction,
                     "ctrl KP_LEFT", DefaultEditorKit.beginLineAction,
                       "ctrl RIGHT", DefaultEditorKit.endLineAction,
                    "ctrl KP_RIGHT", DefaultEditorKit.endLineAction,
                  "ctrl shift LEFT", DefaultEditorKit.selectionBeginLineAction,
               "ctrl shift KP_LEFT", DefaultEditorKit.selectionBeginLineAction,
                 "ctrl shift RIGHT", DefaultEditorKit.selectionEndLineAction,
              "ctrl shift KP_RIGHT", DefaultEditorKit.selectionEndLineAction,
                           "ctrl A", DefaultEditorKit.selectAllAction,
                             "HOME", DefaultEditorKit.beginLineAction,
                              "END", DefaultEditorKit.endLineAction,
                       "shift HOME", DefaultEditorKit.selectionBeginLineAction,
                        "shift END", DefaultEditorKit.selectionEndLineAction,
                       "BACK_SPACE", DefaultEditorKit.deletePrevCharAction,
                 "shift BACK_SPACE", DefaultEditorKit.deletePrevCharAction,
                           "ctrl H", DefaultEditorKit.deletePrevCharAction,
                           "DELETE", DefaultEditorKit.deleteNextCharAction,
                            "RIGHT", DefaultEditorKit.forwardAction,
                             "LEFT", DefaultEditorKit.backwardAction,
                         "KP_RIGHT", DefaultEditorKit.forwardAction,
                          "KP_LEFT", DefaultEditorKit.backwardAction,
                            "ENTER", JTextField.notifyAction,
                  "ctrl BACK_SLASH", "unselect"/*DefaultEditorKit.unselectAction*/,
                   "control shift O", "toggle-componentOrientation"/*DefaultEditorKit.toggleComponentOrientation*/
        });

        Object multilineInputMap = new UIDefaults.LazyInputMap(new Object[] {
                           "ctrl C", DefaultEditorKit.copyAction,
                           "ctrl V", DefaultEditorKit.pasteAction,
                           "ctrl X", DefaultEditorKit.cutAction,
                             "COPY", DefaultEditorKit.copyAction,
                            "PASTE", DefaultEditorKit.pasteAction,
                              "CUT", DefaultEditorKit.cutAction,
                   "control INSERT", DefaultEditorKit.copyAction,
                     "shift INSERT", DefaultEditorKit.pasteAction,
                     "shift DELETE", DefaultEditorKit.cutAction,
                       "shift LEFT", DefaultEditorKit.selectionBackwardAction,
                    "shift KP_LEFT", DefaultEditorKit.selectionBackwardAction,
                      "shift RIGHT", DefaultEditorKit.selectionForwardAction,
                   "shift KP_RIGHT", DefaultEditorKit.selectionForwardAction,
                        "ctrl LEFT", DefaultEditorKit.previousWordAction,
                     "ctrl KP_LEFT", DefaultEditorKit.previousWordAction,
                       "ctrl RIGHT", DefaultEditorKit.nextWordAction,
                    "ctrl KP_RIGHT", DefaultEditorKit.nextWordAction,
                  "ctrl shift LEFT", DefaultEditorKit.selectionPreviousWordAction,
               "ctrl shift KP_LEFT", DefaultEditorKit.selectionPreviousWordAction,
                 "ctrl shift RIGHT", DefaultEditorKit.selectionNextWordAction,
              "ctrl shift KP_RIGHT", DefaultEditorKit.selectionNextWordAction,
                           "ctrl A", DefaultEditorKit.selectAllAction,
                             "HOME", DefaultEditorKit.beginLineAction,
                              "END", DefaultEditorKit.endLineAction,
                       "shift HOME", DefaultEditorKit.selectionBeginLineAction,
                        "shift END", DefaultEditorKit.selectionEndLineAction,

                               "UP", DefaultEditorKit.upAction,
                            "KP_UP", DefaultEditorKit.upAction,
                             "DOWN", DefaultEditorKit.downAction,
                          "KP_DOWN", DefaultEditorKit.downAction,
                          "PAGE_UP", DefaultEditorKit.pageUpAction,
                        "PAGE_DOWN", DefaultEditorKit.pageDownAction,
                    "shift PAGE_UP", "selection-page-up",
                  "shift PAGE_DOWN", "selection-page-down",
               "ctrl shift PAGE_UP", "selection-page-left",
             "ctrl shift PAGE_DOWN", "selection-page-right",
                         "shift UP", DefaultEditorKit.selectionUpAction,
                      "shift KP_UP", DefaultEditorKit.selectionUpAction,
                       "shift DOWN", DefaultEditorKit.selectionDownAction,
                    "shift KP_DOWN", DefaultEditorKit.selectionDownAction,
                            "ENTER", DefaultEditorKit.insertBreakAction,
                       "BACK_SPACE", DefaultEditorKit.deletePrevCharAction,
                 "shift BACK_SPACE", DefaultEditorKit.deletePrevCharAction,
                           "ctrl H", DefaultEditorKit.deletePrevCharAction,
                           "DELETE", DefaultEditorKit.deleteNextCharAction,
                      "ctrl DELETE", DefaultEditorKit.deleteNextWordAction,
                  "ctrl BACK_SPACE", DefaultEditorKit.deletePrevWordAction,
                            "RIGHT", DefaultEditorKit.forwardAction,
                             "LEFT", DefaultEditorKit.backwardAction,
                         "KP_RIGHT", DefaultEditorKit.forwardAction,
                          "KP_LEFT", DefaultEditorKit.backwardAction,
                              "TAB", DefaultEditorKit.insertTabAction,
                  "ctrl BACK_SLASH", "unselect"/*DefaultEditorKit.unselectAction*/,
                        "ctrl HOME", DefaultEditorKit.beginAction,
                         "ctrl END", DefaultEditorKit.endAction,
                  "ctrl shift HOME", DefaultEditorKit.selectionBeginAction,
                   "ctrl shift END", DefaultEditorKit.selectionEndAction,
                           "ctrl T", "next-link-action",
                     "ctrl shift T", "previous-link-action",
                       "ctrl SPACE", "activate-link-action",
                   "control shift O", "toggle-componentOrientation"/*DefaultEditorKit.toggleComponentOrientation*/
        });

        Object scrollPaneBorder = new SwingLazyValue("javax.swing.plaf.metal.MetalBorders$ScrollPaneBorder");
        Object buttonBorder =
                    new SwingLazyValue("javax.swing.plaf.metal.MetalBorders",
                                          "getButtonBorder");

        Object toggleButtonBorder =
            new SwingLazyValue("javax.swing.plaf.metal.MetalBorders",
                                          "getToggleButtonBorder");

        Object titledBorderBorder =
            new SwingLazyValue(
                          "javax.swing.plaf.BorderUIResource$LineBorderUIResource",
                          new Object[] {controlShadow});

        Object desktopIconBorder =
            new SwingLazyValue(
                          "javax.swing.plaf.metal.MetalBorders",
                          "getDesktopIconBorder");

        Object menuBarBorder =
            new SwingLazyValue(
                          "javax.swing.plaf.metal.MetalBorders$MenuBarBorder");

        Object popupMenuBorder =
            new SwingLazyValue(
                         "javax.swing.plaf.metal.MetalBorders$PopupMenuBorder");
        Object menuItemBorder =
            new SwingLazyValue(
                         "javax.swing.plaf.metal.MetalBorders$MenuItemBorder");

        Object menuItemAcceleratorDelimiter = "-";
        Object toolBarBorder = new SwingLazyValue("javax.swing.plaf.metal.MetalBorders$ToolBarBorder");

        Object progressBarBorder = new SwingLazyValue(
                          "javax.swing.plaf.BorderUIResource$LineBorderUIResource",
                          new Object[] {controlDarkShadow, new Integer(1)});

        Object toolTipBorder = new SwingLazyValue(
                          "javax.swing.plaf.BorderUIResource$LineBorderUIResource",
                          new Object[] {primaryControlDarkShadow});

        Object toolTipBorderInactive = new SwingLazyValue(
                          "javax.swing.plaf.BorderUIResource$LineBorderUIResource",
                          new Object[] {controlDarkShadow});

        Object focusCellHighlightBorder = new SwingLazyValue(
                          "javax.swing.plaf.BorderUIResource$LineBorderUIResource",
                          new Object[] {focusColor});

        Object tabbedPaneTabAreaInsets = new InsetsUIResource(4, 2, 0, 6);

        Object tabbedPaneTabInsets = new InsetsUIResource(0, 9, 1, 9);

        final Object[] internalFrameIconArgs = new Object[1];
        internalFrameIconArgs[0] = new Integer(16);

        Object[] defaultCueList = new Object[] {
                "OptionPane.errorSound",
                "OptionPane.informationSound",
                "OptionPane.questionSound",
                "OptionPane.warningSound" };

        GlassForestTheme theme = getCurrentTheme();
        Object menuTextValue = new FontActiveValue(theme,
        		GlassForestTheme.MENU_TEXT_FONT);
        Object controlTextValue = new FontActiveValue(theme,
        		GlassForestTheme.CONTROL_TEXT_FONT);
        Object userTextValue = new FontActiveValue(theme,
        		GlassForestTheme.USER_TEXT_FONT);
        Object windowTitleValue = new FontActiveValue(theme,
        		GlassForestTheme.WINDOW_TITLE_FONT);
        Object subTextValue = new FontActiveValue(theme,
        		GlassForestTheme.SUB_TEXT_FONT);
        Object systemTextValue = new FontActiveValue(theme,
        		GlassForestTheme.SYSTEM_TEXT_FONT);
        //
        // DEFAULTS TABLE
        //

        Object[] defaults = {
            // *** Auditory Feedback
            "AuditoryCues.defaultCueList", defaultCueList,
            // this key defines which of the various cues to render
            // This is disabled until sound bugs can be resolved.
            "AuditoryCues.playList", null, // defaultCueList,

            // Text (Note: many are inherited)
            "TextField.border", textFieldBorder,
            "TextField.font", userTextValue,

            "PasswordField.border", textFieldBorder,
            // passwordField.font should actually map to
            // win.ansiFixed.font.height on windows.
            "PasswordField.font", userTextValue,
            "PasswordField.echoChar", (char)0x2022,

            // TextArea.font should actually map to win.ansiFixed.font.height
            // on windows.
            "TextArea.font", userTextValue,

            "TextPane.background", table.get("window"),
            "TextPane.font", userTextValue,

            "EditorPane.background", table.get("window"),
            "EditorPane.font", userTextValue,

            "TextField.focusInputMap", fieldInputMap,
            "PasswordField.focusInputMap", passwordInputMap,
            "TextArea.focusInputMap", multilineInputMap,
            "TextPane.focusInputMap", multilineInputMap,
            "EditorPane.focusInputMap", multilineInputMap,

            // FormattedTextFields
            "FormattedTextField.border", textFieldBorder,
            "FormattedTextField.font", userTextValue,
            "FormattedTextField.focusInputMap",
              new UIDefaults.LazyInputMap(new Object[] {
                           "ctrl C", DefaultEditorKit.copyAction,
                           "ctrl V", DefaultEditorKit.pasteAction,
                           "ctrl X", DefaultEditorKit.cutAction,
                             "COPY", DefaultEditorKit.copyAction,
                            "PASTE", DefaultEditorKit.pasteAction,
                              "CUT", DefaultEditorKit.cutAction,
                   "control INSERT", DefaultEditorKit.copyAction,
                     "shift INSERT", DefaultEditorKit.pasteAction,
                     "shift DELETE", DefaultEditorKit.cutAction,
                       "shift LEFT", DefaultEditorKit.selectionBackwardAction,
                    "shift KP_LEFT", DefaultEditorKit.selectionBackwardAction,
                      "shift RIGHT", DefaultEditorKit.selectionForwardAction,
                   "shift KP_RIGHT", DefaultEditorKit.selectionForwardAction,
                        "ctrl LEFT", DefaultEditorKit.previousWordAction,
                     "ctrl KP_LEFT", DefaultEditorKit.previousWordAction,
                       "ctrl RIGHT", DefaultEditorKit.nextWordAction,
                    "ctrl KP_RIGHT", DefaultEditorKit.nextWordAction,
                  "ctrl shift LEFT", DefaultEditorKit.selectionPreviousWordAction,
               "ctrl shift KP_LEFT", DefaultEditorKit.selectionPreviousWordAction,
                 "ctrl shift RIGHT", DefaultEditorKit.selectionNextWordAction,
              "ctrl shift KP_RIGHT", DefaultEditorKit.selectionNextWordAction,
                           "ctrl A", DefaultEditorKit.selectAllAction,
                             "HOME", DefaultEditorKit.beginLineAction,
                              "END", DefaultEditorKit.endLineAction,
                       "shift HOME", DefaultEditorKit.selectionBeginLineAction,
                        "shift END", DefaultEditorKit.selectionEndLineAction,
                       "BACK_SPACE", DefaultEditorKit.deletePrevCharAction,
                 "shift BACK_SPACE", DefaultEditorKit.deletePrevCharAction,
                           "ctrl H", DefaultEditorKit.deletePrevCharAction,
                           "DELETE", DefaultEditorKit.deleteNextCharAction,
                      "ctrl DELETE", DefaultEditorKit.deleteNextWordAction,
                  "ctrl BACK_SPACE", DefaultEditorKit.deletePrevWordAction,
                            "RIGHT", DefaultEditorKit.forwardAction,
                             "LEFT", DefaultEditorKit.backwardAction,
                         "KP_RIGHT", DefaultEditorKit.forwardAction,
                          "KP_LEFT", DefaultEditorKit.backwardAction,
                            "ENTER", JTextField.notifyAction,
                  "ctrl BACK_SLASH", "unselect",
                   "control shift O", "toggle-componentOrientation",
                           "ESCAPE", "reset-field-edit",
                               "UP", "increment",
                            "KP_UP", "increment",
                             "DOWN", "decrement",
                          "KP_DOWN", "decrement",
              }),


            // Buttons
            "Button.defaultButtonFollowsFocus", Boolean.FALSE,
            "Button.disabledText", inactiveControlTextColor,
            "Button.select", controlShadow,
            "Button.border", buttonBorder,
            "Button.font", controlTextValue,
            "Button.focus", focusColor,
            "Button.focusInputMap", new UIDefaults.LazyInputMap(new Object[] {
                          "SPACE", "pressed",
                 "released SPACE", "released"
              }),

            "CheckBox.disabledText", inactiveControlTextColor,
            "Checkbox.select", controlShadow,
            "CheckBox.font", controlTextValue,
            "CheckBox.focus", focusColor,
            "CheckBox.icon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getCheckBoxIcon"),
            "CheckBox.focusInputMap",
               new UIDefaults.LazyInputMap(new Object[] {
                            "SPACE", "pressed",
                   "released SPACE", "released"
                 }),
            // margin is 2 all the way around, BasicBorders.RadioButtonBorder
            // (checkbox uses RadioButtonBorder) is 2 all the way around too.
            "CheckBox.totalInsets", new Insets(4, 4, 4, 4),

            "RadioButton.disabledText", inactiveControlTextColor,
            "RadioButton.select", controlShadow,
            "RadioButton.icon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getRadioButtonIcon"),
            "RadioButton.font", controlTextValue,
            "RadioButton.focus", focusColor,
            "RadioButton.focusInputMap",
               new UIDefaults.LazyInputMap(new Object[] {
                          "SPACE", "pressed",
                 "released SPACE", "released"
              }),
            // margin is 2 all the way around, BasicBorders.RadioButtonBorder
            // is 2 all the way around too.
            "RadioButton.totalInsets", new Insets(4, 4, 4, 4),

            "ToggleButton.select", controlShadow,
            "ToggleButton.disabledText", inactiveControlTextColor,
            "ToggleButton.focus", focusColor,
            "ToggleButton.border", toggleButtonBorder,
            "ToggleButton.font", controlTextValue,
            "ToggleButton.focusInputMap",
              new UIDefaults.LazyInputMap(new Object[] {
                            "SPACE", "pressed",
                   "released SPACE", "released"
                }),


            // File View
            "FileView.directoryIcon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getTreeFolderIcon"),
            "FileView.fileIcon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getTreeLeafIcon"),
            "FileView.computerIcon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getTreeComputerIcon"),
            "FileView.hardDriveIcon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getTreeHardDriveIcon"),
            "FileView.floppyDriveIcon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getTreeFloppyDriveIcon"),

            // File Chooser
            "FileChooser.detailsViewIcon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getFileChooserDetailViewIcon"),
            "FileChooser.homeFolderIcon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getFileChooserHomeFolderIcon"),
            "FileChooser.listViewIcon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getFileChooserListViewIcon"),
            "FileChooser.newFolderIcon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getFileChooserNewFolderIcon"),
            "FileChooser.upFolderIcon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getFileChooserUpFolderIcon"),

            "FileChooser.lookInLabelMnemonic", new Integer(KeyEvent.VK_I),
            "FileChooser.fileNameLabelMnemonic", new Integer(KeyEvent.VK_N),
            "FileChooser.filesOfTypeLabelMnemonic", new Integer(KeyEvent.VK_T),
            "FileChooser.usesSingleFilePane", Boolean.TRUE,
            "FileChooser.ancestorInputMap",
               new UIDefaults.LazyInputMap(new Object[] {
                     "ESCAPE", "cancelSelection",
                     "F2", "editFileName",
                     "F5", "refresh",
                     "BACK_SPACE", "Go Up"
                 }),


            // ToolTip
            "ToolTip.font", systemTextValue,
            "ToolTip.border", toolTipBorder,
            "ToolTip.borderInactive", toolTipBorderInactive,
            "ToolTip.backgroundInactive", control,
            "ToolTip.foregroundInactive", controlDarkShadow,
            "ToolTip.hideAccelerator", Boolean.FALSE,

            // ToolTipManager
            "ToolTipManager.enableToolTipMode", "activeApplication",

            // Slider Defaults
            "Slider.font", controlTextValue,
            "Slider.border", null,
            "Slider.foreground", primaryControlShadow,
            "Slider.focus", focusColor,
            "Slider.focusInsets", zeroInsets,
            "Slider.trackWidth", new Integer( 7 ),
            "Slider.majorTickLength", new Integer( 6 ),
            "Slider.horizontalThumbIcon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getHorizontalSliderThumbIcon"),
            "Slider.verticalThumbIcon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getVerticalSliderThumbIcon"),
            "Slider.focusInputMap",
               new UIDefaults.LazyInputMap(new Object[] {
                       "RIGHT", "positiveUnitIncrement",
                    "KP_RIGHT", "positiveUnitIncrement",
                        "DOWN", "negativeUnitIncrement",
                     "KP_DOWN", "negativeUnitIncrement",
                   "PAGE_DOWN", "negativeBlockIncrement",
              "ctrl PAGE_DOWN", "negativeBlockIncrement",
                        "LEFT", "negativeUnitIncrement",
                     "KP_LEFT", "negativeUnitIncrement",
                          "UP", "positiveUnitIncrement",
                       "KP_UP", "positiveUnitIncrement",
                     "PAGE_UP", "positiveBlockIncrement",
                "ctrl PAGE_UP", "positiveBlockIncrement",
                        "HOME", "minScroll",
                         "END", "maxScroll"
                 }),

            // Progress Bar
            "ProgressBar.font", controlTextValue,
            "ProgressBar.foreground", primaryControlShadow,
            "ProgressBar.selectionBackground", primaryControlDarkShadow,
            "ProgressBar.border", progressBarBorder,
            "ProgressBar.cellSpacing", zero,
            "ProgressBar.cellLength", Integer.valueOf(1),

            // Combo Box
            "ComboBox.background", control,
            "ComboBox.foreground", controlTextColor,
            "ComboBox.selectionBackground", primaryControlShadow,
            "ComboBox.selectionForeground", controlTextColor,
            "ComboBox.font", controlTextValue,
            "ComboBox.ancestorInputMap", new UIDefaults.LazyInputMap(new Object[] {
                     "ESCAPE", "hidePopup",
                    "PAGE_UP", "pageUpPassThrough",
                  "PAGE_DOWN", "pageDownPassThrough",
                       "HOME", "homePassThrough",
                        "END", "endPassThrough",
                       "DOWN", "selectNext",
                    "KP_DOWN", "selectNext",
                   "alt DOWN", "togglePopup",
                "alt KP_DOWN", "togglePopup",
                     "alt UP", "togglePopup",
                  "alt KP_UP", "togglePopup",
                      "SPACE", "spacePopup",
                     "ENTER", "enterPressed",
                         "UP", "selectPrevious",
                      "KP_UP", "selectPrevious"
              }),

            // Internal Frame Defaults
            "InternalFrame.icon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getInternalFrameDefaultMenuIcon"),
            "InternalFrame.border", new SwingLazyValue("javax.swing.plaf.metal.MetalBorders$InternalFrameBorder"),
            "InternalFrame.optionDialogBorder", new SwingLazyValue("javax.swing.plaf.metal.MetalBorders$OptionDialogBorder"),
            "InternalFrame.paletteBorder", new SwingLazyValue("javax.swing.plaf.metal.MetalBorders$PaletteBorder"),
            "InternalFrame.paletteTitleHeight", new Integer(11),
            "InternalFrame.paletteCloseIcon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory$PaletteCloseIcon"),
            "InternalFrame.closeIcon",
                  new SwingLazyValue(
                                     "javax.swing.plaf.metal.MetalIconFactory",
                                     "getInternalFrameCloseIcon",
                                     internalFrameIconArgs),
            "InternalFrame.maximizeIcon",
                  new SwingLazyValue(
                                     "javax.swing.plaf.metal.MetalIconFactory",
                                     "getInternalFrameMaximizeIcon",
                                     internalFrameIconArgs),
            "InternalFrame.iconifyIcon",
                  new SwingLazyValue(
                                     "javax.swing.plaf.metal.MetalIconFactory",
                                     "getInternalFrameMinimizeIcon",
                                     internalFrameIconArgs),
            "InternalFrame.minimizeIcon",
                  new SwingLazyValue(
                                     "javax.swing.plaf.metal.MetalIconFactory",
                                     "getInternalFrameAltMaximizeIcon",
                                     internalFrameIconArgs),
            "InternalFrame.titleFont",  windowTitleValue,
            "InternalFrame.windowBindings", null,
            // Internal Frame Auditory Cue Mappings
            "InternalFrame.closeSound", "sounds/FrameClose.wav",
            "InternalFrame.maximizeSound", "sounds/FrameMaximize.wav",
            "InternalFrame.minimizeSound", "sounds/FrameMinimize.wav",
            "InternalFrame.restoreDownSound", "sounds/FrameRestoreDown.wav",
            "InternalFrame.restoreUpSound", "sounds/FrameRestoreUp.wav",

            // Desktop Icon
            "DesktopIcon.border", desktopIconBorder,
            "DesktopIcon.font", controlTextValue,
            "DesktopIcon.foreground", controlTextColor,
            "DesktopIcon.background", control,
            "DesktopIcon.width", Integer.valueOf(160),

            "Desktop.ancestorInputMap",
               new UIDefaults.LazyInputMap(new Object[] {
                 "ctrl F5", "restore",
                 "ctrl F4", "close",
                 "ctrl F7", "move",
                 "ctrl F8", "resize",
                   "RIGHT", "right",
                "KP_RIGHT", "right",
             "shift RIGHT", "shrinkRight",
          "shift KP_RIGHT", "shrinkRight",
                    "LEFT", "left",
                 "KP_LEFT", "left",
              "shift LEFT", "shrinkLeft",
           "shift KP_LEFT", "shrinkLeft",
                      "UP", "up",
                   "KP_UP", "up",
                "shift UP", "shrinkUp",
             "shift KP_UP", "shrinkUp",
                    "DOWN", "down",
                 "KP_DOWN", "down",
              "shift DOWN", "shrinkDown",
           "shift KP_DOWN", "shrinkDown",
                  "ESCAPE", "escape",
                 "ctrl F9", "minimize",
                "ctrl F10", "maximize",
                 "ctrl F6", "selectNextFrame",
                "ctrl TAB", "selectNextFrame",
             "ctrl alt F6", "selectNextFrame",
       "shift ctrl alt F6", "selectPreviousFrame",
                "ctrl F12", "navigateNext",
           "shift ctrl F12", "navigatePrevious"
              }),

            // Titled Border
            "TitledBorder.font", controlTextValue,
            "TitledBorder.titleColor", systemTextColor,
            "TitledBorder.border", titledBorderBorder,

            // Label
            "Label.font", controlTextValue,
            "Label.foreground", systemTextColor,
            "Label.disabledForeground", getInactiveSystemTextColor(),

            // List
            "List.font", controlTextValue,
            "List.focusCellHighlightBorder", focusCellHighlightBorder,
            "List.focusInputMap",
               new UIDefaults.LazyInputMap(new Object[] {
                           "ctrl C", "copy",
                           "ctrl V", "paste",
                           "ctrl X", "cut",
                             "COPY", "copy",
                            "PASTE", "paste",
                              "CUT", "cut",
                   "control INSERT", "copy",
                     "shift INSERT", "paste",
                     "shift DELETE", "cut",
                               "UP", "selectPreviousRow",
                            "KP_UP", "selectPreviousRow",
                         "shift UP", "selectPreviousRowExtendSelection",
                      "shift KP_UP", "selectPreviousRowExtendSelection",
                    "ctrl shift UP", "selectPreviousRowExtendSelection",
                 "ctrl shift KP_UP", "selectPreviousRowExtendSelection",
                          "ctrl UP", "selectPreviousRowChangeLead",
                       "ctrl KP_UP", "selectPreviousRowChangeLead",
                             "DOWN", "selectNextRow",
                          "KP_DOWN", "selectNextRow",
                       "shift DOWN", "selectNextRowExtendSelection",
                    "shift KP_DOWN", "selectNextRowExtendSelection",
                  "ctrl shift DOWN", "selectNextRowExtendSelection",
               "ctrl shift KP_DOWN", "selectNextRowExtendSelection",
                        "ctrl DOWN", "selectNextRowChangeLead",
                     "ctrl KP_DOWN", "selectNextRowChangeLead",
                             "LEFT", "selectPreviousColumn",
                          "KP_LEFT", "selectPreviousColumn",
                       "shift LEFT", "selectPreviousColumnExtendSelection",
                    "shift KP_LEFT", "selectPreviousColumnExtendSelection",
                  "ctrl shift LEFT", "selectPreviousColumnExtendSelection",
               "ctrl shift KP_LEFT", "selectPreviousColumnExtendSelection",
                        "ctrl LEFT", "selectPreviousColumnChangeLead",
                     "ctrl KP_LEFT", "selectPreviousColumnChangeLead",
                            "RIGHT", "selectNextColumn",
                         "KP_RIGHT", "selectNextColumn",
                      "shift RIGHT", "selectNextColumnExtendSelection",
                   "shift KP_RIGHT", "selectNextColumnExtendSelection",
                 "ctrl shift RIGHT", "selectNextColumnExtendSelection",
              "ctrl shift KP_RIGHT", "selectNextColumnExtendSelection",
                       "ctrl RIGHT", "selectNextColumnChangeLead",
                    "ctrl KP_RIGHT", "selectNextColumnChangeLead",
                             "HOME", "selectFirstRow",
                       "shift HOME", "selectFirstRowExtendSelection",
                  "ctrl shift HOME", "selectFirstRowExtendSelection",
                        "ctrl HOME", "selectFirstRowChangeLead",
                              "END", "selectLastRow",
                        "shift END", "selectLastRowExtendSelection",
                   "ctrl shift END", "selectLastRowExtendSelection",
                         "ctrl END", "selectLastRowChangeLead",
                          "PAGE_UP", "scrollUp",
                    "shift PAGE_UP", "scrollUpExtendSelection",
               "ctrl shift PAGE_UP", "scrollUpExtendSelection",
                     "ctrl PAGE_UP", "scrollUpChangeLead",
                        "PAGE_DOWN", "scrollDown",
                  "shift PAGE_DOWN", "scrollDownExtendSelection",
             "ctrl shift PAGE_DOWN", "scrollDownExtendSelection",
                   "ctrl PAGE_DOWN", "scrollDownChangeLead",
                           "ctrl A", "selectAll",
                       "ctrl SLASH", "selectAll",
                  "ctrl BACK_SLASH", "clearSelection",
                            "SPACE", "addToSelection",
                       "ctrl SPACE", "toggleAndAnchor",
                      "shift SPACE", "extendTo",
                 "ctrl shift SPACE", "moveSelectionTo"
                 }),

            // ScrollBar
            "ScrollBar.background", control,
            "ScrollBar.highlight", controlHighlight,
            "ScrollBar.shadow", controlShadow,
            "ScrollBar.darkShadow", controlDarkShadow,
            "ScrollBar.thumb", primaryControlShadow,
            "ScrollBar.thumbShadow", primaryControlDarkShadow,
            "ScrollBar.thumbHighlight", primaryControl,
            "ScrollBar.width", new Integer( 17 ),
            "ScrollBar.allowsAbsolutePositioning", Boolean.TRUE,
            "ScrollBar.ancestorInputMap",
               new UIDefaults.LazyInputMap(new Object[] {
                       "RIGHT", "positiveUnitIncrement",
                    "KP_RIGHT", "positiveUnitIncrement",
                        "DOWN", "positiveUnitIncrement",
                     "KP_DOWN", "positiveUnitIncrement",
                   "PAGE_DOWN", "positiveBlockIncrement",
                        "LEFT", "negativeUnitIncrement",
                     "KP_LEFT", "negativeUnitIncrement",
                          "UP", "negativeUnitIncrement",
                       "KP_UP", "negativeUnitIncrement",
                     "PAGE_UP", "negativeBlockIncrement",
                        "HOME", "minScroll",
                         "END", "maxScroll"
                 }),

            // ScrollPane
            "ScrollPane.border", scrollPaneBorder,
            "ScrollPane.ancestorInputMap",
               new UIDefaults.LazyInputMap(new Object[] {
                           "RIGHT", "unitScrollRight",
                        "KP_RIGHT", "unitScrollRight",
                            "DOWN", "unitScrollDown",
                         "KP_DOWN", "unitScrollDown",
                            "LEFT", "unitScrollLeft",
                         "KP_LEFT", "unitScrollLeft",
                              "UP", "unitScrollUp",
                           "KP_UP", "unitScrollUp",
                         "PAGE_UP", "scrollUp",
                       "PAGE_DOWN", "scrollDown",
                    "ctrl PAGE_UP", "scrollLeft",
                  "ctrl PAGE_DOWN", "scrollRight",
                       "ctrl HOME", "scrollHome",
                        "ctrl END", "scrollEnd"
                 }),

            // Tabbed Pane
            "TabbedPane.font", controlTextValue,
            "TabbedPane.tabAreaBackground", control,
            "TabbedPane.background", controlShadow,
            "TabbedPane.light", control,
            "TabbedPane.focus", primaryControlDarkShadow,
            "TabbedPane.selected", control,
            "TabbedPane.selectHighlight", controlHighlight,
            "TabbedPane.tabAreaInsets", tabbedPaneTabAreaInsets,
            "TabbedPane.tabInsets", tabbedPaneTabInsets,
            "TabbedPane.focusInputMap",
              new UIDefaults.LazyInputMap(new Object[] {
                         "RIGHT", "navigateRight",
                      "KP_RIGHT", "navigateRight",
                          "LEFT", "navigateLeft",
                       "KP_LEFT", "navigateLeft",
                            "UP", "navigateUp",
                         "KP_UP", "navigateUp",
                          "DOWN", "navigateDown",
                       "KP_DOWN", "navigateDown",
                     "ctrl DOWN", "requestFocusForVisibleComponent",
                  "ctrl KP_DOWN", "requestFocusForVisibleComponent",
                }),
            "TabbedPane.ancestorInputMap",
               new UIDefaults.LazyInputMap(new Object[] {
                   "ctrl PAGE_DOWN", "navigatePageDown",
                     "ctrl PAGE_UP", "navigatePageUp",
                          "ctrl UP", "requestFocus",
                       "ctrl KP_UP", "requestFocus",
                 }),

            // Table
            "Table.font", userTextValue,
            "Table.focusCellHighlightBorder", focusCellHighlightBorder,
            "Table.scrollPaneBorder", scrollPaneBorder,
            "Table.dropLineColor", focusColor,
            "Table.dropLineShortColor", primaryControlDarkShadow,
            "Table.gridColor", controlShadow,  // grid line color
            "Table.ancestorInputMap",
               new UIDefaults.LazyInputMap(new Object[] {
                               "ctrl C", "copy",
                               "ctrl V", "paste",
                               "ctrl X", "cut",
                                 "COPY", "copy",
                                "PASTE", "paste",
                                  "CUT", "cut",
                       "control INSERT", "copy",
                         "shift INSERT", "paste",
                         "shift DELETE", "cut",
                                "RIGHT", "selectNextColumn",
                             "KP_RIGHT", "selectNextColumn",
                          "shift RIGHT", "selectNextColumnExtendSelection",
                       "shift KP_RIGHT", "selectNextColumnExtendSelection",
                     "ctrl shift RIGHT", "selectNextColumnExtendSelection",
                  "ctrl shift KP_RIGHT", "selectNextColumnExtendSelection",
                           "ctrl RIGHT", "selectNextColumnChangeLead",
                        "ctrl KP_RIGHT", "selectNextColumnChangeLead",
                                 "LEFT", "selectPreviousColumn",
                              "KP_LEFT", "selectPreviousColumn",
                           "shift LEFT", "selectPreviousColumnExtendSelection",
                        "shift KP_LEFT", "selectPreviousColumnExtendSelection",
                      "ctrl shift LEFT", "selectPreviousColumnExtendSelection",
                   "ctrl shift KP_LEFT", "selectPreviousColumnExtendSelection",
                            "ctrl LEFT", "selectPreviousColumnChangeLead",
                         "ctrl KP_LEFT", "selectPreviousColumnChangeLead",
                                 "DOWN", "selectNextRow",
                              "KP_DOWN", "selectNextRow",
                           "shift DOWN", "selectNextRowExtendSelection",
                        "shift KP_DOWN", "selectNextRowExtendSelection",
                      "ctrl shift DOWN", "selectNextRowExtendSelection",
                   "ctrl shift KP_DOWN", "selectNextRowExtendSelection",
                            "ctrl DOWN", "selectNextRowChangeLead",
                         "ctrl KP_DOWN", "selectNextRowChangeLead",
                                   "UP", "selectPreviousRow",
                                "KP_UP", "selectPreviousRow",
                             "shift UP", "selectPreviousRowExtendSelection",
                          "shift KP_UP", "selectPreviousRowExtendSelection",
                        "ctrl shift UP", "selectPreviousRowExtendSelection",
                     "ctrl shift KP_UP", "selectPreviousRowExtendSelection",
                              "ctrl UP", "selectPreviousRowChangeLead",
                           "ctrl KP_UP", "selectPreviousRowChangeLead",
                                 "HOME", "selectFirstColumn",
                           "shift HOME", "selectFirstColumnExtendSelection",
                      "ctrl shift HOME", "selectFirstRowExtendSelection",
                            "ctrl HOME", "selectFirstRow",
                                  "END", "selectLastColumn",
                            "shift END", "selectLastColumnExtendSelection",
                       "ctrl shift END", "selectLastRowExtendSelection",
                             "ctrl END", "selectLastRow",
                              "PAGE_UP", "scrollUpChangeSelection",
                        "shift PAGE_UP", "scrollUpExtendSelection",
                   "ctrl shift PAGE_UP", "scrollLeftExtendSelection",
                         "ctrl PAGE_UP", "scrollLeftChangeSelection",
                            "PAGE_DOWN", "scrollDownChangeSelection",
                      "shift PAGE_DOWN", "scrollDownExtendSelection",
                 "ctrl shift PAGE_DOWN", "scrollRightExtendSelection",
                       "ctrl PAGE_DOWN", "scrollRightChangeSelection",
                                  "TAB", "selectNextColumnCell",
                            "shift TAB", "selectPreviousColumnCell",
                                "ENTER", "selectNextRowCell",
                          "shift ENTER", "selectPreviousRowCell",
                               "ctrl A", "selectAll",
                           "ctrl SLASH", "selectAll",
                      "ctrl BACK_SLASH", "clearSelection",
                               "ESCAPE", "cancel",
                                   "F2", "startEditing",
                                "SPACE", "addToSelection",
                           "ctrl SPACE", "toggleAndAnchor",
                          "shift SPACE", "extendTo",
                     "ctrl shift SPACE", "moveSelectionTo",
                                   "F8", "focusHeader"
                 }),
            "Table.ascendingSortIcon",
                SwingUtilities2.makeIcon(getClass(), MetalLookAndFeel.class,
                "icons/sortUp.png"),
            "Table.descendingSortIcon",
                SwingUtilities2.makeIcon(getClass(), MetalLookAndFeel.class,
                "icons/sortDown.png"),

            "TableHeader.font", userTextValue,
            "TableHeader.cellBorder", new SwingLazyValue(
                                          "javax.swing.plaf.metal.MetalBorders$TableHeaderBorder"),

            // MenuBar
            "MenuBar.border", menuBarBorder,
            "MenuBar.font", menuTextValue,
            "MenuBar.windowBindings", new Object[] {
                "F10", "takeFocus" },

            // Menu
            "Menu.border", menuItemBorder,
            "Menu.borderPainted", Boolean.TRUE,
            "Menu.menuPopupOffsetX", zero,
            "Menu.menuPopupOffsetY", zero,
            "Menu.submenuPopupOffsetX", new Integer(-4),
            "Menu.submenuPopupOffsetY", new Integer(-3),
            "Menu.font", menuTextValue,
            "Menu.selectionForeground", menuSelectedForeground,
            "Menu.selectionBackground", menuSelectedBackground,
            "Menu.disabledForeground", menuDisabledForeground,
            "Menu.acceleratorFont", subTextValue,
            "Menu.acceleratorForeground", acceleratorForeground,
            "Menu.acceleratorSelectionForeground", acceleratorSelectedForeground,
            "Menu.checkIcon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getMenuItemCheckIcon"),
            "Menu.arrowIcon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getMenuArrowIcon"),

            // Menu Item
            "MenuItem.border", menuItemBorder,
            "MenuItem.borderPainted", Boolean.TRUE,
            "MenuItem.font", menuTextValue,
            "MenuItem.selectionForeground", menuSelectedForeground,
            "MenuItem.selectionBackground", menuSelectedBackground,
            "MenuItem.disabledForeground", menuDisabledForeground,
            "MenuItem.acceleratorFont", subTextValue,
            "MenuItem.acceleratorForeground", acceleratorForeground,
            "MenuItem.acceleratorSelectionForeground", acceleratorSelectedForeground,
            "MenuItem.acceleratorDelimiter", menuItemAcceleratorDelimiter,
            "MenuItem.checkIcon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getMenuItemCheckIcon"),
            "MenuItem.arrowIcon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getMenuItemArrowIcon"),
                 // Menu Item Auditory Cue Mapping
            "MenuItem.commandSound", "sounds/MenuItemCommand.wav",

            // OptionPane.
            "OptionPane.windowBindings", new Object[] {
                "ESCAPE", "close" },
            // Option Pane Auditory Cue Mappings
            "OptionPane.informationSound", "sounds/OptionPaneInformation.wav",
            "OptionPane.warningSound", "sounds/OptionPaneWarning.wav",
            "OptionPane.errorSound", "sounds/OptionPaneError.wav",
            "OptionPane.questionSound", "sounds/OptionPaneQuestion.wav",

            // Option Pane Special Dialog Colors, used when MetalRootPaneUI
            // is providing window manipulation widgets.
            "OptionPane.errorDialog.border.background",
                        new ColorUIResource(153, 51, 51),
            "OptionPane.errorDialog.titlePane.foreground",
                        new ColorUIResource(51, 0, 0),
            "OptionPane.errorDialog.titlePane.background",
                        new ColorUIResource(255, 153, 153),
            "OptionPane.errorDialog.titlePane.shadow",
                        new ColorUIResource(204, 102, 102),
            "OptionPane.questionDialog.border.background",
                        new ColorUIResource(51, 102, 51),
            "OptionPane.questionDialog.titlePane.foreground",
                        new ColorUIResource(0, 51, 0),
            "OptionPane.questionDialog.titlePane.background",
                        new ColorUIResource(153, 204, 153),
            "OptionPane.questionDialog.titlePane.shadow",
                        new ColorUIResource(102, 153, 102),
            "OptionPane.warningDialog.border.background",
                        new ColorUIResource(153, 102, 51),
            "OptionPane.warningDialog.titlePane.foreground",
                        new ColorUIResource(102, 51, 0),
            "OptionPane.warningDialog.titlePane.background",
                        new ColorUIResource(255, 204, 153),
            "OptionPane.warningDialog.titlePane.shadow",
                        new ColorUIResource(204, 153, 102),
            // OptionPane fonts are defined below

            // Separator
            "Separator.background", getSeparatorBackground(),
            "Separator.foreground", getSeparatorForeground(),

            // Popup Menu
            "PopupMenu.border", popupMenuBorder,
                 // Popup Menu Auditory Cue Mappings
            "PopupMenu.popupSound", "sounds/PopupMenuPopup.wav",
            "PopupMenu.font", menuTextValue,

            // CB & RB Menu Item
            "CheckBoxMenuItem.border", menuItemBorder,
            "CheckBoxMenuItem.borderPainted", Boolean.TRUE,
            "CheckBoxMenuItem.font", menuTextValue,
            "CheckBoxMenuItem.selectionForeground", menuSelectedForeground,
            "CheckBoxMenuItem.selectionBackground", menuSelectedBackground,
            "CheckBoxMenuItem.disabledForeground", menuDisabledForeground,
            "CheckBoxMenuItem.acceleratorFont", subTextValue,
            "CheckBoxMenuItem.acceleratorForeground", acceleratorForeground,
            "CheckBoxMenuItem.acceleratorSelectionForeground", acceleratorSelectedForeground,
            "CheckBoxMenuItem.checkIcon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getCheckBoxMenuItemIcon"),
            "CheckBoxMenuItem.arrowIcon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getMenuItemArrowIcon"),
            "CheckBoxMenuItem.commandSound", "sounds/MenuItemCommand.wav",

            "RadioButtonMenuItem.border", menuItemBorder,
            "RadioButtonMenuItem.borderPainted", Boolean.TRUE,
            "RadioButtonMenuItem.font", menuTextValue,
            "RadioButtonMenuItem.selectionForeground", menuSelectedForeground,
            "RadioButtonMenuItem.selectionBackground", menuSelectedBackground,
            "RadioButtonMenuItem.disabledForeground", menuDisabledForeground,
            "RadioButtonMenuItem.acceleratorFont", subTextValue,
            "RadioButtonMenuItem.acceleratorForeground", acceleratorForeground,
            "RadioButtonMenuItem.acceleratorSelectionForeground", acceleratorSelectedForeground,
            "RadioButtonMenuItem.checkIcon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getRadioButtonMenuItemIcon"),
            "RadioButtonMenuItem.arrowIcon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getMenuItemArrowIcon"),
            "RadioButtonMenuItem.commandSound", "sounds/MenuItemCommand.wav",

            "Spinner.ancestorInputMap",
               new UIDefaults.LazyInputMap(new Object[] {
                               "UP", "increment",
                            "KP_UP", "increment",
                             "DOWN", "decrement",
                          "KP_DOWN", "decrement",
               }),
            "Spinner.arrowButtonInsets", zeroInsets,
            "Spinner.border", textFieldBorder,
            "Spinner.arrowButtonBorder", buttonBorder,
            "Spinner.font", controlTextValue,

            // SplitPane

            "SplitPane.dividerSize", new Integer(10),
            "SplitPane.ancestorInputMap",
               new UIDefaults.LazyInputMap(new Object[] {
                        "UP", "negativeIncrement",
                      "DOWN", "positiveIncrement",
                      "LEFT", "negativeIncrement",
                     "RIGHT", "positiveIncrement",
                     "KP_UP", "negativeIncrement",
                   "KP_DOWN", "positiveIncrement",
                   "KP_LEFT", "negativeIncrement",
                  "KP_RIGHT", "positiveIncrement",
                      "HOME", "selectMin",
                       "END", "selectMax",
                        "F8", "startResize",
                        "F6", "toggleFocus",
                  "ctrl TAB", "focusOutForward",
            "ctrl shift TAB", "focusOutBackward"
                 }),
            "SplitPane.centerOneTouchButtons", Boolean.FALSE,
            "SplitPane.dividerFocusColor", primaryControl,

            // Tree
            // Tree.font was mapped to system font pre 1.4.1
            "Tree.font", userTextValue,
            "Tree.textBackground", getWindowBackground(),
            "Tree.selectionBorderColor", focusColor,
            "Tree.openIcon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getTreeFolderIcon"),
            "Tree.closedIcon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getTreeFolderIcon"),
            "Tree.leafIcon", new SwingLazyValue("javax.swing.plaf.metal.MetalIconFactory", "getTreeLeafIcon"),
            "Tree.expandedIcon", new SwingLazyValue(
                                     "javax.swing.plaf.metal.MetalIconFactory",
                                     "getTreeControlIcon",
                                     new Object[] {Boolean.valueOf(MetalIconFactory.DARK)}),
            "Tree.collapsedIcon", new SwingLazyValue(
                                     "javax.swing.plaf.metal.MetalIconFactory",
                                     "getTreeControlIcon",
                                     new Object[] {Boolean.valueOf( MetalIconFactory.LIGHT )}),

            "Tree.line", primaryControl, // horiz lines
            "Tree.hash", primaryControl,  // legs
            "Tree.rowHeight", zero,
            "Tree.focusInputMap",
               new UIDefaults.LazyInputMap(new Object[] {
                                    "ADD", "expand",
                               "SUBTRACT", "collapse",
                                 "ctrl C", "copy",
                                 "ctrl V", "paste",
                                 "ctrl X", "cut",
                                   "COPY", "copy",
                                  "PASTE", "paste",
                                    "CUT", "cut",
                         "control INSERT", "copy",
                           "shift INSERT", "paste",
                           "shift DELETE", "cut",
                                     "UP", "selectPrevious",
                                  "KP_UP", "selectPrevious",
                               "shift UP", "selectPreviousExtendSelection",
                            "shift KP_UP", "selectPreviousExtendSelection",
                          "ctrl shift UP", "selectPreviousExtendSelection",
                       "ctrl shift KP_UP", "selectPreviousExtendSelection",
                                "ctrl UP", "selectPreviousChangeLead",
                             "ctrl KP_UP", "selectPreviousChangeLead",
                                   "DOWN", "selectNext",
                                "KP_DOWN", "selectNext",
                             "shift DOWN", "selectNextExtendSelection",
                          "shift KP_DOWN", "selectNextExtendSelection",
                        "ctrl shift DOWN", "selectNextExtendSelection",
                     "ctrl shift KP_DOWN", "selectNextExtendSelection",
                              "ctrl DOWN", "selectNextChangeLead",
                           "ctrl KP_DOWN", "selectNextChangeLead",
                                  "RIGHT", "selectChild",
                               "KP_RIGHT", "selectChild",
                                   "LEFT", "selectParent",
                                "KP_LEFT", "selectParent",
                                "PAGE_UP", "scrollUpChangeSelection",
                          "shift PAGE_UP", "scrollUpExtendSelection",
                     "ctrl shift PAGE_UP", "scrollUpExtendSelection",
                           "ctrl PAGE_UP", "scrollUpChangeLead",
                              "PAGE_DOWN", "scrollDownChangeSelection",
                        "shift PAGE_DOWN", "scrollDownExtendSelection",
                   "ctrl shift PAGE_DOWN", "scrollDownExtendSelection",
                         "ctrl PAGE_DOWN", "scrollDownChangeLead",
                                   "HOME", "selectFirst",
                             "shift HOME", "selectFirstExtendSelection",
                        "ctrl shift HOME", "selectFirstExtendSelection",
                              "ctrl HOME", "selectFirstChangeLead",
                                    "END", "selectLast",
                              "shift END", "selectLastExtendSelection",
                         "ctrl shift END", "selectLastExtendSelection",
                               "ctrl END", "selectLastChangeLead",
                                     "F2", "startEditing",
                                 "ctrl A", "selectAll",
                             "ctrl SLASH", "selectAll",
                        "ctrl BACK_SLASH", "clearSelection",
                              "ctrl LEFT", "scrollLeft",
                           "ctrl KP_LEFT", "scrollLeft",
                             "ctrl RIGHT", "scrollRight",
                          "ctrl KP_RIGHT", "scrollRight",
                                  "SPACE", "addToSelection",
                             "ctrl SPACE", "toggleAndAnchor",
                            "shift SPACE", "extendTo",
                       "ctrl shift SPACE", "moveSelectionTo"
                 }),
            "Tree.ancestorInputMap",
               new UIDefaults.LazyInputMap(new Object[] {
                     "ESCAPE", "cancel"
                 }),

            // ToolBar
            "ToolBar.border", toolBarBorder,
            "ToolBar.background", menuBackground,
            "ToolBar.foreground", getMenuForeground(),
            "ToolBar.font", menuTextValue,
            "ToolBar.dockingBackground", menuBackground,
            "ToolBar.floatingBackground", menuBackground,
            "ToolBar.dockingForeground", primaryControlDarkShadow,
            "ToolBar.floatingForeground", primaryControl,
            "ToolBar.rolloverBorder", new MetalLazyValue(
                         "javax.swing.plaf.metal.MetalBorders",
                         "getToolBarRolloverBorder"),
            "ToolBar.nonrolloverBorder", new MetalLazyValue(
                         "javax.swing.plaf.metal.MetalBorders",
                         "getToolBarNonrolloverBorder"),
            "ToolBar.ancestorInputMap",
               new UIDefaults.LazyInputMap(new Object[] {
                        "UP", "navigateUp",
                     "KP_UP", "navigateUp",
                      "DOWN", "navigateDown",
                   "KP_DOWN", "navigateDown",
                      "LEFT", "navigateLeft",
                   "KP_LEFT", "navigateLeft",
                     "RIGHT", "navigateRight",
                  "KP_RIGHT", "navigateRight"
                 }),

            // RootPane
            "RootPane.frameBorder", new MetalLazyValue(
                      "javax.swing.plaf.metal.MetalBorders$FrameBorder"),
            "RootPane.plainDialogBorder", dialogBorder,
            "RootPane.informationDialogBorder", dialogBorder,
            "RootPane.errorDialogBorder", new MetalLazyValue(
                      "javax.swing.plaf.metal.MetalBorders$ErrorDialogBorder"),
            "RootPane.colorChooserDialogBorder", questionDialogBorder,
            "RootPane.fileChooserDialogBorder", questionDialogBorder,
            "RootPane.questionDialogBorder", questionDialogBorder,
            "RootPane.warningDialogBorder", new MetalLazyValue(
                    "javax.swing.plaf.metal.MetalBorders$WarningDialogBorder"),
            // These bindings are only enabled when there is a default
            // button set on the rootpane.
            "RootPane.defaultButtonWindowKeyBindings", new Object[] {
                             "ENTER", "press",
                    "released ENTER", "release",
                        "ctrl ENTER", "press",
               "ctrl released ENTER", "release"
              },
        };

        table.putDefaults(defaults);

        if (isWindows() && useSystemFonts() && theme.isSystemTheme()) {
            Object messageFont = new GlassForestFontDesktopProperty(
                "win.messagebox.font.height", GlassForestTheme.CONTROL_TEXT_FONT);

            defaults = new Object[] {
                "OptionPane.messageFont", messageFont,
                "OptionPane.buttonFont", messageFont,
            };
            table.putDefaults(defaults);
        }

        flushUnreferenced(); // Remove old listeners

        boolean lafCond = SwingUtilities2.isLocalDisplay();
        Object aaTextInfo = SwingUtilities2.AATextInfo.getAATextInfo(lafCond);
        table.put(SwingUtilities2.AA_TEXT_PROPERTY_KEY, aaTextInfo);
        new AATextListener(this);
    }
    
    
    public LayoutStyle getLayoutStyle() {
        return GlassForestLayoutStyle.INSTANCE;
    }
    
    private static class GlassForestLayoutStyle extends DefaultLayoutStyle {
    	private static GlassForestLayoutStyle INSTANCE = new GlassForestLayoutStyle();
    	
    	@Override
        public int getPreferredGap(JComponent component1,
                JComponent component2, ComponentPlacement type, int position,
                Container parent) {
            // Checks args
            super.getPreferredGap(component1, component2, type, position,
                                  parent);

            int offset = 0;

            switch(type) {
            case INDENT:
                // Metal doesn't spec this.
                if (position == SwingConstants.EAST ||
                        position == SwingConstants.WEST) {
                    int indent = getIndent(component1, position);
                    if (indent > 0) {
                        return indent;
                    }
                    return 12;
                }
                // Fall through to related.
            case RELATED:
                if (component1.getUIClassID() == "ToggleButtonUI" &&
                        component2.getUIClassID() == "ToggleButtonUI") {
                    ButtonModel sourceModel = ((JToggleButton)component1).
                            getModel();
                    ButtonModel targetModel = ((JToggleButton)component2).
                            getModel();
                    if ((sourceModel instanceof DefaultButtonModel) &&
                        (targetModel instanceof DefaultButtonModel) &&
                        (((DefaultButtonModel)sourceModel).getGroup() ==
                         ((DefaultButtonModel)targetModel).getGroup()) &&
                        ((DefaultButtonModel)sourceModel).getGroup() != null) {
                        // When toggle buttons are exclusive (that is,
                        // they form a radio button set), separate
                        // them with 2 pixels. This rule applies
                        // whether the toggle buttons appear in a
                        // toolbar or elsewhere in the interface.
                        // Note: this number does not appear to
                        // include any borders and so is not adjusted
                        // by the border of the toggle button
                        return 2;
                    }
                    
                    return 5;
                }
                offset = 6;
                break;
            case UNRELATED:
                offset = 12;
                break;
            }
            if (isLabelAndNonlabel(component1, component2, position)) {
                // Insert 12 pixels between the trailing edge of a
                // label and any associated components. Insert 12
                // pixels between the trailing edge of a label and the
                // component it describes when labels are
                // right-aligned. When labels are left-aligned, insert
                // 12 pixels between the trailing edge of the longest
                // label and its associated component
                return getButtonGap(component1, component2, position,
                                    offset + 6);
            }
            return getButtonGap(component1, component2, position, offset);
        }

        @Override
        public int getContainerGap(JComponent component, int position,
                                   Container parent) {
            super.getContainerGap(component, position, parent);
            // Include 11 pixels between the bottom and right
            // borders of a dialog box and its command
            // buttons. (To the eye, the 11-pixel spacing appears
            // to be 12 pixels because the white borders on the
            // lower and right edges of the button components are
            // not visually significant.)
            // NOTE: this last text was designed with Steel in mind,
            // not Ocean.
            //
            // Insert 12 pixels between the edges of the panel and the
            // titled border. Insert 11 pixels between the top of the
            // title and the component above the titled border. Insert 12
            // pixels between the bottom of the title and the top of the
            // first label in the panel. Insert 11 pixels between
            // component groups and between the bottom of the last
            // component and the lower border.
            return getButtonGap(component, position, 12 -
                                getButtonAdjustment(component, position));
        }

        @Override
        protected int getButtonGap(JComponent source, JComponent target,
                                   int position, int offset) {
            offset = super.getButtonGap(source, target, position, offset);
            if (offset > 0) {
                int buttonAdjustment = getButtonAdjustment(source, position);
                if (buttonAdjustment == 0) {
                    buttonAdjustment = getButtonAdjustment(
                            target, flipDirection(position));
                }
                offset -= buttonAdjustment;
            }
            if (offset < 0) {
                return 0;
            }
            return offset;
        }

        private int getButtonAdjustment(JComponent source, int edge) {
            String classID = source.getUIClassID();
            if (classID == "ButtonUI" || classID == "ToggleButtonUI") {
                if ((edge == SwingConstants.EAST ||
                                      edge == SwingConstants.SOUTH)) {
                    if (source.getBorder() instanceof UIResource) {
                        return 1;
                    }
                }
            }
            else if (edge == SwingConstants.SOUTH) {
                if ((classID == "RadioButtonUI" || classID == "CheckBoxUI") ) {
                    return 1;
                }
            }
            return 0;
        }
    }

    /**
     * MetalLazyValue is a slimmed down version of <code>ProxyLaxyValue</code>.
     * The code is duplicate so that it can get at the package private
     * classes in metal.
     */
    private static class MetalLazyValue implements UIDefaults.LazyValue {
        /**
         * Name of the class to create.
         */
        private String className;
        private String methodName;

        MetalLazyValue(String name) {
            this.className = name;
        }

        MetalLazyValue(String name, String methodName) {
            this(name);
            this.methodName = methodName;
        }

        public Object createValue(UIDefaults table) {
            try {
                final Class c = Class.forName(className);

                if (methodName == null) {
                    return c.newInstance();
                }
                Method method = AccessController.doPrivileged(
                    new PrivilegedAction<Method>() {
                    public Method run() {
                        Method[] methods = c.getDeclaredMethods();
                        for (int counter = methods.length - 1; counter >= 0;
                             counter--) {
                            if (methods[counter].getName().equals(methodName)){
                                methods[counter].setAccessible(true);
                                return methods[counter];
                            }
                        }
                        return null;
                    }
                });
                if (method != null) {
                    return method.invoke(null, (Object[])null);
                }
            } catch (ClassNotFoundException cnfe) {
            } catch (InstantiationException ie) {
            } catch (IllegalAccessException iae) {
            } catch (InvocationTargetException ite) {
            }
            return null;
        }
    }


    /**
     * FontActiveValue redirects to the appropriate metal theme method.
     */
    private static class FontActiveValue implements UIDefaults.ActiveValue {
        private int type;
        private GlassForestTheme theme;

        FontActiveValue(GlassForestTheme theme, int type) {
            this.theme = theme;
            this.type = type;
        }

        public Object createValue(UIDefaults table) {
            Object value = null;
            switch (type) {
            case GlassForestTheme.CONTROL_TEXT_FONT:
                value = theme.getControlTextFont();
                break;
            case GlassForestTheme.SYSTEM_TEXT_FONT:
                value = theme.getSystemTextFont();
                break;
            case GlassForestTheme.USER_TEXT_FONT:
                value = theme.getUserTextFont();
                break;
            case GlassForestTheme.MENU_TEXT_FONT:
                value = theme.getMenuTextFont();
                break;
            case GlassForestTheme.WINDOW_TITLE_FONT:
                value = theme.getWindowTitleFont();
                break;
            case GlassForestTheme.SUB_TEXT_FONT:
                value = theme.getSubTextFont();
                break;
            }
            return value;
        }
    }

    static ReferenceQueue<LookAndFeel> queue = new ReferenceQueue<LookAndFeel>();

    static void flushUnreferenced() {
        AATextListener aatl;
        while ((aatl = (AATextListener)queue.poll()) != null) {
            aatl.dispose();
        }
    }

    static class AATextListener
        extends WeakReference<LookAndFeel> implements PropertyChangeListener {

        private String key = SunToolkit.DESKTOPFONTHINTS;

        AATextListener(LookAndFeel laf) {
            super(laf, queue);
            Toolkit tk = Toolkit.getDefaultToolkit();
            tk.addPropertyChangeListener(key, this);
        }

        public void propertyChange(PropertyChangeEvent pce) {
            LookAndFeel laf = get();
            if (laf == null || laf != UIManager.getLookAndFeel()) {
                dispose();
                return;
            }
            UIDefaults defaults = UIManager.getLookAndFeelDefaults();
            boolean lafCond = SwingUtilities2.isLocalDisplay();
            Object aaTextInfo =
                SwingUtilities2.AATextInfo.getAATextInfo(lafCond);
            defaults.put(SwingUtilities2.AA_TEXT_PROPERTY_KEY, aaTextInfo);
            updateUI();
        }

        void dispose() {
            Toolkit tk = Toolkit.getDefaultToolkit();
            tk.removePropertyChangeListener(key, this);
        }

        /**
         * Updates the UI of the passed in window and all its children.
         */
        private static void updateWindowUI(Window window) {
            SwingUtilities.updateComponentTreeUI(window);
            Window ownedWins[] = window.getOwnedWindows();
            for (Window w : ownedWins) {
                updateWindowUI(w);
            }
        }

        /**
         * Updates the UIs of all the known Frames.
         */
        private static void updateAllUIs() {
            Frame appFrames[] = Frame.getFrames();
            for (Frame frame : appFrames) {
                updateWindowUI(frame);
            }
        }

        /**
         * Indicates if an updateUI call is pending.
         */
        private static boolean updatePending;

        /**
         * Sets whether or not an updateUI call is pending.
         */
        private static synchronized void setUpdatePending(boolean update) {
            updatePending = update;
        }

        /**
         * Returns true if a UI update is pending.
         */
        private static synchronized boolean isUpdatePending() {
            return updatePending;
        }

        protected void updateUI() {
            if (!isUpdatePending()) {
                setUpdatePending(true);
                Runnable uiUpdater = new Runnable() {
                        public void run() {
                            updateAllUIs();
                            setUpdatePending(false);
                        }
                    };
                SwingUtilities.invokeLater(uiUpdater);
            }
        }
    }

}
