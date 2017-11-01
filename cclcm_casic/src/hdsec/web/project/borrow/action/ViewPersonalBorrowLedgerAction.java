package hdsec.web.project.borrow.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.borrow.model.EventBorrow;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 * 个人借阅台帐列表管理 2014-4-21 上午9:26:07
 * 
 * @author gaoximin
 */
public class ViewPersonalBorrowLedgerAction extends BorrowBaseAction {
	private static final long serialVersionUID = 1L;
	private List<EventBorrow> eventList = null;
	private String barcode = "";
	private String seclv_code = "";
	private String entity_type = "";
	private Integer borrow_status = null;
	private String job_status = "";// 审批状态
	private Date startTime = null;
	private Date endTime = null;

	public List<EventBorrow> getEventList() {
		return eventList;
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

	public String getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}

	public Integer getBorrow_status() {
		return borrow_status;
	}

	public void setBorrow_status(Integer borrow_status) {
		this.borrow_status = borrow_status;
	}

	public void setEventList(List<EventBorrow> eventList) {
		this.eventList = eventList;
	}

	public String getJob_status() {
		return job_status;
	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public String getStartTime() {
		return startTime == null ? "" : sdf.format(startTime);
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime == null ? "" : sdf.format(endTime);
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public String getActionContext() {
		return "/borrow/viewpersonalborrowledger.action";
	}

	public String getJobType_code() {
		return JobTypeEnum.BORROW.getJobTypeCode();
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("borrow_user_iidd", getCurUser().getUser_iidd());
		map.put("barcode", barcode);
		map.put("seclv_code", seclv_code);
		map.put("entity_type", entity_type);
		map.put("borrow_status", borrow_status);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("job_status", job_status);
		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = borrowService.getAllBorrowLedgerSize(map);
		eventList = borrowService.getAllBorrowLedgerList(map, rbs);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BORROW", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BORROW", 3);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BORROW", 7);
		return SUCCESS;
	}
}
