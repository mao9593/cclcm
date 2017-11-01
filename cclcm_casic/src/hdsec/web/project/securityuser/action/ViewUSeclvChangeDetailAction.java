package hdsec.web.project.securityuser.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.common.bm.model.BmRealUser;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.securityuser.model.AbroadInfo;
import hdsec.web.project.securityuser.model.BmUserInfoEvent;
import hdsec.web.project.securityuser.model.ExperienceInfo;
import hdsec.web.project.securityuser.model.FamilyInfo;
import hdsec.web.project.securityuser.model.UserSeclvChangeEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 查看用户密级变更作业详情
 * 
 * @author gaoximin 2015-6-10
 */
public class ViewUSeclvChangeDetailAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String op = "";
	private UserSeclvChangeEvent event = null;
	private BmUserInfoEvent BMevent = null;
	private BmRealUser userinfo = null;
	private ApproveProcess process = null;
	private ProcessJob job = null;
	private List<ProcessRecord> recordList = null;
	private List<AbroadInfo> abroadinfo = null;
	private List<ExperienceInfo> experienceinfo = null;
	private List<FamilyInfo> familyinfo = null;
	private Integer start = null;
	private List<BmRealUser> bmList = null;
	private String type = "";

	// add by liuyaling
	private Integer listSize = null;
	private String opinion_all = "";

	// add ending

	public List<AbroadInfo> getAbroadinfo() {
		return abroadinfo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getListSize() {
		return listSize;
	}

	public String getOpinion_all() {
		return opinion_all;
	}

	public List<ExperienceInfo> getExperienceinfo() {
		return experienceinfo;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public BmRealUser getUserinfo() {
		return userinfo;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public UserSeclvChangeEvent getEvent() {
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

		event = securityUserService.getUSeclvChangeEventByEventCode(event_code);
		if (event == null) {
			throw new Exception("查询的作业已经被删除");
		} else {
			String job_code = securityUserService.getJobCodeByEventCode(event_code);
			BMevent = securityUserService.getUserInfoEventByEventCode(event_code);
			Map<String, Object> BMmap = new HashMap<String, Object>();
			BMmap.put("real_user_id", event.getChange_user_iidd());
			BMmap.put("info_type", 2);
			userinfo = securityUserService.getUserInfoByEventCode(BMmap);
			if (userinfo != null) {
				BMmap.put("event_code", userinfo.getEvent_code());
				// 个人简历
				if (!userinfo.getJob_resume().equals("")) {
					experienceinfo = new ArrayList<ExperienceInfo>();
					experienceinfo = securityUserService.getUserExperience(BMmap);
				}
				// 出国情况
				if (!userinfo.getAbroad_twice().equals("")) {
					abroadinfo = new ArrayList<AbroadInfo>();
					abroadinfo = securityUserService.getUserAbroad(BMmap);
				}

				// 家庭成员情况
				if (!userinfo.getFamily_info().equals("")) {
					familyinfo = new ArrayList<FamilyInfo>();
					familyinfo = securityUserService.getUserFamily(BMmap);
				}
			}
			if (StringUtils.hasLength(job_code)) {
				job = basicService.getProcessJobByCode(job_code);
				process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
			}

			// add by liuyaling
			listSize = recordList.size() - 1;
			for (int i = 1; i <= listSize; i++) {
				opinion_all = opinion_all + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion() + "<br>审批人："
						+ recordList.get(i).getUser_name() + "<br>审批时间：" + recordList.get(i).getOp_time_str();
			}
			// add ending
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_id", event.getChange_user_iidd());
			map.put("info_type", "2");
			bmList = securityUserService.getAllUserInfoList(map);
			if (bmList.size() != 0) {
				userinfo = bmList.get(0);
			} else {
				start = 1;
			}
			return SUCCESS;
		}
	}

	public BmUserInfoEvent getBMevent() {
		return BMevent;
	}

	public void setBMevent(BmUserInfoEvent bMevent) {
		BMevent = bMevent;
	}

	public List<FamilyInfo> getFamilyinfo() {
		return familyinfo;
	}

	public void setFamilyinfo(List<FamilyInfo> familyinfo) {
		this.familyinfo = familyinfo;
	}
}
