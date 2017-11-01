package hdsec.web.project.input.service;

import hdsec.web.project.input.model.InputEvent;

import java.util.List;
import java.util.Map;

public interface InputService {

	/**
	 * 添加输入信息流程
	 * 
	 * @param event
	 * @param next_approver
	 * @throws Exception
	 */
	void addInputEvent(InputEvent event, String next_approver) throws Exception;

	/**
	 * 查看输入申请信息列表
	 * 
	 * @param map
	 * @return
	 */
	List<InputEvent> getInputEventList(Map<String, Object> map);

	String getJobCodeByEventCode(String event_code);

	String getEventCodeByJobCode(String job_code);

	InputEvent getInputEventByCode(String event_code);

	void cancelInputEventByCode(String event_code);

	List<InputEvent> getInputEventListByJobCode(String job_code);

	void updateInputEventState(Map<String, Object> map);

	/**
	 * 修改导入作业中关于电子文件的信息
	 * 
	 * @param event_code
	 * @param fileNum
	 * @param newFileList
	 * @param newFileSec
	 */
	void updateInputEventFileInfo(String event_code, int fileNum, String newFileList, String newFileSec);

	List<InputEvent> getEfileInputEventList(Map<String, Object> map);

	int getEfileInputEventListSize(Map<String, Object> map);
}
