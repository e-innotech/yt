package com.yt.cms.common;

public class Base {

	/**
	 * limit的开始位置
	 */
	private Integer offSet;
	/**
	 * 每页显示数
	 */
	private Integer pageSize;
	public Integer getOffSet() {
		return offSet;
	}
	public void setOffSet(Integer offSet) {
		this.offSet = offSet;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
