package com.yt.cms.common;
/**
 * 常量类
 * @author admin
 *
 */
public class Const {
	
	public static final String SUCCESS = "操作成功！";
	
	public static final String FAILED = "操作失败！";
	
	public static final String DELETE_NO_FOUND = "没有找到要删除的行！";
	
	/**************登录相关常量************************/
	
	public static final String LOGIN_INFO_NOT_EMPTY = "登陆用户名或者密码不能为空！";
	
	public static final String LOGIN_SUCCESS = "登陆成功！";
	
	public static final String LOGIN_FAILED = "登陆失败！账号或者密码错误。";
	
	public static final String LOGOUT_SUCCESS = "成功退出!";
	
	
	/**************登录相关常量************************/
	
	
	public static final String NOT_FOUND = "没有找到！";
	
	public static final String PASSWORD_REQUIRED = "新密码或者旧密码不能为空！";
	
	public static final String SESSION_TIMEOUT = "超时，请重新登录！";
	// 稿件审批通过
	public static final Integer ADUIT_PASS = 1;
	// 稿件审批不通过
	public static final Integer ADUIT_NO_PASS = 2;
	//已投放但不通过
	public static final Integer LAUNCH_PASS = 1;
	//已投放但不通过
	public static final Integer LAUNCH_NO_PASS = 2;
	// 上线状态值
	public static final Integer ON_LINE = 1;
	// 下线状态值
	public static final Integer OFF_LINE = 0;
	// 删除标记
	public static final Integer DELETE_FLAG = 1;
	// 超级管理员用户组id
	public static final Integer SUPER_ADMIN_USER_GROUP = 0;
	// 后台用户session key
	public static final String SESSION_USER_KEY = "_session_user";
	// 前端会员session key
	public static final String SESSION_MEMBERS_KEY = "_session_members";
	
}
