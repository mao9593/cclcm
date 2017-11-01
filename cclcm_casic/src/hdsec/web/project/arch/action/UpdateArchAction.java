package hdsec.web.project.arch.action;

import java.util.HashMap;
import java.util.Map;

public class UpdateArchAction extends ArchBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String dos_num;
	private String arch_num;
	private String type_code;
	private String file_title;
	private String file_num;
	private String page_num;
	private String count;
	private String total_page;
	private String seclv_code;
	private String file_date;
	private String file_carr;
	private String keep_limit;
	private String summ;
	private int record_count;
	private int dos_id;
	private String T01;
	private String T02;
	private String T03;
	private String T04;
	private String T05;
	private String T06;
	private String T07;
	private String T08;
	private String T09;
	private String T10;
	private String T11;
	private String T12;
	private String T13;
	private String T14;
	private String T15;
	private String T16;
	private String T17;
	private String T18;
	private String T19;
	private String T20;
	private String T21;
	private String T22;
	private String T23;
	private String T24;
	private String T25;
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> archValueMap = new HashMap<String, Object>();
		archValueMap = getArchPropertyMap();
		archService.updateArchProperty(archValueMap);
		setMsg("ok");
		return SUCCESS;
	}

	private Map<String, Object> getArchPropertyMap() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("DOS_NUM", dos_num);
		map.put("ARCH_NUM", arch_num);
		map.put("TYPE_CODE", type_code);
		map.put("FILE_TITLE", file_title);
		map.put("FILE_NUM", file_num);
		map.put("PAGE_NUM", page_num);
		map.put("COUNT", count);
		map.put("TOTAL_PAGE", total_page);
		map.put("SECLV_CODE", seclv_code);
		map.put("FILE_DATE", file_date);
		map.put("FILE_CARR", file_carr);
		map.put("KEEP_LIMIT", keep_limit);
		map.put("SUMM", summ);
		map.put("T01", T01);
		map.put("T02", T02);
		map.put("T03", T03);
		map.put("T04", T04);
		map.put("T05", T05);
		map.put("T06", T06);
		map.put("T07", T07);
		map.put("T08", T08);
		map.put("T09", T09);
		map.put("T10", T10);
		map.put("T11", T11);
		map.put("T12", T12);
		map.put("T13", T13);
		map.put("T14", T14);
		map.put("T15", T15);
		map.put("T16", T16);
		map.put("T17", T17);
		map.put("T18", T18);
		map.put("T19", T19);
		map.put("T20", T20);
		map.put("T21", T21);
		map.put("T22", T22);
		map.put("T23", T23);
		map.put("T24", T24);
		map.put("T25", T25);
		return map;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDos_num() {
		return dos_num;
	}

	public void setDos_num(String dos_num) {
		this.dos_num = dos_num;
	}

	public String getArch_num() {
		return arch_num;
	}

	public void setArch_num(String arch_num) {
		this.arch_num = arch_num;
	}

	public String getType_code() {
		return type_code;
	}

	public void setType_code(String type_code) {
		this.type_code = type_code;
	}

	public String getFile_title() {
		return file_title;
	}

	public void setFile_title(String file_title) {
		this.file_title = file_title;
	}

	public String getFile_num() {
		return file_num;
	}

	public void setFile_num(String file_num) {
		this.file_num = file_num;
	}

	public String getPage_num() {
		return page_num;
	}

	public void setPage_num(String page_num) {
		this.page_num = page_num;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getTotal_page() {
		return total_page;
	}

	public void setTotal_page(String total_page) {
		this.total_page = total_page;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getFile_date() {
		return file_date;
	}

	public void setFile_date(String file_date) {
		this.file_date = file_date;
	}

	public String getFile_carr() {
		return file_carr;
	}

	public void setFile_carr(String file_carr) {
		this.file_carr = file_carr;
	}

	public String getKeep_limit() {
		return keep_limit;
	}

	public void setKeep_limit(String keep_limit) {
		this.keep_limit = keep_limit;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public int getRecord_count() {
		return record_count;
	}

	public void setRecord_count(int record_count) {
		this.record_count = record_count;
	}

	public int getDos_id() {
		return dos_id;
	}

	public void setDos_id(int dos_id) {
		this.dos_id = dos_id;
	}

	public String getT01() {
		return T01;
	}

	public void setT01(String t01) {
		T01 = t01;
	}

	public String getT02() {
		return T02;
	}

	public void setT02(String t02) {
		T02 = t02;
	}

	public String getT03() {
		return T03;
	}

	public void setT03(String t03) {
		T03 = t03;
	}

	public String getT04() {
		return T04;
	}

	public void setT04(String t04) {
		T04 = t04;
	}

	public String getT05() {
		return T05;
	}

	public void setT05(String t05) {
		T05 = t05;
	}

	public String getT06() {
		return T06;
	}

	public void setT06(String t06) {
		T06 = t06;
	}

	public String getT07() {
		return T07;
	}

	public void setT07(String t07) {
		T07 = t07;
	}

	public String getT08() {
		return T08;
	}

	public void setT08(String t08) {
		T08 = t08;
	}

	public String getT09() {
		return T09;
	}

	public void setT09(String t09) {
		T09 = t09;
	}

	public String getT10() {
		return T10;
	}

	public void setT10(String t10) {
		T10 = t10;
	}

	public String getT11() {
		return T11;
	}

	public void setT11(String t11) {
		T11 = t11;
	}

	public String getT12() {
		return T12;
	}

	public void setT12(String t12) {
		T12 = t12;
	}

	public String getT13() {
		return T13;
	}

	public void setT13(String t13) {
		T13 = t13;
	}

	public String getT14() {
		return T14;
	}

	public void setT14(String t14) {
		T14 = t14;
	}

	public String getT15() {
		return T15;
	}

	public void setT15(String t15) {
		T15 = t15;
	}

	public String getT16() {
		return T16;
	}

	public void setT16(String t16) {
		T16 = t16;
	}

	public String getT17() {
		return T17;
	}

	public void setT17(String t17) {
		T17 = t17;
	}

	public String getT18() {
		return T18;
	}

	public void setT18(String t18) {
		T18 = t18;
	}

	public String getT19() {
		return T19;
	}

	public void setT19(String t19) {
		T19 = t19;
	}

	public String getT20() {
		return T20;
	}

	public void setT20(String t20) {
		T20 = t20;
	}

	public String getT21() {
		return T21;
	}

	public void setT21(String t21) {
		T21 = t21;
	}

	public String getT22() {
		return T22;
	}

	public void setT22(String t22) {
		T22 = t22;
	}

	public String getT23() {
		return T23;
	}

	public void setT23(String t23) {
		T23 = t23;
	}

	public String getT24() {
		return T24;
	}

	public void setT24(String t24) {
		T24 = t24;
	}

	public String getT25() {
		return T25;
	}

	public void setT25(String t25) {
		T25 = t25;
	}

}
