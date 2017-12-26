package com.yt.cms.model;

import java.util.List;

public class Menu {
	/**
	 * 主键id
	 */
    private Integer id;
    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 资源uri
     */
    private String uri;
    /**
     * 父id
     */
    private Integer parentId;
    /**
     * 子节点
     */
    private List<Menu> childList;
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }

    public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public List<Menu> getChildList() {
		return childList;
	}

	public void setChildList(List<Menu> childList) {
		this.childList = childList;
	}

    
}