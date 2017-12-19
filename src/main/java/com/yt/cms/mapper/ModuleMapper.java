package com.yt.cms.mapper;

import java.util.List;

import com.yt.cms.model.Module;
import com.yt.cms.model.Resource;

public interface ModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Module record);

    Module selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Module record);
    
    List<Module> query(Module example);
    
    List<Module> queryByParentId(Integer parentId);
    /**
     * 查询模块下所有的资源数据
     * @param moduleId
     * @return
     */
    List<Resource> queryResourcesByModuleId(Integer moduleId);
}