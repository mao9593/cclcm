package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysConfigItem;

/**
 * 交接确认模块配置
 * 
 * @author lixiang
 * 
 */
public class ConfigConfirmAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private Integer retrieve_start = 1;
	private Integer file_start = 1;
	private Integer transfer_start = 1;
	private Integer read_br_start = 1;
	private Integer read_rt_start = 1;
	private Integer device_br_start = 1;
	private Integer device_rt_start = 1;
	private Integer storage_br_start = 1;
	private Integer storage_rt_start = 1;
	private Integer copy_start = 1;
	private Integer enter_start = 1;
	private String update = "N";
	private String type = "";
	private String module = "";

	public Integer getRetrieve_start() {
		return retrieve_start;
	}

	public void setRetrieve_start(Integer retrieve_start) {
		this.retrieve_start = retrieve_start;
	}

	public Integer getFile_start() {
		return file_start;
	}

	public void setFile_start(Integer file_start) {
		this.file_start = file_start;
	}

	public Integer getTransfer_start() {
		return transfer_start;
	}

	public void setTransfer_start(Integer transfer_start) {
		this.transfer_start = transfer_start;
	}

	public Integer getRead_br_start() {
		return read_br_start;
	}

	public void setRead_br_start(Integer read_br_start) {
		this.read_br_start = read_br_start;
	}

	public Integer getRead_rt_start() {
		return read_rt_start;
	}

	public void setRead_rt_start(Integer read_rt_start) {
		this.read_rt_start = read_rt_start;
	}

	public Integer getDevice_br_start() {
		return device_br_start;
	}

	public void setDevice_br_start(Integer device_br_start) {
		this.device_br_start = device_br_start;
	}

	public Integer getDevice_rt_start() {
		return device_rt_start;
	}

	public void setDevice_rt_start(Integer device_rt_start) {
		this.device_rt_start = device_rt_start;
	}

	public Integer getStorage_br_start() {
		return storage_br_start;
	}

	public void setStorage_br_start(Integer storage_br_start) {
		this.storage_br_start = storage_br_start;
	}

	public Integer getStorage_rt_start() {
		return storage_rt_start;
	}

	public void setStorage_rt_start(Integer storage_rt_start) {
		this.storage_rt_start = storage_rt_start;
	}

	public Integer getCopy_start() {
		return copy_start;
	}

	public void setCopy_start(Integer copy_start) {
		this.copy_start = copy_start;
	}

	public Integer getEnter_start() {
		return enter_start;
	}

	public void setEnter_start(Integer enter_start) {
		this.enter_start = enter_start;
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

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {
			if (module.equalsIgnoreCase("RETRIEVE")) {
				if (type.equalsIgnoreCase("open")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_RETRIEVE_CONFIRM,
							SysConfigItem.NAME_RETRIEVE_CONFIRM, null, SysConfigItem.TYPE_CONFIRM, 1));
					insertAdminLog("开启回收交接确认");
				}
				if (type.equalsIgnoreCase("close")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_RETRIEVE_CONFIRM,
							SysConfigItem.NAME_RETRIEVE_CONFIRM, null, SysConfigItem.TYPE_CONFIRM, 0));
					insertAdminLog("关闭回收交接确认");
				}
			} else if (module.equalsIgnoreCase("FILE")) {
				if (type.equalsIgnoreCase("open")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_FILE_CONFIRM,
							SysConfigItem.NAME_FILE_CONFIRM, null, SysConfigItem.TYPE_CONFIRM, 1));
					insertAdminLog("开启归档交接确认");
				}
				if (type.equalsIgnoreCase("close")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_FILE_CONFIRM,
							SysConfigItem.NAME_FILE_CONFIRM, null, SysConfigItem.TYPE_CONFIRM, 0));
					insertAdminLog("关闭归档交接确认");
				}
			} else if (module.equalsIgnoreCase("TRANSFER")) {
				if (type.equalsIgnoreCase("open")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_TRANSFER_CONFIRM,
							SysConfigItem.NAME_TRANSFER_CONFIRM, null, SysConfigItem.TYPE_CONFIRM, 1));
					insertAdminLog("开启流转交接确认");
				}
				if (type.equalsIgnoreCase("close")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_TRANSFER_CONFIRM,
							SysConfigItem.NAME_TRANSFER_CONFIRM, null, SysConfigItem.TYPE_CONFIRM, 0));
					insertAdminLog("关闭流转交接确认");
				}
			} else if (module.equalsIgnoreCase("READ_BR")) {
				if (type.equalsIgnoreCase("open")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_READ_BR_CONFIRM,
							SysConfigItem.NAME_READ_BR_CONFIRM, null, SysConfigItem.TYPE_CONFIRM, 1));
					insertAdminLog("开启部门载体借用交接确认");
				}
				if (type.equalsIgnoreCase("close")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_READ_BR_CONFIRM,
							SysConfigItem.NAME_READ_BR_CONFIRM, null, SysConfigItem.TYPE_CONFIRM, 0));
					insertAdminLog("关闭部门载体借用交接确认");
				}
			} else if (module.equalsIgnoreCase("READ_RT")) {
				if (type.equalsIgnoreCase("open")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_READ_RT_CONFIRM,
							SysConfigItem.NAME_READ_RT_CONFIRM, null, SysConfigItem.TYPE_CONFIRM, 1));
					insertAdminLog("开启部门载体归还交接确认");
				}
				if (type.equalsIgnoreCase("close")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_READ_RT_CONFIRM,
							SysConfigItem.NAME_READ_RT_CONFIRM, null, SysConfigItem.TYPE_CONFIRM, 0));
					insertAdminLog("关闭部门载体归还交接确认");
				}
			} else if (module.equalsIgnoreCase("DEVICE_BR")) {
				if (type.equalsIgnoreCase("open")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_DEVICE_BR_CONFIRM,
							SysConfigItem.NAME_DEVICE_BR_CONFIRM, null, SysConfigItem.TYPE_CONFIRM, 1));
					insertAdminLog("开启磁介质借用交接确认");
				}
				if (type.equalsIgnoreCase("close")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_DEVICE_BR_CONFIRM,
							SysConfigItem.NAME_DEVICE_BR_CONFIRM, null, SysConfigItem.TYPE_CONFIRM, 0));
					insertAdminLog("关闭磁介质借用交接确认");
				}
			} else if (module.equalsIgnoreCase("DEVICE_RT")) {
				if (type.equalsIgnoreCase("open")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_DEVICE_RT_CONFIRM,
							SysConfigItem.NAME_DEVICE_RT_CONFIRM, null, SysConfigItem.TYPE_CONFIRM, 1));
					insertAdminLog("开启磁介质归还交接确认");
				}
				if (type.equalsIgnoreCase("close")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_DEVICE_RT_CONFIRM,
							SysConfigItem.NAME_DEVICE_RT_CONFIRM, null, SysConfigItem.TYPE_CONFIRM, 0));
					insertAdminLog("关闭磁介质归还交接确认");
				}
			} else if (module.equalsIgnoreCase("STORAGE_BR")) {
				if (type.equalsIgnoreCase("open")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_STORAGE_BR_CONFIRM,
							SysConfigItem.NAME_STORAGE_BR_CONFIRM, null, SysConfigItem.TYPE_CONFIRM, 1));
					insertAdminLog("开启存储介质借用交接确认");
				}
				if (type.equalsIgnoreCase("close")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_STORAGE_BR_CONFIRM,
							SysConfigItem.NAME_STORAGE_BR_CONFIRM, null, SysConfigItem.TYPE_CONFIRM, 0));
					insertAdminLog("关闭存储介质借用交接确认");
				}
			} else if (module.equalsIgnoreCase("STORAGE_RT")) {
				if (type.equalsIgnoreCase("open")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_STORAGE_RT_CONFIRM,
							SysConfigItem.NAME_STORAGE_RT_CONFIRM, null, SysConfigItem.TYPE_CONFIRM, 1));
					insertAdminLog("开启存储介质归还交接确认");
				}
				if (type.equalsIgnoreCase("close")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_STORAGE_RT_CONFIRM,
							SysConfigItem.NAME_STORAGE_RT_CONFIRM, null, SysConfigItem.TYPE_CONFIRM, 0));
					insertAdminLog("关闭存储介质归还交接确认");
				}
			} else if (module.equalsIgnoreCase("COPY")) {
				if (type.equalsIgnoreCase("open")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_COPY_CONFIRM,
							SysConfigItem.NAME_COPY_CONFIRM, null, SysConfigItem.TYPE_CONFIRM, 1));
					insertAdminLog("开启复印交接确认");
				}
				if (type.equalsIgnoreCase("close")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_COPY_CONFIRM,
							SysConfigItem.NAME_COPY_CONFIRM, null, SysConfigItem.TYPE_CONFIRM, 0));
					insertAdminLog("关闭复印交接确认");
				}
			} else if (module.equalsIgnoreCase("ENTER")) {
				if (type.equalsIgnoreCase("open")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_ENTER_CONFIRM,
							SysConfigItem.NAME_ENTER_CONFIRM, null, SysConfigItem.TYPE_CONFIRM, 1));
					insertAdminLog("开启录入交接确认");
				}
				if (type.equalsIgnoreCase("close")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_ENTER_CONFIRM,
							SysConfigItem.NAME_ENTER_CONFIRM, null, SysConfigItem.TYPE_CONFIRM, 0));
					insertAdminLog("关闭录入交接确认");
				}
			}
		} else {
			// 当数据库中没有字段，返回的对象是空无法getStartuse()
			if (basicService.getSysConfigItemValue(SysConfigItem.KEY_RETRIEVE_CONFIRM) != null) {
				retrieve_start = basicService.getSysConfigItemValue(SysConfigItem.KEY_RETRIEVE_CONFIRM).getStartuse();
			}
			if (basicService.getSysConfigItemValue(SysConfigItem.KEY_FILE_CONFIRM) != null) {
				file_start = basicService.getSysConfigItemValue(SysConfigItem.KEY_FILE_CONFIRM).getStartuse();
			}
			if (basicService.getSysConfigItemValue(SysConfigItem.KEY_TRANSFER_CONFIRM) != null) {
				transfer_start = basicService.getSysConfigItemValue(SysConfigItem.KEY_TRANSFER_CONFIRM).getStartuse();
			}
			if (basicService.getSysConfigItemValue(SysConfigItem.KEY_READ_BR_CONFIRM) != null) {
				read_br_start = basicService.getSysConfigItemValue(SysConfigItem.KEY_READ_BR_CONFIRM).getStartuse();
			}
			if (basicService.getSysConfigItemValue(SysConfigItem.KEY_READ_RT_CONFIRM) != null) {
				read_rt_start = basicService.getSysConfigItemValue(SysConfigItem.KEY_READ_RT_CONFIRM).getStartuse();
			}
			if (basicService.getSysConfigItemValue(SysConfigItem.KEY_DEVICE_BR_CONFIRM) != null) {
				device_br_start = basicService.getSysConfigItemValue(SysConfigItem.KEY_DEVICE_BR_CONFIRM).getStartuse();
			}
			if (basicService.getSysConfigItemValue(SysConfigItem.KEY_DEVICE_RT_CONFIRM) != null) {
				device_rt_start = basicService.getSysConfigItemValue(SysConfigItem.KEY_DEVICE_RT_CONFIRM).getStartuse();
			}
			if (basicService.getSysConfigItemValue(SysConfigItem.KEY_STORAGE_BR_CONFIRM) != null) {
				storage_br_start = basicService.getSysConfigItemValue(SysConfigItem.KEY_STORAGE_BR_CONFIRM)
						.getStartuse();
			}
			if (basicService.getSysConfigItemValue(SysConfigItem.KEY_STORAGE_RT_CONFIRM) != null) {
				storage_rt_start = basicService.getSysConfigItemValue(SysConfigItem.KEY_STORAGE_RT_CONFIRM)
						.getStartuse();
			}
			if (basicService.getSysConfigItemValue(SysConfigItem.KEY_COPY_CONFIRM) != null) {
				copy_start = basicService.getSysConfigItemValue(SysConfigItem.KEY_COPY_CONFIRM).getStartuse();
			}
			if (basicService.getSysConfigItemValue(SysConfigItem.KEY_ENTER_CONFIRM) != null) {
				enter_start = basicService.getSysConfigItemValue(SysConfigItem.KEY_ENTER_CONFIRM).getStartuse();
			}
		}
		return SUCCESS;
	}
}
