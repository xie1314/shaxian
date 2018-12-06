package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.dto.OrderItemCommentDTO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.OrderItemCommentDO;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 订单商品评论 Mapper 接口
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-03-23
 */
@Repository
public interface OrderItemCommentDAO extends BaseMapper<OrderItemCommentDO> {

    /***
     * 查询评论列表
     * @param pager
     * @param paramMap
     * @return
     */
    List<OrderItemCommentDTO> selectDTOList(Pager pager, @Param("params") Map<String, Object> paramMap);

    /***
     * 根据订单项ID查询
     * @param orderItemId
     * @return
     */
    OrderItemCommentDTO getInfoByOrderItemId(Integer orderItemId);
}