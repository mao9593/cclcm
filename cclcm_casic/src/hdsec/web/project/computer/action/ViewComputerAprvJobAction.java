package hdsec.web.project.computer.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * 计算机管理模块已审批记录 (计算机申请十个类型 ,笔记本借用类型)
 * 
 * @author liuyaling guojiao
 */
public class ViewComputerAprvJobAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private List<ProcessJob> applyList = null;
	private String user_name = "";
	private String user_id = "";
	private Integer seclv_code = null;
	private String type = "";

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	public void setApplyList(List<ProcessJob> applyList) {
		this.applyList = applyList;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String executeFunction() throws Exception {

		List<ProcessJob> tempList = null;
		applyList = new ArrayList<ProcessJob>();
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_INTCOM,
				user_name, seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_SINCOM,
				user_name, seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_CHGCOM,
				user_name, seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_DESCOM,
				user_name, seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_REPCOM,
				user_name, seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_REINSTALL,
				user_name, seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_QUITINT,
				user_name, seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_USBKEY,
				user_name, seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_OPENPORT,
				user_name, seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_LOCALPRINTER,
				user_name, seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BORROW_BOOK, user_name,
				seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BORROW_BOOKOUT,
				user_name, seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BOOK_CHANGE, user_name,
				seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BOOK_REPAIR, user_name,
				seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BOOK_DES, user_name,
				seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BOOK_REINSTALL,
				user_name, seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.QIYUAN_BORROW_BOOK, user_name,
				seclv_code);
		applyList.addAll(tempList);
		
		return SUCCESS;
	}

}