package hdsec.web.project.basic.action;

import hdsec.web.project.common.model.CopyrightLicense;

import java.io.FileReader;

/**
 * 查看系统信息
 * 
 * @author lixiang
 * 
 */
public class ViewInfoAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String web_version = "";
	private String server_version = "";
	private String numbers;
	private String days;
	private String hsNumbers;

	public String getWeb_version() {
		return web_version;
	}

	public void setWeb_version(String web_version) {
		this.web_version = web_version;
	}

	public String getServer_version() {
		return server_version;
	}

	public void setServer_version(String server_version) {
		this.server_version = server_version;
	}

	@Override
	public String executeFunction() throws Exception {
		String src_path = this.getSession().getServletContext().getRealPath("/");
		String server_path = "";
		if (src_path.indexOf("tomcat") > 0) {
			server_path = src_path.substring(0, src_path.indexOf("tomcat"));
		}

		FileReader web_file = new FileReader(src_path + "/version.inf");
		char[] web_array = new char[64];
		int hasRead = 0;
		while ((hasRead = web_file.read(web_array)) > 0) {
			web_version = new String(web_array, 0, hasRead);
		}
		if (!server_path.equals("")) {
			FileReader server_file = new FileReader(server_path + "version");
			char[] server_array = new char[64];
			hasRead = 0;
			while ((hasRead = server_file.read(server_array)) > 0) {
				server_version = new String(server_array, 0, hasRead);
			}
		} else {
			server_version = "";
			logger.info("服务器版本文件路径不存在，请检查");
			// throw new Exception("请检查服务器版本文件的路径");
		}
		numbers = "" + CopyrightLicense.pNum;
		days = CopyrightLicense.limitDate;
		if (CopyrightLicense.hsNum == null) {
			hsNumbers = "";
		} else {
			hsNumbers = "" + CopyrightLicense.hsNum;
		}

		/*
		 * String numberDays = ServerValidation.hasPermissionFile(); if (!"0".equals(numberDays)) { numbers =
		 * numberDays.split("\\|")[0]; UserServiceImpl.numbers = Integer.parseInt(numbers); days =
		 * numberDays.split("\\|")[1]; }
		 */

		return SUCCESS;
	}

	public String getNumbers() {
		return numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public int getL_numbers() throws Exception {
		return userService.getLastNumberCount();
	}

	public String getHsNumbers() {
		return hsNumbers;
	}

	public void setHsNumbers(String hsNumbers) {
		this.hsNumbers = hsNumbers;
	}

}
