package com.yt.cms.model;

import java.util.List;

public class Module {
	/**
	 * 主键id
	 */
    private Integer id;
    /**
     * 模块名称
     */
    private String moduleName;
    /**
     * 模块code
     */
    private String moduleCode;
    /**
     * 上级模块id
     */
    private Integer parentId;
    /**
     * 模块下的所有资源
     */
    private List<Resource> resources;

    public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode == null ? null : moduleCode.trim();
    }
}