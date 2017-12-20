package com.yt.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.model.WebsitesNavigationBar;

public interface WebsitesNavigationBarMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(WebsitesNavigationBar record);
    /**
     * 批量新增
     * @param barList
     */
    void insertBatch(List<WebsitesNavigationBar> barList);
    /**
     * 批量删除指定网站id下的 栏位id
     * @param websitesId
     * @param navigationBarIds
     */
    void deleteByWebsitesId(@Param("websitesId") Integer websitesId, @Param("navigationBarIds") List<Integer> navigationBarIds);
    
    int updateByPrimaryKeySelective(WebsitesNavigationBar record);

    List<WebsitesNavigationBar> query(WebsitesNavigationBar record);
}