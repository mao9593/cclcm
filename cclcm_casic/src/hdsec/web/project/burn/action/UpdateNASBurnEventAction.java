package hdsec.web.project.burn.action;

import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.burn.model.BurnEvent;
import hdsec.web.project.burn.model.BurnFile;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 修改刻录作业
 * 
 * @author yy
 * 
 */
public class UpdateNASBurnEventAction extends BurnBaseAction {

	private static final long serialVersionUID = 1L;
	private String event_code = "";// 刻录作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String summ = "";// 备注
	private Integer data_type = null;// 数据类型
	private Integer cd_num = 1;// 刻录份数
	private Integer file_num = 0;// 文件数量
	private String file_list = "";// 文件列表
	private String file_seclevel = "";// 文件密级列表
	private String cd_serial = "";// 光盘编号
	private Integer is_proxy = 0;// 是否代理刻录 1代理 0不代理
	private String update = "N";
	private BurnEvent event = null;
	private List<BurnFile> burnFileList = null;
	private String cycle_type = "";
	private String conf_code = "";// 保密编号
	private String info;

	public void setUpdate(String update) {
		this.update = update;
	}

	public List<BurnFile> getBurnFileList() {
		return burnFileList;
	}

	public BurnEvent getEvent() {
		return event;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
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

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public void setData_type(Integer data_type) {
		this.data_type = data_type;
	}

	public void setCd_num(Integer cd_num) {
		this.cd_num = cd_num;
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
		return basicService.getSysUsageList();
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	private void handleFileList() throws Exception {
		for (String onefile : info.split("::")) {
			file_num++;
			String fileName = onefile.split(" __ ")[0];
			String seclv_file = onefile.split(" __ ")[1];
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
			event = burnService.getBurnEventByBurnCode(event_code);
			if (update.equals("Y")) {
				if (event != null) {
					handleFileList();
					/*
					 * String user_iidd = getCurUser().getUser_iidd(); String dept_id = getCurUser().getDept_id();
					 * BurnEvent event = new BurnEvent(user_iidd, dept_id, event_code, seclv_code, usage_code,
					 * project_code, cd_serial, cd_num, is_proxy, summ, data_type, file_num, file_list, file_seclevel,
					 * cycle_type);
					 */
					event.setConf_code(conf_code);
					event.setData_type(data_type);
					event.setCd_num(cd_num);
					event.setSumm(summ);
					event.setFile_num(file_num);
					event.setFile_list(file_list);
					event.setFile_seclevel(file_seclevel);
					burnService.updateBurnEvent(event);
					// burnService.updateBurnEventWithoutUsage(event);
					insertCommonLog("修改刻录申请[" + event_code + "]");
					return "ok";
				} else {
					throw new Exception("无法查询作业信息，请重新尝试。");
				}
			} else {
				if (event != null) {
					// 展示已经上传的文件
					String[] filelist = event.getFile_list().split(CCLCMConstants.DEVIDE_SYMBOL);
					String[] fileseclevel = event.getFile_seclevel().split(CCLCMConstants.DEVIDE_SYMBOL);
					if (filelist.length > 0 && StringUtils.hasLength(filelist[0])) {
						String storePath = PropertyUtil.getBurnFileStorePath();
						burnFileList = new ArrayList<BurnFile>();
						Integer seclv_code;
						String seclv_name = "";
						String file_path = "";
						for (int i = 0; i < filelist.length; i++) {
							seclv_code = Integer.parseInt(fileseclevel[i].trim());
							seclv_name = userService.getSecLevelByCode(seclv_code).getSeclv_name();
							file_path = storePath + "/" + event_code + "/" + filelist[i];
							burnFileList.add(new BurnFile(filelist[i], seclv_code, seclv_name, file_path));
						}
					}
					return SUCCESS;
				} else {
					throw new Exception("申请信息为空，请重新尝试。");
				}
			}
		} else {
			throw new Exception("作业流水号为空，请重新尝试。");
		}
	}

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
	}

	public String getCycle_type() {
		return cycle_type;
	}

	public void setCycle_type(String cycle_type) {
		this.cycle_type = cycle_type;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String getNas_url() throws Exception {
		return PropertyUtil.getNasBurnFileStoreUrl();
	}

	@Override
	public String getNas_username() throws Exception {
		return PropertyUtil.getNasBurnFileStoreUsername();
	}

	@Override
	public String getNas_password() throws Exception {
		return PropertyUtil.getNasBurnFileStorePassword();
	}
}
