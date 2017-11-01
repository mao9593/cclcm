package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecLevel;

/**
 * 添加密级
 * 
 * @author renmingfei
 * 
 */
public class AddSeclvAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private Integer seclv_code = null;
	private String seclv_name = "";
	private Integer seclv_rank = null;
	private String add = "N";
	private String othername = "";

	public void setSeclv_rank(Integer seclv_rank) {
		this.seclv_rank = seclv_rank;
	}

	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name.trim();
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public void setOthername(String othername) {
		this.othername = othername.trim();
	}

	public Integer getSeclvCount() {
		return userService.getSeclvCount();
	}

	@Override
	public String executeFunction() throws Exception {
		if (add.equalsIgnoreCase("Y")) {// 处理添加操作
			String str = "" + System.currentTimeMillis();
			seclv_code = Integer.parseInt(str.substring(str.length() - 4));

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

			while (userService.isSeclvExistByCode(seclv_code)) {
				str = "" + System.currentTimeMillis();
				seclv_code = Integer.parseInt(str.substring(str.length() - 4));
			}
			SecLevel seclv = new SecLevel();
			seclv.setSeclv_code(seclv_code);
			seclv.setSeclv_name(seclv_name);
			seclv.setSeclv_rank(seclv_rank);
			seclv.setOthername(othername);
			userService.addSeclv(seclv);
			insertAdminLog("添加密级:" + seclv_name);
			return "insert";
		} else
			return SUCCESS;
	}
}
