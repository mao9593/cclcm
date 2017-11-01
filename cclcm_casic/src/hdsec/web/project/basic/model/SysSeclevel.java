package hdsec.web.project.basic.model;

import hdsec.web.project.user.model.SecLevel;

public class SysSeclevel extends SecLevel {
	private Integer leak_time = null;
	private Integer archive_time = null;
	private Integer allow_reprint = null;
	private String is_paper_audit = "";
	private String is_cd_audit = "";
	
	public Integer getLeak_time() {
		return leak_time;
	}
	
	public void setLeak_time(Integer leak_time) {
		this.leak_time = leak_time;
	}
	
	public Integer getArchive_time() {
		return archive_time;
	}
	
	public void setArchive_time(Integer archive_time) {
		this.archive_time = archive_time;
	}
	
	public Integer getAllow_reprint() {
		return allow_reprint;
	}
	
	public String getAllow_reprint_name() {
		switch (this.allow_reprint) {
			case 0:
				return "不允许补打";
			case 1:
				return "允许补打";
			case 2:
				return "需管理员参与";
			default:
				return "未知";
		}
	}
	
	public void setAllow_reprint(Integer allow_reprint) {
		this.allow_reprint = allow_reprint;
	}
	
	public String getIs_paper_audit() {
		return is_paper_audit;
	}

	public void setIs_paper_audit(String is_paper_audit) {
		this.is_paper_audit = is_paper_audit;
	}
	
	public String getIs_paper_audit_name() {
		if (null == is_paper_audit || "N".equals(is_paper_audit)) {
			return "不允许审计";
		} else {
			return "允许审计";
		}
	}
	public String getIs_cd_audit() {
		return is_cd_audit;
	}

	public void setIs_cd_audit(String is_cd_audit) {
		this.is_cd_audit = is_cd_audit;
	}

	public String getIs_cd_audit_name() {
		if (null == is_cd_audit || "N".equals(is_cd_audit)) {
			return "不允许审计";
		} else {
			return "允许审计";
		}
	}
	
	public SysSeclevel() {
		super();
	}
	
	public SysSeclevel(Integer seclv_code, Integer leak_time, Integer archive_time, Integer allow_reprint, String is_paper_audit, String is_cd_audit) {
		setSeclv_code(seclv_code);
		this.leak_time = leak_time;
		this.archive_time = archive_time;
		this.allow_reprint = allow_reprint;
		this.is_paper_audit = is_paper_audit;
		this.is_cd_audit = is_cd_audit;
	}
	
}
