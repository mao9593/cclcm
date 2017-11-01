package hdsec.web.project.ledger.action;

import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.EventCarryOut;
import hdsec.web.project.user.model.SecUser;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 确认带回载体
 * 
 * @author fyp
 */
public class HandleCarryInConfirmJobAction extends LedgerBaseAction {

	private static final long serialVersionUID = 1L;
	private String updateFlag = "N";
	private EventCarryOut eventCarryOut;
	private SecUser secUser;
	private String entity_type;
	private String job_Code;
	private String barcode;
	private String send_way = ""; // 外发方式 0:专人携带; 1:发机要
	private String output_confidential_num = ""; // 外发(带)机要号

	public String getOutput_confidential_num() {
		return output_confidential_num;
	}

	public void setOutput_confidential_num(String output_confidential_num) {
		this.output_confidential_num = output_confidential_num;
	}

	public String getSend_way() {
		return send_way;
	}

	public void setSend_way(String send_way) {
		this.send_way = send_way;
	}

	public String getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}

	public String getJob_Code() {
		return job_Code;
	}

	public void setJob_Code(String job_Code) {
		this.job_Code = job_Code;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	private String carryinInfo;// 带回说明
	private Integer id;

	public SecUser getSecUser() {
		return secUser;
	}

	public void setSecUser(SecUser secUser) {
		this.secUser = secUser;
	}

	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}

	public EventCarryOut getEventCarryOut() {
		return eventCarryOut;
	}

	public void setEventCarryOut(EventCarryOut eventCarryOut) {
		this.eventCarryOut = eventCarryOut;
	}

	public String getCarryinInfo() {
		return carryinInfo;
	}

	public void setCarryinInfo(String carryinInfo) {
		this.carryinInfo = carryinInfo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String executeFunction() throws Exception {
		if (updateFlag.equals("Y")) {
			ProcessJob processJob = basicService.getProcessJobByCode(job_Code);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("carryinInfo", carryinInfo);
			map.put("carryout_Status", 1);
			map.put("finish_Time", new Date());
			map.put("output_confidential_num", output_confidential_num);
			ledgerService.updateEventCarryOutCarryoutStatusById(map);

			// 更新entity_paper或entity_cd表外带机要号及载体状态
			// if (send_way.trim().equals("1")) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("id", id);
			map1.put("output_confidential_num", output_confidential_num);
			if (entity_type.equals("Paper")) {
				ledgerService.updatePaperOutputConfidentialNumByEventCarryoutId(map1);
			} else {
				ledgerService.updateCDOutputConfidentialNumByEventCarryoutId(map1);
			}
			// }
			String user_name = userService.getUserNameByUserId(processJob.getUser_iidd());
			String dept_name = userService.getDeptNameByDeptId(processJob.getDept_id());
			// 全生命周期记录
			CycleItem cycleitem = new CycleItem();
			if (entity_type.equals("Paper")) {
				cycleitem.setEntity_type("PAPER");
			} else {
				cycleitem.setEntity_type("CD");
			}

			cycleitem.setBarcode(barcode);

			cycleitem.setOper_time(new Date());
			cycleitem.setUser_name(user_name);
			cycleitem.setDept_name(dept_name);
			cycleitem.setOper("CARRYIN");
			// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
			// String job_code = getJobCodeByEventCode(event.getEvent_code());
			cycleitem.setJob_code(job_Code);
			// add ending
			ledgerService.addCycleItem(cycleitem);

			if (entity_type.equals("Paper")) {
				return "paper";
			} else {
				return "cd";
			}
		} else {

			eventCarryOut = ledgerService.getEventCarryOutById(id);
			secUser = userService.getSecUserByUid(eventCarryOut.getUser_Iidd());
			return SUCCESS;
		}
	}
}
