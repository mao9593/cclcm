package hdsec.web.project.ledger.action;

import hdsec.web.project.common.util.TimeUtil;
import hdsec.web.project.ledger.model.ExportPaperEnum;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 导出个人纸质台账
 * 
 * @author yueying
 * 
 */
public class ExportPersonalPaperLedgerAction extends AutoCommonExportAction {
	private static final long serialVersionUID = 1L;

	private String paper_barcode;
	private String seclv_code = "";
	private Date startTime;
	private Date endTime;
	private String file_title;
	private String is_reprint;
	private String paper_state;
	private String keyword_content = "";
	private Integer expire_status = null;
	private final String filename = "Paper台账记录-" + sdf1.format(new Date()) + ".xls";
	private final String sheetName = "Paper台账记录";
	private String cols = "";
	private List<String> tempTitles = new ArrayList<String>();
	private String is_security_result = "";

	public void setIs_security_result(String is_security_result) {
		this.is_security_result = is_security_result;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode.trim();
	}

	public void setPaper_state(String paper_state) {
		this.paper_state = paper_state;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setFile_title(String file_title) {
		this.file_title = file_title.trim();
	}

	public void setIs_reprint(String is_reprint) {
		this.is_reprint = is_reprint;
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

	public void setTempTitles(List<String> tempTitles) {
		tempTitles.add("序号");
		String[] tempcols = cols.split(",");
		for (String string : tempcols) {
			for (ExportPaperEnum item : ExportPaperEnum.values()) {
				if (item.getKey().intValue() == Integer.parseInt(string.trim())) {
					tempTitles.add(item.getName());
					break;
				}
			}
		}
	}

	@Override
	public String executeFunction() throws Exception {
		cols = basicService.getSysConfigItemValue("exportpersonalpaperledger").getItem_value();
		setTempTitles(tempTitles);
		String[] titles = tempTitles.toArray(new String[tempTitles.size()]);
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("user_iidd", getSecUserFromSession().getUser_iidd());
		map.put("paper_barcode", paper_barcode);
		map.put("paper_state", paper_state);
		map.put("file_title", file_title);
		map.put("is_reprint", is_reprint);
		map.put("is_security_result", is_security_result);
		map.put("start_time", startTime);
		map.put("end_time", endTime);
		// map.put("seclv_code", seclv_code);
		map.put("scope", "PERSON");
		map.put("keyword_content", keyword_content);
		map.put("expire_status", expire_status);
		map.put("expire_time", new Date());
		map.put("coming_expire_time", TimeUtil.getAfterXDay(2));
		if (StringUtils.hasLength(seclv_code)) {
			map.put("seclv_codes", seclv_code.split(","));
		}
		OutputStream os = createFile(filename);

		exportFile(os, map, sheetName, titles, "paper", cols);

		return null;
	}
}
