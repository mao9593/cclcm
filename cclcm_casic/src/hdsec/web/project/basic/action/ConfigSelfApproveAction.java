package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysConfigItem;

/**
 * 交接确认模块配置
 * 
 * @author lixiang
 * 
 */
public class ConfigSelfApproveAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private Integer start = 0;
	private String update = "N";
	private String type = "";
	
	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String executeFunction() throws Exception {
		if(update.equalsIgnoreCase("Y")){
			if(type.equalsIgnoreCase("open")){
				basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_SELF_APPROVE, 
						SysConfigItem.NAME_SELF_APPROVE, null, SysConfigItem.TYPE_APPROVE, 1));
			}
			if(type.equalsIgnoreCase("close")){
				basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_SELF_APPROVE, 
						SysConfigItem.NAME_SELF_APPROVE, null, SysConfigItem.TYPE_APPROVE, 0));
			}
		
		}else{
			start = basicService.getSysConfigItemValue(SysConfigItem.KEY_SELF_APPROVE).getStartuse();
		}
		return SUCCESS;
	}
}
