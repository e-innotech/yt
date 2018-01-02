package com.yt.cms.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
public class BaseVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	private Integer isDel;
	@JsonIgnore
	private Date delDate;

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Date getDelDate() {
		return delDate;
	}

	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}
	
	

}
