package com.yt.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yt.cms.model.UserLogs;
import com.yt.cms.service.UserLogsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/log")
@Api(value = "配置服务")
public class UserLogController {
	@Autowired
	private UserLogsService userLogService;

	
	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询系统配置列表")
	public List<UserLogs> query(){
		UserLogs log = new UserLogs();
		return userLogService.queryAll(log);
	}

	
}
