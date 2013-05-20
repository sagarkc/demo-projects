/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.glassforest.util.GlassForestUtils
 * Date     : May 20, 2013
 */
package com.gs.glassforest.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import sun.swing.CachedPainter;
import sun.swing.ImageIconUIResource;

/**
 * @author Sabuj Das
 * 
 */
public class GlassForestUtils {

	public static Icon getDisabledButtonIcon(Image image) {
		Object[] range = (Object[]) UIManager.get("Button.disabledGrayRange");
		int min = 180;
		int max = 215;
		if (range != null) {
			min = ((Integer) range[0]).intValue();
			max = ((Integer) range[1]).intValue();
		}
		ImageProducer prod = new FilteredImageSource(image.getSource(),
				new DisabledButtonImageFilter(min, max));
		return new ImageIconUIResource(Toolkit.getDefaultToolkit().createImage(
				prod));
	}

	/**
	 * Used to create a disabled Icon with the ocean look.
	 */
	private static class DisabledButtonImageFilter extends RGBImageFilter {
		private float min;
		private float factor;

		DisabledButtonImageFilter(int min, int max) {
			canFilterIndexColorModel = true;
			this.min = (float) min;
			this.factor = (max - min) / 255f;
		}

		public int filterRGB(int x, int y, int rgb) {
			// Coefficients are from the sRGB color space:
			int gray = Math.min(255, (int) (((0.2125f * ((rgb >> 16) & 0xFF))
					+ (0.7154f * ((rgb >> 8) & 0xFF))
					+ (0.0721f * (rgb & 0xFF)) + .5f)
					* factor + min));

			return (rgb & 0xff000000) | (gray << 16) | (gray << 8)
					| (gray << 0);
		}
	}

	/**
	 * @param c
	 * @return
	 */
	public static boolean isToolBarButton(JComponent c) {
		return (c.getParent() instanceof JToolBar);
	}

	public static boolean drawGradient(Component c, Graphics g, String key, int x,
			int y, int w, int h, boolean vertical) {
		java.util.List gradient = (java.util.List) UIManager.get(key);
		if (gradient == null || !(g instanceof Graphics2D)) {
			return false;
		}

		if (w <= 0 || h <= 0) {
			return true;
		}

		GradientPainter.INSTANCE.paint(c, (Graphics2D) g, gradient, x, y, w, h,
				vertical);
		return true;
	}

	private static class GradientPainter extends CachedPainter {
		/**
		 * Instance used for painting. This is the only instance that is ever
		 * created.
		 */
		public static final GradientPainter INSTANCE = new GradientPainter(8);

		// Size of images to create. For vertical gradients this is the width,
		// otherwise it's the height.
		private static final int IMAGE_SIZE = 64;

		/**
		 * This is the actual width we're painting in, or last painted to.
		 */
		private int w;
		/**
		 * This is the actual height we're painting in, or last painted to
		 */
		private int h;

		GradientPainter(int count) {
			super(count);
		}

		public void paint(Component c, Graphics2D g, java.util.List gradient,
				int x, int y, int w, int h, boolean isVertical) {
			int imageWidth;
			int imageHeight;
			if (isVertical) {
				imageWidth = IMAGE_SIZE;
				imageHeight = h;
			} else {
				imageWidth = w;
				imageHeight = IMAGE_SIZE;
			}
			synchronized (c.getTreeLock()) {
				this.w = w;
				this.h = h;
				paint(c, g, x, y, imageWidth, imageHeight, gradient, isVertical);
			}
		}

		protected void paintToImage(Component c, Image image, Graphics g,
				int w, int h, Object[] args) {
			Graphics2D g2 = (Graphics2D) g;
			java.util.List gradient = (java.util.List) args[0];
			boolean isVertical = ((Boolean) args[1]).booleanValue();
			// Render to the VolatileImage
			if (isVertical) {
				drawVerticalGradient(g2,
						((Number) gradient.get(0)).floatValue(),
						((Number) gradient.get(1)).floatValue(),
						(Color) gradient.get(2), (Color) gradient.get(3),
						(Color) gradient.get(4), w, h);
			} else {
				drawHorizontalGradient(g2,
						((Number) gradient.get(0)).floatValue(),
						((Number) gradient.get(1)).floatValue(),
						(Color) gradient.get(2), (Color) gradient.get(3),
						(Color) gradient.get(4), w, h);
			}
		}

		protected void paintImage(Component c, Graphics g, int x, int y,
				int imageW, int imageH, Image image, Object[] args) {
			boolean isVertical = ((Boolean) args[1]).booleanValue();
			// Render to the screen
			g.translate(x, y);
			if (isVertical) {
				for (int counter = 0; counter < w; counter += IMAGE_SIZE) {
					int tileSize = Math.min(IMAGE_SIZE, w - counter);
					g.drawImage(image, counter, 0, counter + tileSize, h, 0, 0,
							tileSize, h, null);
				}
			} else {
				for (int counter = 0; counter < h; counter += IMAGE_SIZE) {
					int tileSize = Math.min(IMAGE_SIZE, h - counter);
					g.drawImage(image, 0, counter, w, counter + tileSize, 0, 0,
							w, tileSize, null);
				}
			}
			g.translate(-x, -y);
		}

		private void drawVerticalGradient(Graphics2D g, float ratio1,
				float ratio2, Color c1, Color c2, Color c3, int w, int h) {
			int mid = (int) (ratio1 * h);
			int mid2 = (int) (ratio2 * h);
			if (mid > 0) {
				g.setPaint(getGradient((float) 0, (float) 0, c1, (float) 0,
						(float) mid, c2));
				g.fillRect(0, 0, w, mid);
			}
			if (mid2 > 0) {
				g.setColor(c2);
				g.fillRect(0, mid, w, mid2);
			}
			if (mid > 0) {
				g.setPaint(getGradient((float) 0, (float) mid + mid2, c2,
						(float) 0, (float) mid * 2 + mid2, c1));
				g.fillRect(0, mid + mid2, w, mid);
			}
			if (h - mid * 2 - mid2 > 0) {
				g.setPaint(getGradient((float) 0, (float) mid * 2 + mid2, c1,
						(float) 0, (float) h, c3));
				g.fillRect(0, mid * 2 + mid2, w, h - mid * 2 - mid2);
			}
		}

		private void drawHorizontalGradient(Graphics2D g, float ratio1,
				float ratio2, Color c1, Color c2, Color c3, int w, int h) {
			int mid = (int) (ratio1 * w);
			int mid2 = (int) (ratio2 * w);
			if (mid > 0) {
				g.setPaint(getGradient((float) 0, (float) 0, c1, (float) mid,
						(float) 0, c2));
				g.fillRect(0, 0, mid, h);
			}
			if (mid2 > 0) {
				g.setColor(c2);
				g.fillRect(mid, 0, mid2, h);
			}
			if (mid > 0) {
				g.setPaint(getGradient((float) mid + mid2, (float) 0, c2,
						(float) mid * 2 + mid2, (float) 0, c1));
				g.fillRect(mid + mid2, 0, mid, h);
			}
			if (w - mid * 2 - mid2 > 0) {
				g.setPaint(getGradient((float) mid * 2 + mid2, (float) 0, c1,
						w, (float) 0, c3));
				g.fillRect(mid * 2 + mid2, 0, w - mid * 2 - mid2, h);
			}
		}

		private GradientPaint getGradient(float x1, float y1, Color c1,
				float x2, float y2, Color c2) {
			return new GradientPaint(x1, y1, c1, x2, y2, c2, true);
		}
	}
}
