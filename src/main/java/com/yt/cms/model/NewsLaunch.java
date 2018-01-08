package com.yt.cms.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

public class NewsLaunch extends BaseVo{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer newsId;
    @ApiModelProperty("投放配置-json字符串")
    private String newsLaunchConfig;

    private Integer status;

    private Integer aduitUserId;

    private String aduitDes;

    private Date aduitDate;

    private Integer createUserId;

    private String createDate;

    private News news;
    
    @JsonIgnore
    private String newsTitle;
    @JsonIgnore
    private Date startDate;
    @JsonIgnore
    private Date endDate;
//    private JSONArray config;
    
    public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

    public String getNewsLaunchConfig() {
//    	newsLaunchConfig = this.getConfig().toJSONString();
		return newsLaunchConfig;
	}

	public void setNewsLaunchConfig(String newsLaunchConfig) {
		this.newsLaunchConfig = newsLaunchConfig == null ? null : newsLaunchConfig.trim();
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

//	public JSONArray getConfig() {
//		return config;
//	}
//
//	public void setConfig(JSONArray config) {
//		this.config = config;
//	}


    
}