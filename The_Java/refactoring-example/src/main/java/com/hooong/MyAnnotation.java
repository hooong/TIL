package com.hooong;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
//@Target({ElementType.TYPE, ElementType.FIELD})
@Inherited
public @interface MyAnnotation {

    String value() default "hooong";

    int number() default 100;
}
