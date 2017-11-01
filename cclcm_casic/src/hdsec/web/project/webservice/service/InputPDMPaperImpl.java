package hdsec.web.project.webservice.service;

import hdsec.web.project.basic.mapper.BasicMapper;
import hdsec.web.project.common.ReadXML;
import hdsec.web.project.enter.mapper.EnterMapper;
import hdsec.web.project.ledger.mapper.LedgerMapper;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.print.mapper.PrintMapper;
import hdsec.web.project.user.mapper.UserMapper;
import hdsec.web.project.user.model.RealUser;
import hdsec.web.project.user.model.SecLevel;

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
 * @author liuyl 2015-11-12
 */

@WebService(serviceName = "PDMPaperService")
public class InputPDMPaperImpl {

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

	public String sendRequestPrintFile(
			@WebParam(targetNamespace = "http://service.webservice.project.web.hdsec/") String xmlStr) {
		String result = "0";
		String errorLog = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		logger.debug("调用PDM集成接口！");

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
			logger.warn("sendRequestPrintFile---start to analysize xml string");
			logger.debug("sendRequestPrintFile---start to analysize xml string");

			String[] xmlStr_one = xmlStr.split("<data>");
			for (int i = 1; i < xmlStr_one.length; i++) {
				String one_errorLog = "第" + i + "个数据：";
				String one_xml = "<data>";
				one_xml = one_xml + xmlStr_one[i];
				if (i == xmlStr_one.length - 1) {
					int lenght = one_xml.length() - 7;
					one_xml = one_xml.substring(0, lenght);
				}

				ReadXML xml = new ReadXML();
				String user_id = xml.getParamValue(one_xml, "userid", "data/userid");
				String user_name = xml.getParamValue(one_xml, "username", "data/username");
				String print_time = xml.getParamValue(one_xml, "requesttime", "data/requesttime");
				String seclv_code = xml.getParamValue(one_xml, "secrettype", "data/secrettype");
				String barcode = xml.getParamValue(one_xml, "code", "data/code");
				String file_title = xml.getParamValue(one_xml, "filename", "data/filename");
				String print_double = xml.getParamValue(one_xml, "printdouble", "data/printdouble");
				String print_color = xml.getParamValue(one_xml, "printcolor", "data/printcolor");
				String paper_size = xml.getParamValue(one_xml, "papertype", "data/papertype");
				String page_count = xml.getParamValue(one_xml, "pagecount", "data/pagecount");
				String print_result = xml.getParamValue(one_xml, "result", "data/result");
				String barcode_new = "";
				// 必填项是否为空判断
				if (user_id.equals("") || user_name.equals("") || print_time.equals("") || seclv_code.equals("")
						|| barcode.equals("") || file_title.equals("") || print_double.equals("")
						|| print_color.equals("") || paper_size.equals("") || page_count.equals("")) {

					one_errorLog += "必填参数有空项    ";
					// errorLog += "必填参数有空项    ";
					logger.warn("必填参数有空项");
					logger.warn("必填参数有空项! userid:" + user_id + "username" + user_name + " requesttime:" + print_time
							+ "secrettype" + seclv_code + "code" + barcode + "filename" + file_title + "printdouble"
							+ print_double + "printcolor" + print_color + "papertype" + paper_size + "pagecount"
							+ page_count);
					errorLog += one_errorLog;
					continue;
				}
				// 判断用户是否存在
				try {
					user = userMapper.getRealUserById(user_id);
					if (user == null) {// 用户id不存在
						one_errorLog += user_id + "无此用户   ";
						// errorLog += user_id + "无此用户   ";
						logger.warn("无此用户" + user_id);
					}
				} catch (Exception e) {
					one_errorLog += "用户id错误    ";
					errorLog += one_errorLog;
					logger.error("Exception:" + e.getMessage());
					e.printStackTrace();
				}

				String username = userMapper.getUserNameByUserId(user_id);
				if (!username.equals(user_name)) {// user对应的用户名与输入的用户名不一样
					one_errorLog += user_name + "与系统内不符   ";
					// errorLog += user_name + "与系统内不符   ";
					logger.warn(user_name + "与系统内不符   ");
				}

				// 密级是否符合规范
				try {
					SecLevel secLevel = userMapper.getSecLevelByCode(Integer.parseInt(seclv_code));
					if (secLevel == null) {
						one_errorLog += seclv_code + "无此密级    ";
						// errorLog += seclv_code + "无此密级    ";
						logger.warn("无此密级" + seclv_code);
					}
				} catch (Exception e) {
					one_errorLog += "密级代码错误    ";
					// errorLog += "密级代码错误    ";
					errorLog += one_errorLog;
					logger.error("Exception:" + e.getMessage());
					e.printStackTrace();
				}

				// 检测条码是否在系统中已经存在，建议条码采用与本系统内条码规则有明显区别的标志位
				try {
					// 截取后24位编码
					int endIndex = barcode.length();
					if (endIndex < 24) {
						one_errorLog += barcode + "条码号不符合规定    ";
						// errorLog += barcode + "条码号不符合规定    ";
						logger.warn("条码号不符合规定" + barcode);
					}
					int beginIndex = endIndex - 24;
					barcode_new = barcode.substring(beginIndex, endIndex);

					EntityPaper paper = ledgerMapper.getPaperByBarcode(barcode_new);
					if (paper != null) {
						one_errorLog += barcode + "条码号已存在    ";
						// errorLog += barcode + "条码号已存在    ";
						logger.warn("条码号已存在" + barcode);
					}
				} catch (Exception e) {
					one_errorLog += "条码号已存在    ";
					// errorLog += "条码号已存在    ";
					errorLog += one_errorLog;
					logger.error("Exception:" + e.getMessage());
					e.printStackTrace();
				}

				// 单双面检测
				try {
					if (!print_double.equals("1") && !print_double.equals("2")) {
						one_errorLog += print_double + "单双面设置错误    ";
						// errorLog += print_double + "单双面设置错误    ";
						logger.warn("单双面设置错误" + barcode);
					}
				} catch (Exception e) {
					one_errorLog += "单双面设置错误    ";
					// errorLog += "单双面设置错误    ";
					errorLog += one_errorLog;
					logger.error("Exception:" + e.getMessage());
					e.printStackTrace();
				}

				if (!one_errorLog.equals("")) {// 如果存在上述错误，则将跳出循环
					errorLog += one_errorLog;
					continue;
				}

				// 将数据插入台账中
				try {
					String dept_name = userMapper.getDeptNameByUserId(user_id);
					List<String> dept_id_list = userMapper.getDeptIdByName(dept_name);
					String dept_id = "";
					if (dept_id_list.size() > 0) {
						dept_id = dept_id_list.get(0);
					}

					Map<String, Object> map = new HashMap<String, Object>();
					String seclv_codes = seclv_code + ",";
					String[] codes = seclv_codes.split(",");
					map.put("codes", codes);
					List<String> seclv_name_list = basicMapper.getSeclvNameByCodes(map);
					String seclv_name = "";
					if (seclv_name_list.size() > 0) {
						seclv_name = seclv_name_list.get(0);
					}
					EntityPaper paper = new EntityPaper(barcode_new, "", user_id, user_name, dept_id, dept_name,
							user_id, user_name, dept_id, dept_name, Integer.parseInt(seclv_code), seclv_name,
							sdf.parse(print_time), "", print_result, file_title, "", "", "",
							Integer.parseInt(page_count), paper_size, Integer.parseInt(print_color), null,
							Integer.parseInt(print_double), 0, "", "", "", null, null, "", "", null, "", "", null, "",
							"", "", "LEADIN", "PERSON", "", "");

					enterMapper.addPaperledger(paper);// 添加台账
				} catch (Exception e) {
					one_errorLog += "添加台账失败 ,条码号：" + barcode + " ";
					// errorLog += "添加台账失败 ,条码号：" + barcode + " ";
					errorLog += one_errorLog;
					logger.error("Exception:" + e.getMessage());
					logger.warn("添加台账失败,条码号： " + barcode);
					e.printStackTrace();
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