package hdsec.web.project.ledger.action;

import hdsec.web.project.basic.mapper.BasicMapper;
import hdsec.web.project.basic.model.PaperStatic;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.log.action.LogExportXlsAction;
import hdsec.web.project.user.service.UserService;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

/**
 * 导出按大小纸张统计
 * 
 * @author lixiang
 * 
 */
@SuppressWarnings("deprecation")
public class ExportPaperStaticBySizeAction extends LogExportXlsAction {
	private static final long serialVersionUID = 1L;
	@Resource
	protected UserService userService;
	@Resource
	protected BasicService basicService;
	@Resource
	protected BasicMapper basicMapper;

	private String dept_id = "";
	private Date start_time;
	private Date end_time;
	List<PaperStatic> paperStaticList = null;
	private final String filename = "纸张类型统计-" + sdf1.format(new Date()) + ".xls";
	private final String sheetName = "纸张统计";
	private final String[] titles = { "序号", "部门名称", "纸张类型", "页数", "合计", };
	private HSSFWorkbook wb = new HSSFWorkbook();
	private Integer sum = 0;// 总页数

	@Override
	protected void fillData(HSSFSheet arg0, int arg1) {
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getStart_time() {
		if (start_time != null) {
			return sdf.format(start_time);
		} else
			return "";
	}

	public String getEnd_time() {
		if (end_time != null) {
			return sdf.format(end_time);
		} else
			return sdf.format(new Date());
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dept_id", dept_id);
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		map.put("create_type", "PRINT");
		paperStaticList = basicService.getPaperStaticListBySize(map);
		// 合计
		List<PaperStatic> tempList = new ArrayList<PaperStatic>();
		List<String> deptList = basicMapper.getFirstChildList(dept_id);
		deptList.add(dept_id);
		for (String dept_id : deptList) {
			Integer page_sum = 0;
			for (PaperStatic paper_static : paperStaticList) {
				if (dept_id.equals(paper_static.getDept_id())) {
					page_sum += paper_static.getPage_count();
					sum += paper_static.getPage_count();
				}
			}
			for (PaperStatic paper_static : paperStaticList) {
				if (dept_id.equals(paper_static.getDept_id())) {
					paper_static.setPage_sum(page_sum);
					tempList.add(paper_static);
				}
			}
		}
		paperStaticList.clear();
		paperStaticList.addAll(tempList);
		exportFile();
		return null;
	}

	public void exportFile() throws Exception {
		OutputStream os = createFile(filename);
		BufferedOutputStream bos = new BufferedOutputStream(os);
		cellstyle = wb.createCellStyle();
		cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
		int total_size = paperStaticList.size();
		int count = total_size / CCLCMConstants.SHEET_SIZE + 1;
		int begin = 0;
		for (int i = 0; i < count; i++) {
			begin = i * CCLCMConstants.SHEET_SIZE;
			if (count - 1 != i) {
				insertIntoOneSheet(i, i * CCLCMConstants.SHEET_SIZE - begin, (i + 1) * CCLCMConstants.SHEET_SIZE - 1
						- begin, sheetName, titles);
			} else {
				insertIntoOneSheet(i, i * CCLCMConstants.SHEET_SIZE - begin, total_size - 1 - begin, sheetName, titles);
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

	public void insertIntoOneSheet(int pageIndex, int from, int to, String sheetName, String[] titles)
			throws IOException {
		HSSFSheet sh = wb.createSheet(sheetName + pageIndex);
		insertFirstRow(sh);
		addTitile(sh, titles);
		for (int i = from; i <= to; i++) {
			PaperStatic paper_static = paperStaticList.get(i);
			insertRow(sh, paper_static, i - from + 2);
		}
		// 合并单元格
		int total_size = paperStaticList.size();
		int sizeNum = basicMapper.getPageSizeList().size();
		int deptNum = total_size / sizeNum;
		for (int i = 0; i < deptNum; i++) {
			sh.addMergedRegion(new CellRangeAddress(sizeNum * i + 2, sizeNum * (i + 1) + 1, 1, 1));
			sh.addMergedRegion(new CellRangeAddress(sizeNum * i + 2, sizeNum * (i + 1) + 1, 4, 4));
		}
		// 插入最后一行（总计）
		insertLastRow(sh, total_size);
	}

	public void insertFirstRow(HSSFSheet sh) {
		HSSFRow row = sh.createRow(0);
		String name = userService.getDeptNameByDeptId(dept_id) + "纸张统计" + getStart_time() + "——" + getEnd_time();
		for (int i = 0; i < 5; i++) {
			cteateCell(row, i, name);
		}
		sh.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
	}

	public void addTitile(HSSFSheet sh, String[] titles) {
		HSSFRow row = sh.createRow(1);
		int index = 0;
		for (String str : titles) {
			cteateCell(row, index++, str);
		}
	}

	private void insertRow(HSSFSheet sh, PaperStatic paper_static, int rownum) {
		int index = 0;
		HSSFRow row = sh.createRow(rownum);
		cteateCell(row, index++, rownum - 1 + "");
		cteateCell(row, index++, changeNullOrEmpty(paper_static.getDept_name()));
		cteateCell(row, index++, changeNullOrEmpty(paper_static.getPage_size()));
		cteateCell(row, index++, changeNullOrEmpty(String.valueOf(paper_static.getPage_count())));
		cteateCell(row, index++, changeNullOrEmpty(String.valueOf(paper_static.getPage_sum())));
	}

	private void insertLastRow(HSSFSheet sh, int rownum) {
		int index = 0;
		HSSFRow row = sh.createRow(rownum + 2);
		cteateCell(row, index++, "总计");
		cteateCell(row, index++, " ");
		cteateCell(row, index++, " ");
		cteateCell(row, index++, " ");
		cteateCell(row, index++, changeNullOrEmpty(String.valueOf(sum)));
	}

	private String changeNullOrEmpty(String str) {
		if (null == str || str.length() == 0) {
			return " ";
		} else {
			return str;
		}
	}

}
