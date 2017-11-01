package hdsec.web.project.arch.action;

import hdsec.web.project.arch.model.Dossier;

/**
 * 修改案卷详细信息
 * 
 * @author handouwang
 * 
 *         2015-7-28/
 */
public class UpdateDosAction extends ArchBaseAction {
	private static final long serialVersionUID = 1L;
	private int id = 0;
	private Dossier dos = null;
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
	private String update = "N";

	public void setUpdate(String update) {
		this.update = update;
	}

	public Dossier getDos() {
		return dos;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
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

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public void setDutyman(String dutyman) {
		this.dutyman = dutyman;
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equals("Y")) {
			if (id != 0) {
				Dossier dos = new Dossier(sub_prog_code, prog_code, dos_code,
						type_code, dos_subject, dir_code, unit, seclv_code,
						arch_num, total_page, arch_type, create_user, dos_num,
						summ, dutyman, 0);
				dos.setId(id);
				archService.updateDossier(dos);
				return "update";
			} else {
				throw new Exception("No valid id input");
			}
		} else {
			dos = archService.getDosById(id);
			return SUCCESS;
		}
	}
}
