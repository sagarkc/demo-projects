package com.mercuria.etl.mgr.web.client.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GWTCollectionGridModel<T extends GWTCollectionData> {

	private final List<T> dataList;
	
	
	
	private List<String> columnNameList = new ArrayList<String>(100);
	private Map<String, String> columnFieldNameMap = new LinkedHashMap<String, String>(100);
	private Map<String, Integer> columnWidthMap = new LinkedHashMap<String, Integer>(100);
	
	private int columnCount;
	private int rowCount;
	private String className;
	
	public GWTCollectionGridModel(List<T> data, String className) {
		this.dataList = data;
		this.className = className;
		prepareModel();
	}
	
	private void prepareModel(){
		rowCount = dataList.size();
		Method[] ms = null;
		try {
			Class<?> clazz = Class.forName(getClassName());
			ms = clazz.getMethods();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ms == null){
			columnCount = 0;
			return;
		}
		for (Method method : ms) {
			if(method.getName().startsWith("get") ){
				GWTGridColumnHeader ch = method.getAnnotation(GWTGridColumnHeader.class);
				if(ch != null && ch.visible()){
					columnNameList.add(ch.title());
					columnFieldNameMap.put(ch.title(), ch.fieldName());
					columnWidthMap.put(ch.title(), ch.wigth());
				}
			}
		}
		columnCount = columnNameList.size();
		
	}
	

	
	

	
	public Class<?> getColumnClass(int columnIndex) {
		Method[] ms = null;
		try {
			Class<?> clazz = Class.forName(getClassName());
			ms = clazz.getMethods();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ms == null)
			return String.class;
		for (Method method : ms) {
			if(method.getName().startsWith("get") && method.isAnnotationPresent(GWTGridColumnHeader.class)){
				GWTGridColumnHeader ch = method.getAnnotation(GWTGridColumnHeader.class);
				if(ch != null){
					if(columnIndex == ch.index())
						return method.getReturnType();
				}
			}
		}
		return String.class;
	}

	
	public int getColumnCount() {
		return columnCount;
	}

	
	public String getColumnName(int columnIndex) {
		
		Method[] ms = null;
		try {
			Class<?> clazz = Class.forName(getClassName());
			ms = clazz.getMethods();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ms == null)
			return "";
		for (Method method : ms) {
			if(method.getName().startsWith("get") && method.isAnnotationPresent(GWTGridColumnHeader.class)){
				GWTGridColumnHeader ch = method.getAnnotation(GWTGridColumnHeader.class);
				if(ch != null){
					if(columnIndex == ch.index())
						return ch.title();
				}
			}
		}
		return "";
	}

	public int getRowCount() {
		return rowCount;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		
		T data = dataList.get(rowIndex);
		Method[] ms = data.getClass().getMethods();
		for (Method method : ms) {
			if(method.getName().startsWith("get") && method.isAnnotationPresent(GWTGridColumnHeader.class)){
				GWTGridColumnHeader ch = method.getAnnotation(GWTGridColumnHeader.class);
				if(ch != null){
					if(columnIndex == ch.index())
						try {
							return method.invoke(data, null);
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

	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	

	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

	}
	
	

	public Map<String, Integer> getColumnWidthMap() {
		return columnWidthMap;
	}

	public Map<String, String> getColumnFieldNameMap() {
		return columnFieldNameMap;
	}

	public List<T> getDataList() {
		return dataList;
	}

	
	public List<String> getColumnNameList() {
		return columnNameList;
	}

	public void setColumnNameList(List<String> columnNameList) {
		this.columnNameList = columnNameList;
	}

	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	
}
