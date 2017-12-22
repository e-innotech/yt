package com.yt.cms.mapper;

import java.util.List;

import com.yt.cms.model.YtcmsConfig;

public interface YtcmsConfigMapper {

    int insertSelective(YtcmsConfig record);

    YtcmsConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YtcmsConfig record);
    
    List<YtcmsConfig> query(YtcmsConfig config);

}