package com.yt.cms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	 * 新增角色资源关系
	 * @param rolesResource
	 * @return
	 */
	@PostMapping("/add")
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
	@PutMapping("/update")
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
