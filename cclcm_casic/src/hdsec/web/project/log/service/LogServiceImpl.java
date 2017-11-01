package hdsec.web.project.log.service;

import hdsec.web.project.log.mapper.LogMapper;
import hdsec.web.project.log.model.AdminOperLog;
import hdsec.web.project.log.model.CommonOperLog;
import hdsec.web.project.log.model.SystemLog;
import hdsec.web.project.log.model.UserLoginLog;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

public class LogServiceImpl implements LogService {
	protected Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private LogMapper logMapper;
	@Resource
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<SystemLog> getSystemLog(Map<String, Object> map) {
		return logMapper.getSystemLog(map);
	}

	@Override
	public int getAdminOperLogSize(Map<String, Object> map) {
		return logMapper.getAdminOperLogSize(map);
	}

	@Override
	public List<AdminOperLog> getAdminOperLog(Map<String, Object> map, RowBounds rbs) {
		return logMapper.getAdminOperLog(map, rbs);
	}

	@Override
	public int getCommonOperLogSize(Map<String, Object> map) {
		return logMapper.getCommonOperLogSize(map);
	}

	@Override
	public List<CommonOperLog> getCommonOperLog(Map<String, Object> map, RowBounds rbs) {
		return logMapper.getCommonOperLog(map, rbs);
	}

	@Override
	public void addCommonOperLog(CommonOperLog commonOperLog) {
		if (commonOperLog != null && commonOperLog.getLog_detail().getBytes().length >= 1024) {
			String detail = commonOperLog.getLog_detail();
			detail = detail.substring(detail.length() - 4) + "...";
			commonOperLog.setLog_detail(detail);
			logMapper.addCommonOperLog(commonOperLog);
		} else
			logMapper.addCommonOperLog(commonOperLog);
	}

	@Override
	public void fillDataTest() {
		CommonOperLog log = null;
		SqlSession session = null;
		for (int index = 0; index < 100; index++) {
			session = sqlSessionFactory.openSession(ExecutorType.BATCH);
			for (int i = 0; i < 1000; i++) {
				log = new CommonOperLog();
				log.setUser_id("renmingfei");
				log.setUser_name("任鸣飞");
				log.setDept_name("安全产品部");
				log.setLog_detail("登录成功");
				log.setResult("成功");
				log.setLog_time(new Date());
				log.setLog_type(1);
				log.setLogin_ip("10.93.142.25");
				log.setLogin_hostname("10.93.142.25");
				log.setSubsys_code("admin");
				session.insert("addCommonOperLog", log);
			}
			session.commit(true);
			session.close();
		}
		logger.info("-----------fill data test done!!");
	}

	@Override
	public void addAdminOperLog(AdminOperLog adminOperLog) {
		if (adminOperLog != null && adminOperLog.getLog_detail().getBytes().length >= 1024) {
			String detail = adminOperLog.getLog_detail();
			detail = detail.substring(detail.length() - 4) + "...";
			adminOperLog.setLog_detail(detail);
			logMapper.addAdminOperLog(adminOperLog);
		} else
			logMapper.addAdminOperLog(adminOperLog);
	}

	@Override
	public void addUserLoginLog(UserLoginLog userLoginLog) {
		logMapper.addUserLoginLog(userLoginLog);
	}

	@Override
	public int getUserLoginLogSize(Map<String, Object> map) {
		return logMapper.getUserLoginLogSize(map);
	}

	@Override
	public List<UserLoginLog> getUserLoginLog(Map<String, Object> map, RowBounds rbs) {
		return logMapper.getUserLoginLog(map, rbs);
	}
}
