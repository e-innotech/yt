package com.yt.cms.model;

import java.util.List;
/**
 * 稿件投放网站，栏目配置关系model
 * @author admin
 *
 */
public class NewsLaunchConfig {
	
	private List<Integer> channelId;
	private int webId;

	public void setChannelId(List<Integer> channelId) {
		this.channelId = channelId;
	}

	public List<Integer> getChannelId() {
		return channelId;
	}

	public void setWebId(Integer webId) {
		this.webId = webId;
	}

	public Integer getWebId() {
		return webId;
	}

}
