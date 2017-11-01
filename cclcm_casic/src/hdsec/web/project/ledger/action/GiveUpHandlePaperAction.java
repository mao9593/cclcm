package hdsec.web.project.ledger.action;

import org.springframework.util.StringUtils;

/**
 * 撤销文件闭环申请
 * 
 * @author lixiang
 * 
 */
public class GiveUpHandlePaperAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String paper_id = "";
	private String type = "";

	public void setType(String type) {
		this.type = type;
	}

	public void setPaper_id(String paper_id) {
		this.paper_id = paper_id;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(paper_id)) {
			ledgerService.giveUpHandlePaperById(paper_id);
		}
		insertCommonLog("撤销文件闭环申请[" + paper_id + "]");
		return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
	}
}
