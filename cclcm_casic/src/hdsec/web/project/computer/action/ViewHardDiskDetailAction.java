package hdsec.web.project.computer.action;

import hdsec.web.project.computer.model.EntityHardDisk;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

public class ViewHardDiskDetailAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private EntityHardDisk hdisk = null;
	private String hdisk_no = "";
	private String type = "";

	public String getType() {
		return type;
	}

	public EntityHardDisk getHdisk() {
		return hdisk;
	}

	public void setHdisk(EntityHardDisk hdisk) {
		this.hdisk = hdisk;
	}

	public String getHdisk_no() {
		return hdisk_no;
	}

	public void setHdisk_no(String hdisk_no) {
		this.hdisk_no = hdisk_no;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(hdisk_no)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("hdisk_no", hdisk_no);
			hdisk = computerService.getHardDiskByMap(map);
			if (hdisk.getApprove_url().contains("EVENT_REPCOM")) {
				type = "infosystem";
			}
			return SUCCESS;
		} else {
			throw new Exception("无法查询条码号，请重新尝试。");
		}
	}
}