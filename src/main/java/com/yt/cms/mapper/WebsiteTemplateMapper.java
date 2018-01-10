package com.yt.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.common.Page;
import com.yt.cms.model.WebsiteTemplate;

public interface WebsiteTemplateMapper {

    int insertSelective(WebsiteTemplate record);
    
    /**
     * 批量新增
     * @param list
     */
    void insertBatch(List<WebsiteTemplate> list);
    /**
     * 批量更新
     * @param list
     */
    void updateBatch(List<WebsiteTemplate> list);
    
    int updateByPrimaryKeySelective(WebsiteTemplate record);
    
    List<WebsiteTemplate> query(@Param("webTemplate") WebsiteTemplate web, @Param("page")Page page);

    long queryCount(WebsiteTemplate web);
}