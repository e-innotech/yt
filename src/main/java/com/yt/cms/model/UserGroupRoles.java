package com.yt.cms.model;

public class UserGroupRoles {
    /**
     * 主键id
     */
	private Integer id;
	/**
	 * 角色id
	 */
    private Integer rolesId;
    /**
     * 用户组id
     */
    private Integer userGroupId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRolesId() {
        return rolesId;
    }

    public void setRolesId(Integer rolesId) {
        this.rolesId = rolesId;
    }

	public Integer getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(Integer userGroupId) {
		this.userGroupId = userGroupId;
	}


}