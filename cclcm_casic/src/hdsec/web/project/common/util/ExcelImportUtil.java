package hdsec.web.project.common.util;

import hdsec.web.project.exception.CellBlankException;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;

public class ExcelImportUtil {
	private static Logger logger = Logger.getLogger(ExcelImportUtil.class);
	private static SimpleDateFormat sdf = new SimpleDateFormat();
	
	/**
	 * 把单元格中的数据转化为字符串
	 * 2014-5-1 下午4:42:38
	 * 
	 * @author renmingfei
	 * @param cell
	 * @param is_permit_blank
	 *            是否允许该单元格的数据为空
	 * @return
	 * @throws CellBlankException
	 */
	public static String getCellStringValue(HSSFCell cell, boolean is_permit_blank) throws CellBlankException {
		String cellValue = "";
		if (is_permit_blank) {
			if (cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
				cellValue = "";
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
				if (cell.getStringCellValue() == null || cell.getStringCellValue().trim().isEmpty()) {
					cellValue = "";
				} else {
					cellValue = cell.getStringCellValue().trim();
				}
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				cellValue = String.valueOf((int) cell.getNumericCellValue());
			}
		} else {
			if (cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
				throw new CellBlankException();
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING
					&& (cell.getStringCellValue() == null || cell.getStringCellValue().trim().isEmpty())) {
				throw new CellBlankException();
			} else {
				cellValue = cell.getStringCellValue().trim();
			}
		}
		logger.debug("string cellValue is:" + cellValue);
		return cellValue;
	}
	
	/**
	 * 获得日期类数据
	 * 2014-5-1 下午4:41:09
	 * 
	 * @author renmingfei
	 * @param cell
	 * @param is_compatible如果为true
	 *            ,则无法解析为日期数据时，返回null；否则抛异常
	 * @return
	 * @throws CellBlankException
	 */
	public static Date getCellDateValue(HSSFCell cell, boolean is_compatible) {
		Date cellValue = null;
		try {
			cellValue = cell.getDateCellValue();
		} catch (Exception e) {
			logger.info(e.getMessage());
			if (!is_compatible) {
				throw e;
			}
		}
		logger.debug("date cellValue is:" + cellValue == null ? "" : sdf.format(cellValue));
		return cellValue;
	}
	
	/**
	 * 从格式化的字符类型数据中获得日期类数据
	 * 2014-5-5 下午1:01:43
	 * 
	 * @author renmingfei
	 * @param cell
	 * @param is_compatible
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public static Date formatCellDateValue(HSSFCell cell, boolean is_compatible, String pattern) throws Exception {
		Date cellValue = null;
		try {
			sdf.applyPattern(pattern);
			cellValue = sdf.parse(cell.getStringCellValue().trim());
		} catch (Exception e) {
			logger.info(e.getMessage());
			if (!is_compatible) {
				throw e;
			}
		}
		logger.debug("date cellValue is:" + (cellValue == null ? "" : sdf.format(cellValue)));
		return cellValue;
	}
	
	public static Integer parseCellIntegerValue(HSSFCell cell, boolean is_compatible) throws Exception {
		Integer cellValue = null;
		try {
			cellValue = Integer.parseInt(cell.getStringCellValue().trim());
		} catch (Exception e) {
			logger.info(e.getMessage());
			if (!is_compatible) {
				throw e;
			}
		}
		logger.debug("integer cellValue is:" + String.valueOf(cellValue));
		return cellValue;
	}
	
	/**
	 * 获得整型数据
	 * 2014-5-1 下午4:47:38
	 * 
	 * @author renmingfei
	 * @param cell
	 * @param is_compatible如果为true
	 *            ,则无法解析为整型数据时，返回null；否则抛异常
	 * @return
	 */
	public static Integer getCellIntegerValue(HSSFCell cell, boolean is_compatible) {
		Integer cellValue = null;
		try {
			cellValue = Integer.valueOf((int) cell.getNumericCellValue());
		} catch (Exception e) {
			logger.info(e.getMessage());
			if (!is_compatible) {
				throw e;
			}
		}
		logger.debug("integer cellValue is:" + String.valueOf(cellValue));
		return cellValue;
	}
}
