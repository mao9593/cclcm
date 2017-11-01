package hdsec.web.project.storage.action;

import hdsec.web.project.user.model.SecUser;

/**
 * 分配用户 2014-4-23 下午11:07:26
 * 
 * @author renmingfei
 */
public class DistributeStorageAction extends StorageBaseAction {

	private static final long serialVersionUID = 1L;
	private String storage_code = "";// 主键ID
	private String use_user_iidd = "";
	private String result = "";

	public void setStorage_code(String storage_code) {
		this.storage_code = storage_code;
	}

	public void setUse_user_iidd(String use_user_iidd) {
		this.use_user_iidd = use_user_iidd;
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
			setResult("缺少参数[存储介质编号],使用用户分配失败");
		} else {
			try {
				SecUser admin = getCurUser();
				SecUser use_user = userService.getSecUserByUid(use_user_iidd);
				storageService.distributeStorage(storage_code, use_user, admin);
				setResult("使用用户分配成功");
				insertCommonLog("分配存储介质使用用户：介质编号[" + storage_code + "],用户[" + use_user.getUser_name() + "]");
			} catch (Exception e) {
				logger.error(e.getMessage());
				setResult("出现异常，使用用户分配失败");
			}
		}
		return SUCCESS;
	}
}
