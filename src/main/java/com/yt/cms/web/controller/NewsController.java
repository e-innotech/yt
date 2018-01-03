package com.yt.cms.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yt.cms.common.AjaxResponseBody;
import com.yt.cms.common.Const;
import com.yt.cms.common.Page;
import com.yt.cms.model.AduitNews;
import com.yt.cms.model.News;
import com.yt.cms.model.NewsLaunch;
import com.yt.cms.model.NewsPublish;
import com.yt.cms.model.NewsPublishLine;
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
	public PageInfo<News> query(@RequestParam(required=false) String newsTitle,
			@RequestParam(required=false) String source,
			@RequestParam(required=false) Date startDate,
			@RequestParam(required=false) Date endDate,
			Page page){
		News news = new News();
		news.setNewsTitle(newsTitle);
		news.setSource(source);
		news.setStartDate(startDate);
		news.setEndDate(endDate);
		List<News> list = newsService.queryAll(news,page);
		return new PageInfo<News>(list);
	}

	/**
	 * 按照id查询
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/find/id")
	@ApiOperation("按照id查询稿件")
	public HttpEntity<?> findById(@RequestParam Integer id) {
		News result = newsService.findById(id);
		HttpStatus status = result != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<News>(result, status);
	}
	/**
	 * 新增稿件
	 * @param News
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("添加稿件")
	public HttpEntity<?> add(@RequestBody News News) {
		boolean created = newsService.save(News);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.CREATED);
	}
	/**
	 * 修改稿件
	 * @param News
	 * @return
	 */
	@PutMapping("/update")
	@ApiOperation("修改稿件")
	public HttpEntity<?> update(@RequestBody News News){
		boolean created = newsService.update(News);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.OK);
	}
	/**
	 * 删除稿件
	 * @param id
	 * @return
	 */
	@PutMapping("/delete")
	@ApiOperation("删除稿件")
	public HttpEntity<?> delete(@RequestParam Integer id){
		boolean created = newsService.deleteLogicById(id);
		if(!created) {
			return new ResponseEntity<String>(Const.DELETE_NO_FOUND,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.OK);
	}
	
	/**
	 * 投放稿件到网站和栏目
	 * @return
	 */
	@PostMapping("/launch/add")
	@ApiOperation("投放稿件到网站和栏目")
	public HttpEntity<?> launch(@RequestBody NewsLaunch newsLaunch){
		boolean release =  newsLaunchService.save(newsLaunch);
		if(!release) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.OK);
	}


	/**
	 * 编辑稿件投放网站与栏目
	 * @return
	 */
	@PutMapping("/launch/update")
	@ApiOperation("编辑稿件投放网站与栏目")
	public HttpEntity<?> updateLaunch(@RequestBody NewsLaunch newsLaunch){
		// 从session中拿当前用户id
//		news.setAduitUserId(aduitUserId);
		boolean release =  newsLaunchService.update(newsLaunch);
		if(!release) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.OK);
	}
	
	/**
	 * 按照稿件投放id查询稿件投放详情
	 * @return
	 */
	@GetMapping("/launch/find/id")
	@ApiOperation("按照稿件投放id查询稿件投放详情")
	public HttpEntity<?> findLaunch(@RequestParam Integer id){
		NewsLaunch module =  newsLaunchService.findById(id);
		AjaxResponseBody response = new AjaxResponseBody();
		if(module == null) {
			response.setMsg(Const.NOT_FOUND);
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		response.setMsg(Const.SUCCESS);
		response.setData(module);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.OK);
	}
	
	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/launch/query")
	@ApiOperation("查询稿件列表")
	public PageInfo<NewsLaunch> queryLaunch(@RequestParam(required=false) String newsTitle,
			@RequestParam(required=false) Date startDate,
			@RequestParam(required=false) Date endDate,
			Page page){
		NewsLaunch newsLaunch = new NewsLaunch();
		newsLaunch.setNewsTitle(newsTitle);
		newsLaunch.setStartDate(startDate);
		newsLaunch.setEndDate(endDate);
		List<NewsLaunch> list = newsLaunchService.queryAll(newsLaunch,page);
		return new PageInfo<NewsLaunch>(list);
	}
	/**
	 * 按照稿件投放id删除
	 * @return
	 */
	@PutMapping("/launch/delete")
	@ApiOperation("按照稿件投放id删除")
	public HttpEntity<?> deleteLaunch(@RequestParam Integer id){
		boolean release =  newsLaunchService.deleteLogicById(id);
		if(!release) {
			return new ResponseEntity<String>(Const.DELETE_NO_FOUND,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.OK);
	}
	/**
	 * 稿件审批
	 * @return
	 */
	@PostMapping("/launch/aduit")
	@ApiOperation("稿件审批")
	public HttpEntity<?> aduit(@RequestBody AduitNews aduit){
		NewsLaunch newsLaunch = new NewsLaunch();
		BeanUtils.copyProperties(aduit, newsLaunch);
		// 从session中拿当前用户id
		newsLaunch.setAduitUserId(0);
		boolean release =  newsLaunchService.aduit(newsLaunch);
		if(!release) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.OK);
	}
	/**
	 * 按照稿件发布id查询稿件投放详情
	 * @return
	 */
	@GetMapping("/publish/find/id")
	@ApiOperation("按照稿件发布id查询稿件投放详情")
	public HttpEntity<?> findPublish(@RequestParam Integer id){
		NewsPublishLine module =  newsPublishService.findById(id);
		AjaxResponseBody response = new AjaxResponseBody();
		if(module == null) {
			response.setMsg(Const.NOT_FOUND);
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		response.setMsg(Const.SUCCESS);
		response.setData(module);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.OK);
	}
	
	/**
	 * 查询所有稿件投放信息
	 * @return
	 */
	@GetMapping("/publish/query")
	@ApiOperation("查询所有稿件投放信息")
	public HttpEntity<?> queryPublish(@RequestBody NewsPublish newsPublish){
		List<NewsPublish> modules =  newsPublishService.query(newsPublish);
		AjaxResponseBody response = new AjaxResponseBody();
		if(modules == null || modules.isEmpty()) {
			response.setMsg(Const.NOT_FOUND);
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		response.setMsg(Const.SUCCESS);
		response.setData(modules);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.OK);
	}
	/**
	 * 稿件上下线
	 * @return
	 */
	@PutMapping("/publish/ofLine")
	@ApiOperation("稿件上下线")
	public HttpEntity<?> publishOfLine(@RequestParam Integer id, @RequestParam Integer lineStatus){
		NewsPublish publish = new NewsPublish();
		publish.setId(id);
		// 前端传递要修改的状态
		publish.setIsline(lineStatus);
		if(lineStatus == Const.OFF_LINE) {
			publish.setOfflineDate(new Date());
		} else if(lineStatus == Const.ON_LINE){
			publish.setOnlineDate(new Date());
		}
		// 从session中拿当前用户id
//		news.setStatus(aduit.getStatus());
		boolean release =  newsPublishService.update(publish);
		if(!release) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.OK);
	}
	
	/**
	 * 设置首页
	 * @return
	 */
	@PutMapping("/publish/home")
	@ApiOperation("设置首页")
	public HttpEntity<?> setHome(@RequestParam Integer id, @RequestParam Integer homeWeight){
		NewsPublish publish = new NewsPublish();
		publish.setId(id);
		// 前端传递要修改的状态
		publish.setHomeWeight(homeWeight);
		boolean release =  newsPublishService.update(publish);
		if(!release) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.OK);
	}
	
}
