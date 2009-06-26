/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jnp.ui;

import com.jnp.JnpConstants;
import java.awt.Font;
import javax.swing.JTextArea;

/**
 *
 * @author Green Moon
 */
public class TextEditor {

    private JTextArea textArea;
    private boolean isNewFile;
    private String fileName;
    private boolean isEdited;
    private Font currentFont;
    private boolean insertEnabled = true;
    private boolean numberLocked = false;
    private String fileTitle, fileExtension;
    private int newFileCount = -1;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        if(null == fileName || fileName.equals("")){
            fileName = JnpConstants.NEW_FILE_NAME + newFileCount;
            this.fileTitle = fileName;
            this.fileExtension = "";
        }else{
            this.fileTitle = fileName.substring(
                    fileName.lastIndexOf(System.getProperty("file.separator"))+1);
            this.fileExtension = fileName.substring(
                    fileName.lastIndexOf(".")+1);
        }
        this.fileName = fileName;

    }

    public int getNewFileCount() {
        return newFileCount;
    }

    public void setNewFileCount(int currentFileCount) {
        this.newFileCount = currentFileCount;
    }

    
    public String getFileExtension() {
        return fileExtension;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    
    public boolean isIsEdited() {
        return isEdited;
    }

    public void setIsEdited(boolean isEdited) {
        this.isEdited = isEdited;
    }

    public boolean isIsNewFile() {
        return isNewFile;
    }

    public void setIsNewFile(boolean isNewFile) {
        this.isNewFile = isNewFile;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    public Font getCurrentFont() {
        return currentFont;
    }

    public void setCurrentFont(Font currentFont) {
        this.currentFont = currentFont;
    }

    public boolean isInsertEnabled() {
        return insertEnabled;
    }

    public void setInsertEnabled(boolean insertEnabled) {
        this.insertEnabled = insertEnabled;
    }

    public boolean isNumberLocked() {
        return numberLocked;
    }

    public void setNumberLocked(boolean numberLocked) {
        this.numberLocked = numberLocked;
    }

    
}
