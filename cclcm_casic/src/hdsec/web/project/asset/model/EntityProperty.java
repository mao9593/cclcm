package hdsec.web.project.asset.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.util.ActivitiCons;
import hdsec.web.project.common.BaseDomain;

import java.util.Date;

/**
 * 固定资产台账
 * 
 * @author gaoximin 2015-6-27
 */
public class EntityProperty extends BaseDomain {
	private Integer id = null;// id
	private String property_barcode = ""; // 资产条码
	private String event_code = "";// 作业ID(外键)
	private String user_iidd = "";// 采购申请人ID
	private String user_name = "";// 采购人
	private String dept_id = "";// 采购部门ID
	private String dept_name = "";// 采购部门
	private String duty_user_iidd = "";// 责任人ID
	private String duty_user_name = "";// 责任人姓名
	private String duty_dept_id = "";// 责任人部门名称
	private String duty_dept_name = "";// 责任人部门名称
	private Integer seclv_code = null;// 资产密级
	private String property_kind = ""; // 采购资产种类
	private String property_name = ""; // 资产名称
	private String property_standard = ""; // 资产规格
	private String unit_price = ""; // 单价

	private Integer property_status = 0;// 资产状态（0在用，1申请出库，2出库，3申请报废，4已报废，5申请入库，6申请变更）
	private Date in_time = null; // 入库时间
	private Date out_time = null;// 出库时间
	private Date waste_time = null;// 报废时间
	private String job_code = ""; // 外键-所属任务
	private JobTypeEnum jobType = null;

	private String identity_code = "";// 识别码
	private String property_no = "";// 资产编号
	private String voucher_no = ""; // 凭证号
	private String property_type = "";// 资产型号
	private String factory_no = "";// 出厂编号
	private Date factory_date = null; // 出厂日期
	private Date use_date = null;// 启用日期
	private String setup_place = "";// 安装地点
	private String original_value = ""; // 原值
	private String average_equity = ""; // 净值
	private String supplier = "";// 国别厂家
	private String summ = ""; // 备注

	private String netvalue = ""; // 净值率

	public String getSetup_place() {
		return setup_place;
	}

	public void setSetup_place(String setup_place) {
		this.setup_place = setup_place;
	}

	private String seclv_name = "";
	private String duty_user_name_links = "";
	private String duty_dept_name_link = "";
	private String job_status = "";

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}

	public String getProperty_barcode() {
		return property_barcode;
	}

	public void setProperty_barcode(String property_barcode) {
		this.property_barcode = property_barcode;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
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

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
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

	public String getProperty_type() {
		return property_type;
	}

	public void setProperty_type(String property_type) {
		this.property_type = property_type;
	}

	public String getFactory_no() {
		return factory_no;
	}

	public void setFactory_no(String factory_no) {
		this.factory_no = factory_no;
	}

	// public String getBurn_time() {
	// if (null == burn_time) {
	// return " ";
	// }
	// return getSdf().format(burn_time);
	// }
	//
	// public void setBurn_time(Date burn_time) {
	// this.burn_time = burn_time;
	// }
	public String getFactory_date() {
		if (null == factory_date) {
			return " ";
		}
		return getSdf().format(factory_date);
	}

	public void setFactory_date(Date factory_date) {
		this.factory_date = factory_date;
	}

	public String getUse_date() {
		if (null == use_date) {
			return " ";
		}
		return getSdf().format(use_date);
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

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
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

	public String getUnit_Price() {
		return unit_price;
	}

	public void setUnit_Price(String unit_price) {
		this.unit_price = unit_price;
	}

	public Integer getProperty_status() {
		return property_status;
	}

	public String getProperty_status_str() {
		if (property_status == null) {
			return "未知";
		}
		switch (property_status) {
		case 0:
			return "在用";
		case 1:
			return "申请出库";
		case 2:
			return "已出库";
		case 3:
			return "申请报废";
		case 4:
			return "已报废";
		case 5:
			return "申请入库";
		case 6:
			return "申请变更";
		default:
			return "未知";
		}
	}

	public void setProperty_status(Integer property_status) {
		this.property_status = property_status;
	}

	public String getIn_time() {
		return in_time == null ? "" : getSdf().format(in_time);
	}

	public void setIn_time(Date in_time) {
		this.in_time = in_time;
	}

	public String getOut_time() {
		return out_time == null ? "" : getSdf().format(out_time);
	}

	public void setOut_time(Date out_time) {
		this.out_time = out_time;
	}

	public String getWaste_time() {
		return waste_time == null ? "" : getSdf().format(waste_time);
	}

	public void setWaste_time(Date waste_time) {
		this.waste_time = waste_time;
	}

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public void setJobType(JobTypeEnum jobType) {
		this.jobType = jobType;
	}

	public String getSeclv_name() {
		return seclv_name;
	}

	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name;
	}

	public String getDuty_user_name_links() {
		return duty_user_name_links;
	}

	public String getDuty_dept_name_link() {
		return duty_dept_name_link;
	}

	public String getJob_status() {
		return job_status;
	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public String getJob_status_name() {
		String name = "";
		switch (this.job_status) {
		case ActivitiCons.APPLY_APPROVED_DEFAULT:
			name = "待审批";
			break;
		case ActivitiCons.APPLY_APPROVED_UNDER:
			name = "审批中";
			break;
		case ActivitiCons.APPLY_APPROVED_PASS:
			name = "已通过";
			break;
		case ActivitiCons.APPLY_APPROVED_REJECT:
			name = "已驳回";
			break;
		case ActivitiCons.APPLY_APPROVED_END:
			name = "已关闭";
			break;
		default:
			name = "未申请";
			break;
		}
		return name;
	}

	public String getNetvalue() {
		return netvalue;
	}

	public void setNetvalue(String netvalue) {
		this.netvalue = netvalue;
	}

	public EntityProperty() {
		super();
	}

	public EntityProperty(String property_barcode, String event_code,
			String user_iidd, String user_name, String dept_id,
			String dept_name, String duty_user_iidd, String duty_user_name,
			String duty_dept_id, String duty_dept_name, Integer seclv_code,
			String property_kind, String property_name,
			String property_standard, String supplier, String unit_price,
			Date in_time, String identity_code, String property_no,
			String voucher_no, String property_type, String factory_no,
			Date factory_date, Date use_date, String setup_place,
			String original_value, String average_equity, String summ) {
		super();
		this.property_barcode = property_barcode;
		this.event_code = event_code;
		this.user_iidd = user_iidd;
		this.user_name = user_name;
		this.dept_id = dept_id;
		this.dept_name = dept_name;
		this.duty_user_iidd = duty_user_iidd;
		this.duty_user_name = duty_user_name;
		this.duty_dept_id = duty_dept_id;
		this.duty_dept_name = duty_dept_name;
		this.seclv_code = seclv_code;
		this.property_kind = property_kind;
		this.property_name = property_name;
		this.property_standard = property_standard;
		this.supplier = supplier;
		this.unit_price = unit_price;
		this.in_time = in_time;
		this.identity_code = identity_code;
		this.property_no = property_no;
		this.voucher_no = voucher_no;
		this.property_type = property_type;
		this.factory_no = factory_no;
		this.factory_date = factory_date;
		this.use_date = use_date;
		this.setup_place = setup_place;
		this.original_value = original_value;
		this.average_equity = average_equity;
		this.summ = summ;
	}

}
