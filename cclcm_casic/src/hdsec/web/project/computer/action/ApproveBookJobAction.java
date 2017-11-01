package hdsec.web.project.computer.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.computer.model.ChangeDeviceEvent;
import hdsec.web.project.computer.model.EntityBook;
import hdsec.web.project.computer.model.EntityEventDevice;
import hdsec.web.project.computer.model.EntityHardDisk;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

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

public class ApproveBookJobAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<ChangeDeviceEvent> eventList = null;
	private String approved = "";
	private String opinion = "";
	private String next_approver = "";
	private List<ApproverUser> userList = null;
	private EntityBook book = null;
	private EntityEventDevice book_old = null;
	private String history = "";
	private Integer listSize = null;
	private String opinion_all = "";
	private ChangeDeviceEvent event = null;
	private String isrecycle = "";
	private String content = "";
	private Date operation_time = null;
	private String operation_user_id = "";
	private String operation_content = "";
	private Date start_t = null;

	public void setOperation_time(Date operation_time) {
		this.operation_time = operation_time;
	}

	public void setOperation_user_id(String operation_user_id) {
		this.operation_user_id = operation_user_id;
	}

	public void setOperation_content(String operation_content) {
		this.operation_content = operation_content;
	}

	public void setStart_t(Date start_t) {
		this.start_t = start_t;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setIsrecycle(String isrecycle) {
		this.isrecycle = isrecycle;
	}

	public EntityBook getBook() {
		return book;
	}

	public EntityEventDevice getBook_old() {
		return book_old;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public ProcessJob getJob() {
		return job;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	public ChangeDeviceEvent getEvent() {
		return event;
	}

	public List<ChangeDeviceEvent> getEventList() {
		return eventList;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getHistory() {
		return history;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public List<ApproverUser> getUserList() {
		return userList;
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

	protected void getBookInfoSpecial(ChangeDeviceEvent event1) {

		listSize = recordList.size() - 1;
		for (int i = 1; i <= listSize; i++) {
			opinion_all = opinion_all + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion() + "<br>审批人："
					+ recordList.get(i).getUser_name() + "<br>审批时间：" + recordList.get(i).getOp_time_str();
		}

		EntityEventDevice event_device_content = computerService
				.getEntityEventDeviceByEventCode(event1.getEvent_code());
		Map<String, Object> mapss = new HashMap<String, Object>();
		mapss.put("book_barcode", event1.getBarcode());
		List<EntityBook> blist = computerService.getBookList(mapss);
		if (blist != null) {
			book = blist.get(0);
		}
		if (event1.getEvent_type().equals(11)) {// 笔记本变更
			book_old = event_device_content;
		} else if (event1.getEvent_type().equals(12)) {// 笔记本维修

		} else if (event1.getEvent_type().equals(13)) {// 笔记本销毁

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
			event = computerService.getChangeDeviceEventByJobCode(job_code);
			getBookInfoSpecial(event);

			if (event.getEvent_type() == 11) {// 笔记本变更
				return "ok_change";
			} else if (event.getEvent_type() == 12) {// 笔记本维修
				return "ok_repair";
			} else if (event.getEvent_type() == 13) {// 笔记本报废
				return "ok_des";
			} else if (event.getEvent_type() == 14) {// 笔记本重装系统
				return "ok_install";
			} else {
				return SUCCESS;
			}
		} else {
			if (StringUtils.hasLength(approved)) {// 审批结果
				event = computerService.getChangeDeviceEventByJobCode(job_code);
				SimpleDateFormat t1 = new SimpleDateFormat("yyyy-MM-dd");

				if (event.getEvent_type().equals(12)) {// 笔记本维修

					if (listSize == 3) {
						Map<String, Object> computerrepair = new HashMap<String, Object>();
						computerrepair.put("event_code", event.getEvent_code());
						computerrepair.put("operation_time", operation_time);
						computerrepair.put("operation_user_id", operation_user_id);
						computerrepair.put("operation_content", operation_content);
						computerrepair.put("start_time", start_t);

						computerService.addDeviceOperationByEventCode(computerrepair);
						String time1 = "";
						String time2 = "";
						if (operation_time != null) {
							time1 = t1.format(operation_time);
						}
						if (start_t != null) {
							time2 = t1.format(start_t);
						}
						String[] splOperation_content = operation_content.split(",");
						String opinion_history = "是否更换硬盘:" + splOperation_content[0] + "&nbsp;&nbsp;新硬盘序列号:"
								+ splOperation_content[1] + "<br>是否增加新硬盘:" + splOperation_content[2]
								+ "&nbsp;&nbsp;新硬盘序列号:" + splOperation_content[3] + "<br>是否更换其他部件:"
								+ splOperation_content[4] + "&nbsp;&nbsp;部件名称:" + splOperation_content[5]
								+ "<br>是否增加内存:" + splOperation_content[6] + "&nbsp;&nbsp;具体描述:"
								+ splOperation_content[7] + "<br>是否重装操作系统:" + splOperation_content[8]
								+ "&nbsp;&nbsp;具体描述:" + splOperation_content[9] + "<br>解决办法:"
								+ splOperation_content[10] + "&nbsp;&nbsp;维修地点:" + splOperation_content[11]
								+ "<br>维修时间：" + time1 + "<br>操作系统时间：" + time2 + "<br>维修人：" + operation_user_id;
						opinion = opinion + "<br>" + opinion_history;
					} else if (listSize == 4) {// 保密处回收硬盘
						String other_opinion = "";
						if (isrecycle.equals("是")) {
							String newhdisk = "";
							Map<String, Object> map1 = new HashMap<String, Object>();
							map1.put("book_barcode", event.getBarcode());
							List<EntityBook> blist = computerService.getBookList(map1);
							if (blist != null) {
								book = blist.get(0);
								if (book.getHdisk_no().trim().compareTo(content.trim()) != 0) {
									// 修改硬盘序列号则将该字段更新到硬盘表的备注中，添加到硬盘表中
									newhdisk = content.trim();
								}
							}

							EntityHardDisk disk = new EntityHardDisk("laptop", book.getBook_barcode(),
									book.getConf_code(), book.getHdisk_no(), book.getDuty_user_id(),
									book.getDuty_dept_id(), book.getDuty_entp(), getCurUser().getUser_iidd(),
									new Date(), newhdisk, event.getJob_code(), 1);
							computerService.addEntityHardDisk(disk);// 将硬盘相关信息更新到硬盘台账中

							other_opinion = "回收硬盘：硬盘序列号为：" + content.trim();
						} else {
							// 不回收硬盘，则仅作为展示即可
							other_opinion = "不回收硬盘";
						}
						opinion = opinion + "<br>" + other_opinion;
					}
				} else if (event.getEvent_type().equals(13)) {// 笔记本报废
					if (listSize == 3) {
						String other_opinion = "";
						if (isrecycle.equals("是")) {
							Map<String, Object> map1 = new HashMap<String, Object>();
							String newhdisk = "";
							map1.put("book_barcode", event.getBarcode());
							List<EntityBook> blist = computerService.getBookList(map1);
							if (blist != null) {
								book = blist.get(0);
								if (book.getHdisk_no().trim().compareTo(content.trim()) != 0) {
									// 修改硬盘序列号则将该字段更新到硬盘表的备注中，添加到硬盘表中
									newhdisk = content.trim();
								}
							}

							EntityHardDisk disk = new EntityHardDisk("laptop", book.getBook_barcode(),
									book.getConf_code(), book.getHdisk_no(), book.getDuty_user_id(),
									book.getDuty_dept_id(), book.getDuty_entp(), getCurUser().getUser_iidd(),
									new Date(), newhdisk, event.getJob_code(), 1);
							computerService.addEntityHardDisk(disk);// 将硬盘相关信息更新到硬盘台账中

							other_opinion = "回收硬盘：硬盘序列号为：" + content.trim();
						} else {
							// 不回收硬盘，则仅作为展示即可
							other_opinion = "不回收硬盘";
						}
						opinion = opinion + "<br>" + other_opinion;
					}
				} else if (event.getEvent_type().equals(14)) {// 笔记本重装系统
					if (listSize == 3) {
						Map<String, Object> reinstall = new HashMap<String, Object>();
						reinstall.put("event_code", event.getEvent_code());
						reinstall.put("operation_time", operation_time);
						reinstall.put("operation_user_id", operation_user_id);

						computerService.addDeviceOperationByEventCode(reinstall);
						String time1 = "";
						if (operation_time != null) {
							time1 = t1.format(operation_time);
						}

						String other = "操作系统安装时间：" + time1 + "<br>安装人：" + operation_user_id;
						opinion = opinion + "<br>" + other;
					}
				}

				String next_approver_name = basicService.getApproverName(next_approver);
				ApproverUser user = new ApproverUser(getCurUser().getUser_iidd(), getCurUser().getUser_name());
				ApproverUser approver = new ApproverUser(next_approver, next_approver_name);
				securityUserService.extendApproveJob(job_code, user, approver, approved, opinion, "");
				insertCommonLog("审批流程申请任务[" + job_code + "]");
				return "ok";
			} else {
				// 流程及审批信息
				job = basicService.getProcessJobByCode(job_code);
				process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
				event = computerService.getChangeDeviceEventByJobCode(job_code);
				getBookInfoSpecial(event);
				try {
					List<ApproverUser> oriList = basicService.getNextApprover(job_code, job.getDept_id(),
							job.getSeclv_code(), job.getJobType().getJobTypeCode(), "");
					userList = removeDuplicateList(oriList);
					// 流程需要申请人（普通用户角色）确认,声光电BM，G
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

							if (event.getEvent_type() == 11) {// 笔记本变更
								return "ok_change";
							} else if (event.getEvent_type() == 12) {// 笔记本维修
								return "ok_repair";
							} else if (event.getEvent_type() == 13) {// 笔记本报废
								return "ok_des";
							} else if (event.getEvent_type() == 14) {// 笔记本重装系统
								return "ok_install";
							} else {
								return SUCCESS;
							}
						}
					}
					if (!basicService.isSelfApprove()) {// 不允许自审批
						if ((userList != null) && (userList.size() == 1)
								&& userList.get(0).getUser_iidd().equals(job.getUser_iidd())) {
							throw new Exception("唯一的下级审批人与申请用户相同！由于系统不支持自审批，请联系管理员添加可审批用户！");
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

					List<ApproverUser> tempList = new ArrayList<ApproverUser>();
					for (ApproverUser user : userList) {
						List<SecLevel> seclvList = new ArrayList<SecLevel>();
						seclvList = userService.getSecLevel();
						for (SecLevel seclv : seclvList) {
							if (seclv.getSeclv_code() == job.getSeclv_code()) {
								tempList.add(user);
								break;
							}
						}
					}
					if (userList.size() > 0 && tempList.size() == 0) {
						throw new Exception("下级审批人涉密级别低于审批单密级，请联系管理员");
					}
					userList = tempList;
				} catch (Exception e) {
					logger.error("Exception:" + e.getMessage());
					if (e.getCause() instanceof TooManyResultsException) {
						logger.error("基于该部门、密级和操作的流程定义重复，请提醒管理员修改");
						throw new Exception("基于该部门、密级和操作的流程定义重复，请提醒管理员修改");
					} else {
						throw e;
					}
				}

				if (event.getEvent_type() == 11) {// 笔记本变更
					return "ok_change";
				} else if (event.getEvent_type() == 12) {// 笔记本维修
					return "ok_repair";
				} else if (event.getEvent_type() == 13) {// 笔记本报废
					return "ok_des";
				} else if (event.getEvent_type() == 14) {// 笔记本重装系统
					return "ok_install";
				} else {
					return SUCCESS;
				}
			}
		}
	}
}
