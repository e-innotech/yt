package com.yt.cms.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class MemberInfos implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
    @NotBlank(message="会员昵称不能为空")
    private String nickName;

    private Integer sex;

    private String birth;

    private String phone;

    private String email;

    private String qq;

    private String address;
    
    private String icon;
    private Integer memberId;
    
    public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName == null ? null : nickName.trim();
	}

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

	public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }
}