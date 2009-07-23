/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.ws.invoker.util;

import com.gs.ws.invoker.log.MainLogger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Green Moon
 */
public class FileRWUtil {

    public static String readAsText(File file){
        if(file == null)
            return "";
        StringBuffer buffer = new StringBuffer();
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(file));
            String line = "";
            while((line = br.readLine()) != null){
                buffer.append(line+"\n");
            }
        }catch(Exception e){
            MainLogger.logError(FileRWUtil.class.getName(), e);
            return "";
        }finally{
            if(br != null)
                try {
                br.close();
            } catch (IOException ex) {
                MainLogger.logError(FileRWUtil.class.getName(), ex);
            }
        }
        return buffer.toString();
    }

    public static void writeAsText(File file, String text){
        
    }
}
