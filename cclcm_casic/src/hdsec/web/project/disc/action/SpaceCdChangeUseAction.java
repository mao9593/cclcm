package hdsec.web.project.disc.action;

import hdsec.web.project.disc.model.EntitySpaceCD;
import hdsec.web.project.user.model.SecLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 空白盘中转使用
 * 
 * @author zp
 */
public class SpaceCdChangeUseAction extends DiscBaseAction {
	private static final long serialVersionUID = 1L;
	private String id = null;
	private EntitySpaceCD spaceCD = null;
	private String update = "N";
	private Integer seclv_code;
	private String file_list;
	private String comment;
	private String url_type = "N";

	public String getUrl_type() {
		return url_type;
	}

	public void setUrl_type(String url_type) {
		this.url_type = url_type;
	}

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

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equals("Y")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("seclv_code", seclv_code);
			map.put("file_list", file_list);
			map.put("comment", comment);
			map.put("duty_user_iidd", getCurUser().getUser_iidd());
			map.put("duty_user_name", getCurUser().getUser_name());
			map.put("duty_dept_id", getCurUser().getDept_id());
			map.put("duty_dept_name", getCurUser().getDept_name());
			map.put("id", id);
			discService.updateSpaceCdById(map);
			if (url_type.equals("Y")) {
				return "ok";
			} else {
				return "yes";
			}
		} else {
			spaceCD = discService.getEntitySpaceCdById(id);
			return SUCCESS;
		}
	}

}
