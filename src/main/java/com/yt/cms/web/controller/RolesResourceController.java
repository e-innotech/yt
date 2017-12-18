package com.yt.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yt.cms.common.AjaxResponseBody;
import com.yt.cms.common.Const;
import com.yt.cms.model.RolesResource;
import com.yt.cms.service.RolesResourceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rolesResource")
@Api(value = "角色资源关系服务")
public class RolesResourceController {
	@Autowired
	private RolesResourceService rolesResourceService;

	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询角色资源关系列表")
	public List<RolesResource> query(){
		RolesResource rolesResource = new RolesResource();
		return rolesResourceService.find(rolesResource);
	}

	/**
	 * 按照id查询
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	@ApiOperation("按照id查询角色资源关系")
	public HttpEntity<?> findById(@PathVariable Integer id) {
		RolesResource result = rolesResourceService.findById(id);
		HttpStatus status = result != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<RolesResource>(result, status);
	}
	/**
	 * 新增角色资源关系
	 * @param rolesResource
	 * @return
	 */
	@PostMapping
	@ApiOperation("添加角色资源关系")
	public HttpEntity<?> add(@RequestBody RolesResource rolesResource) {
		boolean created = rolesResourceService.save(rolesResource);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}
	/**
	 * 修改角色资源关系
	 * @param rolesResource
	 * @return
	 */
	@PutMapping
	@ApiOperation("修改角色资源关系")
	public HttpEntity<?> update(@RequestBody RolesResource rolesResource){
		boolean created = rolesResourceService.update(rolesResource);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}
	
	
}
