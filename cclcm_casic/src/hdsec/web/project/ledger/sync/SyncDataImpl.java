package hdsec.web.project.ledger.sync;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SyncDataImpl {

	public String createTmpTable() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String message = "";
		try {
			conn = Connections.getCclcmConnection();
			String createTmpTable = "if exists (select 1 from  sysobjects "
					+ "where  id = object_id('ENTITY_PAPER_TMP')and type = 'U')" + "drop table ENTITY_PAPER_TMP; "
					+ "CREATE TABLE [dbo].[ENTITY_PAPER_TMP]("
					+ "[PAPER_BARCODE] [nvarchar](64) COLLATE Chinese_PRC_CI_AS NULL,"
					+ "[EVENT_CODE] [nvarchar](64) COLLATE Chinese_PRC_CI_AS NULL,"
					+ "[USER_IIDD] [nvarchar](64) COLLATE Chinese_PRC_CI_AS NULL,"
					+ "[USER_NAME] [nvarchar](64) COLLATE Chinese_PRC_CI_AS NULL,"
					+ "[DEPT_ID] [nvarchar](100) COLLATE Chinese_PRC_CI_AS NULL,"
					+ "[DEPT_NAME] [nvarchar](64) COLLATE Chinese_PRC_CI_AS NULL,"
					+ "[DUTY_USER_IIDD] [nvarchar](64) COLLATE Chinese_PRC_CI_AS NULL,"
					+ "[DUTY_USER_NAME] [nvarchar](64) COLLATE Chinese_PRC_CI_AS NULL,"
					+ "[DUTY_DEPT_ID] [nvarchar](64) COLLATE Chinese_PRC_CI_AS NULL,"
					+ "[DUTY_DEPT_NAME] [nvarchar](64) COLLATE Chinese_PRC_CI_AS NULL," + "[SECLV_CODE] [int] NULL,"
					+ "[SECLV_NAME] [nvarchar](128) COLLATE Chinese_PRC_CI_AS NULL," + "[PRINT_TIME] [datetime] NULL,"
					+ "[IS_REPRINT] [nvarchar](10) COLLATE Chinese_PRC_CI_AS NULL DEFAULT ('0'),"
					+ "[PRINT_RESULT] [int] NULL DEFAULT ((1)),"
					+ "[PRINT_RESULT_DETAIL] [nvarchar](128) COLLATE Chinese_PRC_CI_AS NULL,"
					+ "[FILE_TITLE] [nvarchar](1024) COLLATE Chinese_PRC_CI_AS NULL,"
					+ "[PROJECT_CODE] [nvarchar](1024) COLLATE Chinese_PRC_CI_AS NULL,"
					+ "[FILEID] [nvarchar](128) COLLATE Chinese_PRC_CI_AS NULL," + "[PAGE_COUNT] [int] NULL,"
					+ "[PAGE_SIZE] [nvarchar](1024) COLLATE Chinese_PRC_CI_AS NULL," + "[COLOR] [int] NULL,"
					+ "[PRINT_DIRECT] [int] NULL," + "[PRINT_DOUBLE] [int] NULL,"
					+ "[PAPER_STATE] [int] NOT NULL DEFAULT ((0)),"
					+ "[CREATE_TYPE] [nvarchar](64) COLLATE Chinese_PRC_CI_AS NOT NULL DEFAULT ('PRINT'),"
					+ "[SCOPE] [nvarchar](64) COLLATE Chinese_PRC_CI_AS NOT NULL DEFAULT ('PERSON'),"
					+ "[SCOPE_DEPT_ID] [nvarchar](100) COLLATE Chinese_PRC_CI_AS NULL,"
					+ "[SCOPE_DEPT_NAME] [nvarchar](100) COLLATE Chinese_PRC_CI_AS NULL,"
					+ "[PRINTER_CODE] [nvarchar](64) COLLATE Chinese_PRC_CI_AS NULL,"
					+ "[PRINTER_NAME] [nvarchar](1024) COLLATE Chinese_PRC_CI_AS NULL,"
					+ "[RETRIEVE_TIME] [datetime] NULL," + "[RETRIEVE_TYPE] [int] NULL,"
					+ "[RETRIEVE_USER_IIDD] [nvarchar](64) COLLATE Chinese_PRC_CI_AS NULL,"
					+ "[RETRIEVE_BOX_CODE] [nvarchar](64) COLLATE Chinese_PRC_CI_AS NULL,"
					+ "[DESTROY_TIME] [datetime] NULL,"
					+ "[DESTROY_USER_IIDD] [nvarchar](64) COLLATE Chinese_PRC_CI_AS NULL,"
					+ "[SEND_NUM] [nvarchar](64) COLLATE Chinese_PRC_CI_AS NULL,"
					+ "[JOB_CODE] [nvarchar](64) COLLATE Chinese_PRC_CI_AS NULL,"
					+ "[fail_remark] [nvarchar](1024) COLLATE Chinese_PRC_CI_AS NULL,"
					+ "[expire_time] [datetime] NULL)";
			ps = conn.prepareStatement(createTmpTable);
			System.out.println(createTmpTable);
			ps.execute();
			message = "创建临时表成功…………";
			return message;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			message = "创建临时表失败，请重新尝试！！！";
			return message;
		} finally {
			if (null != ps) {
				ps.close();
			}
			if (null != conn) {
				conn.close();
			}
		}
	}

	public List<SyncDataModel> getSpData(String sqlUrl, String sqlUserName, String sqlPassword, String retrieve,
			String seclv) throws Exception {
		List<SyncDataModel> spList = new ArrayList<SyncDataModel>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = Connections.getSpConnection(sqlUrl, sqlUserName, sqlPassword);
			// todo get all record and change them to data model
			String sql = "select p_file.filebarcode '条码',tp.name '密级',u.account '用户账号',duty.account '责任人账号',"
					+ "u.username '用户姓名',duty.username '责任人姓名',u.groupname '用户部门',duty.groupname '责任人部门',"
					+ "event.dlfilename '文件名称',p_file.pagecount ,p_file.printername,p_file.printtime,p_file.printresult,"
					+ "isaddprint,retrive.account retriveuser,p_file.retrivertime,'PRINT' create_type,'PERSON' scope,event.pagesize '纸张类型',"
					+ "event.color '颜色',event.printdouble '单双面',event.printdirect '横纵向',p_file.handleresult "
					+ "from t_filetype tp,t_printfiles p_file "
					+ "left join (select * from t_printevent) event on p_file.printfileno = event.fileno "
					+ "left join (select * from t_user) retrive on retrive.id = p_file.retriveuserid "
					+ "left join (select * from t_user) handle on handle.id = p_file.handleuserid "
					+ "left join (select * from t_user) u on u.id = event.userid "
					+ "left join (select * from t_user) duty on duty.username = p_file.dutyman "
					+ "where event.filetype is not null  and event.filetype = tp.id and u.account is not null and syncflag='N'";
			if (retrieve.equals("nohandle")) {
				sql += "and (handleresult is null or handleresult=1) ";
			}
			if (seclv.equals("1")) {
				sql += "and (tp.name='机密') ";
			} else if (seclv.equals("2")) {
				sql += "and (tp.name='机密' or tp.name='秘密') ";
			} else if (seclv.equals("3")) {
				sql += "and (tp.name != '内部' and tp.name != '非密') ";
			} else if (seclv.equals("4")) {
				sql += "and tp.name != '非密' ";
			}
			sql += "order by tp.name,u.account desc";
			ps = conn.prepareStatement(sql);
			System.out.println(sql);
			rs = ps.executeQuery();
			int mm = 19;
			while (rs.next()) {
				SyncDataModel model = new SyncDataModel();
				model.setFileBarcode(rs.getString("条码"));
				model.setSeclvName(rs.getString("密级"));
				model.setUserId(rs.getString("用户账号"));
				model.setDutyUserId(rs.getString("责任人账号"));
				model.setUserName(rs.getString("用户姓名"));
				model.setDutyUserName(rs.getString("责任人姓名"));
				model.setDeptName(rs.getString("用户部门"));
				model.setDutyDeptName(rs.getString("责任人部门"));
				model.setDlFileName(rs.getString("文件名称"));
				model.setPageCount(rs.getString("pagecount"));
				model.setPrinterName(rs.getString("printername"));
				if (rs.getString("printtime") != null && rs.getString("printtime").toString() != "") {
					mm = rs.getString("printtime").indexOf(".");
					model.setPrintTime(rs.getString("printtime").substring(0, mm));
				} else {
					model.setPrintTime(null);
				}

				// System.out.println("printtime:" + rs.getString("printtime").substring(0, mm));
				// Date sDate = new Date();
				// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				// sDate = sdf.parse(rs.getString("printtime").substring(0, mm));

				model.setPrintResult(rs.getString("printresult"));
				model.setRetriveUserId(rs.getString("retriveuser"));
				if (rs.getString("retrivertime") != null && rs.getString("retrivertime").toString() != "") {
					mm = rs.getString("retrivertime").indexOf(".");
					// System.out.println("retrivertime:" + rs.getString("retrivertime").substring(0, mm));
					model.setRetriverTime(rs.getString("retrivertime").substring(0, mm));
				} else {
					model.setRetriverTime(null);
				}
				model.setCreatType(rs.getString("create_type"));
				model.setScope(rs.getString("scope"));
				model.setIsaddprint(rs.getString("isaddprint"));
				model.setPage_size(rs.getString("纸张类型"));
				model.setColor(rs.getString("颜色"));
				model.setIs_double(rs.getString("单双面"));
				model.setPrint_direct(rs.getString("横纵向"));
				model.setPaper_state(rs.getString("handleresult"));
				spList.add(model);
			}
			System.out.println("需要同步数据总条数 ：" + spList.size());
			return spList;
		} finally {
			if (null != rs) {
				rs.close();
			}
			if (null != ps) {
				ps.close();
			}
			if (null != conn) {
				conn.close();
			}
		}
	}

	public String updateFlagY(String sqlUrl, String sqlUserName, String sqlPassword, String retrieve, String seclv)
			throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String returnUpdateFlag = "";
		try {
			conn = Connections.getSpConnection(sqlUrl, sqlUserName, sqlPassword);
			// todo get all record and change them to data model
			String updateFlag = "update p_file set p_file.syncflag = 'Y' "
					+ "from t_filetype tp,t_printfiles p_file "
					+ "left join (select * from t_printevent) event on p_file.printfileno = event.fileno "
					+ "left join (select * from t_user) retrive on retrive.id = p_file.retriveuserid "
					+ "left join (select * from t_user) handle on handle.id = p_file.handleuserid "
					+ "left join (select * from t_user) u on u.id = event.userid "
					+ "left join (select * from t_user) duty on duty.username = p_file.dutyman "
					+ "where event.filetype is not null  and event.filetype = tp.id and u.account is not null and syncflag='N'";
			if (retrieve.equals("nohandle")) {
				updateFlag += "and (handleresult is null or handleresult=1) ";
			}
			if (seclv.equals("1")) {
				updateFlag += "and (tp.name='机密') ";
			} else if (seclv.equals("2")) {
				updateFlag += "and (tp.name='机密' or tp.name='秘密') ";
			} else if (seclv.equals("3")) {
				updateFlag += "and (tp.name != '内部' and tp.name != '非密') ";
			} else if (seclv.equals("4")) {
				updateFlag += "and tp.name != '非密' ";
			}
			ps = conn.prepareStatement(updateFlag);
			System.out.println(updateFlag);
			ps.execute();
			returnUpdateFlag = "旧系统已同步到临时表中的台账，标志位更新完成……";
			return returnUpdateFlag;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			returnUpdateFlag = "对于旧系统中已同步到临时表中的台账，更新标志位失败！！！";
			return returnUpdateFlag;
		} finally {
			if (null != ps) {
				ps.close();
			}
			if (null != conn) {
				conn.close();
			}
		}

	}

	public String insertTmpTableDate(String sqlUrl, String sqlUserName, String sqlPassword, String retrieve,
			String seclv) throws Exception {
		List<SyncDataModel> spList = getSpData(sqlUrl, sqlUserName, sqlPassword, retrieve, seclv);
		Connection conn = null;
		PreparedStatement ps = null;
		String log = "";
		int i = 0;
		try {
			conn = Connections.getCclcmConnection();
			for (SyncDataModel oneModel : spList) {
				// todo insert all data into tmp table
				String insertSQL = "insert into entity_paper_tmp(paper_barcode,seclv_name,user_iidd,duty_user_iidd,user_name,"
						+ "duty_user_name,dept_name,duty_dept_name,file_title,page_count,printer_name,print_time,print_result,"
						+ "is_reprint,retrieve_user_iidd,retrieve_time,create_type,scope,page_size,color,print_double,print_direct,paper_state) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				// System.out.println(insertSQL);
				ps = conn.prepareStatement(insertSQL);
				ps.setString(1, oneModel.getFileBarcode());
				ps.setString(2, oneModel.getSeclvName());
				ps.setString(3, oneModel.getUserId());
				ps.setString(4, oneModel.getDutyUserId());
				ps.setString(5, oneModel.getUserName());
				ps.setString(6, oneModel.getDutyUserName());
				ps.setString(7, oneModel.getDeptName());
				ps.setString(8, oneModel.getDutyDeptName());
				ps.setString(9, oneModel.getDlFileName());
				ps.setString(10, oneModel.getPageCount());
				ps.setString(11, oneModel.getPrinterName());
				ps.setString(12, oneModel.getPrintTime());
				ps.setString(13, oneModel.getPrintResult());
				ps.setString(14, oneModel.getIsaddprint());
				ps.setString(15, oneModel.getRetriveUserId());
				ps.setString(16, oneModel.getRetriverTime());
				ps.setString(17, oneModel.getCreatType());
				ps.setString(18, oneModel.getScope());
				if ("66".equals(oneModel.getPage_size())) {
					ps.setString(19, "A2");
				} else if ("8".equals(oneModel.getPage_size())) {
					ps.setString(19, "A3");
				} else if ("9".equals(oneModel.getPage_size())) {
					ps.setString(19, "A4");
				} else if ("11".equals(oneModel.getPage_size())) {
					ps.setString(19, "A5");
				} else if ("12".equals(oneModel.getPage_size())) {
					ps.setString(19, "B4");
				} else if ("13".equals(oneModel.getPage_size())) {
					ps.setString(19, "B5");
				} else {
					ps.setString(19, "CUSTOM");
				}
				ps.setString(20, oneModel.getColor());
				ps.setString(21, oneModel.getIs_double());
				ps.setString(22, oneModel.getPrint_direct());
				// 旧系统：1留用，2归档，4报送，8销毁，9其他，16回收，32处理，64外发，空为未提起闭环申请
				// 新系统：0留用，1已回收，2已外发，3已归档，4已销毁，5流转中，6借阅中，7申请销毁，8申请外发，9申请归档，10待交接，其他为未知
				// 同步时，旧系统中9其他、16回收、32处理同步到新系统中均为4已销毁状态
				if ("1".equals(oneModel.getPaper_state())) {
					ps.setString(23, "0");
				} else if ("2".equals(oneModel.getPaper_state())) {
					ps.setString(23, "3");
				} else if ("4".equals(oneModel.getPaper_state())) {
					ps.setString(23, "2");
				} else if ("8".equals(oneModel.getPaper_state())) {
					ps.setString(23, "4");
				} else if ("9".equals(oneModel.getPaper_state())) {
					ps.setString(23, "4");
				} else if ("16".equals(oneModel.getPaper_state())) {
					ps.setString(23, "4");
				} else if ("32".equals(oneModel.getPaper_state())) {
					ps.setString(23, "4");
				} else if ("64".equals(oneModel.getPaper_state())) {
					ps.setString(23, "2");
				} else {
					ps.setString(23, "0");
				}
				ps.execute();
				i++;
			}

			log = "（1）插入临时表" + i + "条台账…………";
			System.out.println(log);
			// 置旧系统中同步到临时表中的台账标志为Y，syncflag='Y'
			String returnUpdateFlag = updateFlagY(sqlUrl, sqlUserName, sqlPassword, retrieve, seclv);
			log += returnUpdateFlag;
			return log;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log = "（1）插入临时表台账失败，请重新尝试！！！";
			return log;
		} finally {
			if (null != ps) {
				ps.close();
			}
			if (null != conn) {
				conn.close();
			}
		}
	}

	public String updateTmpTableSeclvCodeAndDeptId() throws Exception {
		// update seclvcode and deptId
		Connection conn = null;
		PreparedStatement ps = null;
		String log = "";
		try {
			conn = Connections.getCclcmConnection();
			String updateSeclvCode = "update t set t.seclv_code = u.seclv_code from entity_paper_tmp t left join sec_user_seclv u on  t.seclv_name = u.seclv_name"
					+ " where u.is_sealed = 'N'";
			ps = conn.prepareStatement(updateSeclvCode);
			System.out.println(updateSeclvCode);
			ps.execute();
			/*
			 * String updateDeptId = "update t set t.dept_id = d.dept_id from entity_paper_tmp t,sec_dept d" +
			 * " where d.is_sealed = 'N' and t.dept_name = d.dept_name";
			 */
			String updateDeptId = "update t set t.user_name = u.user_name, t.dept_id = d.dept_id, t.dept_name = d.dept_name,"
					+ " t.duty_user_name = dutyu.user_name, t.duty_dept_id = dutyd.dept_id, t.duty_dept_name = dutyd.dept_name"
					+ " from entity_paper_tmp t"
					+ " left join sec_user u on t.user_iidd = u.user_iidd"
					+ " left join sec_dept d on u.dept_id = d.dept_id"
					+ " left join sec_user dutyu on t.duty_user_iidd = dutyu.user_iidd"
					+ " left join sec_dept dutyd on dutyu.dept_id = dutyd.dept_id";
			ps = conn.prepareStatement(updateDeptId);
			System.out.println(updateDeptId);
			ps.execute();
			/*
			 * String updateDutyDeptId = "update t set t.duty_dept_id = d.dept_id from entity_paper_tmp t,sec_dept d" +
			 * " where d.is_sealed = 'N' and t.duty_dept_name = d.dept_name"; ps =
			 * conn.prepareStatement(updateDutyDeptId); System.out.println(updateDutyDeptId); ps.execute();
			 */
			log = "（2）更新临时表密级、部门信息成功…………";
			return log;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log = "（2）更新临时表密级、部门信息失败，请重新尝试！！！";
			return log;
		} finally {
			if (null != ps) {
				ps.close();
			}
			if (null != conn) {
				conn.close();
			}
		}
	}

	public String inertDataIntoEntityTableFromTmpTable() throws Exception {
		// remove duplicate recode, insert into entity table
		Connection conn = null;
		PreparedStatement ps = null;
		String log = "";
		try {
			conn = Connections.getCclcmConnection();
			String insertEntity = "INSERT INTO ENTITY_PAPER"
					+ "([PAPER_BARCODE],[EVENT_CODE],[USER_IIDD],[USER_NAME],[DEPT_ID],[DEPT_NAME],[DUTY_USER_IIDD],[DUTY_USER_NAME],"
					+ "[DUTY_DEPT_ID],[DUTY_DEPT_NAME],[SECLV_CODE],[PRINT_TIME],[IS_REPRINT],[PRINT_RESULT],[PRINT_RESULT_DETAIL],"
					+ "[FILE_TITLE],[PROJECT_CODE],[FILEID],[PAGE_COUNT],[PAGE_SIZE],[COLOR],[PRINT_DIRECT],[PRINT_DOUBLE],[PAPER_STATE],"
					+ "[CREATE_TYPE],[SCOPE],[SCOPE_DEPT_ID],[SCOPE_DEPT_NAME],[PRINTER_CODE],[PRINTER_NAME],[RETRIEVE_TIME],[RETRIEVE_TYPE],"
					+ "[RETRIEVE_USER_IIDD],[RETRIEVE_BOX_CODE],[DESTROY_TIME],[DESTROY_USER_IIDD],[SEND_NUM],[JOB_CODE]) "
					+ "select [PAPER_BARCODE],[EVENT_CODE],[USER_IIDD],[USER_NAME],[DEPT_ID],[DEPT_NAME],[DUTY_USER_IIDD],[DUTY_USER_NAME],"
					+ "[DUTY_DEPT_ID],[DUTY_DEPT_NAME],[SECLV_CODE],[PRINT_TIME],[IS_REPRINT],[PRINT_RESULT],[PRINT_RESULT_DETAIL],"
					+ "[FILE_TITLE],[PROJECT_CODE],[FILEID],[PAGE_COUNT],[PAGE_SIZE],[COLOR],[PRINT_DIRECT],[PRINT_DOUBLE],[PAPER_STATE],"
					+ "[CREATE_TYPE],[SCOPE],[SCOPE_DEPT_ID],[SCOPE_DEPT_NAME],[PRINTER_CODE],[PRINTER_NAME],[RETRIEVE_TIME],[RETRIEVE_TYPE],"
					+ "[RETRIEVE_USER_IIDD],[RETRIEVE_BOX_CODE],[DESTROY_TIME],[DESTROY_USER_IIDD],[SEND_NUM],[JOB_CODE] "
					+ "from (select t.*, row_number() over(partition by paper_barcode,user_name order by print_time desc) rn from entity_paper_tmp t) tmp "
					+ "where tmp.rn = 1 and not exists( select 1 from ENTITY_PAPER p where tmp.paper_barcode = p.paper_barcode)";
			ps = conn.prepareStatement(insertEntity);
			System.out.println(insertEntity);
			ps.execute();
			log = "（3）临时表台账插入台账表成功…………";
			return log;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log = "（3）临时表台账插入台账表失败，请检查后重新尝试！！！";
			return log;
		} finally {
			if (null != ps) {
				ps.close();
			}
			if (null != conn) {
				conn.close();
			}
		}
	}

	/**
	 * 旧系统增加同步标志
	 * 
	 * @param sqlUrl
	 * @param sqlUserName
	 * @param sqlPassword
	 * @return
	 * @throws Exception
	 */
	public String alterOldTable(String sqlUrl, String sqlUserName, String sqlPassword) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String readyDetailalter = "";
		try {
			conn = Connections.getSpConnection(sqlUrl, sqlUserName, sqlPassword);
			String alterTable = "if not exists (select 1 from syscolumns where id=object_id('t_printfiles') and name='syncflag')"
					+ "alter table t_printfiles add syncflag char(1) not null default 'N';";
			System.out.println(alterTable);
			ps = conn.prepareStatement(alterTable);
			ps.execute();
			readyDetailalter = "在旧系统台账表中增加同步标志字段syncflag成功…………";
			return readyDetailalter;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			readyDetailalter = "在旧系统台账表中增加同步标志字段syncflag失败，请重新尝试！！！";
			return readyDetailalter;
		} finally {
			if (null != ps) {
				ps.close();
			}
			if (null != conn) {
				conn.close();
			}
		}
	}

	/**
	 * 检测旧系统用户是否重名
	 * 
	 * @param sqlUrl
	 * @param sqlUserName
	 * @param sqlPassword
	 * @return
	 * @throws Exception
	 */
	public String testUserName(String sqlUrl, String sqlUserName, String sqlPassword) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String readyDetailTestName = "";
		ResultSet rs = null;
		try {
			conn = Connections.getSpConnection(sqlUrl, sqlUserName, sqlPassword);
			String testName = "select username,count(1) count from t_user group by username having count(1)>1 order by count desc";
			System.out.println(testName);
			ps = conn.prepareStatement(testName);
			rs = ps.executeQuery();
			while (rs.next()) {
				readyDetailTestName += "用户名[" + rs.getString("username") + "]重复数量[" + rs.getString("count") + "]个；";
			}
			if (readyDetailTestName.equals("")) {
				readyDetailTestName = "2 检测通过，系统中不存在用户重名的情况…………";
			} else {
				readyDetailTestName = "2 检测用户名重复性不通过：" + readyDetailTestName + "请修改对应用户确保不重名，且新旧系统用户登陆账号与名字统一！！！";
			}
			return readyDetailTestName;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			readyDetailTestName = "2 检测用户重名操作失败，请重新尝试！！！";
			return readyDetailTestName;
		} finally {
			if (null != ps) {
				ps.close();
			}
			if (null != conn) {
				conn.close();
			}
		}
	}

	/**
	 * 检测旧系统密级是否包含在新系统中
	 * 
	 * @param sqlUrl
	 * @param sqlUserName
	 * @param sqlPassword
	 * @return
	 * @throws Exception
	 */
	public String testSeclv(String sqlUrl, String sqlUserName, String sqlPassword) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String readyDetailTestSeclv = "";
		ResultSet rsOld = null;
		ResultSet rsNew = null;
		List<String> newSeclvList = new ArrayList<String>();
		try {
			// 查询新系统密级名称
			conn = Connections.getCclcmConnection();
			String selectNewSeclvName = "select seclv_name from sec_user_seclv";
			System.out.println(selectNewSeclvName);
			ps = conn.prepareStatement(selectNewSeclvName);
			rsNew = ps.executeQuery();
			while (rsNew.next()) {
				newSeclvList.add(rsNew.getString("seclv_name"));
			}
			System.out.println(newSeclvList);
			// 查询旧系统密级名称
			conn = Connections.getSpConnection(sqlUrl, sqlUserName, sqlPassword);
			String selectOldSeclvName = "select name from t_filetype";
			System.out.println(selectOldSeclvName);
			ps = conn.prepareStatement(selectOldSeclvName);
			rsOld = ps.executeQuery();
			// 比较新旧系统密级名称
			if (newSeclvList != null) {
				while (rsOld.next()) {
					int k = 0;
					for (String newSeclv : newSeclvList) {
						if (rsOld.getString("name").equals(newSeclv)) {
							System.out.println("old" + rsOld.getString("name"));
							System.out.println("new" + newSeclv);
							k = k + 1;
						}
					}
					if (k == 0) {
						readyDetailTestSeclv += "密级[" + rsOld.getString("name") + "]在新系统中不存在；";
					}
				}
			}
			if (readyDetailTestSeclv.equals("")) {
				readyDetailTestSeclv = "3 检测通过，新旧系统密级一致…………";
			} else {
				readyDetailTestSeclv += "请在新系统中增加对应密级，或者更新旧系统对应台账密级名称为新系统中存在的指定密级，确保密级一致性！！！";
			}
			return readyDetailTestSeclv;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			readyDetailTestSeclv = "3 检测密级一致性失败，请重新尝试！！！";
			return readyDetailTestSeclv;
		} finally {
			if (null != ps) {
				ps.close();
			}
			if (null != conn) {
				conn.close();
			}
		}
	}

	/**
	 * 检测旧系统条码是否重复
	 * 
	 * @param sqlUrl
	 * @param sqlUserName
	 * @param sqlPassword
	 * @return
	 * @throws Exception
	 */
	public String testBarcode(String sqlUrl, String sqlUserName, String sqlPassword) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String readyDetailTestBarcode = "";
		ResultSet rs = null;
		try {
			conn = Connections.getSpConnection(sqlUrl, sqlUserName, sqlPassword);
			String testBarcode = "select filebarcode,count(1) count from t_printfiles group by filebarcode having count(1)>1 order by count desc";
			System.out.println(testBarcode);
			ps = conn.prepareStatement(testBarcode);
			rs = ps.executeQuery();
			while (rs.next()) {
				readyDetailTestBarcode += "条码[" + rs.getString("filebarcode") + "]重复数量[" + rs.getString("count")
						+ "]个；";
				System.out.println(readyDetailTestBarcode);
			}
			if (readyDetailTestBarcode.equals("")) {
				readyDetailTestBarcode = "4 检测通过，系统中不存在条码重复的情况…………";
			} else {
				readyDetailTestBarcode = "4 条码查重不通过：：" + readyDetailTestBarcode + "请修改或者删除重复的条码，确保条码唯一！！！";
			}
			return readyDetailTestBarcode;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			readyDetailTestBarcode = "4 条码查重操作失败，请重新尝试！！！";
			return readyDetailTestBarcode;
		} finally {
			if (null != ps) {
				ps.close();
			}
			if (null != conn) {
				conn.close();
			}
		}
	}

	/**
	 * 检测当前同步中，未同步成功的台账条码，即旧系统中syncflag为N的台账
	 * 
	 * @param sqlUrl
	 * @param sqlUserName
	 * @param sqlPassword
	 * @param retrieve
	 * @param seclv
	 * @return
	 * @throws Exception
	 */
	public String checkNoSyncFromOldSystem(String sqlUrl, String sqlUserName, String sqlPassword, String retrieve,
			String seclv) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String noSyncBarcode = "";
		String info1 = "";
		String info2 = "";
		try {
			conn = Connections.getSpConnection(sqlUrl, sqlUserName, sqlPassword);
			// todo get all record and change them to data model
			String sql = "select p_file.filebarcode "
					+ "from t_filetype tp,t_printfiles p_file "
					+ "left join (select * from t_printevent) event on p_file.printfileno = event.fileno "
					+ "left join (select * from t_user) u on u.id = event.userid "
					+ "where event.filetype is not null  and event.filetype = tp.id and u.account is not null and syncflag='N'";
			if (retrieve.equals("nohandle")) {
				sql += "and (handleresult is null or handleresult=1) ";
			}
			if (seclv.equals("1")) {
				sql += "and (tp.name='机密') ";
			} else if (seclv.equals("2")) {
				sql += "and (tp.name='机密' or tp.name='秘密') ";
			} else if (seclv.equals("3")) {
				sql += "and (tp.name != '内部' and tp.name != '非密') ";
			} else if (seclv.equals("4")) {
				sql += "and tp.name != '非密' ";
			}
			sql += "order by tp.name,u.account desc";
			ps = conn.prepareStatement(sql);
			System.out.println(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				noSyncBarcode += rs.getString("filebarcode") + ",";
			}
			if (retrieve.equals("nohandle")) {
				info1 = "未闭环处理过的";
			}
			if (seclv.equals("1")) {
				info2 = "机密";
			} else if (seclv.equals("2")) {
				info2 = "机密、秘密";
			} else if (seclv.equals("3")) {
				info2 = "除内部、非密外";
			} else if (seclv.equals("4")) {
				info2 = "除非密外";
			}
			System.out.println("（4）本次同步旧系统中" + info1 + info2 + "的文件台账，未同步成功的台账条码为 ：" + noSyncBarcode);
			if (noSyncBarcode != null && !noSyncBarcode.equals("")) {
				noSyncBarcode = "（4）本次同步旧系统中" + info1 + info2 + "的文件台账，未同步成功的台账条码为 ：" + noSyncBarcode;
			} else {
				noSyncBarcode = "（4）本次同步" + info1 + info2 + "的文件台账成功！";
			}
			// 向C盘日志文件中写入未同步成功的文件条码
			File file = new File("c:\\syncBarcode.log");
			FileWriter writer = new FileWriter(file, true);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			writer.write(sdf.format(new Date()) + "------------------------" + "\r\n" + noSyncBarcode + "\r\n");
			writer.close();
			// 返回值
			return noSyncBarcode;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			noSyncBarcode = "（4）检测未同步成功的台账操作失败，请重新尝试！！！";
			return noSyncBarcode;
		} finally {
			if (null != rs) {
				rs.close();
			}
			if (null != ps) {
				ps.close();
			}
			if (null != conn) {
				conn.close();
			}
		}
	}
}
