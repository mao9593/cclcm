package hdsec.web.project.user.action;

import hdsec.web.project.common.model.CopyrightLicense;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 上传验证授权文件
 * 
 * @author yy
 * 
 */
public class SubmitActivatinCodeAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String register_code;
	private String activation_code;
	private String _submit = "N";
	private String result;

	@Override
	public String execute() throws Exception {
		if ("Y".equals(_submit)) {
			boolean passed = ServerValidation.verifyActivateCode(register_code, activation_code);
			if (passed) {
				CopyrightLicense.registered = true;
				/*
				 * if ("0".equals(ServerValidation.readLicenseFile())) { return "needUploadFile"; }
				 */
				return "ok";
			} else {
				setResult("激活码验证失败，请重新输入");
			}
		}
		return SUCCESS;
	}

	public String getRegister_code() {
		return register_code;
	}

	public void setRegister_code(String register_code) {
		this.register_code = register_code;
	}

	public String getActivation_code() {
		return activation_code;
	}

	public void setActivation_code(String activation_code) {
		this.activation_code = activation_code;
	}

	public void set_submit(String submit) {
		this._submit = submit;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
