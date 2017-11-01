package hdsec.web.project.ledger.action;

import hdsec.web.project.basic.model.SysRecycleBox;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.decorator.CheckboxTableDecorator;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 * 已回收待销毁列表(文件送销)
 * 
 * @author gaoxm
 * 
 */
public class ViewRecycledPaperAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private List<EntityPaper> paperLedgerList = null;
	private String paper_barcode = "";
	private String duty_user_name = "";
	private String seclv_code = "";
	private Date start_time = null;
	private Date end_time = null;
	private String paper_state = "";
	private String retrieve_box_code = "";// 回收柜
	private String conf_code = "";
	private List<String> dept_ids;
	private CheckboxTableDecorator decorator = null;
	private String secError = "N";

	public String getSecError() {
		return secError;
	}

	public void setSecError(String secError) {
		this.secError = secError;
	}

	public CheckboxTableDecorator getDecorator() {
		return decorator;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public List<EntityPaper> getPaperLedgerList() {
		return paperLedgerList;
	}

	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode.trim();
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public String getPaper_barcode() {
		return paper_barcode;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public String getStart_time() {

		return sdf.format(start_time);
	}

	public String getEnd_time() {
		return sdf.format(end_time);
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name.trim();
	}

	public String getPaper_state() {
		return paper_state;
	}

	public void setPaper_state(String paper_state) {
		this.paper_state = paper_state;
	}

	public List<SysRecycleBox> getRecycleBoxList() {
		return basicService.getSysRecycleBoxList();
	}

	public String getRetrieve_box_code() {
		return retrieve_box_code;
	}

	public void setRetrieve_box_code(String retrieve_box_code) {
		this.retrieve_box_code = retrieve_box_code;
	}

	public String getDept_ids() {
		String ret = "";
		for (String d_item : dept_ids) {
			ret += d_item + ",";
		}
		if (ret.endsWith(",")) {
			ret = ret.substring(0, ret.length() - 1);
		}
		return ret;
	}

	@Override
	public String executeFunction() throws Exception {
		String web_url = getModuleName().toLowerCase() + "/" + getTitleName().toLowerCase() + ".action";
		dept_ids = basicService.getAdminDeptIdList(getCurUser().getUser_iidd(), web_url);
		if (dept_ids == null || dept_ids.size() == 0) {
			throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
		}
		List<EntityPaper> entityList = new ArrayList<EntityPaper>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_recycled", true);
		map.put("paper_barcode", paper_barcode);
		map.put("duty_user_name", duty_user_name);
		map.put("seclv_code", seclv_code);
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		map.put("paper_state", paper_state);
		map.put("retrieve_box_code", retrieve_box_code);
		map.put("admin_dept_ids", dept_ids);
		map.put("retrieve_time_sortable", true);
		map.put("time_type", "retrieve");

		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = ledgerService.getAllPaperLedgerSize(map);
		List<EntityPaper> papers = ledgerService.getAllPaperLedgerList(map, rbs);
		if ((null != papers && totalSize != 0) || totalSize > papers.size()) {
			papers = ledgerService.getAllPaperLedgerList(map);
		}
		for (EntityPaper entity : papers) {
			entityList.add(entity);
		}
		paperLedgerList = entityList;
		decorator = new org.displaytag.decorator.CheckboxTableDecorator();
		decorator.setId("paper_barcode");
		decorator.setFieldName("_chk");
		return SUCCESS;
	}
}
