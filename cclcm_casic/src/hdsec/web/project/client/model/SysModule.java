package hdsec.web.project.client.model;

import hdsec.web.project.common.BaseDomain;

/**
 * 系统功能模块
 * @author gaoxm
 *
 */

public class SysModule extends BaseDomain {
	private String module_code = "";//功能模块编码(主键)
	private String module_name = "";//功能描述
	private String module_enable = "";//有效性（Y具有该功能，N不具有）
	
	public String getModule_code() {
		return module_code;
	}
	
	public void setModule_code(String module_code) {
		this.module_code = module_code;
	}
	
	public String getModule_name() {
		return module_name;
	}
	
	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}
	
	public String getModule_enable() {
		return module_enable;
	}
	
	public String getModule_enable_name() {
		return module_enable.equals("Y") ? "是" : "否";
	}
	
	public void setModule_enable(String module_enable) {
		this.module_enable = module_enable;
	}
	
	public SysModule() {
		super();
	}
	
	public SysModule(String module_code, String module_name, String module_enable) {
		this.module_code = module_code;
		this.module_name = module_name;
		this.module_enable = module_enable;
	}
}
