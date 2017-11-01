package hdsec.web.project.user.model;

import hdsec.web.project.common.model.UserBaseDomain;

/**
 * 功能操作树节点对象
 * @author renmingfei
 *
 */
public class OperTree extends UserBaseDomain {
	
	private String oper_code;
	private String oper_name;
	private String oper_parent_code;
	private String oper_desc;
	
	public String getOper_code() {
		return oper_code;
	}
	
	public void setOper_code(String oper_code) {
		this.oper_code = oper_code;
	}
	
	public String getOper_name() {
		return oper_name;
	}
	
	public void setOper_name(String oper_name) {
		this.oper_name = oper_name;
	}
	
	public String getOper_parent_code() {
		return oper_parent_code;
	}
	
	public void setOper_parent_code(String oper_parent_code) {
		this.oper_parent_code = oper_parent_code;
	}
	
	public String getOper_desc() {
		return oper_desc;
	}
	
	public void setOper_desc(String oper_desc) {
		this.oper_desc = oper_desc;
	}
	
}