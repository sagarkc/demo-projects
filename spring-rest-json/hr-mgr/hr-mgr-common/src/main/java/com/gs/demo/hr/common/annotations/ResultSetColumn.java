

package com.gs.demo.hr.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sabuj.das
 * @MailTo sabuj.das@gmail.com
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ResultSetColumn {

	String propertyName();
	String mappedColumnName();
	
}
