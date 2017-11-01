package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecUserPost;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 配置岗位等级
 * @author renmingfei
 *
 */
public class ConfigPostClassAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private List<Collection<SecUserPost>> postList = null;
	private List<String> post_id = null;
	private List<Integer> post_class = null;
	
	public List<Collection<SecUserPost>> getPostList() {
		return postList;
	}
	
	public void setPost_id(List<String> post_id) {
		this.post_id = post_id;
	}
	
	public void setPost_class(List<Integer> post_class) {
		this.post_class = post_class;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (post_id != null) {//处理等级修改
			if (post_id.size() == post_class.size()) {//处理修改
				userService.updatePostClass(post_id, post_class);
			} else {
				//TODO:throw exception
			}
		}
		List<SecUserPost> allPostList = userService.getAllUserPostList();
		List<SecUserPost> blankList = new ArrayList<SecUserPost>();
		Map<Integer, List<SecUserPost>> map = new TreeMap<Integer, List<SecUserPost>>();
		List<SecUserPost> classPostList = null;
		for (SecUserPost item : allPostList) {
			if (item.getPost_class() != null) {
				if (map.get(item.getPost_class()) == null) {
					classPostList = new ArrayList<SecUserPost>();
				} else {
					classPostList = map.get(item.getPost_class());
				}
				classPostList.add(item);
				map.put(item.getPost_class(), classPostList);
			} else {
				blankList.add(item);
			}
		}
		if (blankList.size() > 0) {
			map.put(new Integer(map.size() + 1), blankList);
		}
		postList = new ArrayList<Collection<SecUserPost>>(map.values());
		return SUCCESS;
	}
	
}
