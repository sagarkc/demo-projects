package com.gs.demo.hr.common.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface CollectionFieldMapper {

	String targetCollectionType() default "NA";
	
	String targetKeyType() default "NA";
	
	String retntionMethodName() default "NA";
	
	String addMethodName() default "NA";
}
