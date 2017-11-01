package hdsec.web.project.computer.action;

public class DeleteEventAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String computer_barcode = "";
	private String device_barcode = "";
	private String hdisk_no = "";

	public void setDevice_barcode(String device_barcode) {
		this.device_barcode = device_barcode;
	}

	public void setComputer_barcode(String computer_barcode) {
		this.computer_barcode = computer_barcode;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getHdisk_no() {
		return hdisk_no;
	}

	public void setHdisk_no(String hdisk_no) {
		this.hdisk_no = hdisk_no;
	}

	public String getComputer_barcode() {
		return computer_barcode;
	}

	public String getDevice_barcode() {
		return device_barcode;
	}

	@Override
	public String executeFunction() throws Exception {
		if (!computer_barcode.equals("")) {
			computerService.deleteEntityComputer(computer_barcode);
			return "ok";
		}
		if (!event_code.equals("")) {
			computerService.deleteEvent(event_code);
			if (event_code.contains("BOOK")) {
				return "ok_book";
			}
		}
		if (!device_barcode.equals("")) {
			computerService.deleteEntityBook(device_barcode);
			return "book";
		}
		return SUCCESS;
	}

}