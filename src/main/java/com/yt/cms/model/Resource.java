package com.yt.cms.model;

public class Resource {
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
     * 模块id
     */
    private Integer moduleId;
    /**
     * 资源读写标记
     */
    private Integer rw;
    /**
     * 是否菜单
     */
    private Integer isMenu;

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

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getRw() {
        return rw;
    }

    public void setRw(Integer rw) {
        this.rw = rw;
    }

	public Integer getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(Integer isMenu) {
		this.isMenu = isMenu;
	}
    
    
}