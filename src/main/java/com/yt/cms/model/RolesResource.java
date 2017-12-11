package com.yt.cms.model;

public class RolesResource {
	/**
	 * 主键id
	 */
    private Integer id;
    /**
     * 角色id
     */
    private Integer rolesId;
    /**
     * 资源id
     */
    private Integer resourceId;

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

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }
}