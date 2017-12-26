package com.yt.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.model.Button;
import com.yt.cms.model.Menu;
import com.yt.cms.model.Resource;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resource record);
    
    List<Resource> query(Resource record);
    
    List<Menu> queryMenu();
    
    List<Integer> queryMenuByUserGroupId(Integer userGroupId);
    
    List<Button> queryButtonByUserGroupId_menuId(@Param("userGroupId") Integer userGroupId, @Param("menuId") Integer menuId);
    
}