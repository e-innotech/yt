package com.yt.cms.model;

import java.util.Date;
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

    private Date createDate;

    private String templteConfig;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getTemplteConfig() {
		return templteConfig;
	}

	public void setTemplteConfig(String templteConfig) {
		this.templteConfig = templteConfig == null ? null : templteConfig.trim();;
	}

	
}