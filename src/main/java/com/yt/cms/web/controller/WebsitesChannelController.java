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
import com.yt.cms.model.WebsitesChannel;
import com.yt.cms.service.WebsitesChannelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/websitesChannel")
@Api(value = "网站栏位关系服务")
public class WebsitesChannelController {
	@Autowired
	private WebsitesChannelService websitesChannelService;


	/**
	 * 新增网站栏位关系
	 * @param moduleList
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("添加网站栏位关系")
	public HttpEntity<?> add(@RequestBody List<WebsitesChannel> moduleList) {
		boolean created = websitesChannelService.save(moduleList);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.CREATED);
	}


	/**
	 * 修改网站栏位关系
	 * @param userGroup
	 * @return
	 */
	@PutMapping("/update")
	@ApiOperation("修改网站栏位关系")
	public HttpEntity<?> update(@RequestBody M2MUpdateBody body){
		Integer websitesId = body.getMasterId();
		Integer[] old_channelIds = body.getOld_slaveIds();
		Integer[] new_channelIds = body.getNew_slaveIds();
		boolean created = websitesChannelService.update(websitesId, old_channelIds, new_channelIds);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.OK);
	}

	
	
}
