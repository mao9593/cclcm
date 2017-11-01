package hdsec.web.project.common.action;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.StringUtils;

public abstract class ExportXlsBaseAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	protected static final int SHEET_ROW_NUM = 10000;
	protected SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	protected HSSFCellStyle cellstyle = null;
	protected OutputStream os = null;
	protected int sheetNum = 0;
	protected HSSFSheet sheet = null;
	protected boolean prestart = true;
	
	protected OutputStream createFile(String fileName) throws IOException {
		HttpServletResponse response = getResponse();
		response.reset();
		response.setHeader("Content-disposition", "attachment;filename="
				+ new String(fileName.getBytes(), "iso-8859-1"));//导出日志的名称中汉字的转换
		response.setContentType("application/msexcel");
		return response.getOutputStream();
	}
	
	protected HSSFSheet createSheet(HSSFWorkbook wb, String sheetName, int sheetNum, String[] titles)
			throws IOException {
		HSSFSheet sht = wb.createSheet();
		wb.setSheetName(sheetNum, sheetName + "-" + (sheetNum + 1));
		HSSFRow row = sht.createRow(0);
		sht.createFreezePane(0, 1);
		int index = 0;
		for (String title : titles) {
			cteateCell(row, index++, title);
		}
		return sht;
	}
	
	protected void cteateCell(HSSFRow row, int col, String val) {
		HSSFCell cell = row.createCell(col);
		if (StringUtils.hasLength(val)) {
			cell.setCellValue(val);
		} else {//如果没有值为空，则插入一个空格，否则导出的excel格式会不统一
			cell.setCellValue(" ");
		}
		cell.setCellStyle(cellstyle);
	}
	
	protected abstract void fillData(HSSFSheet sheet, int startIndex);
}
