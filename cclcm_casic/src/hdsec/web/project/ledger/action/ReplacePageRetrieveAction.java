package hdsec.web.project.ledger.action;

import hdsec.web.project.ledger.model.EntityPaper;

import java.util.HashMap;
import java.util.Map;

/**
 * 替换页分页回收 2015-3-28
 * 
 * @author chenrongrong
 */
public class ReplacePageRetrieveAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private Integer paper_id = null;
	private EntityPaper entityPaper = null;
	private String retrieved_ok = "";
	private String retrieve_pagenum = "";
	private String fail_remark = "";
	private String pagenum = "";

	public Integer getPaper_id() {
		return paper_id;
	}

	public void setPaper_id(Integer paper_id) {
		this.paper_id = paper_id;
	}

	public EntityPaper getEntityPaper() {
		return entityPaper;
	}

	public void setEntityPaper(EntityPaper entityPaper) {
		this.entityPaper = entityPaper;
	}

	public void setRetrieved_ok(String retrieved_ok) {
		this.retrieved_ok = retrieved_ok;
	}

	public void setRetrieve_pagenum(String retrieve_pagenum) {
		this.retrieve_pagenum = retrieve_pagenum;
	}

	public void setFail_remark(String fail_remark) {
		this.fail_remark = fail_remark;
	}

	public void setPagenum(String pagenum) {
		this.pagenum = pagenum;
	}

	public String getPagenum() {
		return pagenum;
	}

	@Override
	public String executeFunction() throws Exception {
		if (retrieved_ok.equals("Y")) {
			entityPaper = ledgerService.getPaperById("" + paper_id);

			Map<String, Object> map = new HashMap<String, Object>();
			if (entityPaper.getRetrieve_pagenum() != null && !entityPaper.getRetrieve_pagenum().equals("")) {
				retrieve_pagenum = entityPaper.getRetrieve_pagenum() + "," + retrieve_pagenum;
			}
			if (entityPaper.getFail_remark() != null && !entityPaper.getFail_remark().equals("")) {
				fail_remark = entityPaper.getFail_remark() + "," + fail_remark;
			}
			map.put("retrieve_pagenum", retrieve_pagenum);
			map.put("fail_remark", fail_remark);
			map.put("paper_id", paper_id);

			// 如果被替换的页码全部被回收，则将该文件回收
			String numStr = getNoRetrievePagenum(entityPaper.getPID_pagenum(), retrieve_pagenum);
			if (numStr.equals("")) {
				if (entityPaper.getRetrieve_replace().equalsIgnoreCase("SUB_RETRIEVED-SUB_DESTROYED")
						|| (entityPaper.getDestroy_pagenum() != null && !entityPaper.getDestroy_pagenum().equals(""))) {
					map.put("retrieve_replace", "ALL_RETRIEVED-SUB_DESTROYED");
				} else {
					map.put("retrieve_replace", "ALL_RETRIEVED");
				}
			} else {
				if (entityPaper.getRetrieve_replace().equalsIgnoreCase("SUB_RETRIEVED-SUB_DESTROYED")
						|| (entityPaper.getDestroy_pagenum() != null && !entityPaper.getDestroy_pagenum().equals(""))) {
					map.put("retrieve_replace", "SUB_RETRIEVED-SUB_DESTROYED");
				} else {
					map.put("retrieve_replace", "SUB_RETRIEVED");
				}
			}

			ledgerService.updateRetrievedPage(map);
			return null;
		} else {
			entityPaper = ledgerService.getPaperById("" + paper_id);
			pagenum = getNoRetrievePagenum(entityPaper.getPID_pagenum(), entityPaper.getRetrieve_pagenum());
			return SUCCESS;
		}
	}

	// 获取未回收的页码
	public String getNoRetrievePagenum(String PID_pagenum, String retrieve_pagenum) {
		StringBuilder builder = new StringBuilder();
		String[] str = PID_pagenum.split(",");
		int j = 0;
		for (int i = 0; i < str.length; i++) {
			if (!retrieve_pagenum.contains(str[i])) {
				builder.append(str[i] + ",");
			} else {// 解决多次替换同一页不能回收销毁问题,
				if (++j == retrieve_pagenum.split(",").length) {
					for (int k = i + 1; k < str.length; k++, i++) {
						builder.append(str[k] + ",");
					}
				}
			}
		}
		String numStr = "";
		if (builder.length() > 0) {
			numStr = builder.deleteCharAt(builder.length() - 1).toString();
		}
		return numStr;
	}
}
