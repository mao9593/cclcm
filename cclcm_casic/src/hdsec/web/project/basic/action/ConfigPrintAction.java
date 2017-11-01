package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysConfigItem;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 打印参数设置
 * 
 * @author lixiang
 * 
 */
public class ConfigPrintAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private Integer print_valid_days;
	private Integer repprint_valid_days;
	private Integer repprint_times;
	
	private String print_valid_days_start = "";
	private String repprint_valid_days_start = "";
	private String repprint_times_start = "";
	
	private String update = "N";
	private String done = "N";
	private String printfile_days_array = "";
	private String startuse_array = "";
	
	private List<SysConfigItem> sysConfigItemList = null;
	
	public List<SysConfigItem> getSysConfigItemList() {
		return sysConfigItemList;
	}
	
	public Integer getPrint_valid_days() {
		return print_valid_days;
	}
	
	public void setPrint_valid_days(Integer print_valid_days) {
		this.print_valid_days = print_valid_days;
	}
	
	public Integer getRepprint_valid_days() {
		return repprint_valid_days;
	}
	
	public void setRepprint_valid_days(Integer repprint_valid_days) {
		this.repprint_valid_days = repprint_valid_days;
	}
	
	public Integer getRepprint_times() {
		return repprint_times;
	}
	
	public void setRepprint_times(Integer repprint_times) {
		this.repprint_times = repprint_times;
	}
	
	public String getPrint_valid_days_start() {
		return print_valid_days_start;
	}
	
	public void setPrint_valid_days_start(String print_valid_days_start) {
		this.print_valid_days_start = print_valid_days_start;
	}
	
	public String getRepprint_valid_days_start() {
		return repprint_valid_days_start;
	}
	
	public void setRepprint_valid_days_start(String repprint_valid_days_start) {
		this.repprint_valid_days_start = repprint_valid_days_start;
	}
	
	public String getRepprint_times_start() {
		return repprint_times_start;
	}
	
	public void setRepprint_times_start(String repprint_times_start) {
		this.repprint_times_start = repprint_times_start;
	}
	
	public void setUpdate(String update) {
		this.update = update;
	}
	
	public String getDone() {
		return done;
	}
	
	public String getPrintfile_days_array() {
		return printfile_days_array;
	}
	
	public void setPrintfile_days_array(String printfile_days_array) {
		this.printfile_days_array = printfile_days_array;
	}
	
	public String getStartuse_array() {
		return startuse_array;
	}
	
	public void setStartuse_array(String startuse_array) {
		this.startuse_array = startuse_array;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {// 更新操作
			basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_PRINT_VALID_DAYS,
					SysConfigItem.NAME_PRINT_VALID_DAYS, String.valueOf(print_valid_days), SysConfigItem.TYPE_PRINT,
					print_valid_days_start.equals("1") ? 1 : 0));
			basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_REPPRINT_VALID_DAYS,
					SysConfigItem.NAME_REPPRINT_VALID_DAYS, String.valueOf(repprint_valid_days),
					SysConfigItem.TYPE_PRINT, repprint_valid_days_start.equals("1") ? 1 : 0));
			basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_REPPRINT_TIMES,
					SysConfigItem.NAME_REPPRINT_TIMES, String.valueOf(repprint_times), SysConfigItem.TYPE_PRINT,
					repprint_times_start.equals("1") ? 1 : 0));
			int i = 0;
			for (String printfile_days : printfile_days_array.split(",")) {
				String item_key = printfile_days.split(":")[0];
				String item_name = basicService.getSysConfigItemValue(item_key).getItem_name();
				String item_value = "";
				if(printfile_days.split(":").length == 2){
					item_value = printfile_days.split(":")[1];
				}
				String item_type = SysConfigItem.TYPE_PRINT_SECLV;
				Integer startuse = startuse_array.split(",")[i++].equals("1") ? 1 : 0;
				basicService.updateSysConfigItem(new SysConfigItem(item_key, item_name, item_value, item_type, startuse));
			}
			insertAdminLog("修改打印参数设置");
			done = "Y";
		}
		
		SysConfigItem item_print_valid_days = basicService.getSysConfigItemValue(SysConfigItem.KEY_PRINT_VALID_DAYS);
		SysConfigItem item_repprint_valid_days = basicService
				.getSysConfigItemValue(SysConfigItem.KEY_REPPRINT_VALID_DAYS);
		SysConfigItem item_repprint_times = basicService.getSysConfigItemValue(SysConfigItem.KEY_REPPRINT_TIMES);
		
		print_valid_days = Integer.parseInt(item_print_valid_days.getItem_value());
		print_valid_days_start = item_print_valid_days.getStartuse() == 1 ? "checked" : "";
		repprint_valid_days = Integer.parseInt(item_repprint_valid_days.getItem_value());
		repprint_valid_days_start = item_repprint_valid_days.getStartuse() == 1 ? "checked" : "";
		repprint_times = Integer.parseInt(item_repprint_times.getItem_value());
		repprint_times_start = item_repprint_times.getStartuse() == 1 ? "checked" : "";
		
		List<SecLevel> sec_level_list = userService.getSecLevel();
		Iterator<SecLevel> sec_level_iter = sec_level_list.iterator();
		sysConfigItemList = new ArrayList<SysConfigItem>();
		
		while (sec_level_iter.hasNext()) {
			SecLevel sec_level = sec_level_iter.next();
			SysConfigItem item_printfile_days = basicService.getSysConfigItemValue(SysConfigItem.KEY_PRINTFILE_DAYS_
					+ sec_level.getSeclv_code());
			if (item_printfile_days == null) {
				basicService.addSysConfigItem(new SysConfigItem(SysConfigItem.KEY_PRINTFILE_DAYS_
						+ sec_level.getSeclv_code(), sec_level.getSeclv_name() + SysConfigItem.NAME_PRINTFILE_DAYS,
						null, SysConfigItem.TYPE_PRINT_SECLV, 0));
				item_printfile_days = basicService.getSysConfigItemValue(SysConfigItem.KEY_PRINTFILE_DAYS_
						+ sec_level.getSeclv_code());
				insertAdminLog("添加打印参数设置：" + item_printfile_days.getItem_name());
			}
			
			sysConfigItemList.add(item_printfile_days);
			
		}
		
		return SUCCESS;
	}
}
