/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.glassforest.demo.GlassForestDemo
 * Date     : May 20, 2013
 */

package com.gs.glassforest.demo;

import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

import com.gs.glassforest.plaf.GlassForestLookAndFeel;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class GlassForestDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
        	UIManager.installLookAndFeel(GlassForestLookAndFeel.GLASS_FOREST, 
        			GlassForestLookAndFeel.class.getCanonicalName()
        			);
            UIManager.setLookAndFeel(new GlassForestLookAndFeel());
        } catch (Exception ex) {
        	ex.printStackTrace();
        	try {
				UIManager.setLookAndFeel(MetalLookAndFeel.class.getCanonicalName());
			} catch( Exception e) {
			}
        }
        new GlassForestFrame().setVisible(true);
    }

}
