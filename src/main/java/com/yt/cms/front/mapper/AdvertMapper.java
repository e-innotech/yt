package com.yt.cms.front.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.front.model.Ad;


public interface AdvertMapper {
    
	List<Ad> getWebsiteTemplateAds(@Param("websiteId") Integer websiteId, @Param("templateType") Integer templateType);
    
}