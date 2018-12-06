package com.isxxc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.entity.InformationImgDO;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author likq
 * @since 2017-12-06
 */
@Repository
public interface InformationImgDAO extends BaseMapper<InformationImgDO> {

    /***
     * 根据资讯ID查询图片
     * @param id
     * @return
     */
    List<InformationImgDO> selectByInfoId(Integer id);
}