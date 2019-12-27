package com.dalaoyang.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* Description 注解类
* @Author junwei
* @Date 16:00 2019/12/27
**/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    //参数1
    String param1() default "";

    //参数2
    String param2() default "";

    //以此类推。。需要新增参数的话，继续加
}
