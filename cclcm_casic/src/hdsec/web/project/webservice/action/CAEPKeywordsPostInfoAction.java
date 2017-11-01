package hdsec.web.project.webservice.action;

import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.common.util.PropertiesFileUtil;
import hdsec.web.project.print.mapper.PrintMapper;
import hdsec.web.project.print.service.PrintService;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class CAEPKeywordsPostInfoAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private UserService userService;
	@Resource
	private PrintService printService;
	@Resource
	protected PrintMapper printMapper;
	private SecUser user = null;
	private String idcard = ""; // 身份证号
	private String driver = PropertiesFileUtil.getProperty("hdsec.web.project.config.properties", "jdbc.driver");
	private String url = PropertiesFileUtil.getProperty("hdsec.web.project.config.properties", "jdbc.url");
	private String userName = PropertiesFileUtil.getProperty("hdsec.web.project.config.properties", "jdbc.username");
	private String password = PropertiesFileUtil.getProperty("hdsec.web.project.config.properties", "jdbc.password");
	private String filenameMD5 = ""; // 打印文件md5值
	private String userid = ""; // 提交打印文件的用户账号
	private String htp = "";
	private String returnurl = "";

	public void setReturnurl(String returnurl) {
		this.returnurl = returnurl;
	}

	public void setHtp(String htp) {
		this.htp = htp;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFilenameMD5() {
		return filenameMD5;
	}

	public void setFilenameMD5(String filenameMD5) {
		this.filenameMD5 = filenameMD5;
	}

	/*
	 * public void setUrlStr(String urlStr) { logger.info("webservice url of ezweb is :" + urlStr); this.urlStr =
	 * urlStr; }
	 */

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	/*
	 * public void setSysName(String sysName) { this.sysName = sysName; }
	 */

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
		logger.debug("start to get filenameMD5");
		logger.debug("filenameMD5 is :" + filenameMD5);

		logger.debug("user_iidd is :" + userid);
		logger.debug("start to get file_name");

		// 获取打印、刻录文件路径
		String storeBurnPath = PropertyUtil.getBurnFileStorePath();
		String storePrintBmpZipPath = storeBurnPath.substring(0, storeBurnPath.length() - 7);
		storePrintBmpZipPath = storePrintBmpZipPath.substring(1);
		logger.debug("storePrintBmpZipPath is :" + storePrintBmpZipPath);
		String tempZip = "/printbmp";
		String tempPath = storePrintBmpZipPath + tempZip;
		logger.debug("zip filepath is :" + tempPath);

		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 接口地址
			String apiUrl = htp;
			HttpPost httppost = new HttpPost(apiUrl);
			logger.debug("parameter 1 ===== apiUrl is :" + apiUrl);

			// 文件地址
			// String filePath = tempPath + "/" + filenameMD5;
			String filePath = "/printbmp/" + filenameMD5;
			logger.debug("parameter 2 ===== filePath is :" + filePath);

			// 要上传的文件
			FileBody file = new FileBody(new File(filePath));

			// 事务编号，可为空，最大32位
			String temprandomid = UUID.randomUUID().toString().replaceAll("-", "");
			StringBody transId = new StringBody(temprandomid, ContentType.TEXT_PLAIN);
			logger.debug("parameter 3 ===== transId is :" + temprandomid);

			// 将本次调用的事务编号等相关信息插入数据库keyword_print表
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("tid", temprandomid);
			map1.put("st_filename", filenameMD5);
			map1.put("user_iidd", userid);
			printMapper.AddKeywordPrintEvent(map1);

			// 事务类型，为数组，在这里分两个stringbody传输，20文件隐藏检查，30内容识别检查
			// StringBody transType1 = new StringBody("20", ContentType.TEXT_PLAIN);
			StringBody transType2 = new StringBody("30", ContentType.TEXT_PLAIN);
			// logger.debug("parameter 4 ===== transType1 is :" + transType1);
			logger.debug("parameter 4 ===== transType2 is :" + transType2);

			// 返回类型，默认为1，全部返回
			StringBody returnType = new StringBody("1", ContentType.TEXT_PLAIN);
			logger.debug("parameter 5 ===== returnType is :" + returnType);

			// 通知地址，接入业务系统提供的可访问的http url，事务执行完毕引擎通过该地址通知结果
			String returnurlStr = returnurl + filenameMD5;
			logger.debug("通知地址  6 ===== returnurl is :" + returnurl);
			StringBody notifyUrl = new StringBody(returnurlStr, ContentType.TEXT_PLAIN);
			logger.debug("parameter 7 ===== notifyUrl is :" + notifyUrl);

			// 文件数量，目前只支持1个文件
			StringBody fileCount = new StringBody("1", ContentType.TEXT_PLAIN);
			logger.debug("parameter 8 ===== fileCount is :" + fileCount);

			// 文件传输方式 1http上传，如果该方式上传，则必须有file这个字段。2共享上传，如果是该方式，则fileName字段必填 。 3new http下载方式上传。
			// StringBody fileTransType_http = new StringBody("1", ContentType.TEXT_PLAIN);
			// StringBody fileTransType_smb = new StringBody("2", ContentType.TEXT_PLAIN);
			StringBody fileTransType_newhttp = new StringBody("3", ContentType.TEXT_PLAIN);
			logger.debug("parameter 9 ===== fileTransType_newhttp is :" + fileTransType_newhttp);

			// 文件名称，fileTransType采用共享方式提交事务时必须指定该参数
			StringBody fileName = null;

			// 判断文件后缀名
			String suffix = filenameMD5.substring(filenameMD5.lastIndexOf(".") + 1);
			if (suffix.equals("ps")) {

				fileName = new StringBody(filenameMD5 + ".pdf", ContentType.TEXT_PLAIN);// filePath
			} else {
				fileName = new StringBody(filenameMD5, ContentType.TEXT_PLAIN);// filePath
			}
			logger.debug("parameter 10 ===== fileName is :" + fileName);

			StringBody isOcr = new StringBody("1", ContentType.TEXT_PLAIN);
			logger.debug("parameter 11 ===== isOcr is :" + isOcr);

			// 打印服务器在邦辰中的标识
			StringBody partnerSN = new StringBody("1001", ContentType.TEXT_PLAIN);
			logger.debug("parameter 12 ===== partnerSN is :" + partnerSN);

			// 接口中增加解密标识—— 打印文件 ——不需要DLP平台进行解密操作 [20170323]
			StringBody isNeedDecrypt = new StringBody("0", ContentType.TEXT_PLAIN);
			logger.debug("parameter 13==========isNeedDeccrypt is :" + isNeedDecrypt);

			MultipartEntityBuilder builder = MultipartEntityBuilder.create();

			/** 支持中文字符设置开始 **********************************************************/
			// builder.setCharset(Charset.forName(HTTP.UTF_8));//设置请求的编码格式
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);// 设置浏览器兼容模式
			builder.setCharset(CharsetUtils.get("UTF-8"));
			/** 支持中文字符设置结束 **********************************************************/

			builder.addPart("transId", transId); // 事务id

			// builder.addPart("transType", transType1); // 文件隐藏检查
			builder.addPart("transType", transType2);// 内容识别检查

			builder.addPart("returnType", returnType); // 返回类型

			builder.addPart("notifyUrl", notifyUrl); // 通知地址

			builder.addPart("fileCount", fileCount); // 文件数量

			// 如果是http上传，则使用该代码，默认使用http上传
			// builder.addPart("fileTransType", fileTransType_http);
			// builder.addPart("file", file); // 文件
			// // .setCharset(CharsetUtils.get("UTF-8"))

			// 如果是smb上传，则使用该代码
			builder.addPart("fileTransType", fileTransType_newhttp);
			builder.addPart("fileName", fileName); // 文件名

			builder.addPart("isOcr", isOcr); // 文件ocr检查
			builder.addPart("partnerSN", partnerSN);// 打印系统在邦辰系统中的标识
			builder.addPart("isNeedDecypt", isNeedDecrypt);// 接口中增加是否需要解密标识

			HttpEntity reqEntity = builder.build();
			httppost.setEntity(reqEntity);

			logger.info("<<-----------------------------------------------------");
			logger.info("executing request " + httppost.getRequestLine());

			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				logger.info(response.getStatusLine());

				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					String json = EntityUtils.toString(resEntity);
					logger.info("提交事务接口调用返回值：\r\n" + json);

					JSONObject jsonObject = JSONObject.fromObject(json);
					int status = jsonObject.getInt("status");
					if (status == 1) {
						printService.updateKeywordPrintCheckresultByTid(temprandomid, "Checking");
					} else {
						printService.updateKeywordPrintCheckresultByTid(temprandomid, "RequestFailed");
					}

					// 将本次接口调用成功与否的返回值插入数据库表keyword_print中
					Map<String, Object> map2 = new HashMap<String, Object>();
					map2.put("tid", temprandomid);
					map2.put("call_result", json);
					printMapper.updateKeywordPrintCallresultByTid(map2);
				}
				EntityUtils.consume(resEntity);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		return null;
	}
}
