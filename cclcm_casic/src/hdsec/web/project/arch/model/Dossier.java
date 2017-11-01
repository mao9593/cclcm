package hdsec.web.project.arch.model;

/**
 * 案卷类
 * 
 * @author handouwang
 * 
 *         2015-7-27/
 */
public class Dossier {
	private int id;
	private String sub_prog_code; // 子项号，管理员填写
	private String prog_code; // 项目号，管理员填写
	private String dos_code; // 案卷号
	private String type_code; // 分类号
	private String dos_subject; // 案卷题名
	private String dir_code; // 目录号
	private String unit; // 编制单位
	private String seclv_code; // 密级
	private int arch_num; // 文件件数
	private int total_page; // 总页数
	private String arch_type; // 档案分类
	private String create_user; // 立卷人
	private String dos_num; // 全宗号
	private String summ; // 备注
	private String dutyman; // 责任者
	private int item_id; // 关联的子项ID
	private String context = "arch/viewdosdetail.action";

	public String getContext() {
		return context;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSub_prog_code() {
		return sub_prog_code;
	}

	public void setSub_prog_code(String sub_prog_code) {
		this.sub_prog_code = sub_prog_code;
	}

	public String getProg_code() {
		return prog_code;
	}

	public void setProg_code(String prog_code) {
		this.prog_code = prog_code;
	}

	public String getDos_code() {
		return dos_code;
	}

	public void setDos_code(String dos_code) {
		this.dos_code = dos_code;
	}

	public String getType_code() {
		return type_code;
	}

	public void setType_code(String type_code) {
		this.type_code = type_code;
	}

	public String getDos_subject() {
		return dos_subject;
	}

	public void setDos_subject(String dos_subject) {
		this.dos_subject = dos_subject;
	}

	public String getDir_code() {
		return dir_code;
	}

	public void setDir_code(String dir_code) {
		this.dir_code = dir_code;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public int getArch_num() {
		return arch_num;
	}

	public void setArch_num(int arch_num) {
		this.arch_num = arch_num;
	}

	public int getTotal_page() {
		return total_page;
	}

	public void setTotal_page(int total_page) {
		this.total_page = total_page;
	}

	public String getArch_type() {
		return arch_type;
	}

	public void setArch_type(String arch_type) {
		this.arch_type = arch_type;
	}

	public String getCreate_user() {
		return create_user;
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

	public String getDos_num() {
		return dos_num;
	}

	public void setDos_num(String dos_num) {
		this.dos_num = dos_num;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public String getDutyman() {
		return dutyman;
	}

	public void setDutyman(String dutyman) {
		this.dutyman = dutyman;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public Dossier(String sub_prog_code, String prog_code, String dos_code,
			String type_code, String dos_subject, String dir_code, String unit,
			String seclv_code, int arch_num, int total_page, String arch_type,
			String create_user, String dos_num, String summ, String dutyman,
			int item_id) {
		super();
		this.sub_prog_code = sub_prog_code;
		this.prog_code = prog_code;
		this.dos_code = dos_code;
		this.type_code = type_code;
		this.dos_subject = dos_subject;
		this.dir_code = dir_code;
		this.unit = unit;
		this.seclv_code = seclv_code;
		this.arch_num = arch_num;
		this.total_page = total_page;
		this.arch_type = arch_type;
		this.create_user = create_user;
		this.dos_num = dos_num;
		this.summ = summ;
		this.dutyman = dutyman;
		this.item_id = item_id;
	}

	public Dossier() {
		super();
	}

}
