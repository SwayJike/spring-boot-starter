package cn.frame888.wechatpay.impl;

import cn.frame888.wechatpay.WeChatPayService;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @version: java version 1.8
 * @Author: SwayJike
 * @description:
 * @date: 2023-11-20 14:25
 */
@Slf4j
public class WeChatPayServiceImpl implements WeChatPayService {

    private final WXPay wxPay;

    public WeChatPayServiceImpl(WXPayConfigImpl wxPayConfig) {
        log.info("Initializing Pay For 'WeChatPayServiceImpl'");
        this.wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5);
    }

    /**
     * 微信原生支付响应处理程序
     */
    @FunctionalInterface
    public interface WeChatNativePayResponseHandler extends Supplier<String>{}

    /**
     * 原生支付
     * @param body
     * @param outTradeNo
     * @param totalFee
     * @param spbillCreateIp
     * @return
     * @throws Exception
     */
    @Override
    public WeChatNativePayResponseHandler nativePay(String body,
                                         String outTradeNo,
                                         String totalFee,
                                         String spbillCreateIp) throws Exception {

        Map<String, String> data = new HashMap<>();
        data.put("body", body);
        data.put("out_trade_no", outTradeNo);
        data.put("total_fee", totalFee);
        data.put("spbill_create_ip", spbillCreateIp);
        data.put("trade_type", "NATIVE");
        data.put("notify_url", "异步通知地址");

        Map<String, String> result = wxPay.unifiedOrder(data);

        log.info(result.toString());

        return () -> {
            String codeUrl = null;
            if ("SUCCESS".equals(result.get("return_code")) && "SUCCESS".equals(result.get("result_code"))) {
                // 返回给前端，用于生成支付二维码
                codeUrl = result.get("code_url");
            } else {
                // 处理支付失败的逻辑
                log.error("微信支付失败：" + result.get("return_msg"));
            }
            return codeUrl;
        };
    }

    /**
     * 微信退款响应处理程序
     */
    @FunctionalInterface
    public interface WeChatRefundResponseHandler extends Supplier<Boolean>{}

    @Override
    public WeChatRefundResponseHandler refund(String outTradeNo,
                                              String outRefundNo,
                                              String totalFee,
                                              String refundFee,
                                              String notifyUrl) throws Exception {

        Map<String, String> data = new HashMap<>();
        data.put("out_trade_no", outTradeNo);
        data.put("out_refund_no", outRefundNo);
        data.put("total_fee", totalFee);
        data.put("refund_fee", refundFee);
        data.put("notify_url", notifyUrl);
        data.put("nonce_str", WXPayUtil.generateNonceStr());

        // 调用退款接口
        Map<String, String> result = wxPay.refund(data);

        log.info(result.toString());

        return () -> {
            Boolean flag = Boolean.FALSE;
            if ("SUCCESS".equals(result.get("return_code")) && "SUCCESS".equals(result.get("result_code"))) {
                log.info("微信退款成功");
                flag = Boolean.TRUE;
            } else {
                // 处理支付失败的逻辑
                log.error("微信退款失败：" + result.get("return_msg"));
            }
            return flag;
        };
    }


}
