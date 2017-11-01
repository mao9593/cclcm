package hdsec.web.project.basic.action;

import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 * 审计管理员设置重置密码
 * 
 * @author chenrongrong 2015-4-21
 */
public class AudresetUserpwdAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	@Resource
	private UserService userService;
	private List<SecUser> secUserList;
	private Integer admin_version = 0;
	private String display_flag = "SYS";

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<SecUser> getSecUserList() {
		return secUserList;
	}

	public void setSecUserList(List<SecUser> secUserList) {
		this.secUserList = secUserList;
	}

	public Integer getAdmin_version() {
		return admin_version;
	}

	public void setAdmin_version(Integer admin_version) {
		this.admin_version = admin_version;
	}

	public String getDisplay_flag() {
		return display_flag;
	}

	public void setDisplay_flag(String display_flag) {
		this.display_flag = display_flag;
	}

	@Override
	public String executeFunction() throws Exception {
		String pageIndexName = new ParamEncoder("user").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		Map<String, String> map = new HashMap<String, String>();
		map.put("role_name", "审计管理员");
		secUserList = userService.getAdminUser(map, rbs);
		// 判断当前三员用户管理权限划分版本（集团版本、保密局测评版本）
		admin_version = userService.getSysConfigAdminVersion();
		return SUCCESS;
	}
}
