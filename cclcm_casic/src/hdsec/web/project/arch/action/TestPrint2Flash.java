package hdsec.web.project.arch.action;

import java.io.*;
import com.jacob.activeX.*;
import com.jacob.com.*;

public class TestPrint2Flash {

	public static void main(String[] args) throws java.io.IOException {

		if (true) {
			try {
				ComThread.InitSTA();

				// Create Server
				// object{33DF3495-179B-4E1E-9833-7927C26812A1}{33DF3495-179B-4E1E-9833-7927C26812A1}
				ActiveXComponent p2f = new ActiveXComponent(
						"Print2Flash3.Server");
				// ActiveXComponent p2f = new
				// ActiveXComponent("Print2Flash3.PrintingPreferences");
				ActiveXComponent defProfile = new ActiveXComponent(p2f
						.getProperty("DefaultProfile").toDispatch());
				defProfile.setProperty("InterfaceOptions", P2FConst.INTLOGO
						| P2FConst.INTZOOMSLIDER | P2FConst.INTPREVPAGE
						| P2FConst.INTGOTOPAGE | P2FConst.INTNEXTPAGE);
				defProfile.setProperty("ProtectionOptions",
						P2FConst.PROTDISPRINT | P2FConst.PROTENAPI);

				// Convert document
				p2f.invoke("ConvertFile", new String(args[0]));
				System.out.println("Conversion completed successfully");
			} catch (Exception e) {
				ComFailException ex = (ComFailException) e;

				System.out.println("An error occurred at conversion: "
						+ e.toString());
				System.out.println("An error occurred at conversion: "
						+ ex.getMessage());
			} finally {
				ComThread.Release();
			}
		} else
		System.out.println("Please provide a document file name as a parameter");
		System.out.println("Press Enter to exit...");
		// System.in.read();
		}
	}

