package com.yt.cms.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("User(用户模型)")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
	@ApiModelProperty("id")
	private Integer id;
	/**
	 * 用户名
	 */
	@ApiModelProperty(notes = "Name of the User",name="userName",value="test name")
	private String userName;
	/**
	 * 密码
	 */
	@ApiModelProperty("密码")
	@JsonIgnore
	private String passWord;
	
	
	/**
	 * 用户状态是否可用
	 */
	@ApiModelProperty("用户状态是否可用")
	private Integer isUse;
	/**
	 * 创建用户时间
	 */
	@ApiModelProperty("创建用户时间")
	private String createDate;
	/**
	 * 用户组
	 */
	@JsonIgnore
	private UserGroup userGroup;
	public User() {
		super();
	}

	public User(String userName, String passWord) {
		super();
		this.passWord = passWord;
		this.userName = userName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public String getCreateDate() {
		return createDate;
	}


	public void setCreateDate(String createDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			createDate = sdf.format(sdf.parse(createDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "userName " + this.userName + ", pasword " + this.passWord ;
	}
}