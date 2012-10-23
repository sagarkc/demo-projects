/**
 * 
 */
package net.sf.tools.gsplit.util;

import java.lang.reflect.Method;
import java.util.Comparator;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public class FieldSpecificComparator<T, V extends Comparable<V>> implements
		Comparator<T> {

	private String fieldName;
	private SortOrderEnum sortOrder;

	public FieldSpecificComparator(String fieldName) {
		this(fieldName, SortOrderEnum.ASC);
	}

	public FieldSpecificComparator(String fieldName, SortOrderEnum sortOrderEnum) {
		this.fieldName = fieldName;
		this.sortOrder = sortOrderEnum;
	}

	@SuppressWarnings("unchecked")
	public int compare(T o1, T o2) {
		if (o1 == null || o2 == null) {
			return 0;
		}
		Class<T> clazz = (Class<T>) o1.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		V v1 = null, v2 = null;
		String getterMethodName = "get" + getFieldName();
		for (Method method : methods) {
			if (method.getName().startsWith("get")) {
				if (method.getName().equalsIgnoreCase(getterMethodName)) {
					try {
						v1 = (V) method.invoke(o1, null);
						v2 = (V) method.invoke(o2, null);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}

		if (v1 != null && v2 != null) {
			if (SortOrderEnum.DESC.equals(sortOrder))
				return v2.compareTo(v1);
			else
				return v1.compareTo(v2);
		}
		return 0;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public SortOrderEnum getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrderEnum sortOrder) {
		this.sortOrder = sortOrder;
	}

}