package hdsec.web.project.securityuser.action;

import hdsec.web.project.common.bm.model.BmRealUser;
import hdsec.web.project.user.model.SecUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 脱密离职人员表单查询用户信息
 * 
 * @author Lishu
 * 
 */
public class GetResignUserInfoAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private String useid = "";
	private List<BmRealUser> bmUserList = null;
	private BmRealUser bmUser = null;
	private SecUser resignUser = null;
	private String paper_total = "";
	private String cd_total = "";
	private Integer all_total = null;
	private String computer_num = "";// 计算机数量
	private String device_num = "";// 信息设备数量
	private String media_num = "";// 介质数量

	public String getComputer_num() {
		return computer_num;
	}

	public String getDevice_num() {
		return device_num;
	}

	public String getMedia_num() {
		return media_num;
	}

	public Integer getAll_total() {
		return all_total;
	}

	public String getUseid() {
		return useid;
	}

	public void setUseid(String useid) {
		this.useid = useid;
	}

	public List<BmRealUser> getBmUserList() {
		return bmUserList;
	}

	public void setBmUserList(List<BmRealUser> bmUserList) {
		this.bmUserList = bmUserList;
	}

	public BmRealUser getBmUser() {
		return bmUser;
	}

	public void setBmUser(BmRealUser bmUser) {
		this.bmUser = bmUser;
	}

	public SecUser getResignUser() {
		return resignUser;
	}

	public void setResignuser(SecUser resignUser) {
		this.resignUser = resignUser;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", useid);
		map.put("info_type", "2");
		map.put("user_statue", "1");// 人员状态为：在岗
		bmUserList = securityUserService.getAllUserInfoList(map);
		if (bmUserList.size() == 0 || useid.isEmpty()) {
			return "noinfo";
		}
		bmUser = bmUserList.get(0);
		resignUser = userService.getSecUserByUid(useid);

		// 查询该用户个人涉密载体总数
		paper_total = securityUserService.getUserEntityPaperTotal(map);
		cd_total = securityUserService.getUserEntityCdTotal(map);
		all_total = Integer.valueOf(paper_total).intValue() + Integer.valueOf(cd_total).intValue();

		map.put("duty_user_id", useid);
		computer_num = computerService.getComputerNumByMap(map); // 个人计算机数量查询
		device_num = computerService.getInfoDeviceNumByMap(map); // 信息设备(非介质类)数量查询
		media_num = computerService.getMediaNumByMap(map); // 介质数量查询

		return SUCCESS;
	}
}
