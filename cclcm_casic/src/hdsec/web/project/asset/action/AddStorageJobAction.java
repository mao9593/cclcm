package hdsec.web.project.asset.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.asset.model.PropertyKind;
import hdsec.web.project.asset.model.PurchaseEvent;
import hdsec.web.project.asset.model.StorageEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 添加资产入库流程任务申请
 * 
 * @author gaoximin 2015-7-28
 */
public class AddStorageJobAction extends AssetBaseAction {

	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String summ = "";// 备注
	private final String jobType = JobTypeEnum.STORE.getJobTypeCode();
	private PurchaseEvent event = null;

	private String event_code_purchase = ""; // 采购作业编号（外键）
	private String manage_dept_id = ""; // 资产管理部门ID
	private String manage_dept_name = ""; // 资产管理部门
	private String identity_code = "";// 识别码
	private String property_no = "";// 资产编号
	private String voucher_no = "";// 凭证号
	private String property_name = "";// 资产名称
	private Integer amount = null; // 数量
	private String property_standard = ""; // 资产规格
	private String property_type = "";// 资产型号
	private String supplier = "";// 国别厂家
	private String factory_no = "";// 出厂编号
	private Date factory_date = null;// 出厂日期
	private Date use_date = null;// 启用日期
	private String original_value = "";// 原值
	private String average_equity = "";// 净值率
	private String setup_place = "";// 安装地点

	private String next_approver = "";// 下级审批人

	public String getEvent_code_purchase() {
		return event_code_purchase;
	}

	public void setEvent_code_purchase(String event_code_purchase) {
		this.event_code_purchase = event_code_purchase;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getProject_code() {
		return project_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public String getUsage_code() {
		return usage_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public String getJobType() {
		return jobType;
	}

	public String getManage_dept_id() {
		return manage_dept_id;
	}

	public void setManage_dept_id(String manage_dept_id) {
		this.manage_dept_id = manage_dept_id;
	}

	public String getManage_dept_name() {
		return manage_dept_name;
	}

	public void setManage_dept_name(String manage_dept_name) {
		this.manage_dept_name = manage_dept_name;
	}

	public String getIdentity_code() {
		return identity_code;
	}

	public void setIdentity_code(String identity_code) {
		this.identity_code = identity_code;
	}

	public String getProperty_no() {
		return property_no;
	}

	public void setProperty_no(String property_no) {
		this.property_no = property_no;
	}

	public String getVoucher_no() {
		return voucher_no;
	}

	public void setVoucher_no(String voucher_no) {
		this.voucher_no = voucher_no;
	}

	public void setFactory_no(String factory_no) {
		this.factory_no = factory_no;
	}

	public Date getFactory_date() {
		return factory_date;
	}

	public void setFactory_date(Date factory_date) {
		this.factory_date = factory_date;
	}

	public Date getUse_date() {
		return use_date;
	}

	public void setUse_date(Date use_date) {
		this.use_date = use_date;
	}

	public String getOriginal_value() {
		return original_value;
	}

	public void setOriginal_value(String original_value) {
		this.original_value = original_value;
	}

	public String getAverage_equity() {
		return average_equity;
	}

	public void setAverage_equity(String average_equity) {
		this.average_equity = average_equity;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public String getSetup_place() {
		return setup_place;
	}

	public void setSetup_place(String setup_place) {
		this.setup_place = setup_place;
	}

	public void setProperty_name(String property_name) {
		this.property_name = property_name;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public void setProperty_standard(String property_standard) {
		this.property_standard = property_standard;
	}

	public void setProperty_type(String property_type) {
		this.property_type = property_type;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public PurchaseEvent getEvent() {
		return event;
	}

	// 获取所有密级
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code_purchase)) {
			// 查询采购信息
			event = assetService.getPurchaseEventByCode(event_code_purchase);
			if (StringUtils.hasLength(event_code)) {
				String user_iidd = getCurUser().getUser_iidd();
				String dept_id = getCurUser().getDept_id();
				String serial_number = "";
				PropertyKind kind = assetService.getSerialNoByKindId(event
						.getKind_id());
				if (kind != null) {
					if (kind.getSerial_no() < 10) {
						serial_number = "000" + kind.getSerial_no().toString();
					} else if (kind.getSerial_no() < 100) {
						serial_number = "00" + kind.getSerial_no().toString();
					} else if (kind.getSerial_no() < 1000) {
						serial_number = "0" + kind.getSerial_no().toString();
					} else if (kind.getSerial_no() < 10000) {
						serial_number = "" + kind.getSerial_no().toString();
					}
				}
				property_no = kind.getProperty_prefix() + serial_number;
				StorageEvent storageevent = new StorageEvent(user_iidd,
						dept_id, event_code, seclv_code, usage_code,
						project_code, summ, event_code_purchase, "",
						manage_dept_name, event.getUser_iidd(),
						event.getUser_name(), event.getDept_id(),
						event.getDept_name(), identity_code, property_no,
						voucher_no, event.getProperty_seclvcode(),
						event.getKind_id(), event.getProperty_kind(),
						property_name, amount, property_standard,
						property_type, supplier, factory_no, factory_date,
						use_date, original_value, average_equity, setup_place);
				storageevent.setJobType(JobTypeEnum.valueOf(jobType));

				assetService.addStorageEvent(storageevent, next_approver);
				insertCommonLog("添加资产入账申请作业[" + event_code + "]");
				return "ok";
			} else {
				event_code = getCurUser().getUser_iidd() + ""
						+ System.currentTimeMillis();
				return SUCCESS;
			}
		} else {
			throw new Exception("参数错误，无法获得资产采购基本信息");
		}
	}
}
