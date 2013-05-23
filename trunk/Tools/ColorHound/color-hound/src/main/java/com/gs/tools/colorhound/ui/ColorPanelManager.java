/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.tools.colorhound.ui;

import com.gs.tools.colorhound.event.ApplicationEventManager;
import com.gs.tools.colorhound.event.ColorDetectEvent;
import com.gs.tools.colorhound.event.ColorDetectListener;
import com.gs.tools.colorhound.event.ColorGrabEvent;
import com.gs.tools.colorhound.event.ColorGrabListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sabuj
 */
public class ColorPanelManager implements ColorGrabListener, ColorDetectListener{
    
    private static ApplicationEventManager eventManager 
            = ApplicationEventManager.getInstance();
    private static ColorPanelManager instance;
    private List<ColorPanel> colorPanels = new ArrayList<ColorPanel>();
    private Map<ColorPanel, Integer> indexMap = new HashMap<ColorPanel, Integer>();
    private ColorPanel selectedPanel;
    
    private ColorPanelManager() {
        eventManager.registerListener(ColorGrabEvent.class, this);
        eventManager.registerListener(ColorDetectEvent.class, this);
    }

    public static ColorPanelManager getInstance() {
        if(null != instance)
            return instance;
        synchronized(ColorPanelManager.class){
            if(null == instance){
                instance = new ColorPanelManager();
            }
        }
        return instance;
    }

    public void addPanel(ColorPanel colorPanel){
        colorPanels.add(colorPanel);
        indexMap.put(colorPanel, colorPanels.size()-1);
    }
    
    public void selectPanel(ColorPanel colorPanel){
        for (ColorPanel p : colorPanels) {
            p.setSelected(false);
            p.updateUI();
        }
        
        selectedPanel = colorPanel;
        selectedPanel.setSelected(true);
        
    }
    
    public void colorGrabbed(ColorGrabEvent event) {
        if(null != event && null != event.getNewValue()
                && null != selectedPanel && !selectedPanel.isColorGrabbed()){
            selectedPanel.setSelectedColor(event.getNewValue());
            selectedPanel.setColorGrabbed(true);
            selectedPanel.repaint();
            selectedPanel.updateUI();
            selectedPanel.setColorGrabbed(true);
            selectedPanel.getParentPanel().updateUI();
        }
    }

    public void colorDetected(ColorDetectEvent event) {
        if(null != event && null != event.getNewValue()
                && null != selectedPanel && !selectedPanel.isColorGrabbed()){
            selectedPanel.setSelectedColor(event.getNewValue());
            selectedPanel.repaint();
            selectedPanel.updateUI();
            selectedPanel.getParentPanel().updateUI();
        }
    }
    
    
}
