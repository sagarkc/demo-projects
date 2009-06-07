/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jnp.utils;

import java.awt.Dimension;
import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author Green Moon
 */
public class NotePadContext implements Serializable{

    protected static NotePadContext instance;
    private NotePadContext(){
        openFileList = null;
    }
    public static NotePadContext getInstance(){
        if(instance == null)
            instance = new NotePadContext();
        return instance;
    }


    public String[] openFileList;
    public int currentTabIndex = -1;
    public Dimension frameSize;
    public Point frameLocation;
    public String windowState;

}
