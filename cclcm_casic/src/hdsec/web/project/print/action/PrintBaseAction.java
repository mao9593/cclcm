package hdsec.web.project.print.action;

import hdsec.web.project.activiti.service.ActivitiService;
import hdsec.web.project.basic.service.BasicPrcManage;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.common.action.BaseAction;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.enter.service.EnterService;
import hdsec.web.project.ledger.service.LedgerService;
import hdsec.web.project.log.model.AdminOperLog;
import hdsec.web.project.log.model.CommonOperLog;
import hdsec.web.project.log.service.LogService;
import hdsec.web.project.print.mapper.PrintMapper;
import hdsec.web.project.print.model.FixAccording;
import hdsec.web.project.print.service.PrintService;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

/**
 * 打印模块action的父类
 * 
 * @author renmingfei
 * 
 */
public abstract class PrintBaseAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Resource
	protected UserService userService;
	@Resource
	protected LogService logService;
	@Resource
	protected PrintService printService;
	@Resource
	protected BasicService basicService;
	@Resource
	protected ActivitiService activitiService;
	@Resource
	protected BasicPrcManage basicPrcManage;
	@Resource
	protected LedgerService ledgerService;
	@Resource
	protected EnterService enterService;
	@Resource
	protected PrintMapper printMapper;

	protected AdminOperLog adminOperLog = null;
	protected CommonOperLog commonOperLog = null;
	protected SecUser curUser = getSecUserFromSession();

	public SecUser getCurUser() {
		return curUser;
	}

	public void insertAdminLog(String detail) {
		adminOperLog = new AdminOperLog(curUser.getUser_iidd(), curUser.getUser_name(), curUser.getDept_name(), detail,
				"成功", new Date(), Constants.LOG_OPERATION, getRequest().getRemoteAddr(), getRequest().getRemoteHost(),
				"admin");
		logService.addAdminOperLog(adminOperLog);
	}

	public void insertCommonLog(String detail) {
		commonOperLog = new CommonOperLog(curUser.getUser_iidd(), curUser.getUser_name(), curUser.getDept_name(),
				detail, "成功", new Date(), Constants.LOG_OPERATION, getRequest().getRemoteAddr(), getRequest()
						.getRemoteHost(), "admin");
		logService.addCommonOperLog(commonOperLog);
	}

	@Override
	protected String getModuleName() {
		return "print";
	}

	protected void moveFile(File source, String destPath, String fileName) throws Exception {
		if (!source.isFile()) {
			throw new Exception("Source File[" + source.getName() + "] is not a file.");
		}
		if (!StringUtils.hasLength(destPath.trim())) {
			throw new Exception("The target path of copy action is blank.");
		}
		try {
			File path = new File(destPath);
			if (!path.exists()) {
				logger.debug("path[" + path + "] does not exsit, create it.");
				path.mkdirs();
			}
			if (path.canWrite()) {
				// 等待文件完成写入磁盘
				diskWritenCompleted(source);
				if (source.renameTo(new File(destPath + File.separator + fileName))) {
					logger.info("remove file[" + source.getName() + "] successfully.");
				} else {
					throw new Exception("remove file[" + source.getName() + "] fail.");
				}
			} else {
				throw new Exception("The target path of remove action is not writable");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private void diskWritenCompleted(File source) throws InterruptedException {
		long size = source.length();
		// System.out.println("length of file is :" + size);
		Thread.sleep(2000);
		long size1 = source.length();
		// System.out.println("length of file is :" + size1);
		while (size < size1) {
			Thread.sleep(2000);
			size = size1;
			size1 = source.length();
			// System.out.println("length of file is :" + size1);
		}
	}
	public List<FixAccording> getFixList1() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", "1");
		map.put("dept_id", curUser.getDept_id());
		return printService.getFixAccordingByType(map);
	}

	public List<FixAccording> getFixList2() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", "2");
		map.put("dept_id", curUser.getDept_id());
		return printService.getFixAccordingByType(map);
	}

	public List<FixAccording> getFixList3() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", "3");
		map.put("dept_id", curUser.getDept_id());
		return printService.getFixAccordingByType(map);
	}

}
