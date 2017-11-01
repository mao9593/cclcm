package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysConsole;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.model.ModuleEnum;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

public class UpdateConsoleAction extends BasicBaseAction {

	private static final long serialVersionUID = 1L;
	private String console_code;// 控制台代号
	private String console_name;// 控制台
	private String hardware_type;// 硬件类型
	private String console_type;// 控制台类型
	private int seclv_code;// 密级
	private String console_location;// 位置
	private String curr_version;// 当前版本
	private String set_version;// 设定版本
	private String console_mac;// MAC 地址
	private String console_ipaddr;// ip 地址
	private Date install_time;// 安装时间
	private String console_status;// 监控状态
	private Date uninstall_time;// 卸载时间
	private String is_online;// 是否在线
	private Date last_connect_time;// 上次连接时间
	private String is_barcode_print;// 是否出条形码
	private SysConsole console = null;
	private String update = "N";
	private String allowSecLevel;
	private String define_value;
	private Integer id;
	private String new_console_code = "";

	public void setDefine_value(String define_value) {
		if (StringUtils.hasLength(define_value)) {
			this.define_value = Constants.COMMON_SEPARATOR
					+ define_value.replaceAll(" ", "").replaceAll(",", Constants.COMMON_SEPARATOR)
					+ Constants.COMMON_SEPARATOR;
		}
	}

	public void setNew_console_code(String new_console_code) {
		this.new_console_code = new_console_code;
	}

	public String getConsole_code() {
		return console_code;
	}

	public void setConsole_code(String console_code) {
		this.console_code = console_code;
	}

	public String getConsole_name() {
		return console_name;
	}

	public void setConsole_name(String console_name) {
		this.console_name = console_name;
	}

	public String getHardware_type() {
		return hardware_type;
	}

	public void setHardware_type(String hardware_type) {
		this.hardware_type = hardware_type;
	}

	public int getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(int seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getConsole_location() {
		return console_location;
	}

	public void setConsole_location(String console_location) {
		this.console_location = console_location;
	}

	public String getCurr_version() {
		return curr_version;
	}

	public void setCurr_version(String curr_version) {
		this.curr_version = curr_version;
	}

	public String getSet_version() {
		return set_version;
	}

	public void setSet_version(String set_version) {
		this.set_version = set_version;
	}

	public String getConsole_mac() {
		return console_mac;
	}

	public void setConsole_mac(String console_mac) {
		this.console_mac = console_mac;
	}

	public String getConsole_ipaddr() {
		return console_ipaddr;
	}

	public void setConsole_ipaddr(String console_ipaddr) {
		this.console_ipaddr = console_ipaddr;
	}

	public Date getInstall_time() {
		return install_time;
	}

	public void setInstall_time(Date install_time) {
		this.install_time = install_time;
	}

	public String getConsole_status() {
		return console_status;
	}

	public void setConsole_status(String console_status) {
		this.console_status = console_status;
	}

	public Date getUninstall_time() {
		return uninstall_time;
	}

	public void setUninstall_time(Date uninstall_time) {
		this.uninstall_time = uninstall_time;
	}

	public String getIs_online() {
		return is_online;
	}

	public void setIs_online(String is_online) {
		this.is_online = is_online;
	}

	public Date getLast_connect_time() {
		return last_connect_time;
	}

	public void setLast_connect_time(Date last_connect_time) {
		this.last_connect_time = last_connect_time;
	}

	public String getIs_barcode_print() {
		return is_barcode_print;
	}

	public void setIs_barcode_print(String is_barcode_print) {
		this.is_barcode_print = is_barcode_print;
	}

	public SysConsole getConsole() {
		return console;
	}

	public void setConsole(SysConsole console) {
		this.console = console;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public boolean getIs_print() {
		return ModuleEnum.PRINT.isModuleEnable();
	}

	public boolean getIs_burn() {
		return ModuleEnum.BURN.isModuleEnable();
	}

	public boolean getIs_confirm() {
		return ModuleEnum.CONFIRM.isModuleEnable();
	}

	public boolean getIs_transfer() {
		return ModuleEnum.TRANSFER.isModuleEnable();
	}

	public boolean getIs_device() {
		return ModuleEnum.DEVICE.isModuleEnable();
	}

	public boolean getIs_borrow() {
		return ModuleEnum.BORROW.isModuleEnable();
	}

	public boolean getIs_storage() {
		return ModuleEnum.STORAGE.isModuleEnable();
	}

	public boolean getFile_confirm() {
		return basicService.isConfirmOpen("FILE_CONFIRM");
	}

	public boolean getRetrieve_confirm() {
		return basicService.isConfirmOpen("RETRIEVE_CONFIRM");
	}

	public boolean getTransfer_confirm() {
		return basicService.isConfirmOpen("TRANSFER_CONFIRM");
	}

	public boolean getRead_br_confirm() {
		return basicService.isConfirmOpen("READ_BR_CONFIRM");
	}

	public boolean getRead_rt_confirm() {
		return basicService.isConfirmOpen("READ_RT_CONFIRM");
	}

	public boolean getDevice_br_confirm() {
		return basicService.isConfirmOpen("DEVICE_BR_CONFIRM");
	}

	public boolean getDevice_rt_confirm() {
		return basicService.isConfirmOpen("DEVICE_RT_CONFIRM");
	}

	public boolean getStorage_br_confirm() {
		return basicService.isConfirmOpen("STORAGE_BR_CONFIRM");
	}

	public boolean getStorage_rt_confirm() {
		return basicService.isConfirmOpen("STORAGE_RT_CONFIRM");
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("N")) {
			console = basicService.getConsoleByCode(console_code);
			return SUCCESS;
		} else {
			// 1 判读控制台id是否修改 无则走原来逻辑
			if (!console_code.equalsIgnoreCase(new_console_code)) {
				// 1.新控制台号不能为空
				if (new_console_code.isEmpty()) {

				} else {
					// 修改后的控制台id 是否已经存在 ，不存在则修改 ，存在则不能修改
					SysConsole console = basicService.getConsoleByCode(new_console_code);
					if (console == null) {
						console = new SysConsole(new_console_code, console_name, hardware_type, console_type,
								seclv_code, console_location, curr_version, set_version, console_mac, console_ipaddr,
								install_time, console_status, uninstall_time, is_online, last_connect_time,
								is_barcode_print, allowSecLevel);
						basicService.delConsoleByCode(console_code);
						basicService.addConsole(console);
						insertAdminLog("修改控制台：代号[" + console_code + "修改为 " + new_console_code + "],名称[" + console_name
								+ "]");
					} else {
						// 控制台号已经存在，请重新输入
					}

				}
			} else {
				allowSecLevel = define_value;
				console = new SysConsole(console_code, console_name, hardware_type, console_type, seclv_code,
						console_location, curr_version, set_version, console_mac, console_ipaddr, install_time,
						console_status, uninstall_time, is_online, last_connect_time, is_barcode_print, allowSecLevel);
				basicService.updateConsole(console);
				insertAdminLog("修改控制台：代号[" + console_code + "],名称[" + console_name + "]");
			}
			return "ok";
		}
	}

	public String getAllowSecLevel() {
		return allowSecLevel;
	}

	public void setAllowSecLevel(String allowSecLevel) {
		this.allowSecLevel = allowSecLevel;
	}

	public String getDefine_value() {
		return define_value;
	}

	public String getConsole_type() {
		return console_type;
	}

	public void setConsole_type(String console_type) {
		if (StringUtils.hasLength(console_type)) {
			this.console_type = Constants.COMMON_SEPARATOR
					+ console_type.replaceAll(" ", "").replaceAll(",", Constants.COMMON_SEPARATOR)
					+ Constants.COMMON_SEPARATOR;
		}
	}

}
