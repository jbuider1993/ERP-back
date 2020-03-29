package com.scmp.common.annotation;

import com.scmp.common.model.enums.OperatorLogType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface OperatorLogger {

    /**
     * 操作日志类型
     */
    OperatorLogType type() default OperatorLogType.DATA;

    /**
     * 操作日志描述
     */
    String description() default "";
}