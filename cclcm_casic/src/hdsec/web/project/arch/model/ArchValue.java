package hdsec.web.project.arch.model;

/**
 * @author hd304 自定义表的对应值 2015-7-26/
 */
public class ArchValue {
	private int id;
	private String template_id;
	private String barcode;
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
	private int status;
	private int dos_id;
	private String user_iidd;
	private String dept_id;
	private String user_name;
	private String dept_name;

	private String t01;
	private String t02;
	private String t03;
	private String t04;
	private String t05;
	private String t06;
	private String t07;
	private String t08;
	private String t09;
	private String t10;
	private String t11;
	private String t12;
	private String t13;
	private String t14;
	private String t15;
	private String t16;
	private String t17;
	private String t18;
	private String t19;
	private String t20;
	private String t21;
	private String t22;
	private String t23;
	private String t24;
	private String t25;

	private String type_name;// 所属档案类别名称

	public String getStatus_name() {
		String name = "未知";
		switch (this.status) {
		case 0:
			name = "在档";
			break;
		case 1:
			name = "已锁定";
			break;
		case 2:
			name = "借用审批通过";
			break;
		case 3:
			name = "已借出";
			break;
		}
		return name;
	}

	public String getTValue(int cycle, int i) {
		int index = (cycle - 1) * 3 + i;
		switch (index) {
		case 1:
			return t01;
		case 2:
			return t02;
		case 3:
			return t03;
		case 4:
			return t04;
		case 5:
			return t05;
		case 6:
			return t06;
		case 7:
			return t07;
		case 8:
			return t08;
		case 9:
			return t09;
		case 10:
			return t10;
		case 11:
			return t11;
		case 12:
			return t12;
		case 13:
			return t13;
		case 14:
			return t14;
		case 15:
			return t15;
		case 16:
			return t16;
		case 17:
			return t17;
		case 18:
			return t18;
		case 19:
			return t19;
		case 20:
			return t20;
		case 21:
			return t21;
		case 22:
			return t22;
		case 23:
			return t23;
		case 24:
			return t24;
		case 25:
			return t25;
		default:
			return "";
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDos_id() {
		return dos_id;
	}

	public void setDos_id(int dos_id) {
		this.dos_id = dos_id;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public ArchValue() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
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

	public String getT01() {
		return t01;
	}

	public void setT01(String t01) {
		this.t01 = t01;
	}

	public String getT02() {
		return t02;
	}

	public void setT02(String t02) {
		this.t02 = t02;
	}

	public String getT03() {
		return t03;
	}

	public void setT03(String t03) {
		this.t03 = t03;
	}

	public String getT04() {
		return t04;
	}

	public void setT04(String t04) {
		this.t04 = t04;
	}

	public String getT05() {
		return t05;
	}

	public void setT05(String t05) {
		this.t05 = t05;
	}

	public String getT06() {
		return t06;
	}

	public void setT06(String t06) {
		this.t06 = t06;
	}

	public String getT07() {
		return t07;
	}

	public void setT07(String t07) {
		this.t07 = t07;
	}

	public String getT08() {
		return t08;
	}

	public void setT08(String t08) {
		this.t08 = t08;
	}

	public String getT09() {
		return t09;
	}

	public void setT09(String t09) {
		this.t09 = t09;
	}

	public String getT10() {
		return t10;
	}

	public void setT10(String t10) {
		this.t10 = t10;
	}

	public String getT11() {
		return t11;
	}

	public void setT11(String t11) {
		this.t11 = t11;
	}

	public String getT12() {
		return t12;
	}

	public void setT12(String t12) {
		this.t12 = t12;
	}

	public String getT13() {
		return t13;
	}

	public void setT13(String t13) {
		this.t13 = t13;
	}

	public String getT14() {
		return t14;
	}

	public void setT14(String t14) {
		this.t14 = t14;
	}

	public String getT15() {
		return t15;
	}

	public void setT15(String t15) {
		this.t15 = t15;
	}

	public String getT16() {
		return t16;
	}

	public void setT16(String t16) {
		this.t16 = t16;
	}

	public String getT17() {
		return t17;
	}

	public void setT17(String t17) {
		this.t17 = t17;
	}

	public String getT18() {
		return t18;
	}

	public void setT18(String t18) {
		this.t18 = t18;
	}

	public String getT19() {
		return t19;
	}

	public void setT19(String t19) {
		this.t19 = t19;
	}

	public String getT20() {
		return t20;
	}

	public void setT20(String t20) {
		this.t20 = t20;
	}

	public String getT21() {
		return t21;
	}

	public void setT21(String t21) {
		this.t21 = t21;
	}

	public String getT22() {
		return t22;
	}

	public void setT22(String t22) {
		this.t22 = t22;
	}

	public String getT23() {
		return t23;
	}

	public void setT23(String t23) {
		this.t23 = t23;
	}

	public String getT24() {
		return t24;
	}

	public void setT24(String t24) {
		this.t24 = t24;
	}

	public String getT25() {
		return t25;
	}

	public void setT25(String t25) {
		this.t25 = t25;
	}

}
