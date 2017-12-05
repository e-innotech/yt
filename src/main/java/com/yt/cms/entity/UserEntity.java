package com.yt.cms.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String passWord;
	
	/**
	 * 用户状态是否可用
	 */
	private Integer isUse;
	/**
	 * 创建用户时间
	 */
	private String createDate;

	public UserEntity() {
		super();
	}

	public UserEntity(String userName, String passWord) {
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