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

import com.yt.cms.common.AjaxResponseBody;
import com.yt.cms.common.Const;
import com.yt.cms.model.WebsitesBarBody;
import com.yt.cms.model.WebsitesNavigationBar;
import com.yt.cms.service.WebsitesNavigationBarService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/websitesBar")
@Api(value = "网站栏位关系服务")
public class WebsitesNavigationBarController {
	@Autowired
	private WebsitesNavigationBarService websitesNavigationBarService;


	/**
	 * 新增网站栏位关系
	 * @param NavigationBar
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("添加网站栏位关系")
	public HttpEntity<?> add(@RequestBody List<WebsitesNavigationBar> moduleList) {
		boolean created = websitesNavigationBarService.save(moduleList);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}


	/**
	 * 修改网站栏位关系
	 * @param userGroup
	 * @return
	 */
	@PutMapping("/update")
	@ApiOperation("修改网站栏位关系")
	public HttpEntity<?> update(@RequestBody WebsitesBarBody body){
		Integer websitesId = body.getWebsitesId();
		Integer[] old_navigationBarIds = body.getOld_navigationBarIds();
		Integer[] new_navigationBarIds = body.getNew_navigationBarIds();
		boolean created = websitesNavigationBarService.update(websitesId, old_navigationBarIds, new_navigationBarIds);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}

	
	
}
