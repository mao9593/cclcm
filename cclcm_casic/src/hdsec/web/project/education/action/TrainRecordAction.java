package hdsec.web.project.education.action;

import hdsec.web.project.common.bm.BMPropertyUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 上传保密教育学习材料
 * 
 * @author lishu
 * 
 */
public class TrainRecordAction extends EducationBaseAction {
	private static final long serialVersionUID = 1L;

	private String delfilepath = "";
	private String storepath = "";// 文档存储路径
	private String file_id = "";// 文件编号
	private String file_name = "";// 文件名
	/**
	 * private Integer file_category = null;// 文件类别 private String storePath = "";
	 * 
	 */
	private String type = "";// 类型
	private String comment = "";// 备注
	private List<String> fileNameList = null;

	public List<String> getFileNameList() {
		return fileNameList;
	}

	public String getStorePath() {
		return storepath;
	}

	public String getStorepath() {
		return storepath;
	}

	public void setDelfilepath(String delfilepath) {
		this.delfilepath = delfilepath;
	}

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

//	private List<String> handleFileList() throws Exception {
		// 从配置文件中读取文档的存储路径
	//	storepath = BMPropertyUtil.getLearningFileStorePath();
	//	File path = new File(storepath);
	//	if (!path.exists()) {
	//		path.mkdirs();
	//	}
	//	File[] files = path.listFiles();
	//	fileNameList = new ArrayList<String>();
		// storePath
	//	for (File file : files) {
	//		fileNameList.add(file.getName());
	//	}
	//	return fileNameList;
	//}

	@Override
	public String executeFunction() throws Exception {
		//if (StringUtils.hasLength(delfilepath)) {
		//	storepath = BMPropertyUtil.getLearningFileStorePath();
		//	File deletePath = new File(storepath + "\\" + delfilepath);
		//	if (!deletePath.isDirectory()) {
		//		deletePath.delete();
		//	}
		//}
		//handleFileList();

		return SUCCESS;
	}
}
