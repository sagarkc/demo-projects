/**
 * 
 */
package com.xchanging.etl.mgr.desk.util;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public final class CollectionUtils {

	/**
	 * Checks whether the COLLECTION is not NULL and has at least one element.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param collection
	 *            the collection
	 * @return true, if successful
	 */
	public static <T> boolean hasElements(Collection<T> collection) {
		return (null != collection && collection.size() > 0);
	}

	/**
	 * Checks whether the MAP is not NULL and has at least one element.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param <V>
	 *            the value type
	 * @param map
	 *            the map
	 * @return true, if successful
	 */
	public static <T, V> boolean hasElements(Map<T, V> map) {
		return (null != map && map.size() > 0);
	}

	/**
	 * This API creates a list of list from a given list. This uses the
	 * maxSplitSize to make sublist from the given list and add the list to the
	 * output list.
	 * 
	 * @param <T>
	 *            The type of the Object the list can contain
	 * @param fromList
	 *            The input list which needs to split and create new List of
	 *            list
	 * @param maxSplitSize
	 *            maximum size of each split
	 * @return List of List<code>&lt;T&gt;</code>
	 * @author sabuj.das
	 */
	public static <T> List<List<T>> split(List<T> fromList, int maxSplitSize) {
		List<List<T>> toList = new ArrayList<List<T>>();
		if (null == fromList || fromList.size() == 0)
			return toList;
		int n = fromList.size(); // total size of the list
		// split the list by the maxSplitSize
		for (int i = 0; i < n; i += maxSplitSize) {
			if ((i + maxSplitSize) > n) {
				toList.add(fromList.subList(i, n));
			} else {
				toList.add(fromList.subList(i, (i + maxSplitSize)));
			}
		}
		return toList;
	}

	/**
	 * This API creates a list of list from a given array. This uses the
	 * maxSplitSize to make sub-array from the given array and add the array to
	 * the output list.
	 * 
	 * @param <T>
	 *            The type of the Object the array can contain
	 * @param fromArray
	 *            :<code>[]&lt;T&gt;</code> The input array which needs to split
	 *            and create new List of list
	 * @param maxSplitSize
	 *            maximum size of each split
	 * @return List<code>&lt;T[]&gt;</code>
	 * @author sabuj.das
	 */
	public static <T> List<T[]> split(T[] fromArray, int maxSplitSize) {
		List<T[]> toList = new ArrayList<T[]>();

		if (null == fromArray || fromArray.length == 0)
			return toList;

		List<T> fromList = Arrays.asList(fromArray);
		int n = fromList.size(); // total size of the list
		// split the list by the maxSplitSize
		List<T> subList = null;
		for (int i = 0; i < n; i += maxSplitSize) {
			if ((i + maxSplitSize) > n) {
				subList = fromList.subList(i, n);
			} else {
				subList = fromList.subList(i, (i + maxSplitSize));
			}
			if (null != subList) {
				T[] newArray = (T[]) Array.newInstance(fromArray[0].getClass(),
						subList.size());
				toList.add(subList.toArray(newArray));
			}
		}
		return toList;
	}

	/**
	 * Splits a collection of any object into a map of the provided field value
	 * and collection.
	 * 
	 * @param <T>
	 * @param items
	 * @param fieldName
	 *            (Must have to have get-set methods in T)
	 * @return
	 */
	public static <T, V extends Comparable<V>> Map<V, List<T>> split(
			List<T> items, String fieldName) {
		Map<V, List<T>> splittedMap = new HashMap<V, List<T>>();

		if (null != items) {
			FieldSpecificComparator<T, V> comparator = new FieldSpecificComparator<T, V>(
					fieldName);
			Collections.sort(items, comparator);
			for (int i = 0; i < items.size(); i++) {
				T item = items.get(i);
				Class<T> clazz = (Class<T>) item.getClass();
				Method[] methods = clazz.getDeclaredMethods();
				V value = null;
				String getterMethodName = "get" + fieldName;
				for (Method method : methods) {
					if (method.getName().startsWith("get")) {
						if (method.getName().equalsIgnoreCase(getterMethodName)) {
							try {
								value = (V) method.invoke(item, null);
							} catch (Exception e) {
								e.printStackTrace();
							}
							break;
						}
					}
				}
				if (splittedMap.get(value) == null) {
					List<T> list = new ArrayList<T>();
					splittedMap.put(value, list);
				}

				List<T> list = splittedMap.get(value);
				list.add(items.remove(i));
				i--;
			}
		}

		return splittedMap;
	}

	/**
	 * 
	 * @param <T>
	 * @param a
	 *            variable number of arguments of type <T>
	 * @return
	 */
	public static <T extends Comparable<T>> T lowestValue(T... a) {
		if (null == a || a.length <= 0)
			return null;
		List<T> list = new ArrayList<T>();
		for (T t : a) {
			if (null != t) {
				list.add(t);
			}
		}
		Collections.sort(list);
		return list.get(0);
	}

	/**
	 * This API is to get the lowest value from a List. The input objects need
	 * to implement Comparable.
	 * 
	 * 
	 * @param <T>
	 *            The type of the object the input list can contain and also the
	 *            return type of the method
	 * @param list
	 * @return
	 * @author sabuj.das
	 */
	public static <T extends Comparable<T>> T lowestValue(List<T> list) {
		if (list == null || list.isEmpty()) {
			return null;
		}
		Collections.sort(list);
		return list.get(0);
	}

	/**
	 * 
	 * @param <T>
	 * @param list
	 * @param comparator
	 * @return
	 * @author sabuj.das
	 */
	public static <T> T lowestValue(List<T> list, Comparator<T> comparator) {
		if (list == null || list.isEmpty()) {
			return null;
		}
		Collections.sort(list, comparator);
		return list.get(0);
	}

	/**
	 * 
	 * @param <T>
	 * @param a
	 * @return
	 */
	public static <T extends Comparable<T>> T getLowerMiddleValue(T... a) {
		if (null == a || a.length <= 0)
			return null;
		List<T> list = new ArrayList<T>();
		for (T t : a) {
			if (null != t) {
				list.add(t);
			}
		}
		return getLowerMiddleValue(list);
	}

	/**
	 * 
	 * @param <T>
	 * @param list
	 * @return
	 */
	public static <T extends Comparable<T>> T getLowerMiddleValue(List<T> list) {
		if (null == list || list.isEmpty())
			return null;
		Collections.sort(list);
		return list.get((list.size() - 1) / 2);
	}

	/**
	 * 
	 * @param <T>
	 * @param comparator
	 * @param a
	 * @return
	 */
	public static <T> T getLowerMiddleValue(Comparator<T> comparator, T... a) {
		if (null == a || a.length <= 0)
			return null;
		Arrays.sort(a, comparator);
		return a[(a.length - 1) / 2];
	}

	/**
	 * 
	 * @param <T>
	 * @param list
	 * @param comparator
	 * @return
	 */
	public static <T> T getLowerMiddleValue(List<T> list,
			Comparator<T> comparator) {
		if (null == list || list.isEmpty())
			return null;
		Collections.sort(list, comparator);
		return list.get((list.size() - 1) / 2);
	}

	/**
	 * 
	 * @param <T>
	 * @param list
	 */
	public static <T> void filterNulls(List<T> list) {
		if (null == list || list.isEmpty())
			return;
		for (int i = 0; i < list.size(); i++) {
			T current = list.get(i);
			if (null == current) {
				list.remove(i);
			}
		}
	}

	/**
	 * Find out whether all the elements in a List are equal.
	 * 
	 * @param <T>
	 * @param list
	 * @return
	 */
	public static <T> boolean allEquals(List<T> list) {
		if (list == null || list.size() <= 0)
			return false;

		for (int i = 0; i < list.size() - 1; i++) {
			T t_i = list.get(i);
			T t_j = list.get(i + 1);
			if (!t_i.equals(t_j))
				return false;
		}

		return true;
	}

	/**
	 * 
	 * @param item
	 * @param items
	 * @return
	 */
	public static <T> boolean contains(T item, T... items) {
		if (null == item || null == items) {
			return false;
		}
		if (items.length <= 0) {
			return false;
		}
		return Arrays.asList(items).contains(item);
	}

	/**
	 * 
	 * @param items
	 * @return
	 */
	public static <T> T[] toArray(List<T> items) {
		if (null == items) {
			return null;
		}
		if (items.size() <= 0) {
			return null;
		}
		T[] newArray = (T[]) Array.newInstance(items.get(0).getClass(),
				items.size());
		return items.toArray(newArray);
	}

	/**
	 * 
	 * @param <T>
	 * @param items
	 * @param offset
	 *            - 0 based
	 * @param length
	 *            - 1 based
	 * @return
	 * @throws UtilityException
	 */
	public static <T> T[] slice(T[] items, int offset, int length)
			throws Exception {
		if (null == items || items.length <= 0) {
			return null;
		}
		if (items.length < length) {
			throw new Exception("Array index out of bound : " + length);
		}
		if ((offset + length) > items.length) {
			throw new Exception("Array index out of bound : "
					+ (offset + length));
		}

		return Arrays.<T> copyOfRange(items, offset, (offset + (length - 1)));
	}

	/**
	 * 
	 * @param bytes
	 * @param offset
	 * @param length
	 * @return
	 * @throws UtilityException
	 */
	public static byte[] sliceBytes(byte[] bytes, int offset, int length)
			throws Exception {
		if (null == bytes || bytes.length <= 0) {
			return null;
		}
		if (bytes.length < length) {
			throw new Exception("Array index out of bound : " + length);
		}
		if ((offset + length) > bytes.length) {
			throw new Exception("Array index out of bound : "
					+ (offset + length));
		}
		if (offset < 0 || offset > bytes.length - 1) {
			throw new Exception("Array index out of bound : "
					+ (offset + length));
		}

		byte[] newArray = new byte[length];
		for (int i = offset, j = 0; j < length; i++, j++) {
			newArray[j] = bytes[i];
		}
		return newArray;
	}

	/**
	 * 
	 * @param array
	 * @param value
	 * @return
	 */
	public static <T> int getIndexOf(T[] array, T value) {
		if (array == null || value == null)
			return 0;
		for (int i = 0; i < array.length; i++) {
			if (value.equals(array[i])) {
				return i;
			}
		}
		return 0;
	}

	/**
	 * Removes the duplicates.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param items
	 *            the items
	 * @return the list
	 */
	public static <T> List<T> removeDuplicates(List<T> items) {
		Set<T> set = new LinkedHashSet<T>();
		set.addAll(items);
		return new ArrayList<T>(set);
	}

	/**
	 * Splits a collection of any object into a map of the provided field value
	 * and collection.
	 * 
	 * @param <T>
	 * @param items
	 * @param fieldName
	 *            (Must have to have get-set methods in T)
	 * @return
	 */
	public static <T, V extends Comparable<V>> Map<V, List<T>> splitByField(
			List<T> items, String fieldName) {
		Map<V, List<T>> splittedMap = new HashMap<V, List<T>>();

		if (null != items) {
			FieldSpecificComparator<T, V> comparator = new FieldSpecificComparator<T, V>(
					fieldName);
			Collections.sort(items, comparator);
			for (int i = 0; i < items.size(); i++) {
				T item = items.get(i);
				Class<T> clazz = (Class<T>) item.getClass();
				Method[] methods = clazz.getDeclaredMethods();
				V value = null;
				String getterMethodName = "get" + fieldName;
				for (Method method : methods) {
					if (method.getName().startsWith("get")) {
						if (method.getName().equalsIgnoreCase(getterMethodName)) {
							try {
								value = (V) method.invoke(item, null);
							} catch (Exception e) {
								e.printStackTrace();
							}
							break;
						}
					}
				}
				if (splittedMap.get(value) == null) {
					List<T> list = new ArrayList<T>();
					splittedMap.put(value, list);
				}

				List<T> list = splittedMap.get(value);
				list.add(items.remove(i));
				i--;
			}
		}

		return splittedMap;
	}

	/**
	 * Sort a collection, based on the field provided. Similar to 'ORDER BY' in
	 * SQL.
	 * 
	 * @param data
	 * @param fields
	 * @return
	 */
	public static <T> List<T> orderBy(List<T> data, String... fields)
			throws NoSuchMethodException, SecurityException {
		return orderBy(data, SortOrderEnum.ASC, fields);
	}

	/**
	 * Sort a collection, based on the field provided. Similar to 'ORDER BY' in
	 * SQL. <br/>
	 * Note: This comparator is not type-safe. <br/>
	 * Requirement: <code> V extends Comparable&lt;V&gt; -- where 'V' is a 
	 * data type of each field of fields[].</code>
	 * 
	 * @param data
	 * @param sortOrder
	 * @param fields
	 * @return
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> List<T> orderBy(List<T> data, SortOrderEnum sortOrder,
			String... fields) {
		if (!hasElements(data))
			return data;

		if (null == fields || fields.length <= 0)
			return data;

		if (fields.length == 1) {

			Comparator c = new FieldSpecificComparator(fields[0], sortOrder);
			Collections.sort(data, c);
			return data;
		}

		for (int i = fields.length - 1; i >= 0; i--) {
			Comparator c = new FieldSpecificComparator(fields[i], sortOrder);
			Collections.sort(data, c);
		}

		return data;
	}

	/**
	 * Swaps 2 items
	 * 
	 * @param data
	 * @param from
	 * @param to
	 */
	public static <T> void swapItems(final List<T> data, int from, int to){
		if(from == to)
			return;
		if (!hasElements(data))
			return ;
		if(from < 0 || from >= data.size() ){
			throw new IllegalArgumentException("'from' must be within 0 to n-1");
		}
		if(to < 0 || to >= data.size() ){
			throw new IllegalArgumentException("'to' must be within 0 to n-1");
		}
		
		T temp = data.get(from);
		data.set(from, data.get(to));
		data.set(to, temp);
	}
	
}
