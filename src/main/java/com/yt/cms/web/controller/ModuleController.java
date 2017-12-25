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
import com.yt.cms.model.Module;
import com.yt.cms.service.ModuleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/module")
@Api(value = "模块服务")
public class ModuleController {
	@Autowired
	private ModuleService moduleService;

	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询系统模块列表")
	public List<Module> query(){
		Module module = new Module();
		return moduleService.queryAll(module);
	}

	/**
	 * 按照id查询
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/find/{id}")
	@ApiOperation("按照id查询模块")
	public HttpEntity<?> findById(@PathVariable Integer id) {
		Module result = moduleService.findById(id);
		HttpStatus status = result != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<Module>(result, status);
	}
	/**
	 * 新增模块
	 * @param module
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("添加模块")
	public HttpEntity<?> add(@RequestBody Module module) {
		boolean created = moduleService.save(module);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.CREATED);
	}
	/**
	 * 修改模块
	 * @param module
	 * @return
	 */
	@PutMapping("/update")
	@ApiOperation("修改模块")
	public HttpEntity<?> update(@RequestBody Module module){
		boolean created = moduleService.update(module);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.OK);
	}
	/**
	 * 删除模块
	 * @param id
	 * @return
	 */
	@DeleteMapping("/delete/{id}")
	@ApiOperation("删除模块")
	public HttpEntity<?> delete(@PathVariable Integer id){
		boolean created = moduleService.delete(id);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.OK);
	}
	
	/**
	 * 按照parentId查询所有下属模块
	 * @return
	 */
	@GetMapping("/queryByParentId/{parentId}")
	@ApiOperation("查询系统模块列表")
	public List<Module> queryByParentId(@PathVariable Integer parentId){
		return moduleService.queryByParentId(parentId);
	}

	
}
