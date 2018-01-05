package com.yt.cms.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 广告上下线
 * @author admin
 *
 */
public class AdOffLine implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer status;

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


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
}