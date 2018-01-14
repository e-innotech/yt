package com.yt.cms.front.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yt.cms.common.AjaxResponseBody;
import com.yt.cms.common.Const;
import com.yt.cms.common.Page;
import com.yt.cms.common.PageInfo;
import com.yt.cms.front.model.Article;
import com.yt.cms.front.service.WebsiteChannelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 网站栏目页请求
 * @author admin
 *
 */
@RestController
@RequestMapping("/web/channel")
@Api(value = "网站栏目接口",description = "网站栏目页服务")
public class WebsiteChannelController {
	@Autowired
	private WebsiteChannelService websiteChannelService;
	/**
	 * 查询网站栏目
	 * @return
	 */
	@GetMapping("/query/{websiteId}/{channelId}")
	@ApiOperation("查询网站栏目")
	public AjaxResponseBody query(@PathVariable Integer websiteId,@PathVariable Integer channelId,
			@RequestParam Integer pageNum, 
			@RequestParam Integer pageSize){
		long total = websiteChannelService.getWebsiteChannelNewsCount(websiteId, channelId);
		Page page = new Page(pageNum,pageSize);
		List<Article> list = websiteChannelService.getWebsiteChannelNews(websiteId, channelId, page);
		PageInfo<Article> pageInfo = new PageInfo<Article>(pageNum,pageSize,total,list);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	}
	
	
}
