package com.yt.cms.model;

import java.util.Date;

public class NewsLaunch {
    private Integer id;

    private Integer newsId;

    private String newsLaunchConfig;

    private Integer status;

    private Integer aduitUserId;

    private String aduitDes;

    private Date aduitDate;

    private Integer createUserId;

    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
		this.newsLaunchConfig = newsLaunchConfig == null ? null : newsLaunchConfig.trim();;
	}

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAduitUserId() {
        return aduitUserId;
    }

    public void setAduitUserId(Integer aduitUserId) {
        this.aduitUserId = aduitUserId;
    }

    public String getAduitDes() {
        return aduitDes;
    }

    public void setAduitDes(String aduitDes) {
        this.aduitDes = aduitDes == null ? null : aduitDes.trim();
    }

    public Date getAduitDate() {
        return aduitDate;
    }

    public void setAduitDate(Date aduitDate) {
        this.aduitDate = aduitDate;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}