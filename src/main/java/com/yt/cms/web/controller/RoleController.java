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
import com.yt.cms.model.Roles;
import com.yt.cms.service.RolesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/role")
@Api(value = "角色服务")
public class RoleController {
	@Autowired
	private RolesService rolesService;

	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询角色列表")
	public List<Roles> query(){
		return rolesService.queryAll();
	}

	/**
	 * 按照id查询
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	@ApiOperation("按照id查询角色")
	public HttpEntity<?> findById(@PathVariable Integer id) {
		Roles result = rolesService.findById(id);
		HttpStatus status = result != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<Roles>(result, status);
	}
	/**
	 * 新增角色
	 * @param roles
	 * @return
	 */
	@PostMapping
	@ApiOperation("添加角色")
	public HttpEntity<?> add(@RequestBody Roles roles) {
		boolean created = rolesService.save(roles);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}
	/**
	 * 修改角色
	 * @param userGroup
	 * @return
	 */
	@PutMapping
	@ApiOperation("修改角色")
	public HttpEntity<?> update(@RequestBody Roles roles){
		boolean created = rolesService.update(roles);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}
	
	
}
