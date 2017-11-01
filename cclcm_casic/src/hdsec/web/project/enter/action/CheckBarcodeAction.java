package hdsec.web.project.enter.action;

import org.springframework.util.StringUtils;

/**
 * 检验预填条码是否可用，并返回提示信息
 * @author lixiang
 *
 */
public class CheckBarcodeAction extends EnterBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String barcode = "";
	private String chkResult = "";
	private String file_type;
	private String update = "";
	private String event_code = "";
	
	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode.trim();
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public String getChkResult() {
		return chkResult;
	}
	
	public void setChkResult(String chkResult) {
		this.chkResult = chkResult;
	}
	
	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	@Override
	public String executeFunction() {
		//判断登录ID是否为空
		if (barcode.trim().equals("")) {
			setChkResult("blank");
			return SUCCESS;
		}
		//判断是否包含非法字符
		String[] chars = { "　", " ", "~", "!", "@", "$", "%", "^", "&", "*", "|", "\\", "/", "?", "'", "\"" };
		for (String item : chars) {
			if (barcode.trim().indexOf(item) != -1) {
				setChkResult("syntaxErr");
				return SUCCESS;
			}
		}
		//判断是否已经使用
		try {
			if(StringUtils.hasLength(update)){//修改申请
				boolean is_used = enterService.checkBarcodeIsUsedByOthers(Integer.valueOf(file_type),barcode,event_code);
				if (is_used) {
					setChkResult("used");
				} else {
					setChkResult("available");
				}
			}else{//添加申请
				boolean is_used = enterService.checkBarcodeIsUsed(Integer.valueOf(file_type),barcode);
				if (is_used) {
					setChkResult("used");
				} else {
					setChkResult("available");
				}
			}
		} catch (Exception e) {
			dealException(e);
			setChkResult("exception");
		}
		return SUCCESS;
	}
}
