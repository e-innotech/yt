package com.yt.cms.model;

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