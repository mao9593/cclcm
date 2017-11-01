package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysConfigItem;

public class ThreeAdminLoginIpAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private Integer secIp_start = 0;
	private Integer sysIp_start = 0;
	private Integer audIp_start = 0;
	private String update = "N";
	private String done = "N";
	private String secIp1 = "";
	private String secIp2 = "";
	private String secIp3 = "";
	private String secIp4 = "";
	private String secIp5 = "";
	private String secIp6 = "";
	private String sysIp1 = "";
	private String sysIp2 = "";
	private String sysIp3 = "";
	private String sysIp4 = "";
	private String sysIp5 = "";
	private String sysIp6 = "";
	private String audIp1 = "";
	private String audIp2 = "";
	private String audIp3 = "";
	private String audIp4 = "";
	private String audIp5 = "";
	private String audIp6 = "";

	public Integer getSecIp_start() {
		return secIp_start;
	}

	public void setSecIp_start(Integer secIp_start) {
		this.secIp_start = secIp_start;
	}

	public Integer getSysIp_start() {
		return sysIp_start;
	}

	public void setSysIp_start(Integer sysIp_start) {
		this.sysIp_start = sysIp_start;
	}

	public Integer getAudIp_start() {
		return audIp_start;
	}

	public void setAudIp_start(Integer audIp_start) {
		this.audIp_start = audIp_start;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getDone() {
		return done;
	}

	public void setDone(String done) {
		this.done = done;
	}

	public String getSecIp1() {
		return secIp1;
	}

	public void setSecIp1(String secIp1) {
		this.secIp1 = secIp1;
	}

	public String getSecIp2() {
		return secIp2;
	}

	public void setSecIp2(String secIp2) {
		this.secIp2 = secIp2;
	}

	public String getSecIp3() {
		return secIp3;
	}

	public void setSecIp3(String secIp3) {
		this.secIp3 = secIp3;
	}

	public String getSecIp4() {
		return secIp4;
	}

	public void setSecIp4(String secIp4) {
		this.secIp4 = secIp4;
	}

	public String getSecIp5() {
		return secIp5;
	}

	public void setSecIp5(String secIp5) {
		this.secIp5 = secIp5;
	}

	public String getSecIp6() {
		return secIp6;
	}

	public void setSecIp6(String secIp6) {
		this.secIp6 = secIp6;
	}

	public String getSysIp1() {
		return sysIp1;
	}

	public void setSysIp1(String sysIp1) {
		this.sysIp1 = sysIp1;
	}

	public String getSysIp2() {
		return sysIp2;
	}

	public void setSysIp2(String sysIp2) {
		this.sysIp2 = sysIp2;
	}

	public String getSysIp3() {
		return sysIp3;
	}

	public void setSysIp3(String sysIp3) {
		this.sysIp3 = sysIp3;
	}

	public String getSysIp4() {
		return sysIp4;
	}

	public void setSysIp4(String sysIp4) {
		this.sysIp4 = sysIp4;
	}

	public String getSysIp5() {
		return sysIp5;
	}

	public void setSysIp5(String sysIp5) {
		this.sysIp5 = sysIp5;
	}

	public String getSysIp6() {
		return sysIp6;
	}

	public void setSysIp6(String sysIp6) {
		this.sysIp6 = sysIp6;
	}

	public String getAudIp1() {
		return audIp1;
	}

	public void setAudIp1(String audIp1) {
		this.audIp1 = audIp1;
	}

	public String getAudIp2() {
		return audIp2;
	}

	public void setAudIp2(String audIp2) {
		this.audIp2 = audIp2;
	}

	public String getAudIp3() {
		return audIp3;
	}

	public void setAudIp3(String audIp3) {
		this.audIp3 = audIp3;
	}

	public String getAudIp4() {
		return audIp4;
	}

	public void setAudIp4(String audIp4) {
		this.audIp4 = audIp4;
	}

	public String getAudIp5() {
		return audIp5;
	}

	public void setAudIp5(String audIp5) {
		this.audIp5 = audIp5;
	}

	public String getAudIp6() {
		return audIp6;
	}

	public void setAudIp6(String audIp6) {
		this.audIp6 = audIp6;
	}

	@Override
	public String executeFunction() throws Exception {
		String secIp = "";
		String sysIp = "";
		String audIp = "";
		if (update.equalsIgnoreCase("Y")) {
			// 设置IP
			secIp = secIp1 + "-" + secIp2 + "," + secIp3 + "-" + secIp4 + "," + secIp5 + "-" + secIp6;
			sysIp = sysIp1 + "-" + sysIp2 + "," + sysIp3 + "-" + sysIp4 + "," + sysIp5 + "-" + sysIp6;
			audIp = audIp1 + "-" + audIp2 + "," + audIp3 + "-" + audIp4 + "," + audIp5 + "-" + audIp6;
			basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_SECADMIN_LOGIN_IP,
					SysConfigItem.NAME_SECADMIN_LOGIN_IP, secIp, SysConfigItem.ADMIN_IP_TYPE, secIp_start));
			basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_SYSADMIN_LOGIN_IP,
					SysConfigItem.NAME_SYSADMIN_LOGIN_IP, sysIp, SysConfigItem.ADMIN_IP_TYPE, sysIp_start));
			basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_AUDADMIN_LOGIN_IP,
					SysConfigItem.NAME_AUDADMIN_LOGIN_IP, audIp, SysConfigItem.ADMIN_IP_TYPE, audIp_start));
			insertAdminLog("三员登录IP设置");
			setDone("Y");
		}
		// 查询安全管理员登录IP设置并展示
		SysConfigItem secItem = basicService.getSysConfigItemValue(SysConfigItem.KEY_SECADMIN_LOGIN_IP);
		secIp = secItem.getItem_value();
		if (secIp.length() > 0) {
			String st[] = secIp.split(",");
			if (st[0].length() > 1) {
				String s[] = st[0].split("-");
				secIp1 = s[0];
				secIp2 = s[1];
			}
			if (st[1].length() > 1) {
				String s[] = st[1].split("-");
				secIp3 = s[0];
				secIp4 = s[1];
			}
			if (st[2].length() > 1) {
				String s[] = st[2].split("-");
				secIp5 = s[0];
				secIp6 = s[1];
			}
		}
		secIp_start = secItem.getStartuse();

		// 查询系统管理员登录IP设置并展示
		SysConfigItem sysItem = basicService.getSysConfigItemValue(SysConfigItem.KEY_SYSADMIN_LOGIN_IP);
		sysIp = sysItem.getItem_value();
		if (sysIp.length() > 0) {
			String st[] = sysIp.split(",");
			if (st[0].length() > 1) {
				String s[] = st[0].split("-");
				sysIp1 = s[0];
				sysIp2 = s[1];
			}
			if (st[1].length() > 1) {
				String s[] = st[1].split("-");
				sysIp3 = s[0];
				sysIp4 = s[1];
			}
			if (st[2].length() > 1) {
				String s[] = st[2].split("-");
				sysIp5 = s[0];
				sysIp6 = s[1];
			}
		}
		sysIp_start = sysItem.getStartuse();
		// 查询审计管理员登录IP设置并展示
		SysConfigItem audItem = basicService.getSysConfigItemValue(SysConfigItem.KEY_AUDADMIN_LOGIN_IP);
		audIp = audItem.getItem_value();
		if (audIp.length() > 0) {
			String st[] = audIp.split(",");
			if (st[0].length() > 1) {
				String s[] = st[0].split("-");
				audIp1 = s[0];
				audIp2 = s[1];
			}
			if (st[1].length() > 1) {
				String s[] = st[1].split("-");
				audIp3 = s[0];
				audIp4 = s[1];
			}
			if (st[2].length() > 1) {
				String s[] = st[2].split("-");
				audIp5 = s[0];
				audIp6 = s[1];
			}
		}
		audIp_start = audItem.getStartuse();
		return SUCCESS;
	}
}
