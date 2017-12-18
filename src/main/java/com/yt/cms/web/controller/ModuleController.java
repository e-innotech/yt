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
	@GetMapping("/{id}")
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
	@PostMapping
	@ApiOperation("添加模块")
	public HttpEntity<?> add(@RequestBody Module module) {
		boolean created = moduleService.save(module);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}
	/**
	 * 修改模块
	 * @param module
	 * @return
	 */
	@PutMapping
	@ApiOperation("修改模块")
	public HttpEntity<?> update(@RequestBody Module module){
		boolean created = moduleService.update(module);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}
	
	
}
