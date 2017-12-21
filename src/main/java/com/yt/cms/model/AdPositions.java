package com.yt.cms.model;

import java.util.List;

public class AdPositions {
    private Integer id;

    private String adWidth;

    private String adHeight;

    private String adName;

    private Integer isUse;

    private Integer websitesId;

    private List<Ad> ads;
    
    public List<Ad> getAds() {
		return ads;
	}

	public void setAds(List<Ad> ads) {
		this.ads = ads;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdWidth() {
        return adWidth;
    }

    public void setAdWidth(String adWidth) {
        this.adWidth = adWidth == null ? null : adWidth.trim();
    }

    public String getAdHeight() {
        return adHeight;
    }

    public void setAdHeight(String adHeight) {
        this.adHeight = adHeight == null ? null : adHeight.trim();
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName == null ? null : adName.trim();
    }

    public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public Integer getWebsitesId() {
        return websitesId;
    }

    public void setWebsitesId(Integer websitesId) {
        this.websitesId = websitesId;
    }
}