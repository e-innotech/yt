package com.yt.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.common.Page;
import com.yt.cms.model.UserGroup;

public interface UserGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserGroup record);

    UserGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserGroup record);
    /**
     * 分页查询或者多条件查询
     * @param record
     * @return
     */
    List<UserGroup> query(@Param("groupName") String groupName, @Param("page") Page page);
    
    /**
     * 分页查询或者多条件查询
     * @param record
     * @return
     */
    long queryCount(@Param("groupName") String groupName);
    /**
     * 树形展现使用
     * @return
     */
    List<UserGroup> queryAll();
    
    int deleteLogicById(Integer id);
  
    
}