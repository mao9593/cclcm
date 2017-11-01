package hdsec.web.project.basic.service;

import hdsec.web.project.activiti.service.BaseActivitiManage;

/**
 * 流程相关处理类
 * 
 * @author renmingfei
 * 
 */
public class BasicPrcManage extends BaseActivitiManage {
	public int getCandidateInstanceCount(String user_iidd, String jobType_code) {
		int count = 0;
		count = (int) taskService.createTaskQuery().taskDescription(jobType_code).taskCandidateUser(user_iidd)
				.taskName("approve").active().count();
		return count;
	}
}
