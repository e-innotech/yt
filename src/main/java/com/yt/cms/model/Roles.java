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
    private String desc;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }
}