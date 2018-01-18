package com.yt.cms.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class Resource extends BaseVo{
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
    @NotBlank(message="资源名不能为空")
    private String resourceName;
    /**
     * 资源uri
     */
    private String uri;
    /**
     * 模块id
     */
    @NotNull(message="上级资源不能为空")
    private Integer parentId;
    /**
     * 资源读写标记
     */
    @NotNull(message="读写标记不能为空")
    private Integer rw;
    /**
     * 是否菜单
     */
    @NotNull(message="是否菜单不能为空")
    private Integer isMenu;
    /**
     * 父id的资源名
     */
    private String pname;
    
	public Resource() {
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