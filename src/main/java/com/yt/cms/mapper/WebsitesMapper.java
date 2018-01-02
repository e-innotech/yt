package com.yt.cms.mapper;

import java.util.List;

import com.yt.cms.model.Websites;

public interface WebsitesMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Websites record);
    /**
     * 级联查询网站下的所有栏位信息
     * @param id
     * @return
     */
    Websites selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Websites record);

    List<Websites> query(Websites web);
    
    int deleteLogicById(Integer id);
}