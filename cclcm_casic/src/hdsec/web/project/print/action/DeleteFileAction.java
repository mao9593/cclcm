package hdsec.web.project.print.action;

import java.io.File;

public class DeleteFileAction extends PrintBaseAction implements Runnable {

	private static final long serialVersionUID = 1L;
	private String zipfileName = "";

	public String getZipfileName() {
		return zipfileName;
	}

	public void setZipfileName(String zipfileName) {
		this.zipfileName = zipfileName;
	}

	@Override
	public String executeFunction() throws Exception {
		run();
		String adpath = getLocalPath().substring(beginIndex, (getLocalPath().length() - 16));
		// 删除文件
		String str[] = zipfileName.split("/");
		File folder = new File(adpath + "files/des/" + str[0] + "/");
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.getName().equals(str[1])) {
				file.delete();
			}
		}
		folder.delete();
		return "ok";
	}

	private String getLocalPath() throws Exception {
		String localPath = Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath();
		return localPath;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000 * 60 * 2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
