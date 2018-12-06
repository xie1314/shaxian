package cc.likq.base.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import cc.likq.base.dao.SystemFunctionDAO;
import cc.likq.base.domain.dto.SystemFunctionDTO;
import cc.likq.base.domain.entity.SystemFunctionDO;
import cc.likq.base.service.SystemFunctionService;
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
public class SystemFunctionServiceImpl extends ServiceImpl<SystemFunctionDAO, SystemFunctionDO> implements SystemFunctionService {

    @Override
    public Result save(SystemFunctionDO systemFunctionDO) {
        return insert(systemFunctionDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result remove(Integer id) {
        return deleteById(id) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result updateInfo(SystemFunctionDO systemFunctionDO) {
        SystemFunctionDO systemFunctionDODB = systemFunctionDO.selectById(systemFunctionDO.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(systemFunctionDO, systemFunctionDODB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateById(systemFunctionDODB) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result listTree() {
        EntityWrapper<SystemFunctionDO> entityWrapper = new EntityWrapper<>();
        List<SystemFunctionDO> systemFunctionDOList = selectList(entityWrapper);
        systemFunctionDOList.sort(Comparator.comparing(SystemFunctionDO::getParentId).thenComparing(SystemFunctionDO::getLevel));
        List<SystemFunctionDTO> systemFunctionParents = new ArrayList<>();
        systemFunctionDOList.stream().filter(systemFunction -> systemFunction.getParentId() == 0).forEach(systemFunctionDO -> systemFunctionParents.add(new SystemFunctionDTO(systemFunctionDO)));
        systemFunctionParents.forEach(systemFunctionDTO -> sortTree(systemFunctionDTO, systemFunctionDOList));
        return ResponseResult.success(systemFunctionParents);
    }

    /***
     * 递归构建树
     * @param currSystemFunctionDTO 父对像
     * @param systemFunctionDOList 数据列表
     */
    private void sortTree(SystemFunctionDTO currSystemFunctionDTO, List<SystemFunctionDO> systemFunctionDOList) {
        for (SystemFunctionDO systemFunction : systemFunctionDOList) {
            SystemFunctionDTO systemFunctionDTO = new SystemFunctionDTO(systemFunction);
            if (currSystemFunctionDTO.getId().equals(systemFunctionDTO.getParentId())) {
                if (currSystemFunctionDTO.getChildList() == null) {
                    currSystemFunctionDTO.setChildList(new ArrayList<SystemFunctionDTO>() {{
                        add(systemFunctionDTO);
                    }});
                } else {
                    currSystemFunctionDTO.getChildList().add(systemFunctionDTO);
                }
                sortTree(systemFunctionDTO, systemFunctionDOList);
            }
        }
    }

}
