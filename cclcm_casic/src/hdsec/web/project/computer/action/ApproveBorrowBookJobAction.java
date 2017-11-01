package hdsec.web.project.computer.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.common.bm.BMCycleItem;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.computer.model.BorrowBookEvent;
import hdsec.web.project.computer.model.EntityBook;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.util.StringUtils;

public class ApproveBorrowBookJobAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<BorrowBookEvent> eventList = null;
	private List<ApproverUser> userList = null;
	private String approved = "";
	private String opinion = "";
	private String next_approver = "";
	private String history = "";
	private BorrowBookEvent event = null;
	private Integer listSize = null;
	private String opinion_all = "";
	private String name_str[] = null;
	private String judge_str[] = null;
	private String other_str[] = null;
	// 审批表中填写信息字段

	private String other = "";
	private String other_code = "";
	private String check1 = "";
	private String check2 = "";
	private String check3 = "";
	private String check4 = "";
	private String check5 = "";
	private String check6 = "";
	private String check7 = "";
	private String check_info = "";
	private List<EntityBook> deviceList = null;
	private EntityBook device = null;
	private String book_barcode = "";

	public String getBook_barcode() {
		return book_barcode;
	}

	public void setBook_barcode(String book_barcode) {
		this.book_barcode = book_barcode;
	}

	public EntityBook getDevice() {
		return device;
	}

	public void setCheck1(String check1) {
		this.check1 = check1;
	}

	public void setCheck2(String check2) {
		this.check2 = check2;
	}

	public void setCheck3(String check3) {
		this.check3 = check3;
	}

	public void setCheck4(String check4) {
		this.check4 = check4;
	}

	public void setCheck5(String check5) {
		this.check5 = check5;
	}

	public void setCheck6(String check6) {
		this.check6 = check6;
	}

	public void setCheck7(String check7) {
		this.check7 = check7;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public void setOther_code(String other_code) {
		this.other_code = other_code;
	}

	public void setCheck_info(String check_info) {
		this.check_info = check_info;
	}

	public String[] getName_str() {
		return name_str;
	}

	public String[] getJudge_str() {
		return judge_str;
	}

	public String[] getOther_str() {
		return other_str;
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

	public void setOpinion_all(String opinion_all) {
		this.opinion_all = opinion_all;
	}

	public BorrowBookEvent getEvent() {
		return event;
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

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
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

	private void getNextStep(String job_code) {
		Map<String, Object> mbook = new HashMap<String, Object>();
		eventList = computerService.getBorrowBookEventListByJobCode(job_code);
		event = eventList.get(0);
		if (!event.getDevieinfo().equals("")) {
			name_str = event.getDevieinfo().split(",");
		}
		if (!event.getJudge().equals("")) {
			judge_str = event.getJudge().split(",");
		}
		if (!event.getOther_info().equals("")) {
			other_str = event.getOther_info().split(",");
		}
		if (!event.getAssociat_entity().equals("")) {
			mbook.put("book_barcode", event.getAssociat_entity());
			deviceList = computerService.getBookList(mbook);
			if (deviceList.size() != 0) {
				device = deviceList.get(0);
			}
		}
		// 获取临时选择笔记本条码，更新并展示
		if (!book_barcode.equals("")) {
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("associat_entity", book_barcode);
			map2.put("event_code", event.getEvent_code());
			// 更新笔记本作业表中关联的台账条码号
			computerService.updateBorrowBookAssociate(map2);

			map2.put("book_barcode", book_barcode);
			deviceList = computerService.getBookList(map2);
			if (deviceList.size() != 0) {
				device = deviceList.get(0);
			}
		}
		listSize = recordList.size() - 1;
		for (int i = 1; i <= listSize; i++) {
			opinion_all = opinion_all + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion() + "<br>审批人："
					+ recordList.get(i).getUser_name() + "<br>审批时间：" + recordList.get(i).getOp_time_str();
		}
	}

	@Override
	public String executeFunction() throws Exception {
		if (history.equals("Y")) {
			job = basicService.getProcessJobByCode(job_code);
			process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
			ProcessRecord record = new ProcessRecord();
			record.setJob_code(job_code);
			recordList = activitiService.getProcessRecordList(record);
			getNextStep(job_code);

			return SUCCESS;
		} else {
			if (StringUtils.hasLength(approved)) {// 审批结果
				job = basicService.getProcessJobByCode(job_code);
				// 处理审批步骤中填写信息保存
				String other_opinion = "";
				if ((listSize == 1 && job.getJobType().getJobTypeCode() == "BORROW_BOOK")
						|| (listSize == 2 && job.getJobType().getJobTypeCode() == "BORROW_BOOKOUT")) {
					eventList = computerService.getBorrowBookEventListByJobCode(job_code);
					event = eventList.get(0);
					if (!book_barcode.equals("")) {
						// 根据提交时确定的笔记本条码更新（重复确认）
						Map<String, Object> map1 = new HashMap<String, Object>();
						map1.put("associat_entity", book_barcode);
						map1.put("event_code", event.getEvent_code());
						// 更新笔记本作业表中关联的台账条码号
						computerService.updateBorrowBookAssociate(map1);

						// 更新笔记本台账中笔记本状态为“已借出”
						EntityBook book1 = new EntityBook();
						book1.setBook_status(6);
						book1.setBook_barcode(event.getAssociat_entity());
						computerService.updateEntityBook(book1);

						// 更新该台账全生命周期信息，已借出
						BMCycleItem cycleitem = new BMCycleItem();
						cycleitem.setBarcode(event.getAssociat_entity());
						cycleitem.setEntity_type("DEVICE");
						cycleitem.setOper_time(new Date());
						cycleitem.setUser_name(event.getUser_name());
						cycleitem.setDept_name(event.getDept_name());
						cycleitem.setOper("BORROW");
						cycleitem.setJob_code(event.getJob_code());
						securityUserService.addCycleItem(cycleitem);
					}
					if (!other.equals("")) {
						other_opinion = "借用其他设备/介质 ：" + other + "    编号：" + other_code;
						opinion = other_opinion + "<br>" + opinion;
					}
				} else if ((listSize == 5 && job.getJobType().getJobTypeCode() == "BORROW_BOOK")
						|| (listSize == 6 && job.getJobType().getJobTypeCode() == "BORROW_BOOKOUT")) {
					String info[] = check_info.split(",");

					other_opinion = "1、是否连接各种网络:" + check1 + "  核实情况:" + info[0] + "<br>";
					other_opinion = other_opinion + "2、是否连接各种设备:" + check2 + "  核实情况:" + info[1] + "<br>";
					other_opinion = other_opinion + "3、是否使用外来介质:" + check3 + "  核实情况:" + info[2] + "<br>";
					other_opinion = other_opinion + "4、是否安装各种软件:" + check4 + "  核实情况:" + info[3] + "<br>";
					other_opinion = other_opinion + "5、是否涉密数据输出:" + check5 + "  核实情况:" + info[4] + "<br>";
					other_opinion = other_opinion + "6、是否存储涉密信息:" + check6 + "  核实情况:" + info[5] + "<br>";
					other_opinion = other_opinion + "7、是否有特殊需求（如刻录、打印、三合一导入功能）:" + check7 + "  核实情况:" + info[6]
							+ "<br>";
					other_opinion = other_opinion + "8、其他临时" + info[7];
					opinion = other_opinion + "<br>" + opinion;
				}
				String next_approver_name = basicService.getApproverName(next_approver);
				ApproverUser user = new ApproverUser(getCurUser().getUser_iidd(), getCurUser().getUser_name());
				ApproverUser approver = new ApproverUser(next_approver, next_approver_name);
				// 判断审批最后一步已通过更改信息
				securityUserService.extendApproveJob(job_code, user, approver, approved, opinion, "");
				insertCommonLog("审批用户涉密活动任务，审批单编号[" + job_code + "]");
				return "ok";
			} else {
				job = basicService.getProcessJobByCode(job_code);
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
				getNextStep(job_code);

				String usage_code = event.getUsage_code();
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