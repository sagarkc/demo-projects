/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.jnp.ui;

import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoableEdit;

/**
 *
 * @author sabuj.das
 */
public class EditorTextArea extends JTextArea implements UndoableEdit, UndoableEditListener{

    public void undo() throws CannotUndoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean canUndo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void redo() throws CannotRedoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean canRedo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void die() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean addEdit(UndoableEdit anEdit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean replaceEdit(UndoableEdit anEdit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isSignificant() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getPresentationName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getUndoPresentationName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getRedoPresentationName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void undoableEditHappened(UndoableEditEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
