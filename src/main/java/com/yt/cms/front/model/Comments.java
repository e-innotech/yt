package com.yt.cms.front.model;

import java.io.Serializable;
import java.util.Date;

public class Comments implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	/**
	 * 评论时间
	 */
    private String createDate;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论人昵称
     */
    private String nickName;
    /**
     * 评论人注册时间
     */
    private Date membersRegtime;
    /**
     * 评论人图像地址
     */
    private String membersPic;
    
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getMembersRegtime() {
		return membersRegtime;
	}

	public void setMembersRegtime(Date membersRegtime) {
		this.membersRegtime = membersRegtime;
	}

	public String getMembersPic() {
		return membersPic;
	}

	public void setMembersPic(String membersPic) {
		this.membersPic = membersPic;
	}


    
}