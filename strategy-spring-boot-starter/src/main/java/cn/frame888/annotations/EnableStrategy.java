package cn.frame888.annotations;

import cn.frame888.StrategyImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version: java version 1.8
 * @Author: SwayJike
 * @description:
 * @date: 2023-11-09 16:39
 */
@Retention(RetentionPolicy.RUNTIME) // 指定注解在运行时可用
@Target(ElementType.TYPE) // 指定注解可以用于类、接口等类型
@Import(StrategyImportSelector.class)
public @interface EnableStrategy {

    /**
     * 扫描包路径
     * @return
     */
    String baseScanPackage();
}
