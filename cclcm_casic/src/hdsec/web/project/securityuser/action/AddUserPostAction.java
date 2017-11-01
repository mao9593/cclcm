package hdsec.web.project.securityuser.action;

import hdsec.web.project.securityuser.model.PostDeptConfig;
import hdsec.web.project.securityuser.model.SysUserPost;

/**
 * 添加岗位
 * 
 * @author yangjl 2015-7-1
 * 
 */
public class AddUserPostAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private String post_id = "";// 岗位ID
	private String post_name = "";// 岗位名称
	private String post_desc = "";// 岗位描述
	private Integer post_level = null;// 岗位排序
	private Integer post_class = null;// 岗位等级
	private String is_sealed = "";// 封存标记，默认为N
	private String dept_ids = "";// 部门ID

	private PostDeptConfig postConfig = null;

	public String getPost_id() {
		return post_id;
	}

	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}

	public String getPost_name() {
		return post_name;
	}

	public void setPost_name(String post_name) {
		this.post_name = post_name;
	}

	public String getPost_desc() {
		return post_desc;
	}

	public void setPost_desc(String post_desc) {
		this.post_desc = post_desc;
	}

	public Integer getPost_level() {
		return post_level;
	}

	public void setPost_level(Integer post_level) {
		this.post_level = post_level;
	}

	public Integer getPost_class() {
		return post_class;
	}

	public void setPost_class(Integer post_class) {
		this.post_class = post_class;
	}

	public String getIs_sealed() {
		return is_sealed;
	}

	public void setIs_sealed(String is_sealed) {
		this.is_sealed = is_sealed;
	}

	public String getDept_ids() {
		return dept_ids;
	}

	public void setDept_ids(String dept_ids) {
		this.dept_ids = dept_ids;
	}

	public PostDeptConfig getPostConfig() {
		return postConfig;
	}

	@Override
	public String executeFunction() throws Exception {
		if (post_id.isEmpty()) {
			return SUCCESS;
		} else {
			if (securityUserService.isUserPostExist(post_id, post_name)) {
				throw new Exception("岗位特征值或名称已经存在，不能重复添加");
			}
			SysUserPost userpost = new SysUserPost(post_id, post_name, post_desc, post_level, post_class);
			securityUserService.addSysUserPost(userpost, dept_ids);
			insertAdminLog("添加岗位：特征值[" + post_id + "],岗位名称[" + post_name + "],岗位说明[" + post_desc + "],岗位排序["
					+ post_level + "],岗位等级[" + post_class + "],适用部门[" + dept_ids + "].");
			return "ok";
		}
	}

}
