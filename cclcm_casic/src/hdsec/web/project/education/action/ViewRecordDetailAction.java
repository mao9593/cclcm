package hdsec.web.project.education.action;

import hdsec.web.project.education.model.EduTrainingRecord;

import org.springframework.util.StringUtils;

/**
 * 添加集中培训记录
 * 
 * @author lishu
 * 
 */
public class ViewRecordDetailAction extends EducationBaseAction {
	private static final long serialVersionUID = 1L;
	EduTrainingRecord record = null;
	private String id = ""; // 培训id

	public EduTrainingRecord getRecord() {
		return record;
	}

	public void setRecord(EduTrainingRecord record) {
		this.record = record;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(id)) {
			record = educationService.getTrainingRecordById(id);
			return SUCCESS;
		} else {
			throw new Exception("无法查询培训记录，请重新尝试。");
		}
	}
}
