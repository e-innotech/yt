package com.yt.cms.model;
/**
 * many to many change body
 * @author admin
 *
 */
public class M2MUpdateBody {

	public M2MUpdateBody() {
		super();
	}

	Integer masterId;
	Integer[] old_slaveIds;
	Integer[] new_slaveIds;
	public Integer getMasterId() {
		return masterId;
	}
	public void setMasterId(Integer masterId) {
		this.masterId = masterId;
	}
	public Integer[] getOld_slaveIds() {
		return old_slaveIds;
	}
	public void setOld_slaveIds(Integer[] old_slaveIds) {
		this.old_slaveIds = old_slaveIds;
	}
	public Integer[] getNew_slaveIds() {
		return new_slaveIds;
	}
	public void setNew_slaveIds(Integer[] new_slaveIds) {
		this.new_slaveIds = new_slaveIds;
	}

	

	

}
