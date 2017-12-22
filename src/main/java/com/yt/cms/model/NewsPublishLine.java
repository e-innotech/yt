package com.yt.cms.model;

import java.util.Date;

public class NewsPublishLine {
    private Integer id;

    private News news;

    private Websites websites;

    private Channel channel;

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

    public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public Websites getWebsites() {
		return websites;
	}

	public void setWebsites(Websites websites) {
		this.websites = websites;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
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