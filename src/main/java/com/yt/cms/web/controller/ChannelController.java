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
import com.yt.cms.model.Channel;
import com.yt.cms.service.ChannelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/channel")
@Api(value = "栏位服务")
public class ChannelController {
	@Autowired
	private ChannelService channelService;


	/**
	 * 新增栏位
	 * @param Channel
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("添加栏位")
	public AjaxResponseBody add(@RequestBody Channel bar) {
		boolean created = channelService.save(bar);
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
	@ApiOperation("按照id查询栏位")
	public AjaxResponseBody findById(@RequestParam Integer id) {
		Channel result = channelService.findById(id);
		return new AjaxResponseBody(true,Const.SUCCESS,result);
	}

	/**
	 * 修改栏位
	 * @param userGroup
	 * @return
	 */
	@PutMapping("/update")
	@ApiOperation("修改栏位")
	public AjaxResponseBody update(@RequestBody Channel bar){
		boolean created = channelService.update(bar);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询栏位列表")
	public AjaxResponseBody query(@RequestParam(required=false) String channelName,
			@RequestParam(required=false) Integer isUse,
			Page page){
		Channel bar = new Channel();
		bar.setChannelName(channelName);
		bar.setIsUse(isUse);
		List<Channel> list = channelService.queryAll(bar,page);
		PageInfo<Channel> pageInfo =  new PageInfo<Channel>(list);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	}
	/**
	 * 删除栏位
	 * @param id
	 * @return
	 */
	@PutMapping("/delete")
	@ApiOperation("删除栏位")
	public AjaxResponseBody delete(@RequestParam Integer id){
		boolean created = channelService.deleteLogicById(id);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	/**
	 * 按照栏目名查询
	 * @param id
	 * @return
	 */
	@GetMapping("/find/name")
	@ApiOperation("按照栏目名查询")
	public AjaxResponseBody findByChannelName(@RequestParam String channelName) {
		boolean result = channelService.findByChannelName(channelName);
		return new AjaxResponseBody(true,Const.SUCCESS,result);
	}
}
