package hdsec.web.project.securityuser.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.burn.model.BurnFile;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.bm.BMPropertyUtil;
import hdsec.web.project.common.bm.model.BmRealUser;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.passport.model.EntityPassport;
import hdsec.web.project.secmanage.model.FileListEvent;
import hdsec.web.project.securityuser.model.ResignEvent;
import hdsec.web.project.securityuser.model.UserAbroadEvent;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.util.StringUtils;

public class ApproveUAbroadJobAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<UserAbroadEvent> eventList = null;
	private String approved = "";
	private String opinion = "";
	private String next_approver = "";
	private List<ApproverUser> userList = null;
	private String history = "";
	private BmRealUser applyinfo = null;
	private SecUser applyuser = null;

	private Integer listSize = null;
	private String opinion_all = "";
	private UserAbroadEvent event = null;
	private String add_file = "";
	private FileListEvent file = null;
	private List<BurnFile> approveFileList = null;
	private String p_chk = "";
	// 审批中-申请人确认增加字段，更新数据库中
	private Date abroad_time = null;
	private Date abroad_back = null;
	private String out_customs = "";
	private String in_customs = "";
	private String passorttype = "";
	private List<EntityPassport> userpassport = null;
	private Integer judge = null;// 判断是否为“办理护照”则审批中信息不填写
	private ResignEvent revent = null;

	public ResignEvent getRevent() {
		return revent;
	}

	public void setJudge(Integer judge) {
		this.judge = judge;
	}

	public Integer getJudge() {
		return judge;
	}

	public List<EntityPassport> getUserpassport() {
		return userpassport;
	}

	public void setPassorttype(String passorttype) {
		this.passorttype = passorttype;
	}

	public void setAbroad_time(Date abroad_time) {
		this.abroad_time = abroad_time;
	}

	public void setAbroad_back(Date abroad_back) {
		this.abroad_back = abroad_back;
	}

	public void setOut_customs(String out_customs) {
		this.out_customs = out_customs;
	}

	public void setIn_customs(String in_customs) {
		this.in_customs = in_customs;
	}

	public void setP_chk(String p_chk) {
		this.p_chk = p_chk;
	}

	public List<BurnFile> getApproveFileList() {
		return approveFileList;
	}

	public void setAdd_file(String add_file) {
		this.add_file = add_file;
	}

	public UserAbroadEvent getEvent() {
		return event;
	}

	public BmRealUser getApplyinfo() {
		return applyinfo;
	}

	public SecUser getApplyuser() {
		return applyuser;
	}

	public Integer getListSize() {
		return listSize;
	}

	public void setListSize(Integer listSize) {
		this.listSize = listSize;
	}

	public String getOpinion_all() {
		return opinion_all;
	}

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public ProcessJob getJob() {
		return job;
	}

	public List<UserAbroadEvent> getEventList() {
		return eventList;
	}

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	public List<ApproverUser> getUserList() {
		return userList;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion + " ";
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver.replaceAll(" ", "");
	}

	public String getType() {
		return basicService.getJobTypeCodeByJobCode(job_code);
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	void getApprInfo(UserAbroadEvent event1) {
		listSize = recordList.size() - 1;
		for (int i = 1; i <= listSize; i++) {
			opinion_all = opinion_all + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion() + "<br>审批人："
					+ recordList.get(i).getUser_name() + "<br>审批时间：" + recordList.get(i).getOp_time_str();
		}

		Map<String, Object> mm = new HashMap<String, Object>();
		mm.put("real_user_id", event1.getUser_iidd());
		mm.put("info_type", 2);
		List<BmRealUser> usertemp = securityUserService.getUserInfoByUserIdAndInfoType(mm);
		if (usertemp.size() > 0) {
			applyinfo = usertemp.get(0);
			// 判断申请人状态是否为“离岗”状态，若是则展示其脱密期
			if (applyinfo.getUser_statue() == 3) {
				Map<String, Object> remap = new HashMap<String, Object>();
				remap.put("resign_user_iidd", event1.getUser_iidd());
				List<ResignEvent> resign_event = securityUserService.getUResignEventList(remap);
				if (resign_event.size() != 0) {
					revent = resign_event.get(0);
				}
			}
		} else {
			applyinfo = null;
		}
		applyuser = userService.getSecUserByUid(event1.getUser_iidd());
		// 用户护照信息
		mm.put("duty_user_iidd", event1.getUser_iidd());
		userpassport = passportService.getAllPassportList(mm);
		if (event1.getReason().contains("办理")) {
			judge = 1;
		}
		// 审批时上传的文件
		String[] approvefilelist = event.getApprove_file_list().split(CCLCMConstants.DEVIDE_SYMBOL);
		if (approvefilelist.length > 0 && StringUtils.hasLength(approvefilelist[0])) {
			String storePath = "";
			try {
				storePath = BMPropertyUtil.getSecManageStrorePath();
			} catch (Exception e) {
				e.printStackTrace();
			}
			approveFileList = new ArrayList<BurnFile>();
			String file_path = "";
			for (int i = 0; i < approvefilelist.length; i++) {
				file_path = storePath + "/" + event.getEvent_code() + "/" + "approve" + "/" + approvefilelist[i];
				approveFileList.add(new BurnFile(approvefilelist[i], file_path));
			}
		}
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
		if (history.equals("Y")) {
			job = basicService.getProcessJobByCode(job_code);
			process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
			ProcessRecord record = new ProcessRecord();
			record.setJob_code(job_code);
			recordList = activitiService.getProcessRecordList(record);
			eventList = securityUserService.getUAbroadEventListByJobCode(job_code);
			event = eventList.get(0);
			getApprInfo(event);

			return SUCCESS;
		} else {
			if (StringUtils.hasLength(approved)) {// 审批结果
				eventList = securityUserService.getUAbroadEventListByJobCode(job_code);
				event = eventList.get(0);
				if (listSize == 6) {
					String other_opinion = "";
					Map<String, Object> map = new HashMap<String, Object>();

					if (p_chk.equalsIgnoreCase("yes")) {
						other_opinion = "护照：已交回";
						map.put("passport_status", "0");
						map.put("duty_user_iidd", event.getUser_iidd());
						passportService.updatePassportByUserId(map);
					} else if (p_chk.equalsIgnoreCase("no")) {
						other_opinion = "护照：未交回";
					} else {
						other_opinion = "接收新护照";
					}
					opinion = opinion + "<br>" + other_opinion;
				} else if (listSize == 5 && judge == null) {
					String other_opinion1 = "";
					SimpleDateFormat t1 = new SimpleDateFormat("yyyy-MM-dd");
					String time1 = t1.format(abroad_time);
					SimpleDateFormat t2 = new SimpleDateFormat("yyyy-MM-dd");
					String time2 = t2.format(abroad_back);
					other_opinion1 = "出国时间：" + time1 + "  回国时间：" + time2 + "<br>出境海关：" + out_customs + "  入境海关："
							+ in_customs;
					opinion = opinion + "<br>" + other_opinion1;
					Map<String, Object> mmp = new HashMap<String, Object>();
					mmp.put("event_code", event.getEvent_code());
					mmp.put("abroad_time", abroad_time);
					mmp.put("abroad_back", abroad_back);
					mmp.put("out_customs", out_customs);
					mmp.put("in_customs", in_customs);
					securityUserService.updateUserAbroadConfirmInfo(mmp);
				} else if (listSize == 4) {
					String other_opinion1 = "";
					// 更新护照状态为“已借出”,更新审批用户选择的护照类型
					if (!passorttype.equals("")) {
						String[] types = passorttype.split(",");
						for (int i = 0; i < types.length; i++) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("passport_status", "1");
							map.put("passport_type", types[i]);
							map.put("duty_user_iidd", event.getUser_iidd());
							passportService.updatePassportByUserId(map);
							if (types[i].trim().equals("1")) {
								other_opinion1 += "外交护照" + ",";
							} else if (types[i].trim().equals("2")) {
								other_opinion1 += "公务护照" + ",";
							} else if (types[i].trim().equals("3")) {
								other_opinion1 += "港澳通行证" + ",";
							} else if (types[i].trim().equals("4")) {
								other_opinion1 += "大陆居民来往台湾地区通行证" + ",";
							} else if (types[i].trim().equals("0")) {
								other_opinion1 += "普通护照" + ",";
							}
						}
						opinion = opinion + "<br>" + " 护照类型：" + other_opinion1;
					}
				}
				if (add_file.equals("Y")) {
					// 使用定义上传附件类
					file = new FileListEvent();
					handleFileList(event.getEvent_code(), file, "approve");

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("event_code", event.getEvent_code());
					map.put("file_list", file.getFile_list());
					map.put("file_num", file.getFile_num());
					securityUserService.addApproveAboradFile(map);
				}
				String next_approver_name = basicService.getApproverName(next_approver);
				ApproverUser user = new ApproverUser(getCurUser().getUser_iidd(), getCurUser().getUser_name());
				ApproverUser approver = new ApproverUser(next_approver, next_approver_name);
				securityUserService.extendApproveJob(job_code, user, approver, approved, opinion, "");
				insertCommonLog("审批用户因私出国任务，审批单编号[" + job_code + "]");
				return "ok";
			} else {
				job = basicService.getProcessJobByCode(job_code);
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
				eventList = securityUserService.getUAbroadEventListByJobCode(job_code);
				event = eventList.get(0);
				getApprInfo(event);

				String usage_code = eventList.get(0).getUsage_code();
				process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
				try {
					List<ApproverUser> oriList = basicService.getNextApprover(job_code, job.getDept_id(),
							job.getSeclv_code(), job.getJobType().getJobTypeCode(), usage_code);
					userList = removeDuplicateList(oriList);

					// 流程需要申请人（普通用户角色）确认,声光电BM，
					Map<String, Object> variables = basicPrcManage.getProcessVariables(job.getInstance_id());
					Integer cur_approval = (Integer) variables.get("cur_approval");
					if (process.getTotal_steps() > cur_approval) {
						String[] roles = process.getStep_role().split("#");
						String role_ids = roles[cur_approval];
						// 如果审批人角色有且只有普通用户，则视为申请用户确认
						if (role_ids.equals("11")) {
							String user_iidd = getCurUser().getUser_iidd();
							String user_name = getCurUser().getUser_name();
							if (job != null) {
								user_iidd = job.getUser_iidd();
								user_name = job.getUser_name();
							}
							ApproverUser applyUser = new ApproverUser(user_iidd, user_name);
							userList.removeAll(userList);
							userList.add(applyUser);
							return SUCCESS;
						}
					}

					if (!basicService.isSelfApprove()) {// 不允许自审批
						if ((userList != null) && (userList.size() == 1)
								&& userList.get(0).getUser_iidd().equals(job.getUser_iidd())) {
							throw new Exception("唯一的下级审批人与申请用户相同！由于系统不支持自审批，请先联系管理员添加可审批用户！");
						} else {
							for (ApproverUser user : userList) {
								if (user.getUser_iidd().equals(job.getUser_iidd())) {
									userList.remove(user);
									logger.debug("审批列表中去掉申请用户");
									break;
								}
							}
							for (ApproverUser user : userList) {
								if (user.getUser_iidd().equals(getCurUser().getUser_iidd())) {
									userList.remove(user);
									logger.debug("审批列表中去掉当前审批人");
									break;
								}
							}
						}
					}
					/*
					 * List<ApproverUser> tempList = new ArrayList<ApproverUser>(); for (ApproverUser user : userList) {
					 * List<SecLevel> seclvList = userService.getCopySecLevelByUser(user.getUser_iidd()); for (SecLevel
					 * seclv : seclvList) { if (seclv.getSeclv_code() == job.getSeclv_code()) { tempList.add(user);
					 * break; } } } if (userList.size() > 0 && tempList.size() == 0) { throw new
					 * Exception("下级审批人涉密级别低于审批单密级，请联系管理员"); } userList = tempList;
					 */
				} catch (Exception e) {
					logger.error("Exception:" + e.getMessage());
					if (e.getCause() instanceof TooManyResultsException) {
						logger.error("基于该部门、密级和操作的流程定义重复，请提醒管理员修改");
						throw new Exception("基于该部门、密级和操作的流程定义重复，请提醒管理员修改");
					} else {
						throw e;
					}
				}
				return SUCCESS;
			}
		}

	}
}
