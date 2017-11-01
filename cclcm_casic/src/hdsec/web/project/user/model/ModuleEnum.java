package hdsec.web.project.user.model;

import org.apache.log4j.Logger;

/**
 * 功能模块枚举类 2014-5-7 下午5:19:54
 * 
 * @author renmingfei
 */
public enum ModuleEnum {
	BURN("BURN", "刻录功能模块", false), PRINT("PRINT", "打印功能模块", false), CONFIRM("CONFIRM", "交接确认功能模块", false), COPY("COPY",
			"复印功能模块", false), OUTCOPY("OUTCOPY", "外来文件复印功能模块", false), LEADIN("LEADIN", "录入功能模块", false), DEVICE(
			"DEVICE", "磁介质借用功能模块", false), TRANSFER("TRANSFER", "流转功能模块", false), BORROW("BORROW", "借用功能模块", false), DELAY(
			"DELAY", "延期回收功能模块", false), STORAGE("STORAGE", "存储介质功能模块", false), CHANGE("CHANGE", "载体归属转换", false), INPUT(
			"INPUT", "电子文件导入模块", false);
	private final String module_code;
	private final String module_name;
	private boolean module_enable;
	private static Logger logger = Logger.getLogger(ModuleEnum.class);

	private ModuleEnum(String module_code, String module_name, boolean module_enable) {
		this.module_code = module_code;
		this.module_name = module_name;
		this.module_enable = module_enable;
	}

	public String getModuleCode() {
		return this.module_code;
	}

	public String getModuleName() {
		return this.module_name;
	}

	public boolean isModuleEnable() {
		return this.module_enable;
	}

	public void enableModule() {
		logger.info("Module[" + module_code + "] enabled");
		this.module_enable = true;
	}

	public void disableModule() {
		logger.info("Module[" + module_code + "] disabled");
		this.module_enable = false;
	}

	public void switchModuleEnable(boolean is_enable) {
		logger.info("Switch Module[" + module_code + "] enable state to " + is_enable);
		this.module_enable = is_enable;
	}
}
