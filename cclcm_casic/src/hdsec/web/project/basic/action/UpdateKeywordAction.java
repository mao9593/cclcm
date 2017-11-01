package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysKeyword;

import java.util.Date;

/**
 * 修改关键字
 * 
 * @author lixiang
 * 
 */
public class UpdateKeywordAction extends BasicBaseAction {

	private static final long serialVersionUID = 1L;
	private String keyword_code = "";// 主键
	private String keyword_content = "";// 关键字内容
	private String keyword_detail = "";// 具体说明
	private SysKeyword keyword = null;
	private String update = "N";

	public void setKeyword_code(String keyword_code) {
		this.keyword_code = keyword_code;
	}

	public void setKeyword_content(String keyword_content) {
		this.keyword_content = keyword_content;
	}

	public void setKeyword_detail(String keyword_detail) {
		this.keyword_detail = keyword_detail;
	}

	public SysKeyword getKeyword() {
		return keyword;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("N")) {
			keyword = basicService.getKeywordByCode(keyword_code);
			return SUCCESS;
		} else {
			// 查重
			boolean is_keyword_exist = basicService.isKeywordExist(keyword_content);
			String code = basicService.getKeywordCodeByContent(keyword_content);
			if (is_keyword_exist && !code.equals(keyword_code)) {// 查重
				throw new Exception("该关键字已存在，请更换");
			} else {
				SysKeyword keyword = new SysKeyword(keyword_code, keyword_content, keyword_detail, new Date());
				basicService.updateKeyword(keyword);
				insertAdminLog("修改关键字：关键字[" + keyword_content + "],说明[" + keyword_detail + "].");
				return "ok";
			}
		}
	}

}
