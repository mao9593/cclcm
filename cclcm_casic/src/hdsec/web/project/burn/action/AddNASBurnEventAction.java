package hdsec.web.project.burn.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.burn.model.BurnEvent;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.SecUser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
 * @author yy
 * 
 */
public class AddNASBurnEventAction extends BurnBaseAction {

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
	
	private String output_undertaker = "";
	private List<SecUser> output_undertakerList = null;
	private String send_way = "";
	private String carryout_user_iidds = "";
	private String carryout_user_names = "";

	public String getOutput_undertaker() {
		return output_undertaker;
	}

	public void setOutput_undertaker(String output_undertaker) {
		this.output_undertaker = output_undertaker;
	}

	public String getSend_way() {
		return send_way;
	}

	public void setSend_way(String send_way) {
		this.send_way = send_way;
	}

	public String getCarryout_user_iidds() {
		return carryout_user_iidds;
	}

	public void setCarryout_user_iidds(String carryout_user_iidds) {
		this.carryout_user_iidds = carryout_user_iidds;
	}

	public String getCarryout_user_names() {
		return carryout_user_names;
	}

	public void setCarryout_user_names(String carryout_user_names) {
		this.carryout_user_names = carryout_user_names;
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
	
	// 获取外发承办人列表(仅部门文件管理员)
	public List<SecUser> getOutput_undertakerList() {
		String parent_dept_id = basicService.getParentDeptIdByCurrentId(getCurUser().getDept_id());
		Map<String, Object> map0 = new HashMap<String, Object>();
		map0.put("dept_id", getCurUser().getDept_id());
		map0.put("parent_dept_id", parent_dept_id);
		output_undertakerList = basicService.getOutputUndertakersByDeptId(map0);

		Set<String> set = new HashSet<String>();
		List<SecUser> sulist = new ArrayList<SecUser>();
		for (SecUser item : output_undertakerList) {
			if (set.add(item.getUser_iidd())) {
				sulist.add(item);
			}
		}
		return sulist;
	}

	private void SendToBchenKeywords(String user_iidd, String event_code, String file_name) throws Exception {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			ContentType contentType = ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),
					CharsetUtils.get("UTF-8"));
			// 接口地址
			String apiUrl = PropertyUtil.getDlpCommitUrl();
			HttpPost httppost = new HttpPost(apiUrl);
			logger.debug("刻录：DLP检索接口==========apiUrl is :" + apiUrl);

			// 文件地址
			String filePath = "/" + event_code + "/" + file_name;
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
			map1.put("file_name", file_name);
			map1.put("user_iidd", user_iidd);
			burnService.AddKeywordBurnEvent(map1);

			// 事务类型，为数组，在这里分两个stringbody传输，20文件隐藏检查，30内容识别检查
			StringBody transType2 = new StringBody("30", ContentType.TEXT_PLAIN);
			logger.debug("刻录：DLP检索接口==========transType2 is :" + transType2);

			// 返回类型，默认为1，全部返回
			StringBody returnType = new StringBody("1", ContentType.TEXT_PLAIN);
			logger.debug("刻录：DLP检索接口==========returnType is :" + returnType);

			// 通知地址，接入业务系统提供的可访问的http url，事务执行完毕引擎通过该地址通知结果
			String returnurlStr = "http://localhost/service/CAEPKeywordsService?wsdl";
			StringBody notifyUrl = new StringBody(returnurlStr, ContentType.TEXT_PLAIN);
			logger.debug("刻录：DLP检索接口==========notifyUrl is :" + notifyUrl);

			// 文件数量，目前只支持1个文件
			StringBody fileCount = new StringBody("1", ContentType.TEXT_PLAIN);
			logger.debug("刻录：DLP检索接口==========fileCount is :" + fileCount);

			// 文件传输方式 1http上传，如果该方式上传，则必须有file这个字段。2共享上传，如果是该方式，则fileName字段必填
			// StringBody fileTransType_http = new StringBody("1", ContentType.TEXT_PLAIN);
			StringBody fileTransType_smb = new StringBody("2", ContentType.TEXT_PLAIN);
			logger.debug("刻录：DLP检索接口==========fileTransType_smb is :" + fileTransType_smb);

			// 文件名称，fileTransType采用共享方式提交事务时必须指定该参数
			StringBody fileName = new StringBody(filePath, contentType);

			logger.debug("刻录：DLP检索接口==========fileName is :" + fileName);

			// 刻录系统在邦辰中平台中的标识
			StringBody partnerSN = new StringBody("1002", ContentType.TEXT_PLAIN);
			logger.debug("刻录：DLP检索接口==========partnerSN is :" + partnerSN);

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
			builder.addPart("fileTransType", fileTransType_smb);
			builder.addPart("fileName", fileName); // 文件名
			// builder.addPart("isOcr", isOcr); // 文件ocr检查
			builder.addPart("partnerSN", partnerSN);// 刻录系统在邦辰平台中的标识

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

	private void handleFileList() throws Exception {
		for (String onefile : info.split("::")) {
			file_num++;
			String fileName = onefile.split(" __ ")[0];
			String seclv_file = onefile.split(" __ ")[1];

			if (PropertyUtil.getDlpIsOpenUrl().equals("1")) {
				// 调用邦辰关键字集成接口
				logger.debug("刻录：DLP检索接口==========用户为:" + getCurUser().getUser_iidd());
				logger.debug("刻录：DLP检索接口==========event_code为:" + event_code);
				logger.debug("刻录：DLP检索接口==========文件名为:" + fileName);
				SendToBchenKeywords(getCurUser().getUser_iidd(), event_code, fileName);
			}

			file_list = file_list + fileName + CCLCMConstants.DEVIDE_SYMBOL;
			file_seclevel = file_seclevel + seclv_file + CCLCMConstants.DEVIDE_SYMBOL;
			logger.debug("copy file:" + fileName);
		}
		if (file_list.endsWith(CCLCMConstants.DEVIDE_SYMBOL)) {
			file_list = file_list.substring(0, file_list.length() - 1);
		}
		if (file_seclevel.endsWith(CCLCMConstants.DEVIDE_SYMBOL)) {
			file_seclevel = file_seclevel.substring(0, file_seclevel.length() - 1);
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
			String jobType_code = "BURN_" + cycle_type;
			carryout_user_iidds = carryout_user_iidds.isEmpty() ? "" : carryout_user_iidds.substring(0,
					carryout_user_iidds.length() - 1);
			carryout_user_names = carryout_user_names.isEmpty() ? "" : carryout_user_names.substring(0,
					carryout_user_names.length() - 1);
			JobTypeEnum jobType = JobTypeEnum.valueOf(jobType_code);
			event.setJobType(jobType);
			event.setConf_code(conf_code);
			burnService.addBurnEvent(event, next_approver, output_dept_name, output_user_name, comment,
					output_undertaker, carryout_user_iidds, carryout_user_names, send_way);
			insertCommonLog("添加刻录作业[" + event_code + "]");
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + "_BURN_" + System.currentTimeMillis();
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
