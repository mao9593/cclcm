package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysBarcode;
import hdsec.web.project.basic.model.SysConsole;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.model.SecLevel;

import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 添加条码
 * 
 * @author yueying
 * 
 */
public class AddBarcodeAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String define_seclv;
	private String define_textcontent;
	private String define_usage;
	private String define_project;
	private String define_console;
	private String barcode_name;
	private int bypage;
	private int form;
	private int position;
	private int pageno;
	private int perpage;
	private int cord_x;
	private int cord_y;
	private int size_x;
	private int size_y;
	private String is_default;
	private boolean is_save = false;
	private String is_first = "Y";
	
	public void setDefine_seclv(String define_seclv) {
		if (StringUtils.hasLength(define_seclv)) {
			this.define_seclv = Constants.COMMON_SEPARATOR
					+ define_seclv.replaceAll(" ", "").replaceAll(",", Constants.COMMON_SEPARATOR)
					+ Constants.COMMON_SEPARATOR;
		}
	}
	
	public void setDefine_textcontent(String define_textcontent) {
		if (StringUtils.hasLength(define_textcontent)) {
			this.define_textcontent = Constants.COMMON_SEPARATOR
					+ define_textcontent.replaceAll(" ", "").replaceAll(",", Constants.COMMON_SEPARATOR)
					+ Constants.COMMON_SEPARATOR;
		}
	}
	
	public void setDefine_usage(String define_usage) {
		if (StringUtils.hasLength(define_usage)) {
			this.define_usage = Constants.COMMON_SEPARATOR
					+ define_usage.replaceAll(" ", "").replaceAll(",", Constants.COMMON_SEPARATOR)
					+ Constants.COMMON_SEPARATOR;
		}
	}
	
	public void setDefine_project(String define_project) {
		if (StringUtils.hasLength(define_project)) {
			this.define_project = Constants.COMMON_SEPARATOR
					+ define_project.replaceAll(" ", "").replaceAll(",", Constants.COMMON_SEPARATOR)
					+ Constants.COMMON_SEPARATOR;
		}
	}
	
	public void setDefine_console(String define_console) {
		if (StringUtils.hasLength(define_console)) {
			this.define_console = Constants.COMMON_SEPARATOR
					+ define_console.replaceAll(" ", "").replaceAll(",", Constants.COMMON_SEPARATOR)
					+ Constants.COMMON_SEPARATOR;
		}
	}
	
	public List<SecLevel> getSeclvs() {
		return userService.getSecLevel();
	}
	
	public List<SysUsage> getUsages() {
		return basicService.getSysUsageList();
	}
	
	public List<SysProject> getProjects() {
		return basicService.getSysProjectList();
	}
	
	public List<SysConsole> getConsoles() {
		return basicService.getSysConsoleList();
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (is_save) {
			SysBarcode barcode = new SysBarcode();
			barcode.setBarcode_name(barcode_name);
			barcode.setBypage(bypage);
			barcode.setConsole_code(define_console);
			barcode.setCord_x(cord_x);
			barcode.setCord_y(cord_y);
			barcode.setForm(form);
			barcode.setPageno(pageno);
			barcode.setPerpage(perpage);
			barcode.setPosition(position);
			barcode.setProject_code(define_project);
			barcode.setSeclv_code(define_seclv);
			barcode.setSize_x(size_x);
			barcode.setSize_y(size_y);
			barcode.setTextcontent(define_textcontent);
			barcode.setUsage_code(define_usage);
			barcode.setIs_default(is_default);
			basicService.saveOneBarcode(barcode);
			insertAdminLog("添加条码：代号[" + barcode_name + "]");
			return "ok";
		} else {
			List<SysBarcode> barcodes = basicService.getAllSysBarcodes();
			if (null != barcodes && barcodes.size() > 0) {
				is_first = "N";
			}
			return SUCCESS;
		}
	}
	
	public String getUser_name() {
		return getCurUser().getUser_name();
	}
	
	public String getDefine_seclv() {
		return define_seclv;
	}
	
	public String getDefine_textcontent() {
		return define_textcontent;
	}
	
	public String getDefine_usage() {
		return define_usage;
	}
	
	public String getDefine_project() {
		return define_project;
	}
	
	public String getDefine_console() {
		return define_console;
	}
	
	public String getBarcode_name() {
		return barcode_name;
	}
	
	public void setBarcode_name(String barcode_name) {
		this.barcode_name = barcode_name;
	}
	
	public int getBypage() {
		return bypage;
	}
	
	public void setBypage(int bypage) {
		this.bypage = bypage;
	}
	
	public int getForm() {
		return form;
	}
	
	public void setForm(int form) {
		this.form = form;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public int getPageno() {
		return pageno;
	}
	
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	
	public int getPerpage() {
		return perpage;
	}
	
	public void setPerpage(int perpage) {
		this.perpage = perpage;
	}
	
	public int getCord_x() {
		return cord_x;
	}
	
	public void setCord_x(int cord_x) {
		this.cord_x = cord_x;
	}
	
	public int getCord_y() {
		return cord_y;
	}
	
	public void setCord_y(int cord_y) {
		this.cord_y = cord_y;
	}
	
	public int getSize_x() {
		return size_x;
	}
	
	public void setSize_x(int size_x) {
		this.size_x = size_x;
	}
	
	public int getSize_y() {
		return size_y;
	}
	
	public void setSize_y(int size_y) {
		this.size_y = size_y;
	}
	
	public void setIs_save(boolean is_save) {
		this.is_save = is_save;
	}
	
	public String getIs_default() {
		return is_default;
	}
	
	public void setIs_default(String is_default) {
		this.is_default = is_default;
	}
	
	public String getIs_first() {
		return is_first;
	}
	
	public void setIs_first(String is_first) {
		this.is_first = is_first;
	}
	
}
