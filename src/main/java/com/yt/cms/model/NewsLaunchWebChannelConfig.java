package com.yt.cms.model;

import java.io.Serializable;
import java.util.List;
/**
 * 稿件投放网站，栏目配置关系model
 * 一份稿件投放可能有多个网站
 * @author admin
 *
 */
public class NewsLaunchWebChannelConfig implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Channel> channels;
	private Websites website;
	public List<Channel> getChannels() {
		return channels;
	}
	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}
	public Websites getWebsite() {
		return website;
	}
	public void setWebsite(Websites website) {
		this.website = website;
	}
	

	

}
