package hdsec.web.project.ledger.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

public class ViewSuperviseDestroyCDAction extends LedgerBaseAction {

	private static final long serialVersionUID = 1L;
	private String barcode = "";
	private String seclv_code = "";
	private Date start_time = null;
	private Date end_time = null;

	private List<EntityCD> cdLedgerList = null;
	private final JobTypeEnum jobType = JobTypeEnum.TRANSFER;
	private String cur_user_iidd = "";

	public String getCur_user_iidd() {
		return cur_user_iidd;
	}

	public void setCur_user_iidd(String cur_user_iidd) {
		this.cur_user_iidd = cur_user_iidd;
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode.trim();
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<EntityCD> getCdLedgerList() {
		return cdLedgerList;
	}

	public void setCdLedgerList(List<EntityCD> cdLedgerList) {
		this.cdLedgerList = cdLedgerList;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String temp = getCurUser().getUser_iidd();
		map.put("cd_barcode", barcode);
		map.put("seclv_code", seclv_code);
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		map.put("job_status", "true");
		map.put("cd_state", 4);// 类型已销毁
		map.put("supervise_user_iidd", temp);

		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = ledgerService.getAllCDLedgerSize(map);
		setCdLedgerList(ledgerService.getAllCDLedgerList(map, rbs));

		return SUCCESS;
	}
}
