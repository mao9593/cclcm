package hdsec.web.project.ledger.action;

import hdsec.web.project.ledger.model.EventLog;
import hdsec.web.project.ledger.service.LedgerService;
import hdsec.web.project.log.action.LogExportXlsAction;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 导出用户预台帐日志
 * 
 * @author zp
 * 
 */
public class ExportUnLockEventLogAction extends LogExportXlsAction {
	private static final long serialVersionUID = 1L;
	@Resource
	protected LedgerService ledgerService;
	private List<EventLog> eventLogs = null;
	private Date startTime;
	private Date endTime;
	private String user_name = "";
	private String dept_name = "";
	private String console_name = "";

	private String filename = "用户预台帐日志-" + sdf1.format(new Date()) + ".xls";
	private String sheetName = "用户预台帐日志";
	private HSSFWorkbook wb = new HSSFWorkbook();
	private String[] titles = { "用户ID", "用户名", "归属部门", "控制台名称", "文件名", "解锁时间" };

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("user_name", user_name);
		map.put("dept_name", dept_name);
		map.put("console_name", console_name);
		cellstyle = wb.createCellStyle();
		cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
		os = createFile(filename);
		int size = 0;
		while (prestart || (eventLogs != null && size == SHEET_ROW_NUM)) {
			beginIndex = sheetNum * SHEET_ROW_NUM;
			eventLogs = ledgerService.getEventLogAll(map);// logService.getUserLoginLog(map, new RowBounds(beginIndex,
															// SHEET_ROW_NUM))
			size = eventLogs.size();
			sheet = createSheet(wb, sheetName, sheetNum, titles);
			fillData(sheet, 1);
			prestart = false;
			sheetNum++;
			eventLogs.clear();
		}
		wb.write(os);
		os.flush();
		os.close();
		return null;
	}

	@Override
	protected void fillData(HSSFSheet sheet, int startIndex) {
		int index = startIndex;
		if (eventLogs != null && eventLogs.size() > 0) {
			for (EventLog log : eventLogs) {
				HSSFRow row = sheet.createRow(index++);
				insertRow(row, log);
			}
		}
	}

	private void insertRow(HSSFRow row, EventLog log) {
		int index = 0;
		cteateCell(row, index++, log.getUser_iidd());
		cteateCell(row, index++, log.getUser_name());
		cteateCell(row, index++, log.getDept_name());
		cteateCell(row, index++, log.getConsole_name());
		cteateCell(row, index++, log.getFile_title());
		cteateCell(row, index++, log.getUnlock_time_name());
	}
}
