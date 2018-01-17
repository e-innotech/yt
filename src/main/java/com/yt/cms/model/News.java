package com.yt.cms.model;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class News extends BaseVo{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotBlank(message="稿件标题不能为空")
    private String newsTitle;
	@NotBlank(message="稿件来源不能为空")
    private String source;

    private String staticUrl;

    private Integer submitUserId;
    @NotBlank(message="稿件内容不能为空")
    private String content;
    
    private String createDate;
    
    private Integer status;
    /**
     * 首页稿件头图，非首页展示的稿件不需要头图
     */
    private String topImagePath;
    /**
     * 是否可编辑标记
     * 一个稿件可以同时投放多次，稿件的编辑状态是要看是否投放
     * 如果没有一次投放，则可编辑
     * 如果有1次以上投放，且投放状态都是审批不通过则可编辑
     * 此字段是关联投放表计算出来
     */
    private Integer isEdit;
    @JsonIgnore
    private Date startDate;
    @JsonIgnore
    private Date endDate;
    
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(Integer isEdit) {
		this.isEdit = isEdit;
	}

	public String getTopImagePath() {
		return topImagePath;
	}

	public void setTopImagePath(String topImagePath) {
		this.topImagePath = topImagePath;
	}

	
    
}