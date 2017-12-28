package com.yt.cms.common;

import java.io.Serializable;

public class Page implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Integer MIN_PAGE = 1;// 最小页数

	private static final Integer DEFAULT_PAGE_SIZE = 10;// 默认每页显示10条数据

	/**
	 * limit的开始位置
	 */
	private Integer pageNum = 0;
	/**
	 * 每页显示数
	 */
	private Integer pageSize = DEFAULT_PAGE_SIZE;


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
		this.pageNum = (pageNum-1)*pageSize;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	
	

}
