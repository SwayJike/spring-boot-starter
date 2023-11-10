package cn.frame888;

import javax.annotation.Resource;

/**
 * @version: java version 1.8
 * @Author: SwayJike
 * @description: 上下文类，持有策略接口
 * @date: 2023-11-09 16:43
 */
public class StrategyContext {

    @Resource
    private StrategyFactory strategyFactory;

    public Object doStrategy(String strategy){
        return strategyFactory.getStrategy(strategy).handleStrategy();
    }

}
