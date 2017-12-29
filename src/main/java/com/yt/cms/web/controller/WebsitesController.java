package com.yt.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yt.cms.common.Const;
import com.yt.cms.common.Page;
import com.yt.cms.model.Websites;
import com.yt.cms.service.WebsitesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/websites")
@Api(value = "网站服务")
public class WebsitesController {
	@Autowired
	private WebsitesService websitesService;


	/**
	 * 新增网站
	 * @param Websites
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("添加网站")
	public HttpEntity<?> add(@RequestBody Websites web) {
		boolean created = websitesService.save(web);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.CREATED);
	}
	/**
	 * 按照id查询
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/find")
	@ApiOperation("按照id查询网站")
	public HttpEntity<?> findById(@RequestParam Integer id) {
		Websites result = websitesService.findById(id);
		HttpStatus status = result != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<Websites>(result, status);
	}

	/**
	 * 修改网站
	 * @param userGroup
	 * @return
	 */
	@PutMapping("/update")
	@ApiOperation("修改网站")
	public HttpEntity<?> update(@RequestBody Websites web){
		boolean created = websitesService.update(web);
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
	@ApiOperation("查询网站列表")
	public PageInfo<Websites> query(@RequestParam(required=false) String siteName,
			@RequestParam(required=false) Integer isUse,Page page){
		Websites web = new Websites();
		web.setIsUse(isUse);
		web.setSiteName(siteName);
		List<Websites> list = websitesService.queryAll(web,page);
		return new PageInfo<Websites>(list);
	}
	/**
	 * 删除网站
	 * @param id
	 * @return
	 */
	@DeleteMapping("/delete")
	@ApiOperation("删除网站")
	public HttpEntity<?> delete(@RequestParam Integer id){
		boolean created = websitesService.delete(id);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.OK);
	}
	
}
