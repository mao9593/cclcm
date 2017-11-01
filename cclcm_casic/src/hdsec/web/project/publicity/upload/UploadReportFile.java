package hdsec.web.project.publicity.upload;

import hdsec.web.project.common.bm.BMPropertyUtil;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.EncryptFile;
import hdsec.web.project.user.model.SecUser;

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
import org.springframework.util.StringUtils;

/**
 * 使用uploadreportfile上传文件
 * 
 * @author lishu
 * 
 */
public class UploadReportFile extends HttpServlet {

	private static final long serialVersionUID = 2384326745121073713L;
	private static final int FILE_CONTENT_SIZE = 2048;
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String coding = request.getCharacterEncoding();
		String event_code = request.getParameter("event_code");
		String uploadFileName = "";
		String tempPath = "";

		try {
			tempPath = BMPropertyUtil.getReportStrorePath();
		} catch (Exception e1) {
			logger.error(e1.getMessage());
			e1.printStackTrace();
		}
		if (!StringUtils.hasLength(tempPath.trim())) {
			logger.error("上传涉密活动文件临时存放路径未设置");
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
					if (uploadFileName == null || "".equals(uploadFileName.trim())) {
						continue;
					}
					uploadFileName = event_code + "-" + uploadFileName;
					fis = fi.getInputStream();
					logger.info("InputStream ready.");
				}
			}
			SecUser curUser = (SecUser) request.getSession().getAttribute(Constants.SESSION_USER_KEY);
			tempPath = tempPath + File.separator + curUser.getUser_iidd();
			File path = new File(tempPath);
			if (!path.exists()) {
				logger.info("path[" + tempPath + "] does not exsit, create it.");
				path.mkdirs();
			}
			if (path.canWrite()) {
				fos = new FileOutputStream(tempPath + "\\" + uploadFileName);
				logger.info("OutputStream ready. Filename:" + tempPath + "\\" + uploadFileName);
				byte[] buffer = new byte[FILE_CONTENT_SIZE];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					EncryptFile.encryptBuffer(buffer);
					fos.write(buffer, 0, len);
				}
			} else {
				logger.error("指定的上传涉密活动文件临时存放路径[" + tempPath + "]没有可写权限，请设置该路径权限");
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
