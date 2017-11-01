package hdsec.web.project.passport.service;

import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.passport.model.EntityPassport;

import java.util.List;
import java.util.Map;

public interface PassportService {

	/**
	 * 添加护照
	 * 
	 * @param passport
	 * @param cycleitem
	 */
	void addEntityPassport(EntityPassport passport, CycleItem cycleitem);

	List<EntityPassport> getAllPassportList(Map<String, Object> map);

	Integer getTypeIDByName(String passport_type_name);

	EntityPassport getPassportByNum(String passport_num);

	void updatePassport(EntityPassport passport);

	/** 根据用户id获取用户护照信息 **/
	// EntityPassport getPassportByUserId(String duty_user_iidd);

	/** 根据用户id更新用户护照信息 **/
	void updatePassportByUserId(Map<String, Object> map);

	/**
	 * 根据ID查询护照信息
	 * 
	 * @param passport_id
	 * @return
	 */
	EntityPassport getPassportById(String passport_id);

	/**
	 * 根据护照ID更新护照信息
	 * 
	 * @param passport
	 */
	void updatePassportById(EntityPassport passport);
}
