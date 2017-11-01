package hdsec.web.project.burn.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

import java.util.Date;

/**
 * 邦辰关键字结果信息类
 * 
 * @author haojia
 * 
 */

public class RiskKeywordsBurn extends BaseEvent {
	private Integer cd_num = 1;// 刻录份数
	private Integer data_type = 0;// 数据类型（0：数据刻录；1：镜像刻录；2：音视频刻录
	private Integer file_num = 0;// 文件数量
	private String file_list = "";// 文件列表
	private String file_seclevel = "";// 文件密级列表
	private Integer burn_status = 0;// 刻录状态
	private Date burn_time = null;// 刻录时间
	private String cd_serial = "";// 光盘编号
	private Integer is_proxy = 0;// 是否代理刻录
	private String cycle_type;// 去向
	private String period = "S";// 短期/长期
	private String conf_code = "";// 保密编号
	private String model_num = "";// 型号
	private String post_num = "";// 发文号
	private String record_num = "";// 档号
	private String disk_name = "";// 信息名称
	private String base_mark = "";// 库标记
	private String step = "";// 阶段
	private String purpose = "";// 用途

	// 邦辰关键字结果信息相关字段
	private String policy = "";// 邦辰关键字检查结果：通过or不通过
	private String tid = "";
	private String riskclass = "";
	private String hitcount = "";
	private String levelname = "";
	private String sensitvecontents = "";
	private String filename = "";
	private String filetype = "";

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getRiskclass() {
		return riskclass;
	}

	public void setRiskclass(String riskclass) {
		this.riskclass = riskclass;
	}

	public String getHitcount() {
		return hitcount;
	}

	public void setHitcount(String hitcount) {
		this.hitcount = hitcount;
	}

	public String getLevelname() {
		return levelname;
	}

	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}

	public String getSensitvecontents() {
		return sensitvecontents;
	}

	public void setSensitvecontents(String sensitvecontents) {
		this.sensitvecontents = sensitvecontents;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getCycle_type_name() {
		if ("REMAIN".equals(cycle_type)) {
			return "留用";
		}
		if ("FILE".equals(cycle_type)) {
			return "归档";
		}
		if ("SEND".equals(cycle_type)) {
			return "外发";
		}
		return cycle_type;
	}

	public void setCycle_type(String cycle_type) {
		this.cycle_type = cycle_type;
	}

	public String getPeriod() {
		return period;
	}

	public String getPeriod_name() {
		if ("S".equals(period)) {
			return "短期";
		}
		if ("L".equals(period)) {
			return "长期";
		}
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getPolicy_name() {
		if (policy != null) {
			if ("pass".equals(policy)) {

				return "通过";
			} else {
				return "未通过";
			}
		} else {
			return "未通过";
		}
	}

	public Integer getCd_num() {
		return cd_num;
	}

	public void setCd_num(Integer cd_num) {
		this.cd_num = cd_num;
	}

	public Integer getData_type() {
		return data_type;
	}

	public void setData_type(Integer data_type) {
		this.data_type = data_type;
	}

	public Integer getFile_num() {
		return file_num;
	}

	public void setFile_num(Integer file_num) {
		this.file_num = file_num;
	}

	public String getFile_list() {
		return file_list;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public String getFile_seclevel() {
		return file_seclevel;
	}

	public void setFile_seclevel(String file_seclevel) {
		this.file_seclevel = file_seclevel;
	}

	public Integer getBurn_status() {
		return burn_status;
	}

	public void setBurn_status(Integer burn_status) {
		this.burn_status = burn_status;
	}

	public Date getBurn_time() {
		return burn_time;
	}

	public void setBurn_time(Date burn_time) {
		this.burn_time = burn_time;
	}

	public String getCd_serial() {
		return cd_serial;
	}

	public void setCd_serial(String cd_serial) {
		this.cd_serial = cd_serial;
	}

	public Integer getIs_proxy() {
		return is_proxy;
	}

	public void setIs_proxy(Integer is_proxy) {
		this.is_proxy = is_proxy;
	}

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
	}

	public String getModel_num() {
		return model_num;
	}

	public void setModel_num(String model_num) {
		this.model_num = model_num;
	}

	public String getPost_num() {
		return post_num;
	}

	public void setPost_num(String post_num) {
		this.post_num = post_num;
	}

	public String getRecord_num() {
		return record_num;
	}

	public void setRecord_num(String record_num) {
		this.record_num = record_num;
	}

	public String getDisk_name() {
		return disk_name;
	}

	public void setDisk_name(String disk_name) {
		this.disk_name = disk_name;
	}

	public String getBase_mark() {
		return base_mark;
	}

	public void setBase_mark(String base_mark) {
		this.base_mark = base_mark;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getPolicy() {
		return policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public String getCycle_type() {
		return cycle_type;
	}

	public RiskKeywordsBurn() {
		super(JobTypeEnum.BURN_REMAIN);
	}

	public RiskKeywordsBurn(String tid, String riskclass, String hitcount, String levelname, String sensitvecontents,
			String filename, String filetype) {
		super();
		this.tid = tid;
		this.riskclass = riskclass;
		this.hitcount = hitcount;
		this.levelname = levelname;
		this.sensitvecontents = sensitvecontents;
		this.filename = filename;
		this.filetype = filetype;
	}

}
