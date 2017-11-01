package hdsec.web.project.user.action;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.model.SecOperation;

import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 添加操作节点
 * @author renmingfei
 *
 */
public class AddSecOperAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String subsys_code = "";
	private SecOperation secOper = null;
	private String newOper_code = "";
	private String oper_code = "";
	private String insert = "N";
	private String oper_name = "";
	private String en_directory = "";
	private String oper_desc = "";
	private String web_url = "";
	private String icon_path = "";
	private String web_mark = "";
	private String server_mark = "";
	private String operType = "";
	
	public String getSubsys_code() {
		return subsys_code;
	}
	
	public void setSubsys_code(String subsys_code) {
		this.subsys_code = subsys_code;
	}
	
	public String getNewOper_code() {
		return newOper_code;
	}
	
	public void setNewOper_code(String newOper_code) {
		this.newOper_code = newOper_code;
	}
	
	public void setOper_code(String oper_code) {
		this.oper_code = oper_code;
	}
	
	public String getOper_code() {
		return oper_code;
	}
	
	public void setInsert(String insert) {
		this.insert = insert;
	}
	
	public void setOper_name(String oper_name) {
		this.oper_name = oper_name;
	}
	
	public void setEn_directory(String en_directory) {
		this.en_directory = en_directory;
	}
	
	public String getEn_directory() {
		return en_directory;
	}
	
	public void setOper_desc(String oper_desc) {
		this.oper_desc = oper_desc;
	}
	
	public void setWeb_url(String web_url) {
		this.web_url = web_url.trim();
	}
	
	public void setIcon_path(String icon_path) {
		this.icon_path = icon_path;
	}
	
	public void setWeb_mark(String web_mark) {
		this.web_mark = web_mark;
	}
	
	public void setServer_mark(String server_mark) {
		this.server_mark = server_mark;
	}
	
	public String getOperType() {
		return operType;
	}
	
	public void setOperType(String operType) {
		this.operType = operType;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (insert.equalsIgnoreCase("Y")) {
			if (userService.getOperCountByCode(oper_code) > 0) {//如果该操作编码已经存在，不能重复添加
				//无法添加的时候是否需要返回页面提示？
				throw new Exception("操作编码:" + oper_code + " 已经存在，不能重复添加。");
			} else {//添加节点
				secOper = new SecOperation();
				secOper.setOper_code(oper_code);
				secOper.setOper_desc(oper_desc);
				secOper.setOper_name(oper_name);
				secOper.setSubsys_code(subsys_code);
				secOper.setEn_directory(en_directory);
				secOper.setEn_prvt_oper("N");
				secOper.setWeb_url(web_url);
				if (StringUtils.hasLength(icon_path)) {
					secOper.setIcon_path(icon_path);
				} else {
					secOper.setIcon_path(Constants.DEFAULT_ICON_PATH);
				}
				secOper.setWeb_mark(web_mark);
				secOper.setServer_mark(server_mark);
				userService.addSecOper(secOper);
			}
			return "insert";
		} else {//返回添加页面
			//取得对应的operation,并生成合适的子操作编码
			List<SecOperation> subOperList = userService.getSubOperListByCode(oper_code + "__", subsys_code);
			String temp1 = "";
			String temp2 = "";
			String temp3 = "";
			String temp4 = "";//新节点的编码
			if (subOperList.size() > 0) {
				//子操作列表是按oper_code倒序查询出来的，所以第一个的code最大
				temp1 = subOperList.get(0).getOper_code();//子操作中最大的编码
				temp2 = temp1.substring(temp1.length() - 2);//子编码的最后两位
				temp3 = temp1.substring(0, temp1.length() - 2);//排除最后两位的编码
				int a = Integer.parseInt(temp2) + 1;
				if (a < 10) {
					temp2 = "0" + a;
				} else {
					temp2 = String.valueOf(a);
				}
				temp4 = temp3 + temp2;
			} else {
				temp4 = oper_code + "01";
			}
			setNewOper_code(temp4);
			setEn_directory(temp4.length() <= 6 ? "Y" : "N");
			setOperType(SecOperation.getOperTypeByCode(temp4));
		}
		return SUCCESS;
	}
}
