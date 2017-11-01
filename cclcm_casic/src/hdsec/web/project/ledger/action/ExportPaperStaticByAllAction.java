package hdsec.web.project.ledger.action;

import hdsec.web.project.basic.mapper.BasicMapper;
import hdsec.web.project.basic.model.PaperStatic;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.ledger.model.PaperType;
import hdsec.web.project.ledger.service.LedgerService;
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
 * 导出纸张统计
 * 
 * @author lixiang
 * 
 */
@SuppressWarnings("deprecation")
public class ExportPaperStaticByAllAction extends LogExportXlsAction {
	private static final long serialVersionUID = 1L;
	@Resource
	protected UserService userService;
	@Resource
	protected BasicService basicService;
	@Resource
	protected BasicMapper basicMapper;

	private String seclv_code = "";
	private String dept_id = "";
	private Date start_time;
	private Date end_time;
	private String create_type = "PRINT";
	List<PaperStatic> paperStaticList = new ArrayList<PaperStatic>();
	private final String sheetName = "纸张统计";
	private final String[] titles = { "序号", "部门名称", "纸张类型", "彩色/黑白", "单双面", "页数", };
	private final String[] second_titles = { "部门名称", "黑白纸张数", "彩色纸张数", "折合纸张类型", "金额", };// 备用
	private HSSFWorkbook wb = new HSSFWorkbook();
	private Integer sum = 0;// 总页数

	public String getCreate_type() {
		return create_type;
	}

	public void setCreate_type(String create_type) {
		this.create_type = create_type;
	}

	@Resource
	private LedgerService ledgerService;

	@Override
	protected void fillData(HSSFSheet arg0, int arg1) {
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
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
		map.put("create_type", create_type);
		paperStaticList = basicService.getPaperStaticListByAll(map);

		for (PaperStatic paper_staitic : paperStaticList) {
			sum += paper_staitic.getPage_count();
		}
		exportFile();
		return null;
	}

	public void exportFile() throws Exception {
		String type;
		if (create_type.equals("PRINT")) {
			type = "打印";
		} else {
			type = "复印";
		}
		String filename = "部门" + type + "纸张统计-" + sdf1.format(new Date()) + ".xls";
		OutputStream os = createFile(filename);
		BufferedOutputStream bos = new BufferedOutputStream(os);
		cellstyle = wb.createCellStyle();
		cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
		// 纸的载体有多少
		int total_size = paperStaticList.size();
		// 总数整除60000+1（求用几个sheet）
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
		// 总的载体数
		int total_size = paperStaticList.size();
		// 纸张类型 （A4）
		int sizeNum = basicMapper.getPageSizeList().size();
		int colorNum = 2;
		int doubleNum = 2;
		int dept_size = sizeNum * colorNum * doubleNum;// 每个部门占几个单元格
		int size_size = colorNum * doubleNum;// 每种纸张占几个单元格
		int color_size = doubleNum;// 每种颜色占几个单元格
		for (int i = 0; i < total_size / dept_size; i++) {
			sh.addMergedRegion(new CellRangeAddress(dept_size * i + 2, dept_size * (i + 1) + 1, 1, 1));
		}
		for (int i = 0; i < total_size / size_size; i++) {
			sh.addMergedRegion(new CellRangeAddress(size_size * i + 2, size_size * (i + 1) + 1, 2, 2));
		}
		for (int i = 0; i < total_size / color_size; i++) {
			sh.addMergedRegion(new CellRangeAddress(color_size * i + 2, color_size * (i + 1) + 1, 3, 3));
		}
		// 插入最后一行（总计）
		insertLastRow(sh, total_size);
		addStatisticsResult(sh, total_size + 4);
		// 总计之后的部门统计
		// 1、查询出可以折合的纸张类型
		List<PaperStatic> paperStaticListCountByDept = new ArrayList<PaperStatic>();
		paperStaticListCountByDept.addAll(paperStaticList);
		paperStaticListCountByDept.add(paperStaticList.get(paperStaticList.size() - 1));
		List<PaperType> paperTypeList = new ArrayList();
		paperTypeList = ledgerService.getPaperConversionRate();
		float black_white = 0;
		float colour = 0;
		int rowNum = total_size + 5;
		for (int i = 0; i < paperStaticListCountByDept.size(); i++) {
			// 判断当前i部门与i+1部门是否是同部门
			if ((i < paperStaticListCountByDept.size() - 1)
					&& (paperStaticListCountByDept.get(i).getDept_id() == paperStaticListCountByDept.get(i + 1)
							.getDept_id())) {
				boolean tag = false;
				for (PaperType item : paperTypeList) {
					// 如果存在相同的则换算
					if (item.getType_name() == paperStaticListCountByDept.get(i).getPage_size()
							|| item.getType_name().equals(paperStaticListCountByDept.get(i).getPage_size())) {
						if (paperStaticListCountByDept.get(i).getColor() == 1) {
							black_white += paperStaticListCountByDept.get(i).getPage_count()
									* item.getConversion_rate();// 折算好的数量
						} else {
							colour += paperStaticListCountByDept.get(i).getPage_count() * item.getConversion_rate();// 折算好的数量
						}
						tag = true;
						break;
					}
				}
				if (tag == false) {
					float black_white_else = 0;
					float colour_else = 0;
					if (paperStaticListCountByDept.get(i).getColor() == 1) {
						black_white_else += paperStaticListCountByDept.get(i).getPage_count();
					} else {
						colour_else += paperStaticListCountByDept.get(i).getPage_count();
					}
					// 写出这一行
					// insertRow(sh, paperStaticList.get(i), rowNum);
					if (black_white_else != 0 || colour_else != 0) {

						addStatisticsResult(sh, rowNum, paperStaticListCountByDept.get(i).getDept_name(),
								ceiling(black_white_else), ceiling(colour_else), paperStaticListCountByDept.get(i)
										.getPage_size());
						rowNum++;
					}
				}
			} else {
				// 输出该部门的合计
				if (black_white != 0 || colour != 0) {
					addStatisticsResult(sh, rowNum, paperStaticListCountByDept.get(i).getDept_name(),
							ceiling(black_white), ceiling(colour), "A4");
					rowNum++;
					black_white = 0;
					colour = 0;
				}
			}
		}
	}

	public void insertFirstRow(HSSFSheet sh) {
		HSSFRow row = sh.createRow(0);
		String name = userService.getDeptNameByDeptId(dept_id) + "纸张统计" + getStart_time() + "——" + getEnd_time();
		for (int i = 0; i < 6; i++) {
			cteateCell(row, i, name);
		}
		sh.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
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
		cteateCell(row, index++, changeNullOrEmpty(paper_static.getColor_name()));
		cteateCell(row, index++, changeNullOrEmpty(paper_static.getPrint_double_name()));
		cteateCell(row, index++, changeNullOrEmpty(String.valueOf(paper_static.getPage_count())));
	}

	private void insertLastRow(HSSFSheet sh, int rownum) {
		int index = 0;
		HSSFRow row = sh.createRow(rownum + 2);
		cteateCell(row, index++, "总计");
		cteateCell(row, index++, " ");
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

	// 添加统计结果
	private void addStatisticsResult(HSSFSheet sh, int rownum) {
		int index = 0;
		HSSFRow row = sh.createRow(rownum + 1);
		cteateCell(row, index++, " ");
		cteateCell(row, index++, "部门 ");
		cteateCell(row, index++, "黑白页数合计");
		cteateCell(row, index++, "彩色页数合计");
		cteateCell(row, index++, "纸张类型");
	}

	private void addStatisticsResult(HSSFSheet sh, int rownum, String dept_name, float black_white, float colour,
			String paperType) {
		int index = 0;
		HSSFRow row_sub = sh.createRow(rownum + 2);
		cteateCell(row_sub, index++, " ");
		cteateCell(row_sub, index++, dept_name);
		cteateCell(row_sub, index++, black_white + "");
		cteateCell(row_sub, index++, colour + "");
		cteateCell(row_sub, index++, paperType);
	}

	private float ceiling(float srcFloat) {
		String tempNumber = String.valueOf(srcFloat).substring(0, String.valueOf(srcFloat).indexOf("."));
		float desNumber = Float.valueOf(tempNumber);
		if (srcFloat > desNumber) {
			return desNumber + 1;
		} else {
			return desNumber;
		}
	}

}
