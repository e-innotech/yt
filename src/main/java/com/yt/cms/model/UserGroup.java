package com.yt.cms.model;

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
    private String desc;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }
}