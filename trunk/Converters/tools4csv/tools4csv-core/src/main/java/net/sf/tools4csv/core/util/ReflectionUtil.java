package net.sf.tools4csv.core.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import net.sf.tools4csv.core.IllegalConverterException;

import org.apache.commons.lang.StringUtils;

public class ReflectionUtil {

	public static <T> boolean isNumberType(Class<T> clazz) {
		if (clazz == null)
			return false;
		if (clazz.getCanonicalName().equals(Integer.class.getCanonicalName())
				|| clazz.getCanonicalName().equals(
						Float.class.getCanonicalName())
				|| clazz.getCanonicalName().equals(
						Double.class.getCanonicalName())
				|| clazz.getCanonicalName().equals(
						Long.class.getCanonicalName()))
			return true;
		return false;
	}
	
	public static <T> boolean isTextType(Class<T> clazz) {
		if (clazz == null)
			return false;
		return (clazz.getCanonicalName().equals(String.class.getCanonicalName()));
	}
	
	public static <T> boolean isDateType(Class<T> clazz) {
		if (clazz == null)
			return false;
		return (
				   java.util.Date.class.getCanonicalName().equals(clazz.getCanonicalName())
				|| java.util.Calendar.class.getCanonicalName().equals(clazz.getCanonicalName())
				|| java.sql.Date.class.getCanonicalName().equals(clazz.getCanonicalName())
				|| java.sql.Timestamp.class.getCanonicalName().equals(clazz.getCanonicalName())
			);
	}
	
	public static boolean isDateType(String formatType) {
		return "date".equals(formatType);
	}

	public static boolean isTextType(String formatType) {
		return "string".equals(formatType);
	}

	public static boolean isNumberType(String formatType) {
		return ("number".equals(formatType)
				|| "int".equals(formatType)
				|| "integer".equals(formatType)
				|| "float".equals(formatType)
				|| "long".equals(formatType)
				|| "double".equals(formatType)
				|| "bigint".equals(formatType)
				|| "bigdecimal".equals(formatType));
	}

	public static Object getObject(String type, String value) throws ParseException {
		if(isDateType(type)){
			return new SimpleDateFormat().parseObject(value);
		}
		if(isNumberType(type)){
			if("int".equals(type) || "integer".equals(type))
				return Integer.valueOf(value);
			if("long".equals(type))
				return Long.valueOf(value);
			if("float".equals(type)) 
				return Float.valueOf(value);
			if("double".equals(type)) 
				return Double.valueOf(value);
			if("bigint".equals(type) || "biginteger".equals(type)) 
				return new BigInteger(value);
			if("bigdecimal".equals(type)) 
				return new BigDecimal(value);
			return Long.valueOf(value);
		}
		return (Object)value;
	}
	
	public static Object getObject(String type, String value, String sourceFormat) throws ParseException {
		if(isDateType(type)){
			return new SimpleDateFormat(sourceFormat).parseObject(value);
		}
		return getObject(type, value);
	}

	
	public static Object createObject(String className) {
		Class clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new IllegalConverterException(e.getMessage(),e);
		}
		if(null != clazz){
			try {
				return clazz.newInstance();
			} catch (InstantiationException e) {
				throw new IllegalConverterException(e.getMessage(),e);
			} catch (IllegalAccessException e) {
				throw new IllegalConverterException(e.getMessage(),e);
			}
		}
		return null;
	}
	
	public static void updateObject(final Object object, String fieldName, Object value){
		if(null == object || null == fieldName || null == value){
			return;
		}
		try {
			Method method = object.getClass().getMethod("set"+StringUtils.capitalize(fieldName), value.getClass());
			if(null != method){
				method.invoke(object, new Object[]{value});
			}
		} catch (SecurityException e) {
			throw new IllegalConverterException(e.getMessage(),e);
		} catch (NoSuchMethodException e) {
			throw new IllegalConverterException(e.getMessage(),e);
		} catch (IllegalArgumentException e) {
			throw new IllegalConverterException(e.getMessage(),e);
		} catch (IllegalAccessException e) {
			throw new IllegalConverterException(e.getMessage(),e);
		} catch (Exception e) {
			throw new IllegalConverterException(e.getMessage(),e);
		}
	}

	
}
