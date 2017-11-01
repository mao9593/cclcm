package hdsec.web.project.computer.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.computer.model.BorrowBookEvent;
import hdsec.web.project.computer.model.EntityBook;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.util.StringUtils;

public class ApproveQiYuanBorrowBookJobAction extends ComputerBaseAction {

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
	private List<EntityBook> deviceList = null;
	private EntityBook device = null;
	private String book_barcode = "";

	// 七院笔记本借用表单增加刻录文件名字段
	private String discfile_str[] = null;
	private String security_methord_str[] = null;
	private String isBurnAtBook = "否";

	public String getIsBurnAtBook() {
		return isBurnAtBook;
	}

	public void setIsBurnAtBook(String isBurnAtBook) {
		this.isBurnAtBook = isBurnAtBook;
	}

	public String[] getSecurity_methord_str() {
		return security_methord_str;
	}

	public void setSecurity_methord_str(String[] security_methord_str) {
		this.security_methord_str = security_methord_str;
	}

	public String[] getDiscfile_str() {
		return discfile_str;
	}

	public void setDiscfile_str(String[] discfile_str) {
		this.discfile_str = discfile_str;
	}

	public String getBook_barcode() {
		return book_barcode;
	}

	public void setBook_barcode(String book_barcode) {
		this.book_barcode = book_barcode;
	}

	public EntityBook getDevice() {
		return device;
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

		// 获取七院笔记本借用-各个刻录文件名及密级
		if (!event.getDisc_file().equals("")) {
			discfile_str = event.getDisc_file().split(",");
		}

		// 获取七院笔记本借用-保密措施
		if (!event.getSecurity_methord().equals("")) {
			security_methord_str = event.getSecurity_methord().split(",");
		}

		// 判断七院笔记本借用-光盘编号是否为空
		if (!event.getDisc_num().equals("")) {
			isBurnAtBook = "是";
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
				String next_approver_name = basicService.getApproverName(next_approver);
				ApproverUser user = new ApproverUser(getCurUser().getUser_iidd(), getCurUser().getUser_name());
				ApproverUser approver = new ApproverUser(next_approver, next_approver_name);

				// 判断审批最后一步已通过更改信息
				securityUserService.extendApproveJob(job_code, user, approver, approved, opinion, "");
				insertCommonLog("审批用户借用笔记本，审批单编号[" + job_code + "]");
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