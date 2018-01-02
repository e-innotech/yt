package com.yt.cms.model;

import java.io.Serializable;
import java.util.Date;

public class MembersCollectNews implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer membersId;

    private Integer newsId;
    
    private News news;

    private Date createdate;

    private Integer status;

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

	public Integer getMembersId() {
        return membersId;
    }

    public void setMembersId(Integer membersId) {
        this.membersId = membersId;
    }

    public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}