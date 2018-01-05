package com.yt.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yt.cms.common.AjaxResponseBody;
import com.yt.cms.common.Const;
import com.yt.cms.model.Resource;
import com.yt.cms.service.ResourceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/resource")
@Api(value = "资源服务")
public class ResourceController {
	@Autowired
	private ResourceService resourceService;

	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询系统资源列表")
	
	public AjaxResponseBody query(){
		List<Resource> list =  resourceService.find();
		return new AjaxResponseBody(true,Const.SUCCESS,list);
	}
	/*public AjaxResponseBody query(@RequestParam(required=false) String resourceName,
			@RequestParam(required=false) String uri,
			@RequestParam(required=false) Integer rw,
			@RequestParam(required=false) Integer isMenu,
			Page page){
		Resource resource = new Resource();
		resource.setIsMenu(isMenu);
		resource.setResourceName(resourceName);
		resource.setRw(rw);
		resource.setUri(uri);
		
		List<Resource> list =  resourceService.find(resource,page);
		PageInfo<Resource> pageInfo =  new PageInfo<Resource>(list);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	}*/

	/**
	 * 按照id查询
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/find/id")
	@ApiOperation("按照id查询系统资源")
	public AjaxResponseBody findById(@RequestParam Integer id) {
		Resource result = resourceService.findById(id);
		return new AjaxResponseBody(true,Const.SUCCESS,result);
	}
	/**
	 * 新增系统资源
	 * @param resource
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("添加系统资源")
	public AjaxResponseBody add(@RequestBody Resource resource) {
		boolean created = resourceService.save(resource);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	/**
	 * 修改系统资源
	 * @param resource
	 * @return
	 */
	@PostMapping("/update")
	@ApiOperation("修改系统资源")
	public AjaxResponseBody update(@RequestBody Resource resource){
		boolean created = resourceService.update(resource);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	
	/**
	 * 删除系统资源
	 * @param id
	 * @return
	 */
	@GetMapping("/delete")
	@ApiOperation("删除系统资源")
	public AjaxResponseBody delete(@RequestParam Integer id){
		boolean created = resourceService.deleteLogicById(id);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	
}
