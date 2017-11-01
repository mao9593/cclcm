package hdsec.web.project.burn.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.ClientMsg;
import hdsec.web.project.basic.model.SysConfigItem;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.burn.model.BurnEvent;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.common.util.MD5;
import hdsec.web.project.common.util.PrefixFilenameFilter;
import hdsec.web.project.user.model.SecLevel;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.util.StringUtils;

/**
 * 添加刻录作业
 * 
 * @author renmingfei
 * 
 */
public class AddBurnEventAction extends BurnBaseAction {

	private static final long serialVersionUID = 1L;
	private String event_code = "";// 刻录作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String comment = "";// 备注
	private Integer data_type = null;// 数据类型
	private Integer cd_num = 1;// 刻录份数
	private Integer file_num = 0;// 文件数量
	private String file_list = "";// 文件列表
	private String file_seclevel = "";// 文件密级列表
	private String cd_serial = "";// 光盘编号
	private Integer is_proxy = 0;// 是否代理刻录 1代理 0不代理
	private String cycle_type = "";// 刻录状态
	private String period = "";// 短期/长期
	private String next_approver = "";// 下级审批人
	private String output_dept_name = "";// 外发接收人部门
	private String output_user_name = "";// 外发接收人
	private String conf_code = "";// 保密编号
	private String info;// 上传文件信息（文件名和密级）
	private String proxyburn_user_name = "";
	private String proxyburn_user_iidd = "";

	private Integer uploadfilelimit = null;// 允许上传的文件数量
	private String uploadfiletypes = "";// 不允许上传的压缩文件类型

	// 判断url是否可连接所需变量
	private static URL url;
	private static HttpURLConnection con;
	private static int state = -1;
	private static String succ;
	private String is_special_burn = "N";

	public String getIs_special_burn() {
		return is_special_burn;
	}

	public void setIs_special_burn(String is_special_burn) {
		this.is_special_burn = is_special_burn;
	}

	public Integer getUploadfilelimit() {
		return uploadfilelimit;
	}

	public void setUploadfilelimit(Integer uploadfilelimit) {
		this.uploadfilelimit = uploadfilelimit;
	}

	public String getUploadfiletypes() {
		return uploadfiletypes;
	}

	public void setUploadfiletypes(String uploadfiletypes) {
		this.uploadfiletypes = uploadfiletypes;
	}

	public String getProxyburn_user_name() {
		return proxyburn_user_name;
	}

	public void setProxyburn_user_name(String proxyburn_user_name) {
		this.proxyburn_user_name = proxyburn_user_name;
	}

	public String getProxyburn_user_iidd() {
		return proxyburn_user_iidd;
	}

	public void setProxyburn_user_iidd(String proxyburn_user_iidd) {
		this.proxyburn_user_iidd = proxyburn_user_iidd;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setData_type(Integer data_type) {
		this.data_type = data_type;
	}

	public void setCd_num(Integer cd_num) {
		this.cd_num = cd_num;
	}

	public void setFile_num(Integer file_num) {
		this.file_num = file_num;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public void setFile_seclevel(String file_seclevel) {
		this.file_seclevel = file_seclevel;
	}

	public void setCd_serial(String cd_serial) {
		this.cd_serial = cd_serial;
	}

	public void setIs_proxy(Integer is_proxy) {
		this.is_proxy = is_proxy;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getBurnSecLevelByUser(getCurUser().getUser_iidd());
	}

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageListByModule("BURN");
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	private void SendToBchenKeywords(String user_iidd, String event_code, String MD5file_name) throws Exception {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			ContentType contentType = ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),
					CharsetUtils.get("UTF-8"));
			// 接口地址
			String apiUrl = PropertyUtil.getDlpCommitUrl();
			HttpPost httppost = new HttpPost(apiUrl);
			logger.debug("刻录：DLP检索接口==========apiUrl is :" + apiUrl);

			// 文件地址
			String filePath = "/" + event_code + "/" + MD5file_name;
			// String filePath = burn_file_path + File.separator + file_name;
			logger.debug("刻录：DLP检索接口==========共享filePath is :" + filePath);

			// 要上传的文件
			FileBody file = new FileBody(new File(filePath));

			// 事务编号，可为空，最大32位
			String temprandomid = UUID.randomUUID().toString().replaceAll("-", "");
			StringBody transId = new StringBody(temprandomid, ContentType.TEXT_PLAIN);
			logger.debug("刻录：DLP检索接口==========transId is :" + temprandomid);

			// 将本次调用的事务编号和event_code插入数据库
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("tid", temprandomid);
			map1.put("event_code", event_code);
			map1.put("file_name", MD5file_name);
			map1.put("user_iidd", user_iidd);
			burnService.AddKeywordBurnEvent(map1);

			// 事务类型，为数组，在这里分两个stringbody传输，20文件隐藏检查，30内容识别检查
			StringBody transType2 = new StringBody("30", ContentType.TEXT_PLAIN);
			logger.debug("刻录：DLP检索接口==========transType2 is :" + transType2);

			// 返回类型，默认为1，全部返回
			StringBody returnType = new StringBody("1", ContentType.TEXT_PLAIN);
			logger.debug("刻录：DLP检索接口==========returnType is :" + returnType);

			// 通知地址，接入业务系统提供的可访问的http url，事务执行完毕引擎通过该地址通知结果
			String returnurlStr = PropertyUtil.getDlpBurnDownloadIPUrl() + event_code + "/" + MD5file_name;
			logger.debug("刻录：DLP检索接口==========returnurlStr is :" + returnurlStr);

			StringBody notifyUrl = new StringBody(returnurlStr, ContentType.TEXT_PLAIN);
			logger.debug("刻录：DLP检索接口==========notifyUrl is :" + notifyUrl);

			// 文件数量，目前只支持1个文件
			StringBody fileCount = new StringBody("1", ContentType.TEXT_PLAIN);
			logger.debug("刻录：DLP检索接口==========fileCount is :" + fileCount);

			// 文件传输方式 1http上传，如果该方式上传，则必须有file这个字段。2共享上传，如果是该方式，则fileName字段必填。3new http下载方式上传。
			// StringBody fileTransType_http = new StringBody("1", ContentType.TEXT_PLAIN);
			// StringBody fileTransType_smb = new StringBody("2", ContentType.TEXT_PLAIN);
			// logger.debug("刻录：DLP检索接口==========fileTransType_smb is :" + fileTransType_smb);

			StringBody fileTransType_newhttp = new StringBody("3", ContentType.TEXT_PLAIN);
			logger.debug("刻录：DLP检索接口 ===== fileTransType_newhttp is :" + fileTransType_newhttp);

			// 文件名称，fileTransType采用共享方式提交事务时必须指定该参数
			StringBody fileName = new StringBody(MD5file_name, contentType);

			logger.debug("刻录：DLP检索接口==========fileName is :" + fileName);

			// 刻录系统在邦辰中平台中的标识
			StringBody partnerSN = new StringBody("1002", ContentType.TEXT_PLAIN);
			logger.debug("刻录：DLP检索接口==========partnerSN is :" + partnerSN);

			// 接口中增加解密标识—— http上传刻录附件模式 —— 需要DLP平台进行解密操作 [20170323]
			StringBody isNeedDecrypt = new StringBody("1", ContentType.TEXT_PLAIN);
			logger.debug("刻录：DLP检索接口==========isNeedDeccrypt is :" + isNeedDecrypt);

			/*
			 * StringBody isOcr = new StringBody("1", ContentType.TEXT_PLAIN);
			 * logger.debug("parameter 10 ===== isOcr is :" + isOcr);
			 */

			MultipartEntityBuilder builder = MultipartEntityBuilder.create();

			/** 支持中文字符设置开始 **********************************************************/
			// builder.setCharset(Charset.forName(HTTP.UTF_8));//设置请求的编码格式
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);// 设置浏览器兼容模式
			builder.setCharset(CharsetUtils.get("UTF-8"));
			/** 支持中文字符设置结束 **********************************************************/

			builder.addPart("transId", transId); // 事务id
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
			// builder.addPart("isOcr", isOcr); // 文件ocr检查
			builder.addPart("partnerSN", partnerSN);// 刻录系统在邦辰平台中的标识
			builder.addPart("isNeedDecypt", isNeedDecrypt);// 接口中增加解密标识

			HttpEntity reqEntity = builder.build();
			httppost.setEntity(reqEntity);

			logger.info("<<-----------------------------------------------------");
			logger.info("executing request " + httppost.getRequestLine());

			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				// System.out.println(response.getStatusLine());
				logger.info(response.getStatusLine());

				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					String json = EntityUtils.toString(resEntity);
					logger.info("提交事务接口调用返回值：\r\n" + json);

					// 将本次接口调用成功与否的返回值插入数据库表keyword_print中
					Map<String, Object> map2 = new HashMap<String, Object>();
					map2.put("tid", temprandomid);
					map2.put("call_result", json);
					burnService.updateKeywordBurnCallresultByTid(map2);

				}
				EntityUtils.consume(resEntity);
			} finally {
				response.close();
			}
			logger.info("----------------------------------------------------->>");
		} finally {
			httpclient.close();
		}

	}

	// 检测当前URL是否可连接或是否有效
	private synchronized String isUrlConnect(String urlStr) {
		int counts = 0;
		succ = null;
		if (urlStr == null || urlStr.length() <= 0) {
			return succ;
		}

		while (counts < 1) {
			try {
				url = new URL(urlStr);
				con = (HttpURLConnection) url.openConnection();
				state = con.getResponseCode();
				logger.info("刻录：DLP平台服务连接测试：" + counts + "=" + state);

				if (state == 200) {
					succ = con.getURL().toString();
					logger.info("刻录：DLP平台服务状态可用");
				}
				break;
			} catch (Exception ex) {
				counts++;
				logger.info("刻录：DLP平台服务状态不可用，连接第" + counts + "次");
				continue;
			}
		}
		return succ;
	}

	// 判断DLP服务所在IP和端口是否可以连通
	private boolean isHostConnectable(String url) {
		int iLength = url.length();
		String newulr = url.substring(0, iLength - 16);
		String ret = isUrlConnect(newulr);
		if (ret == null) {
			logger.info("刻录：DLP平台服务状态异常，无法提交刻录文件检索请求");
			return false;
		}
		return true;
	}

	private void handleFileList() throws Exception {
		// 从配置文件中读取刻录文件的临时存储路径和正式存储路径
		String tempPath = PropertyUtil.getBurnFileTempPath();
		String storePath = PropertyUtil.getBurnFileStorePath();
		// 在临时路径后加上用户ID
		tempPath = tempPath + File.separator + getCurUser().getUser_iidd();
		File path = new File(tempPath);
		PrefixFilenameFilter filenameFilter = new PrefixFilenameFilter(event_code + "^");
		if (path.isDirectory()) {
			File[] files = path.listFiles(filenameFilter);
			file_num = files.length;
			for (File file : files) {
				String fileName = file.getName();

				// 邦辰刻录检索文件使用
				String dlp_filename = fileName;

				// 去掉前面的event_code
				// fileName = fileName.substring(fileName.indexOf("-") + 1);
				// 文件名
				fileName = fileName.substring(fileName.indexOf("^") + 1);
				String[] filesInfo = info.split("::");
				String oneFileName;
				String seclv_file = "";
				for (String oneFileInfo : filesInfo) {
					oneFileName = oneFileInfo.split(" __ ")[0];
					if (fileName.equals(oneFileName)) {
						// fileName = oneFileName;
						seclv_file = oneFileInfo.split(" __ ")[1];
					}
				}
				try {
					// 判断系统是否已开启与DLP敏感字识别系统集成标志
					if (PropertyUtil.getDlpIsOpenUrl().equals("1")) {
						if (isHostConnectable(PropertyUtil.getDlpCommitUrl())) {
							// 调用邦辰关键字集成接口
							logger.debug("刻录：DLP检索接口==========用户为:" + getCurUser().getUser_iidd());
							logger.debug("刻录：DLP检索接口==========标识为:" + event_code);
							logger.debug("刻录：DLP检索接口==========文件名为:" + MD5.getStringMD5(fileName));

							SendToBchenKeywords(getCurUser().getUser_iidd(), event_code, MD5.getStringMD5(fileName));
						}
						moveFile(file, storePath + File.separator + event_code, MD5.getStringMD5(fileName));

					} else {
						moveFile(file, storePath + File.separator + event_code, MD5.getStringMD5(fileName));
					}

					file_list = file_list + fileName + CCLCMConstants.DEVIDE_SYMBOL;
					file_seclevel = file_seclevel + seclv_file + CCLCMConstants.DEVIDE_SYMBOL;
					logger.debug("copy file:" + fileName);
				} finally {
					if (PropertyUtil.getDlpIsOpenUrl().equals("0")) {
						file.delete();
					}
				}
			}
			if (file_list.endsWith(CCLCMConstants.DEVIDE_SYMBOL)) {
				file_list = file_list.substring(0, file_list.length() - 1);
			}
			if (file_seclevel.endsWith(CCLCMConstants.DEVIDE_SYMBOL)) {
				file_seclevel = file_seclevel.substring(0, file_seclevel.length() - 1);
			}
		}
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();
			handleFileList();
			BurnEvent event = new BurnEvent(user_iidd, dept_id, event_code, seclv_code, usage_code, project_code,
					cd_serial, cd_num, is_proxy, comment, data_type, file_num, file_list, file_seclevel, cycle_type,
					period);

			String jobType_code = "";
			if (is_special_burn.equals("Y")) {
				jobType_code = "SPECIAL_BURN_ZWYJG";
			} else {
				jobType_code = "BURN_" + cycle_type;
			}

			JobTypeEnum jobType = JobTypeEnum.valueOf(jobType_code);
			event.setJobType(jobType);
			event.setConf_code(conf_code);
			burnService.addBurnEvent(event, next_approver, output_dept_name, output_user_name, comment);

			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("event_code", event_code);
			map1.put("proxyburn_user_iidd", proxyburn_user_iidd);
			burnService.updateBurnProxyUseridByEventCode(map1);

			insertCommonLog("添加刻录申请，文件列表[" + file_list + "]");

			BurnEvent eventtemp = burnService.getBurnEventByBurnCode(event_code);
			if (eventtemp.getJob_status().equals("true")) {

				// 审批通过后， 获取刻录委托人,通知委托人
				String message_proxy = "委托给您的" + jobType.getJobTypeName() + "作业已审批通过，请及时刻录";
				String oper_type_proxy = "PROXY_BURN";
				Integer approveResultValue = 2;
				if (proxyburn_user_iidd != null && !proxyburn_user_iidd.equals("")) {
					String proxypburn_user_name = userService.getUserNameByUserId(proxyburn_user_iidd);
					ClientMsg clientMsg_proxy = new ClientMsg(proxyburn_user_iidd, proxypburn_user_name,
							oper_type_proxy, approveResultValue, eventtemp.getJob_code(), message_proxy, new Date(), 0);
					basicService.addClientMsg(clientMsg_proxy);
				}
			}

			return "ok";
		} else {
			// 从系统配置表获取由系统管理员设置的 刻录上传文件数量配置项
			SysConfigItem item_upload_burnfile_num = basicService
					.getSysConfigItemValue(SysConfigItem.KEY_UPLOAD_BURNFILE_NUM);
			// 若配置生效,则获取允许刻录上传的文件数量值; 否则置为0(不限制刻录上传文件数量)
			uploadfilelimit = item_upload_burnfile_num.getStartuse() == 1 ? Integer.parseInt(item_upload_burnfile_num
					.getItem_value()) : 0;
			// 从系统配置表获取由系统管理员设置的 刻录上传文件类型配置项
			SysConfigItem item_upload_burnfile_ziptype = basicService
					.getSysConfigItemValue(SysConfigItem.KEY_UPLOAD_BURNFILE_ZIPTYPE);
			// 若配置生效,则获取允许刻录上传的文件类型; 否则置为空(不限制刻录上传文件类型)
			uploadfiletypes = item_upload_burnfile_ziptype.getStartuse() == 1 ? item_upload_burnfile_ziptype
					.getItem_value() : "";

			event_code = getCurUser().getUser_iidd() + "_BURN_" + System.currentTimeMillis();

			/*
			 * JNative jNative = null; try { jNative = new JNative("C:\\GenBarCodeDll.dll", "EncryptBurnFile");
			 * jNative.setRetVal(Type.INT);
			 * 
			 * // 设置函数参数 jNative.setParameter(0, "D:\\work\\test中文.txt");
			 * 
			 * jNative.setParameter(1, "D:\\work\\test中文_.txt");
			 * 
			 * // 调用方法 jNative.invoke(); jNative.getRetVal(); } catch (NativeException e) { e.printStackTrace(); } catch
			 * (IllegalAccessException e) { e.printStackTrace(); } finally { if (jNative != null) { jNative.dispose(); }
			 * }
			 */
			return SUCCESS;
		}
	}

	public String getCycle_type() {
		return cycle_type;
	}

	public void setCycle_type(String cycle_type) {
		this.cycle_type = cycle_type;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getOutput_dept_name() {
		return output_dept_name;
	}

	public void setOutput_dept_name(String output_dept_name) {
		this.output_dept_name = output_dept_name;
	}

	public String getOutput_user_name() {
		return output_user_name;
	}

	public void setOutput_user_name(String output_user_name) {
		this.output_user_name = output_user_name;
	}

}
