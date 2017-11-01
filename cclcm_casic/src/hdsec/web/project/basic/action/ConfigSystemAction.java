package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysConfigItem;

public class ConfigSystemAction extends BasicBaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2092668640968517702L;
	private Integer maxChangeResultDays = null;
	private String done = "N";
	
	public String getDone() {
		return done;
	}

	public void setDone(String done) {
		this.done = done;
	}

	public Integer getMaxChangeResultDays() {
		return maxChangeResultDays;
	}

	public void setMaxChangeResultDays(Integer maxChangeResultDays) {
		this.maxChangeResultDays = maxChangeResultDays;
	}

	/**配置标记失败最长天数
	 * @author yaochuanqi
	 */
	@Override
	public String executeFunction() throws Exception {
		SysConfigItem maxChangeResult = basicService.getSysConfigItemValue(SysConfigItem.KEY_MAX_CHANGE_RESULT_DAYS);
		if(maxChangeResultDays!=null){
			SysConfigItem sysconfig = new SysConfigItem(SysConfigItem.KEY_MAX_CHANGE_RESULT_DAYS,
					SysConfigItem.NAME_MAX_CHANGE_RESULT_DAYS, maxChangeResultDays.toString(), SysConfigItem.TYPE_SYSTEM_CONFIG, 1);
			if(maxChangeResult==null){
				basicService.addSysConfigItem(sysconfig);				
			}
			else{
				basicService.updateSysConfigItem(sysconfig);
			}
			done="Y";
		}
		else{//只查询不设置
			if(maxChangeResult==null){
				maxChangeResultDays = 200;//默认允许标记失败时间为200天								
			}
			else{
				maxChangeResultDays = Integer.parseInt(maxChangeResult.getItem_value());
			}
		}
		return SUCCESS;
	
	}
}
