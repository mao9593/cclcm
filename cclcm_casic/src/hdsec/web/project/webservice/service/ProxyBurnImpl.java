package hdsec.web.project.webservice.service;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.basic.mapper.BasicMapper;
import hdsec.web.project.burn.mapper.BurnMapper;
import hdsec.web.project.burn.model.BurnEvent;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PropertiesFileUtil;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

@WebService(serviceName = "proxyBurnService")
public class ProxyBurnImpl {
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private BurnMapper burnMapper;
	@Resource
	private BasicMapper basicMapper;
	@Resource
	private UserService userService;
	String seclvcode = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "seclv.config");

	// private BurnEvent burnEvent;

	/*
	 * 接受刻录申请成功的消息
	 * 
	 * @author huyong
	 */
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
		try {
			logger.debug("start to analysize xml string");
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			NodeList nl_list = doc.getElementsByTagName("list"); // 按文档顺序返回包含在文档中且具有给定标记名称的所有Element的NodeList
			NodeList nl_burn = doc.getElementsByTagName("BurnEvent");
			int beanNum = nl_burn.getLength(); // 获取结点数目

			// 判断刻录事件是否为一个
			if (beanNum != 1) {
				logger.warn("beanNum is not equal 1");
				return "201"; // 刻录事件不为一个则返回刻录事件错误的提示
			}
			Node n = nl_burn.item(0); // 获取第一个也就是唯一一个的结点
			Node node = nl_list.item(0); // 获取list节点
			String idcard = n.getChildNodes().item(0).getTextContent();
			logger.debug("[xml] idcard is :" + idcard);
			String user_iidd = "";
			String dept_id = "";
			String event_code = n.getChildNodes().item(1).getTextContent();
			logger.debug("[xml] event_code is :" + event_code);
			String seclv_code = n.getChildNodes().item(2).getTextContent();
			logger.debug("[xml] seclv_code is :" + seclv_code);
			String cd_num = n.getChildNodes().item(3).getTextContent();
			logger.debug("[xml] cd_num is :" + cd_num);
			String cycle_type = n.getChildNodes().item(4).getTextContent();
			logger.debug("[xml] cycle_type is :" + cycle_type);
			String period = n.getChildNodes().item(5).getTextContent();
			logger.debug("[xml] period is :" + period);
			String use_cd_serial = n.getChildNodes().item(6).getTextContent();
			logger.debug("[xml] use_cd_serial is :" + use_cd_serial);
			String cd_serial = n.getChildNodes().item(7).getTextContent();
			logger.debug("[xml] cd_serial is :" + cd_serial);
			String file_name = n.getChildNodes().item(8).getTextContent();
			logger.debug("[xml] file_name is :" + file_name);
			String file_num = node.getAttributes().getNamedItem("rowNum").getNodeValue();
			logger.debug("[xml] file_num is :" + file_num);
			String apply_time = n.getChildNodes().item(9).getTextContent();
			logger.debug("[xml] apply_time is :" + apply_time);

			// 判断必填参数是否为空
			if (idcard == null || idcard == "" || event_code == null || event_code == "" || seclv_code == null
					|| seclv_code == "" || cd_num == null || cd_num == "" || use_cd_serial == null
					|| use_cd_serial == "" || file_name == null || file_name == "" || apply_time == null
					|| apply_time == "") {
				logger.warn("required files lack of value");
				return "202";
			}

			// 判断能否身份证号获取用户，并设置用户ID和部门ID
			try {
				SecUser user = userService.getSecUserByIdcard(idcard); // 根据身份证号获取用户
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
			// 判断作业密级是否符合格式
			if (!seclvMap.containsKey(seclv_code)) {
				return "205";
			}

			// 判断刻录数量是否符合格式
			if (!(cd_num.matches("[0-9]+") && Integer.parseInt(cd_num) > 0)) {
				return "206";
			}

			// 判断去向是否符合格式
			if (cycle_type == null || cycle_type == "") {
				cycle_type = "REMAIN";
			} else {
				if (!(cycle_type.equalsIgnoreCase("REMAIN") || cycle_type.equalsIgnoreCase("FILE") || cycle_type
						.equalsIgnoreCase("SEND"))) {
					return "207";
				}
			}

			// 判断周期是否符合格式
			if (period == null || period == "") {
				period = "S";
			} else {
				if (!(period.equalsIgnoreCase("S") || period.equalsIgnoreCase("L"))) {
					return "208";
				}
			}

			// 判断光盘标志位是否符合格式,当光盘标志位为1，光盘编号必填
			if (use_cd_serial.matches("[0-1]")) {
				if (Integer.parseInt(use_cd_serial) == 1) {
					if (cd_serial == null || cd_serial == "") {
						return "202";
					}
				}
			} else {
				return "209";
			}

			BurnEvent burnEv = new BurnEvent();
			burnEv.setUser_iidd(user_iidd);
			burnEv.setDept_id(dept_id);
			burnEv.setEvent_code(event_code);
			burnEv.setSeclv_code(Integer.parseInt(seclvMap.get(seclv_code).toString()));
			burnEv.setCd_num(Integer.parseInt(cd_num));
			burnEv.setCycle_type(cycle_type);
			burnEv.setPeriod(period);
			if (Integer.parseInt(use_cd_serial) == 1) {
				burnEv.setCd_serial(cd_serial);
			}

			// 解析出文件名列表和对应的文件密级
			String[] file_list = file_name.split("[|]");
			StringBuffer allFile = new StringBuffer();
			StringBuffer file_level = new StringBuffer();
			file_level.append(seclvMap.get(file_list[0].substring(1, 2)).toString());
			allFile.append("[" + seclvMap.get(seclvMap.get(file_list[0].substring(1, 2))) + "]"
					+ file_list[0].substring(3));
			for (int i = 1; i < file_list.length; i++) {
				file_level.append(":" + seclvMap.get(file_list[i].substring(1, 2)).toString());
				allFile.append(":[" + seclvMap.get(seclvMap.get(file_list[i].substring(1, 2))) + "]"
						+ file_list[i].substring(3));
			}
			logger.debug("file_level is :" + file_level.toString());
			logger.debug("allFile is :" + allFile.toString());
			// 判断文件列表是否超过500字符
			if (allFile.toString().length() > 500) {
				logger.debug("the length of file list is more than 500 characters");
				return "301";
			}
			burnEv.setFile_seclevel(file_level.toString());
			burnEv.setFile_list(allFile.toString());
			// 判断通过文件列表获取的文件数量是否等于list中传来的rowNum
			if (Integer.parseInt(file_num) != file_list.length) {
				logger.debug("the file num is not equal to rowNum");
				return "301";
			}
			burnEv.setFile_num(file_list.length);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			burnEv.setApply_time(sdf.parse(apply_time));
			String jobType_code = "BURN_REMAIN"; // 故意设置为一个固定的值
			JobTypeEnum jobType = JobTypeEnum.valueOf(jobType_code);
			burnEv.setJobType(jobType);
			String job_code = "BaoMiXiTong-" + burnEv.getJobType().getJobTypeCode() + "11223344"; // 所有刻录用同一个job_code
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
			logger.info("data analized from xlmStr is right, then insert it into db");
			burnMapper.addBurnEvent(burnEv);
			Map<String, String> map = new HashMap<String, String>();
			map.put("event_code", burnEv.getEvent_code());
			map.put("job_code", job_code);
			burnMapper.updateBurnEventJobCode(map);
			logger.info("addBurnEvent successly with event_code:" + burnEv.getEvent_code());
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
}
