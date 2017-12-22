package com.yt.cms.service;

import java.util.List;

import com.yt.cms.model.UserLogs;

/**
 * 系统日志表日志添加，列表查询
 * 系统日志接口定义
 * @author admin
 *
 */
public interface UserLogsService {
	/**
	 * 新增日志
	 * 拦截器自动新增日志
	 * @param log
	 * @return
	 */
	public boolean save(UserLogs log);
	
	
	/**
	 * 按照log 参数查询
	 * 列表显示日志信息
	 * @return
	 */
	public List<UserLogs> queryAll(UserLogs log);
	
}
