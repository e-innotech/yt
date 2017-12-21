package com.yt.cms.model;

public class WebsitesBarBody {

	public WebsitesBarBody() {
		super();
	}

	Integer websitesId;
	Integer[] old_channelIds;
	Integer[] new_channelIds;

	public Integer getWebsitesId() {
		return websitesId;
	}

	public void setWebsitesId(Integer websitesId) {
		this.websitesId = websitesId;
	}

	public Integer[] getOld_channelIds() {
		return old_channelIds;
	}

	public void setOld_channelIds(Integer[] old_channelIds) {
		this.old_channelIds = old_channelIds;
	}

	public Integer[] getNew_channelIds() {
		return new_channelIds;
	}

	public void setNew_channelIds(Integer[] new_channelIds) {
		this.new_channelIds = new_channelIds;
	}

	

}
