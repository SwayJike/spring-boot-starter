package cn.frame888.wechatpay;

import cn.frame888.wechatpay.impl.WeChatPayServiceImpl;

/**
 * @version: java version 1.8
 * @Author: SwayJike
 * @description:
 * @date: 2023-11-20 14:24
 */
public interface WeChatPayService {

    /**
     *
     * @param body 交易商品详情
     * @param outTradeNo 交易号
     * @param totalFee 总费用
     * @param spbillCreateIp 请求Ip
     * @return
     * @throws Exception
     */
    WeChatPayServiceImpl.WeChatNativePayResponseHandler nativePay(String body,
                                                                  String outTradeNo,
                                                                  String totalFee,
                                                                  String spbillCreateIp) throws Exception;

    /**
     *
     * @param outTradeNo 交易号
     * @param outRefundNo 退款交易号
     * @param totalFee 总费用
     * @param refundFee 退款费用
     * @param notifyUrl 回调地址
     * @return
     * @throws Exception
     */
    WeChatPayServiceImpl.WeChatRefundResponseHandler refund(String outTradeNo,
                                                            String outRefundNo,
                                                            String totalFee,
                                                            String refundFee,
                                                            String notifyUrl) throws Exception;
}
