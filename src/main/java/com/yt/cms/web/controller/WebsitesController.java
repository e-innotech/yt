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
import com.yt.cms.model.WebsiteTemplate;
import com.yt.cms.model.Websites;
import com.yt.cms.service.WebsitesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
// TODO add update 关系表数据校验
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
	@LogAnnotation(action="新增网站")
	public AjaxResponseBody add(@Valid @RequestBody Websites web, BindingResult result) {
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
	@PostMapping("/update")
	@ApiOperation("修改网站")
	@LogAnnotation(action="修改网站")
	public AjaxResponseBody update(@Valid @RequestBody Websites web,BindingResult result){
		boolean created = websitesService.update(web);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	/**
	 * 查询网站列表
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询网站列表")
	public AjaxResponseBody query(@RequestParam(required=false) String siteName,
			@RequestParam(required=false) Integer isUse,
			@RequestParam Integer pageNum,
			@RequestParam Integer pageSize){
		Websites web = new Websites();
		web.setIsUse(isUse);
		web.setSiteName(siteName);
		Page page = new Page(pageNum,pageSize);
		long total = websitesService.queryCount(web);
		List<Websites> list = websitesService.queryAll(web,page);
		PageInfo<Websites> pageInfo = new PageInfo<Websites>(pageNum,pageSize, total,list);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	}
	
	/**
	 * 查询网站模板配置列表
	 * @return
	 */
	@GetMapping("/template/query")
	@ApiOperation("查询网站模板配置列表")
	public AjaxResponseBody queryWebTemplate(@RequestParam(required=false) String siteName,
			@RequestParam(required=false) Integer templateType,
			@RequestParam Integer pageNum,
			@RequestParam Integer pageSize){
		WebsiteTemplate webTemplate = new WebsiteTemplate();
		webTemplate.setTemplateType(templateType);
		Websites web = new Websites();
		web.setSiteName(siteName);
		webTemplate.setWebsites(web);
		Page page = new Page(pageNum,pageSize);
		long total = websitesService.queryWebsiteTemplateCount(webTemplate);
		List<WebsiteTemplate> list = websitesService.queryWebsiteTemplate(webTemplate, page);
		PageInfo<WebsiteTemplate> pageInfo = new PageInfo<WebsiteTemplate>(pageNum,pageSize, total,list);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	}
	
	/**
	 * 删除网站
	 * @param id
	 * @return
	 */
	@GetMapping("/delete")
	@ApiOperation("删除网站")
	@LogAnnotation(action="删除网站")
	public AjaxResponseBody delete(@RequestParam Integer id){
		boolean created = websitesService.deleteLogicById(id);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	
}
