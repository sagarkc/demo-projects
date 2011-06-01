/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.tools.common.ui;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;

/**
 *
 * @author 50120C1509
 */
public final class MapCompoboxModel<K, V> extends AbstractListModel implements MutableComboBoxModel, Serializable {

    private Map<K, V> dataMap;
    private Vector<K> keyCollection;
    private K selectedKey;
    private V selectedValue;

    public MapCompoboxModel() {
        dataMap = new LinkedHashMap<K, V>();
        keyCollection = new Vector<K>();
    }

    public MapCompoboxModel(final Collection<K> keys, final Map<K, V> dataMap) {
        if(null != dataMap && null != keys){
            this.keyCollection = new Vector<K>();
            this.keyCollection.ensureCapacity(keys.size());
            this.dataMap = new LinkedHashMap<K, V>(dataMap.size());
            for(K key : keys){
                this.keyCollection.add(key);
                this.dataMap.put(key, dataMap.get(key));
            }
        }
        if(getSize() > 0){
            selectedKey = this.keyCollection.get(0);
            selectedValue = this.dataMap.get(selectedKey);
        }
    }

    @Override
    public int getSize() {
        return dataMap.size();
    }

    @Override
    public Object getElementAt(int index) {
        if ( index >= 0 && index < keyCollection.size() )
            return keyCollection.elementAt(index);
        else
            return null;
    }

    public V getValueAt(int index) {
        if ( index >= 0 && index < keyCollection.size() )
            return dataMap.get(keyCollection.elementAt(index));
        else
            return null;
    }

    @Override
    public void addElement(Object obj) {
        keyCollection.addElement((K) obj);
        fireIntervalAdded(this,keyCollection.size()-1, keyCollection.size()-1);
        if ( keyCollection.size() == 1 && selectedKey == null && obj != null ) {
            setSelectedItem( obj );
        }
    }

    public void addValue(K key, V value){
        if(null != key){
            
        }
    }

    @Override
    public void removeElement(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void insertElementAt(Object obj, int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeElementAt(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if ((selectedKey != null && !selectedKey.equals( anItem )) ||
	    selectedKey == null && anItem != null) {
	    selectedKey = (K) anItem;
            selectedValue = dataMap.get(selectedKey);
	    fireContentsChanged(this, -1, -1);
        }
    }

    @Override
    public Object getSelectedItem() {
        return selectedKey;
    }

    public Object getSelectedItemValue() {
        return selectedValue;
    }
}
