package com.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE) //告诉编译器使用在类上面的
@Retention(RetentionPolicy.SOURCE) //是在源码阶段处理
public @interface EntryGenerator {
    String packageName();
    Class<?> entryTemplate();
}
