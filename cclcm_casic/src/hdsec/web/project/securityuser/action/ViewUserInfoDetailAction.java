package hdsec.web.project.securityuser.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.burn.model.BurnFile;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.bm.BMPropertyUtil;
import hdsec.web.project.common.bm.model.BmRealUser;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.securityuser.model.AbroadInfo;
import hdsec.web.project.securityuser.model.BmUserInfoEvent;
import hdsec.web.project.securityuser.model.ExperienceInfo;
import hdsec.web.project.securityuser.model.FamilyInfo;
import hdsec.web.project.user.model.SecUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 查看用户信息完善作业详情
 * 
 * @author guojiao
 */
public class ViewUserInfoDetailAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String op = "";
	private BmUserInfoEvent event = null;
	private BmRealUser userinfo = null;
	private ApproveProcess process = null;
	private ProcessJob job = null;
	private List<ProcessRecord> recordList = null;
	private List<AbroadInfo> abroadinfo = null;
	private List<ExperienceInfo> experienceinfo = null;
	private List<FamilyInfo> familyinfo = null;
	private Integer listSize = null;
	private String opinion_all = "";
	private List<BurnFile> burnFileList = null;
	private SecUser secUser = null;
	private String headpath = "";// 用户头像路径

	public String getHeadpath() {
		return headpath;
	}

	public SecUser getSecUser() {
		return secUser;
	}

	public List<BurnFile> getBurnFileList() {
		return burnFileList;
	}

	public Integer getListSize() {
		return listSize;
	}

	public String getOpinion_all() {
		return opinion_all;
	}

	public List<AbroadInfo> getAbroadinfo() {
		return abroadinfo;
	}

	public List<ExperienceInfo> getExperienceinfo() {
		return experienceinfo;
	}

	public List<FamilyInfo> getFamilyinfo() {
		return familyinfo;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public BmRealUser getUserinfo() {
		return userinfo;
	}

	public BmUserInfoEvent getEvent() {
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
		event = securityUserService.getUserInfoEventByEventCode(event_code);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_code", event_code);
		map.put("real_user_id", event.getUser_iidd());
		map.put("info_type", 1);
		userinfo = securityUserService.getUserInfoByEventCode(map);
		if (userinfo == null) {
			throw new Exception("查询的用户信息已经被删除或不存在");
		}
		secUser = userService.getSecUserByUid(event.getUser_iidd());
		// 个人简历
		if (!userinfo.getJob_resume().equals("")) {
			experienceinfo = securityUserService.getUserExperience(map);
		}
		// 出国情况
		if (!userinfo.getAbroad_twice().equals("")) {
			abroadinfo = securityUserService.getUserAbroad(map);

			burnFileList = new ArrayList<BurnFile>();
			for (int a = 0; a < abroadinfo.size(); a++) {
				String[] filelist = abroadinfo.get(a).getAbroad_file().split(CCLCMConstants.DEVIDE_SYMBOL);
				if (filelist.length > 0 && StringUtils.hasLength(filelist[0])) {
					String storPath = "";
					try {
						storPath = BMPropertyUtil.getSecUserInfoSepcialFilePath();
					} catch (Exception e) {
						e.printStackTrace();
					}

					String file_path = "";
					for (int i = 0; i < filelist.length; i++) {
						file_path = storPath + "/" + event.getUser_iidd() + "/" + filelist[i];
						burnFileList.add(new BurnFile(filelist[i], file_path));

					}
				}
			}
		}

		// 家庭成员情况
		if (!userinfo.getFamily_info().equals("")) {
			familyinfo = securityUserService.getUserFamily(map);
		}

		// 个人头像
		headpath = getHeadShot(userinfo.getReal_user_id());

		if (event == null) {
			throw new Exception("查询的作业已经被删除");
		} else {
			String job_code = securityUserService.getUserInfoJobCodeByEventCode(event_code);
			if (StringUtils.hasLength(job_code)) {
				job = basicService.getProcessJobByCode(job_code);
				process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
			}
			listSize = recordList.size() - 1;
			for (int i = 1; i <= listSize; i++) {
				opinion_all = opinion_all + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion() + "<br>审批人："
						+ recordList.get(i).getUser_name() + "<br>审批时间：" + recordList.get(i).getOp_time_str();
			}
			return SUCCESS;
		}
	}
}
