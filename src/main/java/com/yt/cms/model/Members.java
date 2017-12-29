package com.yt.cms.model;

import java.util.Date;

public class Members {
    private Integer id;

    private String uname;

    private String pwd;

    private Date regDate;

    private Integer isUse;

    private Integer isGag;

    private MemberInfos infos;
    
    public MemberInfos getInfos() {
		return infos;
	}

	public void setInfos(MemberInfos infos) {
		this.infos = infos;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public Integer getIsGag() {
		return isGag;
	}

	public void setIsGag(Integer isGag) {
		this.isGag = isGag;
	}

   
}