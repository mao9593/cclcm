package hdsec.web.project.print.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.print.model.PrintEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.List;

/**
 * 修改打印作业草稿
 * 
 * @author renmingfei
 * 
 */
public class UpdatePrintDraftAction extends PrintBaseAction {
	private static final long serialVersionUID = 1L;
	private String id = "";
	private PrintEvent event = null;
	private String update = "N";
	private Integer seclv_code = null;
	private String file_title = "";
	private String usage_code = "";
	private String project_code = "";
	private String place = "";
	private Integer print_count = null;
	private String page_size = "";
	private Integer color = null;
	private Integer print_double = null;
	private Integer print_collate = null;
	private String summ = "";
	private String cycle_type;
	private int print_type = 1;
	private int part_num = 0;
	
	public void setUpdate(String update) {
		this.update = update;
	}
	
	public PrintEvent getEvent() {
		return event;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}
	
	public void setFile_title(String file_title) {
		this.file_title = file_title;
	}
	
	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}
	
	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}
	
	public void setPlace(String place) {
		this.place = place;
	}
	
	public void setPrint_count(Integer print_count) {
		this.print_count = print_count;
	}
	
	public void setPage_size(String page_size) {
		this.page_size = page_size;
	}
	
	public void setColor(Integer color) {
		this.color = color;
	}
	
	public void setPrint_double(Integer print_double) {
		this.print_double = print_double;
	}
	
	public void setPrint_collate(Integer print_collate) {
		this.print_collate = print_collate;
	}
	
	public void setSumm(String summ) {
		this.summ = summ;
	}
	
	public List<SecLevel> getSeclvList() {
		return userService.getPrintSecLevelByUser(getCurUser().getUser_iidd());
	}
	
	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageList();
	}
	
	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("N")) {
			event = printService.getPrintEventByPrintId(id);
			return SUCCESS;
		} else {
			if (null != file_title && print_type == 2) {
				StringBuffer sb = new StringBuffer();
				String[] names = file_title.split(",");
				part_num = names.length;
				for (String name : names) {
					sb.append(name).append(Constants.COMMON_SEPARATOR);
				}
				file_title = sb.toString().substring(0, sb.length() - 1);
			}
			event = new PrintEvent(JobTypeEnum.PRINT_REMAIN, id, seclv_code, file_title, usage_code, project_code,
					place, print_count, page_size, color, print_double, print_collate, summ, cycle_type, print_type,
					part_num);
			if (update.equalsIgnoreCase("Y")) {
				String event_code = getCurUser().getUser_iidd() + System.currentTimeMillis();
				event.setEvent_code(event_code);
			}
			printService.updatePrintEvent(event);
			insertCommonLog("修改打印草稿[" + file_title + "]");
			return "ok";
		}
	}
	
	public String getCycle_type() {
		return cycle_type;
	}
	
	public void setCycle_type(String cycle_type) {
		this.cycle_type = cycle_type;
	}
	
	public int getPrint_type() {
		return print_type;
	}
	
	public void setPrint_type(int print_type) {
		this.print_type = print_type;
	}
	
	public int getPart_num() {
		return part_num;
	}
	
	public void setPart_num(int part_num) {
		this.part_num = part_num;
	}
}
