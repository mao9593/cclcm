package hdsec.web.project.basic.upload;

import hdsec.web.project.common.PropertyUtil;

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
 * 上传常用文档
 * 
 * @author renmingfei 2014-10-15
 */
public class UploadFile extends HttpServlet {
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
		// request.setCharacterEncoding("GBK");
		String coding = request.getCharacterEncoding();
		String uploadFileName = "";
		String tempPath = "";
		try {
			tempPath = PropertyUtil.getDownloadFileStorePath();
		} catch (Exception e1) {
			logger.error(e1.getMessage());
			e1.printStackTrace();
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
					// fi.write(saveSourceFile);
					fis = fi.getInputStream();
					logger.info("InputStream ready for " + uploadFileName);
				}
			}
			File path = new File(tempPath);
			if (!path.exists()) {
				logger.info("path[" + tempPath + "] does not exsit, create it.");
				path.mkdirs();
			}
			if (path.canWrite()) {
				fos = new FileOutputStream(tempPath + "\\" + uploadFileName);
				logger.info("OutputStream ready. Filename:" + tempPath + "\\" + uploadFileName);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				logger.info("上传常用文档：" + tempPath + "\\" + uploadFileName);
			} else {
				logger.error("指定的常用文档存放路径[" + tempPath + "]没有可写权限，请设置该路径权限");
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
