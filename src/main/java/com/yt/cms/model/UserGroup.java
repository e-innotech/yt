package com.yt.cms.model;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class UserGroup extends BaseVo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
    private Integer id;
    /**
     * 用户组名称
     */
    @NotBlank(message = "用户组名不能为空")
    private String groupName;
    /**
     * 用户组描述
     */
    private String remark;
    /**
     * 用户组角色列表
     */
    private Roles roles;
    @Min(value=1,message="角色不能为空")
    private Integer rolesId;
    
	public Integer getRolesId() {
		return rolesId;
	}

	public void setRolesId(Integer rolesId) {
		this.rolesId = rolesId;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
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
    
    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}


}