/** ***************************************************************************
 *		MetalOfficeLnF :: Office Look and Feel from Metal	
 *	
 *	File	: MetalOffice2003ButtonUI.java
 *	Type	: com.gs.plaf.office.office2003.control.MetalOffice2003ButtonUI.java
 *	Date	: Jan 3, 2010	5:27:39 PM
 *
 *	Author	: Sabuj Das
 *
 *	
 *****************************************************************************/
package com.gs.plaf.office.office2003.control;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.AbstractButton;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalButtonUI;

import com.gs.plaf.common.ColorRoutines;

/**
 *
 */
public class MetalOffice2003ButtonUI extends MetalButtonUI {

	public static final int BG_CHANGE_AMOUNT = 10;
	private static final MetalOffice2003ButtonUI buttonUI = new MetalOffice2003ButtonUI();
	private static final BasicStroke FOCUS_STROKE = new BasicStroke(1.0F, 0, 2,
			1.0F, new float[] { 1.0F, 1.0F }, 0.0F);

	private boolean graphicsTranslated;
	private boolean isToolBarButton;
	private boolean isFileChooserButton;
	private boolean isDefault;

	public void installUI(JComponent c) {
		super.installUI(c);
		if (!(c.isFocusable()))
			return;
		InputMap km = (InputMap) UIManager.get(getPropertyPrefix()
				+ "focusInputMap");
		if (km == null)
			return;
		km.put(KeyStroke.getKeyStroke(10, 0, false), "pressed");
		km.put(KeyStroke.getKeyStroke(10, 0, true), "released");
	}

	public void installDefaults(AbstractButton b) {
		super.installDefaults(b);
		b.setRolloverEnabled(true);
	}

	protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect,
			Rectangle textRect, Rectangle iconRect) {
		Graphics2D g2d = (Graphics2D) g;
		Rectangle focusRect = b.getBounds();

		g.setColor(Color.black);
		g2d.setStroke(FOCUS_STROKE);

		int x1 = 2;
		int y1 = 2;
		int x2 = x1 + focusRect.width - 5;
		int y2 = y1 + focusRect.height - 5;

		if (!(this.isToolBarButton)) {
			++x1;
			++y1;
			--x2;
			--y2;
		}

		if (this.graphicsTranslated) {
			g.translate(-1, -1);
		}

		g2d.drawLine(x1, y1, x2, y1);
		g2d.drawLine(x1, y1, x1, y2);
		g2d.drawLine(x1, y2, x2, y2);
		g2d.drawLine(x2, y1, x2, y2);
	}
	
	public static ComponentUI createUI(JComponent c)
	{
		return buttonUI;
	}
	
	
	protected void paintButtonPressed(Graphics g, AbstractButton b) {
		if ((this.isToolBarButton) || (this.isFileChooserButton)) 
			return;
		
	}
	
	private void drawXpButton(Graphics g, AbstractButton b, Color c,
			boolean isRollover) {
		if (!(b.isContentAreaFilled()))
			return;
		if (!(b.isOpaque()))
			return;

		int w = b.getWidth();
		int h = b.getHeight();

		Color bg = b.getParent().getBackground();
		g.setColor(bg);
		g.drawRect(0, 0, w - 1, h - 1);

		int spread1 = Theme.buttonSpreadLight[Theme.style];
		int spread2 = Theme.buttonSpreadDark[Theme.style];
		if (!(b.isEnabled())) {
			spread1 = Theme.buttonSpreadLightDisabled[Theme.style];
			spread2 = Theme.buttonSpreadDarkDisabled[Theme.style];
		}

		float spreadStep1 = 10.0F * spread1 / (h - 3);
		float spreadStep2 = 10.0F * spread2 / (h - 3);
		int halfY = h / 2;

		for (int y = 1; y < h - 1; ++y) {
			int yd;
			if (y < halfY) {
				yd = halfY - y;
				g.setColor(ColorRoutines.lighten(c, (int) (yd * spreadStep1)));
			} else if (y == halfY) {
				g.setColor(c);
			} else {
				yd = y - halfY;
				g.setColor(ColorRoutines.darken(c, (int) (yd * spreadStep2)));
			}

			g.drawLine(2, y, w - 3, y);

			if (y == 1) {
				g.drawLine(1, 1, 1, h - 2);

				if ((!(isRollover)) && (!(this.isDefault)))
					continue;
				g.drawLine(w - 2, 1, w - 2, h - 2);
			} else {
				if ((y != h - 2) || (isRollover) || (this.isDefault))
					continue;
				g.drawLine(w - 2, 1, w - 2, h - 2);
			}

		}

		if (isRollover) {
			g.setColor(Theme.buttonRolloverColor[Theme.style].getColor());
			g.drawLine(1, h - 2, 1, h - 2);
			g.drawLine(w - 2, h - 2, w - 2, h - 2);
		} else if (this.isDefault) {
			g.setColor(Theme.buttonDefaultColor[Theme.style].getColor());
			g.drawLine(1, h - 2, 1, h - 2);
			g.drawLine(w - 2, h - 2, w - 2, h - 2);
		}
	}
	
	private void drawXpToolBarButton(Graphics g, AbstractButton b, Color c,
			boolean isPressed) {
		int w = b.getWidth();
		int h = b.getHeight();

		if (b.isContentAreaFilled()) {
			g.fillRect(1, 1, w - 2, h - 2);
		}

		Color bg = b.getParent().getBackground();
		g.setColor(bg);
		g.drawRect(0, 0, w - 1, h - 1);
	}
}
