package hdsec.web.project.log.mapper;

import hdsec.web.project.log.model.AdminOperLog;
import hdsec.web.project.log.model.CommonOperLog;
import hdsec.web.project.log.model.SystemLog;
import hdsec.web.project.log.model.UserLoginLog;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface LogMapper {
	
	List<SystemLog> getSystemLog(Map<String, Object> map);
	
	List<AdminOperLog> getAdminOperLog(Map<String, Object> map, RowBounds rb);
	
	List<CommonOperLog> getCommonOperLog(Map<String, Object> map, RowBounds rb);
	
	void addCommonOperLog(CommonOperLog commonOperLog);
	
	int getCommonOperLogSize(Map<String, Object> map);
	
	int getAdminOperLogSize(Map<String, Object> map);
	
	void addAdminOperLog(AdminOperLog adminOperLog);
	
	void addUserLoginLog(UserLoginLog userLoginLog);
	
	int getUserLoginLogSize(Map<String, Object> map);
	
	List<UserLoginLog> getUserLoginLog(Map<String, Object> map, RowBounds rb);
}
