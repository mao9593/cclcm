package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecUserPost;

import java.util.List;

/**
 * 配置岗位显示顺序
 * @author renmingfei
 *
 */
public class ConfigPostOrderAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private List<SecUserPost> postList = null;
	private List<String> post_id = null;
	private List<Integer> post_level = null;
	
	public List<SecUserPost> getPostList() {
		return postList;
	}
	
	public void setPost_id(List<String> post_id) {
		this.post_id = post_id;
	}
	
	public void setPost_level(List<Integer> post_level) {
		this.post_level = post_level;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (post_id == null) {//进入调整顺序的页面
			postList = userService.getAllUserPostList();
		} else {//把调整结果存入数据库
			if (post_id.size() == post_level.size()) {
				userService.updatePostLevel(post_id, post_level);
				insertAdminLog("配置岗位显示顺序");
				return "config";
			} else {
				//TODO:throw exception
				
			}
		}
		return SUCCESS;
	}
	
}
