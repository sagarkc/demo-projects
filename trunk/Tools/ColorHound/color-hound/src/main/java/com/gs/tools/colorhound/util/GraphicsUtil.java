/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.tools.colorhound.util;

import java.awt.Color;

/**
 *
 * @author Sabuj
 */
public final class GraphicsUtil {
    
    public static String encodeColor(Color color){
        if(null == color)
            return "0x000000";
        
        return "0x"+String.format("%02x%02x%02x", 
                color.getRed(), 
                color.getGreen(), 
                color.getBlue()).toUpperCase();
    }
    
    public static String encodeColorForCss(Color color){
        if(null == color)
            return "#000000";
        
        return String.format("#%02x%02x%02x", 
                color.getRed(), 
                color.getGreen(), 
                color.getBlue()).toUpperCase();
    }
}
