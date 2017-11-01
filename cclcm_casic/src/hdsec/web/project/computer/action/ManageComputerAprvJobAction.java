package hdsec.web.project.computer.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;

import java.util.ArrayList;
import java.util.List;

/**
 * 计算机管理模块待审批记录 (计算机申请十个类型 ,笔记本借用类型)
 * 
 * @author liuyaling guojiao
 */
public class ManageComputerAprvJobAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private List<ProcessJob> candidateList = null;
	private List<ProcessJob> applyList = null;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_INTCOM);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_SINCOM);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_CHGCOM);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_DESCOM);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_REPCOM);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_REINSTALL);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_QUITINT);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_USBKEY);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_OPENPORT);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(),
				JobTypeEnum.EVENT_LOCALPRINTER);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BORROW_BOOK);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BORROW_BOOKOUT);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BOOK_CHANGE);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BOOK_REPAIR);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BOOK_DES);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BOOK_REINSTALL);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.QIYUAN_BORROW_BOOK);
		applyList.addAll(candidateList);
		
		
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REPCOM", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REINSTALL", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_QUITINT", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_USBKEY", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_OPENPORT", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_LOCALPRINTER", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_INTCOM", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_SINCOM", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_CHGCOM", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_DESCOM", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BORROW_BOOK", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BORROW_BOOKOUT", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BOOK_CHANGE", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BOOK_REPAIR", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BOOK_DES", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BOOK_REINSTALL", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "QIYUAN_BORROW_BOOK", 1);  //七院借用笔记本定制表单
		
		return SUCCESS;
	}

}