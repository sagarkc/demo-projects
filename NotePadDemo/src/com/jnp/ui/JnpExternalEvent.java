/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jnp.ui;

import java.awt.Event;

/**
 *
 * @author sabuj.das
 */
public class JnpExternalEvent extends Event{

    public static final String OPEN_FILE_EVENT = "OPEN_FILE_EVENT";

    private String type;

    public JnpExternalEvent(Object target, int id, Object arg, String type) {
        super(target, id, arg);
        setType(type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



}
