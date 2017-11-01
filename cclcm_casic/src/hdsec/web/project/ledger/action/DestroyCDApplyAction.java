package hdsec.web.project.ledger.action;

import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityStateEnum;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 * 查看销毁申请记录
 * 
 * @author renmingfei
 * 
 */
public class DestroyCDApplyAction extends LedgerBaseAction {

	private static final long serialVersionUID = 1L;
	private List<EntityCD> cdLedgerList = null;
	private String cd_barcode = "";
	private String seclv_code = "";
	private Date startTime = null;
	private Date endTime = null;
	private String file_list = "";
	private String job_status = "";
	private String cd_state = "";

	public String getCd_state() {
		return cd_state;
	}

	public void setCd_state(String cd_state) {
		this.cd_state = cd_state;
	}

	public String getCd_barcode() {
		return cd_barcode;
	}

	public void setCd_barcode(String cd_barcode) {
		this.cd_barcode = cd_barcode.trim();
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
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

	public String getFile_list() {
		return file_list;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public String getJob_status() {
		return job_status;
	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public List<EntityCD> getCdLedgerList() {
		return cdLedgerList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<EntityStateEnum> getStateList() {
		List<EntityStateEnum> list = new ArrayList<EntityStateEnum>();
		list.add(EntityStateEnum.STATE0);
		list.add(EntityStateEnum.STATE1);
		list.add(EntityStateEnum.STATE4);
		list.add(EntityStateEnum.STATE7);
		return list;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("seclv_code", seclv_code);
		map.put("start_time", startTime);
		map.put("end_time", endTime);
		map.put("cd_barcode", cd_barcode);
		map.put("file_list", file_list);
		map.put("job_status", job_status);
		map.put("cd_state", cd_state);
		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		totalSize = ledgerService.getDestroyCDSize(map);
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		cdLedgerList = ledgerService.getDestroyCDList(map, rbs);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "DESTROY_CD", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "DESTROY_CD", 3);
		return SUCCESS;
	}

}
