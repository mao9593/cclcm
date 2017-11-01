package hdsec.web.project.user.model;

/**
 * 系统配置参数项
 * @author renmingfei
 *
 */
public class ConfigItem {
	//登录设置
	public static final String KEY_PWD_LENGTH = "PWD_LENGTH";//密码长度
	public static final String KEY_VALID_DAYS = "VALID_DAYS";//密码有效期
	public static final String KEY_FAIL_TIMES = "FAIL_TIMES";//尝试失败次数上限
	public static final String KEY_PWD_DIGIT = "PWD_DIGIT";//包含数字
	public static final String KEY_PWD_ALPHA = "PWD_ALPHA";//包含字母
	public static final String KEY_PWD_NOTATION = "PWD_NOTATION";//特殊字符范围
	//系统设置
	public static final String KEY_SPACE_USED = "SPACE_USED";//当前日志存储空间百分比（只读）
	public static final String KEY_MAX_PERCENT = "MAX_PERCENT";//日志存储空间阀值
	public static final String KEY_ALARM_PERCENT = "ALARM_PERCENT";//日志空间告警百分比
	public static final String KEY_STORE_PERIOD = "STORE_PERIOD";//审计日志保存周期
	public static final String KEY_SPACE_FULL = "SPACE_FULL";//审计日志保存周期
	public static final String KEY_SYSTEM_VERSION = "SYSTEM_VERSION";//系统版本号
	public static final String KEY_CLIENT_VERSION = "CLIENT_VERSION";//客户端最新版本号
	
	private String item_key = "";
	private String item_value = "";
	private Integer startuse;
	
	public String getItem_key() {
		return item_key;
	}
	
	public void setItem_key(String item_key) {
		this.item_key = item_key;
	}
	
	public String getItem_value() {
		return item_value;
	}
	
	public void setItem_value(String item_value) {
		this.item_value = item_value;
	}
	
	public Integer getStartuse() {
		return startuse;
	}
	
	public void setStartuse(Integer startuse) {
		this.startuse = startuse;
	}
	
	public ConfigItem(String item_key, String item_value, Integer startuse) {
		super();
		this.item_key = item_key;
		this.item_value = item_value;
		this.startuse = startuse;
	}
	
	public ConfigItem() {
		super();
	}
	
}
