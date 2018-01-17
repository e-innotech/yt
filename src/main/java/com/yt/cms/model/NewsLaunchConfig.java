package com.yt.cms.model;

import java.io.Serializable;
import java.util.List;
/**
 * 稿件投放网站，栏目配置关系model
 * @author admin
 *
 */
public class NewsLaunchConfig implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Integer> channelId;
	private Integer websiteId;
	public List<Integer> getChannelId() {
		return channelId;
	}
	public void setChannelId(List<Integer> channelId) {
		this.channelId = channelId;
	}
	public Integer getWebsiteId() {
		return websiteId;
	}
	public void setWebsiteId(Integer websiteId) {
		this.websiteId = websiteId;
	}

	

}
