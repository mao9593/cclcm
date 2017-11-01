package hdsec.web.project.activiti.service;

import hdsec.web.project.activiti.mapper.ActivitiMapper;
import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

public class ActivitiServiceImpl implements ActivitiService {
	protected Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private ActivitiMapper activitiMapper;
	@Resource
	private UserService userService;
	@Resource
	private BasicService basicService;

	/**
	 * 流程管理
	 */

	@Override
	public void addProcess(ApproveProcess process) {
		activitiMapper.addProcess(process);
	}

	@Override
	public String getProcessNameById(String process_id) {
		return activitiMapper.getProcessNameById(process_id);
	}

	@Override
	public void delProcessByProcessId(String process_id) {
		activitiMapper.delProcessByProcessId(process_id);
	}

	@Override
	public void updateProcess(ApproveProcess process) {
		activitiMapper.updateProcess(process);
	}

	@Override
	public ApproveProcess getProcessById(String process_id) {
		ApproveProcess item = activitiMapper.getProcessById(process_id);
		if (item == null) {
			return null;
		}
		String step_dept = item.getStep_dept();
		String step_role = item.getStep_role();
		String step_dept_name = "";
		String step_role_name = "";
		if (StringUtils.hasLength(step_dept)) {
			String[] depts = step_dept.split("#");
			String[] roles = step_role.split("#");
			for (int i = 0; i < depts.length; i++) {
				String dept_tmp = "";
				String role_tmp = "";
				if (depts[i].startsWith("L:")) {
					// int length = Integer.parseInt(depts[i].substring(2));
					// int level = length / 2 + length % 2;
					dept_tmp = "至:" + depts[i].substring(2) + "级机构" + ",";
				} else {
					for (String dept_item : depts[i].split("@")) {
						if (dept_item.trim().equals("self")) {
							dept_tmp = dept_tmp + "本部门" + ",";
						} else if (dept_item.trim().equals("super")) {
							dept_tmp = dept_tmp + "上级部门" + ",";
						} else {
							dept_tmp = dept_tmp + userService.getDeptNameByDeptId(dept_item) + ",";
						}
					}
				}

				for (String role_id : roles[i].split("@")) {
					role_tmp = role_tmp + userService.getRoleNameByRoleId(role_id) + ",";
				}

				step_dept_name += dept_tmp.substring(0, dept_tmp.length() - 1) + "#";
				step_role_name += role_tmp.substring(0, role_tmp.length() - 1) + "#";
			}
			if (step_dept_name.length() > 0) {
				step_dept_name = step_dept_name.substring(0, step_dept_name.length() - 1);
				step_role_name = step_role_name.substring(0, step_role_name.length() - 1);
			}
			item.setStep_dept_name(step_dept_name);
			item.setStep_role_name(step_role_name);
		}
		// 适用部门
		String dept_id = item.getDept_id();
		String dept_name = "";
		if (StringUtils.hasLength(dept_id)) {
			if (dept_id.equalsIgnoreCase("default")) {
				dept_name = "默认部门";
			} else {
				for (String dept : dept_id.split(",")) {
					if (StringUtils.hasLength(dept)) {
						dept_name = dept_name + userService.getDeptNameByDeptId(dept) + ",";
					}
				}
				dept_name = dept_name.substring(0, dept_name.length() - 1);
			}
			item.setDept_name(dept_name);
		}
		return item;
	}

	@Override
	public List<ApproveProcess> getApproveProcessList(Map<String, Object> map) {
		List<ApproveProcess> prcList = activitiMapper.getApproveProcessList(map);
		int i = 0;
		for (ApproveProcess item : prcList) {
			String step_dept = item.getStep_dept();
			String step_role = item.getStep_role();
			String steps_dest = "申请-->";
			if (StringUtils.hasLength(step_dept)) {
				String[] depts = step_dept.split("#");
				String[] roles = step_role.split("#");
				for (i = 0; i < depts.length; i++) {
					String dept_tmp = "";
					String role_tmp = "";
					if (depts[i].startsWith("L:")) {
						dept_tmp = "至:" + depts[i].substring(2) + "级机构" + ",";
					} else {
						for (String dept_item : depts[i].split("@")) {
							if (dept_item.trim().equals("self")) {
								dept_tmp = dept_tmp + "本部门" + ",";
							} else if (dept_item.trim().equals("super")) {
								dept_tmp = dept_tmp + "上级部门" + ",";
							} else {
								dept_tmp = dept_tmp + userService.getDeptNameByDeptId(dept_item) + ",";
							}
						}
					}
					for (String role_id : roles[i].split("@")) {
						role_tmp = role_tmp + userService.getRoleNameByRoleId(role_id) + ",";
					}

					steps_dest += dept_tmp.substring(0, dept_tmp.length() - 1) + "("
							+ role_tmp.substring(0, role_tmp.length() - 1) + ")-->";
				}
			}
			steps_dest += "结束";
			item.setSteps_dest(steps_dest);
			// 适用部门
			String dept_id = item.getDept_id();
			String dept_name = "";
			if (StringUtils.hasLength(dept_id)) {
				if (dept_id.equalsIgnoreCase("default")) {
					dept_name = "默认部门";
				} else {
					for (String dept : dept_id.split(",")) {
						if (StringUtils.hasLength(dept)) {
							dept_name = dept_name + userService.getDeptNameByDeptId(dept) + ",";
						}
					}
					dept_name = dept_name.substring(0, dept_name.length() - 1);
				}
				item.setDept_name(dept_name);
			}
			// 适用操作
			String jobType_code = item.getJobType_code();
			String jobType_name = "";
			if (StringUtils.hasLength(jobType_code)) {
				for (String type : jobType_code.split(",")) {
					if (StringUtils.hasLength(type)) {
						jobType_name = jobType_name + JobTypeEnum.valueOf(type.trim()).getJobTypeName() + ",";
					}
				}
				jobType_name = jobType_name.substring(0, jobType_name.length() - 1);
				item.setJobType_name(jobType_name);
			}
			// 适用密级
			String seclv_code = item.getSeclv_code();
			String seclv_name = "";
			if (StringUtils.hasLength(seclv_code)) {
				for (String seclv : seclv_code.split(",")) {
					if (StringUtils.hasLength(seclv)) {
						seclv_name = seclv_name
								+ userService.getSecLevelByCode(Integer.parseInt(seclv)).getSeclv_name() + ",";
					}
				}
				seclv_name = seclv_name.substring(0, seclv_name.length() - 1);
				item.setSeclv_name(seclv_name);
			}
			// 适用用途
			String usage_codes = item.getUsage_code();
			String usage_name = "";
			if (StringUtils.hasLength(usage_codes)) {
				for (String usage_code : usage_codes.split(",")) {
					if (StringUtils.hasLength(usage_code)) {
						usage_name = usage_name + basicService.getUsageByCode(usage_code).getUsage_name() + ",";
					}
				}
				usage_name = usage_name.substring(0, usage_name.length() - 1);
				item.setUsage_name(usage_name);
			}
		}
		return prcList;
	}

	@Override
	public List<ApproverUser> getApproversByDeptRole(Map<String, String> map) {
		return activitiMapper.getApproversByDeptRole(map);
	}

	@Override
	public ApproveProcess getProcessByKey(String dept_id_u, String seclv_code, String jobType_code, String usage_code) {
		ApproveProcess item = new ApproveProcess();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dept_id", dept_id_u);
		map.put("seclv_code", seclv_code);
		map.put("jobType_code", jobType_code);
		map.put("usage_code", usage_code);
		item = activitiMapper.getProcessByUsageKey(map);
		if (item == null) {
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("dept_id", dept_id_u);
			map2.put("seclv_code", seclv_code);
			map2.put("jobType_code", jobType_code);
			item = activitiMapper.getProcessByKey(map2);
		}
		if (item == null) {
			return null;
		}
		// 适用部门
		String dept_id = item.getDept_id();
		String dept_name = "";
		if (StringUtils.hasLength(dept_id)) {
			if (dept_id.equalsIgnoreCase("default")) {
				dept_name = "默认部门";
			} else {
				for (String dept : dept_id.split(",")) {
					if (StringUtils.hasLength(dept)) {
						dept_name = dept_name + userService.getDeptNameByDeptId(dept) + ",";
					}
				}
				dept_name = dept_name.substring(0, dept_name.length() - 1);
			}
			item.setDept_name(dept_name);
		}
		return item;
	}

	@Override
	public void addProcessRecord(ProcessRecord record) {
		activitiMapper.addProcessRecord(record);
	}

	@Override
	public List<ProcessRecord> getProcessRecordList(ProcessRecord record) {
		return activitiMapper.getProcessRecordList(record);
	}

}
