package com.yt.cms.model;

import java.util.Date;

public class Ad extends BaseVo{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Date beginDate;

    private Date endDate;

    private Integer status;

    private Integer adPositionsId;

    private String adName;

    private Integer adType;

    private String comment;

    private String source;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAdPositionsId() {
		return adPositionsId;
	}

	public void setAdPositionsId(Integer adPositionsId) {
		this.adPositionsId = adPositionsId;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public Integer getAdType() {
		return adType;
	}

	public void setAdType(Integer adType) {
		this.adType = adType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment == null ? null : comment.trim();
	}

	public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }
}