package com.gmail.sabuj.career.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.gmail.sabuj.career.common.AppSettings;

/**
 * The Class CsvWriterDataModel.
 *
 * @param <T> the generic type
 * @author <a href="mailo:career.sabuj@gmail.com">Sabuj Das</a>
 */
public class CsvWriterDataModel<T> {

	private static final AppSettings settings = AppSettings.getInstance();
	private static final Logger logger = Logger.getLogger(CsvWriterDataModel.class);
	
	/** The data list. */
	private List<T> dataList = new ArrayList<T>();
	
	/** The column name list. */
	private List<String> columnNameList = new ArrayList<String>(100);

	/** The column count. */
	private int columnCount;
	
	/** The row count. */
	private int rowCount;
	
	/** The data clazz. */
	private Class<?> dataClazz;
	
	/** The getter method list. */
	private List<Method> getterMethodList;

	/**
	 * Instantiates a new csv writer data model.
	 *
	 * @param data the data
	 */
	public CsvWriterDataModel(List<T> data) {
		this.dataList = data;
		this.getterMethodList = new ArrayList<Method>();
		prepareModel();
	}

	/**
	 * Prepare model.
	 */
	private void prepareModel() {
		if (!CollectionUtil.hasElements(dataList)) {
			return;
		}
		dataClazz = dataList.get(0).getClass();
		rowCount = dataList.size();

		Method[] accessibleMethods = null;
		try {
			accessibleMethods = dataClazz.getMethods();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		if (accessibleMethods == null) {
			columnCount = 0;
			return;
		}
		Map<Integer, String> columnNamesMap = new HashMap<Integer, String>();
		for (Method method : accessibleMethods) {
			if (method.getName().startsWith("get")) {
				CsvColumnHeader ch = method
						.getAnnotation(CsvColumnHeader.class);
				if (ch != null) {
					getterMethodList.add(method);
					if(null != settings.getCsvColumnHeader(ch.index())){
						columnNamesMap.put(ch.index(), settings.getCsvColumnHeader(ch.index()));
					} else {
						columnNamesMap.put(ch.index(), ch.title());
					}
				}
			}
		}
		List<Integer> indexList = new ArrayList<Integer>();
		indexList.addAll(columnNamesMap.keySet());
		Collections.sort(indexList);
		for (Integer index : indexList) {
			columnNameList.add(columnNamesMap.get(index));
		}
		columnCount = columnNameList.size();
	}

	/**
	 * Gets the column class.
	 *
	 * @param columnIndex the column index
	 * @return the column class
	 */
	public Class<?> getColumnClass(int columnIndex) {
		if (getterMethodList == null)
			return String.class;
		for (Method method : getterMethodList) {
			if (method.getName().startsWith("get")
					&& method.isAnnotationPresent(CsvColumnHeader.class)) {
				CsvColumnHeader ch = method
						.getAnnotation(CsvColumnHeader.class);
				if (ch != null) {
					if (columnIndex == ch.index())
						return method.getReturnType();
				}
			}
		}
		return String.class;
	}

	/**
	 * Gets the column count.
	 *
	 * @return the column count
	 */
	public int getColumnCount() {
		return columnCount;
	}

	/**
	 * Gets the column name.
	 *
	 * @param columnIndex the column index
	 * @return the column name
	 */
	public String getColumnName(int columnIndex) {
		if (getterMethodList == null)
			return "";
		for (Method method : getterMethodList) {
			if (method.getName().startsWith("get")
					&& method.isAnnotationPresent(CsvColumnHeader.class)) {
				CsvColumnHeader ch = method
						.getAnnotation(CsvColumnHeader.class);
				if (ch != null) {
					if (columnIndex == ch.index())
						return ch.title();
				}
			}
		}
		return "";
	}

	/**
	 * Gets the row count.
	 *
	 * @return the row count
	 */
	public int getRowCount() {
		return rowCount;
	}

	/**
	 * Gets the value at.
	 *
	 * @param rowIndex the row index
	 * @param columnIndex the column index
	 * @return the value at
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		T data = dataList.get(rowIndex);
		for (Method method : getterMethodList) {
			if (method.getName().startsWith("get")
					&& method.isAnnotationPresent(CsvColumnHeader.class)) {
				CsvColumnHeader ch = method
						.getAnnotation(CsvColumnHeader.class);
				if (ch != null) {
					if (columnIndex == ch.index())
						try {
							Object value = method.invoke(data, null);
							if(null != value){
								return getFormattedValue(value, ch);
							}
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
				}
			}
		}
		return "";
	}

	private Object getFormattedValue(Object value, CsvColumnHeader ch) {
		if(value instanceof Number 
				&& ch.useNumberFormat()){
			String format = ch.numberFormat();
			if(StringUtil.hasValidContent(format)){
				DecimalFormat df = new DecimalFormat();
				return df.format(value);
			}
		}
		if(value instanceof Date 
				&& ch.useDateFormat()){
			String format = ch.dateFormat();
			if(AppSettings.getInstance().getDateFormat()!= null){
				return AppSettings.getInstance().getDateFormat().format(value);
			}
			if(StringUtil.hasValidContent(format)){
				SimpleDateFormat df = new SimpleDateFormat(format);
				return df.format(value);
			}
		}
		return value;
	}

	/**
	 * Gets the data list.
	 *
	 * @return the data list
	 */
	public List<T> getDataList() {
		return dataList;
	}

	/**
	 * Sets the data list.
	 *
	 * @param dataList the new data list
	 */
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	/**
	 * Gets the column name list.
	 *
	 * @return the column name list
	 */
	public List<String> getColumnNameList() {
		return columnNameList;
	}

	/**
	 * Sets the column name list.
	 *
	 * @param columnNameList the new column name list
	 */
	public void setColumnNameList(List<String> columnNameList) {
		this.columnNameList = columnNameList;
	}

	/**
	 * Sets the column count.
	 *
	 * @param columnCount the new column count
	 */
	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}

	/**
	 * Sets the row count.
	 *
	 * @param rowCount the new row count
	 */
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

}
