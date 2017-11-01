package hdsec.web.project.burn.mapper;

import hdsec.web.project.burn.model.BurnEvent;
import hdsec.web.project.burn.model.BurnPublicity;
import hdsec.web.project.burn.model.RiskKeywordsBurn;

import java.util.List;
import java.util.Map;

public interface BurnMapper {

	void addBurnEvent(BurnEvent event);

	List<BurnEvent> getBurnEventList(Map<String, Object> map);

	BurnEvent getBurnEventByBurnCode(String event_code);

	void delBurnEventByBurnCode(String event_code);

	String getJobCodeByEventCode(String event_code);

	void updateBurnEvent(BurnEvent event);

	void updateBurnEventFileInfo(Map<String, Object> map);

	List<BurnEvent> getBurnEventListByJobCode(String job_code);

	void addBurnEventJobRel(Map<String, Object> map);

	void updateBurnEventJobCode(Map<String, String> map);

	void updateBurnEventWithoutUsage(BurnEvent event);

	void updateBurnComment(BurnEvent event);

	List<BurnPublicity> getBurnPublicity(String event_code);

	void updateBurnStatus(Map<String, Object> map);

	void updateBurnProxyUseridByEventCode(Map<String, Object> map);

	void AddKeywordBurnEvent(Map<String, Object> map);

	void updateKeywordBurnEvent(Map<String, Object> map);

	void updateKeywordBurnCallresultByTid(Map<String, Object> map);

	void addRisklistBurn(RiskKeywordsBurn temp);

	// 根据文件名称/用户名ID/event_code获取刻录transid
	String getBurnTransID(Map<String, Object> map);

	// 根据transid获取刻录文件检索结果列表
	List<RiskKeywordsBurn> getRisklistBurn(Map<String, Object> map);

	void updateKeywordBurnCheckresultByTid(Map<String, Object> map);
}
