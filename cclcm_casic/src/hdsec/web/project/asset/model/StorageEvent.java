package hdsec.web.project.asset.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

import java.util.Date;

/**
 * 入库申请
 * 
 * @author gaoximin 2015-7-28
 */
public class StorageEvent extends BaseEvent {
	private String event_code_purchase = ""; // 采购作业编号（外键）
	private String manage_dept_id = ""; // 资产管理部门ID
	private String manage_dept_name = ""; // 资产管理部门
	private String duty_user_iidd = "";// 使用人ID
	private String duty_user_name = "";// 使用人
	private String duty_dept_id = "";// 使用部门
	private String duty_dept_name = "";// 使用部门ID
	private String identity_code = "";// 识别码
	private String property_no = "";// 资产编号
	private String voucher_no = "";// 凭证号
	private Integer property_seclvcode = null;// 资产密级
	private Integer kind_id = null;// 资产类别ID
	private String property_kind = "";// 资产类别
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
	private Integer store_status = null;// 入库状态（0未入库，1已入库）
	private Date store_time = null;// 入库时间
	private String setup_place = "";// 资产编号

	private String property_seclvcode_name = "";// 资产密级名称

	public String getEvent_code_purchase() {
		return event_code_purchase;
	}

	public void setEvent_code_purchase(String event_code_purchase) {
		this.event_code_purchase = event_code_purchase;
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

	public String getDuty_user_iidd() {
		return duty_user_iidd;
	}

	public void setDuty_user_iidd(String duty_user_iidd) {
		this.duty_user_iidd = duty_user_iidd;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public String getDuty_dept_name() {
		return duty_dept_name;
	}

	public void setDuty_dept_name(String duty_dept_name) {
		this.duty_dept_name = duty_dept_name;
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

	public Integer getProperty_seclvcode() {
		return property_seclvcode;
	}

	public void setProperty_seclvcode(Integer property_seclvcode) {
		this.property_seclvcode = property_seclvcode;
	}

	public Integer getKind_id() {
		return kind_id;
	}

	public void setKind_id(Integer kind_id) {
		this.kind_id = kind_id;
	}

	public String getProperty_kind() {
		return property_kind;
	}

	public void setProperty_kind(String property_kind) {
		this.property_kind = property_kind;
	}

	public String getProperty_name() {
		return property_name;
	}

	public void setProperty_name(String property_name) {
		this.property_name = property_name;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getProperty_standard() {
		return property_standard;
	}

	public void setProperty_standard(String property_standard) {
		this.property_standard = property_standard;
	}

	public String getProperty_type() {
		return property_type;
	}

	public void setProperty_type(String property_type) {
		this.property_type = property_type;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getFactory_no() {
		return factory_no;
	}

	public void setFactory_no(String factory_no) {
		this.factory_no = factory_no;
	}

	public String getFactory_date_str() {
		return factory_date == null ? "" : getSdf().format(factory_date);
	}

	public void setFactory_date(Date factory_date) {
		this.factory_date = factory_date;
	}

	public String getUse_date_str() {
		return use_date == null ? "" : getSdf().format(use_date);
	}

	public Date getFactory_date() {
		return factory_date;
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

	public Integer getStore_status() {
		return store_status;
	}

	public void setStore_status(Integer store_status) {
		this.store_status = store_status;
	}

	public String getStore_status_str() {
		if (store_status == null) {
			return "未知";
		}
		switch (store_status) {
		case 0:
			return "未入账";
		case 1:
			return "已入账";
		default:
			return "未知";
		}
	}

	public String getStore_time() {
		return store_time == null ? "" : getSdf().format(store_time);
	}

	public void setStore_time(Date store_time) {
		this.store_time = store_time;
	}

	public String getSetup_place() {
		return setup_place;
	}

	public void setSetup_place(String setup_place) {
		this.setup_place = setup_place;
	}

	public String getProperty_seclvcode_name() {
		return property_seclvcode_name;
	}

	public void setProperty_seclvcode_name(String property_seclvcode_name) {
		this.property_seclvcode_name = property_seclvcode_name;
	}

	public StorageEvent() {
		super(JobTypeEnum.STORE);
	}

	public StorageEvent(String user_iidd, String dept_id, String event_code,
			Integer seclv_code, String usage_code, String project_code,
			String summ, String event_code_purchase, String manage_dept_id,
			String manage_dept_name, String duty_user_iidd,
			String duty_user_name, String duty_dept_id, String duty_dept_name,
			String identity_code, String property_no, String voucher_no,
			Integer property_seclvcode, Integer kind_id, String property_kind,
			String property_name, Integer amount, String property_standard,
			String property_type, String supplier, String factory_no,
			Date factory_date, Date use_date, String original_value,
			String average_equity, String setup_place) {
		super(JobTypeEnum.STORE, event_code, user_iidd, dept_id, seclv_code,
				usage_code, project_code, summ);

		this.event_code_purchase = event_code_purchase;
		this.manage_dept_id = manage_dept_id;
		this.manage_dept_name = manage_dept_name;
		this.duty_user_iidd = duty_user_iidd;
		this.duty_user_name = duty_user_name;
		this.duty_dept_id = duty_dept_id;
		this.duty_dept_name = duty_dept_name;
		this.identity_code = identity_code;
		this.property_no = property_no;
		this.voucher_no = voucher_no;
		this.property_seclvcode = property_seclvcode;
		this.kind_id = kind_id;
		this.property_kind = property_kind;
		this.property_name = property_name;
		this.amount = amount;
		this.property_standard = property_standard;
		this.property_type = property_type;
		this.supplier = supplier;
		this.factory_no = factory_no;
		this.factory_date = factory_date;
		this.use_date = use_date;
		this.original_value = original_value;
		this.average_equity = average_equity;
		this.setup_place = setup_place;
	}

}
