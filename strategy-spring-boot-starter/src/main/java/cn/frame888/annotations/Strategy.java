package cn.frame888.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version: java version 1.8
 * @Author: SwayJike
 * @description: 策略注解
 * @date: 2023-11-09 16:38
 */
@Retention(RetentionPolicy.RUNTIME) // 指定注解在运行时可用
@Target(ElementType.TYPE) // 指定注解可以用于类、接口等类型
public @interface Strategy {

    String value();
}
