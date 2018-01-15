package com.yt.cms.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("UserResponseBody(新增用户模型)")
public class UserResponseBody implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户名
	 */
	@ApiModelProperty(notes = "Name of the User",name="userName",value="test name")
	@NotBlank(message="登陆用户名不能为空")
	private String userName;
	/**
	 * 密码
	 */
	@ApiModelProperty("密码")
	@NotBlank(message="登陆密码不能为空")
	private String passWord;
	
	
	private Integer userGroupId;
	
	public Integer getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(Integer userGroupId) {
		this.userGroupId = userGroupId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord == null ? "888888" : passWord.trim();
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	
}