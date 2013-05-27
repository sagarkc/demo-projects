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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Sabuj
 */
public class ColorPaletteManager implements ColorGrabListener, ColorDetectListener{
    
    private static ApplicationEventManager eventManager 
            = ApplicationEventManager.getInstance();
    private static ColorPaletteManager instance;
    private Map<String, List<ColorPanel>> colorPanelPaletteMap
            = new HashMap<String, List<ColorPanel>>();
    private Set<String> paletteNames = new LinkedHashSet<String>();
    private ColorPanel selectedPanel;
    
    private ColorPaletteManager() {
        eventManager.registerListener(ColorGrabEvent.class, this);
        eventManager.registerListener(ColorDetectEvent.class, this);
    }

    public static ColorPaletteManager getInstance() {
        if(null != instance)
            return instance;
        synchronized(ColorPaletteManager.class){
            if(null == instance){
                instance = new ColorPaletteManager();
            }
        }
        return instance;
    }

    public boolean isPaletteExists(String paletteName){
        return paletteNames.contains(paletteName);
    }
    
    public void addPalette(String paletteName){
        if(null != paletteName 
                && !"".equals(paletteName)
                && ! isPaletteExists(paletteName)){
            paletteNames.add(paletteName);
        }
    }
    
    public List<ColorPanel> getAllColorPanels(String paletteName){
        return colorPanelPaletteMap.get(paletteName);
    }
    
    public void addPanel(String paletteName, ColorPanel colorPanel){
        if(null == colorPanelPaletteMap.get(paletteName)){
            colorPanelPaletteMap.put(paletteName, new ArrayList<ColorPanel>());
        }
        colorPanelPaletteMap.get(paletteName).add(colorPanel);
    }
    
    public void selectPanel(String paletteName, ColorPanel colorPanel){
        final List<ColorPanel> colorPanels = colorPanelPaletteMap.get(paletteName);
        for (ColorPanel p : colorPanels) {
            p.setSelected(false);
            p.updateUI();
        }
        selectedPanel = colorPanel;
        selectedPanel.setSelected(true);
    }
    
    public void makeColorPanelEditable(String paletteName, ColorPanel colorPanel){
        final List<ColorPanel> colorPanels = colorPanelPaletteMap.get(paletteName);
    	for (ColorPanel p : colorPanels) {
            p.setSelected(false);
            p.updateUI();
        }
        selectedPanel = colorPanel;
        selectedPanel.setSelected(true);
        selectedPanel.setColorGrabbed(false);
    }
    
    public void makeSelectedColorPanelEditable(String paletteName){
    	final List<ColorPanel> colorPanels = colorPanelPaletteMap.get(paletteName);
        for (ColorPanel p : colorPanels) {
            if(p != selectedPanel)
            	p.setSelected(false);
            p.updateUI();
        }
        selectedPanel.setSelected(true);
        selectedPanel.setColorGrabbed(false);
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

    public void removeSelectedPanel(String paletteName) {
        final List<ColorPanel> colorPanels = colorPanelPaletteMap.get(paletteName);
        if(selectedPanel != null){
            for (ColorPanel p : colorPanels) {
                if(p.equals(selectedPanel)){
                    colorPanels.remove(p);
                    break;
                } 
            }
        }
        selectedPanel = null;
        
    }

    public ColorPanel getSelectPanel() {
        return selectedPanel;
    }
    
    
}
