package com.gs.demo.hr.jdbc;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.RowMapper;



/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 * @param <T>
 */
public class ReflectionBasedRowMapper<T> implements RowMapper<T>{
	
	private Class<T> dtoClass;
	private Map<String, String> columnPropertyMap;
	private List<Method> getterMethodList;
	
	public ReflectionBasedRowMapper(Class<T> dataClazz) {
		this.dtoClass = dataClazz;
		this.columnPropertyMap = new HashMap<String, String>();
		this.getterMethodList = new ArrayList<Method>();
		
		Method[] accessibleMethods = null;
		try {
			accessibleMethods = dataClazz.getMethods();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		for (Method method : accessibleMethods) {
			if (method.getName().startsWith("get")) {
				ResultSetColumn resultSetCol = method.getAnnotation(ResultSetColumn.class);
				if (resultSetCol != null) {
					getterMethodList.add(method);
					columnPropertyMap.put(resultSetCol.mappedColumnName(), resultSetCol.propertyName());
				}
			}
		}
	}
	
	public T mapRow(ResultSet resultSet, int arg1) throws SQLException {
		T result;
		try {
			result = dtoClass.newInstance();
			
			ResultSetMetaData rsMetaData = resultSet.getMetaData();
			int noOfColumns = rsMetaData.getColumnCount();
			for (int j = 1; j <= noOfColumns; j++) {
				String columnName = rsMetaData.getColumnLabel(j);
				if(!columnPropertyMap.containsKey(columnName)){
					// ignore the current column. read other columns
					continue;
				}
				PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(dtoClass, columnName);
				if ( pd == null ) {
					columnName = rsMetaData.getColumnName(j);
					String propertyName = columnPropertyMap.get(columnName);
					pd = BeanUtils.getPropertyDescriptor(dtoClass,  propertyName);
				}
				Object value = resultSet.getObject(j);
				if( value instanceof java.util.Date && 
					pd.getPropertyType().getName().equalsIgnoreCase("java.util.Date")){
					pd.getWriteMethod().invoke(result, value);
				}
				else if(value instanceof java.util.Date && 
					pd.getPropertyType().getName().equalsIgnoreCase("java.sql.Timestamp")){
					pd.getWriteMethod().invoke(result, value);
				}else if(value instanceof java.util.Date) {
					java.util.Date date = (Date) value;
					SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
					pd.getWriteMethod().invoke(result, dateFormat.format(date));
				}else if(value instanceof java.lang.String && 
						pd.getPropertyType().getName().equalsIgnoreCase("java.lang.Long")){
					java.lang.String stringValue = (java.lang.String) value;					
					pd.getWriteMethod().invoke(result, Long.valueOf(stringValue));
				}else if(value instanceof java.math.BigDecimal && 
						pd.getPropertyType().getName().equalsIgnoreCase("java.lang.Long")){
					java.math.BigDecimal bigDecimalValue = (java.math.BigDecimal) value;	
					
					pd.getWriteMethod().invoke(result, Long.valueOf(String.valueOf(bigDecimalValue)));
				}else if(value instanceof java.sql.Clob && 
						pd.getPropertyType().getName().equalsIgnoreCase("java.lang.String")){
					java.sql.Clob clobValue = (java.sql.Clob) value;					
					long pos = 1;
					String strValue = clobValue.getSubString(pos, (int)clobValue.length());
					pd.getWriteMethod().invoke(result, strValue);
				}
				else {
					pd.getWriteMethod().invoke(result, value);
				}
				
			}
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} 
		return result;
	}

	
	
}
