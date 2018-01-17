package com.yt.cms.front.model;

import java.io.Serializable;

public class Channel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String channelName;

    private Integer websiteId;

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName == null ? null : channelName.trim();
	}

	public Integer getWebsiteId() {
		return websiteId;
	}

	public void setWebsiteId(Integer websiteId) {
		this.websiteId = websiteId;
	}


}