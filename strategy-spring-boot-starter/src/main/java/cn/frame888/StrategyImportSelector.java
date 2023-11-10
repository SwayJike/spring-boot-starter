package cn.frame888;

import cn.frame888.annotations.EnableStrategy;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Objects;

/**
 * @version: java version 1.8
 * @Author: SwayJike
 * @description:
 * @date: 2023-11-10 9:35
 */
public class StrategyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 在这里可以使用 importingClassMetadata 对象获取注解信息
        String basePackage = (String) Objects.requireNonNull(importingClassMetadata.getAnnotationAttributes(EnableStrategy.class.getName())).get("baseScanPackage");
        // 在实际应用中，你可能需要进行一些额外的逻辑，比如处理默认值等
        StrategyFactory.packageName = basePackage;

        return new String[]{StrategyFactory.class.getName(), StrategyContext.class.getName()};
    }
}
