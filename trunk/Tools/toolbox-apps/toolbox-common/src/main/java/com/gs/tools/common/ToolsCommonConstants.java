/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.tools.common;

import java.io.File;

/**
 *
 * @author 50120C1509
 */
public interface ToolsCommonConstants {
    
    String USER_HOME = System.getProperty("user.home");
    
    String DirectoryTree_IMAGE_PATH = "/common/images/DirectoryTree";
    
    final class TOOLS_CONSTANTS{
        public static File[] getFileSystemRootFiles(){
            return File.listRoots();
        }
        
        public static String[] getFileSystemRootNames(){
            File[] roots = File.listRoots();
            if(null != roots && roots.length > 0){
                String[] rootNames = new String[roots.length];
                for (int i = 0; i < roots.length; i++) {
                    File file = roots[i];
                    rootNames[i] = file.getPath();
                }
                return rootNames;
            }
            return null;
        }
    }
    
}
