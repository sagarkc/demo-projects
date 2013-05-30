/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.tools.colorhound.ui.ColorPaletteListModel
 * Date     : May 30, 2013
 */

package com.gs.tools.colorhound.ui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class ColorPaletteListModel implements ListModel{

    private final List<String> colorCodeList;

    public ColorPaletteListModel(List<String> colorCodeList) {
        if(null == colorCodeList){
            colorCodeList = new ArrayList<String>(0);
        }
        this.colorCodeList = colorCodeList;
    }
    
    
    
    @Override
    public int getSize() {
        return colorCodeList.size();
    }

    @Override
    public String getElementAt(int index) {
        return colorCodeList.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        
    }
    
}
