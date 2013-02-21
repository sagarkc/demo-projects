package jmx.spring.ui;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author sabuj.das
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnHeader {

        String title();
        int index();
        boolean visible() default true;
        
}