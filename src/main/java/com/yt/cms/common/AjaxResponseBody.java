package com.yt.cms.common;

import java.io.Serializable;

/**
 * 用于异步请求的统一返回对象封装
 * 
 * @author admin
 *
 */
public class AjaxResponseBody implements Serializable{

	/**
     * 返回客户端统一格式，包括状态码，提示信息，以及业务数据
     */
    private static final long serialVersionUID = 1L;
    //必要的提示信息
    private String msg;
    //业务数据
    private Object data;
	// 成功标记
    private boolean success;
    // 错误码
    private String errCode;
    

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public AjaxResponseBody() {
		super();
	}
	
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public AjaxResponseBody(boolean success,String msg) {
		super();
		this.msg = msg;
		this.success = success;
	}
	public AjaxResponseBody(boolean success,String msg, Object data) {
		super();
		this.success = success;
		this.msg = msg;
		this.data = data;
	}
	
	public AjaxResponseBody(boolean success,String msg, String errCode) {
		super();
		this.success = success;
		this.msg = msg;
		this.errCode = errCode;
	}

/*	public String toString(){
        if(null == this.data){
            this.setData(new Object());
        }
        return JSON.toJSONString(this);
    }*/

    
}
