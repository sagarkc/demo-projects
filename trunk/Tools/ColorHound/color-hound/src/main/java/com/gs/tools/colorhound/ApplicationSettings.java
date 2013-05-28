/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.tools.colorhound;

import java.io.File;
import java.io.Serializable;

/**
 *
 * @author Sabuj
 */
public class ApplicationSettings implements Serializable{
    
    private boolean doNotShowExitDialog = true;
    private String appDataPath ;
    private String appSettingsPath ;
    
    public ApplicationSettings() {
        appDataPath = System.getProperty("user.home")
            + File.separator + ".colorhound"
            + File.separator + "app-data";
        appSettingsPath = System.getProperty("user.home")
            + File.separator + ".colorhound";
    }

    public boolean isDoNotShowExitDialog() {
        return doNotShowExitDialog;
    }

    public void setDoNotShowExitDialog(boolean doNotShowExitDialog) {
        this.doNotShowExitDialog = doNotShowExitDialog;
    }

    public String getAppDataPath() {
        return appDataPath;
    }

    public void setAppDataPath(String appDataPath) {
        this.appDataPath = appDataPath;
    }

    public String getAppSettingsPath() {
        return appSettingsPath;
    }

    public void setAppSettingsPath(String appSettingsPath) {
        this.appSettingsPath = appSettingsPath;
    }
    
    
}
