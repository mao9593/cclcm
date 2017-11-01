package hdsec.web.project.burn.action;

import hdsec.web.project.activiti.service.ActivitiService;
import hdsec.web.project.basic.service.BasicPrcManage;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.burn.service.BurnService;
import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.common.action.BaseAction;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.log.model.AdminOperLog;
import hdsec.web.project.log.model.CommonOperLog;
import hdsec.web.project.log.service.LogService;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

public abstract class BurnBaseAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Resource
	protected UserService userService;
	@Resource
	protected BasicService basicService;
	@Resource
	protected LogService logService;
	@Resource
	protected BurnService burnService;
	@Resource
	protected ActivitiService activitiService;
	@Resource
	protected BasicPrcManage basicPrcManage;

	protected AdminOperLog adminOperLog = null;
	protected CommonOperLog commonOperLog = null;
	protected SecUser curUser = getSecUserFromSession();

	public SecUser getCurUser() {
		return curUser;
	}

	public void insertCommonLog(String detail) {
		commonOperLog = new CommonOperLog(curUser.getUser_iidd(), curUser.getUser_name(), curUser.getDept_name(),
				detail, "成功", new Date(), Constants.LOG_OPERATION, getRequest().getRemoteAddr(), getRequest()
						.getRemoteHost(), "admin");
		logService.addCommonOperLog(commonOperLog);
	}

	@Override
	protected String getModuleName() {
		return "burn";
	}

	protected void copyFile(File source, String destPath, String fileName) throws Exception {
		if (!source.isFile()) {
			throw new Exception("Source File[" + source.getName() + "] is not a file.");
		}
		if (!StringUtils.hasLength(destPath.trim())) {
			throw new Exception("The target path of copy action is blank.");
		}
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			File path = new File(destPath);
			if (!path.exists()) {
				logger.debug("path[" + path + "] does not exsit, create it.");
				path.mkdirs();
			}
			if (path.canWrite()) {
				fos = new FileOutputStream(destPath + File.separator + fileName);
				fis = new FileInputStream(source);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
			} else {
				throw new Exception("The target path of copy action is not writable");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (fis != null) {
				fis.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
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

	public String getNas_url() throws Exception {
		return PropertyUtil.getNasBurnFileStoreUrl();
	}

	public String getNas_username() throws Exception {
		return PropertyUtil.getNasBurnFileStoreUsername();
	}

	public String getNas_password() throws Exception {
		return PropertyUtil.getNasBurnFileStorePassword();
	}
}
