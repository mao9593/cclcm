package hdsec.web.project.burn.action;

import hdsec.web.project.common.PropertyUtil;

import java.io.File;

/**
 * 删除刻录作业
 * 
 * @author renmingfei
 * 
 */
public class DelBurnEventAction extends BurnBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String type = "";

	public void setType(String type) {
		this.type = type;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	private void delAttachFiles() throws Exception {
		String storePath = PropertyUtil.getBurnFileStorePath();
		storePath = storePath + File.separator + event_code;
		File path = new File(storePath);
		if (path.isDirectory()) {
			File[] files = path.listFiles();
			for (File f : files) {
				f.delete();
			}
		}
		path.delete();
	}

	@Override
	public String executeFunction() throws Exception {
		delAttachFiles();
		burnService.delBurnEventByBurnCode(event_code);
		insertCommonLog("删除刻录作业[" + event_code + "],删除上传附件");
		return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
	}
}
