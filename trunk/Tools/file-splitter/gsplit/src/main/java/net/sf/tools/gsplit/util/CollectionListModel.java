
package net.sf.tools.gsplit.util;

import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class CollectionListModel<T> implements ListModel{
    
    private List<T> dataList;

    public CollectionListModel(List<T> dataList) {
        this.dataList = dataList;
    }

    
    
    @Override
    public int getSize() {
        return dataList.size();
    }

    @Override
    public Object getElementAt(int index) {
        return dataList.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        
    }
    
    
}
