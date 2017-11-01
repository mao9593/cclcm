package hdsec.web.project.user.model;

import hdsec.web.project.common.model.UserBaseDomain;

/**
 * 部门地点
 * @author renmingfei
 *
 */
public class SecTerritory extends UserBaseDomain {
	
	private String terr_code;
	private String terr_name;
	private String terr_desc;
	private Integer dept_count;
	
	public String getTerr_code() {
		return terr_code;
	}
	
	public void setTerr_code(String terr_code) {
		this.terr_code = terr_code;
	}
	
	public String getTerr_name() {
		return terr_name;
	}
	
	public void setTerr_name(String terr_name) {
		this.terr_name = terr_name;
	}
	
	public String getTerr_desc() {
		return terr_desc;
	}
	
	public void setTerr_desc(String terr_desc) {
		this.terr_desc = terr_desc;
	}
	
	public Integer getDept_count() {
		return dept_count;
	}
	
	public void setDept_count(Integer dept_count) {
		this.dept_count = dept_count;
	}
	
}
