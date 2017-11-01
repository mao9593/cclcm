package hdsec.web.project.common.bm;

import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.bm.model.BmRealUser;
import hdsec.web.project.computer.model.EntityBook;
import hdsec.web.project.computer.model.EntityComputer;
import hdsec.web.project.computer.model.EntityHardDisk;
import hdsec.web.project.computer.model.EntityInfoDevice;
import hdsec.web.project.computer.model.InfoType;
import hdsec.web.project.computer.service.ComputerService;
import hdsec.web.project.ledger.action.CommonExportAction;
import hdsec.web.project.passport.model.EntityPassport;
import hdsec.web.project.passport.service.PassportService;
import hdsec.web.project.securityuser.model.ResignEvent;
import hdsec.web.project.securityuser.service.SecurityUserService;
import hdsec.web.project.user.model.SecUser;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * BM业务导出通用
 * 
 * @author gaoximin 2015-7-4
 */
public class BMCommonExportAction extends CommonExportAction {

	private static final long serialVersionUID = 1L;

	@Resource
	protected ComputerService computerService;
	@Resource
	protected SecurityUserService securityUserService;
	@Resource
	protected PassportService passportService;

	private HSSFWorkbook wb = new HSSFWorkbook();
	private final String COMPUTER = "computer";
	List<?> ledgers;

	public void exportFileBM(OutputStream os, Map<String, Object> map, String sheetName, String[] titles, String type)
			throws Exception {
		BufferedOutputStream bos = new BufferedOutputStream(os);
		cellstyle = wb.createCellStyle();
		cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
		int total_size = 0;

		if (COMPUTER.equals(type)) {
			if (null != computerService.getAllComputerList(map)) {
				total_size = computerService.getAllComputerList(map).size();
			}
		} else if (type.equals("device")) {
			if (null != computerService.getInfoDeviceList(map)) {
				total_size = computerService.getInfoDeviceList(map).size();
			}
		} else if (type.equals("book")) {
			if (null != computerService.getBookList(map)) {
				total_size = computerService.getBookList(map).size();
			}
		} else if (type.equals("secuser")) {
			if (null != securityUserService.getAllBmSecUserList(map)) {
				total_size = securityUserService.getBmSecUserSize(map);
			}
		} else if (type.equals("passport")) {
			if (null != passportService.getAllPassportList(map)) {
				total_size = passportService.getAllPassportList(map).size();
			}
		} else if (type.equals("harddisk")) {
			if (null != passportService.getAllPassportList(map)) {
				total_size = passportService.getAllPassportList(map).size();
			}
		}

		int count = total_size / CCLCMConstants.SHEET_SIZE + 1;
		int begin = 0;
		for (int i = 0; i < count; i++) {
			begin = i * CCLCMConstants.SHEET_SIZE;

			if (COMPUTER.equals(type)) {
				ledgers = computerService.getAllComputerList(map);
			} else if (type.equals("device")) {
				ledgers = computerService.getInfoDeviceList(map);
			} else if (type.equals("book")) {
				ledgers = computerService.getBookList(map);
			} else if (type.equals("secuser")) {
				ledgers = securityUserService.getAllBmSecUserList(map);
			} else if (type.equals("passport")) {
				ledgers = passportService.getAllPassportList(map);
			} else if (type.equals("harddisk")) {
				ledgers = computerService.getAllHardDiskList(map);
			}

			if (count - 1 != i) {
				insertIntoOneSheetBM(i, i * CCLCMConstants.SHEET_SIZE - begin, (i + 1) * CCLCMConstants.SHEET_SIZE - 1
						- begin, sheetName, titles, type);
			} else {
				insertIntoOneSheetBM(i, i * CCLCMConstants.SHEET_SIZE - begin, total_size - 1 - begin, sheetName,
						titles, type);
			}
		}

		wb.write(bos);
		if (null != bos) {
			bos.flush();
			bos.close();
		}
		if (null != os) {
			os.close();
		}
	}

	public void insertIntoOneSheetBM(int pageIndex, int from, int to, String sheetName, String[] titles, String type)
			throws IOException {
		HSSFSheet sh = wb.createSheet(sheetName + pageIndex);
		addTitile(sh, titles);
		int i = from;
		String paper_total = "";
		String cd_total = "";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			for (i = from; i <= to; i++) {
				if (COMPUTER.equals(type)) {
					EntityComputer computer = (EntityComputer) ledgers.get(i);
					insertComputerRow(sh, computer, i - from + 1);
				} else if (type.equals("device")) {
					EntityInfoDevice entity = (EntityInfoDevice) ledgers.get(i);
					insertDeviceRow(sh, entity, i - from + 1);
				} else if (type.equals("book")) {
					EntityBook entity = (EntityBook) ledgers.get(i);
					insertBookRow(sh, entity, i - from + 1);
				} else if (type.equals("secuser")) {
					List<ResignEvent> resign_event = null;
					ResignEvent revent = null;
					SecUser user = (SecUser) ledgers.get(i);
					map.put("real_user_id", user.getUser_iidd());
					map.put("info_type", 2);
					BmRealUser userinfo = securityUserService.getUserInfoByEventCode(map);

					map.put("user_id", user.getUser_iidd());
					paper_total = securityUserService.getUserEntityPaperTotal(map);
					cd_total = securityUserService.getUserEntityCdTotal(map);

					// 查询离职、退休人员的脱密期时间
					if (userinfo != null) {
						if (userinfo.getUser_statue() == 2 || userinfo.getUser_statue() == 3) {
							Map<String, Object> remap = new HashMap<String, Object>();
							remap.put("resign_user_iidd", userinfo.getReal_user_id());
							if (userinfo.getUser_statue() == 2) {
								remap.put("reason", "1");
							} else {
								remap.put("reason", "2");
							}
							resign_event = securityUserService.getUResignEventList(remap);
							if (resign_event.size() != 0 && resign_event != null) {
								revent = resign_event.get(0);
							}
						}
					}

					insertUserRow(sh, user, userinfo, i - from + 1, paper_total, cd_total, revent);
				} else if (type.equals("passport")) {
					EntityPassport entity = (EntityPassport) ledgers.get(i);
					insertPassportRow(sh, entity, i - from + 1);
				} else if (type.equals("harddisk")) {
					EntityHardDisk entity = (EntityHardDisk) ledgers.get(i);
					insertHardDiskRow(sh, entity, i - from + 1);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String changeNullOrEmpty(String str) {
		if (null == str || str.length() == 0) {
			return " ";
		} else {
			return str;
		}
	}

	/*
	 * private int changeNullOrEmpty(int i) { if (0 == i) { return 0; } else { return i; } }
	 */

	private void insertComputerRow(HSSFSheet sh, EntityComputer computer, int rownum) {
		int index = 0;
		HSSFRow row = sh.createRow(rownum);
		cteateCell(row, index++, rownum + "");
		cteateCell(row, index++, changeNullOrEmpty(computer.getMed_type_name()));
		cteateCell(row, index++, changeNullOrEmpty(computer.getComputer_name()));
		// cteateCell(row, index++, changeNullOrEmpty(computer.getComputer_model()));
		cteateCell(row, index++, changeNullOrEmpty(computer.getComputer_code()));
		cteateCell(row, index++, changeNullOrEmpty(computer.getOldconf_code()));
		cteateCell(row, index++, changeNullOrEmpty(computer.getConf_code()));
		cteateCell(row, index++, changeNullOrEmpty(computer.getSeclv_name()));
		cteateCell(row, index++, changeNullOrEmpty(computer.getDuty_user_name()));
		cteateCell(row, index++, changeNullOrEmpty(computer.getDuty_dept_name()));
		cteateCell(row, index++, changeNullOrEmpty(computer.getDuty_entp_name()));
		cteateCell(row, index++, changeNullOrEmpty(computer.getInternet_type_name()));
		cteateCell(row, index++, changeNullOrEmpty(computer.getComputer_os()));
		cteateCell(row, index++, changeNullOrEmpty(computer.getInstall_time_str()));
		cteateCell(row, index++, changeNullOrEmpty(computer.getHdisk_no()));
		cteateCell(row, index++, changeNullOrEmpty(computer.getKey_code()));
		cteateCell(row, index++, changeNullOrEmpty(computer.getComputer_ip()));
		cteateCell(row, index++, changeNullOrEmpty(computer.getMark_code()));
		cteateCell(row, index++, changeNullOrEmpty(computer.getComputer_mac()));
		cteateCell(row, index++, changeNullOrEmpty(computer.getOutput_point()));
		cteateCell(row, index++, changeNullOrEmpty(computer.getStorage_area()));
		cteateCell(row, index++, changeNullOrEmpty(computer.getStorage_location()));
		cteateCell(row, index++, changeNullOrEmpty(computer.getComputer_status_name()));
		cteateCell(row, index++, changeNullOrEmpty(computer.getVlan_num()));
		cteateCell(row, index++, changeNullOrEmpty(computer.getComputer_gateway()));
		cteateCell(row, index++, changeNullOrEmpty(computer.getSwitch_num()));
		cteateCell(row, index++, changeNullOrEmpty(computer.getSwitch_point()));
		// cteateCell(row, index++, changeNullOrEmpty(computer.getComputer_no()));
		cteateCell(row, index++, changeNullOrEmpty(computer.getEnter_time_str()));
		// cteateCell(row, index++, changeNullOrEmpty(computer.getComputer_application()));

		if (!computer.getSoftware_type().equals("")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("computer_barcode", computer.getComputer_barcode());
			InfoType medname = null;
			String names = "";
			String[] med = computer.getSoftware_type().split(",");
			for (int i = 0; i < med.length; i++) {
				map.put("info_id", med[i].trim());
				medname = computerService.getInfoTypeByID(map);
				names += medname.getInfo_type() + ",";
			}
			cteateCell(row, index++, changeNullOrEmpty(names));
		}
		cteateCell(row, index++, changeNullOrEmpty(computer.getSumm()));
	}

	// 信息设备台账导出
	private void insertDeviceRow(HSSFSheet sh, EntityInfoDevice entity, int rownum) {
		int index = 0;
		HSSFRow row = sh.createRow(rownum);
		cteateCell(row, index++, rownum + "");
		cteateCell(row, index++, changeNullOrEmpty(entity.getDevice_barcode()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getDevice_type_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getInfo_type()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getDuty_user_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getDuty_dept_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getSeclv_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getCompany()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getOldconf_code()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getConf_code()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getDevice_series()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getDevice_usage()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getBrand_type()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getDevice_version()));
		// cteateCell(row, index++, changeNullOrEmpty(entity.getSerial_num()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getPurchase_time_str()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getUse_time_str()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getDestroy_time_str()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getLocation()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getCert_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getCert_num()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getMemory()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getDevice_statues_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getDetail()));
	}

	// 笔记本导出
	private void insertBookRow(HSSFSheet sh, EntityBook entity, int rownum) {
		int index = 0;
		HSSFRow row = sh.createRow(rownum);
		cteateCell(row, index++, rownum + "");
		cteateCell(row, index++, changeNullOrEmpty(entity.getBook_code()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getOldconf_code()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getConf_code()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getDuty_entp()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getDuty_dept_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getDuty_user_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getSeclv_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getBook_os()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getHdisk_no()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getBook_mac()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getInternet_type()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getBook_model()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getStorage_location()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getBook_status_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getOutsideinfo()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getOutput_point()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getStorage_secinfo()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getDetail()));
	}

	// 人员信息台账导出
	private void insertUserRow(HSSFSheet sh, SecUser entity, BmRealUser userinfo, int rownum, String paper_total,
			String cd_total, ResignEvent revent) {
		int index = 0;
		HSSFRow row = sh.createRow(rownum);
		cteateCell(row, index++, rownum + "");
		cteateCell(row, index++, changeNullOrEmpty(entity.getDept_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getUser_name()));
		if (userinfo != null) {
			cteateCell(row, index++, changeNullOrEmpty(userinfo.getBase_sex_name()));
		} else {
			cteateCell(row, index++, changeNullOrEmpty(entity.getBase_sex()));
		}

		cteateCell(row, index++, changeNullOrEmpty(entity.getBase_country()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getBase_nation()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getBase_nativeplace()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getBase_birthday_str()));
		if (userinfo != null) {
			cteateCell(row, index++, changeNullOrEmpty(userinfo.getBase_politics_name()));
			cteateCell(row, index++, changeNullOrEmpty(userinfo.getEdu_education_name()));
		} else {
			cteateCell(row, index++, changeNullOrEmpty(entity.getBase_politics()));
			cteateCell(row, index++, changeNullOrEmpty(entity.getEdu_education()));
		}

		cteateCell(row, index++, changeNullOrEmpty(entity.getIdCard()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getCom_email()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getCom_telephone()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getCom_residency()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getCom_address()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getJob_techpost()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getJob_techtitle()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getJob_insectime_str()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getSecurity_name()));
		if (userinfo != null) {
			cteateCell(row, index++, changeNullOrEmpty(userinfo.getMarital_status_str()));
			cteateCell(row, index++, changeNullOrEmpty(userinfo.getResident_card()));
			cteateCell(row, index++, changeNullOrEmpty(userinfo.getBmpost_name()));
			cteateCell(row, index++, changeNullOrEmpty(userinfo.getOversea_name()));
			cteateCell(row, index++, changeNullOrEmpty(userinfo.getFamliy_oversea_name()));
			cteateCell(row, index++, changeNullOrEmpty(userinfo.getUser_statue_name()));
			// 人员脱密期起止时间,2016.3.31
			if (revent != null) {
				cteateCell(row, index++,
						changeNullOrEmpty(revent.getStart_time()) + "至" + changeNullOrEmpty(revent.getEnd_time()));
			} else {
				cteateCell(row, index++, "");
			}
		}
		cteateCell(row, index++, changeNullOrEmpty(paper_total));
		cteateCell(row, index++, changeNullOrEmpty(cd_total));
	}

	// 护照导出
	private void insertPassportRow(HSSFSheet sh, EntityPassport entity, int rownum) {
		int index = 0;
		HSSFRow row = sh.createRow(rownum);
		cteateCell(row, index++, rownum + "");
		cteateCell(row, index++, changeNullOrEmpty(entity.getPassport_num()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getPassport_type_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getPassport_status_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getDuty_user_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getDuty_dept_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getIssuing_date()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getIssuing_authority()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getEnding_date()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getSumm()));

	}

	private void insertHardDiskRow(HSSFSheet sh, EntityHardDisk entity, int rownum) {
		int index = 0;
		HSSFRow row = sh.createRow(rownum);
		cteateCell(row, index++, rownum + "");
		cteateCell(row, index++, changeNullOrEmpty(entity.getComputer_type()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getComputer_barcode()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getConf_code()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getDuty_user_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getDuty_dept_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getDuty_entp_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getHdisk_no()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getRetrieve_user_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getRetrieve_time_str()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getApprove_url()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getHdisk_status_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getSumm()));

	}

}