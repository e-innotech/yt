package com.yt.cms.model;

import java.util.Date;
import java.util.List;

public class Websites {
    private Integer id;

    private String siteName;

    private String route;

    private Integer isUse;

    private Date createDate;

    private String templteRoute;

    private List<NavigationBar> navigationBar;
    
    public List<NavigationBar> getNavigationBar() {
		return navigationBar;
	}

	public void setNavigationBar(List<NavigationBar> navigationBar) {
		this.navigationBar = navigationBar;
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

	public String getTemplteRoute() {
		return templteRoute;
	}

	public void setTemplteRoute(String templteRoute) {
		this.templteRoute = templteRoute == null ? null : templteRoute.trim();
	}

	
}