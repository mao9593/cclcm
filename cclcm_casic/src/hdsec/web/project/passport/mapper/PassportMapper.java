package hdsec.web.project.passport.mapper;

import hdsec.web.project.passport.model.EntityPassport;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author gaoyiming 2015-7-11
 */

public interface PassportMapper {

	void addEntityPassport(EntityPassport passport);

	List<EntityPassport> getAllPassportList(Map<String, Object> map);

	Integer getTypeIDByName(String typename);

	EntityPassport getPassportByNum(String passport_num);

	void updatePassport(EntityPassport passport);

	// EntityPassport getPassportByUserId(String duty_user_iidd);

	void updatePassportByUserId(Map<String, Object> map);

	EntityPassport getPassportById(String passport_id);

	void updatePassportById(EntityPassport passport);
}
