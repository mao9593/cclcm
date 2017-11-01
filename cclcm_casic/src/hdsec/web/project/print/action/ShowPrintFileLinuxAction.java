package hdsec.web.project.print.action;

import hdsec.web.project.common.FileEncryptionUtil;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;

public class ShowPrintFileLinuxAction extends PrintBaseAction {

	private static final long serialVersionUID = 1L;
	private String secpass = "";
	private String unzipdirname = "";
	private String pagecount = "";
	private String errorMsg = "";

	@Override
	public String getErrorMsg() {
		return errorMsg;
	}

	@Override
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getSecpass() {
		return secpass;
	}

	public void setSecpass(String secpass) {
		this.secpass = secpass;
	}

	public String getUnzipdirname() {
		return unzipdirname;
	}

	public void setUnzipdirname(String unzipdirname) {
		this.unzipdirname = unzipdirname;
	}

	public String getPagecount() {
		return pagecount;
	}

	public void setPagecount(String pagecount) {
		this.pagecount = pagecount;
	}

	public String getRemoteAddr() {
		return getRequest().getScheme() + "://" + getRequest().getServerName() + ":" + getRequest().getServerPort();
	}

	@Override
	public String executeFunction() throws Exception {
		String adpath = null;
		adpath = getLocalPath().substring(beginIndex, (getLocalPath().length() - 16));
		// 创建路径
		File path = new File(adpath + "files/des/");
		if (!(path.exists())) {
			logger.info("path[" + path + "] does not exsit, create it.");
			path.mkdirs();
		}
		File zipFile = new File(adpath + "files/linux_print/" + getUnzipdirname() + ".zip");
		if (zipFile.exists()) {
			// 解密
			FileEncryptionUtil.decryptFile(zipFile.getPath(), adpath + "files/des/" + getUnzipdirname() + ".zip");

			// 解压
			Project p = new Project();
			Expand e = new Expand();
			e.setProject(p);
			e.setSrc(new File(adpath + "files/des/" + getUnzipdirname() + ".zip"));
			e.setOverwrite(false);
			e.setDest(new File(adpath + "files/des/" + getUnzipdirname()));
			e.setEncoding("GBK");
			e.execute();
			File folder = new File(adpath + "files/des/");
			File[] files = folder.listFiles();
			for (File file : files) {
				if (file.getName().equals(getUnzipdirname() + ".zip")) {
					file.delete();
				}
			}
			folder = new File(adpath + "files/des/" + getUnzipdirname() + "/");
			files = folder.listFiles();
			for (File file : files) {
				if (acceptPDF(file.getName())) {
					setUnzipdirname(getUnzipdirname() + "/" + file.getName());
				}
			}
		} else {
			setErrorMsg("你查看的文件不存在或被删除。");
			return "exception";
		}

		return SUCCESS;
	}

	private String getLocalPath() throws Exception {
		String localPath = Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath();
		return localPath;
	}

	private boolean acceptPDF(String fileName) {
		if (fileName.endsWith(".pdf")) {
			return true;
		}
		return false;
	}
}
