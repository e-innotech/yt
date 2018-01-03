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
	public PageInfo<Resource> query(@RequestParam(required=false) String resourceName,
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
		return new PageInfo<Resource>(list);
	}

	/**
	 * 按照id查询
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/find/id")
	@ApiOperation("按照id查询系统资源")
	public HttpEntity<?> findById(@RequestParam Integer id) {
		Resource result = resourceService.findById(id);
		HttpStatus status = result != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<Resource>(result, status);
	}
	/**
	 * 新增系统资源
	 * @param resource
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("添加系统资源")
	public HttpEntity<?> add(@RequestBody Resource resource) {
		boolean created = resourceService.save(resource);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.CREATED);
	}
	/**
	 * 修改系统资源
	 * @param resource
	 * @return
	 */
	@PutMapping("/update")
	@ApiOperation("修改系统资源")
	public HttpEntity<?> update(@RequestBody Resource resource){
		boolean created = resourceService.update(resource);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.OK);
	}
	
	/**
	 * 删除系统资源
	 * @param id
	 * @return
	 */
	@PutMapping("/delete")
	@ApiOperation("删除系统资源")
	public HttpEntity<?> delete(@RequestParam Integer id){
		boolean created = resourceService.deleteLogicById(id);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.OK);
	}
	
}
