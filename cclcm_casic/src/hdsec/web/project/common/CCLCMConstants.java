package hdsec.web.project.common;

/**
 * 涉密载体常量类
 * 
 * @author renmingfei
 * 
 */
public class CCLCMConstants {

	/**
	 * 被委托用户ID(在session中使用)
	 */
	public static final String APPROVE_AGENT_USERID_KEY = "AGENT_USERID";
	public static final String APPROVE_AGENT_USERNAME_KEY = "AGENT_USERNAME";
	public static final String APPROVE_APPLY_USERID_KEY = "APPLY_USERID";
	public static final String APPROVE_APPLY_USERPW_KEY = "APPLY_USERPW";
	public static final String APPROVE_AGENT_TYPE_KEY = "AGENT_TYPE";
	/**
	 * 委托类型
	 */
	public static final Integer AGENT_TYPE_ALL = 0;
	public static final Integer AGENT_TYPE_EXPORT = 1;
	public static final Integer AGENT_TYPE_IMPORT = 2;
	/**
	 * 分隔符，用于分割罗列的数据
	 */
	public static final String DEVIDE_SYMBOL = ":";

	/**
	 * 每个excel sheet最多放置记录
	 */
	public static final int SHEET_SIZE = 60000;
	/**
	 * 配置文件中单位编码的唯一标志
	 */
	public static final String UNIT_CODE = "unit.code";

	/**
	 * 配置文件中条码版本标识
	 */
	public static final String UNIT_BARCODE = "unit.barcode";

	/**
	 * 配置文件中单位名称
	 */
	public static final String UNIT_NAME = "unit.name";
}
