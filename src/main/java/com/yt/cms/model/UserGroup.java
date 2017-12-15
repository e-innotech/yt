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
    private String comment;

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