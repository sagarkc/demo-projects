/**
 * 
 */
package net.sf.jquery.tags.util;

/**
 * @author sabuj.das
 *
 */
public final class ReflectionUtil {

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
}
