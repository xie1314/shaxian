package cc.likq.base.service;

import com.baomidou.mybatisplus.service.IService;

import cc.likq.base.domain.entity.SystemRoleDO;
import cc.likq.result.Result;

/**
 * <p> 服务类 </p>
 *
 * @author likq
 * @since 2017-11-22
 */
public interface SystemRoleService extends IService<SystemRoleDO> {

    /***
     *添加角色
     */
    Result save(SystemRoleDO systemRoleDO);

    /**
     * 更新信息
     */
    Result updateInfo(SystemRoleDO systemRoleDO);

    /***
     *删除角色
     */
    Result remove(Integer id);

}
