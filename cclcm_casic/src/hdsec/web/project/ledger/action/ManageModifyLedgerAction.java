package hdsec.web.project.ledger.action;

import hdsec.web.project.basic.model.SysPrinter;
import hdsec.web.project.ledger.model.EventModify;
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
 * 
 * 处理申请记录
 * 
 * @author congxue 2015-4-29
 */
public class ManageModifyLedgerAction extends LedgerBaseAction {

	private static final long serialVersionUID = 1L;
	private String researchFlag = "N";
	private List<EventModify> eventModidy = null;
	private String paper_barcode = "";
	private String cd_barcode = "";
	private Integer seclv_code = null;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private String entity_type = "";
	private String barcode = "";

	public String getPaper_barcode() {
		return paper_barcode;
	}

	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode.trim();
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

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<SysPrinter> getPrinterList() {
		return basicService.getSysPrinterList();
	}

	public List<EventModify> getEventModidy() {
		return eventModidy;
	}

	public void setEventModidy(List<EventModify> eventModidy) {
		this.eventModidy = eventModidy;
	}

	public String getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}

	public String getResearchFlag() {
		return researchFlag;
	}

	public void setResearchFlag(String researchFlag) {
		this.researchFlag = researchFlag;
	}

	public String getCd_barcode() {
		return cd_barcode;
	}

	public void setCd_barcode(String cd_barcode) {
		this.cd_barcode = cd_barcode;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Override
	public String executeFunction() throws Exception {
		if (researchFlag.equals("Y")) {
			String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
			if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
				page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
			}
			beginIndex = page * pageSize;
			if (entity_type.equals("Paper")) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("user_iidd", getCurUser().getUser_iidd());
				map.put("barcode", barcode);
				map.put("job_status", job_status);
				map.put("seclv_code", seclv_code);
				map.put("start_time", startTime);
				map.put("end_time", endTime);
				map.put("entity_type", entity_type);
				RowBounds rbs = new RowBounds(beginIndex, pageSize);
				// 查询纸质变更记录
				totalSize = ledgerService.getAllEventModifySize(map);
				eventModidy = ledgerService.getAllEventModifyList(map, rbs);
				for (EventModify modify : eventModidy) {
					modify.setPre_seclv_name(ledgerService.getSeclvNameByCode(modify.getPre_seclv()));
					modify.setTrg_seclv_name(ledgerService.getSeclvNameByCode(modify.getTrg_seclv()));
				}

			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("user_iidd", getCurUser().getUser_iidd());
				map.put("barcode", barcode);
				map.put("job_status", job_status);
				map.put("seclv_code", seclv_code);
				map.put("start_time", startTime);
				map.put("end_time", endTime);
				map.put("entity_type", entity_type);
				RowBounds rbs = new RowBounds(beginIndex, pageSize);
				// 查询光盘变更记录
				totalSize = ledgerService.getAllEventCDModifySize(map);
				eventModidy = ledgerService.getAllEventCDModifyList(map, rbs);
				for (EventModify modify : eventModidy) {
					modify.setPre_seclv_name(ledgerService.getSeclvNameByCode(modify.getPre_seclv()));
					modify.setTrg_seclv_name(ledgerService.getSeclvNameByCode(modify.getTrg_seclv()));
				}

			}
		} else {
			researchFlag = "WARING";
		}
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "MODIFY_SECLV", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "MODIFY_SECLV", 3);
		return SUCCESS;
	}
}
