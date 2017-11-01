package hdsec.web.project.burn.action;

import hdsec.web.project.burn.model.RiskKeywordsBurn;
import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.common.util.MD5;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;

/**
 * 查看文件关键字检索结果
 * 
 * @author haojia
 * 
 */
public class ShowRisklistBurnAction extends BurnBaseAction {
	private static final long serialVersionUID = 1L;
	private String secpass = "";
	private String event_code = "";
	private String filename = "";
	private String user_iidd = "";
	private List<RiskKeywordsBurn> eventList = null;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public List<RiskKeywordsBurn> getEventList() {
		return eventList;
	}

	public void setEventList(List<RiskKeywordsBurn> eventList) {
		this.eventList = eventList;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getSecpass() {
		return secpass;
	}

	public void setSecpass(String secpass) {
		this.secpass = secpass;
	}

	public String getRemoteAddr() {
		return getRequest().getScheme() + "://" + getRequest().getServerName() + ":" + getRequest().getServerPort();
	}

	@Override
	public String executeFunction() throws Exception {

		String searchTID = "";
		filename = URLDecoder.decode(getRequest().getParameter("filename"), "UTF-8");

		// 将event_code与filename拼接
		String burnEventcodeFilename = MD5.getStringMD5(filename);

		// 获取文件的关键字检查唯一性id
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("file_name", burnEventcodeFilename);
		map.put("event_code", event_code);
		String transIDTemp = burnService.getBurnTransID(map);
		logger.info("transIDTemp111111:" + transIDTemp);

		if (transIDTemp == null || transIDTemp == "") {
			Map<String, Object> map4 = new HashMap<String, Object>();
			map4.put("file_name", filename);
			map4.put("event_code", event_code);
			transIDTemp = burnService.getBurnTransID(map4);
			logger.info("transIDTemp222222:" + transIDTemp);
		}

		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 获取关键字平台检索结果接口url
			String LDAP_URL = PropertyUtil.getDlpQueryUrl();

			// LDAP访问地址 //
			logger.debug("查询关键字检索结果=====LDAP_URL is:" + LDAP_URL);

			String apiUrl = LDAP_URL;
			HttpPost httppost = new HttpPost(apiUrl);
			logger.debug("parameter 1 ===== apiUrl is :" + apiUrl);

			StringBody transId = new StringBody(transIDTemp, ContentType.TEXT_PLAIN);
			logger.debug("parameter 3 ===== transId is :" + transIDTemp);

			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			// 支持中文字符设置开始

			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);// 设置浏览器兼容模式
			builder.setCharset(CharsetUtils.get("UTF-8"));
			// 支持中文字符设置结束

			builder.addPart("transId", transId); // 事务id

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

					logger.info("接收到的json串数据：\r\n" + json);

					if (json != null && !json.equals("")) {

						// 开始解析返回的json串
						JSONObject jsonObject = JSONObject.fromObject(json);

						String message = jsonObject.getString("message");
						logger.info("解析json数据=====message：" + message);

						boolean isSuccess = false;
						if (message != null && message != "") {
							if (message.equals("查询成功")) {
								isSuccess = true;
							} else {
								logger.info("解析json数据=====查询失败，不进行数据解析json串操作");
							}
						}

						if (isSuccess) {
							String status = jsonObject.getString("status");

							logger.info("解析json数据=====status：" + status);

							String result = jsonObject.getString("result");
							JSONObject jsonresult = JSONObject.fromObject(result);

							String fileCheckStatus = jsonresult.getString("fileCheckStatus");
							logger.info("解析json数据=====fileCheckStatus：" + fileCheckStatus);

							String fileCheckReslut = jsonresult.getString("fileCheckReslut");
							logger.info("解析json数据=====fileCheckReslut：" + fileCheckReslut);

							String contentCheckStatus = jsonresult.getString("contentCheckStatus");
							logger.info("解析json数据=====contentCheckStatus：" + contentCheckStatus);

							String contentCheckReslut = jsonresult.getString("contentCheckReslut");
							JSONObject jsonCheckReslut = JSONObject.fromObject(contentCheckReslut);

							String ScanType = jsonCheckReslut.getString("ScanType");
							logger.info("解析json数据=====contentCheckStatus：" + ScanType);

							String TID = jsonCheckReslut.getString("TID");
							logger.info("解析json数据=====contentCheckStatus：" + TID);

							String policy = jsonCheckReslut.getString("policy");
							logger.info("解析json数据=====contentCheckStatus：" + policy);

							// JSONArray jsonFileListArray = jsonCheckReslut.getJSONArray("FilsList");
							JSONArray jsonFileListArray = null;
							String strFileList = jsonCheckReslut.getString("FilsList");
							if (strFileList == null || strFileList == "null" || strFileList == "") {
								throw new Exception("该文件内容没有敏感信息！");

							} else {
								jsonFileListArray = jsonCheckReslut.getJSONArray("FilsList");
							}

							for (int i = 0; i < jsonFileListArray.size(); i++) {
								String filelisti = jsonFileListArray.getString(i);

								logger.info("解析json数据=====第" + i + "个FilsList=====：" + filelisti);
								JSONObject jsonFileList = JSONObject.fromObject(filelisti);

								String FileName = jsonFileList.getString("FileName");
								logger.info("解析json数据=====第" + i + "个FilsList======FileName：" + FileName);

								String FileType = jsonFileList.getString("FileType");
								logger.info("解析json数据=====第" + i + "个FilsList======FileType：" + FileType);

								JSONArray jsonriskListArray = jsonFileList.getJSONArray("riskList");
								logger.info("解析json数据=====第" + i + "个FilsList======riskList：" + jsonriskListArray);

								for (int j = 0; j < jsonriskListArray.size(); j++) {

									String risklisti = jsonriskListArray.getString(j);

									logger.info("解析json数据=====第" + j + "个riskList=====：" + risklisti);
									JSONObject jsonrisklisti = JSONObject.fromObject(risklisti);

									String riskClass = jsonrisklisti.getString("riskClass");
									logger.info("解析json数据=====第" + j + "个riskClass：" + riskClass);

									String HitCount = jsonrisklisti.getString("HitCount");
									logger.info("解析json数据=====第" + j + "个HitCount：" + HitCount);

									String LevelName = jsonrisklisti.getString("LevelName");//
									logger.info("解析json数据=====LevelName：" + LevelName);

									String SensitveContent = jsonrisklisti.getString("SensitveContent");
									logger.info("解析json数据=====第" + j + "个SensitveContent：" + SensitveContent);

									RiskKeywordsBurn temp = new RiskKeywordsBurn(TID, riskClass, HitCount, LevelName,
											SensitveContent, FileName, FileType);

									searchTID = TID;
									burnService.addRisklistBurn(temp);
								}
							}
						}

					}
					EntityUtils.consume(resEntity);
				}
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}

		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("tid", transIDTemp);
		eventList = burnService.getRisklistBurn(map3);

		return SUCCESS;
	}
}
