package hdsec.web.project.user.action;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.MD5;
import hdsec.web.project.user.model.Post;
import hdsec.web.project.user.model.SecRole;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.model.UserSecurity;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 添加安全用户
 * 
 * @author renmingfei
 * 
 */
public class AddSecUserAction extends UserBaseAction {

	private static final long serialVersionUID = 1L;
	private String dept_id = "";
	private String user_iidd = "";
	private String user_name = "";
	private String security_code = "";
	private String idCard = "";
	private String pass_num = "";
	private String print_method = "0";// 是否独立模式
	private String print_permission = "0";// 可否本地打印
	private Integer rank = null; // 排序值
	private String user_ppww = "";
	private String post_id = null;
	private String dept_name = "";
	private String oper = "";
	private String todo = "";
	private String role_id_list = "";// 配置的角色ID列表
	private String real_user_id = "";// 用户真实信息ID
	private String base_username = "";// 姓名
	private String base_sex = "";// 性别
	private String base_nation = "";// 民族
	private Date base_birthday = null;// 出生日期
	private String base_birthplace = "";// 出生地
	private String base_nativeplace = "";// 籍贯
	private String base_country = "";// 国别
	private String base_politics = "";// 政治面貌
	private Date base_joinpartytime = null;// 入党时间
	private String edu_education = "";// 学历
	private String edu_degree = "";// 学位
	private String edu_school = "";// 毕业院校
	private String edu_major = "";// 专业
	private String edu_language = "";// 掌握语言
	private String edu_familiarity = "";// 熟悉程度
	private String com_residency = "";// 户籍所在地
	private String com_police = "";// 户籍派出所
	private String com_address = "";// 现住址
	private String com_telephone = "";// 联系电话
	private String com_mobile = "";// 手机号码
	private String com_email = "";// 电子邮箱
	private String job_category = "";// 涉密人员类别
	private String job_seclevel = "";// 岗位密级
	private String job_adminpost = "";// 行政职务
	private String job_techpost = "";// 技术职务
	private String job_techtitle = "";// 技术职称
	private String job_humansort = "";// 人员类别
	private Date job_insectime = null;// 进入涉密岗位时间
	private Integer job_workyears = null;// 涉密工作年限
	private String job_employtype = "";// 聘用形式
	private String job_passnum = "";// 出入证号
	private String job_passlevel = "";// 出入证级别
	private Date job_inposttime = null;// 入岗时间
	private Date job_offposttime = null;// 离岗时间
	private String job_resume = "";// 个人简历
	private List<SecRole> allRoleList = null;// 普通角色列表
	private String item_key = "DEFAULT_PASSWORD";// 默认密码
	private Integer admin_version = 0;

	public void setRole_id_list(String role_id_list) {
		this.role_id_list = role_id_list;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public void setSecurity_code(String security_code) {
		this.security_code = security_code;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public void setPass_num(String pass_num) {
		this.pass_num = pass_num;
	}

	public void setPrint_method(String print_method) {
		if (StringUtils.hasLength(print_method) && print_method.equals("1")) {
			this.print_method = print_method;
		} else {
			this.print_method = "0";
		}
	}

	public void setPrint_permission(String print_permission) {
		if (StringUtils.hasLength(print_permission) && print_permission.equals("1")) {
			this.print_permission = print_permission;
		} else {
			this.print_permission = "0";
		}
	}

	public void setUser_ppww(String user_ppww) {
		this.user_ppww = user_ppww;
	}

	public void setReal_user_id(String real_user_id) {
		this.real_user_id = real_user_id;
	}

	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}

	public void setBase_username(String base_username) {
		this.base_username = base_username;
	}

	public void setBase_sex(String base_sex) {
		this.base_sex = base_sex;
	}

	public void setBase_nation(String base_nation) {
		this.base_nation = base_nation;
	}

	public void setBase_birthday(Date base_birthday) {
		this.base_birthday = base_birthday;
	}

	public void setBase_birthplace(String base_birthplace) {
		this.base_birthplace = base_birthplace;
	}

	public void setBase_nativeplace(String base_nativeplace) {
		this.base_nativeplace = base_nativeplace;
	}

	public void setBase_country(String base_country) {
		this.base_country = base_country;
	}

	public void setBase_politics(String base_politics) {
		this.base_politics = base_politics;
	}

	public void setBase_joinpartytime(Date base_joinpartytime) {
		this.base_joinpartytime = base_joinpartytime;
	}

	public void setEdu_education(String edu_education) {
		this.edu_education = edu_education;
	}

	public void setEdu_degree(String edu_degree) {
		this.edu_degree = edu_degree;
	}

	public void setEdu_school(String edu_school) {
		this.edu_school = edu_school;
	}

	public void setEdu_major(String edu_major) {
		this.edu_major = edu_major;
	}

	public void setEdu_language(String edu_language) {
		this.edu_language = edu_language;
	}

	public void setEdu_familiarity(String edu_familiarity) {
		this.edu_familiarity = edu_familiarity;
	}

	public void setCom_residency(String com_residency) {
		this.com_residency = com_residency;
	}

	public void setCom_police(String com_police) {
		this.com_police = com_police;
	}

	public void setCom_address(String com_address) {
		this.com_address = com_address;
	}

	public void setCom_telephone(String com_telephone) {
		this.com_telephone = com_telephone;
	}

	public void setCom_mobile(String com_mobile) {
		this.com_mobile = com_mobile;
	}

	public void setCom_email(String com_email) {
		this.com_email = com_email;
	}

	public void setJob_category(String job_category) {
		this.job_category = job_category;
	}

	public void setJob_seclevel(String job_seclevel) {
		this.job_seclevel = job_seclevel;
	}

	public void setJob_adminpost(String job_adminpost) {
		this.job_adminpost = job_adminpost;
	}

	public void setJob_techpost(String job_techpost) {
		this.job_techpost = job_techpost;
	}

	public void setJob_techtitle(String job_techtitle) {
		this.job_techtitle = job_techtitle;
	}

	public void setJob_humansort(String job_humansort) {
		this.job_humansort = job_humansort;
	}

	public void setJob_insectime(Date job_insectime) {
		this.job_insectime = job_insectime;
	}

	public void setJob_workyears(Integer job_workyears) {
		this.job_workyears = job_workyears;
	}

	public void setJob_employtype(String job_employtype) {
		this.job_employtype = job_employtype;
	}

	public void setJob_passnum(String job_passnum) {
		this.job_passnum = job_passnum;
	}

	public void setJob_passlevel(String job_passlevel) {
		this.job_passlevel = job_passlevel;
	}

	public void setJob_inposttime(Date job_inposttime) {
		this.job_inposttime = job_inposttime;
	}

	public void setJob_offposttime(Date job_offposttime) {
		this.job_offposttime = job_offposttime;
	}

	public void setJob_resume(String job_resume) {
		this.job_resume = job_resume;
	}

	public List<Post> getPostList() {
		return userService.getPostByDeptAsCon(this.dept_id);
	}

	public List<UserSecurity> getSecurityList() {
		return userService.getSecurityList();
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}

	public List<SecRole> getAllRoleList() {
		return allRoleList;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getItem_key() {
		return item_key;
	}

	public void setItem_key(String item_key) {
		this.item_key = item_key;
	}

	@Override
	public String executeFunction() throws Exception {
		// 判断当前三员用户管理权限划分版本（集团版本、保密局测评版本）
		admin_version = userService.getSysConfigAdminVersion();
		if (oper.equals("insert")) {// 添加用户
			// 登录名查重
			SecUser user = userService.getSecUserByUid(user_iidd.trim());
			if (user != null) {
				throw new Exception("登陆名称已使用,请更换！");
			}
			// 卡号查重
			if (userService.checkPassNumIsUsed(pass_num.trim())) {
				throw new Exception("用户卡号已存在,请更换！");
			}
			if (!StringUtils.hasLength(user_ppww.trim())) {
				// setUser_ppww("12345678a@");
				String defaultPassword = userService.getSysConfigItemValue(item_key);
				setUser_ppww(defaultPassword);
			}
			user_ppww = MD5.getMD5Str(user_ppww);
			SecUser secUser = new SecUser();
			secUser.setUser_iidd(user_iidd);
			secUser.setUser_ppww(user_ppww);
			secUser.setUser_name(user_name);
			secUser.setSecurity_code(security_code);
			secUser.setIdCard(idCard);
			secUser.setPass_num(pass_num);
			secUser.setPrint_method(Integer.parseInt(print_method));
			secUser.setPrint_permission(Integer.parseInt(print_permission));
			secUser.setDept_id(dept_id);
			secUser.setPost_id(post_id);
			secUser.setRank(rank); // 设置排序值
			// real_blank为true表示实际用户信息是在已有人员中选择的
			boolean real_blank = StringUtils.hasLength(real_user_id);
			if (real_blank) {
				secUser.setReal_user_id(real_user_id);
			} else {
				secUser.setReal_user_id(user_iidd);
				secUser.setBase_username(base_username);
				secUser.setBase_sex(base_sex);
				secUser.setBase_nation(base_nation);
				secUser.setBase_birthday(base_birthday);
				secUser.setBase_birthplace(base_birthplace);
				secUser.setBase_nativeplace(base_nativeplace);
				secUser.setBase_country(base_country);
				secUser.setBase_politics(base_politics);
				secUser.setBase_joinpartytime(base_joinpartytime);
				secUser.setEdu_education(edu_education);
				secUser.setEdu_degree(edu_degree);
				secUser.setEdu_school(edu_school);
				secUser.setEdu_major(edu_major);
				secUser.setEdu_language(edu_language);
				secUser.setEdu_familiarity(edu_familiarity);
				secUser.setCom_residency(com_residency);
				secUser.setCom_police(com_police);
				secUser.setCom_address(com_address);
				secUser.setCom_telephone(com_telephone);
				secUser.setCom_mobile(com_mobile);
				secUser.setCom_email(com_email);
				secUser.setJob_category(job_category);
				secUser.setJob_seclevel(job_seclevel);
				secUser.setJob_adminpost(job_adminpost);
				secUser.setJob_techpost(job_techpost);
				secUser.setJob_techtitle(job_techtitle);
				secUser.setJob_humansort(job_humansort);
				secUser.setJob_insectime(job_insectime);
				secUser.setJob_workyears(job_workyears);
				secUser.setJob_employtype(job_employtype);
				secUser.setJob_passnum(job_passnum);
				secUser.setJob_passlevel(job_passlevel);
				secUser.setJob_inposttime(job_inposttime);
				secUser.setJob_offposttime(job_offposttime);
				secUser.setJob_resume(job_resume);

			}
			userService.addSecUser(secUser, real_blank, role_id_list);
			// userService.updateUserRole(user_iidd, "admin", role_id_list, Constants.ROLE_TYPE_COMMON);
			insertAdminLog("添加用户:" + user_name);
			if (todo.equalsIgnoreCase("add")) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("subsys_code", "admin");
				map.put("role_type", String.valueOf(Constants.ROLE_TYPE_COMMON));
				allRoleList = userService.getSecRoleBySubsys(map);
				return SUCCESS;
			} else {
				return "return";
			}
		} else {
			dept_name = userService.getDeptNameByDeptId(dept_id);
			Map<String, String> map = new HashMap<String, String>();
			map.put("subsys_code", "admin");
			map.put("role_type", String.valueOf(Constants.ROLE_TYPE_COMMON));
			allRoleList = userService.getSecRoleBySubsys(map);
			return SUCCESS;
		}
	}

	public Integer getAdmin_version() {
		return admin_version;
	}

	public void setAdmin_version(Integer admin_version) {
		this.admin_version = admin_version;
	}
}
