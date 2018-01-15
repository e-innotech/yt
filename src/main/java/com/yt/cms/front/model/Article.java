package com.yt.cms.front.model;

import java.io.Serializable;
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

	private Integer id;

    private String newsTitle;

    private String source;

    private String staticUrl;

    private Integer submitUserId;

    private String content;
    
    private String subContent;
    
    private String createDate;
    private Integer channelId;
    /**
     * 首页稿件头图，非首页展示的稿件不需要头图
     */
    private String topImagePath;
    /**
     * 点赞数量
     */
    private Long vote;
    /**
     * 收藏数量
     */
    private Long collect;
    /**
     * 评论数
     */
    private Long comment;
    
    
	public Long getVote() {
		return vote;
	}

	public void setVote(Long vote) {
		this.vote = vote;
	}

	public Long getCollect() {
		return collect;
	}

	public void setCollect(Long collect) {
		this.collect = collect;
	}

	public Long getComment() {
		return comment;
	}

	public void setComment(Long comment) {
		this.comment = comment;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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

	
    
}