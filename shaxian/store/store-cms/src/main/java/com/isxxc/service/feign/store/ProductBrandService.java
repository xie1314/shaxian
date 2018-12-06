package com.isxxc.service.feign.store;

import com.isxxc.client.ProductBrandClient;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * <p>
 * 产品品牌 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-26
 */
@FeignClient("${feign-client.store-server}")
public interface ProductBrandService extends ProductBrandClient {

}
