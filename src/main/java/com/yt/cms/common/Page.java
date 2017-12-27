package com.yt.cms.common;

import java.io.Serializable;

public class Page implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Integer MIN_PAGE = 1;// 最小页数

	private static final Integer DEFAULT_PAGE_SIZE = 20;// 默认每页显示20条数据

	private static final Integer TOTAL_SIZE = 0;// 总数据数

	/**
	 * 当前页码
	 */
	private Integer currentPage = MIN_PAGE;
	/**
	 * limit的开始位置
	 */
	private Integer offSet;
	/**
	 * 每页显示数
	 */
	private Integer pageSize;


	public Page() {
	}

	public Page(Integer currentPage,Integer offSet, Integer pageSize) {
		super();
		this.currentPage = currentPage;
		this.offSet = offSet;
		this.pageSize = pageSize;
	}

	
	public Integer getCurrentPage() {
		if (this.currentPage != null && this.currentPage >= 1) {
			return currentPage;
		}
		return MIN_PAGE;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getOffSet() {
		offSet = (this.getCurrentPage() - 1) * this.getPageSize();
		return offSet;
	}

	public void setOffSet(Integer offSet) {
		this.offSet = offSet;
	}

	

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		if (this.pageSize != null && this.pageSize >= 1) {
			return this.pageSize;
		}
		return DEFAULT_PAGE_SIZE;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


}
