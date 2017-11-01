package hdsec.web.project.common.bm;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 新增业务任务流程类型
 * 
 * @author gaoximin 2015-7-7
 */
public enum ExtendsJobTypeEnum {
	USERSECLV_CHANGE("USERSECLV_CHANGE", "用户密级变更", true), USERSEC_ACTIVITY("USERSEC_ACTIVITY", "涉密活动", true), SECUSER_ABROAD(
			"SECUSER_ABROAD", "涉密人员因私出国", true), SECUSER_ENTRUST("SECUSER_ENTRUST", "委托保密管理", true), RESIGN("RESIGN",
			"离职", true), ENTER_SECPLACE("ENTER_SECPLACE", "进出涉密场所", true), EVENT_SECPLACE("EVENT_SECPLACE", "申请涉密场所",
			true), OUT_EXCHANGE("OUT_EXCHANGE", "涉外交流", true), PURCHASE("PURCHASE", "资产采购", true), WASTE("WASTE",
			"资产报废", true), STORE("STORE", "入库", true), PROPERTYOUT("PROPERTYOUT", "出库", true),EVENT_REPORT("EVENT_REPORT", "宣传报道", true);
	private final String code;
	private final String name;
	private boolean is_used;
	private static Logger logger = Logger.getLogger(ExtendsJobTypeEnum.class);

	private ExtendsJobTypeEnum(String code, String name, boolean is_used) {
		this.code = code;
		this.name = name;
		this.is_used = is_used;
	}

	public String getJobTypeCode() {
		return this.code;
	}

	public String getJobTypeName() {
		return this.name;
	}

	public boolean isJobTypeUsed() {
		return this.is_used;
	}

	public void enableUsed() {
		logger.info("JobType[" + code + "] used");
		this.is_used = true;
	}

	public void disableUsed() {
		logger.info("JobType[" + code + "] unUsed");
		this.is_used = false;
	}

	public void switchUsed() {
		this.is_used = !is_used;
		logger.info("Switch JobType[" + code + "] used state to " + is_used);
	}

	public String getEventTableName() {
		switch (this) {
		case PURCHASE:
			return "event_purchase";
		case WASTE:
			return "event_waste";
		}
		return null;
	}

	public static List<ExtendsJobTypeEnum> getAllJobTypeList() {
		List<ExtendsJobTypeEnum> list = new ArrayList<ExtendsJobTypeEnum>();
		for (ExtendsJobTypeEnum item : ExtendsJobTypeEnum.values()) {
			list.add(item);
		}
		return list;
	}

	public static ExtendsJobTypeEnum[] getAllJobType() {
		return ExtendsJobTypeEnum.values();
	}

	public static List<ExtendsJobTypeEnum> getUsedJobTypeList() {
		List<ExtendsJobTypeEnum> list = new ArrayList<ExtendsJobTypeEnum>();
		for (ExtendsJobTypeEnum item : ExtendsJobTypeEnum.values()) {
			if (item.isJobTypeUsed()) {
				list.add(item);
			}
		}
		return list;
	}
}
