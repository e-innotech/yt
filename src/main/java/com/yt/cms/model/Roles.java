package com.yt.cms.model;

import java.util.List;

public class Roles extends BaseVo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
    private Integer id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色说明
     */
    private String comment;
    /**
     * 角色对应的资源列表
     */
    private List<Resource> resource;

    public List<Resource> getResource() {
		return resource;
	}

	public void setResource(List<Resource> resource) {
		this.resource = resource;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment == null ? null : comment.trim();
	}

 
}