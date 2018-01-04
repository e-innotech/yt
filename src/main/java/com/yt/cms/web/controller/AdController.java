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
import com.yt.cms.annotations.LogAnnotation;
import com.yt.cms.common.AjaxResponseBody;
import com.yt.cms.common.Const;
import com.yt.cms.common.Page;
import com.yt.cms.model.Ad;
import com.yt.cms.service.AdService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ad")
@Api(value = "广告服务")
public class AdController {
	@Autowired
	private AdService adService;

	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询广告列表")
	public AjaxResponseBody query(@RequestParam(required=false) String adName, // 广告名称
			@RequestParam(required=false) Integer status, // 广告上下 线状态
			@RequestParam(required=false) Integer adType, // 广告类型  0 图片，1 ，视频 
			Page page){
		Ad ad = new Ad();
		ad.setAdName(adName);
		ad.setStatus(status);
		ad.setAdType(adType);
		List<Ad> list = adService.queryAll(ad, page);
		PageInfo<Ad> pageInfo =  new PageInfo<Ad>(list);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	}

	/**
	 * 按照id查询
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/find/id")
	@ApiOperation("按照id查询广告")
	public AjaxResponseBody findById(@RequestParam Integer id) {
		Ad result = adService.findById(id);
		return new AjaxResponseBody(true,Const.SUCCESS, result);
	}
	/**
	 * 新增广告
	 * @param Ad
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("添加广告")
	@LogAnnotation(action="新增广告")
	public AjaxResponseBody add(@RequestBody Ad Ad) {
		boolean created = adService.save(Ad);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	/**
	 * 修改广告
	 * @param Ad
	 * @return
	 */
	@PutMapping("/update")
	@ApiOperation("修改广告")
	@LogAnnotation(action="修改广告")
	public AjaxResponseBody update(@RequestBody Ad ad){
		boolean created = adService.update(ad);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	/**
	 * 删除广告
	 * @param id
	 * @return
	 */
	@PutMapping("/delete")
	@ApiOperation("删除广告")
	@LogAnnotation(action="删除广告")
	public AjaxResponseBody delete(@RequestParam Integer id){
		boolean created = adService.deleteLogicById(id);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	
}
