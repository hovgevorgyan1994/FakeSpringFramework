package com.fakespring.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Hovhannes Gevorgyan on 29.08.2022
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectProperty {

    String value() default "";
}
