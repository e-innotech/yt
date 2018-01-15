package com.yt.cms.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class YtcmsConfig implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
    @NotBlank(message = "配置名不能为空")
    private String name;
    @NotBlank(message = "配置值不能为空")
    private String value;

    private Integer isUse;

    private String createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}