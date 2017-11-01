package hdsec.web.project.passport.service;

import hdsec.web.project.ledger.mapper.LedgerMapper;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.passport.mapper.PassportMapper;
import hdsec.web.project.passport.model.EntityPassport;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

public class PassportServiceImpl implements PassportService {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Resource
	private PassportMapper PassportMapper;
	@Resource
	private LedgerMapper ledgerMapper;

	@Override
	public void addEntityPassport(EntityPassport passport, CycleItem cycleitem) {
		// System.out.println(passport);
		logger.debug("addEntityPassport:" + passport.getPassport_num());
		PassportMapper.addEntityPassport(passport);
		ledgerMapper.addCycleItem(cycleitem);
	}

	@Override
	public List<EntityPassport> getAllPassportList(Map<String, Object> map) {
		logger.debug("getAllPassportList");
		return PassportMapper.getAllPassportList(map);
	}

	@Override
	public Integer getTypeIDByName(String typename) {
		logger.debug("getTypeIDByName");
		return PassportMapper.getTypeIDByName(typename);
	}

	@Override
	public EntityPassport getPassportByNum(String passport_num) {
		logger.debug("getPassportByNum:" + passport_num);
		return PassportMapper.getPassportByNum(passport_num);
	}

	@Override
	public void updatePassport(EntityPassport passport) {
		logger.debug("updatePassport:" + passport.getPassport_num());
		PassportMapper.updatePassport(passport);
	}

	/*
	 * @Override public EntityPassport getPassportByUserId(String duty_user_iidd) {
	 * logger.debug("getPassportByUserId:"); return PassportMapper.getPassportByUserId(duty_user_iidd); }
	 */

	@Override
	public void updatePassportByUserId(Map<String, Object> map) {
		logger.debug("updatePassportByUserId:");
		PassportMapper.updatePassportByUserId(map);
	}

	@Override
	public EntityPassport getPassportById(String passport_id) {
		logger.debug("getPassportById:" + passport_id);
		return PassportMapper.getPassportById(passport_id);
	}

	@Override
	public void updatePassportById(EntityPassport passport) {
		logger.debug("updatePassportById:" + passport.getPassport_id());
		PassportMapper.updatePassportById(passport);
	}
}