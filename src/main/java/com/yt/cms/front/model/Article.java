package com.yt.cms.front.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 网站文章模型
 * @author admin
 *
 */
public class Article implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	private Integer newsId;
	private Integer publishId;
    private String newsTitle;

    private String source;

    private String staticUrl;

    private Integer submitUserId;

    private String content;
    
    private String subContent;
    
    private String createDate;
    @JsonIgnore
    private Integer channelId;
    /**
     * 首页稿件头图，非首页展示的稿件不需要头图
     */
    private String topImagePath;
    /**
     * 点赞数量
     */
    private long vote;
    /**
     * 收藏数量
     */
    private long collect;
    /**
     * 评论数
     */
    private long comment;
    
    
	public long getVote() {
		return vote;
	}

	public void setVote(long vote) {
		this.vote = vote;
	}

	public long getCollect() {
		return collect;
	}

	public void setCollect(long collect) {
		this.collect = collect;
	}

	public long getComment() {
		return comment;
	}

	public void setComment(long comment) {
		this.comment = comment;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

    public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
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
   
    public Integer getSubmitUserId() {
        return submitUserId;
    }

    public void setSubmitUserId(Integer submitUserId) {
        this.submitUserId = submitUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public String getTopImagePath() {
		return topImagePath;
	}

	public void setTopImagePath(String topImagePath) {
		this.topImagePath = topImagePath;
	}

	public String getSubContent() {
//		String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
//		Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
//	    Matcher m_html=p_html.matcher(subContent); 
//	    subContent=m_html.replaceAll(""); //过滤html标签 
		return subContent;
	}

	public void setSubContent(String subContent) {
		this.subContent = subContent;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public Integer getPublishId() {
		return publishId;
	}

	public void setPublishId(Integer publishId) {
		this.publishId = publishId;
	}

	
    
}