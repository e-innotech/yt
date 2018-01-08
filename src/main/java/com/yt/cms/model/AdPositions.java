package com.yt.cms.model;

public class AdPositions extends BaseVo{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer adWidth;

    private Integer adHeight;

    private String adName;

    private String remark;

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdWidth() {
        return adWidth;
    }

    public void setAdWidth(Integer adWidth) {
        this.adWidth = adWidth;
    }

    public Integer getAdHeight() {
        return adHeight;
    }

    public void setAdHeight(Integer adHeight) {
        this.adHeight = adHeight;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName == null ? null : adName.trim();
    }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}