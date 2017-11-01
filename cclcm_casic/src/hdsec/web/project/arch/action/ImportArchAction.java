package hdsec.web.project.arch.action;

import hdsec.web.project.arch.model.ArchKey;
import hdsec.web.project.arch.model.ArchStatus;
import hdsec.web.project.common.util.ExcelImportUtil;
import hdsec.web.project.exception.CellBlankException;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 批量导入档案
 * 
 * @author handouwang
 * 
 *         2015-7-29/
 */
public class ImportArchAction extends ArchBaseAction {
	private static final long serialVersionUID = 1L;
	private File upload = null;
	private String done = "N";
	private List<Integer> failList = new ArrayList<Integer>();
	private int successNum = 0;
	private String uploadFileName = "";
	private String msg = "";

	public String getMsg() {
		return msg;
	}

	public void setDone(String done) {
		this.done = done;
	}

	public String getDone() {
		return done;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getFailList() {
		return failList.toString();
	}

	public int getSuccessNum() {
		return successNum;
	}

	private String getCellStringValue(HSSFCell cell, boolean is_permit_blank)
			throws CellBlankException {
		return ExcelImportUtil.getCellStringValue(cell, is_permit_blank);
	}

	private String parseTemplateIdFromFileName(String fileName) {
		return fileName.substring(0, fileName.indexOf(".xls"));
	}

	private String getDosIdByCode(String dos_code) throws Exception {
		return archService.getDosIdByCode(dos_code);
	}

	private Map<String, Object> getArchFromRow(HSSFRow row, String template_id,
			int record_count) throws Exception {
		int column = 0;
		int count = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("template_id", template_id);
		map.put("DOS_ID",
				getDosIdByCode(getCellStringValue(row.getCell(column++), false)));// 案卷号
		map.put("BARCODE",
				chkBarcode(getCellStringValue(row.getCell(column++), true))
						.trim());
		map.put("DOS_NUM", getCellStringValue(row.getCell(column++), true));
		map.put("ARCH_NUM", getCellStringValue(row.getCell(column++), true));
		map.put("TYPE_CODE", getCellStringValue(row.getCell(column++), true));
		map.put("FILE_TITLE", getCellStringValue(row.getCell(column++), true));
		map.put("FILE_NUM", getCellStringValue(row.getCell(column++), true));
		map.put("PAGE_NUM", getCellStringValue(row.getCell(column++), true));
		map.put("COUNT", getCellStringValue(row.getCell(column++), true));
		map.put("TOTAL_PAGE", getCellStringValue(row.getCell(column++), true));
		map.put("SECLV_CODE", getCellStringValue(row.getCell(column++), true));
		map.put("FILE_DATE", getCellStringValue(row.getCell(column++), true));
		map.put("FILE_CARR", getCellStringValue(row.getCell(column++), true));
		map.put("KEEP_LIMIT", getCellStringValue(row.getCell(column++), true));
		map.put("SUMM", getCellStringValue(row.getCell(column++), true));
		map.put("STATUS", ArchStatus.AVAILABLE);
		if (++count <= record_count) {
			map.put("T01", getCellStringValue(row.getCell(column++), true));
		}
		if (++count <= record_count) {
			map.put("T02", getCellStringValue(row.getCell(column++), true));
		}
		if (++count <= record_count) {
			map.put("T03", getCellStringValue(row.getCell(column++), true));
		}
		if (++count <= record_count) {
			map.put("T04", getCellStringValue(row.getCell(column++), true));
		}
		if (++count <= record_count) {
			map.put("T05", getCellStringValue(row.getCell(column++), true));
		}
		if (++count <= record_count) {
			map.put("T06", getCellStringValue(row.getCell(column++), true));
		}
		if (++count <= record_count) {
			map.put("T07", getCellStringValue(row.getCell(column++), true));
		}
		if (++count <= record_count) {
			map.put("T08", getCellStringValue(row.getCell(column++), true));
		}
		if (++count <= record_count) {
			map.put("T09", getCellStringValue(row.getCell(column++), true));
		}
		if (++count <= record_count) {
			map.put("T10", getCellStringValue(row.getCell(column++), true));
		}
		if (++count <= record_count) {
			map.put("T11", getCellStringValue(row.getCell(column++), true));
		}
		if (++count <= record_count) {
			map.put("T12", getCellStringValue(row.getCell(column++), true));
		}
		if (++count <= record_count) {
			map.put("T13", getCellStringValue(row.getCell(column++), true));
		}
		if (++count <= record_count) {
			map.put("T14", getCellStringValue(row.getCell(column++), true));
		}
		if (++count <= record_count) {
			map.put("T15", getCellStringValue(row.getCell(column++), true));
		}
		if (++count <= record_count) {
			map.put("T16", getCellStringValue(row.getCell(column++), true));
		}
		if (++count <= record_count) {
			map.put("T17", getCellStringValue(row.getCell(column++), true));
		}
		if (++count <= record_count) {
			map.put("T18", getCellStringValue(row.getCell(column++), true));
		}
		if (++count <= record_count) {
			map.put("T19", getCellStringValue(row.getCell(column++), true));
		}
		if (++count <= record_count) {
			map.put("T20", getCellStringValue(row.getCell(column++), true));
		}
		if (++count <= record_count) {
			map.put("T21", getCellStringValue(row.getCell(column++), true));
		}
		if (++count <= record_count) {
			map.put("T22", getCellStringValue(row.getCell(column++), true));
		}
		if (++count <= record_count) {
			map.put("T23", getCellStringValue(row.getCell(column++), true));
		}
		if (++count <= record_count) {
			map.put("T24", getCellStringValue(row.getCell(column++), true));
		}
		if (++count <= record_count) {
			map.put("T25", getCellStringValue(row.getCell(column++), true));
		}
		return map;
	}

	@Override
	public String executeFunction() throws Exception {
		if (done.equalsIgnoreCase("Y")) {
			if (!uploadFileName.endsWith("xls")) {
				throw new Exception("上传的文件类型错误，请上传EXCEL文件");
			}
			String template_id = parseTemplateIdFromFileName(uploadFileName);
			ArchKey template = archService.getTemplateBySystemCode(template_id);
			// 判断文件名是否被改动过，文件名的template_id是否存在
			if (template == null) {
				throw new Exception("模板文件名已被更改，该模板已不可用，请重新下载模板并填充导入");
			}
			int record_count = template.getRecord_count();
			InputStream myxls = new FileInputStream(upload);
			HSSFWorkbook wb = new HSSFWorkbook(myxls);
			HSSFSheet sheet = wb.getSheetAt(0);// 第一个表单
			Map<String, Object> archMap = null;
			// 循环文档，跳过第一行标题行
			for (int num = sheet.getFirstRowNum() + 1; num <= sheet
					.getLastRowNum(); num++) {
				// 分析每一行数据
				HSSFRow row = sheet.getRow(num);
				try {
					archMap = getArchFromRow(row, template_id, record_count);
					archService.saveArchProperty(archMap);
					successNum++;
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("解析档案数据出现异常：行[" + (num + 1) + "]");
					logger.error(e.getMessage());
					failList.add(num + 1);
					continue;
				}
			}
			insertCommonLog("批量导入档案");
		}
		// msg = "导入成功:" + successNum + "条档案，导入失败的档案行号为：" + failList.toString();
		msg = successNum + ":" + failList.toString();
		return SUCCESS;
	}

	private String chkBarcode(String arch_barcode) throws Exception {
		if (arch_barcode != "") {
			int arch_barcode_count = archService
					.getCountArchBarcodeByArchbarcode(arch_barcode);
			if (arch_barcode_count == 0) {
				return arch_barcode;
			} else {
				throw new Exception("条码已经存在");
			}
		}
		return basicService.createEntityBarcode("ENTERPAPER");
	}
}
