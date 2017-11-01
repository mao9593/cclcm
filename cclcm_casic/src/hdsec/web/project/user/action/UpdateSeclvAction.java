package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecLevel;

/**
 * 修改密级信息
 * 
 * @author renmingfei
 * 
 */
public class UpdateSeclvAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private SecLevel seclv = null;
	private String update = "N";
	private Integer seclv_code = null;
	private String seclv_name = "";
	private Integer seclv_rank = null;
	private String othername = "";

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public SecLevel getSeclv() {
		return seclv;
	}

	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name.trim();
	}

	public void setSeclv_rank(Integer seclv_rank) {
		this.seclv_rank = seclv_rank;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getOthername() {
		return othername;
	}

	public void setOthername(String othername) {
		this.othername = othername.trim();
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {// 处理修改操作
			if (othername != null && !othername.equals("")) {
				if (userService.isSeclvExistByOthername(othername)
						&& !seclv_code.equals(userService.getSeclvCodeByOthername(othername))) {
					throw new Exception("密级别名已经存在，不能重复添加。");
				}
			}
			if (seclv_name != null && !seclv_name.equals("")) {
				if (userService.isSeclvExistByName(seclv_name)
						&& !seclv_code.equals(userService.getSeclvCodeByName(seclv_name))) {
					throw new Exception("密级名称已经存在，不能重复添加。");
				}
			}
			if (seclv_rank != null) {
				if (userService.isSeclvExistByRank(seclv_rank)
						&& !seclv_code.equals(userService.getSeclvCodeByRank(seclv_rank))) {
					throw new Exception("密级排序号已经存在，不能重复添加。");
				}
			}
			seclv = new SecLevel();
			seclv.setSeclv_code(seclv_code);
			seclv.setSeclv_name(seclv_name);
			seclv.setSeclv_rank(seclv_rank);
			seclv.setOthername(othername);
			userService.updateSeclv(seclv);
			insertAdminLog("修改密级信息:" + seclv_code + "," + seclv_name);
			return "update";
		} else {
			seclv = userService.getSecLevelByCode(seclv_code);
		}
		return SUCCESS;
	}
}
