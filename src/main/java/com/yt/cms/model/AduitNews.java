package com.yt.cms.model;

public class AduitNews {
	/**
	 * 稿件id
	 */
	private Integer id;
	/**
	 * 稿件审批状态
	 */
	private Integer status;
	/**
	 * 稿件审批意见
	 */
	private String aduitDesc;
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
	public String getAduitDesc() {
		return aduitDesc;
	}
	public void setAduitDesc(String aduitDesc) {
		this.aduitDesc = aduitDesc;
	}
	
	
}
