package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.model.SecUserPost;

import java.util.Collections;
import java.util.List;

public class ManageDeptPostAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String dept_id = "";
	private String update = "N";
	private List<String> selectedPostIdList = null;
	private SecDept secDept = null;
	private List<SecUserPost> inDeptPostList = null;
	private List<SecUserPost> outDeptPostList = null;
	
	public String getDept_id() {
		return dept_id;
	}
	
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
	public void setUpdate(String update) {
		this.update = update;
	}
	
	public SecDept getSecDept() {
		return secDept;
	}
	
	public void setSelectedPostIdList(List<String> selectedPostIdList) {
		this.selectedPostIdList = selectedPostIdList;
	}
	
	public List<SecUserPost> getInDeptPostList() {
		return inDeptPostList;
	}
	
	public List<SecUserPost> getOutDeptPostList() {
		return outDeptPostList;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {//进行岗位配置的数据库操作
			if (selectedPostIdList == null) {
				selectedPostIdList = Collections.emptyList();
			}
			userService.addSecDeptPostRel(dept_id, selectedPostIdList);
		}
		secDept = userService.getSecDeptById(dept_id);
		inDeptPostList = userService.getInDeptPostListByDept(dept_id);
		outDeptPostList = userService.getOutDeptPostListByDept(dept_id);
		return SUCCESS;
	}
}
