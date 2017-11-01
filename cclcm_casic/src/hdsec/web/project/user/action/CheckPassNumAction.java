package hdsec.web.project.user.action;

import org.springframework.util.StringUtils;

/**
 * 检验用户卡号是否可用，并返回提示信息
 * @author lixiang
 *
 */
public class CheckPassNumAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String pass_num = "";
	private String chkResult = "";
	private String user_iidd="";
	
	public String getPass_num() {
		return pass_num;
	}

	public void setPass_num(String pass_num) {
		this.pass_num = pass_num;
	}

	public String getChkResult() {
		return chkResult;
	}
	
	public void setChkResult(String chkResult) {
		this.chkResult = chkResult;
	}
	
	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	@Override
	public String executeFunction() {
		//判断用户卡号是否为空
		if (pass_num.trim().equals("")) {
			setChkResult("blank");
			return SUCCESS;
		}
		//判断是否包含非法字符
		String[] chars = { "　", " ", "~", "!", "@", "$", "%", "^", "&", "*", "|", "\\", "/", "?", "'", "\"" };
		for (String item : chars) {
			if (pass_num.trim().indexOf(item) != -1) {
				setChkResult("syntaxErr");
				return SUCCESS;
			}
		}
		//判断是否已经使用
		try {
			if(StringUtils.hasLength(user_iidd)){//修改用户
				String used_user_iidd = userService.getSecUserIdByPassNum(pass_num.trim());
				if (StringUtils.hasLength(used_user_iidd) && !user_iidd.equals(used_user_iidd)) {
					setChkResult("used");
				} else {
					setChkResult("available");
				}
			}
			else{//添加用户
				boolean is_used = userService.checkPassNumIsUsed(pass_num.trim());
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
