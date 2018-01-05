package com.yt.cms.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("UserResponseBody(用户模型)")
public class UserResponseBody implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户名
	 */
	@ApiModelProperty(notes = "Name of the User",name="userName",value="test name")
	@NotBlank
	private String userName;
	/**
	 * 密码
	 */
	@ApiModelProperty("密码")
	@NotBlank
	private String passWord;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	
}