/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.tools.net.core;

import java.io.Serializable;

/**
 *
 * @author 50120C1509
 */
public class Configuration implements Serializable{

    private String configName = "";
    private String hostName = "";
    private String userName = "";
    private String password = "";
    private Integer displayOrder = 0;
    private boolean propertySaved = false;

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isPropertySaved() {
        return propertySaved;
    }

    public void setPropertySaved(boolean propertySaved) {
        this.propertySaved = propertySaved;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Configuration other = (Configuration) obj;
        if ((this.configName == null) ? (other.configName != null) : !this.configName.equals(other.configName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.configName != null ? this.configName.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return configName;
    }


}
