package hdsec.web.project.arch.action;

import hdsec.web.project.arch.model.ArchKey;
import hdsec.web.project.arch.model.ArchTypeName;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.StringUtils;

/**
 * 批量导入档案
 * 
 * @author handouwang
 * 
 *         2015-7-29/
 */
public class BatchImportArchAction extends ArchBaseAction {
	private static final long serialVersionUID = 1L;
	private int id = 0;
	private HSSFCellStyle cellstyle = null;
	private HSSFWorkbook wb = null;
	private String msg = "";

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ArchTypeName> getArchTypeList() {
		return archService.getValidTypeList();
	}

	private void downloadTemplate(String template_id, String type_name)
			throws IOException {
		OutputStream os = null;
		BufferedOutputStream bos = null;
		HttpServletResponse response = getResponse();
		response.reset();
		response.setHeader("Content-disposition", "attachment;filename="
				+ template_id + ".xls");// 防止模板文件名中汉字乱码
		response.setContentType("application/msexcel");
		os = response.getOutputStream();
		bos = new BufferedOutputStream(os);
		HSSFSheet sh = wb.createSheet(type_name);
		addTitle(sh, template_id);
		wb.write(bos);
		if (null != bos) {
			bos.flush();
			bos.close();
		}
		if (null != os) {
			os.close();
		}
	}

	private void addTitle(HSSFSheet sh, String template_id) {
		ArchKey key = archService.getTemplateBySystemCode(template_id);
		HSSFRow row = sh.createRow(0);
		int index = 0;
		cteateCell(row, index++, "案卷号");
		cteateCell(row, index++, getTitle(key.getBarcode()));
		cteateCell(row, index++, getTitle(key.getDos_num()));
		cteateCell(row, index++, getTitle(key.getArch_num()));
		cteateCell(row, index++, getTitle(key.getType_code()));
		cteateCell(row, index++, getTitle(key.getFile_title()));
		cteateCell(row, index++, getTitle(key.getFile_num()));
		cteateCell(row, index++, getTitle(key.getPage_num()));
		cteateCell(row, index++, getTitle(key.getCount()));
		cteateCell(row, index++, getTitle(key.getTotal_page()));
		cteateCell(row, index++, getTitle(key.getSeclv_code()));
		cteateCell(row, index++, getTitle(key.getFile_date()));
		cteateCell(row, index++, getTitle(key.getFile_carr()));
		cteateCell(row, index++, getTitle(key.getKeep_limit()));
		cteateCell(row, index++, getTitle(key.getSumm()));
		cteateCell(row, index++, getTitle(key.getT01()));
		cteateCell(row, index++, getTitle(key.getT02()));
		cteateCell(row, index++, getTitle(key.getT03()));
		cteateCell(row, index++, getTitle(key.getT04()));
		cteateCell(row, index++, getTitle(key.getT05()));
		cteateCell(row, index++, getTitle(key.getT06()));
		cteateCell(row, index++, getTitle(key.getT07()));
		cteateCell(row, index++, getTitle(key.getT08()));
		cteateCell(row, index++, getTitle(key.getT09()));
		cteateCell(row, index++, getTitle(key.getT10()));
		cteateCell(row, index++, getTitle(key.getT11()));
		cteateCell(row, index++, getTitle(key.getT12()));
		cteateCell(row, index++, getTitle(key.getT13()));
		cteateCell(row, index++, getTitle(key.getT14()));
		cteateCell(row, index++, getTitle(key.getT15()));
		cteateCell(row, index++, getTitle(key.getT16()));
		cteateCell(row, index++, getTitle(key.getT17()));
		cteateCell(row, index++, getTitle(key.getT18()));
		cteateCell(row, index++, getTitle(key.getT19()));
		cteateCell(row, index++, getTitle(key.getT20()));
		cteateCell(row, index++, getTitle(key.getT21()));
		cteateCell(row, index++, getTitle(key.getT22()));
		cteateCell(row, index++, getTitle(key.getT23()));
		cteateCell(row, index++, getTitle(key.getT24()));
		cteateCell(row, index++, getTitle(key.getT25()));
	}

	private String getTitle(String temKey) {
		return temKey == null ? "" : temKey.substring(0, temKey.indexOf("^"));
	}

	private void cteateCell(HSSFRow row, int col, String val) {
		HSSFCell cell = row.createCell(col);
		if (StringUtils.hasLength(val)) {
			cell.setCellValue(val);
		} else {// 如果没有值为空，则插入一个空格，否则导出的excel格式会不统一
			cell.setCellValue(" ");
		}
		cell.setCellStyle(cellstyle);
	}

	private void init() {
		wb = new HSSFWorkbook();
		cellstyle = wb.createCellStyle();
		cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
	}

	@Override
	public String executeFunction() throws Exception {
		if (id != 0) {
			ArchTypeName type = archService.getArchTypeNameById(id);
			if (type != null) {
				init();
				downloadTemplate(type.getTemplate_id(), type.getType_name());
			}
			return null;
		} else {
			return SUCCESS;
		}
	}
}
