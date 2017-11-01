package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysKeyword;

import java.util.Date;

public class AddKeywordAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String keyword_code = "";// 主键
	private String keyword_content = "";// 关键字内容
	private String keyword_detail = "";// 具体说明

	public void setKeyword_code(String keyword_code) {
		this.keyword_code = keyword_code;
	}

	public void setKeyword_content(String keyword_content) {
		this.keyword_content = keyword_content;
	}

	public void setKeyword_detail(String keyword_detail) {
		this.keyword_detail = keyword_detail;
	}

	@Override
	public String executeFunction() throws Exception {
		if (keyword_content.isEmpty()) {
			return SUCCESS;
		} else {
			boolean is_keyword_exist = basicService.isKeywordExist(keyword_content);
			if (is_keyword_exist) {// 查重
				throw new Exception("该关键字已存在，请更换");
			} else {
				keyword_code = "KW-" + String.valueOf(System.currentTimeMillis());
				SysKeyword keyword = new SysKeyword(keyword_code, keyword_content, keyword_detail, new Date());
				basicService.addSysKeyword(keyword);
				insertAdminLog("添加关键字[" + keyword_content + "]");
				return "ok";
			}
		}
	}

}
