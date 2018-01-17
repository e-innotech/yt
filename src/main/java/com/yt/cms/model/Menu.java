package com.yt.cms.model;

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
    private String uri;
    /**
     * 父id
     */
    private Integer parentId;
    
    
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
   
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

    
}