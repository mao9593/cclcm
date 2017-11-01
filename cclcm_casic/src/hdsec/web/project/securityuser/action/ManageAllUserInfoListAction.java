package hdsec.web.project.securityuser.action;

import hdsec.web.project.common.bm.model.BmRealUser;
import hdsec.web.project.securityuser.model.JudgeUserInfo;
import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.model.SecUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 * 查看部门用户资料完善记录
 * 
 * @author guojiao
 */
public class ManageAllUserInfoListAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private String dept_id = "";
	private String user_id = "";
	private List<BmRealUser> eventList = null;
	private List<SecDept> secAdminDeptList = null;
	private List<String> dept_ids = null;
	private List<SecUser> userSec = null;
	private List<JudgeUserInfo> infoList = null;
	private List<JudgeUserInfo> infoListTmp = null;
	private JudgeUserInfo judgeInfo = null;
	private SecUser tmp = null;
	private Integer statue = null;
	private int i = 0;
	private String user_name = "";
	private String dept_name = "";

	public Integer getStatue() {
		return statue;
	}

	public void setStatue(Integer statue) {
		this.statue = statue;
	}

	public List<JudgeUserInfo> getInfoList() {
		return infoList;
	}

	public List<SecUser> getUserSec() {
		return userSec;
	}

	public void setUserSec(List<SecUser> userSec) {
		this.userSec = userSec;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public List<BmRealUser> getEventList() {
		return eventList;
	}

	public List<SecDept> getSecAdminDeptList() {
		return secAdminDeptList;
	}

	public String getDept_ids() {
		String ret = "";
		for (String d_item : dept_ids) {
			ret += d_item + ",";
		}
		if (ret.endsWith(",")) {
			ret = ret.substring(0, ret.length() - 1);
		}
		return ret;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	@Override
	public String executeFunction() throws Exception {
		String web_url = getModuleName().toLowerCase() + "/" + getTitleName().toLowerCase() + ".action";
		dept_ids = basicService.getAdminDeptIdList(getCurUser().getUser_iidd(), web_url);
		if (dept_ids == null || dept_ids.size() == 0) {
			throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
		}
		secAdminDeptList = basicService.getDeptAdminList(getCurUser().getUser_iidd(), web_url);

		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;

		Map<String, Object> map2 = new HashMap<String, Object>();
		infoList = new ArrayList<JudgeUserInfo>();
		infoListTmp = new ArrayList<JudgeUserInfo>();
		userSec = userService.getSecUser(tmp);
		for (SecUser userid : userSec) {
			// 过滤系统内置三员用户
			if (userid.getUser_iidd().startsWith("sysadmin") || userid.getUser_iidd().startsWith("secadmin")
					|| userid.getUser_iidd().startsWith("admin") || userid.getUser_iidd().startsWith("audadmin")) {
				continue;
			}

			int test = 0;
			for (String deptid : dept_ids) {
				if (userid.getDept_id().equals(deptid)) {
					test = 1;
					break;
				}
			}
			if (test != 1) {
				continue;
			}

			// 先判断Bmuser中是否存在该用户的信息
			if (securityUserService.getBmUserById(userid.getUser_iidd()) != 0) {
				map2.put("user_id", userid.getUser_iidd());
				map2.put("info_type", "2");
				eventList = securityUserService.getAllUserInfoList(map2);
				// 存在该用户信息但是未通过，已申请状态2
				if (eventList.size() == 0) {
					map2.put("user_id", userid.getUser_iidd());
					map2.put("info_type", "1");
					eventList = securityUserService.getAllUserInfoList(map2);
					if (eventList.size() == 0) {
						// 该用户资料已保存3但未申请
						judgeInfo = new JudgeUserInfo(userid, null, 1);
						infoListTmp.add(judgeInfo);
					} else {
						judgeInfo = new JudgeUserInfo(userid, eventList.get(0), 2);
						infoListTmp.add(judgeInfo);
					}
				} else {
					// 该用户个人资料已申请并审批通过3
					judgeInfo = new JudgeUserInfo(userid, eventList.get(0), 3);
					infoListTmp.add(judgeInfo);
				}
			} else {
				// 该用户资料未申请1
				judgeInfo = new JudgeUserInfo(userid, null, 1);
				infoListTmp.add(judgeInfo);
			}
			judgeInfo = null;
		}
		// 查询界面根据”部门“和”状态“进行判断查询
		if (!dept_id.equals("") && statue != null) {
			for (JudgeUserInfo infoTmp : infoListTmp) {
				if (infoListTmp.get(i).getSuser().getDept_id().equals(dept_id)
						&& infoListTmp.get(i).getStatue() == statue) {
					infoList.add(infoTmp);
				}
				i++;
			}
		} else if (!user_id.equals("")) {
			for (JudgeUserInfo infoTmp : infoListTmp) {
				if (infoListTmp.get(i++).getSuser().getUser_iidd().equals(user_id)) {
					infoList.add(infoTmp);
				}
			}
		} else if (!dept_id.equals("")) {
			for (JudgeUserInfo infoTmp : infoListTmp) {
				if (infoListTmp.get(i++).getSuser().getDept_id().equals(dept_id)) {
					infoList.add(infoTmp);
				}
			}
		} else if (statue != null) {
			for (JudgeUserInfo infoTmp : infoListTmp) {
				if (infoListTmp.get(i++).getStatue() == statue) {
					infoList.add(infoTmp);
				}
			}
		} else {
			infoList = infoListTmp;
		}

		return SUCCESS;
	}
}
