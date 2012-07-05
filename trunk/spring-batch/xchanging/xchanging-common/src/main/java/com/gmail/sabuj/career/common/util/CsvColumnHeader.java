/**
 * 
 */
package com.gmail.sabuj.career.common.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 *
 * @author <a href="mailo:career.sabuj@gmail.com">Sabuj Das</a>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CsvColumnHeader {

	String title() default "--";
	int index();
	boolean useDateFormat() default false;
	boolean useTextFormat() default false;
	boolean useNumberFormat() default false;
	String dateFormat() default "";
	String textFormat() default "";
	String numberFormat() default "";
}
