package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysConsole;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.model.ModuleEnum;
import hdsec.web.project.user.model.SecLevel;

import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 添加控制台
 * 
 * @author yy
 * 
 */
public class AddConsoleAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String console_code = "";// 控制台代号
	private String console_name;// 控制台
	private String hardware_type;// 硬件类型
	private String console_type;// 控制台类型
	private int seclv_code;// 密级
	private String console_location;// 位置
	private String set_version;// 设定版本
	private String is_barcode_print;// 是否出条形码
	private SysConsole console;
	private String define_value;
	private String allowSecLevel;
	private int id;

	public void setDefine_value(String define_value) {
		if (StringUtils.hasLength(define_value)) {
			this.define_value = Constants.COMMON_SEPARATOR
					+ define_value.replaceAll(" ", "").replaceAll(",", Constants.COMMON_SEPARATOR)
					+ Constants.COMMON_SEPARATOR;
		}
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

	public String getSet_version() {
		return set_version;
	}

	public void setSet_version(String set_version) {
		this.set_version = set_version;
	}

	public String getIs_barcode_print() {
		return is_barcode_print;
	}

	public void setIs_barcode_print(String is_barcode_print) {
		this.is_barcode_print = is_barcode_print;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public String getDefine_value() {
		return define_value;
	}

	public SysConsole getConsole() {
		return console;
	}

	public void setConsole(SysConsole console) {
		this.console = console;
	}

	public String getAllowSecLevel() {
		return allowSecLevel;
	}

	public void setAllowSecLevel(String allowSecLevel) {
		this.allowSecLevel = allowSecLevel;
	}

	public String getConsole_type() {
		return console_type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setConsole_type(String console_type) {
		if (StringUtils.hasLength(console_type)) {
			this.console_type = Constants.COMMON_SEPARATOR
					+ console_type.replaceAll(" ", "").replaceAll(",", Constants.COMMON_SEPARATOR)
					+ Constants.COMMON_SEPARATOR;
		}
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
		if (console_code.isEmpty()) {
			return SUCCESS;
		} else {
			if (basicService.checkConsole(console_code.trim(), console_name.trim())) {
				throw new Exception("控制台名称或代号已经存在！");
			}
			allowSecLevel = define_value;
			SysConsole console = new SysConsole(console_code.trim(), console_name.trim(), hardware_type, console_type,
					seclv_code, console_location, set_version, is_barcode_print, allowSecLevel);
			basicService.addConsole(console);
			insertAdminLog("添加控制台：代号[" + console_code + "],名称[" + console_name + "]");
			return "ok";
		}

	}
}
