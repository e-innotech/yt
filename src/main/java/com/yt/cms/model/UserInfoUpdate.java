package com.yt.cms.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("UserInfoUpdate(修改用户信息模型)")
public class UserInfoUpdate implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
	@ApiModelProperty("id")
	private Integer id;

	/**
	 * 用户状态是否可用
	 */
	@ApiModelProperty("用户状态是否可用")
	private Integer isUse;

	/**
	 * 用户组
	 */
	private Integer userGroupId;
	public UserInfoUpdate() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public Integer getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(Integer userGroupId) {
		this.userGroupId = userGroupId;
	}

	
}