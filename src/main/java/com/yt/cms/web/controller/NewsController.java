package com.yt.cms.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yt.cms.common.AjaxResponseBody;
import com.yt.cms.common.Const;
import com.yt.cms.common.DateUtil;
import com.yt.cms.common.Page;
import com.yt.cms.common.PageInfo;
import com.yt.cms.model.AduitNews;
import com.yt.cms.model.News;
import com.yt.cms.model.NewsLaunch;
import com.yt.cms.model.NewsPublish;
import com.yt.cms.model.NewsPublishLine;
import com.yt.cms.model.User;
import com.yt.cms.service.NewsLaunchService;
import com.yt.cms.service.NewsPublishService;
import com.yt.cms.service.NewsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/news")
@Api(value = "稿件服务")
public class NewsController {
	@Autowired
	private NewsService newsService;
	@Autowired
	private NewsLaunchService newsLaunchService;
	@Autowired
	private NewsPublishService newsPublishService;
	
	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询稿件列表")
	public AjaxResponseBody query(@RequestParam(required=false) String newsTitle,
			@RequestParam(required=false) String source,
			@RequestParam(required=false) Date startDate,
			@RequestParam(required=false) Date endDate,
			@RequestParam Integer pageNum,
			@RequestParam Integer pageSize){
		News news = new News();
		news.setNewsTitle(newsTitle);
		news.setSource(source);
		news.setStartDate(startDate);
		news.setEndDate(endDate);
		Page page = new Page(pageNum,pageSize);
		long total = newsService.queryCount(news);
		List<News> list = newsService.queryAll(news,page);
		PageInfo<News> pageInfo = new PageInfo<News>(pageNum, pageSize,total,list);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	}

	/**
	 * 按照id查询
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/find/id")
	@ApiOperation("按照id查询稿件")
	public AjaxResponseBody findById(@RequestParam Integer id) {
		News result = newsService.findById(id);
		return new AjaxResponseBody(true,Const.SUCCESS,result);
	}
	/**
	 * 新增稿件
	 * @param News
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("添加稿件")
	public AjaxResponseBody add(@RequestBody News News) {
		boolean created = newsService.save(News);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	/**
	 * 修改稿件
	 * @param News
	 * @return
	 */
	@PostMapping("/update")
	@ApiOperation("修改稿件")
	public AjaxResponseBody update(@RequestBody News News){
		// TODO 稿件上线状态不可修改，需要关联publish，launch 表拿到状态
		boolean created = newsService.update(News);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	/**
	 * 不能删除稿件
	 * @param id
	 * @return
	 */
/*	@GetMapping("/delete")
	@ApiOperation("删除稿件")
	public AjaxResponseBody delete(@RequestParam Integer id){
		boolean created = newsService.deleteLogicById(id);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}*/
	
	/**
	 * 投放稿件到网站和栏目
	 * @return
	 */
	@PostMapping("/launch/add")
	@ApiOperation("投放稿件到网站和栏目")
	public AjaxResponseBody launch(@RequestBody NewsLaunch newsLaunch){
		boolean release =  newsLaunchService.save(newsLaunch);
		if(!release) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}


	/**
	 * 编辑稿件投放网站与栏目
	 * @return
	 */
	@PostMapping("/launch/update")
	@ApiOperation("编辑稿件投放网站与栏目")
	public AjaxResponseBody updateLaunch(@RequestBody NewsLaunch newsLaunch){
		// 从session中拿当前用户id
//		news.setAduitUserId(aduitUserId);
		boolean release =  newsLaunchService.update(newsLaunch);
		if(!release) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	
	/**
	 * 按照稿件投放id查询稿件投放详情
	 * @return
	 */
	@GetMapping("/launch/find/id")
	@ApiOperation("按照稿件投放id查询稿件投放详情")
	public AjaxResponseBody findLaunch(@RequestParam Integer id){
		NewsLaunch module =  newsLaunchService.findById(id);
		if(module == null) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,module);
	}
	
	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/launch/query")
	@ApiOperation("查询稿件列表")
	public AjaxResponseBody queryLaunch(@RequestParam(required=false) String newsTitle,
			@RequestParam(required=false) Date startDate,
			@RequestParam(required=false) Date endDate,
			@RequestParam Integer pageNum,
			@RequestParam Integer pageSize){
		NewsLaunch newsLaunch = new NewsLaunch();
		newsLaunch.setNewsTitle(newsTitle);
		newsLaunch.setStartDate(startDate);
		newsLaunch.setEndDate(endDate);
		Page page = new Page(pageNum,pageSize);
		long total = newsLaunchService.queryCount(newsLaunch);
		List<NewsLaunch> list = newsLaunchService.queryAll(newsLaunch,page);
		PageInfo<NewsLaunch> pageInfo = new PageInfo<NewsLaunch>(pageNum,pageSize, total,list);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	}
	/**
	 * 不能删除稿件投放
	 * @return
	 */
	/*@GetMapping("/launch/delete")
	@ApiOperation("按照稿件投放id删除")
	public AjaxResponseBody deleteLaunch(@RequestParam Integer id){
		boolean release =  newsLaunchService.deleteLogicById(id);
		if(!release) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}*/
	/**
	 * 稿件审批
	 * @return
	 */
	@PostMapping("/launch/aduit")
	@ApiOperation("稿件审批")
	public AjaxResponseBody aduit(@RequestBody AduitNews aduit,HttpSession session){
		NewsLaunch newsLaunch = new NewsLaunch();
		BeanUtils.copyProperties(aduit, newsLaunch);
		User user = (User) session.getAttribute(Const.SESSION_USER_KEY);
		if(user == null) {
			return new AjaxResponseBody(false,Const.SESSION_TIMEOUT,null);
		}
		// 从session中拿当前用户id
		newsLaunch.setAduitUserId(user.getId());
		boolean release =  newsLaunchService.aduit(newsLaunch);
		if(!release) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	/**
	 * 按照稿件发布id查询稿件投放详情
	 * @return
	 */
	@GetMapping("/publish/find/id")
	@ApiOperation("按照稿件发布id查询稿件投放详情")
	public AjaxResponseBody findPublish(@RequestParam Integer id){
		NewsPublishLine module =  newsPublishService.findById(id);
		if(module == null) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,module);
	}
	
	/**
	 * 查询所有稿件投放信息
	 * @return
	 */
	@GetMapping("/publish/query")
	@ApiOperation("查询所有稿件投放信息")
	public AjaxResponseBody queryPublish(@RequestParam(required=false) String websiteName,
			@RequestParam(required=false) String channelName,
			@RequestParam(required=false) Integer isline,
			@RequestParam(required=false) Integer ishome,
			@RequestParam Integer pageNum,
			@RequestParam Integer pageSize){
		
		NewsPublish newsPublish = new NewsPublish();
		newsPublish.setChannelName(channelName);
		newsPublish.setWebsiteName(websiteName);
		newsPublish.setIshome(ishome);
		newsPublish.setIsline(isline);
		Page page = new Page(pageNum,pageSize);
		long total = newsPublishService.queryCount(newsPublish);
		List<NewsPublish> modules =  newsPublishService.query(newsPublish,page);
		PageInfo<NewsPublish> pageInfo =  new PageInfo<NewsPublish>(pageNum,pageSize, total,modules);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	
	}
	/**
	 * 稿件上下线
	 * @return
	 */
	@GetMapping("/publish/offLine")
	@ApiOperation("稿件上下线")
	public AjaxResponseBody publishOffLine(@RequestParam Integer id, @RequestParam Integer lineStatus){
		NewsPublish publish = new NewsPublish();
		publish.setId(id);
		// 前端传递要修改的状态
		publish.setIsline(lineStatus);
		if(lineStatus == Const.OFF_LINE) {
			publish.setOfflineDate(DateUtil.getDateStr(new Date()));
		} else if(lineStatus == Const.ON_LINE){
			publish.setOfflineDate(DateUtil.getDateStr(new Date()));
		}
		boolean release =  newsPublishService.update(publish);
		if(!release) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	
	/**
	 * 设置首页
	 * @return
	 */
	@GetMapping("/publish/setHome")
	@ApiOperation("设置首页")
	public AjaxResponseBody setHome(@RequestParam Integer id, @RequestParam Integer isHome,@RequestParam Integer homeWeight){
		NewsPublish publish = new NewsPublish();
		publish.setId(id);
		// 前端传递要修改的状态
		publish.setHomeWeight(homeWeight);
		publish.setIshome(isHome);
		boolean release =  newsPublishService.update(publish);
		if(!release) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	
}
