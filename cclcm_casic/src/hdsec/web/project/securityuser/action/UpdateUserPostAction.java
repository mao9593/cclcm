package hdsec.web.project.securityuser.action;

import hdsec.web.project.securityuser.model.PostDeptConfig;
import hdsec.web.project.securityuser.model.SysUserPost;

import java.util.List;

/**
 * 修改岗位信息
 * 
 * @author yangjl2015-6-30
 * 
 */
public class UpdateUserPostAction extends SecurityUserBaseAction {

	private static final long serialVersionUID = 1L;
	private String post_id = "";// 岗位ID
	private String post_name = "";// 岗位名称
	private String post_desc = "";// 岗位描述
	private Integer post_level = null;// 岗位排序
	private Integer post_class = null;// 岗位等级
	private String is_sealed = "";// 封存标记，默认为N
	private SysUserPost userpost = null;
	private String update = "N";
	private String dept_ids = "";// 部门ID
	private String dept_names = "";
	private List<PostDeptConfig> postDeptList = null;
	private List<PostDeptConfig> depts = null;

	public List<PostDeptConfig> getDepts() {
		return depts;
	}

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

	public String getDept_ids() {
		return dept_ids;
	}

	public void setDept_ids(String dept_ids) {
		this.dept_ids = dept_ids;
	}

	public String getIs_sealed() {
		return is_sealed;
	}

	public void setIs_sealed(String is_sealed) {
		this.is_sealed = is_sealed;
	}

	public SysUserPost getUserpost() {
		return userpost;
	}

	public void setUserpost(SysUserPost userpost) {
		this.userpost = userpost;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public List<PostDeptConfig> getPostDeptList() {
		return postDeptList;
	}

	public String getDept_names() {
		return dept_names;
	}

	public void setDept_names(String dept_names) {
		this.dept_names = dept_names;
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("N")) {
			userpost = securityUserService.getUserPostByCode(post_id);
			// 拼接部门名称
			postDeptList = securityUserService.getPostDeptList(post_id, null);
			for (PostDeptConfig item : postDeptList) {
				dept_ids += item.getDept_id() + ",";
				dept_names += item.getDept_name() + ",";
			}
			if (dept_ids.endsWith(",")) {
				dept_ids = dept_ids.substring(0, dept_ids.length() - 1);
			}
			if (dept_names.endsWith(",")) {
				dept_names = dept_names.substring(0, dept_names.length() - 1);
			}
			depts = securityUserService.getPostDeptList(post_id, null);
			return SUCCESS;
		} else {
			userpost = new SysUserPost(post_id, post_name, post_desc, post_level, post_class);
			securityUserService.updateUserPost(userpost, dept_ids);
			insertAdminLog("修改岗位：特征值[" + post_id + "],岗位名称[" + post_name + "],岗位说明[" + post_desc + "],岗位排序["
					+ post_level + "],岗位等级[" + post_class + "],适用部门[" + dept_ids + "]");
			return "ok";
		}
	}
}
