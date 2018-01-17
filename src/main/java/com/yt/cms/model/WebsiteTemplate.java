package com.yt.cms.model;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
public class WebsiteTemplate implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
//    @Pattern(regexp="*[*.html]$",message="模板路径格式不正确，必须是html文件")
    private String templatePath;
    /**
     * 网站模板类型
     * 0 表示首页
     * 1 表示栏目页
     * 2 表示 详情页
     */
    @Range(min=0, max=2,message="网站模板类型格式不正确，网站模板类型:0 表示首页,1 表示栏目页,2 表示 详情页")
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