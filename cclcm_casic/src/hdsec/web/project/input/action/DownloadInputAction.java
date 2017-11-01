package hdsec.web.project.input.action;

import hdsec.web.project.common.util.EncryptFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class DownloadInputAction extends InputBaseAction {
	private static final long serialVersionUID = 1L;
	private String file_path = "";
	private String file_name = "";

	// private String type = "";

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	private OutputStream getOutputStream(String fileName) throws IOException {
		HttpServletResponse response = getResponse();
		response.reset();
		response.setHeader("Content-disposition", "attachment;filename="
				+ new String(fileName.getBytes(), "iso-8859-1"));// 导出日志的名称中汉字的转换
		response.setContentType("application/octet-stream");
		return response.getOutputStream();
	}

	@Override
	public String executeFunction() throws Exception {
		String fileName = file_name;
		OutputStream os = null;
		FileInputStream fis = null;
		try {
			File file = new File(file_path);
			if (!file.exists()) {
				logger.error("文件[" + fileName + "]不存在或者已经被删除。");
				return "blank";
			}
			if (file.canRead()) {
				os = getOutputStream(fileName);
				fis = new FileInputStream(file);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					EncryptFile.encryptBuffer(buffer);
					os.write(buffer, 0, len);
				}

			} else {
				throw new Exception("文件[" + fileName + "]被占用，请稍候再试。");
			}
		} catch (Exception e) {
			dealException(e);
			return "exception";
		} finally {
			if (fis != null) {
				fis.close();
			}
			if (os != null) {
				os.flush();
				os.close();
			}
		}
		return SUCCESS;

	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

}
