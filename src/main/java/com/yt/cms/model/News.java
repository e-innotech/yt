package com.yt.cms.model;

public class News {
    private Integer id;

    private String newsTitle;

    private String source;

    private String staticUrl;

    private Integer status;

    private Integer isLine;

    private Integer aduitUserId;

    private Integer submitUserId;

    private String aduitDes;

    private String content;

    private String aduitDate;
    
    public String getAduitDate() {
		return aduitDate;
	}

	public void setAduitDate(String aduitDate) {
		this.aduitDate = aduitDate;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle == null ? null : newsTitle.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getStaticUrl() {
		return staticUrl;
	}

	public void setStaticUrl(String staticUrl) {
		this.staticUrl = staticUrl == null ? null : staticUrl.trim();
	}

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsLine() {
		return isLine;
	}

	public void setIsLine(Integer isLine) {
		this.isLine = isLine;
	}

    public Integer getAduitUserId() {
        return aduitUserId;
    }

    public void setAduitUserId(Integer aduitUserId) {
        this.aduitUserId = aduitUserId;
    }

    public Integer getSubmitUserId() {
        return submitUserId;
    }

    public void setSubmitUserId(Integer submitUserId) {
        this.submitUserId = submitUserId;
    }

    public String getAduitDes() {
        return aduitDes;
    }

    public void setAduitDes(String aduitDes) {
        this.aduitDes = aduitDes == null ? null : aduitDes.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}