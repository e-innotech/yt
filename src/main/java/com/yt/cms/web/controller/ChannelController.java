package com.yt.cms.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yt.cms.annotations.LogAnnotation;
import com.yt.cms.common.AjaxResponseBody;
import com.yt.cms.common.Const;
import com.yt.cms.common.Page;
import com.yt.cms.common.PageInfo;
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
	@LogAnnotation(action="新增栏位")
	public AjaxResponseBody add(@Valid @RequestBody Channel channel,BindingResult result) {
		if(channel != null && channel.getChannelName() != null) {
			boolean isExists = channelService.findByChannelName(channel.getChannelName());
			if(isExists) {
				return new AjaxResponseBody(false,Const.FAILED,"该栏目名已经存在");
			}
		}
		
		boolean created = channelService.save(channel);
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
	@PostMapping("/update")
	@ApiOperation("修改栏位")
	@LogAnnotation(action="修改栏位")
	public AjaxResponseBody update(@Valid @RequestBody Channel channel,BindingResult result){
		if(channel != null && channel.getChannelName() != null) {
			boolean isExists = channelService.findByChannelName(channel.getChannelName());
			if(isExists) {
				return new AjaxResponseBody(false,Const.FAILED,"该栏目名已经存在");
			}
		}
		boolean created = channelService.update(channel);
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
	public AjaxResponseBody query(
			@RequestParam(required=false) String channelName,
			@RequestParam(required=false) Integer isUse,
			@RequestParam Integer pageNum,
			@RequestParam Integer pageSize){
		Channel channel = new Channel();
		channel.setChannelName(channelName);
		channel.setIsUse(isUse);
		long total = channelService.queryCount(channel);
		Page page = new Page(pageNum,pageSize);
		List<Channel> list = channelService.queryAll(channel,page);
		PageInfo<Channel> pageInfo =  new PageInfo<Channel>(pageNum,pageSize,total,list);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	}
	/**
	 * 删除栏位
	 * @param id
	 * @return
	 */
	@GetMapping("/delete")
	@ApiOperation("删除栏位")
	@LogAnnotation(action="删除栏位")
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
