/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.automata.plugins;

/**
 *
 * @author sabuj.das    
 */
public abstract class AbstractPlugin implements Plugin{

    private String pluginName;
    private String fileName;
    
    public AbstractPlugin(String pluginName, String fileName) {
        this.pluginName = pluginName;
        this.fileName = fileName;
    }
    
}
