package com.yt.cms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Button {
	/**
	 * 主键id
	 */
	@JsonIgnore
    private Integer id;
    /**
     * 资源名称
     */
	@JsonIgnore
    private String resourceName;
    /**
     * 资源uri
     */
    private String uri;
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }

    
}