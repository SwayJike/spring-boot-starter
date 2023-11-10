package cn.frame888.wechatpay;

import cn.frame888.wechatpay.impl.WXPayConfigImpl;
import cn.frame888.wechatpay.impl.WeChatPayServiceImpl;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @version: java version 1.8
 * @Author: SwayJike
 * @description:
 * @date: 2023-11-20 15:15
 */
public class WeChatPayImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{WeChatPayServiceImpl.class.getName(), WXPayConfigImpl.class.getName()};
    }
}
