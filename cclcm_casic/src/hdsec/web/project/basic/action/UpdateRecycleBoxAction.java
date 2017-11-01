package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysRecycleBox;
import hdsec.web.project.user.model.SecLevel;

import java.util.List;

/**
 * 修改智能回收柜信息
 * @author gaoxm
 *
 */
public class UpdateRecycleBoxAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String box_code = "";//柜子ID
	private String box_name = "";//柜子名称
	private String box_location = "";//所在地点
	private Integer seclv_code = null;//密级
	private Integer box_type = null;//回收柜类型（0纸质、1光盘）
	private Integer current_num = null;//当前介质数量
	private Integer box_status = null;//回收柜状态（0空、1已存、2损坏、3打开）
	private SysRecycleBox recyclebox = null;
	private String update = "N";
	
	public void setBox_code(String box_code) {
		this.box_code = box_code;
	}
	
	public void setBox_name(String box_name) {
		this.box_name = box_name;
	}
	
	public void setBox_location(String box_location) {
		this.box_location = box_location;
	}
	
	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}
	
	public void setBox_type(Integer box_type) {
		this.box_type = box_type;
	}
	
	public void setCurrent_num(Integer current_num) {
		this.current_num = current_num;
	}
	
	public void setBox_status(Integer box_status) {
		this.box_status = box_status;
	}
	
	public void setRecyclebox(SysRecycleBox recyclebox) {
		this.recyclebox = recyclebox;
	}
	
	public SysRecycleBox getRecyclebox() {
		return recyclebox;
	}
	
	public void setUpdate(String update) {
		this.update = update;
	}
	
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("N")) {
			recyclebox = basicService.getRecycleBoxByCode(box_code);
			return SUCCESS;
		} else {
			recyclebox = new SysRecycleBox(box_code, box_name, box_location, seclv_code, box_type, current_num,
					box_status);
			basicService.updateRecycleBox(recyclebox);
			insertAdminLog("修改智能回收柜：编号[" + box_code + "]");
			return "ok";
		}
	}
	
}
