package com.isxxc.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/***
 * 微信信息配置
 * @author 泥水佬
 */
@Component
@ConfigurationProperties(prefix = "weixinConfig")
public class WeixinConstant {
    /***
     * 应用AppId
     */
    public static String APP_ID;
    /***
     * 应用AppSecret
     */
    public static String API_KEY;
    /***
     * 支付回调
     */
    public static String NOTIFY_URL;
    /***
     * 商户号ID
     */
    public static String MCH_ID;
    /***
     * 微信支付统一接口(POST)
     */
    public final static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /***
     * 微信支付查询订单
     */
    public final static String ORDER_QUERY = "https://api.mch.weixin.qq.com/pay/orderquery";

    public static String getAppId() {
        return APP_ID;
    }

    public static void setAppId(String appId) {
        APP_ID = appId;
    }

    public static String getApiKey() {
        return API_KEY;
    }

    public static void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }

    public static String getNotifyUrl() {
        return NOTIFY_URL;
    }

    public static void setNotifyUrl(String notifyUrl) {
        NOTIFY_URL = notifyUrl;
    }

    public static String getMchId() {
        return MCH_ID;
    }

    public static void setMchId(String mchId) {
        MCH_ID = mchId;
    }

    public static String getUnifiedOrderUrl() {
        return UNIFIED_ORDER_URL;
    }

    public static String getOrderQuery() {
        return ORDER_QUERY;
    }
}
