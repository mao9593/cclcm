package hdsec.web.project.education.mapper;

import hdsec.web.project.education.model.ClassHour;
import hdsec.web.project.education.model.EduTrainingRecord;
import hdsec.web.project.education.model.UploadEduFiles;

import java.util.List;
import java.util.Map;

public interface EducationMapper {

	void UploadLearningFile(UploadEduFiles event);

	List<UploadEduFiles> getLearningFileList(Map<String, Object> map);

	int getSeclvcodeByUid(String userId);

	void delUploadFileByName(String file_name);

	void addEduTrainingRecord(EduTrainingRecord record);

	List<EduTrainingRecord> getAllTrainingRecordList(Map<String, Object> map);

	void deleteTrainingRecord(String course_id);

	EduTrainingRecord getTrainingRecordByCourseId(String course_id);

	void updateTrainingRecord(EduTrainingRecord record);

	List<ClassHour> getAllClassHourList(Map<String, Object> map);

	List<ClassHour> getClassHourRecordByUserId(Map<String, Object> map);

	void addClassHour(ClassHour hour);

	void addClassHourRecord(Map<String, Object> map);

	void updateClassHourRecord(Map<String, Object> map);

	void deleteClassHourRecord(String course_id);

	String getUserClassHourOnlineByUserId(Map<String, Object> map);

	int getCountByFileName(String file_name);

	String getTotalHour();
	
	EduTrainingRecord getTrainingRecordById(String id);
}
