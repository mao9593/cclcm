package hdsec.web.project.user.model;

import hdsec.web.project.common.model.UserBaseDomain;
import hdsec.web.project.common.util.Constants;

/**
 * 说明: 角色
 * 
 * @author renmingfei
 * 
 */
public class SecRole extends UserBaseDomain {
	private String role_id = "";
	private String role_name = "";
	private String role_desc = "";
	private String subsys_code = "";
	private Integer role_type;
	private String role_spec_key = "";
	private boolean hasUser;
	private boolean hasRelationship;
	
	public boolean isHasRelationship() {
		return hasRelationship;
	}
	
	public void setHasRelationship(boolean hasRelationship) {
		this.hasRelationship = hasRelationship;
	}
	
	public boolean isHasUser() {
		return hasUser;
	}
	
	public void setHasUser(boolean hasUser) {
		this.hasUser = hasUser;
	}
	
	public String getRole_id() {
		return role_id;
	}
	
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	
	public String getRole_name() {
		return role_name;
	}
	
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	public String getSubsys_code() {
		return subsys_code;
	}
	
	public void setSubsys_code(String subsys_code) {
		this.subsys_code = subsys_code;
	}
	
	public String getRole_desc() {
		return role_desc;
	}
	
	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}
	
	public Integer getRole_type() {
		return role_type;
	}
	
	public void setRole_type(Integer role_type) {
		this.role_type = role_type;
	}
	
	public String getRole_spec_key() {
		return role_spec_key;
	}
	
	public void setRole_spec_key(String role_spec_key) {
		this.role_spec_key = role_spec_key;
	}
	
	public SecRole() {
		super();
	}
	
	public SecRole(String role_name, String role_desc, String subsys_code, Integer role_type, String role_spec_key) {
		super();
		this.role_name = role_name;
		this.role_desc = role_desc;
		this.subsys_code = subsys_code;
		this.role_type = role_type;
		this.role_spec_key = role_spec_key;
	}
	
	public String getRoleTypeName() {
		String name = "";
		switch (this.role_type) {
			case Constants.ROLE_TYPE_HIDDEN:
				name = "内置角色";
				break;
			case Constants.ROLE_TYPE_READONLY:
				name = "三员角色";
				break;
			case Constants.ROLE_TYPE_COMMON:
				name = "普通角色";
				break;
			case Constants.ROLE_TYPE_SPECIAL:
				name = "特殊角色";
				break;
			default:
				name = "非法角色";
				break;
		}
		return name;
	}
}