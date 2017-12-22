package com.yt.cms.model;

import java.util.Date;

public class AduitNews {
	/**
	 * 投放id
	 */
	private Integer id;
	/**
	 * 稿件审批状态
	 */
	private Integer status;
	/**
	 * 稿件审批意见
	 */
	private String aduitDes;
	/**
	 * 稿件审批时间
	 */
	private Date aduitDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAduitDes() {
		return aduitDes;
	}
	public void setAduitDes(String aduitDes) {
		this.aduitDes = aduitDes;
	}
	public Date getAduitDate() {
		return aduitDate;
	}
	public void setAduitDate(Date aduitDate) {
		this.aduitDate = aduitDate;
	}
	
	
}
