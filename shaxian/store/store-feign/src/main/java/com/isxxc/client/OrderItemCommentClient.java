package com.isxxc.client;


import com.isxxc.domain.dto.OrderItemCommentDTO;
import com.isxxc.domain.dto.Pager;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cc.likq.result.Result;

/**
 * <p>
 * 订单商品评价 服务治理
 * </p>
 *
 * @author 泥水佬
 * @since 2018-03-23
 */
@RequestMapping("/orderItemCommentClient")
public interface OrderItemCommentClient {

    /***
     * 添加评价
     * @param orderItemCommentDTO
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result add(OrderItemCommentDTO orderItemCommentDTO);

    /***
     * 查询评价列表
     * @param pager
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result listPage(Pager pager);

    /***
     * 根据订单项ID查询评价
     * @param orderItemId
     * @return
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    Result getInfoByOrderItemId(@RequestParam("orderItemId") Integer orderItemId);
}
