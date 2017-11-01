package hdsec.web.project.securityuser.action;

import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.model.SecUserPost;
import hdsec.web.project.user.model.UserSecurity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 * 查看涉密人员总台账
 * 
 * @author guojiao
 */
public class ManageBmSecUserListAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private String user_id = "";
	private String base_name = "";
	private String dept_id = "";
	private String base_sex = "";
	private String security_code = "";
	private Integer user_statue = 1;// 默认展示在岗人员
	private Integer secret_statue = null;
	private List<SecUser> userTemp = null;
	private List<SecUser> user = null;
	private List<String> dept_ids = null;
	private List<SecDept> secAdminDeptList = null;
	private String paper_total = "";
	private String cd_total = "";
	private Integer all_total = null;
	private String user_name = "";
	private String dept_name = "";
	private String post_name = "";
	private String post_id = "";
	private List<String> depts = null;
	private String type = "";

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSecret_statue() {
		return secret_statue;
	}

	public void setSecret_statue(Integer secret_statue) {
		this.secret_statue = secret_statue;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getBase_name() {
		return base_name;
	}

	public String getDept_id() {
		return dept_id;
	}

	public String getBase_sex() {
		return base_sex;
	}

	public Integer getUser_statue() {
		return user_statue;
	}

	public void setUser_statue(Integer user_statue) {
		this.user_statue = user_statue;
	}

	public String getSecurity_code() {
		return security_code;
	}

	public void setSecurity_code(String security_code) {
		this.security_code = security_code;
	}

	public void setBase_sex(String base_sex) {
		this.base_sex = base_sex;
	}

	public void setBase_name(String base_name) {
		this.base_name = base_name;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public List<SecUser> getUserTemp() {
		return userTemp;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public List<UserSecurity> getSecurityList() {
		return userService.getSecurityList();
	}

	public List<SecDept> getSecAdminDeptList() {
		return secAdminDeptList;
	}

	public String getDept_ids() {
		String ret = "";
		for (String d_item : dept_ids) {
			ret += d_item + ",";
		}
		if (ret.endsWith(",")) {
			ret = ret.substring(0, ret.length() - 1);
		}
		return ret;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getPost_name() {
		return post_name;
	}

	public void setPost_name(String post_name) {
		this.post_name = post_name;
	}

	public String getPost_id() {
		return post_id;
	}

	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}

	public List<SecUserPost> getPostList() {
		return userService.getAllUserPostList();
	}

	// 前台部门选择框为多选，将多选id存到list类型中在数据库进行查询
	private void getAllDept(String dept) {
		depts = new ArrayList<String>();
		String temp[] = dept.split(",");
		for (int i = 0; i < temp.length; i++) {
			depts.add(temp[i]);
		}
	}

	@Override
	public String executeFunction() throws Exception {
		if (type.equals("DEPT")) {
			String web_url = getModuleName().toLowerCase() + "/" + getTitleName().toLowerCase() + ".action?type=DEPT";
			dept_ids = basicService.getAdminDeptIdList(getCurUser().getUser_iidd(), web_url);
			if (dept_ids == null || dept_ids.size() == 0) {
				throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
			}
			secAdminDeptList = basicService.getDeptAdminList(getCurUser().getUser_iidd(), web_url);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		userTemp = new ArrayList<SecUser>();
		map.put("user_iidd", user_id);
		map.put("user_name", base_name);
		if (!dept_id.equals("")) {
			getAllDept(dept_id);
			map.put("dept_id", depts);
		}
		/* map.put("base_sex", base_sex); */
		map.put("post_id", post_id);
		map.put("security_code", security_code);
		if (type.equals("DEPT")) {
			map.put("scope_dept_ids", dept_ids);
		}
		map.put("user_statue", user_statue);
		map.put("secret_statue", secret_statue);

		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = securityUserService.getBmSecUserSize(map);
		user = securityUserService.getBmSecUserList(map, rbs);

		for (SecUser userid : user) {
			// 查询该用户个人涉密载体总数
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("user_id", userid.getUser_iidd());
			paper_total = securityUserService.getUserEntityPaperTotal(map2);
			cd_total = securityUserService.getUserEntityCdTotal(map2);
			all_total = Integer.valueOf(paper_total).intValue() + Integer.valueOf(cd_total).intValue();
			userid.setExt_code(all_total.toString());
			userTemp.add(userid);
		}
		return SUCCESS;
	}
}
