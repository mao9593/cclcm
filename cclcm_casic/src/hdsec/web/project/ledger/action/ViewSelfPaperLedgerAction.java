package hdsec.web.project.ledger.action;

import hdsec.web.project.basic.model.SysPrinter;
import hdsec.web.project.common.util.TimeUtil;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.model.EntityStateEnum;
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
 * 查看个人纸质台帐、提交纸质载体处理申请
 * 
 * @author renmingfei
 * 
 */
public class ViewSelfPaperLedgerAction extends LedgerBaseAction {

	private static final long serialVersionUID = 1L;
	private List<EntityPaper> paperLedgerList = null;
	private String paper_barcode = "";
	private Date startTime = null;
	private Date endTime = null;
	private String file_title = "";
	private String is_reprint = "";
	private String secError = "N";
	private CheckboxTableDecorator decorator = null;
	private String paper_state = "";
	private String keyword_content = "";
	private Integer expire_status = null;
	private String seclv_code = "";

	public String getSecError() {
		return secError;
	}

	public void setSecError(String secError) {
		this.secError = secError;
	}

	public String getPaper_barcode() {
		return paper_barcode;
	}

	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode.trim();
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

	public String getFile_title() {
		return file_title;
	}

	public void setFile_title(String file_title) {
		this.file_title = file_title.trim();
	}

	public String getIs_reprint() {
		return is_reprint;
	}

	public void setIs_reprint(String is_reprint) {
		this.is_reprint = is_reprint;
	}

	// public CheckboxTableDecorator getDecorator() {
	// return decorator;
	// }
	//
	public String getPaper_state() {
		return paper_state;
	}

	public void setPaper_state(String paper_state) {
		this.paper_state = paper_state;
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

	public List<EntityPaper> getPaperLedgerList() {
		return paperLedgerList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<SysPrinter> getPrinterList() {
		return basicService.getSysPrinterList();
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
		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("paper_barcode", paper_barcode);
		map.put("paper_state", paper_state);
		map.put("file_title", file_title);
		map.put("is_reprint", is_reprint);
		// map.put("seclv_code", seclv_code);
		map.put("start_time", startTime);
		map.put("end_time", endTime);
		map.put("scope", "PERSON");
		map.put("printResult", true);
		map.put("keyword_content", keyword_content);
		map.put("expire_status", expire_status);
		map.put("expire_time", new Date());
		map.put("coming_expire_time", TimeUtil.getAfterXDay(2));
		if (StringUtils.hasLength(seclv_code)) {
			map.put("seclv_codes", seclv_code.split(","));
		}
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = ledgerService.getAllPaperLedgerSize(map);
		List<EntityPaper> listPaperLedger = ledgerService.getAllPaperLedgerList(map, rbs);
		decorator = new org.displaytag.decorator.CheckboxTableDecorator();
		// 如果该文件被替换，则打印方式改为“替换页打印”
		List<String> paperBarcodeList = ledgerService.getPIDBarcode();
		List<EntityPaper> paper = new ArrayList<EntityPaper>();
		for (int i = 0; i < listPaperLedger.size(); i++) {
			EntityPaper entity_paper = listPaperLedger.get(i);
			if (paperBarcodeList.contains(entity_paper.getPaper_barcode())) {
				entity_paper.setCreate_type("REPLACEPRINT");
				paper.add(entity_paper);
			} else {
				paper.add(entity_paper);
			}
		}
		paperLedgerList = paper;

		decorator.setId("paper_barcode");
		decorator.setFieldName("_chk");
		// 删除客户端提醒文件留用过期消息
		map.put("oper_type", "PRINT");
		map.put("message_type", 4);
		clientService.delRetrieveMsgByUser(map);
		return SUCCESS;
	}
}
