package hdsec.web.project.disc.action;

import hdsec.web.project.basic.model.SysPrinter;
import hdsec.web.project.disc.model.EntitySpaceCD;
import hdsec.web.project.user.model.SecLevel;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看空白盘申请记录
 * 
 * @author zp
 * 
 */
public class ManageBorrowSpaceCdListAction extends DiscBaseAction {

	private static final long serialVersionUID = 1L;
	private List<EntitySpaceCD> entityList=null;
	private String barcode = "";
	private Integer seclv_code = null;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";


	public List<EntitySpaceCD> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<EntitySpaceCD> entityList) {
		this.entityList = entityList;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getStartTime() {
		return startTime == null ? "" : sdf.format(startTime);
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime == null ? "" : sdf.format(endTime);
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getJob_status() {
		return job_status;
	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<SysPrinter> getPrinterList() {
		return basicService.getSysPrinterList();
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("barcode", barcode);
		map.put("seclv_code", seclv_code);
		map.put("start_time", startTime);
		map.put("end_time", endTime);
		map.put("job_status", job_status);
		entityList=discService.getSendEntitySpaceCdList(map);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BURN_BORROW", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BURN_BORROW", 3);
		return SUCCESS;
	}

}
