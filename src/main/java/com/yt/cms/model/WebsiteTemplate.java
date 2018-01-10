package com.yt.cms.model;

public class WebsiteTemplate {
    private Integer id;

    private String templatePath;

    private Integer templateType;

    private Websites websites;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath == null ? null : templatePath.trim();
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

	public Websites getWebsites() {
		return websites;
	}

	public void setWebsites(Websites websites) {
		this.websites = websites;
	}

}