package hdsec.web.project.webservice.action;

import hdsec.web.project.burn.mapper.BurnMapper;
import hdsec.web.project.burn.model.BurnEvent;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PropertiesFileUtil;
import hdsec.web.project.ledger.mapper.LedgerMapper;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.log4j.Logger;
import org.codehaus.xfire.client.Client;

import com.opensymphony.xwork2.ActionSupport;

public class PostSuccBurnAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private LedgerMapper ledgerMapper;
	@Resource
	private BurnMapper burnMapper;
	@Resource
	private UserService userService;
	private SecUser user = null;
	private EntityCD entity_cd = null;
	private BurnEvent burn_event = null;
	private String cd_barcode = ""; // entity_cd表中的主键字段
	private String idcard = ""; // 身份证号
	private String file_name = "";
	private String seclvcode = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "seclv.config");
	private String xmlStr = "";
	private String urlStr = "";
	private String driver = PropertiesFileUtil.getProperty("hdsec.web.project.config.properties", "jdbc.driver");
	private String url = PropertiesFileUtil.getProperty("hdsec.web.project.config.properties", "jdbc.url");
	private String userName = PropertiesFileUtil.getProperty("hdsec.web.project.config.properties", "jdbc.username");
	private String password = PropertiesFileUtil.getProperty("hdsec.web.project.config.properties", "jdbc.password");
	private String sysName = "";// 刻录集成系统名称

	public void setUrlStr(String urlStr) {
		logger.info("webservice url of ezweb is :" + urlStr);
		this.urlStr = urlStr;
	}

	public String getCd_barcode() {
		return cd_barcode;
	}

	public void setCd_barcode(String cd_barcode) {
		this.cd_barcode = cd_barcode;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	private void setSynFailFlag(String cd_barcode) {
		logger.info("syn fail, set syn_fail = 1");
		Connection conn = null;
		try {
			Class.forName(this.driver);
			conn = DriverManager.getConnection(this.url, this.userName, this.password);
			if (conn == null) {
				logger.info("Create JDBC Connection Fail");
			} else {
				logger.info("Create JDBC Connection Successful");
				PreparedStatement ps = conn.prepareStatement("update entity_cd set syn_fail=1 where cd_barcode='"
						+ cd_barcode + "'");
				int cnt = ps.executeUpdate();
				logger.info("update rows :" + cnt);
			}
		} catch (Exception e) {
			this.logger.error(e.getClass().getSimpleName() + ".Message:" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				this.logger.error(e.getClass().getSimpleName() + ".Message:" + e.getMessage());
			}
		}
	}

	@Override
	public String execute() throws Exception {
		logger.debug("start to get entity_cd");
		logger.debug("cd_barcode is :" + cd_barcode);
		try {
			entity_cd = ledgerMapper.getCDByBarcode(cd_barcode);
			if (entity_cd == null) {
				logger.warn("cd_barcode does not exist in entity_cd");
				throw new Exception("cd_barcode does not exist in entity_cd");
			} else {
				logger.debug("success to get entit_cd");
			}
			logger.debug("event_code is :" + entity_cd.getEvent_code());
			burn_event = burnMapper.getBurnEventByBurnCode(entity_cd.getEvent_code());
			if (burn_event == null) {
				logger.warn("event_code does not exist in event_burn");
				throw new Exception("event_code does not exist in event_burn");
			} else {
				logger.debug("success to get burn_event");
			}
			logger.debug("get seclvconifg from config file");
			// 创建密级映射
			Map<String, String> seclvMap = new HashMap<String, String>();
			String[] seclv;
			// 检查密级配置是否正确
			try {
				seclv = seclvcode.split(",");
			} catch (NullPointerException e) {
				logger.warn("seclvcode need config");
				throw new Exception("seclv config is wrong");
			}
			for (int i = 0; i < seclv.length; i++) {
				String[] oneSeclv = seclv[i].split(":");
				// 检测配置文件中密级是否正确配置
				if (oneSeclv.length != 3) {
					logger.warn("seclv config is wrong");
					throw new Exception("seclv config is wrong");
				}
				seclvMap.put(oneSeclv[2], oneSeclv[0]);
			}
			logger.debug("get seclvcode mapping information successful");
			logger.debug("user_iidd is :" + burn_event.getUser_iidd());
			user = userService.getSecUserByUid(burn_event.getUser_iidd());
			idcard = user.getIdCard();
			if (idcard == null || idcard.length() == 0) {
				logger.warn("idcard does not exist");
				throw new Exception("idcard does not exist");
			}
			logger.debug("start to get file_name");
			// 拼装成file_name
			String[] file_list = burn_event.getFile_list().split(":");
			String[] seclv_list = burn_event.getFile_seclevel().split(":");
			StringBuffer fileNameBuffer = new StringBuffer();
			for (int i = 0; i < file_list.length; i++) {
				fileNameBuffer.append("[");
				if (seclvMap.get(seclv_list[i]).toString() == null) {
					logger.warn("seclvcode mapping is wrong");
					throw new Exception("seclvcode mapping is wrong");
				}
				fileNameBuffer.append(seclvMap.get(seclv_list[i]).toString());
				fileNameBuffer.append("]");
				fileNameBuffer.append(file_list[i].substring(4)); // 去除文件名中的类似[非密],重新拼接
				if (i != file_list.length - 1) {
					fileNameBuffer.append("|");
				}
			}
			file_name = fileNameBuffer.toString();
			logger.debug("get file_name successful");
			// 拼接传过去的xml字符串
			if (sysName.equals("yuanjiguan")) {
				xmlStr = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><data><list type=\"BurnState\" rowNum=\""
						+ file_list.length + "\" SecrecyLeavle=\""
						+ seclvMap.get(burn_event.getSeclv_code().toString()) + "\" >" + "<BurnEvent><EventCode>"
						+ burn_event.getEvent_code() + "</EventCode><IdCard>" + idcard
						+ "</IdCard><FileName>[F]zhanweimingcheng.txt</FileName><BurnState>"
						+ entity_cd.getBurn_result() + "</BurnState><RecordNum>1</RecordNum><SerialNumber>"
						+ entity_cd.getCd_barcode() + "</SerialNumber><BurnDate>" + entity_cd.getBurn_time()
						+ "</BurnDate></BurnEvent></list></data></root>";
			} else {
				xmlStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?><list type=\"BurnState\" rowNum=\""
						+ file_list.length + "\" SecrecyLeavle=\""
						+ seclvMap.get(burn_event.getSeclv_code().toString()) + "\" >" + "<BurnEvent><EventCode>"
						+ burn_event.getEvent_code() + "</EventCode><IdCard>" + idcard
						+ "</IdCard><FileName>[F]zhanweimingcheng.txt</FileName><BurnState>"
						+ entity_cd.getBurn_result() + "</BurnState><RecordNum>1</RecordNum><SerialNumber>"
						+ entity_cd.getCd_barcode() + "</SerialNumber><BurnDate>" + entity_cd.getBurn_time()
						+ "</BurnDate></BurnEvent></list>";
			}

			logger.info("success to create xmlStr :" + this.xmlStr);
			logger.debug("start to call ProxyBurnStateService");
			try {
				logger.debug("urlStr is :" + this.urlStr);
				if (sysName.equals("yuanjiguan")) {
					Service service = new Service();
					Call call = (Call) service.createCall();
					call.setTargetEndpointAddress(urlStr);
					call.setOperationName("runBiz");
					call.addParameter("packageName", XMLType.XSD_STRING, ParameterMode.IN);
					call.addParameter("unitId", XMLType.XSD_STRING, ParameterMode.IN);
					call.addParameter("processName", XMLType.XSD_STRING, ParameterMode.IN);
					call.addParameter("bizDataXML", XMLType.XSD_STRING, ParameterMode.IN);
					call.setReturnType(XMLType.XSD_STRING);
					String[] param = { "SECRECY_common", "0", "bizFlackReportBurn.bizReceive", xmlStr };
					Object resp = call.invoke(param);
					if (!resp.toString().contains("<ReturnStr>101</ReturnStr>")) {
						logger.error("CD info with cd_barcode[" + entity_cd.getCd_barcode()
								+ "] post fail, result code is:" + resp.toString());
						throw new Exception("CD info with cd_barcode[" + this.entity_cd.getCd_barcode()
								+ "] post fail, result code is:" + resp.toString());
					}
				} else {
					Client client = new Client(new URL(urlStr));
					Object[] result = client.invoke("ProxyBurnStateServicePort", new Object[] { xmlStr });
					String ret = String.valueOf(result[0]);
					logger.info("Result of calling ProxyBurnStateServicePort is" + ret);
					if (!ret.startsWith("101")) {
						logger.error("CD info with cd_barcode[" + entity_cd.getCd_barcode()
								+ "] post fail, result code is:" + ret);
						throw new Exception("CD info with cd_barcode[" + this.entity_cd.getCd_barcode()
								+ "] post fail, result code is:" + ret);
					}
				}
				logger.debug("success to call ProxyBurnStateService");
			} catch (Exception e) {
				logger.error(e.getClass().getSimpleName() + ".Message:" + e.getMessage());
				throw e;
			}
		} catch (Exception e) {
			logger.error(e.getClass().getSimpleName() + ".Message:" + e.getMessage());
			setSynFailFlag(this.cd_barcode);
		}
		return null;
	}
}
