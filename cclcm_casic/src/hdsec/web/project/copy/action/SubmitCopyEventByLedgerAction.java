package hdsec.web.project.copy.action;

import hdsec.web.project.ledger.action.LedgerBaseAction;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.user.model.SecLevel;

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
 * 查看个人留用纸质台帐
 * 
 * @author lixiang
 * 
 */
public class SubmitCopyEventByLedgerAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private List<EntityPaper> paperLedgerList;
	private String paper_barcode;
	private String seclv_code;
	private Date start_time;
	private Date end_time;
	private String paper_state;
	private String duty_user_name = "";
	private String duty_dept_name = "";

	private CheckboxTableDecorator decorator = null;

	public List<EntityPaper> getPaperLedgerList() {
		return paperLedgerList;
	}

	public String getPaper_barcode() {
		return paper_barcode;
	}

	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode.trim();
	}

	public String getPaper_state() {
		return paper_state;
	}

	public void setPaper_state(String paper_state) {
		this.paper_state = paper_state;
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

	public List<SecLevel> getSeclvList() {
		return userService.getCopySecLevelByUser(getCurUser().getUser_iidd());
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public CheckboxTableDecorator getDecorator() {
		return decorator;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public String getDuty_dept_name() {
		return duty_dept_name;
	}

	public void setDuty_dept_name(String duty_dept_name) {
		this.duty_dept_name = duty_dept_name;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("paper_barcode", paper_barcode);
		map.put("seclv_code", seclv_code);
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		map.put("paper_state", "0");
		map.put("duty_user_name", duty_user_name);
		map.put("duty_dept_name", duty_dept_name);
		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = ledgerService.getAllPaperLedgerSize(map);
		paperLedgerList = ledgerService.getAllPaperLedgerList(map, rbs);

		decorator = new org.displaytag.decorator.CheckboxTableDecorator();
		decorator.setId("paper_barcode");
		decorator.setFieldName("_chk");

		return SUCCESS;
	}

}
