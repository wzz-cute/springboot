package com.wzz.base.aop.an;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 判断字段名，如果userId在于方法列表参数直接获取
 * 如果不在表面参数就根据字段名称，获取类型中的userId，类型只获取一层
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserIdTransformation {
    String filed() default "userId";

    Class<?> type();

    interface Constant{
        public String USER_ID = "userId";
    }
}
