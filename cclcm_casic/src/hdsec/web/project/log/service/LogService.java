package hdsec.web.project.log.service;

import hdsec.web.project.log.model.AdminOperLog;
import hdsec.web.project.log.model.CommonOperLog;
import hdsec.web.project.log.model.SystemLog;
import hdsec.web.project.log.model.UserLoginLog;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public interface LogService {
	
	void fillDataTest();
	
	List<SystemLog> getSystemLog(Map<String, Object> map);
	
	int getAdminOperLogSize(Map<String, Object> map);
	
	List<AdminOperLog> getAdminOperLog(Map<String, Object> map, RowBounds rbs);
	
	int getCommonOperLogSize(Map<String, Object> map);
	
	List<CommonOperLog> getCommonOperLog(Map<String, Object> map, RowBounds rbs);
	
	//普通用户操作日志
	void addCommonOperLog(CommonOperLog commonOperLog);
	
	//三员操作日志
	void addAdminOperLog(AdminOperLog adminOperLog);
	
	//用户登录日志
	void addUserLoginLog(UserLoginLog userLoginLog);
	
	int getUserLoginLogSize(Map<String, Object> map);
	
	List<UserLoginLog> getUserLoginLog(Map<String, Object> map, RowBounds rbs);
}
