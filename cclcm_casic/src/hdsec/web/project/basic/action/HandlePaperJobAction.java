package hdsec.web.project.basic.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysSeclevel;
import hdsec.web.project.ledger.action.ReplacePageRetrieveAction;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.SecUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;

/**
 * 纸质台帐处理申请
 * 
 * @author renmingfei
 * 
 */
public class HandlePaperJobAction extends BasicBaseAction {

	private static final long serialVersionUID = 1L;
	private String _chk = "";
	private String addjob = "N";
	private String isDept = "N";
	private String next_approver = "";
	private JobTypeEnum jobType = null;
	private SecLevel seclv = null;
	private Integer seclv_code = null;
	private ApproveProcess process = null;
	private List<ApproverUser> userList = null;
	private List<EntityPaper> paperList = null;
	private String output_dept_name = "";
	private String output_user_name = "";
	private String paper_barcodes = "";
	private Integer delay_days = null;
	private String checkResult = "";
	private String cyc_remarks = "";
	private String reason = "";// 录入文件错误发起流程填写原因
	private String supervise_user_iidd = "";
	private String supervise_user_name = "";
	private String time_flag = ""; // 用于标识时间属性,科研工作手册借用台账查看详情时(time_flag="USETIME")为"启用时间",其他为"制作时间"
	private String replacePageFlag = "";// 替换页申请监销标识
	private Integer paper_id = null;
	private String retrieve_pagenum = "";
	private String fail_remark = "";
	private List<SecUser> output_undertakerList = null;
	private String send_way = "";
	private String carryout_user_iidds = ""; // 携带人(1个或多个携带人)
	private String carryout_user_names = "";
	private String output_undertaker = "";
	private Integer page_all = 0;// 台账合并需默认展示台账的总页数和

	public Integer getPage_all() {
		return page_all;
	}

	public void setPage_all(Integer page_all) {
		this.page_all = page_all;
	}

	public String getSend_way() {
		return send_way;
	}

	public void setSend_way(String send_way) {
		this.send_way = send_way;
	}

	public String getCarryout_user_names() {
		return carryout_user_names;
	}

	public void setCarryout_user_names(String carryout_user_names) {
		this.carryout_user_names = carryout_user_names;
	}

	public String getCarryout_user_iidds() {
		return carryout_user_iidds;
	}

	public void setCarryout_user_iidds(String carryout_user_iidds) {
		this.carryout_user_iidds = carryout_user_iidds;
	}

	public String getOutput_undertaker() {
		return output_undertaker;
	}

	public void setOutput_undertaker(String output_undertaker) {
		this.output_undertaker = output_undertaker;
	}

	// 获取外发承办人列表(仅部门文件管理员)
	public List<SecUser> getOutput_undertakerList() {
		String parent_dept_id = basicService.getParentDeptIdByCurrentId(getCurUser().getDept_id());
		Map<String, Object> map0 = new HashMap<String, Object>();
		map0.put("dept_id", getCurUser().getDept_id());
		map0.put("parent_dept_id", parent_dept_id);
		output_undertakerList = basicService.getOutputUndertakersByDeptId(map0);
		// 选择自己，若为自己，则外发确认或接收在 个人台账->外发申请记录中
		// output_undertakerList.add(getCurUser());

		Set<String> set = new HashSet<String>();
		List<SecUser> sulist = new ArrayList<SecUser>();
		for (SecUser item : output_undertakerList) {
			if (set.add(item.getUser_iidd())) {
				sulist.add(item);
			}
		}
		return sulist;
	}

	// 获取监销人列表
	public List<SecUser> getSuperviseUserList() {
		return basicService.getSuperviseUserList();
	}

	public void setPaper_id(Integer paper_id) {
		this.paper_id = paper_id;
	}

	public Integer getPaper_id() {
		return paper_id;
	}

	public void setRetrieve_pagenum(String retrieve_pagenum) {
		this.retrieve_pagenum = retrieve_pagenum;
	}

	public String getRetrieve_pagenum() {
		return retrieve_pagenum;
	}

	public String getFail_remark() {
		return fail_remark;
	}

	public void setFail_remark(String fail_remark) {
		this.fail_remark = fail_remark;
	}

	public void setReplacePageFlag(String replacePageFlag) {
		this.replacePageFlag = replacePageFlag;
	}

	public String getReplacePageFlag() {
		return replacePageFlag;
	}

	public String getTime_flag() {
		return time_flag;
	}

	public void setTime_flag(String time_flag) {
		this.time_flag = time_flag;
	}

	public String getSupervise_user_iidd() {
		return supervise_user_iidd;
	}

	public void setSupervise_user_iidd(String supervise_user_iidd) {
		this.supervise_user_iidd = supervise_user_iidd;
	}

	public String getSupervise_user_name() {
		return supervise_user_name;
	}

	public void setSupervise_user_name(String supervise_user_name) {
		this.supervise_user_name = supervise_user_name;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public List<EntityPaper> getPaperList() {
		return paperList;
	}

	public String getCyc_remarks() {
		return cyc_remarks;
	}

	public void setCyc_remarks(String cyc_remarks) {
		this.cyc_remarks = cyc_remarks;
	}

	@Override
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public void set_chk(String _chk) {
		this._chk = _chk.replaceAll(" ", "");
	}

	public String get_chk() {
		return _chk;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setAddjob(String addjob) {
		this.addjob = addjob;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = JobTypeEnum.valueOf(jobType);
	}

	public SecLevel getSeclv() {
		return seclv;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public List<ApproverUser> getUserList() {
		return userList;
	}

	public void setOutput_dept_name(String output_dept_name) {
		this.output_dept_name = output_dept_name;
	}

	public void setOutput_user_name(String output_user_name) {
		this.output_user_name = output_user_name;
	}

	public String getIsDept() {
		return isDept;
	}

	public void setIsDept(String isDept) {
		this.isDept = isDept;
	}

	public String getPaper_barcodes() {
		return paper_barcodes;
	}

	public void setPaper_barcodes(String paper_barcodes) {
		this.paper_barcodes = paper_barcodes;
	}

	public Integer getDelay_days() {
		return delay_days;
	}

	public void setDelay_days(Integer delay_days) {
		this.delay_days = delay_days;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
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
		isDept = getRequest().getParameter("isDept");
		if (addjob.equalsIgnoreCase("Y")) {
			try {
				SysSeclevel sysSeclevel = basicService.getSysSecLevelByCode(seclv_code);
				if (sysSeclevel.getArchive_time() > 0) {
					// 延期留用期限不为空时（即设置的值）
					if (delay_days != null) {
						String resultInfo = "";
						// 延期留用期限与回收时间期限比较
						if (delay_days > sysSeclevel.getArchive_time()) {
							resultInfo = "延期留用期限不能大于回收时间期限,请重新输入";
							setCheckResult(resultInfo);
							return "ok2";
						} else {
							// 延期留用期限小于回收时间期限，则更新数据库
							String[] barcodes = paper_barcodes.split(",");
							for (String barcode : barcodes) {
								Map<String, Object> map = new HashMap<String, Object>();
								map.put("paper_barcode", barcode);
								map.put("delay_days", delay_days);
								ledgerService.updateDelayDays(map);
							}
						}
					}
				}

				if (replacePageFlag.equalsIgnoreCase("Y")) {
					basicService.handleReplacePaperJob(getCurUser().getUser_iidd(), getCurUser().getDept_id(),
							seclv_code, jobType, next_approver, _chk, output_dept_name, output_user_name, reason,
							supervise_user_iidd);

					EntityPaper entityPaper = ledgerService.getPaperById("" + paper_id);

					Map<String, Object> map = new HashMap<String, Object>();
					if (entityPaper.getRetrieve_pagenum() != null && !entityPaper.getRetrieve_pagenum().equals("")) {
						retrieve_pagenum = entityPaper.getRetrieve_pagenum() + "," + retrieve_pagenum;
					}
					if (entityPaper.getFail_remark() != null && !entityPaper.getFail_remark().equals("")) {
						fail_remark = entityPaper.getFail_remark() + "," + fail_remark;
					}
					map.put("retrieve_pagenum", retrieve_pagenum);
					map.put("fail_remark", fail_remark);
					map.put("paper_id", paper_id);

					// 如果被替换的页码全部被回收，则将该文件回收
					String numStr = new ReplacePageRetrieveAction().getNoRetrievePagenum(entityPaper.getPID_pagenum(),
							retrieve_pagenum);
					if (numStr.equals("")) {
						if (entityPaper.getRetrieve_replace().equalsIgnoreCase("SUB_RETRIEVED-SUB_DESTROYED")
								|| (entityPaper.getDestroy_pagenum() != null && !entityPaper.getDestroy_pagenum()
										.equals(""))) {
							map.put("retrieve_replace", "ALL_RETRIEVED-SUB_DESTROYED");
						} else {
							map.put("retrieve_replace", "ALL_RETRIEVED");
						}
					} else {
						if (entityPaper.getRetrieve_replace().equalsIgnoreCase("SUB_RETRIEVED-SUB_DESTROYED")
								|| (entityPaper.getDestroy_pagenum() != null && !entityPaper.getDestroy_pagenum()
										.equals(""))) {
							map.put("retrieve_replace", "SUB_RETRIEVED-SUB_DESTROYED");
						} else {
							map.put("retrieve_replace", "SUB_RETRIEVED");
						}
					}

					ledgerService.updateRetrievedPage(map);
				} else if (jobType.getJobTypeCode().equals("SEND_PAPER")) { // 外发
					/*
					 * if (paper_barcodes != "") { _chk = paper_barcodes; }
					 */
					// String carryout_dept_id = transferService.getDeptIdByUserId(carryout_user_iidd);
					carryout_user_iidds = carryout_user_iidds.isEmpty() ? "" : carryout_user_iidds.substring(0,
							carryout_user_iidds.length() - 1);
					carryout_user_names = carryout_user_names.isEmpty() ? "" : carryout_user_names.substring(0,
							carryout_user_names.length() - 1);
					basicService.handleSendPaperJob(getCurUser().getUser_iidd(), getCurUser().getDept_id(), seclv_code,
							jobType, next_approver, _chk, output_dept_name, output_user_name, reason, send_way,
							carryout_user_iidds, carryout_user_names, output_undertaker);
				} else {
					basicService.handlePaperJob(getCurUser().getUser_iidd(), getCurUser().getDept_id(), seclv_code,
							jobType, next_approver, _chk, output_dept_name, output_user_name, reason,
							supervise_user_iidd);
				}
				insertCommonLog("提交纸质载体" + jobType.getJobTypeName() + "审批申请：条码号[" + _chk + "].");

			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			if (isDept.equalsIgnoreCase("Y")) {
				return "ok1";
			} else {
				return "ok";
			}

		} else {
			if (StringUtils.hasLength(_chk)) {
				paperList = basicService.getPaperListByBarcodes(_chk);
				Integer tempSeclv = -1;
				Integer highest_seclv = 10000;
				for (EntityPaper item : paperList) {
					SecLevel seclevel = userService.getSecLevelByCode(item.getSeclv_code());
					if (highest_seclv > seclevel.getSeclv_rank()) {
						highest_seclv = seclevel.getSeclv_rank();
						tempSeclv = seclevel.getSeclv_code();
					}

					if (jobType.getJobTypeCode().contains("PAPER_DEL")
							|| jobType.getJobTypeCode().contains("PAPER_MODIFY")) {
						if (!item.getCreate_type().contains("LEADIN")) {
							throw new Exception("文件修改/删除流程申请仅限于【录入】产生的台账，请重新选择台账列表");
						}
					} else if (jobType.getJobTypeCode().contains("MERGE_ENTITY")) {
						if (item.getCreate_type().equals("MERGE_ENTITY")) {
							throw new Exception("合并台账申请仅限提交非已合并台账的载体进行提交，请重新选择台账列表");
						}
						page_all = page_all + item.getPage_count();
					}
				}
				// 查询流程定义，用于展示流程信息
				process = basicService.getApproveProcessByKey(getCurUser().getDept_id(), tempSeclv,
						jobType.getJobTypeCode(), "");
				List<ApproverUser> oriList = basicService.getNextApprover(null, getCurUser().getDept_id(), tempSeclv,
						jobType.getJobTypeCode(), "");
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
			} else {
				throw new Exception("参数错误，没有载体条码号");
			}
			return SUCCESS;
		}
	}
}