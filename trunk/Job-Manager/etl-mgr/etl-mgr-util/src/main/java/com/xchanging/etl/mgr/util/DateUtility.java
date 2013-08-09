/**
 * 
 */
package com.xchanging.etl.mgr.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Sabuj
 *
 */
public final class DateUtility {

	public static Date getCurrentDateWithoutTime(){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
}
