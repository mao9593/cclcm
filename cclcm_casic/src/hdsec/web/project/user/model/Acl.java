package hdsec.web.project.user.model;

/**
 * 说明: 访问控制表的值对象
 * @author: renmingfei
 *
 */
public class Acl {
	
	/** 访问控制表唯一标识键值.
	 * @ibatis.column column="acl_id" */
	private Integer acl_id;
	
	/** 角色ID，与sec_role表关联.
	 * @ibatis.column column="role_id" */
	private String role_id;
	
	/** 操作Code，与sec_oper_xxx表关联.
	 * @ibatis.column column="oper_code" */
	private String oper_code;
	/** 资源类型.
	 * @ibatis.column column=""res_type_code"" */
	private Integer res_type_code;
	
	/** 资源编码.
	 * @ibatis.column column="res_code" */
	private String res_code;
	
	/** 资源编码.
	 * @ibatis.column column="subsys_code" */
	private String subsys_code;
	
	/** 是否为资源规则.
	 * @ibatis.column column="en_rule" */
	private String en_rule;
	
	/** 是否为负权限.
	 * @ibatis.column column="en_deny" */
	private String en_deny;
	
	public Integer getAcl_id() {
		return acl_id;
	}
	
	public Acl() {
	}
	
	public Acl(String subsys_code, String role_id, String oper_code, Integer res_type_code, String en_deny) {
		this.subsys_code = subsys_code;
		this.role_id = role_id;
		this.oper_code = oper_code;
		this.res_type_code = res_type_code;
		this.en_rule = "N";
		this.en_deny = en_deny;
	}
	
	public Acl(String role_id, String oper_code) {
		super();
		this.role_id = role_id;
		this.oper_code = oper_code;
	}
	
	public void setAcl_id(Integer acl_id) {
		this.acl_id = acl_id;
	}
	
	public String getRole_id() {
		return role_id;
	}
	
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	
	public String getOper_code() {
		return oper_code;
	}
	
	public void setOper_code(String oper_code) {
		this.oper_code = oper_code;
	}
	
	public Integer getRes_type_code() {
		return res_type_code;
	}
	
	public void setRes_type_code(Integer res_type_code) {
		this.res_type_code = res_type_code == null ? new Integer(-1) : res_type_code;
	}
	
	public String getRes_code() {
		return res_code;
	}
	
	public void setRes_code(String res_code) {
		this.res_code = res_code;
	}
	
	public String getEn_rule() {
		return en_rule;
	}
	
	public void setEn_rule(String en_rule) {
		this.en_rule = en_rule == null || en_rule.equals("") ? "N" : en_rule;
	}
	
	public String getEn_deny() {
		return en_deny;
	}
	
	public void setEn_deny(String en_deny) {
		this.en_deny = en_deny == null || en_deny.equals("") ? "N" : en_deny;
	}
	
	public String getSubsys_code() {
		return subsys_code;
	}
	
	public void setSubsys_code(String subsys_code) {
		this.subsys_code = subsys_code;
	}
}