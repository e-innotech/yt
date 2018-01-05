package com.yt.cms.model;

import java.io.Serializable;
/**
 * 系统日志使用需要查询所有系统的可写资源
 * @author admin
 *
 */
public class ResourceW implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String uri;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	
}
