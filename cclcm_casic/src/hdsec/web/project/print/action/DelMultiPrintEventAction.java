package hdsec.web.project.print.action;

import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.common.util.StringUtil;
import hdsec.web.project.print.model.OaPrintEvent;

import java.io.File;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 批量删除打印作业/草稿
 * 
 * @author lixiang
 * 
 */
public class DelMultiPrintEventAction extends PrintBaseAction {
	private static final long serialVersionUID = 1L;
	private String _chk = "";
	private String event_delids = "";

	public void setEvent_delids(String event_delids) {
		this.event_delids = event_delids;
	}

	public void set_chk(String _chk) {
		this._chk = _chk;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_delids)) {
			String[] ids = event_delids.split(":");
			for (String id : ids) {
				logger.info("id:" + id);
				OaPrintEvent event = printService.getSpecialPrintEventById(id);
				String[] filelist = event.getFile_list().split(CCLCMConstants.DEVIDE_SYMBOL);
				if (filelist.length > 0 && StringUtils.hasLength(filelist[0])) {
					String storePath = PropertyUtil.getOaFileStorePath();
					String file_path = storePath + "/" + event.getEvent_code() + "/";
					File folder = new File(file_path);
					File[] files = folder.listFiles();
					for (File file : files) {
						file.delete();
					}
					folder.delete();
				}
				printService.delSpecialPrintEventByPringId(id);
				insertCommonLog("删除特殊打印作业[" + id + "]");
			}
			return "special";
		}
		if (StringUtils.hasLength(_chk)) {
			List<String> event_id_list = StringUtil.stringArrayToList(_chk.split(","));
			String file_names = printService.delMultiPrintEvents(event_id_list);
			insertCommonLog("批量删除打印作业[" + file_names + "]");
		} else {
			throw new Exception("没有打印作业号，删除失败");
		}
		return SUCCESS;
	}
}
