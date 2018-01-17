package com.yt.cms.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yt.cms.common.AjaxResponseBody;
import com.yt.cms.common.Const;
import com.yt.cms.common.Page;
import com.yt.cms.common.PageInfo;
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
	public AjaxResponseBody query(@RequestParam(required=false) String userName,
			@RequestParam(required=false) String resourceName,
			@RequestParam(required=false) String action,
			@RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
			@RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
			@RequestParam Integer pageNum,
			@RequestParam Integer pageSize){
		UserLogs log = new UserLogs();
		log.setAction(action);
		log.setEndDate(endDate);
		log.setStartDate(startDate);
		log.setResourceName(resourceName);
		log.setUserName(userName);
		Page page = new Page(pageNum,pageSize);
		long total = userLogService.queryCount(log);
		List<UserLogs> logList = userLogService.queryAll(log,page);
		PageInfo<UserLogs> pageInfo = new PageInfo<UserLogs>(pageNum,pageSize, total,logList);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	}

	
}
