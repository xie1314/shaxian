package com.isxxc.web;


import com.isxxc.service.feign.store.PaymentService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.util.NetworkUtils;

/**
 * <p>
 * 支付 前端控制器
 * </p>
 *
 * @author 泥水佬
 * @since 2018-02-03
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    /***
     * 根据订单号生成微信预支付信息
     * @param orderNo
     * @return
     */
    @RequestMapping(value = "generateWxPayByOrderNo", method = RequestMethod.GET)
    public Result generateWxPayByOrderNo(HttpServletRequest request, String orderNo) {
        if (StringUtils.isBlank(orderNo)) {
            return ResponseResult.paramNotNull("订单号不能为空");
        }
        String clientIp = NetworkUtils.getIpAddress(request);
        return paymentService.generateWxPayByOrderNo(clientIp, orderNo);
    }

    /***
     * 根据合并号生成微信预支付信息
     * @param mergerNo
     * @return
     */
    @RequestMapping(value = "generateWxPayByMergerNo", method = RequestMethod.GET)
    public Result generateWxPayByMergerNo(HttpServletRequest request, String mergerNo) {
        if (StringUtils.isBlank(mergerNo)) {
            return ResponseResult.paramNotNull("订单号不能为空");
        }
        String clientIp = NetworkUtils.getIpAddress(request);
        return paymentService.generateWxPayByMergerNo(clientIp, mergerNo);
    }

    /**
     * 微信二维码支付回调
     */
    @RequestMapping(value = "wxQRPayResult")
    public void wxQRPayResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("***********************微信支付回调开始**********************");
        String resultXml = "";
        String inputLine;
        try {
            while ((inputLine = request.getReader().readLine()) != null) {
                resultXml += inputLine;
            }
            request.getReader().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("微信回调信息: " + resultXml);

        // 支付结果
        Map<String, String> paramsMap = new HashMap<>(1);
        paramsMap.put("resultXml", resultXml);
        String resXml = paymentService.wxQRPayResult(paramsMap);
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
        System.out.println("系统响应信息: " + resXml);
        System.out.println("***********************微信支付回调结束**********************");
    }

}
