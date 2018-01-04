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
import com.yt.cms.model.Websites;
import com.yt.cms.service.WebsitesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/websites")
@Api(value = "网站服务")
public class WebsitesController {
	@Autowired
	private WebsitesService websitesService;


	/**
	 * 新增网站
	 * @param Websites
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("添加网站")
	public AjaxResponseBody add(@RequestBody Websites web) {
		boolean created = websitesService.save(web);
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
	@ApiOperation("按照id查询网站")
	public AjaxResponseBody findById(@RequestParam Integer id) {
		Websites result = websitesService.findById(id);
		return new AjaxResponseBody(true,Const.SUCCESS,result);
	}

	/**
	 * 修改网站
	 * @param userGroup
	 * @return
	 */
	@PutMapping("/update")
	@ApiOperation("修改网站")
	public AjaxResponseBody update(@RequestBody Websites web){
		boolean created = websitesService.update(web);
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
	@ApiOperation("查询网站列表")
	public AjaxResponseBody query(@RequestParam(required=false) String siteName,
			@RequestParam(required=false) Integer isUse,Page page){
		Websites web = new Websites();
		web.setIsUse(isUse);
		web.setSiteName(siteName);
		List<Websites> list = websitesService.queryAll(web,page);
		PageInfo<Websites> pageInfo = new PageInfo<Websites>(list);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	}
	/**
	 * 删除网站
	 * @param id
	 * @return
	 */
	@PutMapping("/delete")
	@ApiOperation("删除网站")
	public AjaxResponseBody delete(@RequestParam Integer id){
		boolean created = websitesService.deleteLogicById(id);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	
}
