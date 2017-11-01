package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysProxy;
import hdsec.web.project.user.model.ModuleEnum;
import hdsec.web.project.user.model.SecRole;
import hdsec.web.project.user.model.SecSubsys;
import hdsec.web.project.user.model.SecUser;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

/**
 * 添加代理
 * 
 * @author yueying
 * 
 */
public class AddUserApproveProxyAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private SysProxy proxy;
	private String next_approver;
	private Date useful_life_time;
	private String proxy_type;
	private String update = "N";
	private List<SecUser> userList = null;
	private List<SecSubsys> secSubsyslist = null;

	public List<SecSubsys> getSecSubsyslist() {
		return secSubsyslist;
	}

	public void setSecSubsyslist(List<SecSubsys> secSubsyslist) {
		this.secSubsyslist = secSubsyslist;
	}

	public String getNext_approver() {
		return next_approver;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public List<SecUser> getUserList() {
		return userList;
	}

	public void setUserList(List<SecUser> userList) {
		this.userList = userList;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equals("Y")) {
			for (String item : next_approver.split(",")) {
				Map<String, Object> map_check = new HashMap<String, Object>();
				map_check.put("proxy_user_iidd", item);
				map_check.put("proxy_type", proxy_type);
				map_check.put("user_iidd", getCurUser().getUser_iidd());
				int count = basicService.getCountbyApproveProxy(map_check);
				if (count == 0) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("proxy_user_iidd", item);
					map.put("useful_life_time", useful_life_time);
					map.put("proxy_type", proxy_type);
					map.put("user_iidd", getCurUser().getUser_iidd());
					basicService.addUserProxy(map);
					// 代理人增加可审批人权限
					/*
					 * secSubsyslist = userService.getAllSubsysAsCon(); SecSubsys secSubsys = secSubsyslist.get(0);
					 * String subsys_code = secSubsys.getSubsys_code();
					 */
					String newCodes = "";
					Map<String, String> map_role = new HashMap<String, String>();
					map_role.put("user_iidd", item);
					List<SecRole> secRoleList = userService.getRoleListByUser(map_role);
					for (SecRole secRole : secRoleList) {
						if (secRole.getRole_id().equals("10")) {
							newCodes = secRole.getRole_id();
						}
					}
					// userService.updateUserRole(item, subsys_code, newCodes)
					// 增加角色代理信息
					if (!newCodes.equals("10")) {
						Map<String, String> map_proxy = new HashMap<String, String>();
						map_proxy.put("user_iidd", item);
						map_proxy.put("role_id", "10");
						map_proxy.put("is_proxy", "is_proxy");
						map_proxy.put("agent", getCurUser().getUser_iidd());
						userService.insertProxyInfo(map_proxy);
					}
				}
			}
			return "ok";
		} else {
			/*
			 * List<ApproverUser> oriList = basicService.getNextApprover(null, getCurUser().getDept_id(), 1,
			 * "PRINT_REMAIN", "");
			 */
			beginIndex = 0;
			pageSize = 1000;
			RowBounds rbs = new RowBounds(beginIndex, pageSize);
			Map<String, Object> map_dept_id = new HashMap<String, Object>();
			// 本部门激活状态下的用户
			map_dept_id.put("status", "0");
			map_dept_id.put("is_sealed", "N");
			map_dept_id.put("isAllDept", "");
			map_dept_id.put("mainDeptId", getCurUser().getDept_id());
			userList = userService.getUserByDept(map_dept_id, rbs);
			for (SecUser user : userList) {
				if (user.getUser_iidd().equals(getCurUser().getUser_iidd())) {
					userList.remove(user);
					logger.debug("审批列表中去掉申请用户");
					break;
				}
			}
			if (userList.size() == 0) {
				throw new Exception("没有可代理的审批用户");
			}
			return SUCCESS;
		}

	}

	/*
	 * private List<ApproverUser> removeDuplicateList(List<ApproverUser> oriList) { Set<String> set = new
	 * HashSet<String>(); List<ApproverUser> newList = new ArrayList<ApproverUser>(); for (ApproverUser item : oriList)
	 * { if (set.add(item.getUser_iidd())) { newList.add(item); } } return newList; }
	 */
	public SysProxy getProxy() {
		return proxy;
	}

	public void setProxy(SysProxy proxy) {
		this.proxy = proxy;
	}

	public Date getUseful_life_time() {
		return useful_life_time;
	}

	public void setUseful_life_time(Date useful_life_time) {
		this.useful_life_time = useful_life_time;
	}

	public String getProxy_type() {
		return proxy_type;
	}

	public void setProxy_type(String proxy_type) {
		this.proxy_type = proxy_type;
	}

	public String getUser_name() {
		return getCurUser().getUser_name();
	}

	@Override
	public boolean getIsPrintEnable() {
		return ModuleEnum.PRINT.isModuleEnable();
	}

	@Override
	public boolean getIsBurnEnable() {
		return ModuleEnum.BURN.isModuleEnable();
	}

}
