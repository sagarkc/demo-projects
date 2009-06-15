/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.jnp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Green Moon
 */
public class FileRWUtil {

    public static final String CONTEXT_FILE_NAME = "gui_context.ctx";

    public static void writeContext(NotePadContext context){
        ObjectOutputStream oos = null;
        try{
            oos = new ObjectOutputStream(
                    new FileOutputStream(new File(CONTEXT_FILE_NAME)));

            oos.writeObject(context);
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(oos != null){
                try {
                    oos.close();
                } catch (IOException ex) {
                    Logger.getLogger(FileRWUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static NotePadContext readContext(){
        NotePadContext ctx = null;
        File ctxFile = new File(CONTEXT_FILE_NAME);
        if(!ctxFile.exists())
            return null;
        ObjectInputStream ois = null;
        try{
            ois = new ObjectInputStream(
                    new FileInputStream(new File(CONTEXT_FILE_NAME)));
            ctx = (NotePadContext) ois.readObject();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(ois != null){
                try {
                    ois.close();
                } catch (IOException ex) {
                    Logger.getLogger(FileRWUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return ctx;
    }
    
}
