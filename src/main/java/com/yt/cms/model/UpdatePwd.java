package com.yt.cms.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("UpdatePwd(修改密码模型)")
public class UpdatePwd implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	/**
	 * 密码
	 */
	@ApiModelProperty("密码")
	@NotBlank(message="新密码不能为空")
	private String passWord;
	/**
	 * 当前密码，修改密码使用
	 */
	@NotBlank(message="旧密码不能为空")
	private String currentPwd;
	
	public UpdatePwd() {
		super();
	}

	public UpdatePwd(String currentPwd, String passWord) {
		super();
		this.currentPwd = currentPwd;
		this.passWord = passWord;
	}

	public String getCurrentPwd() {
		return currentPwd;
	}

	public void setCurrentPwd(String currentPwd) {
		this.currentPwd = currentPwd;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

}