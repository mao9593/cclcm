package hdsec.web.project.common.util;

import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

public class DeptListUtil {
	@Resource
	private UserService userService;
	private List<DeptList> allDeptList = null;
	
	public List<DeptList> getDeptList() {
		
		List<SecDept> allDept = userService.getAllSecDept();
		if (allDept == null || allDept.size() == 0)
			return null;
		
		allDeptList = new ArrayList<DeptList>();
		
		HashMap<String, DeptList> orderMap = new HashMap<String, DeptList>();
		boolean isFirstLevel = true;
		//int nowLevel = allDept.get(0).getDept_cs().length() / 2;//根机构级别
		for (SecDept item : allDept) {
			DeptList deptList = new DeptList();
			deptList.dept = item;
			if (!StringUtils.hasLength(deptList.dept.getDept_level_code()))
				continue;
			if (StringUtils.hasLength(item.getDept_parent_id())) {//父机构ID不为空，说明不是根级机构
				isFirstLevel = false;
			}
			
			if (isFirstLevel) {
				allDeptList.add(deptList);
			} else {
				DeptList parDeptList = orderMap.get(getParentIdCodeByDeptId(item.getDept_id()));
				if (parDeptList != null) {
					if (parDeptList.childDeptList == null) {
						parDeptList.childDeptList = new ArrayList<DeptList>();
					}
					parDeptList.childDeptList.add(deptList);
				}
			}
			orderMap.put(item.getDept_id(), deptList);
		}
		return allDeptList;
	}
	
	private String getParentIdCodeByDeptId(String dept_id) {
		return userService.getSecDeptById(dept_id).getDept_parent_id();
	}
	
	public List<DeptList> getAllDeptList() {
		if (allDeptList == null)
			getDeptList();
		return allDeptList;
	}
	
	/**
	 *
	 * @param deptIds         候选部门编码，用,分隔
	 * @param selectedDeptIds 已经选中的部门编码，用,分隔
	 * @param treeType          呈现方式，在候选部门编码不为空时有效。child->部门及子部门，self->只有部门，这里没有使用
	 * @param enReload          刷新，为true时刷新
	 * @param deptOrUser        是部门显示还是用户显示，为true是为部门显示
	 * @param onlyMainDept      是否仅为主部门
	 * @return 部门树代码
	 */
	public StringBuffer getDeptListTreeXML(String selectedDeptIds, String treeType, boolean enReload) {
		if (allDeptList == null || enReload) {
			getDeptList();
		}
		StringBuffer sb = new StringBuffer();
		List<SecDept> deptsWithUserTmp = userService.getAllSecDept();
		List<SecDept> tmpDepList = new ArrayList<SecDept>();
		for (SecDept item : deptsWithUserTmp) {
			if (StringUtils.hasLength(item.getDept_level_code())) {//注意机构等级不能为空！！！
				tmpDepList.add(item);
			}
		}
		sb.append("<tree>");
		for (int i = 0; i < allDeptList.size(); i++) {
			sb.append(deptListToXML(allDeptList.get(i), selectedDeptIds, true, tmpDepList));
		}
		sb.append("</tree>");
		return sb;
	}
	
	/**
	 * 获取一个部门节点极其子部门
	 * @param deptList
	 * @param deptId  部门编码
	 * @return
	 */
	public StringBuffer getOneDeptListTreeXML(List<DeptList> deptList, String deptId, String selectedDeptIds,
			boolean withChild, boolean deptOrUser, boolean onlyMainDept, String specCodes, String operCodes,
			List<SecDept> deptsWithUser) {
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < deptList.size(); i++) {
			DeptList deptListTemp = deptList.get(i);
			if (deptListTemp.dept.getDept_id().equals(deptId)) {
				sb.append(deptListToXML(deptList.get(i), selectedDeptIds, withChild, deptsWithUser));
			}
			if (deptListTemp.childDeptList != null) {
				sb.append(getOneDeptListTreeXML(deptListTemp.childDeptList, deptId, selectedDeptIds, withChild,
						deptOrUser, onlyMainDept, specCodes, operCodes, deptsWithUser));
			}
		}
		return sb;
	}
	
	private StringBuffer deptListToXML(DeptList deptList, String selectedDeptIds, boolean withChild,
			List<SecDept> deptsWithUser) {
		
		//判断是否选中
		deptList.selected = false;
		if (selectedDeptIds != null) {
			deptList.selected = (("," + selectedDeptIds + ",").indexOf("," + deptList.dept.getDept_id() + ",") != -1);
		}
		StringBuffer s = new StringBuffer();
		String nodeType = "dept";
		s.append("<tree text=\"" + deptList.dept.getDept_name() + "\" sid=\"" + deptList.dept.getDept_id()
				+ "\" selected=\"" + String.valueOf(deptList.selected) + "\" nodeType=\"" + nodeType + "\" ");
		s.append(">");
		if (withChild && deptList.childDeptList != null) {
			for (int i = 0; i < deptList.childDeptList.size(); i++) {
				s.append(deptListToXML(deptList.childDeptList.get(i), selectedDeptIds, withChild, deptsWithUser));
			}
		}
		s.append("</tree>");
		
		return s;
	}
	
}
