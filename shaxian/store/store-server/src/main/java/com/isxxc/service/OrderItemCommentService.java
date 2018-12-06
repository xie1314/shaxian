package com.isxxc.service;

import com.baomidou.mybatisplus.service.IService;
import com.isxxc.domain.dto.OrderItemCommentDTO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.OrderItemCommentDO;

import cc.likq.result.Result;

/**
 * <p>
 * 订单商品评价 服务类
 * </p>
 *
 * @author 泥水佬
 * @since 2018-03-23
 */
public interface OrderItemCommentService extends IService<OrderItemCommentDO> {

    /***
     * 添加评价
     * @param orderItemCommentDTO
     * @return
     */
    Result add(OrderItemCommentDTO orderItemCommentDTO);

    /***
     * 查询评价列表
     * @param pager
     * @return
     */
    Result listPage(Pager pager);

    /***
     * 根据订单项ID查询评价
     * @param orderItemId
     * @return
     */
    Result getInfoByOrderItemId(Integer orderItemId);
}
