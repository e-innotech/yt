package com.yt.cms.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class AdPositions extends BaseVo{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotNull(message="广告位宽度像素不能为空")
    private Integer adWidth;
	@NotNull(message="广告位高度像素不能为空")
    private Integer adHeight;
	@NotBlank(message="广告位名称不能为空")
    private String adName;

    private String remark;
    /**
     * 添加使用
     */
    @NotNull(message="广告位关联的网站模板id不能为空")
    private Integer webTemplateId;
    
    private WebsiteTemplate webTemplate;

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdWidth() {
        return adWidth;
    }

    public void setAdWidth(Integer adWidth) {
        this.adWidth = adWidth;
    }

    public Integer getAdHeight() {
        return adHeight;
    }

    public void setAdHeight(Integer adHeight) {
        this.adHeight = adHeight;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName == null ? null : adName.trim();
    }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public WebsiteTemplate getWebTemplate() {
		return webTemplate;
	}

	public void setWebTemplate(WebsiteTemplate webTemplate) {
		this.webTemplate = webTemplate;
	}

	public Integer getWebTemplateId() {
		return webTemplateId;
	}

	public void setWebTemplateId(Integer webTemplateId) {
		this.webTemplateId = webTemplateId;
	}

	
}