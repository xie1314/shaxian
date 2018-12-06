package com.isxxc.domain.dto;

import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.domain.entity.OrderItemAttrDO;
import com.isxxc.domain.entity.OrderItemCommentDO;

import java.util.Date;
import java.util.List;

/**
 * 订单商品评论
 *
 * @author 泥水佬
 * @date 2018/3/23
 */
public class OrderItemCommentDTO extends OrderItemCommentDO {
    /***
     * 会员昵称
     */
    private String nickname;
    /***
     * 会员头像
     */
    private String avaterWebPath;
    /***
     * 商品属性
     */
    private List<OrderItemAttrDO> attrList;

    public OrderItemCommentDTO() {
    }

    public OrderItemCommentDTO(Long id, Integer orderItemId, Integer storeId, Integer userId, Integer spuId, Integer skuId, Integer describeScore, Integer serviceScore, String comment, Date gmtModified, Date gmtCreated, String nickname, String avaterPath) {
        super(id, orderItemId, storeId, userId, spuId, skuId, describeScore, serviceScore, comment, gmtModified, gmtCreated);
        this.nickname = nickname;
        this.avaterWebPath = CommonFolderConstant.getAvaterWebPath(avaterPath);
    }

    /***
     * 图片集
     */
    private List<OrderItemCommentImgDTO> commentImgList;

    public List<OrderItemCommentImgDTO> getCommentImgList() {
        return commentImgList;
    }

    public void setCommentImgList(List<OrderItemCommentImgDTO> commentImgList) {
        this.commentImgList = commentImgList;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvaterWebPath() {
        return avaterWebPath;
    }

    public void setAvaterWebPath(String avaterWebPath) {
        this.avaterWebPath = avaterWebPath;
    }

    public List<OrderItemAttrDO> getAttrList() {
        return attrList;
    }

    public void setAttrList(List<OrderItemAttrDO> attrList) {
        this.attrList = attrList;
    }
}
