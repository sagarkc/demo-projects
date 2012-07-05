/**
 * 
 */
package com.gmail.sabuj.career.common.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="mailo:career.sabuj@gmail.com">Sabuj Das</a>    
 *
 */
public class CollectionUtil
{
	

    
    /**
     * Checks whether the COLLECTION is not NULL and has at least one element.
     *
     * @param <T> the generic type
     * @param collection the collection
     * @return true, if successful
     */
    public static <T> boolean hasElements(Collection<T> collection){
    	return (null != collection && collection.size() > 0);
    }
    
    /**
     * Checks whether the MAP is not NULL and has at least one element.
     *
     * @param <T> the generic type
     * @param <V> the value type
     * @param map the map
     * @return true, if successful
     */
    public static <T, V> boolean hasElements(Map<T, V> map){
    	return (null != map && map.size() > 0);
    }

    
	/**
	 * Checks for elements in array.
	 *
	 * @param <T> the generic type
	 * @param items the items
	 * @return true, if successful
	 */
	public static <T> boolean hasElements(T[] items) {
		if(null != items && items.length > 0)
			return true;
		return false;
	}

    /**
     * Contains.
     *
     * @param <T> the generic type
     * @param items the items
     * @param item the item
     * @return true, if successful
     */
    public static <T> boolean contains(T[] items, T item){
    	if(!hasElements(items) || item == null){
    		return false;
    	}
    	for (T t : items) {
			if(item.equals(t)){
				return true;
			}
		}
    	return false;
    }
    
	/**
	 * Removes the duplicates.
	 *
	 * @param <T> the generic type
	 * @param items the items
	 * @return the list
	 */
	public static <T> List<T> removeDuplicates(List<T> items) {
		Set<T> set = new LinkedHashSet<T>();
		set.addAll(items);
		return new ArrayList<T>(set);
	}
	
	
	/**
	 * 
	 * @param items
	 * @return
	 */
	public static <T> T[] toArray(List<T> items){
		if(null == items){
			return null;
		}
		if(items.size() <= 0){
			return null;
		}
		T[] newArray = (T[]) Array.newInstance(items.get(0).getClass(), items.size());
		return items.toArray(newArray);
	}
	

}


