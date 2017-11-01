package hdsec.web.project.user.model;

import hdsec.web.project.common.model.UserBaseDomain;

/**
 * 用户密级
 * 
 * @author renmingfei
 * 
 */
public class SecLevel extends UserBaseDomain {
	private Integer seclv_code = null;
	private String seclv_name = "";
	private Integer seclv_rank = null;
	private Integer user_count = null;
	private String othername = "";

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getSeclv_name() {
		return seclv_name;
	}

	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name;
	}

	public Integer getSeclv_rank() {
		return seclv_rank;
	}

	public void setSeclv_rank(Integer seclv_rank) {
		this.seclv_rank = seclv_rank;
	}

	public Integer getUser_count() {
		return user_count;
	}

	public void setUser_count(Integer user_count) {
		this.user_count = user_count;
	}

	public String getOthername() {
		return othername;
	}

	public void setOthername(String othername) {
		this.othername = othername;
	}

}
