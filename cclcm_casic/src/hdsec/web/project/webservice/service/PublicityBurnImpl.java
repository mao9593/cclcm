package hdsec.web.project.webservice.service;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.basic.mapper.BasicMapper;
import hdsec.web.project.burn.mapper.BurnMapper;
import hdsec.web.project.burn.model.BurnEvent;
import hdsec.web.project.burn.model.BurnPublicity;
import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.EncryptFile;
import hdsec.web.project.common.util.MD5;
import hdsec.web.project.common.util.PropertiesFileUtil;
import hdsec.web.project.log.model.CommonOperLog;
import hdsec.web.project.log.service.LogService;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@WebService(serviceName = "publicityBurnService")
public class PublicityBurnImpl {
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private BurnMapper burnMapper;
	@Resource
	private BasicMapper basicMapper;
	@Resource
	private UserService userService;
	@Resource
	protected LogService logService;
	private String seclvcode = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "seclv.config");
	protected CommonOperLog commonOperLog = null;

	@WebMethod
	public String postBurnEvent(
			@WebParam(targetNamespace = "http://service.webservice.project.web.hdsec/") String xmlStr) {
		logger.debug("input xmlStr is :" + xmlStr);
		InputSource is = new InputSource(new StringReader(xmlStr));
		// 创建密级映射
		HashMap<String, String> seclvMap = new HashMap<String, String>();
		String[] seclv;
		try {
			logger.debug("seclv.config in property file is :" + seclvcode);
			seclv = seclvcode.split(",");
		} catch (NullPointerException e) {
			logger.warn("seclvcode need config");
			return "301";
		}
		for (int i = 0; i < seclv.length; i++) {
			logger.debug((i + 1) + "th item in seclv config is :" + seclv[i]);
			String[] oneSeclv = seclv[i].split(":");
			// 检测配置文件中密级是否正确配置
			if (oneSeclv.length != 3) {
				logger.warn("seclv config is wrong");
				return "301";
			}
			seclvMap.put(oneSeclv[0], oneSeclv[2]);
			seclvMap.put(oneSeclv[2], oneSeclv[1]);
		}
		Integer id = null;
		String idcard = "";
		String event_code = "";
		String cd_serial = "";
		String file_name = "";
		byte[] file_contents = null;
		String fileSeclevel = "";
		String eventCode = "";
		// 判断能否身份证号获取用户，并设置用户ID和部门ID
		String user_iidd = "";
		String dept_id = "";
		SecUser user = null;

		try {
			logger.debug("start to analysize xml string");
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			NodeList nl_burn = doc.getElementsByTagName("BurnEvent");
			int beanNum = nl_burn.getLength(); // 获取结点数目

			// 判断刻录事件是否为一个
			if (beanNum != 1) {
				logger.warn("beanNum is not equal 1");
				return "201"; // 刻录事件不为一个则返回刻录事件错误的提示
			}
			Node n = nl_burn.item(0); // 获取第一个也就是唯一一个的结点
			event_code = n.getChildNodes().item(0).getTextContent();
			logger.debug("[xml] event_code is :" + event_code);

			// 判断必填参数是否为空
			if (event_code == null || event_code == "") {
				logger.debug("event_code为空");
				logger.warn("event_code lack of value");
				return "202";
			}

			// 获得刻录宣传报道文件
			List<BurnPublicity> fileList = burnMapper.getBurnPublicity(event_code);
			if (fileList.size() <= 0) {
				logger.debug("event_code对应的文件内容为空");
			}
			BurnEvent burnEv = new BurnEvent();
			StringBuffer allFile = new StringBuffer();
			StringBuffer file_level = new StringBuffer();
			StringBuffer ids = new StringBuffer();
			for (int j = 0; j < fileList.size(); j++) {
				BurnPublicity file = fileList.get(j);
				id = file.getId();
				ids.append(id + ",");
				idcard = file.getIdCard();
				event_code = file.getEvent_code();
				cd_serial = file.getCd_serial();
				file_name = file.getFile_name();
				file_contents = file.getFile_contents();
				fileSeclevel = file.getFile_seclevel();

				// 判断中间表中必填参数是否为空
				if (idcard == null || idcard == "" || event_code == null || event_code == "" || file_name == null
						|| file_name == "" || fileSeclevel == null || fileSeclevel == "") {
					logger.warn("required files lack of value");
					return "202";
				}

				try {
					user = userService.getSecUserByIdcard(idcard); // 根据身份证号获取用户
					user_iidd = user.getUser_iidd();
					dept_id = user.getDept_id();
				} catch (Exception e) {
					if (e.getMessage().contains("没有")) {
						logger.warn("id card is not found in database");
						return "203";
					} else {
						logger.warn("id card belongs to more than one user");
						return "204";
					}
				}
				burnEv.setUser_iidd(user_iidd);
				burnEv.setDept_id(dept_id);
				eventCode = user_iidd + "_BURN_yuanjiguan" + event_code;
				burnEv.setEvent_code(eventCode);
				if (file_contents.length <= 0) {
					logger.debug("文本内容为空");
				} else {
					uploadFile(file_contents, file_name, eventCode);
				}

				// 解析出文件名列表和对应的文件密级
				allFile.append(file_name + ":");
				file_level.append(seclvMap.get(fileSeclevel.trim()) + ":");

			}
			// 判断文件列表是否超过500字符
			if (allFile.toString().length() > 500) {
				logger.debug("the length of file list is more than 500 characters");
			}
			file_level.delete(file_level.lastIndexOf(":"), file_level.lastIndexOf(":") + 1);
			allFile.delete(allFile.lastIndexOf(":"), allFile.lastIndexOf(":") + 1);
			burnEv.setFile_seclevel(file_level.toString());
			burnEv.setFile_list(allFile.toString());
			burnEv.setFile_num(fileList.size());
			// 解析出作业密级
			Integer seclv_rank = null;
			SecLevel seclevel = null;
			Integer rank_tmp = null;
			SecLevel seclv_tmp = null;
			String[] secCode = file_level.toString().split(":");
			for (int i = 0; i < secCode.length; i++) {
				seclv_tmp = userService.getSecLevelByCode(Integer.parseInt(secCode[i]));
				rank_tmp = seclv_tmp.getSeclv_rank();
				if (seclv_rank == null || rank_tmp < seclv_rank) {
					seclv_rank = rank_tmp;
					seclevel = seclv_tmp;
				}
			}
			burnEv.setSeclv_code(seclevel.getSeclv_code());
			burnEv.setCd_num(1);
			burnEv.setCycle_type("REMAIN");
			burnEv.setPeriod("S");
			burnEv.setCd_serial(cd_serial);
			burnEv.setApply_time(new Date());
			String jobType_code = "BURN_REMAIN"; // 故意设置为一个固定的值
			JobTypeEnum jobType = JobTypeEnum.valueOf(jobType_code);
			burnEv.setJobType(jobType);
			String job_code = "YuanJiGuanXiTong-" + burnEv.getJobType().getJobTypeCode() + "111222333"; // 所有刻录用同一个job_code
			ProcessJob job = new ProcessJob(job_code, "webservice", "webservice", new Integer(1), burnEv.getJobType(),
					new Date(), "true", "", "", "11111111");
			try {
				basicMapper.addProcessJob(job);
			} catch (Exception e) {
				if (e instanceof DuplicateKeyException) {
					logger.debug("job_code[" + job_code + "] already exist.");
				} else {
					logger.error(e.getMessage());
					e.printStackTrace();
					throw e;
				}
			}
			burnMapper.addBurnEvent(burnEv);
			Map<String, String> map = new HashMap<String, String>();
			map.put("event_code", eventCode);
			map.put("job_code", job_code);
			burnMapper.updateBurnEventJobCode(map);
			insertCommonLog("添加宣传报道刻录申请，文件列表[" + allFile.toString() + "]", user);
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("ids", ids.toString().split(","));
			map1.put("burn_states", "1");
			burnMapper.updateBurnStatus(map1);
			return "101";
		} catch (SAXException e) {
			logger.error(e.getClass().getSimpleName() + ".Message:" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getClass().getSimpleName() + ".Message:" + e.getMessage());
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			logger.error(e.getClass().getSimpleName() + ".Message:" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getClass().getSimpleName() + ".Message:" + e.getMessage());
			e.printStackTrace();// doc容器
		}
		return "301";
	}

	// 将数据库文件存放并加密
	public void uploadFile(byte[] file_contents, String file_name, String eventCode) throws IOException {
		String storePath = "";
		try {
			storePath = PropertyUtil.getBurnFileStorePath();
		} catch (Exception e1) {
			logger.error(e1.getMessage());
			e1.printStackTrace();
		}
		// 创建文件存储路径
		File path = new File(storePath + File.separator + eventCode);
		if (!(path.exists())) {
			logger.info("path[" + path + "] does not exsit, create it.");
			path.mkdirs();
		}
		// 将文件加密并存放到指定的文件夹下
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(storePath + File.separator + eventCode + "\\" + MD5.getStringMD5(file_name));
			EncryptFile.encryptBuffer(file_contents);
			fos.write(file_contents);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

	public void insertCommonLog(String detail, SecUser user) {
		commonOperLog = new CommonOperLog(user.getUser_iidd(), user.getUser_name(), user.getDept_name(), detail, "成功",
				new Date(), Constants.LOG_OPERATION, "127.0.0.1", "127.0.0.1", "admin");
		logService.addCommonOperLog(commonOperLog);
	}
}
