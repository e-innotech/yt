package com.yt.cms.mapper;

import java.util.List;

import com.yt.cms.model.UserLogs;

public interface UserLogsMapper {

    int insertSelective(UserLogs record);

    List<UserLogs> query(UserLogs logs);
}