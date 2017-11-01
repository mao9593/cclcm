package hdsec.web.project.computer.action;

public class DeleteHardDiskAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;

	private String hdisk_no = "";

	public String getHdisk_no() {
		return hdisk_no;
	}

	public void setHdisk_no(String hdisk_no) {
		this.hdisk_no = hdisk_no;
	}

	@Override
	public String executeFunction() throws Exception {

		if (!hdisk_no.equals("")) {
			computerService.deleteEntityHDisk(hdisk_no);
			return "ok";
		}
		return SUCCESS;
	}

}