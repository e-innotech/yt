package com.yt.cms.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Integer MIN_PAGE = 1;// 最小页数

	private static final Integer DEFAULT_PAGE_SIZE = 20;// 默认每页显示20条数据

	private static final Integer TOTAL_SIZE = 0;// 总数据数

	/**
	 * 当前页码
	 */
	private Integer currentPage = MIN_PAGE;

	/**
	 * 每页显示数
	 */
	private Integer pageSize;

	/**
	 * limit的开始位置
	 */
	private Integer offSet;

	/**
	 * 总数据数
	 */
	private Integer totalSize;
	/**
	 * 总页数
	 */
	private Integer totalPage;

	private List list = new ArrayList();

	public PageInfo() {
	}

	public PageInfo(Integer currentPage, Integer pageSize, Integer offSet, Integer totalSize,Integer totalPage) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.offSet = offSet;
		this.totalSize = totalSize;
		this.totalPage = totalPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage =  (totalSize % pageSize == 0) ? (totalSize / pageSize) : (totalSize / pageSize) + 1;
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

	public Integer getTotalSize() {
		if (this.totalSize != null && this.totalSize >= 0) {
			return this.totalSize;
		}
		return TOTAL_SIZE;
	}

	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
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

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

}
