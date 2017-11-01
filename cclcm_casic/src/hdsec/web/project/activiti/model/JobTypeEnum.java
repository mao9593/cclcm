package hdsec.web.project.activiti.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 任务流程类型 2014-4-16 下午9:36:52
 * 
 * @author renmingfei
 */
public enum JobTypeEnum {
	BURN_REMAIN("BURN_REMAIN", "刻录使用", false), BURN_SEND("BURN_SEND", "刻录外发", false), BURN_FILE("BURN_FILE", "刻录归档",
			false), PRINT_REMAIN("PRINT_REMAIN", "打印使用", false), PRINT_SEND("PRINT_SEND", "打印外发", false), PRINT_FILE(
			"PRINT_FILE", "打印归档", false), COPY("COPY", "复印使用", false), COPY_MERGE("COPY_MERGE", "复印合并", true), COPY_SEND(
			"COPY_SEND", "复印外发", false), COPY_FILE("COPY_FILE", "复印归档", false), OUTCOPY_REMAIN("OUTCOPY_REMAIN",
			"外来文件复印使用", false), OUTCOPY_SEND("OUTCOPY_SEND", "外来文件复印外发", false), OUTCOPY_FILE("OUTCOPY_FILE",
			"外来文件复印归档", false), LEADIN("LEADIN", "录入", false), DEVICE("DEVICE", "磁介质借用", false), TRANSFER("TRANSFER",
			"流转", false), BORROW("BORROW", "部门载体借用", false), DELAY("DELAY", "延期回收", false), SEND("SEND", "外发", false), FILE(
			"FILE", "归档", true), DESTROY("DESTROY", "销毁", false), DELAY_CD("DELAY_CD", "光盘延期回收", false), SEND_CD(
			"SEND_CD", "光盘外发", false), FILE_CD("FILE_CD", "光盘归档", false), DESTROY_CD("DESTROY_CD", "光盘销毁", false), DELAY_PAPER(
			"DELAY_PAPER", "文件延期回收", false), SEND_PAPER("SEND_PAPER", "文件外发", false), FILE_PAPER("FILE_PAPER", "文件归档",
			true), DESTROY_PAPER("DESTROY_PAPER", "文件销毁", true), DESTROY_DEVICE("DESTROY_DEVICE", "磁介质销毁", false), CHANGE(
			"CHANGE", "载体归属转换", false), SCAN_LEADIN("SCAN_LEADIN", "扫描录入", false), SENDES_PAPER("SENDES_PAPER", "文件送销",
			false), SENDES_CD("SENDES_CD", "光盘送销", false), CARRYOUT("CARRYOUT", "外带", true), CARRYOUT_PAPER(
			"CARRYOUT_PAPER", "文件外带", true), CARRYOUT_CD("CARRYOUT_CD", "光盘外带", true), MODIFY_SECLV("MODIFY_SECLV",
			"密级变更", true), SPECIAL_PRINT("SPECIAL_PRINT", "特殊打印", true), SPACECD_BORROW("SPACECD_BORROW", "空白盘领用", true), SPACECD_IMPORT(
			"SPACECD_IMPORT", "空白盘录入", true), PURCHASE("PURCHASE", "资产采购", false), PURCHASES("PURCHASES", "大额资产采购",
			false), WASTE("WASTE", "资产报废", false), STORE("STORE", "资产入库", false), PROPERTYOUT("PROPERTYOUT", "资产出库",
			false), PROPERTYCHANGE("PROPERTYCHANGE", "资产变更", false), USERSECLV_ADD("USERSECLV_ADD", "新增涉密人员", false), USERSECLV_CHANGE(
			"USERSECLV_CHANGE", "涉密人员变更", false), USERSEC_ACTIVITY("USERSEC_ACTIVITY", "重大涉密活动", false), SECUSER_ABROAD(
			"SECUSER_ABROAD", "涉密人员因私出国/境", false), SECUSER_ENTRUST("SECUSER_ENTRUST", "委托保密管理", false), RESIGN(
			"RESIGN", "脱密/离岗", false), ENTER_SECPLACE("ENTER_SECPLACE", "进出要害部门部位", false), EVENT_SECPLACE(
			"EVENT_SECPLACE", "申请涉密场所", false), OUT_EXCHANGE("OUT_EXCHANGE", "与境外交流保密工作", false), EVENT_INTCOM(
			"EVENT_INTCOM", "新增计算机网络机", false), EVENT_SINCOM("EVENT_SINCOM", "新增计算机单机", false), EVENT_CHGCOM(
			"EVENT_CHGCOM", "计算机变更", false), EVENT_DESCOM("EVENT_DESCOM", "计算机报废", false), USER_INFO("USER_INFO",
			"用户信息完善", false), EVENT_REPORT("EVENT_REPORT", "宣传报道保密审查(涉密网）", false), EVENT_REPORT2("EVENT_REPORT2",
			"宣传报道保密审查（报送）", false), EVENT_REPORT3("EVENT_REPORT3", "宣传报道保密审查（其他）", false), EVENT_DEPTREPORT(
			"EVENT_DEPTREPORT", "部门投稿保密审查", false), EVENT_INTRAPUBL("EVENT_INTRAPUBL", "内网信息发布保密审查", false), EVENT_INTERPUBL(
			"EVENT_INTERPUBL", "外网信息发布保密审查", false), FIELDIN("FIELDIN", "进入重要科研场地", false), INFO_DEVICE("INFO_DEVICE",
			"新增信息设备产品", false), INFO_OTHER("INFO_OTHER", "新增信息设备办公设备", false), DEVICE_CHANGE("DEVICE_CHANGE", "信息设备变更",
			false), CHANGE_OTHER("CHANGE_OTHER", "信息设备变更介质", false), DEVICE_DES("DEVICE_DES", "信息设备报废", false), EVENT_REPCOM(
			"EVENT_REPCOM", "计算机维修", false), EVENT_REINSTALL("EVENT_REINSTALL", "计算机重装系统", false), EVENT_QUITINT(
			"EVENT_QUITINT", "计算机退网", false), EVENT_USBKEY("EVENT_USBKEY", "USB-KEY申请/更新", false), INTER_EMAIL(
			"INTER_EMAIL", "外网电子邮件", false), SEC_CHECK("SEC_CHECK", "部门专项保密检查", false), FILEOUTMAKE("FILEOUTMAKE",
			"涉密文件外出制作", false), EVENT_OPENPORT("EVENT_OPENPORT", "开通端口", false), EVENT_LOCALPRINTER(
			"EVENT_LOCALPRINTER", "保留本地打印机", false), MATERIAL("MATERIAL", "对外提供资料", false), EXHIBITION("EXHIBITION",
			"展示展览保密审查", false), PUNISH_DEPT("PUNISH_DEPT", "部门保密自查违规", false), PUNISH_SECCHECK("PUNISH_SECCHECK",
			"单位保密检查违规", false), PUNISH_RECTIFY("PUNISH_RECTIFY", "保密整改督查", false), PAPER_RESEARCH("PAPER_RESEARCH",
			"科研技术类论文发表", false), PAPER_OTHERS("PAPER_OTHERS", "政研管理类论文发表", false), PAPERPATENT("PAPERPATENT", "专利申请",
			false), BORROW_BOOK("BORROW_BOOK", "笔记本借用公司内", false), PERSONAL_FILE("PERSONAL_FILE", "个人涉密资料", false), BORROW_BOOKOUT(
			"BORROW_BOOKOUT", "笔记本借用公司外", false), BOOK_CHANGE("BOOK_CHANGE", "笔记本变更", false), BOOK_REPAIR(
			"BOOK_REPAIR", "笔记本维修", false), BOOK_DES("BOOK_DES", "笔记本报废", false), BOOK_REINSTALL("BOOK_REINSTALL",
			"笔记本重装系统", false), BRWARCH("BRWARCH", "档案借阅", false), URGENTPURCHASE("URGENTPURCHASE", "紧急采购", false), PAPER_DEL(
			"PAPER_DEL", "文件台账删除", false), PAPER_MODIFY("PAPER_MODIFY", "文件台账修改", false), QIYUAN_BORROW_BOOK(
			"QIYUAN_BORROW_BOOK", "便携式计算机借用", false), FILE_DESTROY("FILE_DESTROY", "已归档文件销毁", false), FILECD_DESTROY(
			"FILECD_DESTROY", "已归档光盘销毁", false), DESTROY_PAPER_BYSELF("DESTROY_PAPER_BYSELF", "文件监销", true), DESTROY_CD_BYSELF(
			"DESTROY_CD_BYSELF", "光盘监销", true), DESTROY_DEVICE_BYSELF("DESTROY_DEVICE_BYSELF", "磁介质监销", true), MSG_INPUT(
			"MSG_INPUT", "电子文件导入", true), SPECIAL_PRINT_ZWYJG("SPECIAL_PRINT_ZWYJG", "特殊打印流程", true), SPECIAL_BURN_ZWYJG(
			"SPECIAL_BURN_ZWYJG", "特殊刻录流程", true), MERGE_ENTITY("MERGE_ENTITY", "文件台账合并", true);

	private final String code;
	private final String name;
	private boolean is_used;
	private static Logger logger = Logger.getLogger(JobTypeEnum.class);

	private JobTypeEnum(String code, String name, boolean is_used) {
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
		case BURN_REMAIN:
			return "event_burn";
		case BURN_SEND:
			return "event_burn";
		case BURN_FILE:
			return "event_burn";
		case SPECIAL_BURN_ZWYJG:
			return "event_burn";
		case PRINT_REMAIN:
			return "event_print";
		case PRINT_SEND:
			return "event_print";
		case PRINT_FILE:
			return "event_print";
		case SPECIAL_PRINT_ZWYJG:
			return "event_print";
		case COPY:
			return "event_copy";
		case COPY_SEND:
			return "event_copy";
		case COPY_FILE:
			return "event_copy";
		case COPY_MERGE:
			return "event_copy";
		case OUTCOPY_REMAIN:
			return "event_copy";
		case OUTCOPY_SEND:
			return "event_copy";
		case OUTCOPY_FILE:
			return "event_copy";
		case LEADIN:
			return "event_import";
		case DEVICE:
			return "event_device";
		case TRANSFER:
			return "event_transfer";
		case BORROW:
			return "event_borrow";
		case CHANGE:
			return "event_change";
		case SCAN_LEADIN:
			return "event_import";
		case SENDES_CD:
			return "event_senddestroy";
		case SENDES_PAPER:
			return "event_senddestroy";
		case MODIFY_SECLV:
			return "event_modify";
		case SPECIAL_PRINT:
			return "event_specialprint";
		case USERSECLV_ADD:
			return "event_userseclv_change";
		case USERSECLV_CHANGE:
			return "event_userseclv_change";
		case RESIGN:
			return "event_resign";
		case USERSEC_ACTIVITY:
			return "event_activities";
		case SECUSER_ABROAD:
			return "event_abroad_personal";
		case SECUSER_ENTRUST:
			return "event_entrust";
		case OUT_EXCHANGE:
			return "event_outexchange";
		case ENTER_SECPLACE:
			return "event_io_secplace";
		case EVENT_SECPLACE:
			return "event_secplace";
		case USER_INFO:
			return "event_userinfo";
		case EVENT_INTCOM:
			return "event_changedevice";
		case EVENT_SINCOM:
			return "event_changedevice";
		case EVENT_CHGCOM:
			return "event_changedevice";
		case EVENT_DESCOM:
			return "event_changedevice";
		case INFO_DEVICE:
			return "event_newdevice";
		case DEVICE_CHANGE:
			return "event_newdevice";
		case DEVICE_DES:
			return "event_newdevice";
		case INTER_EMAIL:
			return "event_email";
		case FIELDIN:
			return "event_researchfieldin";
		case SEC_CHECK:
			return "event_seccheck";
		case MATERIAL:
			return "event_material";
		case FILEOUTMAKE:
			return "event_fileoutmake";
		case EVENT_LOCALPRINTER:
			return "event_changedevice";
		case EXHIBITION:
			return "event_exhibition";
		case INFO_OTHER:
			return "event_newdevice";
		case CHANGE_OTHER:
			return "event_newdevice";
		case EVENT_REPORT:
			return "event_report";
		case EVENT_REPORT2:
			return "event_report";
		case EVENT_REPORT3:
			return "event_report";
		case EVENT_DEPTREPORT:
			return "event_report";
		case EVENT_INTRAPUBL:
			return "event_report";
		case EVENT_INTERPUBL:
			return "event_report";
		case EVENT_REPCOM:
			return "event_changedevice";
		case EVENT_REINSTALL:
			return "event_changedevice";
		case EVENT_QUITINT:
			return "event_changedevice";
		case EVENT_USBKEY:
			return "event_changedevice";
		case EVENT_OPENPORT:
			return "event_changedevice";
		case PUNISH_DEPT:
			return "event_punishrectify";
		case PUNISH_SECCHECK:
			return "event_punishrectify";
		case PUNISH_RECTIFY:
			return "event_punishrectify";
		case PAPER_RESEARCH:
			return "event_paperpatent";
		case PAPER_OTHERS:
			return "event_paperpatent";
		case PAPERPATENT:
			return "event_paperpatent";
		case BORROW_BOOK:
			return "event_borrowbook";
		case PERSONAL_FILE:
			return "event_carriermng";
		case BORROW_BOOKOUT:
			return "event_borrowbook";
		case BOOK_CHANGE:
			return "event_changedevice";
		case BOOK_REPAIR:
			return "event_changedevice";
		case BOOK_DES:
			return "event_changedevice";
		case BOOK_REINSTALL:
			return "event_changedevice";
		case QIYUAN_BORROW_BOOK:
			return "event_borrowbook";
		case FILE_DESTROY:
			return "event_temp";
		case FILECD_DESTROY:
			return "event_temp";
		case MSG_INPUT:
			return "event_input";
		}
		return null;
	}

	public static List<JobTypeEnum> getAllJobTypeList() {
		List<JobTypeEnum> list = new ArrayList<JobTypeEnum>();
		for (JobTypeEnum item : JobTypeEnum.values()) {
			list.add(item);
		}
		return list;
	}

	public static JobTypeEnum[] getAllJobType() {
		return JobTypeEnum.values();
	}

	public static List<JobTypeEnum> getUsedJobTypeList() {
		List<JobTypeEnum> list = new ArrayList<JobTypeEnum>();
		for (JobTypeEnum item : JobTypeEnum.values()) {
			if (item.isJobTypeUsed()) {
				list.add(item);
			}
		}
		return list;
	}
}
