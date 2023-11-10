package cn.frame888.wechatpay.annotations;

import cn.frame888.wechatpay.WeChatPayImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version: java version 1.8
 * @Author: SwayJike
 * @description:
 * @date: 2023-11-20 15:14
 */
@Retention(RetentionPolicy.RUNTIME) // 指定注解在运行时可用
@Target(ElementType.TYPE) // 指定注解可以用于类、接口等类型
@Import(WeChatPayImportSelector.class)
public @interface EnableWeChatPay {
}
