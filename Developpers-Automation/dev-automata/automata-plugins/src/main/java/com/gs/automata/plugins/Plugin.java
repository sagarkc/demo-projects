/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.automata.plugins;


/**
 *
 * @author sabuj.das
 */
public interface Plugin extends Installable{
    
    public void preInit(String pluginName, int ... eventCodes);
    
    public void init(String pluginName, int ... eventCodes);
    
    public void postInit(String pluginName, int ... eventCodes);
    
    public void start(String pluginName, int ... eventCodes);
    
    public void stop(String pluginName, int ... eventCodes);
    
    public void dispose(String pluginName, int ... eventCodes);
    
    public void load(String pluginName);
    
}
