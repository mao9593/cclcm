package hdsec.web.project.print.action;

import hdsec.web.project.ledger.action.LedgerBaseAction;
import hdsec.web.project.ledger.model.EntityPaper;
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
 * 替换页打印 2015-3-25
 * 
 * @author chenrongrong
 */
public class ReplacePagePrintAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String user_iidd = "";
	private List<EntityPaper> paperLedgerList = null;
	private String updateFlag = "N";
	private Integer seclv_code = null;
	private String file_title = "";
	private String paper_barcode = "";
	private Date startTime = null;
	private Date endTime = null;
	private List<SecLevel> seclvList = null;

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public List<EntityPaper> getPaperLedgerList() {
		return paperLedgerList;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getFile_title() {
		return file_title;
	}

	public void setFile_title(String file_title) {
		this.file_title = file_title;
	}

	public String getPaper_barcode() {
		return paper_barcode;
	}

	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode;
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
		return seclvList;
	}

	@Override
	public String executeFunction() throws Exception {
		if (updateFlag.equals("Y")) {
			String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
			if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
				page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
			}
			beginIndex = page * pageSize;
			RowBounds rbs = new RowBounds(beginIndex, pageSize);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_iidd", user_iidd);
			map.put("scope", "PERSON");
			map.put("replacePage", "ReplacePage");
			map.put("create_type", "PRINT");
			map.put("seclv_code", seclv_code);
			map.put("file_title", file_title);
			map.put("paper_barcode", paper_barcode);
			map.put("start_time", startTime);
			map.put("end_time", endTime);
			totalSize = ledgerService.getAllPaperLedgerSize(map);
			paperLedgerList = ledgerService.getAllPaperLedgerList(map, rbs);
			seclvList = userService.getSecLevel();
			return SUCCESS;
		} else {
			return "ok";
		}
	}
}
