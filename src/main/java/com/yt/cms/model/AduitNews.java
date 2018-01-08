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
	 * 稿件id
	 */
	private Integer newsId;
	/**
	 * 稿件审批状态
	 */
	private Integer status;
	/**
	 * 稿件审批意见
	 */
	private String aduitDes;
	/**
	 * 稿件投放情况，审批通过时使用
	 */
	private String newsLaunchConfig;
	
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

	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public String getNewsLaunchConfig() {
		return newsLaunchConfig;
	}
	public void setNewsLaunchConfig(String newsLaunchConfig) {
		this.newsLaunchConfig = newsLaunchConfig;
	}
	
	
}
