package hdsec.web.project.user.action;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 修改用户
 * 
 * @author renmingfei
 * 
 */
public class UpdateSecUserAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	/**
	 * todo为空的时候，以下几个变量是从用户列表页面传来的查询条件， todo不为空的时候，为用户要修改的属性值,所以这几个变量只有set方法
	 */
	private String user_iidd = "";
	private String user_name = "";
	private String security_code = "";
	private String idCard = "";
	private String pass_num = "";
	private String print_method = "0";// 是否独立模式
	private String print_permission = "0";// 可否本地打印
	private Integer rank = null; // 排序值
	private String post_id = "";
	private Boolean isAllDept = null;
	private String status = "";
	private String todo = "";
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
	/**
	 * userIdList从列表跳进修改用户的页面后，会把之前的用户列表ID缓存进session, 通过session传递参数，所以该变量不需要getter和setter,注意修改用户返回之后，要把该变量从session中删除
	 */
	private List<String> userIdList = null;

	/**
	 * 以下几个变量需要在页面之间来回传递参数，所以既需要setter也需要getter
	 */
	private String userId = "";
	private String dept_id = "";
	private String direction = "";
	private String position = "";
	private Integer beforeCount = null;
	private Integer behindCount = null;

	/**
	 * 私有方法，修改安全用户信息
	 */
	private void updateSecUser() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", userId);
		map.put("user_name", user_name);
		map.put("post_id", "");
		map.put("security_code", security_code);
		map.put("idCard", idCard);
		map.put("pass_num", pass_num);
		map.put("print_method", print_method);
		map.put("print_permission", print_permission);
		map.put("rank", rank);
		boolean real_blank = StringUtils.hasLength(real_user_id);
		if (real_blank) {
			map.put("real_user_id", real_user_id);
		} else {
			map.put("base_username", base_username);
			map.put("base_sex", base_sex);
			map.put("base_nation", base_nation);
			map.put("base_birthday", base_birthday);
			map.put("base_birthplace", base_birthplace);
			map.put("base_nativeplace", base_nativeplace);
			map.put("base_country", base_country);
			map.put("base_politics", base_politics);
			map.put("base_joinpartytime", base_joinpartytime);
			map.put("edu_education", edu_education);
			map.put("edu_degree", edu_degree);
			map.put("edu_school", edu_school);
			map.put("edu_major", edu_major);
			map.put("edu_language", edu_language);
			map.put("edu_familiarity", edu_familiarity);
			map.put("com_residency", com_residency);
			map.put("com_police", com_police);
			map.put("com_address", com_address);
			map.put("com_telephone", com_telephone);
			map.put("com_mobile", com_mobile);
			map.put("com_email", com_email);
			map.put("job_category", job_category);
			map.put("job_seclevel", job_seclevel);
			map.put("job_adminpost", job_adminpost);
			map.put("job_techpost", job_techpost);
			map.put("job_techtitle", job_techtitle);
			map.put("job_humansort", job_humansort);
			map.put("job_insectime", job_insectime);
			map.put("job_workyears", job_workyears);
			map.put("job_employtype", job_employtype);
			map.put("job_passnum", job_passnum);
			map.put("job_passlevel", job_passlevel);
			map.put("job_inposttime", job_inposttime);
			map.put("job_offposttime", job_offposttime);
			map.put("job_resume", job_resume);
		}
		userService.updateSecUser(map, real_blank);
		insertAdminLog("修改用户:" + userId);
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

	public void setRank(Integer rank) {
		this.rank = rank;
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

	public void setReal_user_id(String real_user_id) {
		this.real_user_id = real_user_id;
	}

	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}

	public void setIsAllDept(Boolean isAllDept) {
		this.isAllDept = isAllDept;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public void setTodo(String todo) {
		this.todo = todo;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getBeforeCount() {
		return beforeCount;
	}

	public void setBeforeCount(Integer beforeCount) {
		this.beforeCount = beforeCount;
	}

	public Integer getBehindCount() {
		return behindCount;
	}

	public void setBehindCount(Integer behindCount) {
		this.behindCount = behindCount;
	}

	// 卡号查重
	public void checkPassNumIsUsed() throws Exception {
		if (StringUtils.hasLength(pass_num.trim())) {
			String used_user_iidd = "";
			used_user_iidd = userService.getSecUserIdByPassNum(pass_num.trim());
			if (StringUtils.hasLength(used_user_iidd) && !userId.equals(used_user_iidd)) {
				throw new Exception("用户卡号已存在,请更换！");
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String executeFunction() throws Exception {
		int newPos = -1;
		if (todo.equalsIgnoreCase("")) {// 没有todo值，表示是从列表点击进入修改的
			Map<String, String> map = new HashMap<String, String>();
			map.put("isAllDept", isAllDept == null ? "" : "Y");
			map.put("user_iidd", user_iidd);
			map.put("user_name", user_name);
			map.put("com_telephone", com_telephone);
			map.put("status", status);
			map.put("post_id", post_id);
			map.put("mainDeptId", dept_id);
			userIdList = userService.getUserIdList(map);
			getSession().setAttribute("userIdList", userIdList);
			newPos = userIdList.indexOf(userId);
		} else if (todo.equalsIgnoreCase("continue")) {// 修改完当前用户，继续修改其他用户，向前还是向后根据direction判断
			checkPassNumIsUsed();
			updateSecUser();
			userIdList = (List<String>) getSession().getAttribute("userIdList");
			if (direction.equalsIgnoreCase("prev")) {// 修改上一个用户
				newPos = userIdList.indexOf(userId) - 1;
			} else if (direction.equalsIgnoreCase("next")) {// 修改下一个用户
				newPos = userIdList.indexOf(userId) + 1;
			}
			userId = userIdList.get(newPos);
		} else if (todo.equalsIgnoreCase("return")) {// 修改并返回,返回
			checkPassNumIsUsed();
			updateSecUser();
			getSession().removeAttribute("userIdList");
			return "return";
		} else if (todo.equalsIgnoreCase("update")) {// 仅修改当前用户
			checkPassNumIsUsed();
			updateSecUser();
			userIdList = (List<String>) getSession().getAttribute("userIdList");
			newPos = userIdList.indexOf(userId);
		}
		if (newPos == 0) {
			position = "first";
		} else if (newPos == userIdList.size() - 1) {
			position = "last";
		}
		setBeforeCount(new Integer(newPos));
		setBehindCount(new Integer(userIdList.size() - newPos - 1));
		return SUCCESS;
	}
}
