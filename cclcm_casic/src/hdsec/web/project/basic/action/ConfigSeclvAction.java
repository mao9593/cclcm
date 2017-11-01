package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysSeclevel;
import hdsec.web.project.user.model.SecLevel;

/**
 * 配置密级信息
 * 
 * @author renmingfei
 * 
 */
public class ConfigSeclvAction extends BasicBaseAction {

	private static final long serialVersionUID = 1L;
	private Integer seclv_code = null;
	private String config = "N";
	private SysSeclevel seclv = null;
	private Integer leak_time = 20;
	private Integer archive_time = null;
	private Integer allow_reprint = null;
	private String is_paper_audit = "";
	private String is_cd_audit = "";
	private String done = "N";

	public void setConfig(String config) {
		this.config = config;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public SecLevel getSeclv() {
		return seclv;
	}

	public void setLeak_time(Integer leak_time) {
		this.leak_time = leak_time;
	}

	public void setArchive_time(Integer archive_time) {
		this.archive_time = archive_time;
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

	public String getIs_cd_audit() {
		return is_cd_audit;
	}

	public void setIs_cd_audit(String is_cd_audit) {
		this.is_cd_audit = is_cd_audit;
	}

	public String getDone() {
		return done;
	}

	public void setDone(String done) {
		this.done = done;
	}

	@Override
	public String executeFunction() throws Exception {
		if (seclv_code != null) {
			if (config.equalsIgnoreCase("Y")) {
				seclv = new SysSeclevel(seclv_code, leak_time, archive_time, allow_reprint, is_paper_audit, is_cd_audit);
				basicService.configSeclv(seclv);
				setDone("Y");
				insertAdminLog("配置密级信息:最长解密[" + leak_time + "]年，回收时间[" + archive_time + "]天，补打模式["
						+ seclv.getAllow_reprint_name() + "]，审计打印文件权限[" + seclv.getIs_paper_audit_name()
						+ "]，审计刻录文件权限[" + seclv.getIs_cd_audit_name() + "]");
			}
			seclv = basicService.getSysSecLevelByCode(seclv_code);
		} else {
			throw new Exception("缺失参数[密级编号]");
		}
		return SUCCESS;
	}
}
