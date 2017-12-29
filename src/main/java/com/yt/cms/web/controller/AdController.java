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
import com.yt.cms.model.Ad;
import com.yt.cms.service.AdService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ad")
@Api(value = "广告服务")
public class AdController {
	@Autowired
	private AdService adService;

	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询广告列表")
	public PageInfo<Ad> query(@RequestParam(required=false) String adName, // 广告名称
			@RequestParam(required=false) Integer status, // 广告上下 线状态
			@RequestParam(required=false) Integer adType, // 广告类型  0 图片，1 ，视频 
			Page page){
		Ad ad = new Ad();
		ad.setAdName(adName);
		ad.setStatus(status);
		ad.setAdType(adType);
		List<Ad> list = adService.queryAll(ad, page);
		return new PageInfo<Ad>(list);
	}

	/**
	 * 按照id查询
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/find")
	@ApiOperation("按照id查询广告")
	public HttpEntity<?> findById(@RequestParam Integer id) {
		Ad result = adService.findById(id);
		HttpStatus status = result != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<Ad>(result, status);
	}
	/**
	 * 新增广告
	 * @param Ad
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("添加广告")
	public HttpEntity<?> add(@RequestBody Ad Ad) {
		boolean created = adService.save(Ad);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.CREATED);
	}
	/**
	 * 修改广告
	 * @param Ad
	 * @return
	 */
	@PutMapping("/update")
	@ApiOperation("修改广告")
	public HttpEntity<?> update(@RequestBody Ad ad){
		boolean created = adService.update(ad);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.OK);
	}
	/**
	 * 删除广告
	 * @param id
	 * @return
	 */
	@DeleteMapping("/delete")
	@ApiOperation("删除广告")
	public HttpEntity<?> delete(@RequestParam Integer id){
		boolean created = adService.delete(id);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.OK);
	}
	
}
