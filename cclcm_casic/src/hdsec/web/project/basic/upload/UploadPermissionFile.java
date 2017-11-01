package hdsec.web.project.basic.upload;

import hdsec.web.project.common.PropertyUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * 使用uploadify上传文件
 * 
 * @author yy
 * 
 */
public class UploadPermissionFile extends HttpServlet {

	private static final long serialVersionUID = 2384326745121073713L;
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
		String filepath = "";
		try {
			filepath = PropertyUtil.getUploadPermissionFilePath();
		} catch (Exception e1) {
			logger.error(e1.getMessage());
			e1.printStackTrace();
		}
		if (!StringUtils.hasLength(filepath.trim())) {
			logger.error("上传文件临时存放路径未设置");
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
					// fi.write(saveSourceFile);
					fis = fi.getInputStream();
					logger.info("InputStream ready.");
				}
			}
			File path = new File(filepath);
			// 如果permission不存在，抛异常
			if (!path.exists()) {
				throw new Exception("permission文件夹不存在");
			}
			// 如果已经存在License.copyright文件，重命名以备份
			File licenseFile = new File(filepath + File.separator + "License.copyright");
			if (licenseFile.exists()) {
				String bkLicFilePath = filepath + File.separator + "License.copyright."
						+ new SimpleDateFormat("yyyy-MM-dd_HH_mm").format(new Date()) + ".bk";
				File bkLicFile = new File(bkLicFilePath);
				licenseFile.renameTo(bkLicFile);
				logger.info("file[" + licenseFile + "] exists, rename it[" + bkLicFilePath + "] for backup.");
			}
			// 写入上传的授权文件
			if (path.canWrite()) {
				fos = new FileOutputStream(filepath + "\\" + uploadFileName);
				logger.info("OutputStream ready. Filename:" + filepath + "\\" + uploadFileName);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
			} else {
				logger.error("指定的上传文件临时存放路径[" + filepath + "]没有可写权限，请设置该路径权限");
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
