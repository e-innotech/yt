package com.yt.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yt.cms.common.Const;
import com.yt.cms.model.M2MUpdateBody;
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
	public HttpEntity<?> add(@RequestBody List<RolesResource> rolesResource) {
		boolean created = rolesResourceService.save(rolesResource);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.CREATED);
	}

	/**
	 * 修改角色资源关系
	 * @param rolesResource
	 * @return
	 */
	@PutMapping("/update")
	@ApiOperation("修改角色资源关系")
	public HttpEntity<?> update(@RequestBody M2MUpdateBody body){
		Integer rolesId = body.getMasterId();
		Integer[] old_resourceIds = body.getOld_slaveIds();
		Integer[] new_resourceIds = body.getNew_slaveIds();
		boolean created = rolesResourceService.update(rolesId, old_resourceIds, new_resourceIds);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.CREATED);
	}

	
}
