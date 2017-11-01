package hdsec.web.project.basic.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.util.TimeUtil;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.RowBounds;

/**
 * 台账中处理“全部提交”的申请
 * 
 * @author liuyaling 2015-5-29
 */
public class HandleSubmitAllAction extends BasicBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// all
	private String hidseclv_code = "";
	private Date hidstartTime = null;
	private Date hidendTime = null;
	private String hidhandle_type = "";
	private String addjob = "N";
	private ApproveProcess process = null;
	private String next_approver = "";
	private JobTypeEnum hidjobType = null;
	private JobTypeEnum jobType = null;
	private List<ApproverUser> userList = null;
	private SecLevel seclv = null;
	private Integer seclv_code = null;
	private String isDept = "N";
	// self paper
	private String hidfile_title = "";
	private String hidpaper_barcode = "";
	private String hidkeyword_content = "";
	private Integer hidexpire_status = null;
	private String is_reprint = "";
	private String hidpaper_state = "";
	private List<EntityPaper> paperList = null;

	// self cd
	private List<EntityCD> CDLedgerList = null;
	private String hidcd_barcode = "";
	private String hidconf_code = "";
	private String hidcd_state = "";

	// dept paper
	private String hidscope_dept_id = "";
	private String hiduser_name = "";
	private String hiddept_name = "";
	private String hiddept_ids = "";

	// dept cd
	private String hidfile_list = "";

	public List<EntityPaper> getPaperList() {
		return paperList;
	}

	public void setPaperList(List<EntityPaper> paperList) {
		this.paperList = paperList;
	}

	public String getHidfile_list() {
		return hidfile_list;
	}

	public void setHidfile_list(String hidfile_list) {
		this.hidfile_list = hidfile_list;
	}

	public String getHiddept_ids() {
		return hiddept_ids;
	}

	public void setHiddept_ids(String hiddept_ids) {
		this.hiddept_ids = hiddept_ids;
	}

	public String getHidscope_dept_id() {
		return hidscope_dept_id;
	}

	public void setHidscope_dept_id(String hidscope_dept_id) {
		this.hidscope_dept_id = hidscope_dept_id;
	}

	public String getHiduser_name() {
		return hiduser_name;
	}

	public void setHiduser_name(String hiduser_name) {
		this.hiduser_name = hiduser_name;
	}

	public String getHiddept_name() {
		return hiddept_name;
	}

	public void setHiddept_name(String hiddept_name) {
		this.hiddept_name = hiddept_name;
	}

	public String getHidcd_barcode() {
		return hidcd_barcode;
	}

	public void setHidcd_barcode(String hidcd_barcode) {
		this.hidcd_barcode = hidcd_barcode;
	}

	public String getHidconf_code() {
		return hidconf_code;
	}

	public void setHidconf_code(String hidconf_code) {
		this.hidconf_code = hidconf_code;
	}

	public String getHidcd_state() {
		return hidcd_state;
	}

	public void setHidcd_state(String hidcd_state) {
		this.hidcd_state = hidcd_state;
	}

	public List<ApproverUser> getUserList() {
		return userList;
	}

	public void setUserList(List<ApproverUser> userList) {
		this.userList = userList;
	}

	public JobTypeEnum getHidjobType() {
		return hidjobType;
	}

	public void setHidjobType(JobTypeEnum hidjobType) {
		this.hidjobType = hidjobType;
	}

	public JobTypeEnum getJobType() {
		return hidjobType;
	}

	public void setJobType(JobTypeEnum jobType) {
		this.jobType = jobType;
	}

	public Integer getHidexpire_status() {
		return hidexpire_status;
	}

	public void setHidexpire_status(Integer hidexpire_status) {
		this.hidexpire_status = hidexpire_status;
	}

	public String getIsDept() {
		return isDept;
	}

	public void setIsDept(String isDept) {
		this.isDept = isDept;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public SecLevel getSeclv() {
		return seclv;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public String getHidpaper_state() {
		return hidpaper_state;
	}

	public void setHidpaper_state(String hidpaper_state) {
		this.hidpaper_state = hidpaper_state;
	}

	public String getIs_reprint() {
		return is_reprint;
	}

	public void setIs_reprint(String is_reprint) {
		this.is_reprint = is_reprint;
	}

	public String getHidhandle_type() {
		return hidhandle_type;
	}

	public void setHidhandle_type(String hidhandle_type) {
		this.hidhandle_type = hidhandle_type;
	}

	public String getAddjob() {
		return addjob;
	}

	public void setAddjob(String addjob) {
		this.addjob = addjob;
	}

	public String getHidfile_title() {
		return hidfile_title;
	}

	public void setHidfile_title(String hidfile_title) {
		this.hidfile_title = hidfile_title;
	}

	public String getHidpaper_barcode() {
		return hidpaper_barcode;
	}

	public void setHidpaper_barcode(String hidpaper_barcode) {
		this.hidpaper_barcode = hidpaper_barcode;
	}

	public String getHidkeyword_content() {
		return hidkeyword_content;
	}

	public void setHidkeyword_content(String hidkeyword_content) {
		this.hidkeyword_content = hidkeyword_content;
	}

	public String getHidseclv_code() {
		return hidseclv_code;
	}

	public void setHidseclv_code(String hidseclv_code) {
		this.hidseclv_code = hidseclv_code;
	}

	public String getHidstartTime() {
		return hidstartTime == null ? "" : sdf.format(hidstartTime);
	}

	public void setHidstartTime(Date hidstartTime) {
		this.hidstartTime = hidstartTime;
	}

	public String getHidendTime() {
		return hidendTime == null ? "" : sdf.format(hidendTime);
	}

	public void setHidendTime(Date hidendTime) {
		this.hidendTime = hidendTime;
	}

	public String getHandle_type() {
		return "submitall";
	}

	/**
	 * 去重
	 * 
	 * @param oriList
	 * @return
	 */
	private List<ApproverUser> removeDuplicateList(List<ApproverUser> oriList) {
		Set<String> set = new HashSet<String>();
		List<ApproverUser> newList = new ArrayList<ApproverUser>();
		for (ApproverUser item : oriList) {
			if (set.add(item.getUser_iidd())) {
				newList.add(item);
			}
		}
		return newList;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> dept_ids = new ArrayList<String>();

		map.put("seclv_code", hidseclv_code);
		map.put("start_time", hidstartTime);
		map.put("end_time", hidendTime);
		map.put("expire_status", hidexpire_status);
		map.put("expire_time", new Date());
		map.put("coming_expire_time", TimeUtil.getAfterXDay(2));
		map.put("user_name", hiduser_name);
		map.put("dept_name", hiddept_name);

		if (hidhandle_type.equals("paper")) {
			map.put("paper_barcode", hidpaper_barcode);
			map.put("paper_state", hidpaper_state);
			map.put("file_title", hidfile_title);
			if (isDept.contains("N")) {// 个人
				map.put("user_iidd", getCurUser().getUser_iidd());
				map.put("is_reprint", is_reprint);
				map.put("keyword_content", hidkeyword_content);
				map.put("scope", "PERSON");

			} else {// 部门
				if (hiddept_ids == null || hiddept_ids == "") {
					throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
				} else {
					for (String ids : hiddept_ids.split(",")) {
						dept_ids.add(ids);
					}
				}
				map.put("scope_dept", "DEPT");
				map.put("scope_dept_id", hidscope_dept_id);
				map.put("scope_dept_ids", dept_ids);

			}
		} else {// 光盘

			map.put("cd_barcode", hidcd_barcode);
			map.put("cd_state", hidcd_state);
			map.put("conf_code", hidconf_code);
			if (isDept.contains("N")) {// 个人
				map.put("user_iidd", getCurUser().getUser_iidd());
				map.put("scope", "PERSON");
			} else {// 部门
				if (hiddept_ids == null || hiddept_ids == "") {
					throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
				} else {
					for (String ids : hiddept_ids.split(",")) {
						dept_ids.add(ids);
					}
				}

				map.put("file_list", hidfile_list);
				map.put("scope_dept", "DEPT");
				map.put("scope_dept_id", hidscope_dept_id);
				map.put("scope_dept_ids", dept_ids);
			}

		}
		totalSize = 0;
		if (hidhandle_type.equals("paper")) {
			totalSize = ledgerService.getAllPaperLedgerSize(map);
		} else {
			totalSize = ledgerService.getAllCDLedgerSize(map);
		}

		if (totalSize == 0) {
			return "ok_null";// 检索结果为空
		}

		RowBounds rbs = new RowBounds(0, totalSize);
		if (hidhandle_type.equals("paper")) {
			paperList = ledgerService.getAllPaperLedgerList(map, rbs);
		} else {
			CDLedgerList = ledgerService.getAllCDLedgerList(map, rbs);
		}

		if (addjob.equalsIgnoreCase("Y")) {// 已经填写审批信息，要添加流程
			try {
				if (hidhandle_type.equals("paper")) {// 纸
					basicService.handleSubmitAllPaperJob(getCurUser().getUser_iidd(), getCurUser().getDept_id(),
							seclv_code, jobType, next_approver, paperList, "");
					for (int i = 0; i < paperList.size(); i++) {
						insertCommonLog("提交纸质载体" + jobType.getJobTypeName() + "审批申请：条码号["
								+ paperList.get(i).getPaper_barcode() + "].");
					}
				} else {// 光盘
					basicService.handleSubmitAllCDJob(getCurUser().getUser_iidd(), getCurUser().getDept_id(),
							seclv_code, jobType, next_approver, CDLedgerList, "");
					for (int i = 0; i < CDLedgerList.size(); i++) {
						insertCommonLog("提交纸质载体" + jobType.getJobTypeName() + "审批申请：条码号["
								+ CDLedgerList.get(i).getCd_barcode() + "].");
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			if (isDept.equalsIgnoreCase("N")) {// 个人
				if (hidhandle_type.equals("paper")) {
					return "paper_ok";
				} else {
					return "cd_ok";
				}

			} else {// 部门

				if (hidhandle_type.equals("paper")) {
					return "paper_ok1";
				} else {
					return "cd_ok1";
				}
			}

		} else {// 展示审批流程信息
			Integer tempSeclv = -1;
			if (hidhandle_type.equals("paper")) {
				for (EntityPaper item : paperList) {
					if (tempSeclv == -1 || tempSeclv == item.getSeclv_code()) {
						tempSeclv = item.getSeclv_code();
					} else {
						return "paper_secError";
					}
				}
			} else if (hidhandle_type.equals("cd")) {
				for (EntityCD item : CDLedgerList) {
					if (tempSeclv == -1 || tempSeclv == item.getSeclv_code()) {
						tempSeclv = item.getSeclv_code();
					} else {
						return "cd_secError";
					}
				}
			}

			// 查询流程定义，用于展示流程信息
			process = basicService.getApproveProcessByKey(getCurUser().getDept_id(), tempSeclv,
					hidjobType.getJobTypeCode(), "");
			List<ApproverUser> oriList = basicService.getNextApprover(null, getCurUser().getDept_id(), tempSeclv,
					hidjobType.getJobTypeCode(), "");
			userList = removeDuplicateList(oriList);
			if (!basicService.isSelfApprove()) {// 不允许自审批
				if ((userList != null) && (userList.size() == 1)
						&& userList.get(0).getUser_iidd().equals(getCurUser().getUser_iidd())) {
					throw new Exception("唯一的下级审批人与申请用户相同！由于系统不支持自审批，请先联系管理员重设此流程步骤或添加可审批用户！");
				} else {
					for (ApproverUser user : userList) {
						if (user.getUser_iidd().equals(getCurUser().getUser_iidd())) {
							userList.remove(user);
							logger.debug("审批列表中去掉申请用户");
							break;
						}
					}
				}
			}
			List<ApproverUser> tempList = new ArrayList<ApproverUser>();
			for (ApproverUser user : userList) {
				List<SecLevel> seclvList = userService.getPrintSecLevelByUser(user.getUser_iidd());
				for (SecLevel seclv : seclvList) {
					if (seclv.getSeclv_code() == tempSeclv) {
						tempList.add(user);
						break;
					}
				}
			}
			if (userList.size() > 0 && tempList.size() == 0) {
				throw new Exception("下级审批人涉密级别低于审批单密级，请联系管理员");
			}
			userList = tempList;
			seclv = userService.getSecLevelByCode(tempSeclv);

			if (hidhandle_type.equals("paper")) {
				return "paper_success";
			} else {
				return "cd_success";
			}
		}
	}
}