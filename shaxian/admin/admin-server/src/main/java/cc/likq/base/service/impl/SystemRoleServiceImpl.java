package cc.likq.base.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import cc.likq.base.dao.SystemFunctionRoleRelationDAO;
import cc.likq.base.dao.SystemRoleDAO;
import cc.likq.base.domain.entity.SystemRoleDO;
import cc.likq.base.service.SystemRoleService;
import com.isxxc.constant.CommonStateEnum;
import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.util.MyBeanUtils;

/**
 * <p> 服务实现类 </p>
 *
 * @author likq
 * @since 2017-11-22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SystemRoleServiceImpl extends ServiceImpl<SystemRoleDAO, SystemRoleDO> implements SystemRoleService {

    @Resource
    private SystemRoleDAO systemRoleDAO;

    @Resource
    private SystemFunctionRoleRelationDAO systemFunctionRoleRelationDAO;

    @Override
    public Result save(SystemRoleDO systemRoleDO) {
        systemRoleDO.setIsDeleted(CommonStateEnum.IsDisable.NOT_DISABLE.code);
        return insert(systemRoleDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result updateInfo(SystemRoleDO systemRoleDO) {
        systemRoleDO.setIsDeleted(null);
        SystemRoleDO systemRoleDODB = systemRoleDAO.selectById(systemRoleDO.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(systemRoleDO, systemRoleDODB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateById(systemRoleDODB) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result remove(Integer id) {
        SystemRoleDO systemRoleDO = selectById(id);
        systemRoleDO.setIsDeleted(CommonStateEnum.IsDisable.HAVE_DISABLE.code);
        return updateById(systemRoleDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

}
