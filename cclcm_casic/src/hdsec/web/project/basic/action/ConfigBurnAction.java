package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysConfigItem;

public class ConfigBurnAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private Integer upload_burnfile_num;
	private String upload_burnfile_num_start = "";
	private String upload_burnfile_ziptype = "";
	private String upload_burnfile_ziptype_start = "";

	private String update = "N";
	private String done = "N";

	public Integer getUpload_burnfile_num() {
		return upload_burnfile_num;
	}

	public void setUpload_burnfile_num(Integer upload_burnfile_num) {
		this.upload_burnfile_num = upload_burnfile_num;
	}

	public String getDone() {
		return done;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getUpload_burnfile_num_start() {
		return upload_burnfile_num_start;
	}

	public void setUpload_burnfile_num_start(String upload_burnfile_num_start) {
		this.upload_burnfile_num_start = upload_burnfile_num_start;
	}

	public String getUpload_burnfile_ziptype() {
		return upload_burnfile_ziptype;
	}

	public void setUpload_burnfile_ziptype(String upload_burnfile_ziptype) {
		this.upload_burnfile_ziptype = upload_burnfile_ziptype;
	}

	public String getUpload_burnfile_ziptype_start() {
		return upload_burnfile_ziptype_start;
	}

	public void setUpload_burnfile_ziptype_start(String upload_burnfile_ziptype_start) {
		this.upload_burnfile_ziptype_start = upload_burnfile_ziptype_start;
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {

			basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_UPLOAD_BURNFILE_NUM,
					SysConfigItem.NAME_UPLOAD_BURNFILE_NUM, String.valueOf(upload_burnfile_num),
					SysConfigItem.TYPE_BURN, upload_burnfile_num_start.equals("1") ? 1 : 0));

			basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_UPLOAD_BURNFILE_ZIPTYPE,
					SysConfigItem.NAME_UPLOAD_BURNFILE_ZIPTYPE, upload_burnfile_ziptype, SysConfigItem.TYPE_BURN,
					upload_burnfile_ziptype_start.equals("1") ? 1 : 0));

			insertAdminLog("修改刻录参数设置");
			done = "Y";
		}
		SysConfigItem item_upload_burnfile_num = basicService
				.getSysConfigItemValue(SysConfigItem.KEY_UPLOAD_BURNFILE_NUM);
		upload_burnfile_num = Integer.parseInt(item_upload_burnfile_num.getItem_value());
		upload_burnfile_num_start = item_upload_burnfile_num.getStartuse() == 1 ? "checked" : "";

		SysConfigItem item_upload_burnfile_ziptype = basicService
				.getSysConfigItemValue(SysConfigItem.KEY_UPLOAD_BURNFILE_ZIPTYPE);
		upload_burnfile_ziptype = item_upload_burnfile_ziptype.getItem_value();
		upload_burnfile_ziptype_start = item_upload_burnfile_ziptype.getStartuse() == 1 ? "checked" : "";
		return SUCCESS;
	}
}
