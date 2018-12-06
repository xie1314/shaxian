package cc.likq.base.web;


import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import cc.likq.base.domain.entity.SystemRoleDO;
import cc.likq.base.service.SystemRoleService;
import cc.likq.result.ResponseResult;
import cc.likq.result.Result;

/**
 * 角色管理
 *
 * @author likq
 * @since 2017-11-22
 */
@RestController
@RequestMapping("/systemRole")
public class SystemRoleController {

    @Resource
    private SystemRoleService systemRoleService;

    /***
     * 添加角色
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    private Result save(SystemRoleDO systemRoleDO) {
        if (StringUtils.isBlank(systemRoleDO.getName())) {
            return ResponseResult.paramNotNull("名称不能为空");
        }
        return systemRoleService.save(systemRoleDO);
    }

    /***
     * 更新角色信息
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    private Result update(SystemRoleDO systemRoleDO) {
        if (systemRoleDO.getId() == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return systemRoleService.updateInfo(systemRoleDO);
    }

    /***
     * 删除角色
     */
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    private Result remove(Integer id) {
        if (id == null) {
            return ResponseResult.paramNotNull("ID不能为空");
        }
        return systemRoleService.remove(id);
    }

}
