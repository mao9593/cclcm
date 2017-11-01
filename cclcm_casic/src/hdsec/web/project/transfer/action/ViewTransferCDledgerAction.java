package hdsec.web.project.transfer.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityStateEnum;
import hdsec.web.project.ledger.service.LedgerService;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.decorator.CheckboxTableDecorator;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 * 个人台账管理列表
 * 
 * @author yueying
 * 
 */
public class ViewTransferCDledgerAction extends TransferBaseAction {
	private static final long serialVersionUID = 1L;
	@Resource
	protected LedgerService ledgerService;

	private List<EntityCD> cdLedgerList = null;
	private String cd_barcode = "";
	private String user_name = "";
	private String seclv_code = "";
	private Date start_time = null;
	private Date end_time = null;
	private String cd_state;
	private final String jobType = JobTypeEnum.TRANSFER.getJobTypeCode();
	private CheckboxTableDecorator decorator = null;

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public List<EntityCD> getCDLedgerList() {
		return cdLedgerList;
	}

	public void setCd_barcode(String cd_barcode) {
		this.cd_barcode = cd_barcode.trim();
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name.trim();
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<EntityStateEnum> getStateList() {
		/*
		 * if (getCurUser().is_cycleAdmin()) { return EntityStateEnum.getCycleAdminEntityStateList(); } else if
		 * (getCurUser().is_fileAdmin()) { return EntityStateEnum.getFileAdminEntityStateList(); } else return
		 * EntityStateEnum.getUserEntityStateList();
		 */
		return EntityStateEnum.getEntityStateList();
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("cd_barcode", cd_barcode);
		map.put("seclv_code", seclv_code);
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		map.put("cd_state", cd_state);
		map.put("is_transfer", true);
		map.put("scope", "PERSON");

		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = ledgerService.getAllCDLedgerSize(map);
		List<EntityCD> cds = ledgerService.getAllCDLedgerList(map, rbs);
		if ((null != cds && totalSize != 0) || totalSize > cds.size()) {
			cds = ledgerService.getAllCDLedgerList(map);
		}
		cdLedgerList = cds;
		decorator = new org.displaytag.decorator.CheckboxTableDecorator();
		decorator.setId("cd_id");
		decorator.setFieldName("_chk");
		return SUCCESS;
	}

	public String getCd_barcode() {
		return cd_barcode;
	}

	public String getUser_name() {
		return user_name;
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

	public String getCd_state() {
		return cd_state;
	}

	public void setCd_state(String cd_state) {
		this.cd_state = cd_state;
	}

	public String getJobType() {
		return jobType;
	}

	public CheckboxTableDecorator getDecorator() {
		return decorator;
	}
}
