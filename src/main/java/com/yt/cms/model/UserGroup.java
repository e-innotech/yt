package com.yt.cms.model;

import java.util.List;

public class UserGroup {
	/**
	 * 主键id
	 */
    private Integer id;
    /**
     * 用户组名称
     */
    private String groupName;
    /**
     * 用户组描述
     */
    private String comment;
    /**
     * 用户组角色列表
     */
    private List<Roles> roles;
    
    public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }
    
    public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment == null ? null : comment.trim();
	}

}