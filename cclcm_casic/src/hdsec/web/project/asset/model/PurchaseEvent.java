package hdsec.web.project.asset.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

import java.util.Date;

/**
 * 固定资产采购申请
 * 
 * @author gaoximin 2015-6-25
 */
public class PurchaseEvent extends BaseEvent {
	private String purchase_title = ""; // 工单标题
	private String purchase_code = "";// 工单编号
	private String telephone = ""; // 申请人联系电话
	private Integer property_seclvcode = null; // 资产密级
	private Integer kind_id = null; // 采购资产类型
	private String property_kind = ""; // 采购资产类型
	private String property_name = ""; // 资产名称
	private String property_standard = ""; // 资产规格
	private String supplier = ""; // 供应厂商
	private Integer amount = null; // 数量
	private String unit_price = ""; // 单价
	private String total_price = "";// 总价
	private String reason = "";// 采购原因
	private Integer store_status = 0; // 入库状态（0未入库，1已采购入库，2申请入库）
	private Date store_time = null;// 入库时间
	private String his_job_code = ""; // 包含该作业的历史任务列表
	private String property_type = ""; // 资产型号
	private String details = ""; // 主要技术指标和配套附件主要技术指标和配套附件
	private String budget_year = ""; // 年度预算指标（元）

	private String property_seclvcode_name = "";// 资产密级名称

	public String getPurchase_title() {
		return purchase_title;
	}

	public void setPurchase_title(String purchase_title) {
		this.purchase_title = purchase_title;
	}

	public String getPurchase_code() {
		return purchase_code;
	}

	public void setPurchase_code(String purchase_code) {
		this.purchase_code = purchase_code;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public String getProperty_standard() {
		return property_standard;
	}

	public void setProperty_standard(String property_standard) {
		this.property_standard = property_standard;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}

	public String getTotal_price() {
		return total_price;
	}

	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getStore_status() {
		return store_status;
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
		case 2:
			return "申请入账";
		default:
			return "未知";
		}
	}

	public void setStore_status(Integer store_status) {
		this.store_status = store_status;
	}

	public String getStore_time() {
		return store_time == null ? "" : getSdf().format(store_time);
	}

	public void setStore_time(Date store_time) {
		this.store_time = store_time;
	}

	public String getHis_job_code() {
		return his_job_code;
	}

	public void setHis_job_code(String his_job_code) {
		this.his_job_code = his_job_code;
	}

	public String getProperty_seclvcode_name() {
		return property_seclvcode_name;
	}

	public void setProperty_seclvcode_name(String property_seclvcode_name) {
		this.property_seclvcode_name = property_seclvcode_name;
	}

	public String getProperty_type() {
		return property_type;
	}

	public void setProperty_type(String property_type) {
		this.property_type = property_type;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getBudget_year() {
		return budget_year;
	}

	public void setBudget_year(String budget_year) {
		this.budget_year = budget_year;
	}

	public PurchaseEvent() {
		super(JobTypeEnum.PURCHASE);
	}

	public PurchaseEvent(String user_iidd, String dept_id, String event_code,
			Integer seclv_code, String usage_code, String project_code,
			String summ, String purchase_title, String purchase_code,
			String telephone, Integer property_seclvcode, String property_kind,
			String property_name, String property_standard, String supplier,
			Integer amount, String unit_price, String total_price,
			String reason, String property_type, String details,
			String budget_year, Integer kind_id) {
		super(JobTypeEnum.PURCHASE, event_code, user_iidd, dept_id, seclv_code,
				usage_code, project_code, summ);

		this.purchase_title = purchase_title;
		this.purchase_code = purchase_code;
		this.telephone = telephone;
		this.property_seclvcode = property_seclvcode;
		this.property_kind = property_kind;
		this.property_name = property_name;
		this.property_standard = property_standard;
		this.supplier = supplier;
		this.amount = amount;
		this.unit_price = unit_price;
		this.total_price = total_price;
		this.reason = reason;
		this.property_type = property_type;
		this.details = details;
		this.budget_year = budget_year;
		this.kind_id = kind_id;
	}

}
