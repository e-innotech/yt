package com.yt.cms.model;

public class WebsitesBarBody {

	public WebsitesBarBody() {
		super();
	}

	Integer websitesId;
	Integer[] old_navigationBarIds;
	Integer[] new_navigationBarIds;

	public Integer getWebsitesId() {
		return websitesId;
	}

	public void setWebsitesId(Integer websitesId) {
		this.websitesId = websitesId;
	}

	public Integer[] getOld_navigationBarIds() {
		return old_navigationBarIds;
	}

	public void setOld_navigationBarIds(Integer[] old_navigationBarIds) {
		this.old_navigationBarIds = old_navigationBarIds;
	}

	public Integer[] getNew_navigationBarIds() {
		return new_navigationBarIds;
	}

	public void setNew_navigationBarIds(Integer[] new_navigationBarIds) {
		this.new_navigationBarIds = new_navigationBarIds;
	}

}
