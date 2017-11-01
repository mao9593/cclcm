package hdsec.web.project.education.action;

import hdsec.web.project.education.model.EduTrainingRecord;
import hdsec.web.project.education.model.UploadEduFiles;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学习资料列表
 * 
 * 上传在线学习的时长
 * 
 * @author lixiao lishu guojiao
 * 
 */
public class UpdateClassHourAction extends EducationBaseAction {
	private static final long serialVersionUID = 1L;
	private String file_id = "";// 文件编号
	private String file_name = "";// 文件名
	private float class_time = 0;// 转化学时
	private String timer_text = "";// 页面统计在线时长

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public void setTimer_text(String timer_text) {
		this.timer_text = timer_text;
	}

	@Override
	public String executeFunction() throws Exception {

		// ①拆分页面传回的学时数值
		String[] timer = timer_text.split(":");
		/* int_timer = timer_text.substring(2, timer_text.indexOf(".")); */

		float a = 0;
		float b = 0;
		float c = 0;
		// 将查分后的时、分、秒转换成float
		a = Float.parseFloat(timer[0]);
		b = Float.parseFloat(timer[1]);
		c = Float.parseFloat(timer[2]);
		// 对学时计算结果进行四舍五入，保留小数点后两位
		class_time = (a * 60 + b + c / 60) / 50;
		//System.out.println(class_time);
		class_time = (float)(Math.round(class_time * 100)) / 100;
		//System.out.println(class_time);
		/*
		 * int scale = 2; int roundingMode = 4; BigDecimal time_test = new BigDecimal(class_time); time_test =
		 * time_test.setScale(scale, roundingMode); class_time = time_test.floatValue();
		 */
		// BigDecimal ff = new BigDecimal(class_time);
		// float class_time1 = ff.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();

		// ②根据在线学习文档名（唯一）判断该文档在线学习累计总时长，累计时长不能超过该文档学时要求上限，
		// 若超过，新学学时记为0.2016.3.5
		// 获取该文档上传时，规定的学时要求
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("file_name", file_name);
		List<UploadEduFiles> eventlist = educationService.getLearningFileList(map);
		UploadEduFiles event = null;
		if (eventlist.size() != 0) {
			event = eventlist.get(0);
		}

		// 获取当前用户该文档的在线学习累计总时长
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("user_iidd", getCurUser().getUser_iidd());
		map1.put("course_name", file_name);
		float user_online_classtime = 0;
		String tempOnline = educationService.getUserClassHourOnlineByUserId(map1);
		if(tempOnline != null && tempOnline !=""){
			user_online_classtime =  Float.parseFloat(tempOnline);
		}
		// 数据库累计时长+此次学时时长，与规定学时要求的大小比较。使得该文档累计总学时不超过学时要求。
		// 当数据库累计时长超过总时长，本次学时为0
		if (user_online_classtime + class_time > event.getFile_edu_hour()) {
			class_time = event.getFile_edu_hour() - user_online_classtime;
			if (class_time < 0) {
				class_time = 0;
			}
		}

		// ③ 将处理好的学时插入学时统计数据库表
		String user_iidd = getCurUser().getUser_iidd();
		String user_name = userService.getUserNameByUserId(user_iidd);
		String dept_id = getCurUser().getDept_id();

		EduTrainingRecord record = new EduTrainingRecord(file_id, file_name, 0, "online", class_time, user_iidd,
				user_name, new Date(), new Date(), "", "", null, "", new Date());
		educationService.addEduTrainingRecord(record);
		insertCommonLog("添加在线培训记录[" + file_name + "]");

		Map<String, Object> mmp = new HashMap<String, Object>();
		mmp.put("user_iidd", user_iidd);
		mmp.put("dept_id", dept_id);
		mmp.put("course_id", file_id);
		mmp.put("course_name", file_name);
		mmp.put("class_hour_online", class_time);
		mmp.put("training_type", 0);
		mmp.put("class_hour_centraining", 0);
		mmp.put("class_hour_outtraining", 0);
		mmp.put("class_hour_total", 0);
		mmp.put("apply_time", new Date());
		educationService.addClassHourRecord(mmp);

		return SUCCESS;
	}
}
