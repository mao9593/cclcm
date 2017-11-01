package hdsec.web.project.ledger.action;

import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityStateEnum;
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
 * 查看委托刻录光盘台账列表
 * 
 * @author haojia
 * 
 */
public class ViewProxyCDLedgerAction extends LedgerBaseAction {

	private static final long serialVersionUID = 1L;
	private List<EntityCD> cdLedgerList = null;
	private String cd_barcode = "";
	private String seclv_code = "";
	private Date startTime = null;
	private Date endTime = null;
	private String secError = "N";
	private CheckboxTableDecorator decorator = null;
	private String cd_state = "";
	private String conf_code = "";
	private Integer expire_status = null;

	public String getSecError() {
		return secError;
	}

	public void setSecError(String secError) {
		this.secError = secError;
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

	/*
	 * public CheckboxTableDecorator getDecorator() { return decorator; }
	 */

	public String getCd_state() {
		return cd_state;
	}

	public void setCd_state(String cd_state) {
		this.cd_state = cd_state;
	}

	public List<EntityCD> getCdLedgerList() {
		return cdLedgerList;
	}

	public void setCdLedgerList(List<EntityCD> cdLedgerList) {
		this.cdLedgerList = cdLedgerList;
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

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code.trim();
	}

	public Integer getExpire_status() {
		return expire_status;
	}

	public void setExpire_status(Integer expire_status) {
		this.expire_status = expire_status;
	}

	@Override
	public String executeFunction() throws Exception {
		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", getCurUser().getUser_iidd());
		map.put("cd_barcode", cd_barcode);
		map.put("cd_state", cd_state);
		map.put("seclv_code", seclv_code);
		map.put("start_time", startTime);
		map.put("end_time", endTime);
		map.put("scope", "PERSON");

		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = ledgerService.getAllCDLedgerSize(map);
		cdLedgerList = ledgerService.getAllCDLedgerList(map, rbs);
		decorator = new org.displaytag.decorator.CheckboxTableDecorator();
		decorator.setId("cd_barcode");
		decorator.setFieldName("_chk");
		// 删除客户端提醒文件留用过期消息
		map.put("oper_type", "BURN");
		map.put("message_type", 4);
		clientService.delRetrieveMsgByUser(map);
		return SUCCESS;
	}

}
