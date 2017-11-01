package hdsec.web.project.basic.service;

import hdsec.web.project.basic.mapper.BasicMapper;
import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PropertiesFileUtil;
import hdsec.web.project.user.mapper.UserMapper;
import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.model.SecLevel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

/**
 * 生成条码专用类
 * 
 * @author renmingfei
 * 
 */
public class BarcodeGeneratorImpl {
	public final String INIT_STR = "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
	@Resource
	private BasicMapper basicMapper;
	@Resource
	private UserMapper userMapper;

	private String createBarcode() {
		Map<String, Object> map = new HashMap<String, Object>();
		basicMapper.getCreateBarcode(map);
		String barcode = String.valueOf(map.get("outValue"));
		String prefix = "0000000000000";
		return prefix.substring(barcode.length()).concat(barcode);
	}

	private String createBarcodeCAEP(Integer seclv_code, String ext_code, String curYear) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("inSeclvCode", seclv_code);
		map.put("inDeptID", ext_code);
		map.put("inYear", curYear);
		basicMapper.getCreateBarcodeCAEP(map);
		String barcode = String.valueOf(map.get("outValue"));
		String prefix = "00000";
		return prefix.substring(barcode.length()).concat(barcode);
	}

	public String createEntityBarcode(String entityType) throws Exception {
		String unitCode = PropertyUtil.getUnitCode();
		String barcodeType = PropertyUtil.getEntityTypeKey(entityType);
		String curYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		String barcode = createBarcode();
		return unitCode + barcodeType + curYear + barcode + "00";
	}

	/**
	 * 中物5所一维码
	 */
	public String createEntityBarcodeCAEP(String entityType, Integer seclv_code, String dept_id) throws Exception {
		String unitCode = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "unit.code");// 5S
		// String barcodeType = PropertyUtil.getEntityTypeKey(entityType);
		SecLevel seclv = userMapper.getSecLevelByCode(seclv_code);
		String seclv_str = "";
		if (seclv != null) {
			seclv_str = seclv.getOthername();
		}
		SecDept dept = userMapper.getSecDeptById(dept_id);
		String ext_code = dept.getExt_code();
		String source = "";
		if (StringUtils.hasLength(entityType)) {
			switch (entityType.toUpperCase()) {
			case "PRINTPAPER":
				source = "ZWN";
				break;
			case "PRINTGRAPH":
				source = "ZWN";
				break;
			case "COPY":
				source = "ZWN";
				break;
			case "ENTERPAPER":
				source = "ZWW";
				break;
			case "BURN":
				source = "CDN";
				break;
			case "ENTERCD":
				source = "CDW";
				break;
			case "SPACECD":
				source = "CDW";
				break;
			case "DEVICE":
				source = "ZWN";
				break;
			case "STORAGE":
				source = "ZWN";
				break;
			default:
				source = "";
			}
		} else {
			source = "";
		}
		String curYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		String barcode = createBarcodeCAEP(seclv_code, ext_code, curYear);
		return unitCode + "-" + seclv_str + "-" + ext_code + "-" + source + "-" + curYear + "-" + barcode;
	}

	public String getOriBarcode(String barcode) {
		return barcode.substring(barcode.length() - 24);
	}

	public String createTwoDimenosionalBarcode(String seclv_id, String version, String user_name, String seclv_code,
			String medium, String from_src, String papge_count, String page_index, String file_name, String dept_id,
			String entityType, String entity_barcode, int type) throws Exception {
		int num = 0;
		String fixChineseStr = null;
		StringBuffer sb = new StringBuffer();
		// 加密标识 2 ,二维码标准 2 ,预留 3
		sb.append(fillStringWithZero(seclv_id, 2)).append(fillStringWithZero(version, 2))
				.append(fillStringWithZero("", 3));
		// 单位代码或名称 4
		sb.append(PropertyUtil.getUnitCode());
		// 员工姓名 8
		if (user_name.getBytes("GBK").length < 8) {
			sb.append(fillStringWithZero(user_name, 8));
		} else {
			num = trimGBK(user_name.getBytes("GBK"), 8);
			fixChineseStr = user_name.substring(0, num);
			sb.append(fillStringWithZero(fixChineseStr, 8));
		}
		// 预留 8
		sb.append(fillStringWithZero("", 8));
		// 日期 8 yyyymmdd
		sb.append(getSdf().format(new Date()));
		// 预留 2
		sb.append(fillStringWithZero("", 2));
		// 密级 1
		sb.append(fillStringWithZero(seclv_code, 1));
		// 预留 7
		sb.append(fillStringWithZero("", 7));
		// 预留 7
		sb.append(fillStringWithZero("", 7));
		// 介质 1
		sb.append(fillStringWithZero(medium, 1));
		// 来源 1
		sb.append(fillStringWithZero(from_src, 1));
		// 页数 4
		sb.append(fillStringWithZero(papge_count, 4));
		// 页码 4
		sb.append(fillStringWithZero(page_index, 4));
		// 预留 5
		sb.append(fillStringWithZero("", 5));
		// 流水号 13
		sb.append(fillStringWithZero(createBarcode(), 13));
		// 文件名称 128
		if (file_name.getBytes("GBK").length < 122) {
			sb.append(fillStringWithZero(file_name, 122));
		} else {
			num = trimGBK(file_name.getBytes("GBK"), 122);
			fixChineseStr = file_name.substring(0, num);
			sb.append(fillStringWithZero(fixChineseStr, 122));
		}
		// 制作部门 4
		sb.append(fillStringWithZero(dept_id, 4));
		// type为1的时候是生成新的条码
		if (type == 1) {
			sb.append(fillStringWithZero(createEntityBarcode(entityType), 22));
		} else if (type == 2) {// type为2的时候是补打已有条码
			sb.append(fillStringWithZero(entity_barcode, 22));
		}
		// return new sun.misc.BASE64Encoder().encode(sb.toString().getBytes());
		return sb.toString();
	}

	private int trimGBK(byte[] buf, int n) {
		int num = 0;
		boolean chineseHalf = false;
		for (int i = 0; i < n; i++) {
			if (buf[i] < 0 && !chineseHalf) {
				chineseHalf = true;
			} else {
				num++;
				chineseHalf = false;
			}
		}
		return num;
	}

	private String fillStringWithZero(String str, int total_length) {
		if (null == str || "".equals(str)) {
			return INIT_STR.substring(0, total_length);
		}
		int chineseCount = getChineseCount(str);
		if (str.length() + chineseCount >= total_length) {
			return str.substring(0, str.length());
		} else {
			return INIT_STR.substring(0, total_length - str.length() - chineseCount) + str;
		}

	}

	private SimpleDateFormat getSdf() {
		return new SimpleDateFormat("yyyyMMdd");
	}

	private int getChineseCount(String str) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (isChinese(str.charAt(i))) {
				count++;
			}
		}
		return count;
	}

	private boolean isChinese(char c) {
		int v = c;
		return (v >= 19968 && v < 171941);
	}
}
