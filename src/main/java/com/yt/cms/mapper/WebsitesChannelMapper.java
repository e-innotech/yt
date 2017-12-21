package com.yt.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.model.WebsitesChannel;

public interface WebsitesChannelMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(WebsitesChannel record);
    /**
     * 批量新增
     * @param list
     */
    void insertBatch(List<WebsitesChannel> list);
    /**
     * 批量删除指定网站id下的 栏位id
     * @param websitesId
     * @param channelIds
     */
    void deleteByWebsitesId(@Param("websitesId") Integer websitesId, @Param("channelIds") List<Integer> channelIds);
    
    int updateByPrimaryKeySelective(WebsitesChannel record);

    List<WebsitesChannel> query(WebsitesChannel record);
}