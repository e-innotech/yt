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
import com.yt.cms.model.UserGroupRoles;
import com.yt.cms.service.UserGroupRolesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/userGroupRoles")
@Api(value = "用户组角色关系服务")
public class UserGroupRolesController {
	@Autowired
	private UserGroupRolesService userGroupRolesService;

	/**
	 * 给用户组赋予角色
	 * @param rolesResource
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("给用户组赋予角色")
	public HttpEntity<?> add(@RequestBody UserGroupRoles userGroupRoles) {
		boolean created = userGroupRolesService.save(userGroupRoles);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}
	/**
	 * 修改用户组的角色
	 * @param rolesResource
	 * @return
	 */
	@PutMapping("/update")
	@ApiOperation("修改用户组的角色")
	public HttpEntity<?> update(@RequestBody UserGroupRoles userGroupRoles){
		boolean created = userGroupRolesService.update(userGroupRoles);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}
	
	
}
