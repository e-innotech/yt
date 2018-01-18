package com.yt.cms.model.page;

import com.yt.cms.common.Page;

public class RolesPage extends Page {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String roleName;
	
	private Integer pageLimit;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getPageLimit() {
		pageLimit = (this.getPageNum() -1)*this.getPageSize();
		return pageLimit;
	}

	public void setPageLimit(Integer pageLimit) {
		this.pageLimit = pageLimit;
	}
	
	
	
}
