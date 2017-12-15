package com.yt.cms.model;

public class Roles {
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