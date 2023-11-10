package cn.frame888.controller.strategy;

import cn.frame888.annotations.Strategy;
import cn.frame888.controller.enums.StrategyEnums;
import cn.frame888.interfaces.IStrategy;
import lombok.extern.slf4j.Slf4j;

/**
 * @version: java version 1.8
 * @Author: SwayJike
 * @description:
 * @date: 2023-11-10 11:48
 */
@Slf4j
@Strategy(StrategyEnums.A_Strategy)
public class AStrategy implements IStrategy {

    @Override
    public Object handleStrategy() {
        log.info("AStrategy handleStrategy...");
        return null;
    }
}
