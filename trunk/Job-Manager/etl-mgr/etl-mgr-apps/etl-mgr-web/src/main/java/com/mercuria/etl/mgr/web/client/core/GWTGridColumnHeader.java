package com.mercuria.etl.mgr.web.client.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GWTGridColumnHeader {

	String title();
	String fieldName();
	int index();
	int wigth() default 80;
	boolean visible() default true;
	String alignment() default "left";
	String type() default "TEXT";
	String imageURLPrefix() default "NA";
	String imageURLSuffix() default "NA";
}
