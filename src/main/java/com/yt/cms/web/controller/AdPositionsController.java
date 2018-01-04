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
import com.yt.cms.model.AdPositions;
import com.yt.cms.service.AdPositionsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/adPositions")
@Api(value = "广告位服务")
public class AdPositionsController {
	@Autowired
	private AdPositionsService adPositionsService;

	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询广告位列表")
	public AjaxResponseBody query(@RequestParam(required=false) String adName, // 广告位名称
			@RequestParam(required=false) Integer isUse,
			Page page){
		AdPositions adPositions = new AdPositions();
		adPositions.setAdName(adName);
		adPositions.setIsUse(isUse);
		List<AdPositions> list =  adPositionsService.queryAll(adPositions,page);
		PageInfo<AdPositions> pageInfo = new PageInfo<AdPositions>(list);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	}

	/**
	 * 按照id查询
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/find/id")
	@ApiOperation("按照id查询广告位")
	public AjaxResponseBody findById(@RequestParam Integer id) {
		AdPositions result = adPositionsService.findById(id);
		return new AjaxResponseBody(true,Const.SUCCESS,result);
	}
	/**
	 * 新增广告位
	 * @param Ad
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("添加广告位")
	public AjaxResponseBody add(@RequestBody AdPositions adPositions) {
		boolean created = adPositionsService.save(adPositions);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	/**
	 * 修改广告位
	 * @param Ad
	 * @return
	 */
	@PutMapping("/update")
	@ApiOperation("修改广告位")
	public AjaxResponseBody update(@RequestBody AdPositions adPositions){
		boolean created = adPositionsService.update(adPositions);
		if(!created) {
			return new AjaxResponseBody(true,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	/**
	 * 删除广告位
	 * @param id
	 * @return
	 */
	@PutMapping("/delete")
	@ApiOperation("删除广告位")
	public AjaxResponseBody delete(@RequestParam Integer id){
		boolean created = adPositionsService.deleteLogicById(id);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	

	
}
