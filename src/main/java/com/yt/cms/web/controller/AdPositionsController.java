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
import com.yt.cms.model.AdPositions;
import com.yt.cms.service.AdPositionsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/adPositions")
@Api(value = "广告位服务")
public class AdPositionsController {
	@Autowired
	private AdPositionsService adPositionsService;

	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询广告位列表")
	public List<AdPositions> query(){
		AdPositions adPositions = new AdPositions();
		return adPositionsService.queryAll(adPositions);
	}

	/**
	 * 按照id查询
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/find/{id}")
	@ApiOperation("按照id查询广告位")
	public HttpEntity<?> findById(@PathVariable Integer id) {
		AdPositions result = adPositionsService.findById(id);
		HttpStatus status = result != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<AdPositions>(result, status);
	}
	/**
	 * 新增广告位
	 * @param Ad
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("添加广告位")
	public HttpEntity<?> add(@RequestBody AdPositions adPositions) {
		boolean created = adPositionsService.save(adPositions);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}
	/**
	 * 修改广告位
	 * @param Ad
	 * @return
	 */
	@PutMapping("/update")
	@ApiOperation("修改广告位")
	public HttpEntity<?> update(@RequestBody AdPositions adPositions){
		boolean created = adPositionsService.update(adPositions);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}
	/**
	 * 删除广告位
	 * @param id
	 * @return
	 */
	@DeleteMapping("/delete/{id}")
	@ApiOperation("删除广告位")
	public HttpEntity<?> delete(@PathVariable Integer id){
		boolean created = adPositionsService.delete(id);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}
	

	
}
