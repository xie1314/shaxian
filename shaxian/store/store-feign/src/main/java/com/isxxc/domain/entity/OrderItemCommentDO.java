package com.isxxc.domain.entity;

import java.io.Serializable;


import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 订单商品评价
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-03-23
 */
@TableName("order_item_comment")
public class OrderItemCommentDO extends Model<OrderItemCommentDO> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 订单详情项ID
     */
	@TableField("order_item_id")
	private Integer orderItemId;
    /**
     * 商店ID
     */
	@TableField("store_id")
	private Integer storeId;
    /**
     * 会员ID
     */
	@TableField("user_id")
	private Integer userId;
	/**
	 * 商品SPUID
	 */
	@TableField("spu_id")
	private Integer spuId;
    /**
     * 商品SKUID
     */
	@TableField("sku_id")
	private Integer skuId;
    /**
     * 描述评分(小于5且大于0)
     */
	@TableField("describe_score")
	private Integer describeScore;
    /**
     * 服务评分(小于5且大于0)
     */
	@TableField("service_score")
	private Integer serviceScore;
    /**
     * 评价
     */
	private String comment;
    /**
     * 更新时间
     */
	@TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
	private Date gmtModified;
    /**
     * 创建时间
     */
	@TableField("gmt_created")
	private Date gmtCreated;

	public OrderItemCommentDO() {
	}

	public OrderItemCommentDO(Long id, Integer orderItemId, Integer storeId, Integer userId, Integer spuId, Integer skuId, Integer describeScore, Integer serviceScore, String comment, Date gmtModified, Date gmtCreated) {
		this.id = id;
		this.orderItemId = orderItemId;
		this.storeId = storeId;
		this.userId = userId;
		this.spuId = spuId;
		this.skuId = skuId;
		this.describeScore = describeScore;
		this.serviceScore = serviceScore;
		this.comment = comment;
		this.gmtModified = gmtModified;
		this.gmtCreated = gmtCreated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSpuId() {
		return spuId;
	}

	public void setSpuId(Integer spuId) {
		this.spuId = spuId;
	}

	public Integer getSkuId() {
		return skuId;
	}

	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}

	public Integer getDescribeScore() {
		return describeScore;
	}

	public void setDescribeScore(Integer describeScore) {
		this.describeScore = describeScore;
	}

	public Integer getServiceScore() {
		return serviceScore;
	}

	public void setServiceScore(Integer serviceScore) {
		this.serviceScore = serviceScore;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Date getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "OrderItemComment{" +
			", id=" + id +
			", orderItemId=" + orderItemId +
			", storeId=" + storeId +
			", userId=" + userId +
			", skuId=" + skuId +
			", describeScore=" + describeScore +
			", serviceScore=" + serviceScore +
			", comment=" + comment +
			", gmtModified=" + gmtModified +
			", gmtCreated=" + gmtCreated +
			"}";
	}
}
