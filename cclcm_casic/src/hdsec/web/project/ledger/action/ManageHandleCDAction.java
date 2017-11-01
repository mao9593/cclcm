package hdsec.web.project.ledger.action;

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

/**
 * 查看光盘闭环申请记录
 * 
 * @author lixiang
 * 
 */
public class ManageHandleCDAction extends LedgerBaseAction {
	
	private static final long serialVersionUID = 1L;
	private List<EntityCD> cdLedgerList = null;
	private String cd_barcode = "";
	private Integer seclv_code = null;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	
	public String getCd_barcode() {
		return cd_barcode;
	}
	
	public void setCd_barcode(String cd_barcode) {
		this.cd_barcode = cd_barcode.trim();
	}
	
	public Integer getSeclv_code() {
		return seclv_code;
	}
	
	public void setSeclv_code(Integer seclv_code) {
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
	
	@Override
	public String executeFunction() throws Exception {
		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("cd_barcode", cd_barcode);
		map.put("job_status", job_status);
		map.put("seclv_code", seclv_code);
		map.put("start_time", startTime);
		map.put("end_time", endTime);
		map.put("has_job_code", true);
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = ledgerService.getAllCDLedgerSize(map);
		cdLedgerList = ledgerService.getAllCDLedgerList(map, rbs);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "CD", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "CD", 3);
		return SUCCESS;
	}
	
}
