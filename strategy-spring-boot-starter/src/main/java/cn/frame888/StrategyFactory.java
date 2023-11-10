package cn.frame888;

import cn.frame888.annotations.Strategy;
import cn.frame888.interfaces.IStrategy;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.HashMap;
import java.util.Set;

/**
 * @version: java version 1.8
 * @Author: SwayJike
 * @description: 策略工厂
 * @date: 2023-11-09 16:45
 */
public class StrategyFactory {

    public static String packageName;

    private final HashMap<String, IStrategy> strategyHashMap;


    public StrategyFactory(){
        strategyHashMap = new HashMap<>();
        try {
            initStrategy();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void initStrategy() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Strategy.class));
        Set<BeanDefinition> candidates = scanner.findCandidateComponents(packageName);
        for (BeanDefinition candidate : candidates) {
            Class<?> clazz = Class.forName(candidate.getBeanClassName());
            Object instance = clazz.newInstance();
            Strategy strategy = clazz.getAnnotation(Strategy.class);
            if (strategyHashMap.containsKey(strategy.value())) {
                throw new RuntimeException(strategy.value() + " strategy already exists");
            }else {
                strategyHashMap.put(strategy.value(), (IStrategy) instance);
            }
        }
    }

    public IStrategy getStrategy(String strategy){
        return strategyHashMap.get(strategy);
    }

    public Object doStrategy(String strategy){
        return this.getStrategy(strategy).handleStrategy();
    }


}
