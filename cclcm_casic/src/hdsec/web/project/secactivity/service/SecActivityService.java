package hdsec.web.project.secactivity.service;

import hdsec.web.project.secactivity.model.SecOutExchangeEvent;
import hdsec.web.project.secactivity.model.UserSecActiEvent;

import java.util.List;
import java.util.Map;

public interface SecActivityService {

	/** 添加涉密活动作业 **/
	void AddUserSecActiEvent(UserSecActiEvent event, String next_approver) throws Exception;

	/** 通过流水号查找用户密级变更详情 **/
	UserSecActiEvent getSecActiEvent(String event_code);

	/** 通过作业流水号查询任务流水号 */
	String getJobCodeByEventCode(String event_code);

	/** 查询用户涉密活动作业信息 */
	List<UserSecActiEvent> getUSecActiEventList(Map<String, Object> map);

	/** 根据任务作业号查询用户涉密活动作业信息 */
	List<UserSecActiEvent> getUSecActiEventListByJobCode(String job_code);

	/** 通过作业流水号删除用户涉密活动作业 */
	void delUSecActiEventByEventCode(String event_code);

	/** 涉外交流保密工作 **/

	/** 添加涉外交流保密作业 **/
	void AddSecOutExchangeEvent(SecOutExchangeEvent event, String next_approver) throws Exception;

	/** 查询涉外交流作业信息 **/
	List<SecOutExchangeEvent> getSecOutExchangeEventList(Map<String, Object> map);

	/** 通过流水号查找涉外交流作业详情 **/
	SecOutExchangeEvent getSecOutExchangeByEventCode(String event_code);

	/** 通过涉外交流作业流水号查询任务流水号 */
	String getOutExchangeJobCodeByEventCode(String event_code);

	/** 通过作业流水号删除涉外交流作业 */
	void delSecOutExchangeEventByEventCode(String event_code);

	/** 根据任务作业号查询涉外交流作业信息 */
	List<SecOutExchangeEvent> getOutExchangeEventListByJobCode(String job_code);
}
