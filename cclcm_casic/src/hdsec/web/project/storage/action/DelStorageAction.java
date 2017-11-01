package hdsec.web.project.storage.action;

/**
 * 删除存储介质
 * 2014-4-23 下午9:27:39
 * 
 * @author renmingfei
 */
public class DelStorageAction extends StorageBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String storage_code = "";
	
	public void setStorage_code(String storage_code) {
		this.storage_code = storage_code;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (storage_code.isEmpty()) {
			throw new Exception("参数错误，没有存储介质编号");
		} else {
			storageService.delStorageByCode(storage_code);
			insertCommonLog("封存存储介质：编号[" + storage_code + "]");
		}
		return SUCCESS;
	}
	
}
