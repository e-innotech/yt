package com.yt.cms.model;

import java.util.Date;

public class Ad extends BaseVo{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String beginDate;

    private String endDate;

    private Integer status;

    private Integer adPositionsId;

    private String adName;

    private Integer adType;

    private String remark;

    private String source;

    private Date offLineDate;
    
	public Date getOffLineDate() {
		return offLineDate;
	}

	public void setOffLineDate(Date offLineDate) {
		this.offLineDate = offLineDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark =  remark == null ? null : remark.trim();
	}

	public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }
}