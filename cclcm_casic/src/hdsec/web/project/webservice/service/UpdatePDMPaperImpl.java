package hdsec.web.project.webservice.service;

import hdsec.web.project.basic.mapper.BasicMapper;
import hdsec.web.project.common.ReadXML;
import hdsec.web.project.enter.mapper.EnterMapper;
import hdsec.web.project.ledger.mapper.LedgerMapper;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.print.mapper.PrintMapper;
import hdsec.web.project.user.mapper.UserMapper;
import hdsec.web.project.user.model.RealUser;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.log4j.Logger;

/**
 * 接收PDM系统打印台账信息
 * 
 * @author liuyl 2016-01-25
 */

@WebService(serviceName = "UpdatePDMPaperService")
public class UpdatePDMPaperImpl {

	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private UserMapper userMapper;
	@Resource
	protected PrintMapper printMapper;
	@Resource
	protected LedgerMapper ledgerMapper;
	@Resource
	protected EnterMapper enterMapper;
	@Resource
	protected BasicMapper basicMapper;

	private String xmlPost = "";
	RealUser user = null;

	public String updatePaperState(
			@WebParam(targetNamespace = "http://service.webservice.project.web.hdsec/") String xmlStr) {
		String result = "0";
		String errorLog = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		logger.debug("调用UpdatePDMPaperService接口！");

		if (xmlStr == null) {// xml为空时处理及返回结果
			errorLog = "xml 为空";
			logger.debug("传递参数为空，xml无效！");
			logger.warn("传递参数为空，xml无效！");
			// logger.
			xmlPost = "<?xml version=\"1.0\" encoding=\"utf-8\"?><root><result>" + result + "</result><desc>"
					+ errorLog + "</desc></root>";
			return xmlPost;
		}
		// xml不为空时处理
		try {
			logger.warn("updatePaperState---start to analysize xml string");
			logger.debug("updatePaperState---start to analysize xml string");

			String[] xmlStr_one = xmlStr.split("<data>");
			for (int i = 1; i < xmlStr_one.length; i++) {
				String one_errorLog = "";// "第" + i + "个数据：";
				String one_xml = "<data>";
				one_xml = one_xml + xmlStr_one[i];
				if (i == xmlStr_one.length - 1) {
					int lenght = one_xml.length() - 7;
					one_xml = one_xml.substring(0, lenght);
				}

				ReadXML xml = new ReadXML();
				String code = xml.getParamValue(one_xml, "code", "data/code");
				String dutyuserid = xml.getParamValue(one_xml, "dutyuserid", "data/dutyuserid");
				String dutyusername = xml.getParamValue(one_xml, "dutyusername", "data/dutyusername");
				String operationname = xml.getParamValue(one_xml, "operationname", "data/operationname");
				// String operationtype = xml.getParamValue(one_xml, "operationtype", "data/operationtype");
				String operationtime = xml.getParamValue(one_xml, "operationtime", "data/operationtime");
				String barcode_new = "";
				// 必填项是否为空判断
				if (code == "" || dutyuserid == "" || dutyusername == "" || operationname == "") {

					one_errorLog += "必填参数有空项    ";
					// errorLog += "必填参数有空项    ";
					errorLog += one_errorLog;
					logger.warn("必填参数有空项");
					logger.warn("必填参数有空项! code:" + code + " dutyuserid" + dutyuserid + " dutyusername:" + dutyusername
							+ " operationname" + operationname);
					continue;
				}

				// 检测条码是否在系统中存在，建议条码采用与本系统内条码规则有明显区别的标志位
				try {
					// 截取后24位编码
					int endIndex = code.length();
					if (endIndex < 24) {
						one_errorLog += code + "条码号不符合规定    ";
						errorLog += code + "条码号不符合规定    ";
						logger.warn("条码号不符合规定" + code);
					}
					int beginIndex = endIndex - 24;
					barcode_new = code.substring(beginIndex, endIndex);

					EntityPaper paper = ledgerMapper.getPaperByBarcode(barcode_new);
					if (paper == null) {
						one_errorLog += code + "条码号不存在    ";
						// errorLog += code + "条码号不存在    ";
						logger.warn("条码号不存在" + code);
					}
				} catch (Exception e) {
					one_errorLog += "条码号不存在    ";
					// errorLog += "条码号不存在    ";
					errorLog += one_errorLog;
					logger.error("Exception:" + e.getMessage());
					e.printStackTrace();
				}

				// 判断用户是否存在
				try {
					user = userMapper.getRealUserById(dutyuserid);
					if (user == null) {// 用户id不存在
						one_errorLog += dutyuserid + "无此用户   ";
						// errorLog += dutyuserid + "无此用户   ";
						logger.warn("无此用户" + dutyuserid);
					}
				} catch (Exception e) {
					one_errorLog += "用户id错误    ";
					errorLog += one_errorLog;
					logger.error("Exception:" + e.getMessage());
					e.printStackTrace();
				}

				String username = userMapper.getUserNameByUserId(dutyuserid);
				if (!username.equals(dutyusername)) {// user对应的用户名与输入的用户名不一样
					one_errorLog += dutyusername + "与系统内不符   ";
					// errorLog += dutyusername + "与系统内不符   ";
					logger.warn(dutyusername + "与系统内不符   ");
				}

				if (!one_errorLog.equals("")) {// 如果存在上述错误，则将跳出循环
					errorLog += one_errorLog;
					continue;
				}

				if (!operationname.equals("1") && !operationname.equals("2") && !operationname.equals("3")
						&& !operationname.equals("4")) {
					one_errorLog += "操作类型错误   ";
					// errorLog += "操作类型错误    ";
					logger.warn("操作类型错误");
					logger.warn("操作类型错误! operationname应该为1~4中的数字！此项中 operationname：" + operationname);
				} else {
					try {

						if (operationname.equals("1")) {// 回收
							EntityPaper paper = ledgerMapper.getPaperByBarcode(code);
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("paper_id", paper.getPaper_id().toString());
							map.put("retrieve_time", sdf.parse(operationtime));
							map.put("retrieve_user_iidd", dutyuserid);
							map.put("paper_state", 1);
							ledgerMapper.updateRetrievePaper(map);
						} else if (operationname.equals("2")) {// 外发
							EntityPaper paper = ledgerMapper.getPaperByBarcode(code);
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("paper_id", paper.getPaper_id());
							map.put("duty_user_iidd", paper.getDuty_user_iidd());
							map.put("duty_user_name", paper.getDuty_user_name());
							map.put("duty_dept_id", paper.getDuty_dept_id());
							map.put("duty_dept_name", paper.getDuty_dept_name());
							map.put("retrieve_time", sdf.parse(operationtime));
							map.put("retrieve_user_iidd", dutyuserid);
							map.put("paper_state", 2);// 已外发
							ledgerMapper.updateEntityPaper(map);
						} else if (operationname.equals("3")) {// 归档
							EntityPaper paper = ledgerMapper.getPaperByBarcode(code);
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("paper_id", paper.getPaper_id());
							map.put("duty_user_iidd", dutyuserid);
							map.put("duty_user_name", dutyusername);
							// 获取用户所在部门
							String dept_name = userMapper.getDeptNameByUserId(dutyuserid);
							List<String> dept_id_list = userMapper.getDeptIdByName(dept_name);
							String dept_id = "";
							if (dept_id_list.size() > 0) {
								dept_id = dept_id_list.get(0);
							}
							// end
							map.put("duty_dept_id", dept_id);
							map.put("duty_dept_name", dept_name);
							map.put("retrieve_time", sdf.parse(operationtime));
							map.put("retrieve_user_iidd", dutyuserid);
							map.put("paper_state", 3);// 已归档
							ledgerMapper.updateEntityPaper(map);
						} else if (operationname.equals("4")) {// 销毁
							EntityPaper paper = ledgerMapper.getPaperByBarcode(code);
							String paper_id = paper.getPaper_id() + ",";
							String[] ids = paper_id.split(",");
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("paper_ids", ids);
							map.put("destroy_time", sdf.parse(operationtime));
							map.put("duty_user_iidd", dutyuserid);
							map.put("duty_user_name", dutyusername);
							// 获取用户所在部门
							String dept_name = userMapper.getDeptNameByUserId(dutyuserid);
							List<String> dept_id_list = userMapper.getDeptIdByName(dept_name);
							String dept_id = "";
							if (dept_id_list.size() > 0) {
								dept_id = dept_id_list.get(0);
							}
							// end
							map.put("duty_dept_id", dept_id);
							map.put("duty_dept_name", dept_name);
							ledgerMapper.destroyRetrievedPapers(map);

						} else {
							one_errorLog += "操作类型错误   ";
							// errorLog += "操作类型错误    ";
							logger.warn("操作类型错误");
							logger.warn("操作类型错误! operationname应该为1~4中的数字！此项中 operationname：" + operationname);
						}

					} catch (Exception e) {
						one_errorLog += "更改台账失败 ,条码号：" + code + " ";
						// errorLog += "更改台账失败 ,条码号：" + code + " ";
						errorLog += one_errorLog;
						logger.error("Exception:" + e.getMessage());
						logger.warn("更改台账失败,条码号： " + code);
						e.printStackTrace();
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();// doc容器

		}

		// 返回结果
		if (errorLog.equals("")) {
			// 成功
			result = "1";
			errorLog = "无";
			logger.warn("成功！");
		}
		xmlPost = "<?xml version=\"1.0\" encoding=\"utf-8\"?><root><result>" + result + "</result><desc>" + errorLog
				+ "</desc></root>";
		return xmlPost;

	}
}