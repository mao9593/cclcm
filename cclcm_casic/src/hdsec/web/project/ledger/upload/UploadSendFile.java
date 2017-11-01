package hdsec.web.project.ledger.upload;

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
import org.springframework.util.StringUtils;

/**
 * 上传外发回执单
 * 
 * @author congxue 2015-1-29
 */
public class UploadSendFile extends HttpServlet {
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
		String paper_barcode = request.getParameter("paper_barcode");
		String uploadFileName = "";
		String sendPath = "";
		try {
			sendPath = PropertyUtil.getUploadSendFilePath();

		} catch (Exception e1) {
			logger.error(e1.getMessage());
			e1.printStackTrace();
		}
		if (!StringUtils.hasLength(sendPath.trim())) {
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
					fis = fi.getInputStream();
					logger.info("InputStream ready for " + uploadFileName);
				}
			}
			sendPath = sendPath + File.separator + paper_barcode;
			File path = new File(sendPath);
			if (!path.exists()) {
				logger.info("path[" + sendPath + "] does not exsit, create it.");
				path.mkdirs();
			}
			if (path.canWrite()) {
				fos = new FileOutputStream(sendPath + File.separator + uploadFileName);
				logger.info("OutputStream ready. Filename:" + sendPath + "\\" + uploadFileName);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				logger.info("上传外发回执单：" + sendPath + "\\" + uploadFileName);
			} else {
				logger.error("指定的外发回执单存放路径[" + sendPath + "]没有可写权限，请设置该路径权限");
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
