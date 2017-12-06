package com.yt.cms.common;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

/**
 * 用于异步请求的统一返回对象封装
 * 
 * @author admin
 *
 */
public class Response implements Serializable{

	/**
     * 返回客户端统一格式，包括状态码，提示信息，以及业务数据
     */
    private static final long serialVersionUID = 1L;
    //必要的提示信息
    private String message;
    //业务数据
    private Object data;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	public String toString(){
        if(null == this.data){
            this.setData(new Object());
        }
        return JSON.toJSONString(this);
    }

    
}
