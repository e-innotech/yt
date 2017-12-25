package com.yt.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	 * 新增角色
	 * @param roles
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("添加角色")
	public HttpEntity<?> add(@RequestBody Roles roles) {
		boolean created = rolesService.save(roles);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.CREATED);
	}
	/**
	 * 查询角色id对应的资源数据
	 * @param id
	 * @return
	 */
	@GetMapping("/queryResources/{id}")
	@ApiOperation("查询角色id对应的资源数据")
	public HttpEntity<?> findById(@PathVariable Integer id) {
		Roles result = rolesService.findById(id);
		HttpStatus status = result != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<Roles>(result, status);
	}

	/**
	 * 修改角色
	 * @param userGroup
	 * @return
	 */
	@PutMapping("/update")
	@ApiOperation("修改角色")
	public HttpEntity<?> update(@RequestBody Roles roles){
		boolean created = rolesService.update(roles);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.OK);
	}
	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询角色列表")
	public List<Roles> query(Roles roles){
		return rolesService.find(roles);
	}
	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	@DeleteMapping("/delete/{id}")
	@ApiOperation("删除角色")
	public HttpEntity<?> delete(@PathVariable Integer id){
		boolean created = rolesService.delete(id);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.OK);
	}
	/**
	 * 删除角色下的所有资源
	 * @param id
	 * @return
	 */
	@DeleteMapping("/resource/{rolesId}")
	@ApiOperation("删除角色下的所有资源")
	public HttpEntity<?> deleteRolesResource(@PathVariable Integer rolesId){
		boolean created = rolesService.removeRolesResource(rolesId);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.OK);
	}
	
}
