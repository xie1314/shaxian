package com.isxxc.client;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

import cc.likq.result.Result;

/**
 * <p>
 * 支付 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@RequestMapping("/paymentClient")
public interface PaymentClient {
    /***
     * 根据订单号生成微信预支付信息
     * @param orderNo
     * @param clientIp
     * @return
     */
    @RequestMapping(value = "generateWxPayByOrderNo", method = RequestMethod.GET)
    Result generateWxPayByOrderNo(@RequestParam("clientIp") String clientIp, @RequestParam("orderNo") String orderNo);

    /***
     * 根据合并号生成微信预支付信息
     * @param mergerNo
     * @param clientIp
     * @return
     */
    @RequestMapping(value = "generateWxPayByMergerNo", method = RequestMethod.GET)
    Result generateWxPayByMergerNo(@RequestParam("clientIp") String clientIp, @RequestParam("mergerNo") String mergerNo);

    /***
     * 微信二维码支付结果回调
     * @param paramsMap
     * @return
     */
    @RequestMapping(value = "wxQRPayResult", method = RequestMethod.POST)
    String wxQRPayResult(@RequestParam Map<String, String> paramsMap);
}
