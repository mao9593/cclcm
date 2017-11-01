package hdsec.web.project.ledger.action;

import hdsec.web.project.ledger.model.EntityPaper;

import java.util.HashMap;
import java.util.Map;

/**
 * 替换页分页回收 2015-3-28
 * 
 * @author chenrongrong
 */
public class ReplacePageDestroyAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private Integer paper_id = null;
	private EntityPaper entityPaper = null;
	private String destroyed_ok = "";
	private String destroy_pagenum = "";
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

	public void setDestroyed_ok(String destroyed_ok) {
		this.destroyed_ok = destroyed_ok;
	}

	public void setDestroy_pagenum(String destroy_pagenum) {
		this.destroy_pagenum = destroy_pagenum;
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
		if (destroyed_ok.equals("Y")) {
			entityPaper = ledgerService.getPaperById("" + paper_id);

			Map<String, Object> map = new HashMap<String, Object>();
			if (entityPaper.getDestroy_pagenum() != null && !entityPaper.getDestroy_pagenum().equals("")) {
				destroy_pagenum = entityPaper.getDestroy_pagenum() + "," + destroy_pagenum;
			}

			if (entityPaper.getFail_remark() != null && !entityPaper.getFail_remark().equals("")) {
				fail_remark = entityPaper.getFail_remark() + "," + fail_remark;
			}
			map.put("destroy_pagenum", destroy_pagenum);
			map.put("fail_remark", fail_remark);
			map.put("paper_id", paper_id);

			// 如果被替换的页码全部被销毁，则将该文件销毁
			String numStr = getNoDestroyPagenum(entityPaper.getPID_pagenum(), destroy_pagenum);
			if (numStr.equals("")) {
				map.put("retrieve_replace", "ALL_DESTROYED");
			} else {
				if (entityPaper.getRetrieve_replace().equalsIgnoreCase("ALL_RETRIEVED")
						|| entityPaper.getRetrieve_replace().equalsIgnoreCase("ALL_RETRIEVED-SUB_DESTROYED")) {
					map.put("retrieve_replace", "ALL_RETRIEVED-SUB_DESTROYED");
				} else {
					map.put("retrieve_replace", "SUB_RETRIEVED-SUB_DESTROYED");
				}
			}

			ledgerService.updateDestroyedPage(map);
			return "ok";
		} else {
			entityPaper = ledgerService.getPaperById("" + paper_id);
			pagenum = getNoDestroyPagenum(entityPaper.getRetrieve_pagenum(), entityPaper.getDestroy_pagenum());
			return SUCCESS;
		}
	}

	// 获取待销毁的页码
	public String getNoDestroyPagenum(String retrieve_pagenum, String destroy_pagenum) {
		StringBuilder builder = new StringBuilder();
		String[] str = retrieve_pagenum.split(",");
		int j = 0;
		for (int i = 0; i < str.length; i++) {
			if (!destroy_pagenum.contains(str[i])) {
				builder.append(str[i] + ",");
			} else {// 解决多次替换同一页不能回收销毁问题,
				if (++j == destroy_pagenum.split(",").length) {
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
