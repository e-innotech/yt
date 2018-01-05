package com.yt.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.common.Page;
import com.yt.cms.model.UserLogs;

public interface UserLogsMapper {

    int insertSelective(UserLogs record);

    List<UserLogs> query(@Param("logs") UserLogs logs, @Param("page") Page page);
    
    long queryCount(UserLogs logs);
}