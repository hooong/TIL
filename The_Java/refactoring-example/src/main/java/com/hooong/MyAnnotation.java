package com.hooong;

import java.lang.annotation.*;

// Retention으로 해당 애노테이션이 어디까지 살아남을 수 있는지를 지정할 수 있음.
@Retention(RetentionPolicy.RUNTIME)
//@Target({ElementType.TYPE, ElementType.FIELD})
@Inherited
public @interface MyAnnotation {

    String value() default "hooong";

    int number() default 100;
}
