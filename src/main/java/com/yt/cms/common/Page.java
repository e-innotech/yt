package com.yt.cms.common;

import java.io.Serializable;

public class Page implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * limit的开始位置
	 */
	private Integer pageNum;
	/**
	 * 每页显示数
	 */
	private Integer pageSize;


	public Page() {
	}

	public Page(Integer pageNum, Integer pageSize) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
