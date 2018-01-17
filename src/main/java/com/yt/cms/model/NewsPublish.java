package com.yt.cms.model;

public class NewsPublish {
    private Integer id;

    private Integer newsId;

    private Integer websiteId;

    private Integer channelId;

    private Integer isline;

    private Integer ishome;

    private Integer homeWeight;

//    private String onlineDate;

    private String offlineDate;

    private NewsLaunch newsLaunch;
    private String websiteName;
    private String channelName;
    private String newsTitle;
    
    public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getWebsiteName() {
		return websiteName;
	}

	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

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

//    public String getOnlineDate() {
//        return onlineDate;
//    }
//
//    public void setOnlineDate(String onlineDate) {
//        this.onlineDate = onlineDate;
//    }

    public String getOfflineDate() {
        return offlineDate;
    }

    public void setOfflineDate(String offlineDate) {
        this.offlineDate = offlineDate;
    }

	public NewsLaunch getNewsLaunch() {
		return newsLaunch;
	}

	public void setNewsLaunch(NewsLaunch newsLaunch) {
		this.newsLaunch = newsLaunch;
	}
    
    
}