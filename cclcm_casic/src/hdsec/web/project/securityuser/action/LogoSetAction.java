package hdsec.web.project.securityuser.action;

import hdsec.web.project.common.bm.model.BMSysConfigItem;

import java.io.File;

public class LogoSetAction extends SecurityUserBaseAction {

	private static final long serialVersionUID = 1L;
	private Integer start = 0;
	private String update = "N";
	private String type = "";

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String executeFunction() throws Exception {
		String localPath = Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath();
		String path_str = localPath.substring(0, localPath.indexOf("cclcm"))
				+ "cclcm\\images\\_system\\portal";
		File path = new File(path_str);
		if (path.exists()) {
			if (update.equalsIgnoreCase("Y")) {
				if (type.equalsIgnoreCase("open")) {
					securityUserService.updateBMSysConfigItem(new BMSysConfigItem(BMSysConfigItem.KEY_LOGO_SET,
							BMSysConfigItem.NAME_LOGO_SET, null, BMSysConfigItem.TYPE_LOGO_SET, 1));
					File file_1 = new File(path_str + "\\cclcm_banner.jpg");
					File file_2 = new File(path_str + "\\cclcm_banner_cclcm.jpg");
					if (file_1.exists() && file_2.exists()) {
						file_1.renameTo(new File(path_str + "\\cclcm_banner_bm.jpg"));
						file_2.renameTo(new File(path_str + "\\cclcm_banner.jpg"));
					}
				} else if (type.equalsIgnoreCase("close")) {
					securityUserService.updateBMSysConfigItem(new BMSysConfigItem(BMSysConfigItem.KEY_LOGO_SET,
							BMSysConfigItem.NAME_LOGO_SET, null, BMSysConfigItem.TYPE_LOGO_SET, 0));
					File file_3 = new File(path_str + "\\cclcm_banner.jpg");
					File file_4 = new File(path_str + "\\cclcm_banner_bm.jpg");
					if (file_3.exists() && file_4.exists()) {
						file_3.renameTo(new File(path_str + "\\cclcm_banner_cclcm.jpg"));
						file_4.renameTo(new File(path_str + "\\cclcm_banner.jpg"));
					}
				} else {
					start = securityUserService.getBMSysConfigItemValue(BMSysConfigItem.KEY_LOGO_SET).getStartuse();
				}
			} else {
				start = securityUserService.getBMSysConfigItemValue(BMSysConfigItem.KEY_LOGO_SET).getStartuse();
			}
		} else {
			return "exception";
		}

		return SUCCESS;
	}
}