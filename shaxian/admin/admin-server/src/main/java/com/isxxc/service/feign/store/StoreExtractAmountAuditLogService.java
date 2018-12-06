package com.isxxc.service.feign.store;

import com.isxxc.client.StoreExtractAmountAuditLogClient;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * <p>
 * 商店提取金额审核日志 服务类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-03-06
 */
@FeignClient("${feign-client.store-server}")
public interface StoreExtractAmountAuditLogService extends StoreExtractAmountAuditLogClient {

}
