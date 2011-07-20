/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.automata.plugins;

/**
 *
 * @author sabuj.das
 */
public interface Installable {
    
    public void install(String fileName);
    
    public void unInstall(String pluginName);
    
    public void update(String pluginName, String fileName);
    
}
