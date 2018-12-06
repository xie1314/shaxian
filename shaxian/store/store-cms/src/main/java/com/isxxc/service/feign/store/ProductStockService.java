package com.isxxc.service.feign.store;

import com.isxxc.client.ProductStockClient;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 商品服务
 *
 * @author 泥水佬
 * @date 2018/1/14
 */
@FeignClient("${feign-client.store-server}")
public interface ProductStockService extends ProductStockClient {
}
