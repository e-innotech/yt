package com.yt.cms.common;

import java.io.Serializable;
import java.util.List;

public class  PageInfo<T> implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//当前页
    private int pageNum;
    //每页的数量
    private int pageSize;

    //总记录数
    private long total;
   
    //结果集
    private List<T> list;

    public PageInfo() {
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageInfo{");
        sb.append("pageNum=").append(pageNum);
        sb.append(", pageSize=").append(pageSize);
       
        sb.append(", total=").append(total);
        sb.append(", list=").append(list);
     
        sb.append('}');
        return sb.toString();
    } 
}
