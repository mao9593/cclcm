package hdsec.web.project.input.action;

import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.input.model.InputEvent;

import java.io.File;

import org.springframework.util.StringUtils;

/**
 * 电子文件导入任务撤销
 * 
 * @author guoxh 2016-10-8 18:25:46
 */
public class CancelInputEventAction extends InputBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String type = "";

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String executeFunction() throws Exception {
		// 若撤销电子文件导入申请,则删除提交的电子文件
		InputEvent event = inputService.getInputEventByCode(event_code);
		if (event != null) {
			String[] filelist = event.getFile_list().split(":");
			if (filelist.length > 0 && StringUtils.hasLength(filelist[0])) {
				String storePath = PropertyUtil.getInputFileStorePath();
				String file_path = storePath + "/" + event_code;
				File path = new File(file_path);
				if (path.isDirectory()) {
					File[] files = path.listFiles();
					for (File file : files) {
						file.delete();
					}
				}
				path.delete();
			}
		}

		inputService.cancelInputEventByCode(event_code);
		insertCommonLog("撤销作业[" + event_code + "]");
		return type.equalsIgnoreCase("ajax") ? null : "ok";
	}

}
