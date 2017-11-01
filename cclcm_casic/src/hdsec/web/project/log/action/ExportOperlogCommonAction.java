package hdsec.web.project.log.action;

import hdsec.web.project.log.model.CommonOperLog;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 导出操作日志
 * 
 * @author renmingfei
 * 
 */
public class ExportOperlogCommonAction extends LogExportXlsAction {
	private static final long serialVersionUID = 1L;
	private List<CommonOperLog> logList = null;
	private String user_name = "";
	private String dept_name = "";
	private String result = "";
	private Date startTime = null;
	private Date endTime = null;
	private String filename = "用户操作日志-" + sdf1.format(new Date()) + ".xls";
	private String sheetName = "用户操作日志";
	private HSSFWorkbook wb = new HSSFWorkbook();
	private String[] titles = { "用户ID", "用户名", "归属部门", "日志内容", "操作结果", "时间", "登录IP", "主机名称", "子系统" };

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public void setResult(String result) {
		this.result = result;
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
		map.put("user_name", user_name);
		map.put("dept_name", dept_name);
		map.put("result", result);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		cellstyle = wb.createCellStyle();
		cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
		os = createFile(filename);
		int size = 0;
		while (prestart || (logList != null && size == SHEET_ROW_NUM)) {
			beginIndex = sheetNum * SHEET_ROW_NUM;
			logList = logService.getCommonOperLog(map, new RowBounds(beginIndex, SHEET_ROW_NUM));
			size = logList.size();
			sheet = createSheet(wb, sheetName, sheetNum, titles);
			fillData(sheet, 1);
			prestart = false;
			sheetNum++;
			logList.clear();
		}
		wb.write(os);
		os.flush();
		os.close();
		return null;
	}

	@Override
	protected void fillData(HSSFSheet sheet, int startIndex) {
		int index = startIndex;
		if (logList != null && logList.size() > 0) {
			for (CommonOperLog log : logList) {
				HSSFRow row = sheet.createRow(index++);
				insertRow(row, log);
			}
		}
	}

	private void insertRow(HSSFRow row, CommonOperLog log) {
		int index = 0;
		cteateCell(row, index++, log.getUser_id());
		cteateCell(row, index++, log.getUser_name());
		cteateCell(row, index++, log.getDept_name());
		cteateCell(row, index++, log.getLog_detail());
		cteateCell(row, index++, log.getResult());
		cteateCell(row, index++, log.getLog_time_str());
		// cteateCell(row, index++, String.valueOf(log.getLog_type()));
		cteateCell(row, index++, log.getLogin_ip());
		cteateCell(row, index++, log.getLogin_hostname());
		cteateCell(row, index++, log.getSubsys_str());
	}
}
