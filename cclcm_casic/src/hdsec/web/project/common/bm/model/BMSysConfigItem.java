package hdsec.web.project.common.bm.model;

import hdsec.web.project.basic.model.SysConfigItem;

/**
 * 保密新增，参数配置
 * 
 * @author gaoximin 2015-7-6
 */
public class BMSysConfigItem extends SysConfigItem {
	// LOGO SET
	public static final String KEY_LOGO_SET = "LOGO_SET";
	public static final String NAME_LOGO_SET = "LOGO配置";
	public static final String TYPE_LOGO_SET = "LOGO_DEFAULT";

	// 声光电单位组织机构代号
	public static final String SGDGC_CODE = "01"; // 声光电公司
	public static final String ERSI_CODE = "0111"; // 24所
	public static final String ERLIU_CODE = "0112"; // 26所
	public static final String SISI_CODE = "0113"; // 44所

	public static final String SERIAL_NUM = "123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String COMMON_SEPARATOR_END = "end\\|#";

	public BMSysConfigItem() {
		super();
	}

	public BMSysConfigItem(String item_key, String item_name, String item_value, String item_type, Integer startuse) {
		super(item_key, item_name, item_value, item_type, startuse);

	}
}
