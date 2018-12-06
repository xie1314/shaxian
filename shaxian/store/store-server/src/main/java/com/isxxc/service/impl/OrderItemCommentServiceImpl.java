package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.constant.OrderStateEnum;
import com.isxxc.dao.OrderItemCommentDAO;
import com.isxxc.domain.dto.OrderItemCommentDTO;
import com.isxxc.domain.dto.OrderItemCommentImgDTO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.OrderInfoDO;
import com.isxxc.domain.entity.OrderItemAttrDO;
import com.isxxc.domain.entity.OrderItemCommentDO;
import com.isxxc.domain.entity.OrderItemDO;
import com.isxxc.service.OrderInfoService;
import com.isxxc.service.OrderItemAttrService;
import com.isxxc.service.OrderItemCommentImgService;
import com.isxxc.service.OrderItemCommentService;
import com.isxxc.service.OrderItemService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;
import cc.likq.util.FileUtils;

/**
 * <p>
 * 订单商品评价 服务实现类
 * </p>
 *
 * @author 泥水佬
 * @since 2018-03-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderItemCommentServiceImpl extends ServiceImpl<OrderItemCommentDAO, OrderItemCommentDO> implements OrderItemCommentService {

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private OrderItemService orderItemService;

    @Resource
    private OrderItemCommentDAO orderItemCommentDAO;

    @Resource
    private OrderItemCommentImgService orderItemCommentImgService;

    @Resource
    private OrderItemAttrService orderItemAttrService;

    @Override
    public Result add(OrderItemCommentDTO orderItemCommentDTO) {
        for (OrderItemCommentImgDTO commentImgDTO : orderItemCommentDTO.getCommentImgList()) {
            if (!FileUtils.exists(CommonFolderConstant.getImageTempPath(commentImgDTO.getName()))) {
                return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "图片已经失效,请重新上传", new HashMap<String, String>(1) {{
                    put("key", commentImgDTO.getName());
                }});
            }
        }
        OrderItemDO orderItemDO = orderItemService.selectById(orderItemCommentDTO.getOrderItemId());
        if (orderItemDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "订单详情项ID错误");
        }
        OrderInfoDO orderInfoDO = orderInfoService.selectById(orderItemDO.getOrderInfoId());
        if (orderInfoDO == null || !orderInfoDO.getUserId().equals(orderItemCommentDTO.getUserId())) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "订单信息错误,请联系管理员");
        }
        if (OrderStateEnum.MasterState.COMPLETE_TRANSACTION.code != orderInfoDO.getMasterState()) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "当前订单不能评价");
        }
        if (OrderStateEnum.CommentState.WAIT_COMMENT.code != orderItemDO.getCommentState()) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "当前订单商品已评价,请不要重复评价");
        }

        orderItemCommentDTO.setStoreId(orderInfoDO.getStoreId());
        orderItemCommentDTO.setSpuId(orderItemDO.getSpuId());
        orderItemCommentDTO.setSkuId(orderItemDO.getSkuId());
        orderItemCommentDTO.setGmtModified(new Date());
        orderItemCommentDTO.setGmtCreated(new Date());
        orderItemCommentDAO.insert(orderItemCommentDTO);

        orderItemDO.setCommentState(OrderStateEnum.CommentState.ALREADY_COMMENT.code);
        orderItemService.updateById(orderItemDO);

        orderItemCommentDTO.getCommentImgList().forEach(orderItemCommentImgDTO -> {
            orderItemCommentImgDTO.setOrderItemCommentId(orderItemCommentDTO.getId());
            orderItemCommentImgDTO.setGmtCreate(new Date());
            orderItemCommentImgDTO.setGmtModified(new Date());
            orderItemCommentImgService.insert(orderItemCommentImgDTO);
        });
        orderItemCommentDTO.getCommentImgList().forEach(orderItemCommentImgDTO -> FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(orderItemCommentImgDTO.getName()), CommonFolderConstant.getProductCommentPath()));
        return ResponseResult.success();
    }

    @Override
    public Result listPage(Pager pager) {
        List<OrderItemCommentDTO> itemCommentDTOList = orderItemCommentDAO.selectDTOList(pager, pager.getParamMap());
        itemCommentDTOList.forEach(orderItemCommentDTO -> {
            List<OrderItemCommentImgDTO> commentImgDTOList = orderItemCommentImgService.selectDTOByCommentId(orderItemCommentDTO.getId());
            orderItemCommentDTO.setCommentImgList(commentImgDTOList);
            EntityWrapper<OrderItemAttrDO> itemAttrDOEntityWrapper = new EntityWrapper<>();
            itemAttrDOEntityWrapper.eq("order_item_id", orderItemCommentDTO.getOrderItemId());
            orderItemCommentDTO.setAttrList(orderItemAttrService.selectList(itemAttrDOEntityWrapper));
        });
        pager.setRecords(itemCommentDTOList);
        return ResponseResult.success(pager);
    }

    @Override
    public Result getInfoByOrderItemId(Integer orderItemId) {
        OrderItemCommentDTO itemCommentDTO = orderItemCommentDAO.getInfoByOrderItemId(orderItemId);
        List<OrderItemCommentImgDTO> commentImgDTOList = orderItemCommentImgService.selectDTOByCommentId(itemCommentDTO.getId());
        itemCommentDTO.setCommentImgList(commentImgDTOList);
        EntityWrapper<OrderItemAttrDO> itemAttrDOEntityWrapper = new EntityWrapper<>();
        itemAttrDOEntityWrapper.eq("order_item_id", itemCommentDTO.getOrderItemId());
        itemCommentDTO.setAttrList(orderItemAttrService.selectList(itemAttrDOEntityWrapper));
        return ResponseResult.success(itemCommentDTO);
    }
}
