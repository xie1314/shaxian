package com.isxxc.service;

import java.util.Map;

import cc.likq.result.Result;

/**
 * <p>
 * 支付 服务类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-02-03
 */
public interface PaymentService {

    /***
     * 根据合并号生成微信预支付信息
     * @param mergerNo
     * @return
     */
    Result generateWxPayByMergerNo(String clientIp, String mergerNo);

    /***
     * 根据订单号生成微信预支付信息
     * @param orderNo
     * @return
     */
    Result generateWxPayByOrderNo(String clientIp, String orderNo);

    /***
     * 微信二维码支付回调
     * @param paramsMap
     * @return
     */
    String wxQRPayResult(Map<String, String> paramsMap);
}
