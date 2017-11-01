package hdsec.web.project.storage.service;

import hdsec.web.project.basic.mapper.BasicMapper;
import hdsec.web.project.basic.model.ConfirmRecord;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.ledger.mapper.LedgerMapper;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.storage.mapper.StorageMapper;
import hdsec.web.project.storage.model.EntityStorage;
import hdsec.web.project.storage.model.StorageStatusEnum;
import hdsec.web.project.user.mapper.UserMapper;
import hdsec.web.project.user.model.SecUser;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

public class StorageServiceImpl implements StorageService {
	@Resource
	private StorageMapper storageMapper;
	@Resource
	private BasicMapper basicMapper;
	@Resource
	private LedgerMapper ledgerMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private BasicService basicService;

	@Override
	public List<EntityStorage> getStorageList(Map<String, Object> map) throws Exception {
		String changedBarcode = basicService.changeBarcode((String) map.get("storage_barcode"));
		map.put("storage_barcode", changedBarcode);
		return storageMapper.getStorageList(map);
	}

	@Override
	public void addStorage(EntityStorage storage) {
		storageMapper.addStorage(storage);
	}

	@Override
	public EntityStorage getStorageByCode(String storage_code) {
		return storageMapper.getStorageByCode(storage_code);
	}

	@Override
	public void updateStorage(EntityStorage storage) {
		storageMapper.updateStorage(storage);
	}

	@Override
	public void delStorageByCode(String storage_code) {
		storageMapper.delStorageByCode(storage_code);
	}

	@Override
	public void updateStorageStatus(Map<String, Object> map) {
		storageMapper.updateStorageStatus(map);
		Integer storage_status = (Integer) map.get("storage_status");
		if (storage_status == StorageStatusEnum.SS3.getKey()) {
			clearStorageUser((String) map.get("storage_code"));
		}
	}

	private void clearStorageUser(String storage_code) {
		storageMapper.clearStorageUser(storage_code);
	}

	@Override
	public EntityStorage getStorageByBaecode(String storage_barcode) {
		return storageMapper.getStorageByBarcode(storage_barcode);
	}

	@Override
	public void distributeStorage(String storage_code, SecUser use_user, SecUser admin) {

		EntityStorage storage = storageMapper.getStorageByCode(storage_code);
		if (basicService.isConfirmOpen("STORAGE_BR_CONFIRM")) {
			// 交接确认开启
			ConfirmRecord record = new ConfirmRecord();
			record.setApply_user_iidd(admin.getUser_iidd());
			record.setApply_user_name(admin.getUser_name());
			record.setApply_dept_id(admin.getDept_id());
			record.setApply_dept_name(admin.getDept_name());
			record.setConfirm_user_iidd(use_user.getUser_iidd());
			record.setConfirm_user_name(use_user.getUser_name());
			record.setConfirm_dept_id(use_user.getDept_id());
			record.setConfirm_dept_name(use_user.getDept_name());
			record.setEntity_type("STORAGE");
			record.setEntity_barcode(storage.getStorage_barcode());
			record.setEntity_name(storage.getStorage_name());
			record.setSeclv_name(storage.getSeclv_name());
			record.setConfirm_type("STORAGE_BR");
			// record.setEvent_code(event_code);
			record.setCreate_time(new Date());
			basicMapper.saveConfirmRecord(record);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("storage_code", storage_code);
			map.put("storage_status", StorageStatusEnum.SS4.getKey());// 待交接
			storageMapper.distributeStorage(map);
		} else {
			// 交接确认关闭
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("storage_code", storage_code);
			map.put("use_user_iidd", use_user.getUser_iidd());
			map.put("use_user_name", use_user.getUser_name());
			map.put("storage_status", StorageStatusEnum.SS1.getKey());// 已分配
			storageMapper.distributeStorage(map);
			// 生成载体生命周期记录：借入
			CycleItem cycleitem = new CycleItem();
			cycleitem.setBarcode(storage.getStorage_barcode());
			cycleitem.setEntity_type("STORAGE");
			cycleitem.setOper_time(new Date());
			cycleitem.setUser_name(use_user.getUser_name());
			cycleitem.setDept_name(use_user.getDept_name());
			cycleitem.setOper("BORROW");
			// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
			// 此处不用启动流程，job_code设置为default
			cycleitem.setJob_code("default");
			ledgerMapper.addCycleItem(cycleitem);
		}
	}

	@Override
	public void returnStorage(EntityStorage storage, SecUser admin) {

		SecUser use_user = userMapper.getSecUserByUid(storage.getUse_user_iidd());
		if (basicService.isConfirmOpen("STORAGE_RT_CONFIRM")) {
			// 交接确认开启
			ConfirmRecord record = new ConfirmRecord();
			record.setApply_user_iidd(use_user.getUser_iidd());
			record.setApply_user_name(use_user.getUser_name());
			record.setApply_dept_id(use_user.getDept_id());
			record.setApply_dept_name(use_user.getDept_name());
			record.setConfirm_user_iidd(admin.getUser_iidd());
			record.setConfirm_user_name(admin.getUser_name());
			record.setConfirm_dept_id(admin.getDept_id());
			record.setConfirm_dept_name(admin.getDept_name());
			record.setEntity_type("STORAGE");
			record.setEntity_barcode(storage.getStorage_barcode());
			record.setEntity_name(storage.getStorage_name());
			record.setSeclv_name(storage.getSeclv_name());
			record.setConfirm_type("STORAGE_RT");
			// record.setEvent_code(event_code);
			record.setCreate_time(new Date());
			basicMapper.saveConfirmRecord(record);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("storage_code", storage.getStorage_code());
			map.put("storage_status", StorageStatusEnum.SS4.getKey());// 待交接
			storageMapper.updateStorageStatus(map);
		} else {
			// 交接确认关闭
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("storage_code", storage.getStorage_code());
			map.put("storage_status", StorageStatusEnum.SS0.getKey());// 未分配
			storageMapper.updateStorageStatus(map);
			storageMapper.clearStorageUser(storage.getStorage_code());
			// 生成载体生命周期记录：归还
			CycleItem cycleitem = new CycleItem();
			cycleitem.setBarcode(storage.getStorage_barcode());
			cycleitem.setEntity_type("STORAGE");
			cycleitem.setOper_time(new Date());
			cycleitem.setUser_name(use_user.getUser_name());
			cycleitem.setDept_name(use_user.getDept_name());
			cycleitem.setOper("RETURN");
			// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
			// 此处不用启动流程，job_code设置为default
			cycleitem.setJob_code("default");
			ledgerMapper.addCycleItem(cycleitem);
		}

	}
}
