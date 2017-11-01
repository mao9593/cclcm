package hdsec.web.project.basic.action;

/**
 * 删除关键字
 * 
 * @author lixiang
 * 
 */
public class DelKeywordAction extends BasicBaseAction {

	private static final long serialVersionUID = 1L;
	private String keyword_code = "";

	public void setKeyword_code(String keyword_code) {
		this.keyword_code = keyword_code;
	}

	@Override
	public String executeFunction() throws Exception {
		if (keyword_code.isEmpty()) {
			throw new Exception("参数错误，缺少关键字号");
		} else {
			String keyword_content = basicService.getKeywordByCode(keyword_code).getKeyword_content();
			basicService.delKeywordByCode(keyword_code);
			insertAdminLog("删除关键字[" + keyword_content + "]");
		}
		return SUCCESS;
	}
}
