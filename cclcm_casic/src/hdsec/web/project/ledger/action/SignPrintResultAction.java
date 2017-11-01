package hdsec.web.project.ledger.action;

import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.EntityPaper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 标记打印
 * 
 * @author yueying
 * 
 */
public class SignPrintResultAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String paper_id = "";
	private String paper_barcode = "";
	private String fail_remark = "";
	private String sign_ok = "N";
	private String fail_remark_tips = "";
	private Integer page_count = null;
	private String isDestroyPaper = "N";
	private String user_iidd = "";
	private String type = "PAPER";

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getIsDestroyPaper() {
		return isDestroyPaper;
	}

	public void setIsDestroyPaper(String isDestroyPaper) {
		this.isDestroyPaper = isDestroyPaper;
	}

	public String getPaper_barcode() {
		return paper_barcode;
	}

	public String getPaper_id() {
		return paper_id;
	}

	public void setPaper_id(String paper_id) {
		this.paper_id = paper_id;
	}

	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode;
	}

	public String getFail_remark() {
		return fail_remark;
	}

	public void setFail_remark(String fail_remark) {
		this.fail_remark = fail_remark;
	}

	public String getSign_ok() {
		return sign_ok;
	}

	public void setSign_ok(String sign_ok) {
		this.sign_ok = sign_ok;
	}

	public String getFail_remark_tips() {
		return fail_remark_tips;
	}

	public void setFail_remark_tips(String fail_remark_tips) {
		this.fail_remark_tips = fail_remark_tips;
	}

	public Integer getPage_count() {
		return page_count;
	}

	public void setPage_count(Integer page_count) {
		this.page_count = page_count;
	}

	@Override
	public String executeFunction() throws Exception {
		EntityPaper paper = ledgerService.getPaperById(paper_id);
		if (sign_ok.equals("Y")) {
			if (isDestroyPaper.equals("Y")) {
				Date destroy_time = new Date();
				String ids = paper_id + ",";
				String[] paper_ids = ids.split(",");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("paper_ids", paper_ids);
				map.put("paper_barcode", paper_barcode);
				map.put("destroy_time", destroy_time);
				map.put("duty_user_iidd", getCurUser().getUser_iidd());
				map.put("duty_user_name", getCurUser().getUser_name());
				map.put("duty_dept_id", getCurUser().getDept_id());
				map.put("duty_dept_name", getCurUser().getDept_name());
				ledgerService.destroyRetrievedPapers(map);

				CycleItem cycleitem = new CycleItem();
				cycleitem.setBarcode(paper_barcode);
				cycleitem.setEntity_type("PAPER");
				cycleitem.setOper_time(new Date());
				cycleitem.setUser_name(getCurUser().getUser_name());
				cycleitem.setDept_name(getCurUser().getDept_name());
				cycleitem.setOper("DESTORY");
				// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
				// 此处不用启动流程，job_code设置为default
				cycleitem.setJob_code("default");
				ledgerService.addCycleItem(cycleitem);// 全生命周期记录

				insertCommonLog("销毁文件：条码[" + paper_barcode + "]");
			}
			ledgerService.signPaper(paper_id, fail_remark, page_count);

			insertCommonLog("标记文件[" + paper_barcode + "]打印失败");
			return "ok";
		} else {

			if (paper.getPage_count() != null && !paper.getPage_count().equals("")) {
				page_count = paper.getPage_count();
			}
			if (paper.getFail_remark() != null && !paper.getFail_remark().equals("")) {
				fail_remark_tips = paper.getFail_remark();
			}
			return SUCCESS;
		}
	}
}
