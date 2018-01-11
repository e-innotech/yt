package com.yt.cms.model;
/**
 * 稿件审批
 * @author admin
 *
 */
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


	
	
}
