package com.yt.cms.mapper;

import java.util.List;

import com.yt.cms.model.Roles;
import com.yt.cms.model.page.RolesPage;

public interface RolesMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Roles record);

    Roles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Roles record);

    List<Roles> query(RolesPage record);
    
    int queryCount(RolesPage page);
    
    int deleteLogicById(Integer id);
}