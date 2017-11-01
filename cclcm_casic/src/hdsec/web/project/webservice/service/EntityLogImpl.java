package hdsec.web.project.webservice.service;

import hdsec.web.project.copy.service.CopyService;
import hdsec.web.project.enter.service.EnterService;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.service.LedgerService;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
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

@WebService(serviceName = "EntityLogService")
public class EntityLogImpl {
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private UserService userService;
	@Resource
	private LedgerService ledgerService;
	@Resource
	private CopyService copyService;
	@Resource
	private EnterService enterService;

	// 同步文件台账
	@WebMethod
	public String postEntityPaper(
			@WebParam(targetNamespace = "http://service.webservice.project.web.hdsec/") String xmlStr) {

		InputSource is = new InputSource(new StringReader(xmlStr));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Map<String, Object> map = new HashMap<String, Object>();
		String barcode201 = "";
		String barcode202 = "";
		String barcode203 = "";
		String barcode204 = "";
		String barcode205 = "";
		String barcode206 = "";
		String barcode207 = "";
		String barcode208 = "";
		try {
			logger.debug("start to analysize xml string");
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			// NodeList nl_list = doc.getElementsByTagName("list"); // 按文档顺序返回包含在文档中且具有给定标记名称的所有Element的NodeList
			NodeList nl_entity = doc.getElementsByTagName("ENTITYPAPER");
			int beanNum = nl_entity.getLength(); // 获取结点数目
			if (beanNum > 50) {
				logger.warn("the total number is greater than 50");
				return "209";
			}

			for (int i = 0; i < beanNum; i++) {
				Node n = nl_entity.item(i); // 获取第i个结点
				// Node node = nl_list.item(0); // 获取list节点
				String idcard = n.getChildNodes().item(0).getTextContent();
				String user_iidd = "";
				String dept_id = "";
				String user_name = "";
				String dept_name = "";
				String paper_barcode = n.getChildNodes().item(1).getTextContent();
				String seclv_code = n.getChildNodes().item(2).getTextContent();
				String print_time = n.getChildNodes().item(3).getTextContent();
				String file_title = n.getChildNodes().item(4).getTextContent();
				String page_count = n.getChildNodes().item(5).getTextContent();
				String page_size = n.getChildNodes().item(6).getTextContent();
				String color = n.getChildNodes().item(7).getTextContent();
				String print_direct = n.getChildNodes().item(8).getTextContent();
				// String file_num = node.getAttributes().getNamedItem("rowNum").getNodeValue();

				// 判断必填参数是否为空
				if (idcard == null || idcard == "" || paper_barcode == null || paper_barcode == ""
						|| seclv_code == null || seclv_code == "" || print_time == null || print_time == ""
						|| file_title == null || file_title == "" || page_count == null || page_count == "") {
					logger.warn("required fields lack of value");
					if (barcode201 == "") {
						barcode201 = paper_barcode;
					} else {
						barcode201 += "," + paper_barcode;
					}
					continue;
					// return "201";
				}

				// 判断作业密级是否符合格式
				if (Integer.parseInt(seclv_code) != 1 && Integer.parseInt(seclv_code) != 2
						&& Integer.parseInt(seclv_code) != 3 && Integer.parseInt(seclv_code) != 4) {
					logger.warn("seclv_code is wrong");
					if (barcode205 == "") {
						barcode205 = paper_barcode;
					} else {
						barcode205 += "," + paper_barcode;
					}
					continue;
					// return "205";
				}

				// 判断文件页数是否符合格式
				if (!(page_count.matches("[0-9]+") && Integer.parseInt(page_count) > 0)) {
					logger.warn("page_count is wrong");
					if (barcode206 == "") {
						barcode206 = paper_barcode;
					} else {
						barcode206 += "," + paper_barcode;
					}
					continue;
					// return "206";
				}

				// 设置默认纸张类型
				if (page_size == null || page_size == "") {
					page_size = "A4";
				}

				// 判断颜色是否符合格式
				if (color == null || color == "") {
					color = "1";
				} else {
					if (!(color.equalsIgnoreCase("1") || color.equalsIgnoreCase("2"))) {
						logger.warn("color is wrong");
						if (barcode207 == "") {
							barcode207 = paper_barcode;
						} else {
							barcode207 += "," + paper_barcode;
						}
						continue;
						// return "207";
					}
				}

				// 判断横纵向是否符合格式
				if (print_direct == null || print_direct == "") {
					print_direct = "1";
				} else {
					if (!(print_direct.equalsIgnoreCase("1") || print_direct.equalsIgnoreCase("2"))) {
						logger.warn("page_direct is wrong");
						if (barcode208 == "") {
							barcode208 = paper_barcode;
						} else {
							barcode208 += "," + paper_barcode;
						}
						continue;
						// return "208";
					}
				}

				// 判断能否根据身份证号获取用户，并设置用户ID和部门ID
				try {
					SecUser user = userService.getSecUserByIdcard(idcard); // 根据身份证号获取用户
					user_iidd = user.getUser_iidd();
					dept_id = user.getDept_id();
					user_name = user.getUser_name();
					dept_name = user.getDept_name();
				} catch (Exception e) {
					if (e.getMessage().contains("没有")) {
						logger.warn("id card is not found in database");
						if (barcode202 == "") {
							barcode202 = paper_barcode;
						} else {
							barcode202 += "," + paper_barcode;
						}
						continue;
						// return "202";
					} else {
						logger.warn("id card belongs to more than one user");
						if (barcode203 == "") {
							barcode203 = paper_barcode;
						} else {
							barcode203 += "," + paper_barcode;
						}
						continue;
						// return "203";
					}
				}

				// 判断条码唯一性
				EntityPaper paper = null;
				paper = ledgerService.getPaperByBarcode(paper_barcode);
				if (paper != null) {
					logger.warn(" paper_barcode is exist");
					if (barcode204 == "") {
						barcode204 = paper_barcode;
					} else {
						barcode204 += "," + paper_barcode;
					}
					continue;
					// return "204";
				}

				EntityPaper paper_oa = new EntityPaper();
				paper_oa.setPaper_barcode(paper_barcode);
				paper_oa.setEvent_code(null);
				paper_oa.setUser_iidd(user_iidd);
				paper_oa.setUser_name(user_name);
				paper_oa.setDept_id(dept_id);
				paper_oa.setDept_name(dept_name);
				paper_oa.setDuty_user_iidd(user_iidd);
				paper_oa.setDuty_user_name(user_name);
				paper_oa.setDuty_dept_id(dept_id);
				paper_oa.setDuty_dept_name(dept_name);
				paper_oa.setSeclv_code(Integer.parseInt(seclv_code));
				paper_oa.setPrint_time(sdf.parse(print_time));
				paper_oa.setPrint_result("1");
				paper_oa.setFile_title(file_title);
				paper_oa.setProject_code(null);
				paper_oa.setPage_count(Integer.parseInt(page_count));
				paper_oa.setPage_size(page_size);
				paper_oa.setColor(Integer.parseInt(color));
				paper_oa.setPrint_direct(Integer.parseInt(print_direct));
				paper_oa.setPrint_double(null);
				paper_oa.setPaper_state(0);
				paper_oa.setCreate_type("LEADIN");
				paper_oa.setScope("PERSON");
				paper_oa.setJob_code(null);

				try {
					copyService.addPaperledger(paper_oa);
				} catch (Exception e) {
					if (e instanceof DuplicateKeyException) {
						logger.error(e.getMessage());
						throw new Exception("文件台账插入数据库错误");
					}
					logger.info("addPaperledger" + paper_barcode);
				}
			}
			map.put("201", barcode201);
			map.put("202", barcode202);
			map.put("203", barcode203);
			map.put("204", barcode204);
			map.put("205", barcode205);
			map.put("206", barcode206);
			map.put("207", barcode207);
			map.put("208", barcode208);
			if (barcode201 == "" && barcode202 == "" && barcode203 == "" && barcode204 == "" && barcode205 == ""
					&& barcode206 == "" && barcode207 == "" && barcode208 == "") {
				return "101";
			} else {
				String return_str = "";
				if (barcode201 != "") {
					return_str += "201[" + barcode201 + "]";
				}
				if (barcode202 != "") {
					return_str += "202[" + barcode202 + "]";
				}
				if (barcode203 != "") {
					return_str += "203[" + barcode203 + "]";
				}
				if (barcode204 != "") {
					return_str += "204[" + barcode204 + "]";
				}
				if (barcode205 != "") {
					return_str += "205[" + barcode205 + "]";
				}
				if (barcode206 != "") {
					return_str += "206[" + barcode206 + "]";
				}
				if (barcode207 != "") {
					return_str += "207[" + barcode207 + "]";
				}
				if (barcode208 != "") {
					return_str += "208[" + barcode208 + "]";
				}
				logger.warn(return_str);
				return return_str;
			}
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();// doc容器
		}
		logger.warn("Others");
		return "301";
	}

	// 同步光盘台账
	@WebMethod
	public String postEntityCD(@WebParam(targetNamespace = "http://service.webservice.project.web.hdsec/") String xmlStr) {

		InputSource is = new InputSource(new StringReader(xmlStr));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Map<String, Object> map = new HashMap<String, Object>();
		String barcode201 = "";
		String barcode202 = "";
		String barcode203 = "";
		String barcode204 = "";
		String barcode205 = "";

		try {
			logger.debug("start to analysize xml string");
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			// NodeList nl_list = doc.getElementsByTagName("list"); // 按文档顺序返回包含在文档中且具有给定标记名称的所有Element的NodeList
			NodeList nl_entity = doc.getElementsByTagName("ENTITYCD");
			int beanNum = nl_entity.getLength(); // 获取结点数目
			if (beanNum > 50) {
				logger.warn("the total number is greater than 50");
				return "206";
			}
			for (int i = 0; i < beanNum; i++) {
				Node n = nl_entity.item(i); // 获取第i个结点
				String idcard = n.getChildNodes().item(0).getTextContent();
				String user_iidd = "";
				String dept_id = "";
				String user_name = "";
				String dept_name = "";
				String cd_barcode = n.getChildNodes().item(1).getTextContent();
				String seclv_code = n.getChildNodes().item(2).getTextContent();
				String burn_time = n.getChildNodes().item(3).getTextContent();
				String file_list = n.getChildNodes().item(4).getTextContent();

				// 判断必填参数是否为空
				if (idcard == null || idcard == "" || cd_barcode == null || cd_barcode == "" || seclv_code == null
						|| seclv_code == "" || burn_time == null || burn_time == "" || file_list == null
						|| file_list == "") {
					logger.warn("required fields lack of value");
					if (barcode201 == "") {
						barcode201 = cd_barcode;
					} else {
						barcode201 += "," + cd_barcode;
					}
					continue;
					// return "201";
				}

				// 判断作业密级是否符合格式
				if (Integer.parseInt(seclv_code) != 1 && Integer.parseInt(seclv_code) != 2
						&& Integer.parseInt(seclv_code) != 3 && Integer.parseInt(seclv_code) != 4) {
					logger.warn("seclv_code is wrong");
					if (barcode205 == "") {
						barcode205 = cd_barcode;
					} else {
						barcode205 += "," + cd_barcode;
					}
					continue;
					// return "205";
				}

				// 判断能否根据身份证号获取用户，并设置用户ID和部门ID
				try {
					SecUser user = userService.getSecUserByIdcard(idcard); // 根据身份证号获取用户
					user_iidd = user.getUser_iidd();
					dept_id = user.getDept_id();
					user_name = user.getUser_name();
					dept_name = user.getDept_name();
				} catch (Exception e) {
					if (e.getMessage().contains("没有")) {
						logger.warn("id card is not found in database");
						if (barcode202 == "") {
							barcode202 = cd_barcode;
						} else {
							barcode202 += "," + cd_barcode;
						}
						continue;
						// return "202";
					} else {
						logger.warn("id card belongs to more than one user");
						if (barcode203 == "") {
							barcode203 = cd_barcode;
						} else {
							barcode203 += "," + cd_barcode;
						}
						continue;
						// return "203";
					}
				}

				// 判断条码唯一性
				EntityCD cd = null;
				cd = ledgerService.getCDByBarcode(cd_barcode);
				if (cd != null) {
					logger.warn("cd_barcode is exist");
					if (barcode204 == "") {
						barcode204 = cd_barcode;
					} else {
						barcode204 += "," + cd_barcode;
					}
					continue;
					// return "204";
				}

				EntityCD cd_oa = new EntityCD();
				cd_oa.setCd_barcode(cd_barcode);
				cd_oa.setEvent_code(null);
				cd_oa.setUser_iidd(user_iidd);
				cd_oa.setUser_name(user_name);
				cd_oa.setDept_id(dept_id);
				cd_oa.setDept_name(dept_name);
				cd_oa.setDuty_user_iidd(user_iidd);
				cd_oa.setDuty_user_name(user_name);
				cd_oa.setDuty_dept_id(dept_id);
				cd_oa.setDuty_dept_name(dept_name);
				cd_oa.setSeclv_code(Integer.parseInt(seclv_code));
				cd_oa.setBurn_time(sdf.parse(burn_time));
				cd_oa.setBurn_result("1");
				cd_oa.setFile_list(file_list);
				cd_oa.setProject_code(null);
				cd_oa.setCd_state(0);
				cd_oa.setCreate_type("LEADIN");
				cd_oa.setScope("PERSON");
				cd_oa.setScope_dept_id(null);
				cd_oa.setScope_dept_name(null);

				try {
					enterService.addCDledger(cd_oa);
				} catch (Exception e) {
					if (e instanceof DuplicateKeyException) {
						logger.error(e.getMessage());
						throw new Exception("光盘台账插入数据库时出错");
					}
					logger.info("addCDledger" + cd_barcode);
				}
			}

			map.put("201", barcode201);
			map.put("202", barcode202);
			map.put("203", barcode203);
			map.put("204", barcode204);
			map.put("205", barcode205);
			if (barcode201 == "" && barcode202 == "" && barcode203 == "" && barcode204 == "" && barcode205 == "") {
				return "101";
			} else {
				String return_str = "";
				if (barcode201 != "") {
					return_str += "201[" + barcode201 + "]";
				}
				if (barcode202 != "") {
					return_str += "202[" + barcode202 + "]";
				}
				if (barcode203 != "") {
					return_str += "203[" + barcode203 + "]";
				}
				if (barcode204 != "") {
					return_str += "204[" + barcode204 + "]";
				}
				if (barcode205 != "") {
					return_str += "205[" + barcode205 + "]";
				}
				logger.warn(return_str);
				return return_str;
			}
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();// doc容器
		}
		logger.warn("Others");
		return "301";
	}
}
