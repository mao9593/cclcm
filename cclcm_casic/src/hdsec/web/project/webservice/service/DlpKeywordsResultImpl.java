package hdsec.web.project.webservice.service;

import hdsec.web.project.burn.mapper.BurnMapper;
import hdsec.web.project.burn.model.RiskKeywordsBurn;
import hdsec.web.project.burn.service.BurnService;
import hdsec.web.project.print.mapper.PrintMapper;
import hdsec.web.project.print.model.RiskKeywordsPrint;
import hdsec.web.project.print.service.PrintService;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

@WebService(serviceName = "dlpResultService")
public class DlpKeywordsResultImpl {
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private BurnMapper burnMapper;
	@Resource
	private BurnService burnService;
	@Resource
	private PrintMapper printMapper;
	@Resource
	private PrintService printService;

	/*
	 * 接受邦辰DLP敏感字识别系统发来的打印/刻录文件检索结果
	 * 
	 * @author haojia
	 */
	@WebMethod
	public String postDlpKeywordsResult(
			@WebParam(targetNamespace = "http://service.webservice.project.web.hdsec/") String json) {
		logger.info("开始接收关键字检索结果！");
		logger.info("接收到的json串数据：\r\n" + json);

		// 开始解析返回的json串
		JSONObject jsonObject = JSONObject.fromObject(json);

		String message = jsonObject.getString("message");
		logger.info("解析json数据=====message：" + message);

		/*
		 * boolean isSuccess = false; if (message != null && message != "") { if (message.equals("查询成功")) { isSuccess =
		 * true; } else { logger.info("解析json数据=====查询失败，不进行数据解析json串操作"); } }
		 */
		int status = jsonObject.getInt("status");
		logger.info("解析json数据=====status：" + status);

		boolean isSuccess = false;
		if (status == 0) {
			logger.info("解析json数据=====DLP检测中");
		} else if (status == 1) {
			isSuccess = true;
			logger.info("解析json数据=====DLP检测成功");
		} else {
			isSuccess = true;
			logger.info("解析json数据=====DLP检测失败");
		}

		if (isSuccess) {

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

			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("tid", TID);
			String print_tid1 = printMapper.getTransIDfromPrint(map1);

			if (status == 2) {
				// 将本次查询失败的结果(异常Failed)插入数据库表keyword_print的字段CHECK_RESULT中
				if (print_tid1 != null && !print_tid1.isEmpty()) {
					printService.updateKeywordPrintCheckresultByTid(TID, "Failed");
					logger.info("--Print-- updateKeywordPrintCheckresultByTid: " + TID + " - Failed");
				} else {
					burnService.updateKeywordBurnCheckresultByTid(TID, "Failed");
					logger.info("--Burn-- updateKeywordBurnCheckresultByTid: " + TID + " - Failed");
				}
				return "0";
			}

			String policy = jsonCheckReslut.getString("policy");
			logger.info("解析json数据=====contentCheckStatus：" + policy);

			JSONArray jsonFileListArray = jsonCheckReslut.getJSONArray("FilsList");
			// JSONArray jsonFileListArray = null;
			// String strFileList = jsonCheckReslut.getJSONArray("FilsList");

			if (jsonFileListArray == null || jsonFileListArray.size() == 0) {
				// 将本次查询成功的结果(未命中NoHit)插入数据库表keyword_burn的字段CHECK_RESULT中
				if (print_tid1 != null && !print_tid1.isEmpty()) {
					printService.updateKeywordPrintCheckresultByTid(TID, "NoHit");
					logger.info("--Print-- updateKeywordPrintCheckresultByTid: " + TID + " - NoHit");
				} else {
					burnService.updateKeywordBurnCheckresultByTid(TID, "NoHit");
					logger.info("--Burn-- updateKeywordBurnCheckresultByTid: " + TID + " - NoHit");
				}
			} else {
				// 将本次查询成功的结果(命中Hit)插入数据库表keyword_burn的字段CHECK_RESULT中
				if (print_tid1 != null && !print_tid1.isEmpty()) {
					printService.updateKeywordPrintCheckresultByTid(TID, "Hit");
					logger.info("--Print-- updateKeywordPrintCheckresultByTid: " + TID + " - Hit");
				} else {
					burnService.updateKeywordBurnCheckresultByTid(TID, "Hit");
					logger.info("--Burn-- updateKeywordBurnCheckresultByTid: " + TID + " - Hit");
				}
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

					Map<String, Object> map3 = new HashMap<String, Object>();
					map3.put("tid", TID);
					String print_tid = printMapper.getTransIDfromPrint(map3);
					if (print_tid != null && print_tid != "") {
						RiskKeywordsPrint tempprint = new RiskKeywordsPrint(TID, riskClass, HitCount, LevelName,
								SensitveContent, FileName, FileType);

						printMapper.addRisklistPrint(tempprint);
					} else {
						RiskKeywordsBurn tempburn = new RiskKeywordsBurn(TID, riskClass, HitCount, LevelName,
								SensitveContent, FileName, FileType);
						burnMapper.addRisklistBurn(tempburn);
					}
				}
			}
		}

		return "0";
	}
}
