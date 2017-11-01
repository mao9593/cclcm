package hdsec.web.project.secplace.action;

import hdsec.web.project.secplace.model.EntitySecplace;

import org.springframework.util.StringUtils;

/**
 * 查看涉密场所详细信息
 * 
 * @author liuyaling 2015-5-13
 */
public class ViewSecplaceDetailAction extends SecplaceBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	EntitySecplace secplace = null;
	private String secplace_barcode = "";

	public EntitySecplace getSecplace() {
		return secplace;
	}

	public void setSecplace(EntitySecplace secplace) {
		this.secplace = secplace;
	}

	public String getSecplace_barcode() {
		return secplace_barcode;
	}

	public void setSecplace_barcode(String secplace_barcode) {
		this.secplace_barcode = secplace_barcode;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(secplace_barcode)) {
			secplace = secplaceService.getSecplaceByBarcode(secplace_barcode);
			return SUCCESS;
		} else {
			throw new Exception("无法查询条码号，请重新尝试。");
		}
	}

}