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

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

import javax.swing.Icon;
import javax.swing.UIManager;

import sun.swing.ImageIconUIResource;

/**
 * @author Sabuj Das
 *
 */
public class GlassForestUtils {

	public static Icon getDisabledButtonIcon(Image image) {
        Object[] range = (Object[])UIManager.get("Button.disabledGrayRange");
        int min = 180;
        int max = 215;
        if (range != null) {
            min = ((Integer)range[0]).intValue();
            max = ((Integer)range[1]).intValue();
        }
        ImageProducer prod = new FilteredImageSource(image.getSource(),
                      new DisabledButtonImageFilter(min , max));
        return new ImageIconUIResource(Toolkit.getDefaultToolkit().createImage(prod));
    }
	
	/**
     * Used to create a disabled Icon with the ocean look.
     */
    private static class DisabledButtonImageFilter extends RGBImageFilter{
        private float min;
        private float factor;

        DisabledButtonImageFilter(int min, int max) {
            canFilterIndexColorModel = true;
            this.min = (float)min;
            this.factor = (max - min) / 255f;
        }

        public int filterRGB(int x, int y, int rgb) {
            // Coefficients are from the sRGB color space:
            int gray = Math.min(255, (int)(((0.2125f * ((rgb >> 16) & 0xFF)) +
                    (0.7154f * ((rgb >> 8) & 0xFF)) +
                    (0.0721f * (rgb & 0xFF)) + .5f) * factor + min));

            return (rgb & 0xff000000) | (gray << 16) | (gray << 8) |
                (gray << 0);
        }
    }
}
