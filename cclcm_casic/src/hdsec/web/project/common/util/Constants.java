package hdsec.web.project.common.util;

public class Constants {
	
	/**
	 * 存储在session或request中的采用XMLHTTP请求方式执行后存放结果的关键字。
	 */
	public static final String XML_HTTP_RESULT_KEY = "XMLHTTPResult";
	/**
	 * 存储配置文件名称，方便在代码中使用配置文件
	 */
	public static final String PROJECT_CONFIG_FILE = "hdsec.web.project.config.properties";
	/**
	 * 存储在session中的临时访问控制对象的关键字，仅用于角色管理的操作资源配置
	 */
	public static final String SESSION_ACL_KEY = "SessionACL";
	public static final String COOKIE_MENU = "COOKIE_MENU";
	/**
	 * 以下是用户登录成功后存进session中的数据的key
	 */
	public static final String SESSION_USER_KEY = "USER_INFO";
	public static final String SESSION_SPEC_ROLE_KEY = "SPEC_ROLE";
	public static final String SESSION_SPEC_OPER_KEY = "SPEC_OPER";
	public static final String LOGON_TIME = "LOGON_TIME";
	public static final String LOGON_IP = "LOGON_IP";
	public static final String SESSION_LDAP_USER_KEY = "USER_DATA";
	/**
	 * 添加功能时，如果没有指定图标路径，则默认使用此图标
	 */
	public static final String DEFAULT_ICON_PATH = "_image/ico/default.gif";
	/**
	 * 4种角色，hidden只有admin用户拥有，readonly为三员角色，special为秘书类角色，要指定限定的资源
	 */
	public static final int ROLE_TYPE_HIDDEN = 1;
	public static final int ROLE_TYPE_READONLY = 2;
	public static final int ROLE_TYPE_COMMON = 3;
	public static final int ROLE_TYPE_SPECIAL = 4;
	/**
	 * 特殊角色限定资源类型的标记。
	 */
	public static final String ROLE_KEY_DEPT = "DEPT";
	public static final String ROLE_KEY_TERR = "TERR";
	/**
	 * 特殊角色限定资源对应的表名，在sql中使用
	 */
	public static final String ROLE_DOMAIN_TABLE_DEPT = "SEC_DEPT";
	public static final String ROLE_DOMAIN_TABLE_TERR = "SEC_TERRITORY";
	/**
	 * 统计在线用户时的门限值，非常量，可修改
	 */
	public static int normalValue = 150;
	public static int warningValue = 220;
	public static int criticalValue = 260;
	/**
	 * 系统日志类型
	 */
	public static final String LOG_SYSTEM_TYPE_1 = "一般消息";
	public static final String LOG_SYSTEM_TYPE_2 = "警告";
	public static final String LOG_SYSTEM_TYPE_3 = "严重";
	public static final String LOG_SYSTEM_TYPE_4 = "运维调试";
	/**
	 * 系统日志来源
	 */
	public static final String LOG_SYSTEM_SOURCE_1 = "服务器";
	public static final String LOG_SYSTEM_SOURCE_2 = "客户端";
	public static final String LOG_SYSTEM_SOURCE_3 = "控制台";
	public static final String LOG_SYSTEM_SOURCE_4 = "页面";
	public static final String LOG_SYSTEM_SOURCE_5 = "其他";
	/**
	 * 系统日志子系统标记
	 */
	public static final String LOG_SUBSYS_PRINT = "打印系统";
	public static final String LOG_SUBSYS_BURN = "刻录系统";
	public static final String LOG_SUBSYS_NAS = "安全NAS系统";
	public static final String LOG_SUBSYS_NAD = "接入控制系统";
	public static final String LOG_SUBSYS_ADMIN = "WEB维护系统";
	public static final String LOG_SUBSYS_MONITOR = "主机监控系统";
	/**
	 * 日志子系统存储编号
	 */
	public static final String LOG_SUBSYS_ADMIN_CODE = "admin";
	/**
	 * 普通用户操作日志类型
	 */
	public static final Integer LOG_COMMON_LOGIN = 1;// 登录日志
	public static final Integer LOG_COMMON_OPERATION = 2;// 登录日志
	/**
	 * 用户操作日志类型
	 */
	public static final Integer LOG_OPERATION = 1;
	public static final Integer LOG_LOGON = 2;
	/**
	 * 管道分隔符
	 */
	public static final String COMMON_SEPARATOR = "|";
	public static final String COMMON_SEPARATOR_REGEX = "[|]";
}
