package hdsec.web.project.secmanage.action;

import hdsec.web.project.activiti.service.ActivitiService;
import hdsec.web.project.basic.mapper.BasicMapper;
import hdsec.web.project.basic.service.BasicPrcManage;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.action.BaseAction;
import hdsec.web.project.common.bm.BMPropertyUtil;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PrefixFilenameFilter;
import hdsec.web.project.log.model.AdminOperLog;
import hdsec.web.project.log.model.CommonOperLog;
import hdsec.web.project.log.service.LogService;
import hdsec.web.project.publicity.service.PublicityService;
import hdsec.web.project.secactivity.service.SecActivityService;
import hdsec.web.project.secmanage.model.FileListEvent;
import hdsec.web.project.secmanage.service.SecManageService;
import hdsec.web.project.secplace.service.SecplaceService;
import hdsec.web.project.securityuser.service.SecurityUserService;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

public abstract class SecManageBaseAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Resource
	protected UserService userService;
	@Resource
	protected BasicService basicService;
	@Resource
	protected LogService logService;
	@Resource
	protected SecManageService secManageService;
	@Resource
	protected SecActivityService secActivityService;
	@Resource
	protected SecplaceService secplaceService;
	@Resource
	protected PublicityService publicityService;
	@Resource
	protected ActivitiService activitiService;
	@Resource
	protected BasicPrcManage basicPrcManage;
	@Resource
	protected BasicMapper basicMapper;
	@Resource
	protected SecurityUserService securityUserService;

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
		return "secmanage";
	}

	private void diskWritenCompleted(File source) throws InterruptedException {
		long size = source.length();
		Thread.sleep(2000);
		long size1 = source.length();

		while (size < size1) {
			Thread.sleep(2000);
			size = size1;
			size1 = source.length();
		}
	}

	private void moveFile(File source, String destPath, String fileName) throws Exception {
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

	public void handleFileList(String event_code, FileListEvent file, String flag) throws Exception {
		// 从配置文件中读取保密管理文件的存储路径
		String storePath = BMPropertyUtil.getSecManageStrorePath();
		// 在临时路径后加上用户ID
		String tempPath = storePath + File.separator + getCurUser().getUser_iidd();
		File path = new File(tempPath);
		PrefixFilenameFilter filenameFilter = new PrefixFilenameFilter(event_code + "-");
		if (path.isDirectory()) {
			File[] files = path.listFiles(filenameFilter);
			file.setFile_num(files.length);
			for (File file1 : files) {
				String fileName = file1.getName();
				fileName = fileName.substring(fileName.indexOf("-") + 1);
				try {
					if (flag.equals("approve")) {
						moveFile(file1, storePath + File.separator + event_code + File.separator + "approve", fileName);
					} else {
						moveFile(file1, storePath + File.separator + event_code, fileName);
					}

					file.setFile_list(file.getFile_list() + fileName + CCLCMConstants.DEVIDE_SYMBOL);
					logger.debug("copy file:" + fileName);
				} finally {
					file1.delete();
				}
			}
			if (file.getFile_list().endsWith(CCLCMConstants.DEVIDE_SYMBOL)) {
				file.setFile_list(file.getFile_list().substring(0, file.getFile_list().length() - 1));
			}
		}
	}

	protected List<ApproverUser> getFixApprover(String dept_id, String role_id) {
		List<ApproverUser> approvers = new ArrayList<ApproverUser>();

		Map<String, String> map = new HashMap<String, String>();
		map.put("dept_id", dept_id);
		map.put("role_id", role_id);
		approvers.addAll(activitiService.getApproversByDeptRole(map));
		if ((approvers != null) && (approvers.size() > 0)) {
			return approvers;
		} else {
			/*
			 * String dept_name = ""; String role_name = ""; dept_name = userService.getDeptNameByDeptId(dept_id);
			 * role_name = userService.getRoleNameByRoleId(role_id);
			 * 
			 * try { throw new Exception("WARN:部门[" + dept_name + "]内不存在拥有角色[" + role_name + "]的用户，没有符合条件的审批人，请联系管理员。");
			 * } catch (Exception e) { e.printStackTrace(); }
			 */
			return null;
		}
	}
}
