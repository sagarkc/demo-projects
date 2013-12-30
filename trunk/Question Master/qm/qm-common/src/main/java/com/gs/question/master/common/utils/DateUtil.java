/**
 * 
 */
package com.gs.question.master.common.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public final class DateUtil {

	/**
	 * Add seconds to the date and return date
	 * @param date
	 * @param seconds
	 * @return
	 */
	public static Date addSeconds(Date date, int seconds){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, seconds);
		return calendar.getTime();
	}
	
}
