package hdsec.web.project.ledger.action;

import hdsec.web.project.basic.model.SysPrinter;
import hdsec.web.project.common.util.TimeUtil;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
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

public class ViewModifyLedgerAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private List<EntityPaper> paperLedgerList = null;
	private String researchFlag = "N";
	private String create_type = "";// 制作方式
	private String barcode = "";// 载体编码
	// private String file_title = "";// 文件名
	private String seclv_code = "";// 密级
	private String event_state = "";// 载体状态
	private Date startTime = null;
	private Date endTime = null;
	private CheckboxTableDecorator decorator = null;
	private String keyword_content = "";// 关键字
	private Integer expire_status = null;// 到期状态
	private Date make_time = null;// 载体时间
	private String paper_barcode = "";
	private String entity_type = "";
	private List<EntityCD> cdLedgerList = null;
	private String cd_barcode = "";
	private String cd_state = "";

	public String getResearchFlag() {
		return researchFlag;
	}

	public void setResearchFlag(String researchFlag) {
		this.researchFlag = researchFlag;
	}

	public String getCreate_type() {
		return create_type;
	}

	public void setCreate_type(String create_type) {
		this.create_type = create_type;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getEvent_state() {
		return event_state;
	}

	public void setEvent_state(String event_state) {
		this.event_state = event_state;
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

	public CheckboxTableDecorator getDecorator() {
		return decorator;
	}

	public void setDecorator(CheckboxTableDecorator decorator) {
		this.decorator = decorator;
	}

	public String getKeyword_content() {
		return keyword_content;
	}

	public void setKeyword_content(String keyword_content) {
		this.keyword_content = keyword_content.trim();
	}

	public Integer getExpire_status() {
		return expire_status;
	}

	public void setExpire_status(Integer expire_status) {
		this.expire_status = expire_status;
	}

	public Date getMake_time() {
		return make_time;
	}

	public void setMake_time(Date make_time) {
		this.make_time = make_time;
	}

	public List<EntityPaper> getPaperLedgerList() {
		return paperLedgerList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<SysPrinter> getPrinterList() {
		return basicService.getSysPrinterList();
	}

	public String getPaper_barcode() {
		return paper_barcode;
	}

	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode;
	}

	public String getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}

	public List<EntityCD> getCdLedgerList() {
		return cdLedgerList;
	}

	public void setCdLedgerList(List<EntityCD> cdLedgerList) {
		this.cdLedgerList = cdLedgerList;
	}

	public String getCd_barcode() {
		return cd_barcode;
	}

	public void setCd_barcode(String cd_barcode) {
		this.cd_barcode = cd_barcode;
	}

	public String getCd_state() {
		return cd_state;
	}

	public void setCd_state(String cd_state) {
		this.cd_state = cd_state;
	}

	public void setPaperLedgerList(List<EntityPaper> paperLedgerList) {
		this.paperLedgerList = paperLedgerList;
	}

	public List<EntityStateEnum> getStateList() {

		return EntityStateEnum.getEntityStateList();
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
			if (entity_type.equals("Paper")) {
				String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
				if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
					page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
				}
				beginIndex = page * pageSize;
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("user_iidd", getCurUser().getUser_iidd());
				map.put("paper_barcode", barcode);
				map.put("seclv_code", seclv_code);
				map.put("end_time", endTime);
				map.put("start_time", startTime);
				map.put("paper_state", "0");
				map.put("expire_status", expire_status);
				map.put("keyword_content", keyword_content);
				map.put("coming_expire_time", TimeUtil.getAfterXDay(2));
				RowBounds rbs = new RowBounds(beginIndex, pageSize);
				totalSize = ledgerService.getAllPaperLedgerSize(map);
				paperLedgerList = ledgerService.getAllPaperLedgerList(map, rbs);
				decorator = new org.displaytag.decorator.CheckboxTableDecorator();
				decorator.setId("paper_barcode");
				decorator.setFieldName("_chk");
				// 删除客户端提醒文件留用过期消息
				map.put("oper_type", "PAPER");
				map.put("message_type", 4);
				clientService.delRetrieveMsgByUser(map);
			} else {
				String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
				if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
					page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
				}
				beginIndex = page * pageSize;
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("user_iidd", getCurUser().getUser_iidd());
				map.put("cd_barcode", barcode);
				map.put("seclv_code", seclv_code);
				map.put("start_time", startTime);
				map.put("end_time", endTime);
				map.put("cd_state", "0");
				map.put("expire_status", expire_status);
				map.put("coming_expire_time", TimeUtil.getAfterXDay(2));
				RowBounds rbs = new RowBounds(beginIndex, pageSize);
				totalSize = ledgerService.getAllCDLedgerSize(map);
				cdLedgerList = ledgerService.getAllCDLedgerList(map, rbs);
				decorator = new org.displaytag.decorator.CheckboxTableDecorator();
				decorator.setId("cd_barcode");
				decorator.setFieldName("_chk");
				// 删除客户端提醒文件留用过期消息
				map.put("oper_type", "CD");
				map.put("message_type", 4);
				clientService.delRetrieveMsgByUser(map);
			}
			// researchFlag = "Y";
		} else {
			researchFlag = "WARING";
		}
		return SUCCESS;
	}
}
