package hdsec.web.project.enter.upload;

import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.common.util.EncryptFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

/**
 * 上传扫描文件
 * 
 * @author gaoximin 2014-10-17
 */
public class UploadScan extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String event_code = request.getParameter("event_code");
		String coding = request.getCharacterEncoding();
		String uploadFileName = "";
		String storePath = "";
		try {
			storePath = PropertyUtil.getScanFileStorePath(event_code);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		OutputStream fos = null;
		InputStream fis = null;
		try {
			ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
			sfu.setHeaderEncoding(coding);
			@SuppressWarnings("unchecked")
			List<FileItem> fileList = sfu.parseRequest(request);
			for (int i = 0; i < fileList.size(); i++) {
				FileItem fi = fileList.get(i);
				if (!fi.isFormField()) {
					uploadFileName = fi.getName();
					fis = fi.getInputStream();
					logger.info("InputStream ready.");
				}
			}
			File path = new File(storePath);
			if (!path.exists()) {
				logger.info("path[" + storePath + "] does not exsit, create it.");
				path.mkdirs();
			}
			if (path.canWrite()) {
				fos = new FileOutputStream(storePath + "\\" + uploadFileName);
				logger.info("OutputStream ready. Filename:" + storePath + "\\" + uploadFileName);
				byte[] buffer = new byte[4096];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					EncryptFile.encryptBuffer(buffer);
					fos.write(buffer, 0, len);
				}
				logger.info("上传扫描文件：" + storePath + "\\" + uploadFileName);
			} else {
				logger.error("指定的扫描文件存放路径[" + storePath + "]没有可写权限，请设置该路径权限");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			if (fis != null) {
				fis.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
	}
}
