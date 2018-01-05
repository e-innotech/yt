package com.yt.cms.mapper;

import java.util.List;

import com.yt.cms.model.Resource;
import com.yt.cms.model.ResourceW;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resource record);
    
    List<Resource> query(Resource record);
    
    List<Resource> queryAll();
    
    int deleteLogicById(Integer id);
    /**
     * 查出所有写的资源，即相对于数据库的写操作
     * @return
     */
    List<ResourceW> queryResource_W();
}