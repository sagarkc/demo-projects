/**
 * 
 */
package net.sf.tools4csv.core.util;


import java.text.DecimalFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;

/**
 * @author sabuj.das
 * 
 */
public final class FormatManager {

	private static FormatManager instance;

	/**
	 * Private constructor
	 */
	private FormatManager() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the instance
	 */
	public static FormatManager getInstance() {
		synchronized (FormatManager.class) {
			if (null == instance)
				instance = new FormatManager();
		}
		return instance;
	}

	protected final Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Clone Not Supported: [ "
				+ getClass().getCanonicalName() + " ] is a singleton class!!!");
	}

	private <V> boolean isFormatableType(Class<V> clazz) {
		return (ReflectionUtil.isTextType(clazz) || ReflectionUtil.isNumberType(clazz) || ReflectionUtil.isDateType(clazz));
	}

	@SuppressWarnings("unchecked")
	public final <T extends Format, V> T getDataFormat(Class<V> valueClazz,
			String formatString) {
		if (!isFormatableType(valueClazz))
			throw new IllegalArgumentException(
					"No Format available for class: "
							+ valueClazz.getCanonicalName());

		if (null == formatString || "".equals(formatString.trim()))
			throw new IllegalArgumentException(
					"Invalid format pattern: "
							+ formatString);

		if (ReflectionUtil.isTextType(valueClazz)) {
			return (T) new MessageFormat(formatString);
		}
		if (ReflectionUtil.isDateType(valueClazz)) {
			return (T) new SimpleDateFormat(formatString);
		}
		if (ReflectionUtil.isNumberType(valueClazz)) {
			return (T) new DecimalFormat(formatString);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public final <T extends Format, V> T getDataFormat(String formatType,
			String formatString) {
		if (!isFormatableType(formatType))
			throw new IllegalArgumentException(
					"No Format available for class: "
							+ formatType);

		if (null == formatString || "".equals(formatString.trim()))
			throw new IllegalArgumentException(
					"Invalid format pattern: "
							+ formatString);

		if (ReflectionUtil.isTextType(formatType)) {
			return (T) new MessageFormat(formatString);
		}
		if (ReflectionUtil.isDateType(formatType)) {
			return (T) new SimpleDateFormat(formatString);
		}
		if (ReflectionUtil.isNumberType(formatType)) {
			return (T) new DecimalFormat(formatString);
		}
		return null;
	}

	
	private boolean isFormatableType(String formatType) {
		return (ReflectionUtil.isTextType(formatType) || ReflectionUtil.isNumberType(formatType) || ReflectionUtil.isDateType(formatType));
	}

}
