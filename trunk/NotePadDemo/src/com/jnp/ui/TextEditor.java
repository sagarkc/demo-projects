/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jnp.ui;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
