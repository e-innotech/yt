package com.yt.cms.model;

public class ResourceLevel2 extends BaseVo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
    private Integer parentId;
    /**
     * 资源读写标记
     */
    private Integer rw;
    /**
     * 是否菜单
     */
    private Integer isMenu;
    /**
     * 父id的资源名
     */
    private String pname;
    
    private ResourceLevel3 level3;
    
	public ResourceLevel3 getLevel3() {
		return level3;
	}

	public void setLevel3(ResourceLevel3 level3) {
		this.level3 = level3;
	}

	public ResourceLevel2() {
		super();
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

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