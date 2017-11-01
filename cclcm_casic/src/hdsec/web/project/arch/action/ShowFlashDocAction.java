package hdsec.web.project.arch.action;

import com.jacob.activeX.*;
import com.jacob.com.*;

public class ShowFlashDocAction extends ArchBaseAction {

	private static final long serialVersionUID = 1L;
	private String file_address = "";

	public String getFile_address() {
		return file_address;
	}

	public void setFile_address(String file_address) {
		this.file_address = file_address;
	}

	public void publishFlash(String file_address) {
		try {
			// ComThread.InitSTA();

			// Create Server object
			ActiveXComponent p2f = new ActiveXComponent("Print2Flash3.Server");

			// Setup interface and protection options
			ActiveXComponent defProfile = new ActiveXComponent(p2f.getProperty(
					"DefaultProfile").toDispatch());
			defProfile.setProperty("InterfaceOptions", P2FConst.INTLOGO
					| P2FConst.INTZOOMSLIDER | P2FConst.INTPREVPAGE
					| P2FConst.INTGOTOPAGE | P2FConst.INTNEXTPAGE);
			defProfile.setProperty("ProtectionOptions", P2FConst.PROTDISPRINT
					| P2FConst.PROTENAPI);

			// Convert document
			p2f.invoke("ConvertFile", new String(
					"C:\\Users\\Dell\\Desktop\\领导交办.doc"));
			System.out.println("Conversion completed successfully");
		} catch (Exception e) {
			System.out.println("An error occurred at conversion: "
					+ e.toString());
		} finally {
			ComThread.Release();
		}

	}

	@Override
	public String executeFunction() throws Exception {

		publishFlash(file_address);

		return SUCCESS;

	}

}
