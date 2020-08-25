package com.blankj.utilcode.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface BusUtils$Bus {
    int priority() default 0;

    boolean sticky() default false;

    String tag();

    BusUtils$ThreadMode threadMode() default BusUtils$ThreadMode.POSTING;
}
