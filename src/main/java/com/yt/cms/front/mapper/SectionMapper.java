package com.yt.cms.front.mapper;

import java.util.List;

import com.yt.cms.front.model.Channel;


public interface SectionMapper {
    
    List<Channel> query(Integer websiteId);
    
   
}