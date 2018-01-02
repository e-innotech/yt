package com.yt.cms.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MembersCommentsNews extends BaseVo{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer membersId;

    private Integer newsId;
    
    private News news;

    private Date createDate;

    private String content;
    @JsonIgnore
    private String newsTitle;
    @JsonIgnore
    private String memberUName;
    @JsonIgnore
    private Date startDate;
    @JsonIgnore
    private Date endDate;
    
    public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getMemberUName() {
		return memberUName;
	}

	public void setMemberUName(String memberUName) {
		this.memberUName = memberUName;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
    
    
}