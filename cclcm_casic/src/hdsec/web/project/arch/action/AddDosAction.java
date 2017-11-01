package hdsec.web.project.arch.action;

import hdsec.web.project.arch.model.Dossier;

/**
 * 添加案卷
 * 
 * @author handouwang
 * 
 *         2015-7-27/
 */
public class AddDosAction extends ArchBaseAction {
	private static final long serialVersionUID = 1L;
	private int item_id;
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
	private String add = "N";

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public void setSub_prog_code(String sub_prog_code) {
		this.sub_prog_code = sub_prog_code;
	}

	public void setProg_code(String prog_code) {
		this.prog_code = prog_code;
	}

	public void setDos_code(String dos_code) {
		this.dos_code = dos_code;
	}

	public void setType_code(String type_code) {
		this.type_code = type_code;
	}

	public void setDos_subject(String dos_subject) {
		this.dos_subject = dos_subject;
	}

	public void setDir_code(String dir_code) {
		this.dir_code = dir_code;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setArch_num(int arch_num) {
		this.arch_num = arch_num;
	}

	public void setTotal_page(int total_page) {
		this.total_page = total_page;
	}

	public void setArch_type(String arch_type) {
		this.arch_type = arch_type;
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

	public void setDos_num(String dos_num) {
		this.dos_num = dos_num;
	}

	public void setDutyman(String dutyman) {
		this.dutyman = dutyman;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	@Override
	public String executeFunction() throws Exception {
		if (add.equalsIgnoreCase("Y")) {// 处理添加操作
			if (archService.isDosExistByCode(dos_code)) {
				throw new Exception("案卷号已经存在，不能重复添加。");
			} else {
				Dossier dos = new Dossier(sub_prog_code, prog_code, dos_code,
						type_code, dos_subject, dir_code, unit, seclv_code,
						arch_num, total_page, arch_type, create_user, dos_num,
						summ, dutyman, item_id);
				archService.addDossier(dos);
				insertCommonLog("添加项目[" + dos_code + "][" + dos_subject + "].");
				return "insert";
			}
		} else {
			return SUCCESS;
		}
	}
}
