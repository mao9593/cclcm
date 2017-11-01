package hdsec.web.project.education.service;

import hdsec.web.project.education.model.ClassHour;
import hdsec.web.project.education.model.EduTrainingRecord;
import hdsec.web.project.education.model.UploadEduFiles;

import java.util.List;
import java.util.Map;

public interface EducationService {

	/**
	 * 
	 * 
	 * @param event
	 * @throws Exception
	 */
	void UploadLearningFile(UploadEduFiles event);

	/** 获取所有保密教育资料列表 **/
	List<UploadEduFiles> getLearningFileList(Map<String, Object> map);

	void delUploadFileByName(String file_name);

	void addEduTrainingRecord(EduTrainingRecord record);

	/** 查询所有教育培训记录 **/
	List<EduTrainingRecord> getAllTrainingRecordList(Map<String, Object> map);

	/** 根据培训编码删除培训记录 **/
	void deleteTrainingRecord(String course_id);

	EduTrainingRecord getTrainingRecordByCourseId(String course_id);

	/** 根据培训编码更新培训记录 **/
	void updateTrainingRecord(EduTrainingRecord record);

	/** 查询所有学时统计记录 **/
	List<ClassHour> getAllClassHourList(Map<String, Object> map);

	List<ClassHour> getClassHourRecordByUserId(Map<String, Object> map);

	/** 增加学时统计记录 **/
	void addClassHourRecord(Map<String, Object> map);

	void addClassHour(ClassHour hour);

	/** 更新学时统计记录 **/
	void updateClassHourRecord(Map<String, Object> map);

	/** 根据培训编码删除学时统计记录 **/
	void deleteClassHourRecord(String course_id);

	String getUserClassHourOnlineByUserId(Map<String, Object> map);

	/**
	 * 根据文件名查询已存在的文件数量
	 * 
	 * @param file_name
	 * @return
	 */
	int getCountByFileName(String file_name);

	/**
	 * 查询所有在线学习文件总学时
	 * 
	 * @return
	 */
	String getTotalHour();
	/**
	 *根据ID查询培训记录
	 * @param id
	 * @return
	 */
	EduTrainingRecord getTrainingRecordById(String id);
}
