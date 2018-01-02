package com.yt.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
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
	public HttpEntity<?> add(@RequestBody Channel bar) {
		boolean created = channelService.save(bar);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.CREATED);
	}
	/**
	 * 按照id查询
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/find")
	@ApiOperation("按照id查询栏位")
	public HttpEntity<?> findById(@RequestParam Integer id) {
		Channel result = channelService.findById(id);
		HttpStatus status = result != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<Channel>(result, status);
	}

	/**
	 * 修改栏位
	 * @param userGroup
	 * @return
	 */
	@PutMapping("/update")
	@ApiOperation("修改栏位")
	public HttpEntity<?> update(@RequestBody Channel bar){
		boolean created = channelService.update(bar);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.OK);
	}
	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询栏位列表")
	public PageInfo<Channel> query(@RequestParam(required=false) String channelName,
			@RequestParam(required=false) Integer isUse,
			Page page){
		Channel bar = new Channel();
		bar.setChannelName(channelName);
		bar.setIsUse(isUse);
		List<Channel> list = channelService.queryAll(bar,page);
		return new PageInfo<Channel>(list);
	}
	/**
	 * 删除栏位
	 * @param id
	 * @return
	 */
	@PutMapping("/delete")
	@ApiOperation("删除栏位")
	public HttpEntity<?> delete(@RequestParam Integer id){
		boolean created = channelService.deleteLogicById(id);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.OK);
	}
	/**
	 * 按照栏目名查询
	 * @param id
	 * @return
	 */
	@GetMapping("/name")
	@ApiOperation("按照栏目名查询")
	public HttpEntity<?> findByChannelName(@RequestParam String channelName) {
		boolean result = channelService.findByChannelName(channelName);
		HttpStatus status = result == true ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<Boolean>(result, status);
	}
}
