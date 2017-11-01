package hdsec.web.project.user.action;

import hdsec.web.project.common.model.CopyrightLicense;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 登录action,目前只判断用户名和密码是否正确，后续会换成security的登录
 * 
 * @author renmingfei
 * 
 */
public class LoginAction extends ActionSupport {
	private Logger logger = Logger.getLogger(this.getClass());
	private static final long serialVersionUID = 1L;

	private String license;

	@Override
	public String execute() {
		if (!(CopyrightLicense.skipLicense || CopyrightLicense.registered)) {
			try {
				license = ServerValidation.getLicenseResult();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			return "submitActivationCode";
		}

		/*
		 * try { license = ServerValidation.getLicenseResult(); if ("1".equals(license)) { if
		 * ("0".equals(ServerValidation.hasPermissionFile())) { return "needUploadFile"; } } else { return
		 * "submitActivationCode"; } } catch (Exception e) { e.printStackTrace(); }
		 */
		return "error";

	}

	public String getLastLoginIp() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}

	public Timestamp getLastLoginTime() {
		return new Timestamp(System.currentTimeMillis());
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

}
