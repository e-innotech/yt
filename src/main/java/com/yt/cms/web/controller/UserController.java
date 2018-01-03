package com.yt.cms.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yt.cms.common.AjaxResponseBody;
import com.yt.cms.common.Const;
import com.yt.cms.model.MenuLeve1;
import com.yt.cms.model.User;
import com.yt.cms.model.UserResponseBody;
import com.yt.cms.model.UserUpdatePwd;
import com.yt.cms.service.PermissionService;
import com.yt.cms.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "用户服务",description = "提供RESTful风格API的用户的增删改查服务")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private PermissionService permissionService;
	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/user/query")
	@ApiOperation("查询用户列表")
	public PageInfo<User> query(@RequestParam(required=false) String userName,
			@RequestParam(defaultValue="10") Integer pageSize,
			@RequestParam(defaultValue="1") Integer pageNum){
		// 列表页面查出该用户在列表页面所有的按钮资源
		List<User> list = userService.query(userName, pageSize, pageNum);
		return new PageInfo<User>(list);
	}

	/**
	 * 按照id查询
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/user/find/id")
	@ApiOperation("按照id查询用户")
	public User findById(@RequestParam Integer id) {
		User result = userService.findById(id);
		return result;
	}
	
	/**
	 * 按照注册名查询
	 * @param id
	 * @return
	 */
	@GetMapping("/user/find/name")
	@ApiOperation("按照用户名查询用户")
	public AjaxResponseBody findByUserName(@RequestParam String userName) {
		boolean result = userService.findByUserName(userName);
		AjaxResponseBody response = new AjaxResponseBody();
		if(result) {
			response.setMsg("该用户名已注册！");
			response.setSuccess(false);
		} else {
			response.setMsg("该用户名可注册！");
			response.setSuccess(true);
		} 
		return response;
	}
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	@PostMapping("/user/add")
	@ApiOperation("添加用户")
	public AjaxResponseBody add(@RequestBody UserResponseBody userBody) {
		User user = new User();
		user.setPassWord(userBody.getPassWord());
		user.setUserName(userBody.getUserName());
		boolean created = userService.save(user);
		AjaxResponseBody response = new AjaxResponseBody();
		if(!created) {
			response.setMsg(Const.FAILED);
			response.setSuccess(false);
		} else {
			response.setMsg(Const.SUCCESS);
			response.setSuccess(true);
		}
		return response;
		
	}
	/**
	 * 用户登录
	 * @param userBody
	 */
	@PostMapping("/login")
	@ApiOperation("用户登陆")
	public AjaxResponseBody login(@RequestBody UserResponseBody userBody, HttpServletRequest request) {
		User user = new User();
		// TODO 用户名和密码不能为空
		user.setPassWord(userBody.getPassWord());
		user.setUserName(userBody.getUserName());
		
		User db_user = userService.login(user);
		HttpSession session = request.getSession();
		AjaxResponseBody response = new AjaxResponseBody();
		if(db_user == null) {
			response.setMsg(Const.LOGIN_FAILED);
			response.setSuccess(false);
		} else {
			session.setAttribute(Const.SESSION_USER_KEY, db_user);
			response.setMsg(Const.LOGIN_SUCCESS);
			response.setSuccess(true);
			Map<String, Object> map = new HashMap<String, Object>();
			List<MenuLeve1> menu = permissionService.queryMenu(db_user);
			String roleName = permissionService.queryRolesName(db_user.getUserGroup().getId());
			map.put("userName", db_user.getUserName());
			map.put("roleName", roleName);
			map.put("menu", menu);
			response.setData(map);
		}
		return response;
	}
	/**
	 * 用户退出
	 * @param userId
	 */
	@PostMapping("/logout")
	@ApiOperation("用户登陆")
	public AjaxResponseBody logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(Const.SESSION_USER_KEY);
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.LOGOUT_SUCCESS);
		response.setSuccess(true);
		return response;
	}
	/**
	 * 更新用户
	 * 还需要验证新旧密码是否相同
	 * @param user
	 * @return
	 */
	@PutMapping("/user/update/pwd")
	@ApiOperation("更新用户密码")
	public AjaxResponseBody updatePwd(@RequestBody UserUpdatePwd user,  HttpServletRequest request) {
		// TODO 公用的资源不属于菜单
		HttpSession session = request.getSession();
		User user_session = (User) session.getAttribute(Const.SESSION_USER_KEY);
		AjaxResponseBody response = new AjaxResponseBody();
		if(user_session == null || user_session.getId() == null) {
			response.setMsg(Const.SESSION_TIMEOUT);
			response.setSuccess(false);
			return response;
		}
		if(StringUtils.isEmpty(user.getPassWord()) || StringUtils.isEmpty(user.getCurrentPwd())) {
			response.setMsg(Const.PASSWORD_REQUIRED);
			response.setSuccess(false);
			return response;
		}
		user.setId(user_session.getId());
		boolean flag = userService.update(user);
		if(!flag) {
			response.setMsg(Const.FAILED);
			response.setSuccess(false);
		} else {
			response.setMsg(Const.SUCCESS);
			response.setSuccess(true);
		}
		return response;
	}
	
	/**
	 * 启停用户
	 * @param user
	 * @return
	 */
	@PutMapping("/user/update/status")
	@ApiOperation("启停用户")
	public AjaxResponseBody updateStatus(@RequestParam Integer id,@RequestParam Integer isUse) {
		AjaxResponseBody response = new AjaxResponseBody();
		User user = new User();
		user.setId(id);
		user.setIsUse(isUse);
		boolean flag = userService.disableOrEnable(user);
		if(!flag) {
			response.setMsg(Const.FAILED);
			response.setSuccess(false);
		} else {
			response.setMsg(Const.SUCCESS);
			response.setSuccess(true);
		}
		return response;
	}
	
	/**
	 * 给用户授权
	 * @param user
	 * @return
	 */
	@PutMapping("/user/grant")
	@ApiOperation("给用户授权")
	public AjaxResponseBody grant(@RequestParam Integer userId,@RequestParam Integer userGroupId) {
		AjaxResponseBody response = new AjaxResponseBody();
		boolean flag = userService.setUserGroup4User(userId, userGroupId);
		if(!flag) {
			response.setMsg(Const.FAILED);
			response.setSuccess(false);
		} else {
			response.setMsg(Const.SUCCESS);
			response.setSuccess(true);
		}
		return response;
	}
	
}
