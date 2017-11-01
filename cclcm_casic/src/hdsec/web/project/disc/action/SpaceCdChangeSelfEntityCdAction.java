package hdsec.web.project.disc.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hdsec.web.project.basic.model.SysConfigItem;
import hdsec.web.project.basic.model.SysSeclevel;
import hdsec.web.project.common.util.TimeUtil;
import hdsec.web.project.disc.model.EntitySpaceCD;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.user.model.SecLevel;

/**
 * 空白盘一键转换
 * 
 * @author zp
 */
public class SpaceCdChangeSelfEntityCdAction extends DiscBaseAction {
	private static final long serialVersionUID = 1L;
	private String id = null;
	private EntitySpaceCD spaceCD = null;
	private String update = "N";
	private Integer seclv_code;
	private String file_list;
	private String comment;
	
	

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getFile_list() {
		return file_list;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EntitySpaceCD getSpaceCD() {
		return spaceCD;
	}

	public void setSpaceCD(EntitySpaceCD spaceCD) {
		this.spaceCD = spaceCD;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equals("Y")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("file_list", file_list);
			map.put("comment", comment);
			map.put("id", id);
			discService.updateSpaceCdChangeSelf(map);
			// 生成载体生命周期记录
			spaceCD=discService.getEntitySpaceCdById(id);
			CycleItem cycleitem = new CycleItem();
			cycleitem.setBarcode(spaceCD.getBarcode());
			cycleitem.setEntity_type("CD");
			cycleitem.setOper_time(new Date());
			cycleitem.setUser_name(getCurUser().getUser_name());
			cycleitem.setDept_name(getCurUser().getDept_name());
			cycleitem.setOper("LEADINSPACECD");
			discService.addCycleItem(cycleitem);
			
			String user_iidd=getCurUser().getUser_iidd();
			String user_name=getCurUser().getUser_name();
			String dept_id=getCurUser().getDept_id();
			String dept_name=getCurUser().getDept_name();
			EntityCD entityCD=new EntityCD(spaceCD.getBarcode(), spaceCD.getEvent_code(), user_iidd, user_name, dept_id, dept_name, user_iidd,
					user_name, dept_id, dept_name, spaceCD.getSeclv_code(), "", new Date(), "", "", "", "", null,
					file_list, null, "N", "1", null, "", 0, "", "CHANGESPACECD", "PERSON", "",
					"", "", "", null, null, "", "", "", null, "", "", spaceCD.getJob_code(), null, "", "", "",
					"");
			entityCD.setCd_type(spaceCD.getCd_type());
			entityCD.setFile_num(file_list.split("[|]").length);
			
			SysSeclevel sysSeclevel = basicService.getSysSecLevelByCode(entityCD.getSeclv_code());
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("archive_time", sysSeclevel.getArchive_time());
			maps.put("period","L");
			maps.put("expire_time", TimeUtil.getAfterXDay(sysSeclevel.getArchive_time()));
			SysConfigItem sysconfig = basicService.getSysConfigItemValue("IMPORT_SHORT_DAYS");
			maps.put("expire_time_short", TimeUtil.getAfterXDay(Integer.parseInt(sysconfig.getItem_value())));
			discService.addCDledger(entityCD);
				if (String.valueOf(maps.get("period")).equals("L")) {
					if (maps.get("archive_time") != null && !String.valueOf(maps.get("archive_time")).equals("0")) {
						// 更新载体提醒回收时间
						Map<String, Object> map_update = new HashMap<String, Object>();
						map_update.put("cd_barcode", entityCD.getCd_barcode());
						map_update.put("expire_time", maps.get("expire_time"));
						basicService.updateCDExpireTime(map_update);
					}
				} else if (String.valueOf(maps.get("period")).equals("S")) {
					// 更新载体提醒回收时间
					Map<String, Object> map_update = new HashMap<String, Object>();
					map_update.put("cd_barcode", entityCD.getCd_barcode());
					map_update.put("expire_time", maps.get("expire_time_short"));
					basicService.updateCDExpireTime(map_update);
				}
			
			
			return "ok";
		} else {
			spaceCD=discService.getEntitySpaceCdById(id);
			return SUCCESS;
		}
	}
	
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}
}
