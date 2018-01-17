package com.yt.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.common.Page;
import com.yt.cms.model.YtcmsConfig;

public interface YtcmsConfigMapper {

    int insertSelective(YtcmsConfig record);

    YtcmsConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YtcmsConfig record);
    
    List<YtcmsConfig> query(@Param("config") YtcmsConfig config, @Param("page") Page page);
    
    long queryCount(YtcmsConfig config);

}