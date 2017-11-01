package hdsec.web.project.storage.action;

import hdsec.web.project.storage.model.EntityStorage;
import hdsec.web.project.storage.model.StorageStatusEnum;
import hdsec.web.project.user.model.SecUser;

import java.util.HashMap;
import java.util.Map;

/**
 * 修改存储介质状态
 * 2014-4-23 下午10:34:56
 * 
 * @author renmingfei
 */
public class UpdateStorageStatusAction extends StorageBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String storage_code = "";// 主键ID
	private Integer storage_status = null;
	private String result = "";
	
	public void setStorage_code(String storage_code) {
		this.storage_code = storage_code;
	}
	
	public void setStorage_status(Integer storage_status) {
		this.storage_status = storage_status;
	}
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (storage_code.isEmpty()) {
			setResult("缺少参数[存储介质编号],介质状态修改失败");
		} else {
			try {
				EntityStorage storage = storageService.getStorageByCode(storage_code);
				SecUser admin = getCurUser();
				
				if(storage_status == 0){//收回
					storageService.returnStorage(storage, admin);
				}else{
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("storage_code", storage_code);
					if (storage_status == -2) {// 还原
						if (storage.getUse_user_iidd().isEmpty()) {
							storage_status = StorageStatusEnum.SS0.getKey();
						} else {
							storage_status = StorageStatusEnum.SS1.getKey();
						}
					}
					map.put("storage_status", storage_status);
					storageService.updateStorageStatus(map);
				}
				
				setResult("存储介质状态更新成功");
				insertCommonLog("更改存储介质状态：介质编号[" + storage_code + "],更改后状态["
						+ StorageStatusEnum.valueOf("SS" + storage_status).getName() + "]");
			} catch (Exception e) {
				logger.error(e.getMessage());
				setResult("出现异常，介质状态修改失败");
			}
		}
		return SUCCESS;
	}
}
