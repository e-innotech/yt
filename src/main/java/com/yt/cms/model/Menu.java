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
    private String text;
    /**
     * 资源uri
     */
    private String href;
    /**
     * 父id
     */
    private Integer parentId;
    /**
     * 子节点
     */
    private List<Menu> nodes;
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

    public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public List<Menu> getNodes() {
		return nodes;
	}

	public void setNodes(List<Menu> nodes) {
		this.nodes = nodes;
	}

    
}