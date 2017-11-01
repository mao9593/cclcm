package hdsec.web.project.ledger.action;

import hdsec.web.project.ledger.model.EntityPaper;

import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 取消载体处理申请
 * 
 * @author renmingfei
 * 
 */
public class CancelHandlePaperAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String paper_id = "";
	private String type = "";
	private String code = "";// 添加台账合并撤销

	public void setCode(String code) {
		this.code = code;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPaper_id(String paper_id) {
		this.paper_id = paper_id;
	}

	@Override
	public String executeFunction() throws Exception {
		if (code.equals("merge") && StringUtils.hasLength(paper_id)) {
			EntityPaper paper = ledgerService.getPaperById(paper_id);
			List<EntityPaper> paperList = ledgerService.getPaperListByJobCode(paper.getJob_code());
			if (paperList != null) {
				for (EntityPaper temp : paperList) {
					ledgerService.cancelHandlePaperById(String.valueOf(temp.getPaper_id()));
					insertCommonLog("取消纸质载体处理申请[" + temp.getPaper_id() + "]");
				}
			}
		} else {
			if (StringUtils.hasLength(paper_id)) {
				ledgerService.cancelHandlePaperById(paper_id);
			}
			insertCommonLog("取消纸质载体处理申请[" + paper_id + "]");
		}
		return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
	}
}
