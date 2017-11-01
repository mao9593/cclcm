package hdsec.web.project.education.service;

import hdsec.web.project.education.mapper.EducationMapper;
import hdsec.web.project.education.model.ClassHour;
import hdsec.web.project.education.model.EduTrainingRecord;
import hdsec.web.project.education.model.UploadEduFiles;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

public class EducationServiceImpl implements EducationService {
	private final Logger logger = Logger.getLogger(this.getClass());

	@Resource
	private EducationMapper educationMapper;

	@Override
	public void UploadLearningFile(UploadEduFiles event) {
		// logger.debug("UploadLearningFile" + event.getEvent_code());
		educationMapper.UploadLearningFile(event);
	}

	@Override
	public List<UploadEduFiles> getLearningFileList(Map<String, Object> map) {
		logger.debug("getLearningFileList");
		return educationMapper.getLearningFileList(map);
	}

	@Override
	public void delUploadFileByName(String file_name) {
		logger.debug("delUploadFileByName");
		educationMapper.delUploadFileByName(file_name);
	}

	@Override
	public void addEduTrainingRecord(EduTrainingRecord record) {
		logger.debug("EduTrainingRecord");
		educationMapper.addEduTrainingRecord(record);
	}

	@Override
	public List<EduTrainingRecord> getAllTrainingRecordList(Map<String, Object> map) {
		logger.debug("getAllTrainingRecordList");
		return educationMapper.getAllTrainingRecordList(map);
	}

	@Override
	public void deleteTrainingRecord(String course_id) {
		logger.debug("deleteTrainingRecord:" + course_id);
		educationMapper.deleteTrainingRecord(course_id);
	}

	@Override
	public void updateTrainingRecord(EduTrainingRecord record) {
		logger.debug("updateTrainingRecord:" + record.getCourse_id());
		educationMapper.updateTrainingRecord(record);
	}

	@Override
	public List<ClassHour> getClassHourRecordByUserId(Map<String, Object> map) {
		logger.debug("getClassHourRecordByUserId");
		return educationMapper.getClassHourRecordByUserId(map);
	}

	@Override
	public EduTrainingRecord getTrainingRecordByCourseId(String course_id) {
		logger.debug("getTrainingRecordByCourseId:" + course_id);
		return educationMapper.getTrainingRecordByCourseId(course_id);
	}

	@Override
	public List<ClassHour> getAllClassHourList(Map<String, Object> map) {
		logger.debug("getAllClassHourList");
		return educationMapper.getAllClassHourList(map);
	}

	@Override
	public void addClassHour(ClassHour hour) {
		logger.debug("addClassHour:" + hour.getUser_iidd());
		educationMapper.addClassHour(hour);
	}

	@Override
	public void addClassHourRecord(Map<String, Object> map) {
		logger.debug("addClassHourRecord:");
		educationMapper.addClassHourRecord(map);
	}

	@Override
	public void updateClassHourRecord(Map<String, Object> map) {
		logger.debug("updateClassHourRecord:");
		educationMapper.updateClassHourRecord(map);
	}

	@Override
	public void deleteClassHourRecord(String course_id) {
		logger.debug("deleteClassHourRecord:" + course_id);
		educationMapper.deleteClassHourRecord(course_id);
	}

	@Override
	public String getUserClassHourOnlineByUserId(Map<String, Object> map) {
		logger.debug("getUserClassHourOnlineByUserId");
		return educationMapper.getUserClassHourOnlineByUserId(map);
	}

	@Override
	public int getCountByFileName(String file_name) {
		logger.debug("getCountByFileName");
		return educationMapper.getCountByFileName(file_name);
	}

	@Override
	public String getTotalHour() {
		logger.debug("getTotalHour");
		return educationMapper.getTotalHour();
	}
	@Override
	public EduTrainingRecord getTrainingRecordById(String id) {
		logger.debug("getTrainingRecordById:" + id);
		return educationMapper.getTrainingRecordById(id);
	}
}
