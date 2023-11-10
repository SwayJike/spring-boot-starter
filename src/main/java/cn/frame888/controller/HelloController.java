package cn.frame888.controller;

import cn.frame888.ResponseBodyWrapper;
import cn.frame888.StrategyContext;
import cn.frame888.controller.enums.StrategyEnums;
import cn.frame888.util.OrderNumberGenerator;
import cn.frame888.wechatpay.WeChatPayService;
import cn.hutool.extra.servlet.ServletUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @version: java version 1.8
 *
 *
 * @Author: SwayJike
 * @description:
 * @date: 2023-11-09 15:59
 */
@RestController
@ResponseBodyWrapper
public class HelloController {

    @Resource
    private StrategyContext strategyContext;

    @Resource
    private WeChatPayService weChatPayService;

    @RequestMapping("/hello")
    public String hello(HttpServletRequest request) throws Exception {
        strategyContext.doStrategy(StrategyEnums.A_Strategy);
        String outTradeNo = OrderNumberGenerator.generateOrderNumber(null);
        String clientIP = ServletUtil.getClientIP(request);
        System.out.println(outTradeNo);
        System.out.println(clientIP);
        return weChatPayService.nativePay(
                "支付测试",
                outTradeNo,
                "1",
                clientIP).get();
    }

    @RequestMapping("/refund")
    public Boolean refund() throws Exception {
        return weChatPayService.refund(
                "20231121141139_0561",
                "Refund_20231121141139_0561",
                "1",
                "1",
                "https://localhost:8888/refund").get();
    }

    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
