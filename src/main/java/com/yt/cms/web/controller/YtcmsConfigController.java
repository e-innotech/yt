package com.yt.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yt.cms.common.AjaxResponseBody;
import com.yt.cms.common.Const;
import com.yt.cms.common.Page;
import com.yt.cms.model.YtcmsConfig;
import com.yt.cms.service.YtcmsConfigService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/config")
@Api(value = "配置服务")
public class YtcmsConfigController {
	@Autowired
	private YtcmsConfigService configService;
	/**
	 * 新增配置
	 * @param module
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("添加配置")
	public AjaxResponseBody add(@RequestBody YtcmsConfig config) {
		boolean created = configService.save(config);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	

	/**
	 * 按照id查询
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/find/id")
	@ApiOperation("按照id查询配置")
	public AjaxResponseBody findById(@RequestParam Integer id) {
		YtcmsConfig result = configService.findById(id);
		return new AjaxResponseBody(true,Const.SUCCESS,result);
	}
	
	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询系统配置列表")
	public AjaxResponseBody query(@RequestParam(required=false) String name, // 配置名称
			@RequestParam(required=false) String value, //配置值
			@RequestParam(required=false) Integer isUse,
			Page page){
		YtcmsConfig config = new YtcmsConfig();
		config.setIsUse(isUse);
		config.setName(name);
		config.setValue(value);
		List<YtcmsConfig> list = configService.queryAll(config,page);
		PageInfo<YtcmsConfig> pageInfo = new PageInfo<YtcmsConfig>(list);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	}

	
	/**
	 * 修改配置
	 * @param module
	 * @return
	 */
	@PutMapping("/update")
	@ApiOperation("修改配置")
	public AjaxResponseBody update(@RequestBody YtcmsConfig config){
		boolean created = configService.update(config);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	

	
}
