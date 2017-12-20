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

import com.yt.cms.common.AjaxResponseBody;
import com.yt.cms.common.Const;
import com.yt.cms.model.NavigationBar;
import com.yt.cms.service.NavigationBarService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/navigation")
@Api(value = "栏位服务")
public class NavigationBarController {
	@Autowired
	private NavigationBarService navigationBarService;


	/**
	 * 新增栏位
	 * @param NavigationBar
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("添加栏位")
	public HttpEntity<?> add(@RequestBody NavigationBar bar) {
		boolean created = navigationBarService.save(bar);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}
	/**
	 * 按照id查询
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/find/{id}")
	@ApiOperation("按照id查询栏位")
	public HttpEntity<?> findById(@PathVariable Integer id) {
		NavigationBar result = navigationBarService.findById(id);
		HttpStatus status = result != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<NavigationBar>(result, status);
	}

	/**
	 * 修改栏位
	 * @param userGroup
	 * @return
	 */
	@PutMapping("/update")
	@ApiOperation("修改栏位")
	public HttpEntity<?> update(@RequestBody NavigationBar bar){
		boolean created = navigationBarService.update(bar);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}
	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询栏位列表")
	public List<NavigationBar> query(NavigationBar bar){
		return navigationBarService.queryAll(bar);
	}
	/**
	 * 删除栏位
	 * @param id
	 * @return
	 */
	@DeleteMapping("/delete/{id}")
	@ApiOperation("删除栏位")
	public HttpEntity<?> delete(@PathVariable Integer id){
		boolean created = navigationBarService.delete(id);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}
	
}
