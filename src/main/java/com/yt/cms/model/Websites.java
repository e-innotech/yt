package com.yt.cms.model;

import java.util.List;

//import com.fasterxml.jackson.annotation.JsonIgnore;

public class Websites extends BaseVo{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String siteName;

    private String route;

    private Integer isUse;

    private String createDate;

    private String templateConfig;
    /**
     * 网站首页权重最大值
     */
    private Integer homeWeightMax;
//    @JsonIgnore
    private List<Channel> channels;
    
	public List<Channel> getChannels() {
		return channels;
	}

	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName == null ? null : siteName.trim();
	}

	public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route == null ? null : route.trim();
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

	public String getTemplateConfig() {
		return templateConfig;
	}

	public void setTemplateConfig(String templateConfig) {
		this.templateConfig = templateConfig == null ? null : templateConfig.trim();
	}

	public Integer getHomeWeightMax() {
		return homeWeightMax;
	}

	public void setHomeWeightMax(Integer homeWeightMax) {
		this.homeWeightMax = homeWeightMax;
	}

	
}