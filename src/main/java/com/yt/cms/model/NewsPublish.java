package com.yt.cms.model;

import java.util.Date;

public class NewsPublish {
    private Integer id;

    private Integer newsId;

    private Integer websiteId;

    private Integer channelId;

    private Integer isline;

    private Integer ishome;

    private Integer homeWeight;

    private Date onlineDate;

    private Date offlineDate;

    private NewsLaunch newsLaunch;
    
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

    public Integer getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(Integer websiteId) {
        this.websiteId = websiteId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getIsline() {
        return isline;
    }

    public void setIsline(Integer isline) {
        this.isline = isline;
    }

    public Integer getIshome() {
        return ishome;
    }

    public void setIshome(Integer ishome) {
        this.ishome = ishome;
    }

    public Integer getHomeWeight() {
        return homeWeight;
    }

    public void setHomeWeight(Integer homeWeight) {
        this.homeWeight = homeWeight;
    }

    public Date getOnlineDate() {
        return onlineDate;
    }

    public void setOnlineDate(Date onlineDate) {
        this.onlineDate = onlineDate;
    }

    public Date getOfflineDate() {
        return offlineDate;
    }

    public void setOfflineDate(Date offlineDate) {
        this.offlineDate = offlineDate;
    }

	public NewsLaunch getNewsLaunch() {
		return newsLaunch;
	}

	public void setNewsLaunch(NewsLaunch newsLaunch) {
		this.newsLaunch = newsLaunch;
	}
    
    
}