package hdsec.web.project.burn.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.burn.model.BurnEvent;
import hdsec.web.project.burn.model.BurnFile;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.common.util.MD5;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 查看刻录作业详细
 * 
 * @author renmingfei
 * 
 */
public class ViewBurnEventDetailAction extends BurnBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String op = "";
	private BurnEvent event = null;
	private ApproveProcess process = null;
	private ProcessJob job = null;
	private List<ProcessRecord> recordList = null;
	private List<BurnFile> burnFileList = null;
	private String file_src;
	private String proxyoutput_user_name = "";

	public String getProxyoutput_user_name() {
		return proxyoutput_user_name;
	}

	public void setProxyoutput_user_name(String proxyoutput_user_name) {
		this.proxyoutput_user_name = proxyoutput_user_name;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getOp() {
		return op;
	}

	public List<BurnFile> getBurnFileList() {
		return burnFileList;
	}

	public BurnEvent getEvent() {
		return event;
	}

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	public ProcessJob getJob() {
		return job;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	@Override
	public String executeFunction() throws Exception {
		event = burnService.getBurnEventByBurnCode(event_code);
		if (event == null) {
			throw new Exception("查询的作业已经被删除");
		} else {
			String[] filelist = event.getFile_list().split(CCLCMConstants.DEVIDE_SYMBOL);
			String[] fileseclevel = event.getFile_seclevel().split(CCLCMConstants.DEVIDE_SYMBOL);
			if (filelist.length > 0 && StringUtils.hasLength(filelist[0])) {
				String storePath = PropertyUtil.getBurnFileStorePath();
				burnFileList = new ArrayList<BurnFile>();
				Integer seclv_code;
				String seclv_name = "";
				String file_path = "";
				for (int i = 0; i < filelist.length; i++) {
					seclv_code = Integer.parseInt(fileseclevel[i].trim());
					seclv_name = userService.getSecLevelByCode(seclv_code).getSeclv_name();
					file_path = storePath + "/" + event_code + "/" + MD5.getStringMD5(filelist[i]);
					burnFileList.add(new BurnFile(filelist[i], seclv_code, seclv_name, file_path));
				}
			}
			String job_code = burnService.getJobCodeByEventCode(event_code);
			if (StringUtils.hasLength(job_code)) {
				job = basicService.getProcessJobByCode(job_code);
				// process = basicService.getApproveProcessByKey(job.getDept_id(), job.getSeclv_code(), job.getJobType()
				// .getJobTypeCode());
				process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);

				// 获取打印代理人或刻录代理人
				String proxyburn_user_iidd = basicService.getBurnProxyUserIdByJobcode(job_code);
				if (proxyburn_user_iidd != null && !proxyburn_user_iidd.equals("")) {
					setProxyoutput_user_name(userService.getUserNameByUserId(proxyburn_user_iidd));
				}
			}
			return SUCCESS;
		}
	}

	public String getFile_src() {
		return file_src;
	}

	public void setFile_src(String file_src) {
		this.file_src = file_src;
	}

	@Override
	public String getNas_url() throws Exception {
		return PropertyUtil.getNasBurnFileStoreUrl();
	}

	@Override
	public String getNas_username() throws Exception {
		return PropertyUtil.getNasBurnFileStoreUsername();
	}

	@Override
	public String getNas_password() throws Exception {
		return PropertyUtil.getNasBurnFileStorePassword();
	}
}
