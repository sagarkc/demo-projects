/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xchanging.etl.mgr.desk.util;

import com.xchanging.etl.mgr.desk.EtlManagerConstants;

/**
 *
 * @author SG1736
 */
public enum LafEnum {
    
    LAF_SYSTEM("System Default", EtlManagerConstants.LAF_SYSTEM),
    LAF_METAL("Metal", EtlManagerConstants.LAF_METAL),
    LAF_MOTIF("Motif", EtlManagerConstants.LAF_MOTIF),
    LAF_NIMBUS("Nimbus", EtlManagerConstants.LAF_NIMBUS),
    LAF_WINDOWS("Windows", EtlManagerConstants.LAF_WINDOWS),
    LAF_WINDOWS_CLASSIC("Windows Classic", EtlManagerConstants.LAF_WINDOWS_CLASSIC);
    
    private final String name;
    private final String className;

    private LafEnum(String name, String className) {
        this.name = name;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }
    
    public static LafEnum getLafEnum(String name){
        if(null != name && !"".equals(name.trim())){
            if(LAF_METAL.getName().equals(name) || LAF_METAL.getClassName().equals(name)){
                return LAF_METAL;
            } else if(LAF_MOTIF.getName().equals(name) || LAF_METAL.getClassName().equals(name)){
                return LAF_MOTIF;
            } else if(LAF_NIMBUS.getName().equals(name) || LAF_METAL.getClassName().equals(name)){
                return LAF_NIMBUS;
            } else if(LAF_WINDOWS.getName().equals(name) || LAF_METAL.getClassName().equals(name)){
                return LAF_WINDOWS;
            } else if(LAF_WINDOWS_CLASSIC.getName().equals(name) || LAF_METAL.getClassName().equals(name)){
                return LAF_WINDOWS_CLASSIC;
            }
        }
        return LAF_SYSTEM;
    }
}
