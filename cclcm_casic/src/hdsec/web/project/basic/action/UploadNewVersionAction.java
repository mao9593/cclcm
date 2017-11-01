package hdsec.web.project.basic.action;

import hdsec.web.project.common.PropertyUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *版本管理
 * @author yueying
 *
 */
public class UploadNewVersionAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	
	private boolean is_upload = false;
	private String comment;
	private String need_upload_file_name;
	private String type;
	
	private String handleFileList() throws Exception {
		String fileName = null;
		// 从配置文件中读取刻录文件的临时存储路径和正式存储路径
		String tempPath = PropertyUtil.getUploadVersionFileTempPath();
		String storePath = PropertyUtil.getUploadVersionFileStorePath();
		// 在临时路径后加上用户ID
		tempPath = tempPath + File.separator + getCurUser().getUser_iidd();
		File path = new File(tempPath);
		if (!path.exists()) {
			path.mkdirs();
		}
		File[] files = path.listFiles();
		for (File file : files) {
			if (need_upload_file_name.equals(file.getName())) {
				fileName = saveFile(file, storePath);
			}
		}
		return fileName;
	}
	
	private String saveFile(File file, String storePath) throws IOException {
		File f = new File(storePath);
		if (!f.exists()) {
			f.mkdirs();
		}
		String fileName = file.getName();
		String storeName = storePath + File.separator + fileName;
		File oneFile = new File(storeName);
		oneFile.createNewFile();
		FileInputStream fis = new FileInputStream(file);
		FileOutputStream fos = new FileOutputStream(oneFile);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		int count = -1;
		byte data[] = new byte[2048];
		while ((count = fis.read(data, 0, 2048)) != -1) {
			bos.write(data, 0, count);
		}
		bos.flush();
		bos.close();
		fos.close();
		fis.close();
		return fileName;
	}
	
	private String unzipAndSaveFile(File file, String storePath) throws Exception {
		File f = new File(storePath);
		if (!f.exists()) {
			f.mkdirs();
		}
		String fileName = null;
		FileInputStream fis = new FileInputStream(file);
		ZipInputStream zis = new ZipInputStream(fis);
		ZipEntry entry = null;
		while ((entry = zis.getNextEntry()) != null) {
			if (entry.isDirectory()) {
				new File(storePath + File.separator + entry.getName()).mkdirs();
			} else {
				byte data[] = new byte[2048];
				String tempName = entry.getName();
				if (tempName != null && tempName.endsWith(".exe")) {
					fileName = tempName;
				}
				tempName = storePath + File.separator + tempName;
				File oneFile = new File(tempName);
				oneFile.createNewFile();
				FileOutputStream fos = new FileOutputStream(oneFile);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				int count = -1;
				while ((count = zis.read(data, 0, 2048)) != -1) {
					bos.write(data, 0, count);
				}
				bos.flush();
				bos.close();
				fos.close();
			}
		}
		zis.close();
		try {
			if (null == fileName) {
				throw (new Exception(" 文件没有可执行的exe文件"));
			}
			logger.debug("copy file:" + fileName);
		} finally {
			file.delete();
		}
		return fileName;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (is_upload) {
			String fileName = handleFileList();
			String relstorePath = PropertyUtil.getRelUploadVersionFileStorePath();
			basicService.saveFileInfo(fileName, relstorePath, getCurUser().getUser_iidd(), comment, type);
			insertAdminLog("保存上传文件信息 ， 文件名称： " + fileName + "   文件路径： " + relstorePath);
			return type;
		}
		return SUCCESS;
	}
	
	public void setIs_upload(boolean is_upload) {
		this.is_upload = is_upload;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getNeed_upload_file_name() {
		return need_upload_file_name;
	}
	
	public void setNeed_upload_file_name(String need_upload_file_name) {
		this.need_upload_file_name = need_upload_file_name;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
}
