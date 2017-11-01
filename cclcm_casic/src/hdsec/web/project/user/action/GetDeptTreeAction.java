package hdsec.web.project.user.action;

import hdsec.web.project.user.model.DeptTree;

import java.util.List;

/**
 * 获得部门树形结构
 * 
 * @author renmingfei
 * 
 */
public class GetDeptTreeAction extends UserBaseAction {

	private static final long serialVersionUID = 1L;
	private List<DeptTree> deptTreeList = null;
	private String context = "";

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public List<DeptTree> getDeptTreeList() {
		return deptTreeList;
	}

	@Override
	public String executeFunction() throws Exception {
		deptTreeList = userService.getDeptTree();
		/**
		 * 遍历，找不到父节点的，则把父节点置成空 List<String> codeList = new ArrayList<String>(); List<String> parentCodeList = new
		 * ArrayList<String>(); for (DeptTree item:deptTreeList) { if (item.getDept_level_code() == 0)//部门等级从1开始
		 * continue; codeList.add(item.getDept_id()); parentCodeList.add(item.getDept_parent_code()); } outer: for (int
		 * i = 0, n = parentCodeList.size(); i < n; i++) { Object code = parentCodeList.get(i); for (int j = 0; j < i;
		 * j++) { if (codeList.get(j).equals(code)) { continue outer; } } DeptTree deptTree = deptTreeList.get(i);
		 * deptTree.setDept_parent_code(""); }
		 */
		return SUCCESS;
	}

}
