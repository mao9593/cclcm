package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecOperation;
import hdsec.web.project.user.model.SecSubsys;

import java.util.List;

public class UpdateFirstDirAction extends UserBaseAction {

	private static final long serialVersionUID = 1L;
	private String update = "N";// 标志是否经过修改
	private String oper_code = ""; // 操作代码
	private String subsys_code = "admin"; // 子系统代码
	private String dir_rank = null; // 目录排序值
	private List<SecSubsys> subsysList = null;
	private SecOperation secoperation;

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getSubsys_code() {
		return subsys_code;
	}

	public void setSubsys_code(String subsys_code) {
		this.subsys_code = subsys_code;
	}

	public SecOperation getSecoperation() {
		return secoperation;
	}

	public void setSecoperation(SecOperation secoperation) {
		this.secoperation = secoperation;
	}

	public String getOper_code() {
		return oper_code;
	}

	public void setOper_code(String oper_code) {
		this.oper_code = oper_code;
	}

	public String getDir_rank() {
		return dir_rank;
	}

	public void setDir_rank(String dir_rank) {
		this.dir_rank = dir_rank;
	}

	@Override
	public String executeFunction() throws Exception {
		// TODO Auto-generated method stub
		// 获取子系统列表
		subsysList = userService.getAllSubsysAsCon();
		if (subsysList.size() > 0 && subsys_code.equals("")) {
			setSubsys_code(subsysList.get(0).getSubsys_code());
		}
		secoperation = userService.getSecOperByCodeAndSubsys(oper_code, subsys_code);
		if (update.equalsIgnoreCase("Y")) { // 处理修改操作
			secoperation.setDir_rank(dir_rank);
			userService.updateSecOper(secoperation); // 更新操作对象
			return "ok";
		}
		return SUCCESS;
	}
}
