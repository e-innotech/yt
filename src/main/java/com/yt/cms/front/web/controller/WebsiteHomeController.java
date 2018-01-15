package com.yt.cms.front.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yt.cms.common.AjaxResponseBody;
import com.yt.cms.common.Const;
import com.yt.cms.common.Page;
import com.yt.cms.common.PageInfo;
import com.yt.cms.front.model.Article;
import com.yt.cms.front.service.WebsiteHomeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 网站首页请求接口
 * @author admin
 *
 */
@RestController
@Api(value = "网站首页服务",description = "网站首页服务")
public class WebsiteHomeController {
	@Autowired
	private WebsiteHomeService websiteHomeService;
/*	@GetMapping("/index")
	String index(Model model) {
		model.addAttribute("msg", "Spring Boot AJAX Example,hhhhh");
//		return "yynews/html/index";
		return "yynews/html/index";
	}*/
	/**
	 * 网站首页接口
	 * @param websiteId
	 * @param homeWeight
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/home/{websiteId}/{homeWeight}")
	@ApiOperation("查询网站首页权重内容")
	public AjaxResponseBody query(@PathVariable Integer websiteId,
			@PathVariable Integer homeWeight,
			@RequestParam Integer pageNum, 
			@RequestParam Integer pageSize){
	
		Page page = new Page(pageNum,pageSize);
		long total = websiteHomeService.getWebsiteHomeByWeightCount(websiteId, homeWeight);
		List<Article> list = websiteHomeService.getWebsiteHomeByWeight(websiteId, homeWeight, page);
		PageInfo<Article> pageInfo = new PageInfo<Article>(pageNum,pageSize,total,list);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	}
}
