package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecCommonContact;
import hdsec.web.project.user.model.SecCommonGroup;
import hdsec.web.project.user.model.SecUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 查询常用联系人
 * @author renmingfei
 *
 */
public class ConfigCommonTreeAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	String deptOrUser = "";
	String deptCodes = "";
	String treeType = "";
	String onlyMainDept = "";
	String specCodes = "";
	String operCodes = "";
	Map<Integer, List<SecCommonContact>> groupMap = new HashMap<Integer, List<SecCommonContact>>();
	List<SecCommonGroup> groupList = new ArrayList<SecCommonGroup>();
	
	public Map<Integer, List<SecCommonContact>> getGroupMap() {
		return groupMap;
	}
	
	public List<SecCommonGroup> getGroupList() {
		return groupList;
	}
	
	public String getDeptOrUser() {
		return deptOrUser;
	}
	
	public void setDeptOrUser(String deptOrUser) {
		this.deptOrUser = deptOrUser;
	}
	
	public String getDeptCodes() {
		return deptCodes;
	}
	
	public void setDeptCodes(String deptCodes) {
		this.deptCodes = deptCodes;
	}
	
	public String getTreeType() {
		return treeType;
	}
	
	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}
	
	public String getOnlyMainDept() {
		return onlyMainDept;
	}
	
	public void setOnlyMainDept(String onlyMainDept) {
		this.onlyMainDept = onlyMainDept;
	}
	
	public String getSpecCodes() {
		return specCodes;
	}
	
	public void setSpecCodes(String specCodes) {
		this.specCodes = specCodes;
	}
	
	public String getOperCodes() {
		return operCodes;
	}
	
	public void setOperCodes(String operCodes) {
		this.operCodes = operCodes;
	}
	
	@Override
	public String executeFunction() throws Exception {
		//查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.hasLength(deptCodes)) {
			List<String> tempDept = new ArrayList<String>();
			for (String t : deptCodes.split(",")) {
				tempDept.add(t);
			}
			map.put("dept_id", tempDept);
		}
		
		if (treeType.equals("child")) {
			map.put("child", "1");
		} else {
			map.put("child", "0");
		}
		if (onlyMainDept.equals("true")) {
			map.put("en_main", "true");
		}
		if (StringUtils.hasLength(operCodes)) {
			List<String> tempOper = new ArrayList<String>();
			for (String t : operCodes.split(",")) {
				tempOper.add(t);
			}
			map.put("oper_code", tempOper);
		}
		
		SecUser user = getSecUserFromSession();
		if (user == null) {
			//TODO
		} else {
			map.put("user_iidd", user.getUser_iidd());
			//分组
			groupList = userService.getSecCommonGroupByUserId(user.getUser_iidd());
			List<SecCommonContact> contactList = new ArrayList<SecCommonContact>();
			
			if (deptOrUser.equals("true")) {//部门
				contactList = userService.getCommonContactForDept(map);
				
			} else if (deptOrUser.equals("false")) { //用户
				contactList = userService.getCommonContactForUser(map);
			}
			//联系人放入map中
			for (int i = 0; i < contactList.size(); i++) {
				SecCommonContact commonContact = contactList.get(i);
				List<SecCommonContact> oneGroupList = groupMap.get(new Integer(commonContact.getGroup_id()));
				if (oneGroupList == null)
					oneGroupList = new ArrayList<SecCommonContact>();
				oneGroupList.add(commonContact);
				groupMap.put(new Integer(commonContact.getGroup_id()), oneGroupList);
			}
			
		}
		return SUCCESS;
	}
}
