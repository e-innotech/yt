package com.yt.cms.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yt.cms.common.AjaxResponseBody;
import com.yt.cms.common.CollectionUtils;
import com.yt.cms.common.Const;
import com.yt.cms.common.PageInfo;
import com.yt.cms.model.Roles;
import com.yt.cms.model.RolesResource;
import com.yt.cms.model.page.RolesPage;
import com.yt.cms.service.RolesResourceService;
import com.yt.cms.service.RolesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/role")
@Api(value = "角色服务")
public class RoleController {
	@Autowired
	private RolesService rolesService;
	@Autowired
	private RolesResourceService rolesResourceService;

	/**
	 * 新增角色
	 * @param roles
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("添加角色")
	public AjaxResponseBody add(@RequestBody Roles roles) {
		try {
			rolesService.save(roles);
			addRolesResource(roles);
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	/**
	 * 查询角色id对应的资源数据
	 * @param id
	 * @return
	 */
	@GetMapping("/find/id")
	@ApiOperation("查询角色id对应的资源数据")
	public AjaxResponseBody findById(@RequestParam Integer id) {
		Roles result = rolesService.findById(id);
		return new AjaxResponseBody(true,Const.SUCCESS,result);
	}

	/**
	 * 修改角色
	 * @param userGroup
	 * @return
	 */
	@PutMapping("/update")
	@ApiOperation("修改角色")
	public AjaxResponseBody update(@RequestBody Roles roles){
		try {
			rolesService.update(roles);
			// 删除角色资源关系
			rolesService.removeRolesResource(roles.getId());
			// 新增角色资源关系
			addRolesResource(roles);
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	private void addRolesResource(Roles roles) {
		List<Integer> resourceIds = roles.getResourceIds();
		if(CollectionUtils.isNotEmpty(resourceIds)) {
			// 角色资源关系
			List<RolesResource> rolesResource = new ArrayList<>();
			for(Integer resourceId : resourceIds) {
				RolesResource rr = new RolesResource();
				rr.setResourceId(resourceId);
				rr.setRolesId(roles.getId());
				rolesResource.add(rr);
			}
			rolesResourceService.save(rolesResource);
		}
	}
	/**
	 * 原始分页写法，mybatis resultMap 有折叠情况分页也不对
	 * 列表页面
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询角色列表")
	public AjaxResponseBody query(@RequestParam(required=false) String roleName,
			@RequestParam Integer pageNum,@RequestParam Integer pageSize){
		RolesPage roles = new RolesPage();
		roles.setRoleName(roleName);
		roles.setPageNum(pageNum);
		roles.setPageSize(pageSize);
		int total = rolesService.findCount(roles);
		List<Roles> list = rolesService.find(roles);
		PageInfo<Roles> pageInfo = new PageInfo<Roles>();
		pageInfo.setList(list);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(total);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	}
	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	@PutMapping("/delete")
	@ApiOperation("删除角色")
	public AjaxResponseBody delete(@RequestParam Integer id){
		boolean created = rolesService.deleteLogicById(id);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	/**
	 * 删除角色下的所有资源
	 * @param id
	 * @return
	 */
/*	@DeleteMapping("/resource")
	@ApiOperation("删除角色下的所有资源")
	public HttpEntity<?> deleteRolesResource(@RequestParam Integer rolesId){
		boolean created = rolesService.removeRolesResource(rolesId);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.OK);
	}*/
	
}
