/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.tools.net.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author 50120C1509
 */
public class AppContext implements Serializable{

    private static AppContext appContext = null;

    private AppContext(){}

    public static synchronized  AppContext getAppContext() {
        if(null == appContext){
            appContext = new AppContext();
        }
        return appContext;
    }

    public Set<Configuration> configurationList = new LinkedHashSet<Configuration>();
    public String lastSelectedPath = ".";
}
