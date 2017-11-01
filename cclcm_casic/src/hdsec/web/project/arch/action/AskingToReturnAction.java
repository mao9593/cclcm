package hdsec.web.project.arch.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AskingToReturnAction extends ArchBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String job_code;// 档案 的id
	private String user_iidd;// 用户user_iidd
	private String msg;

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String executeFunction() throws Exception {
		// 准备向client中插入的数据
		String user_name = userService.getUserNameByUserId(user_iidd);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accept_user_iidd", user_iidd);
		map.put("accept_user_name", user_name);
		map.put("oper_type", "ASKRETURN");
		map.put("message_type", 2);
		map.put("job_code", job_code);
		map.put("message", "来自档案管理员：您借阅的档案到期请及时归还！");
		map.put("insert_time", new Date());
		map.put("is_read", 0);
		archService.addArcAskingToReturnClientMsg(map);
		setMsg("ok");
		return SUCCESS;
	}

}
