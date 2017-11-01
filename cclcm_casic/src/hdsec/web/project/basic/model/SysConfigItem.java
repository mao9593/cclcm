package hdsec.web.project.basic.model;

/**
 * 设置参数项
 * 
 * @author lixiang
 * 
 */
public class SysConfigItem {
	// 刻录参数配置
	public static final String KEY_UPLOAD_BURNFILE_NUM = "UPLOAD_BURNFILE_NUM";// 刻录上传文件限制数目
	public static final String NAME_UPLOAD_BURNFILE_NUM = "刻录上传文件限制数目";
	public static final String KEY_UPLOAD_BURNFILE_ZIPTYPE = "UPLOAD_BURNFILE_ZIPTYPE";// 刻录上传文件限制类型
	public static final String NAME_UPLOAD_BURNFILE_ZIPTYPE = "刻录上传文件限制类型";
	public static final String TYPE_BURN = "BURN";// 刻录配置

	// 打印参数配置
	public static final String KEY_PRINT_VALID_DAYS = "PRINT_VALID_DAYS";// 打印任务有效时间
	public static final String KEY_REPPRINT_VALID_DAYS = "REPPRINT_VALID_DAYS";// 补打有效时间
	public static final String KEY_REPPRINT_TIMES = "REPPRINT_TIMES";// 补打限制次数
	public static final String KEY_PRINTFILE_DAYS_ = "PRINTFILE_DAYS_";// 文档保存时间
	public static final String NAME_PRINT_VALID_DAYS = "打印任务有效时间";
	public static final String NAME_REPPRINT_VALID_DAYS = "补打有效时间";
	public static final String NAME_REPPRINT_TIMES = "补打限制次数";
	public static final String NAME_PRINTFILE_DAYS = "文档保存时间";
	public static final String TYPE_PRINT = "PRINT";// 打印配置
	public static final String TYPE_PRINT_SECLV = "PRINT_SECLV";// 打印配置中与密级相关的配置

	// 空间使用配置
	public static final String KEY_SPACE_USED = "SPACE_USED";// 磁盘空间使用百分比
	public static final String KEY_ALARM_PERCENT = "ALARM_PERCENT";// 磁盘空间告警百分比
	public static final String NAME_SPACE_USED = "磁盘空间使用百分比";
	public static final String NAME_ALARM_PERCENT = "磁盘空间告警百分比";
	public static final String TYPE_SPACE = "SPACE";// 空间使用配置

	// 交接确认配置
	public static final String KEY_RETRIEVE_CONFIRM = "RETRIEVE_CONFIRM";// 回收模块交接确认
	public static final String KEY_FILE_CONFIRM = "FILE_CONFIRM";// 归档模块交接确认
	public static final String KEY_TRANSFER_CONFIRM = "TRANSFER_CONFIRM";// 流转模块交接确认
	public static final String KEY_READ_BR_CONFIRM = "READ_BR_CONFIRM";// 部门载体借用模块交接确认
	public static final String KEY_READ_RT_CONFIRM = "READ_RT_CONFIRM";// 部门载体归还模块交接确认
	public static final String KEY_DEVICE_BR_CONFIRM = "DEVICE_BR_CONFIRM";// 磁介质借用模块交接确认
	public static final String KEY_DEVICE_RT_CONFIRM = "DEVICE_RT_CONFIRM";// 磁介质归还模块交接确认
	public static final String KEY_STORAGE_BR_CONFIRM = "STORAGE_BR_CONFIRM";// 存储介质分配模块交接确认
	public static final String KEY_STORAGE_RT_CONFIRM = "STORAGE_RT_CONFIRM";// 存储介质归还模块交接确认
	public static final String KEY_COPY_CONFIRM = "COPY_CONFIRM";// 复印模块交接确认
	public static final String KEY_ENTER_CONFIRM = "ENTER_CONFIRM";// 录入模块交接确认
	public static final String KEY_CLIENT_MSG = "CLIENT_MSG";// 消息提醒开关配置
	public static final String NAME_RETRIEVE_CONFIRM = "回收模块交接确认";
	public static final String NAME_FILE_CONFIRM = "归档模块交接确认";
	public static final String NAME_TRANSFER_CONFIRM = "流转模块交接确认";
	public static final String NAME_READ_BR_CONFIRM = "部门载体借用模块交接确认";
	public static final String NAME_READ_RT_CONFIRM = "部门载体归还模块交接确认";
	public static final String NAME_DEVICE_BR_CONFIRM = "磁介质借用模块交接确认";
	public static final String NAME_DEVICE_RT_CONFIRM = "磁介质归还模块交接确认";
	public static final String NAME_STORAGE_BR_CONFIRM = "存储介质分配模块交接确认";
	public static final String NAME_STORAGE_RT_CONFIRM = "存储介质归还模块交接确认";
	public static final String NAME_COPY_CONFIRM = "复印模块交接确认";
	public static final String NAME_ENTER_CONFIRM = "录入模块交接确认";
	public static final String NAME_CLIENT_MSG = "消息提醒开关配置";
	public static final String TYPE_CONFIRM = "CONFIRM";// 交接确认配置
	public static final String TYPE_MESSAGE = "SYSTEM";// 消息提醒开关配置

	// 客户端审批消息提醒和回收消息提醒开关设置
	// 审批消息提醒开关
	public static final String KEY_APPROVE_ON_OFF = "APPROVE_ON_OFF";
	public static final String NAME_APPROVE_ON_OFF = "审批消息提醒开关";
	public static final String TYPE_APPROVE_ON_OFF = "CLIENT_MSG";

	// 回收消息提醒开关
	public static final String KEY_RETRIEVE_ON_OFF = "RETRIEVE_ON_OFF";
	public static final String NAME_RETRIEVE_ON_OFF = "回收消息提醒开关";
	public static final String TYPE_RETRIEVE_ON_OFF = "CLIENT_MSG";

	// 自审批设置
	public static final String KEY_SELF_APPROVE = "SELF_APPROVE";
	public static final String NAME_SELF_APPROVE = "自审批流程";
	public static final String TYPE_APPROVE = "APPROVE";

	// 关键字检测
	public static final String KEY_KEYWORD = "KEYWORD";
	public static final String NAME_KEYWORD = "关键字检测";
	public static final String TYPE_KEYWORD = "PRINT_KW";

	// 回收设置
	public static final String KEY_Recover_On_Off = "Recover_On_Off";
	public static final String NAME_Recover_On_Off = "回收开关";
	public static final String TYPE_Recover_On_Off = "CONFIRM";

	// 送销设置
	public static final String KEY_SendDestroy_On_Off = "SendDestroy_On_Off";
	public static final String NAME_SendDestroy_On_Off = "送销开关";
	public static final String TYPE_SendDestroy_On_Off = "CONFIRM";

	// 流转消息提醒开关
	public static final String KEY_TRANSFER_ON_OFF = "TRANSFER_ON_OFF";
	public static final String NAME_TRANSFER_ON_OFF = "流转消息提醒开关";
	public static final String TYPE_TRANSFER_ON_OFF = "CLIENT_MSG";

	// 借用消息提醒开关
	public static final String KEY_BORROW_ON_OFF = "BORROW_ON_OFF";
	public static final String NAME_BORROW_ON_OFF = "借用消息提醒开关";
	public static final String TYPE_BORROW_ON_OFF = "CLIENT_MSG";

	// 回收提醒时间
	public static final String KEY_IMPORT_SHORT_DAYS = "IMPORT_SHORT_DAYS";
	public static final String NAME_IMPORT_SHORT_DAYS = "载体短期留用时间";
	public static final String KEY_REMIND_MSG_DAYS = "REMIND_MSG_DAYS";
	public static final String NAME_REMIND_MSG_DAYS = "载体回收消息提醒周期";
	public static final String TYPE_REMIND = "REMIND_TIME";

	// 外发拒收后载体状态
	public static final String KEY_SEND_REJECT_STATUS = "SEND_REJECT_STATUS";
	public static final String NAME_SEND_REJECT_STATUS = "外发拒收后载体状态";
	public static final String TYPE_SEND = "SEND";

	// 默认编码设置
	public static final String KEY_DEFAULT_PASSWORD = "DEFAULT_PASSWORD";
	public static final String NAME_DEFAULT_PASSWORD = "默认密码设置";
	public static final String TYPE_DEFAULT = "DEFAULT_PASSWORD";
	public static final String KEY_PWD_LENGTH = "PWD_LENGTH";// 密码长度
	public static final String KEY_PWD_DIGIT = "PWD_DIGIT";// 包含数字
	public static final String KEY_PWD_ALPHA = "PWD_ALPHA";// 包含字母
	public static final String KEY_PWD_NOTATION = "PWD_NOTATION";// 特殊字符范围

	// 设置三员登录IP
	public static final String KEY_SECADMIN_LOGIN_IP = "SECADMIN_LOGIN_IP";
	public static final String NAME_SECADMIN_LOGIN_IP = "secadmin设定IP";
	public static final String KEY_SYSADMIN_LOGIN_IP = "SYSADMIN_LOGIN_IP";
	public static final String NAME_SYSADMIN_LOGIN_IP = "sysadmin设定IP";
	public static final String KEY_AUDADMIN_LOGIN_IP = "AUDADMIN_LOGIN_IP";
	public static final String NAME_AUDADMIN_LOGIN_IP = "audadmin设定IP";
	public static final String ADMIN_IP_TYPE = "THREE_ADMIN_LOGIN_IP";

	public static final String KEY_MAX_CHANGE_RESULT_DAYS = "MAX_CHANGE_RESULT_DAYS";// 标记失败最长天数
	public static final String NAME_MAX_CHANGE_RESULT_DAYS = "标记失败最长天数";
	public static final String TYPE_SYSTEM_CONFIG = "SYSTEM_CONFIG";// 全局系统配置，对所有模块均生效的配置项

	public static final String KEY_RECEIPT_WITH_SECLV = "RECEIPT_WITH_SECLV";
	public static final String NAME_RECEIPT_WITH_SECLV = "可输出交接单的密级";

	// 设置控制台退出密码
	public static final String KEY_CONSOLE_EXIT_PASSWORD = "CONSOLE_EXIT_PASSWORD";// 控制台退出密码设置
	public static final String NAME_CONSOLE_EXIT_PASSWORD = "控制台退出密码设置";

	// 部门载体归属转换个人模式-7所定制
	public static final String KEY_DEPTTOPERSON_MODE = "DEPTTOPERSON_MODE";
	public static final String NAME_DEPTTOPERSON_MODE = "部门载体归属转换个人模式";
	public static final String TYPE_DEPTTOPERSON_MODE = "DTPM";

	private String item_key = "";
	private String item_name = "";
	private String item_value = "";
	private String item_type = "";
	private Integer startuse;
	private String item_value_name = "";

	public String getItem_value_name() {
		return item_value_name;
	}

	public void setItem_value_name(String item_value_name) {
		this.item_value_name = item_value_name;
	}

	public String getItem_key() {
		return item_key;
	}

	public void setItem_key(String item_key) {
		this.item_key = item_key;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_value() {
		return item_value;
	}

	public void setItem_value(String item_value) {
		this.item_value = item_value;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public Integer getStartuse() {
		return startuse;
	}

	public void setStartuse(Integer startuse) {
		this.startuse = startuse;
	}

	public SysConfigItem(String item_key, String item_name, String item_value, String item_type, Integer startuse) {
		super();
		this.item_key = item_key;
		this.item_name = item_name;
		this.item_value = item_value;
		this.item_type = item_type;
		this.startuse = startuse;
	}

	public SysConfigItem() {
		super();
	}
}
